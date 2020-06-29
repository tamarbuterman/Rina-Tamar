package renderer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;;


public class Render 
{
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	
	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;


	/**
	 * the size of the pixel and the image
	 */
	ImageWriter _imageWriter;
	/**
	 * Scene class which includes the colors of the scene, source lights, camera and more data about the scene
	 */
	Scene _scene;
	
	private class Pixel
	{
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols)
		{
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}
		
		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target)
		{
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}
	
		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	
	
	
	/**
	 * Render constructor gets imageWriter and scene
	 * 
	 * @param imageWriter
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene)
	{
		_imageWriter = imageWriter;
		_scene = scene;
	}
	
	/**
	 * render getter returns the imageWriter
	 * 
	 * @return imageWriter
	 */
	public ImageWriter getImageWriter()
	{
		return _imageWriter;
	}
	
	/**
	 * renderImage function creates the image and paint the pixels
	 * 
	 */
	public void renderImage()
	{
		Camera camera = _scene.getCamera();		
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		
		final Pixel thePixel = new Pixel(nY, nX);
		
		// Generate threads
		Thread[] threads = new Thread[_threads];
		
		for (int i = _threads - 1; i >= 0; --i)
		{
			threads[i] = new Thread(() -> 
			{
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel))
				{	
					Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, _scene.getDistance(), _imageWriter.getWidth()
							, _imageWriter.getHeight());
					Color color;
					if(camera.getWidthSh() == 0 && camera.getHeightSh() == 0)
					{
					    GeoPoint closestpoint = findCLosestIntersection(ray);
					    
						color = closestpoint == null? _scene.getBackground():calcColor(closestpoint,ray);
						//_imageWriter.writePixel(pixel.col, pixel.row, color.getColor());

					}
					else
					{
					Point3D pScreen = new Point3D(camera.getCenterOfPixel(nX, nY, pixel.col, pixel.row, _scene.getDistance(), _imageWriter.getWidth(), _imageWriter.getHeight()));
					Point3D focalPoint = findFocalPoint(pScreen, _scene.getFocalPlaneDistance()-_scene.getDistance(), ray.getDirectiion().normalized(), camera.getVto().normalized());
					//mini project 2
					List<Point3D> ps = camera.getPointsPixel(pScreen, camera.getWidthSh(), camera.getHeightSh()/*, nX, nY,  _imageWriter.getWidth(), _imageWriter.getHeight()*/); // returns 4 points the edges of the pixel
					color = calcColorPixel4(ps, 1, focalPoint); // sends to the superSempling function
					
					//mini project 1
					//Point3D focalPoint = findFocalPoint(pScreen, _scene.getFocalPlaneDistance()-_scene.getDistance(), ray.getDirectiion().normalized(), camera.getVto().normalized());
					//List<Ray> rays  = (createFocalRays(focalPoint, camera, /*_scene.getDistance(),*/ pScreen));
					//rays.add(ray);
					//Color color = colorPixel(rays); 
					}
					_imageWriter.writePixel(pixel.col, pixel.row, color.getColor());
				}
			});
		}
		
		// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
	}
	
	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}
	
	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() 
	{
		_print = true;
		return this;
	}

	
	/**
	 * calculates the specular color
	 *  
	 * @param ks
	 * @param l
	 * @param n
	 * @param nl
	 * @param V
	 * @param nShininess
	 * @param ip
	 * @return
	 */
	private Color calcSpecular(double ks, Vector d, Vector n, Vector v, int nExponent, Color il)
	{
	    double nd = alignZero(n.dotProduct(d));
	    Vector r = d.add(n.scale(-2 * nd));
	       double minusVR = alignZero(r.dotProduct(v) * (-1));
	       if (minusVR <= 0) return Color.BLACK; // view from direction opposite to r vector
	       return il.scale(ks * Math.pow(minusVR, nExponent));
	   }

	/**
	 * 
	 * @param kd
	 * @param nl
	 * @param il
	 * @return
	 */
	private Color calcDiffusive(double kd, double nl, Color il) {
	       if (nl < 0) nl = -nl;
	       return il.scale(nl * kd);
	   }

		/**
		 * 
		 * @param val
		 * @return
		 */
	   private boolean sign(double val) {
	       return (val > 0d);
	   }
	   
	   /**
	    * 
	    * @param geopoint
	    * @param inRay
	    * @return
	    */
	   private Color calcColor(GeoPoint geopoint, Ray inRay) 
	   {
		   return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(
		   _scene.getAmbientLight().getIntensity());
		   }
	/**
	 * calculate the color of pixel according to the geometry and returns the color of the pixel
	 * 
	 * @param p
	 * @return Color
	 */
	
	private Color calcColor(GeoPoint gp,Ray inRay, int level, double k)
	{
		if (level == 1||k < MIN_CALC_COLOR_K) 
	    	   return Color.BLACK;
	       Color result = new Color(gp.getGeometry().getEmission());
	       Vector v = gp.getPoint().subtract(_scene.getCamera().getP0()).normalize();
	       Vector n = gp.getGeometry().getNormal(gp.getPoint());
	       if(alignZero(n.dotProduct(v)) == 0)
	    	   return result;

	       Material material = gp.getGeometry().getMaterial();
	       int nShininess = material.getShin();
	       double kd = material.getKD();
	       double ks = material.getKS();
	       
	       result = result.add(getLightSource(gp, k, result,v,n,nShininess,kd,ks));
	       
	       double kr = gp.getGeometry().getMaterial().getKR();
	       double kkr = k * kr;
	       if (kkr > MIN_CALC_COLOR_K) 
	       {
	    	   Ray reflectedRay = constructReflectedRay(gp.getPoint(), inRay,n);
	    	   GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
	    	   if (reflectedPoint != null)
	    		   result = result.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
	       }
	       double kt = gp.geometry.getMaterial().getKT();
	       double kkt = k * kt;
	       if (kkt > MIN_CALC_COLOR_K) 
	       {
	    	   Ray refractedRay = constructRefractedRay(gp.getPoint(), inRay,n) ;
	    	   GeoPoint refractedPoint = findCLosestIntersection(refractedRay);
	    	   if (refractedPoint != null)
	    		   result = result.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
	       }
	       return result;
	       
	   }
	       


	private Color getLightSource(GeoPoint geoPoint, double k, Color result, Vector v, Vector n, int nShininess, double kd, double ks)
	{
		if (_scene.getLights() != null)
	       {
	           for (LightSource lightSource : _scene.getLights())
	           {

	               Vector l = lightSource.getL(geoPoint.getPoint());
	               double nl = alignZero(n.dotProduct(l));
	               double nv = alignZero(n.dotProduct(v));

	               if(nl * nv > 0)
	               {

	            	   double ktr = transparency(lightSource, l, n, geoPoint);
	            	   if (ktr * k > MIN_CALC_COLOR_K) 
	            	   {
	            		   	Color ip = lightSource.getIntensity(geoPoint.getPoint());//.scale(ktr);
	            		   	result = result.add(
	                        calcDiffusive(kd, nl, ip),
	                        calcSpecular(ks, l, n, v, nShininess, ip));
	            	   }
                    }  
	               
	           }
	       }
	       return result;
	}
	/**
	 * gets list of points and find the closet point to the camera
	 * 
	 * @param points
	 * @return GeoPoint
	 */
	public GeoPoint getClosestPoint(List<GeoPoint> points)//:Point3D
	{
		double distance = Double.MAX_VALUE;
		Point3D p0 = _scene.getCamera().getP0();
		GeoPoint minDistancePoint = null;
		for(GeoPoint point:points)
		{
			if(p0.distance(point.getPoint()) < distance)
			{
				minDistancePoint = point;
				distance = p0.distance(point.getPoint());
			}
		}
		return minDistancePoint;
	}
	
	/**
	 * prints the grid, going over each pixel and print the color
	 * 
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, java.awt.Color color)
	{
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		double w = _imageWriter.getWidth();
		double h = _imageWriter.getHeight();
		for(int i = 0; i< nY; i++)
		{	
			for(int j= 0;j < nX; j++)
			{
				if(j % (nX/(h/100))==0 || i % (nY/(w/100)) == 0)
					_imageWriter.writePixel(i, j, color);
			}
		}
	}
	
	/**
	 * sends to the writeToImage function
	 */
	public void writeToImage() {
        _imageWriter.writeToImage();
    }
	
	/**
	 * 
	 * @param pointGeo
	 * @param inRay
	 * @param n
	 * @return
	 */
	private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay._direction, n);
    }
	
