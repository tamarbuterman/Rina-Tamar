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


	/**
	 * the size of the pixel and the image
	 */
	ImageWriter _imageWriter;
	/**
	 * Scene class which includes the colors of the scene, source lights, camera and more data about the scene
	 */
	Scene _scene;
	
	
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
		if(camera.getHeightSh() == 0 && camera.getWidthSh() == 0)
		{
			for (int i=0; i<nY; i++)
			{
				for(int j=0; j<nX; j++)
				{
					Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.getDistance(), _imageWriter.getWidth()
						, _imageWriter.getHeight());
				
					GeoPoint closestPoint = findCLosestIntersection(ray);
					Color color = closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, ray);
					 _imageWriter.writePixel(j, i, color.getColor());
				}
			}
		}
		else
		{
			for (int i=0; i<nY; i++)
			{
				for(int j=0; j<nX; j++)
				{
					Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.getDistance(), _imageWriter.getWidth()
						, _imageWriter.getHeight());
					Point3D focalPoint = findFocalPoint(ray, _scene.getFocalPlane());
					List<Ray> rays = createFocalRays(focalPoint, camera, _scene.getDistance());
					rays.add(ray);
					Color color = colorPixel(rays); 
					_imageWriter.writePixel(j, i, color.getColor());
			}
		}
		}
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
	private Color calcSpecular(double ks, Vector d, Vector n, Vector v, int nExponent, Color il) {
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
	       
	       double kr = gp.geometry.getMaterial().getKR();
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
	            		   	Color ip = lightSource.getIntensity(geoPoint.getPoint()).scale(ktr);
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
	
	
	
	private Point3D findFocalPoint(Ray ray, Plane focalPlane)
	{
		List<GeoPoint> listGp = focalPlane.findIntersections(ray);
		/*if(listGp == null)
			return null;*/
		GeoPoint gp = listGp.get(0);
		return gp.getPoint();
	}
	
	private List<Ray> createFocalRays(Point3D focalPoint, Camera camera, double dis)
	{
		Point3D pScreen = camera.getP0().add(camera.getVto().scale(dis));
		Vector v1 = camera.getVup().scale(camera.getHeightSh()/2);
		Vector v2 = camera.getVright().scale(camera.getWidthSh()/2);
		List<Point3D> points = new LinkedList<Point3D>();
		points.add(pScreen.add(v1.add(v2)));
		points.add(pScreen.add(v1).add(v2.scale(-1)));
		points.add(pScreen.add(v1.scale(-1)).add(v2));
		points.add(pScreen.add(v1.scale(-1)).add(v2.scale(-1)));
		
		List<Ray> ray = new LinkedList<Ray>();
		for(int i=0; i<4; i++)
		{
			ray.add(new Ray(points.get(i), new Vector(focalPoint.subtract(points.get(i)))));
		}
		
		return ray;
	}
	
	private Color colorPixel(List<Ray> ray)
	{
		GeoPoint closestPoint = findCLosestIntersection(ray.get(0));
		Color color = (closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, ray.get(0)));
		for(int i=1; i<5; i++)
		{
			closestPoint = findCLosestIntersection(ray.get(i));
			color.add(closestPoint == null ? _scene.getBackground(): calcColor(closestPoint, ray.get(i)));
		}
		return color;
	}
}