/**
 * 
 * @param pointGeo
 * @param inRay
 * @param n
 * @return
 */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        Vector v = inRay._direction;
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }
    
    /**
     * 
     * @param ray
     * @return
     */
    private GeoPoint findCLosestIntersection(Ray ray)
    {
    	 if (ray == null) {
             return null;
         }

         GeoPoint closestPoint = null;
         double closestDistance = Double.MAX_VALUE;
         Point3D ray_p0 = ray.getPoint();

         List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
         if (intersections == null)
             return null;

         for (GeoPoint geoPoint : intersections) {
             double distance = ray_p0.distance(geoPoint.getPoint());
             if (distance < closestDistance) {
                 closestPoint = geoPoint;
                 closestDistance = distance;
             }
         }
         return closestPoint;
    }
    
	/**
	 * Check if the point unshaded.
	 * 
	 * @param l
	 * @param n
	 * @param gp
	 * @return true if the point unshaded.
	 */
    
	private boolean unshaded(Vector l, Vector n, GeoPoint gp, LightSource lightSource)
	{
		//Vector lightDirection = l.scale(-1);
		//lightDirection.normalized();
		//Vector delta = n.scale(n.dotProduct(lightDirection) >= 0 ? DELTA : - DELTA);
		//Point3D point = gp.getPoint().add(delta);
		//Ray lightRay = new Ray(point, lightDirection);
		Vector lDir = l.scale(-1);
		Ray shadowRay = new Ray(gp.point, lDir, n);

		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(shadowRay);
		
		if (intersections==null)
			return true;
		double distance = lightSource.getDistance(gp.getPoint());
		for (GeoPoint g : intersections)
		{
			if(alignZero(g.getPoint().distance(gp.getPoint())-distance)<=0
					&&gp.geometry.getMaterial().getKT() == 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param ls
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint)
	{
		Vector lDir = l.scale(-1);
		Ray shadowRay = new Ray(geopoint.getPoint(), lDir, n);

		List<GeoPoint> intersections = _scene.getGeometries().findIntersections(shadowRay);
		
		if (intersections==null)
			return 1d;
		double distance = ls.getDistance(geopoint.getPoint());
		double ktr = 1d;
		for (GeoPoint g : intersections)
		{
			if(alignZero(g.getPoint().distance(geopoint.getPoint())-distance)<=0)
			{
				ktr *= geopoint.getGeometry().getMaterial().getKT();
                if (ktr < MIN_CALC_COLOR_K) 
                    return 0.0;
			}

		}
		 return ktr;
	}
	
	
	/**
	 * 
	 * @param p
	 * @param dis
	 * @param vTo
	 * @return intersection point in the focal plane
	 */
	private Point3D findFocalPoint(Point3D pScreen, double dis, Vector direction, Vector vTo)
	{
		double t = dis/(direction.dotProduct(vTo));
		return new Point3D(pScreen.add(direction.scale(t)));
	}
	
	/**
	 * Creates focus rays
	 * 
	 * @param focalPoint
	 * @param camera
	 * @param dis
	 * @param pScreen
	 * @return list of rays
	 */
	private List<Ray> createFocalRays(Point3D focalPoint, Camera camera, /*double dis,*/ Point3D pScreen)
	{
		List<Ray> ray = new LinkedList<Ray>();
		try
		{
			//Vector v1 = new Vector(camera.getVup().scale(camera.getHeightSh()/2));
			//Vector v2 = new Vector(camera.getVright().scale(camera.getWidthSh()/2));
			//Vector vu = new Vector(camera.getVup());
			//Vector vr = new Vector(camera.getVright());
		
			
			/*points.add(pScreen.add(v1).add(v2));
			points.add(pScreen.add(v1.scale(-1)).add(v2.scale(-1)));
			points.add(pScreen.add(v1.scale(-1)).add(v2));
			points.add(pScreen.add(v1).add(v2.scale(-1)));*/
			
			List<Point3D> points = new LinkedList<Point3D>();
	    	double xStart = pScreen.getX() - camera.getWidthSh() / 2;
	    	double xEnd = pScreen.getX() + camera.getWidthSh() / 2;
	    	double yStart = pScreen.getY() - camera.getHeightSh() / 2;
	    	double yEnd = pScreen.getY() + camera.getHeightSh() / 2;
    		double z = pScreen.getZ();
	    	for(int i = 0; i < 16; i ++)
	    	{
	    		double x = (double) ((Math.random()*(xEnd - xStart + 1)) + xStart);
	    		double y = (double) ((Math.random()*(yEnd - yStart + 1)) + yStart);
		    	points.add(new Point3D(x, y, z));
	    	}
	    	
	    	points.add(new Point3D(xEnd, yEnd, z));
	    	points.add(new Point3D(xEnd, yStart, z));
	    	points.add(new Point3D(xStart, yEnd, z));
	    	points.add(new Point3D(xStart, yStart, z));
	    	
	    	for(Point3D point: points)
			{
				ray.add(new Ray(point, new Vector(focalPoint.subtract(point))));
			}
	    	
	    	//points.add(viewPoint);
	    	
	    	
			/*for(int i =2; i<5; i++)
			{
				Vector v1 = new Vector(vu.scale(camera.getHeightSh()/i));
				Vector v2 = new Vector(vr.scale(camera.getWidthSh()/i));
				points.add(pScreen.add(v1.add(v2)));
				points.add(pScreen.add(v1.scale(-1)).add(v2.scale(-1)));
				points.add(pScreen.add(v1.scale(-1)).add(v2));
				points.add(pScreen.add(v1).add(v2.scale(-1)));
			}*/
			
			
			/*int j=-1;
			for(int k =0; k<4; k++)
			{
				Vector v = new Vector(k<2? camera.getVup(): camera.getVright());
				double d = k<2? camera.getHeightSh()/101 : camera.getWidthSh()/101 ; 
				for(int i=1; i<=100; i++)
				{
					points.add(new Point3D(points.get(k).add(v.scale(j*i*d))));
				}
				j = j*-1;
			}*/
				
			
		}
		catch(IllegalArgumentException e)
		{
			return new LinkedList<Ray>();
		}
		return ray;
	}
	
	/**
	 * Calculate the color of each pixel by the extra rays
	 * 
	 * @param ray
	 * @return Color pixel
	 */
	private Color colorPixel(List<Ray> rays)
	{
		int count =1;
		Ray r = rays.remove(0);
		GeoPoint closestPoint = findCLosestIntersection(r);
		Color color = (closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, r));
		for(Ray ray: rays)
		{
			closestPoint = findCLosestIntersection(ray);
			Color c = closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, ray);
			if(!(c.getColor().equals(color.getColor())))
			{
				color = color.add(c);
				count++;
			}
		}
		return color.reduce(count);
    }
	
	private Color calcColorPixel4(List<Point3D> points, int level, Point3D focalPoint)
	{
		
		Camera camera = _scene.getCamera();
		
		double width = camera.getWidthSh();
		double height = camera.getHeightSh();
		
		List<Ray> rays = camera.constructRaysThroughPixel(points, focalPoint);
		Ray r = rays.remove(0);
		GeoPoint closestPoint = findCLosestIntersection(r);
		Color cr = (closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, r));
		
		if(level>=3)
		{
			return cr;
		}
		
		cr.reduce(4);
		Color color = cr;
		for(Ray ray:rays)
		{
			closestPoint = findCLosestIntersection(ray);
			Color c = (closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, ray)).reduce(4);
			if(!c.getColor().equals(cr.getColor()))
			{
				List<Point3D> centerP = findCenterNewPixels(points);
				Color colors = new Color(0,0,0);
				for(Point3D p: centerP)
				{
					List<Point3D> newPoints = camera.getPointsPixel(p, width/(Math.pow(2, level)), height/(Math.pow(2, level)));
					colors = colors.add(calcColorPixel4(newPoints, level+1, focalPoint).reduce(4));
				}
				return colors;
			}
			color = color.add(c);
		}
		
		return color;
	}
	
	private List<Point3D> findCenterNewPixels(List<Point3D> points)
	{
		Vector vUp = _scene.getCamera().getVup().normalized();
		Vector vRight = _scene.getCamera().getVright().normalized();
		double w = points.get(0).distance(points.get(3));
		double h = points.get(0).distance(points.get(2));
		List<Point3D> centerPoints = new LinkedList<Point3D>();
		centerPoints.add(points.get(0).add(vRight.scale(w/-4)).add(vUp.scale(h/-4)));
		centerPoints.add(points.get(3).add(vRight.scale(w/4)).add(vUp.scale(h/-4)));
		centerPoints.add(points.get(2).add(vRight.scale(w/-4)).add(vUp.scale(h/4)));
		centerPoints.add(points.get(1).add(vRight.scale(w/4)).add(vUp.scale(h/4)));
		return centerPoints;
	}
	
}

