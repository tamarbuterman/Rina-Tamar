package renderer;

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
import static primitives.Util.alignZero;
import static primitives.Util.isZero;;


public class Render 
{
	private static final double DELTA = 0.1;

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
		Intersectable geometries = _scene.getGeometries();
		java.awt.Color background = _scene.getBackground().getColor();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		for (int i=0; i<nY; i++)
		{
			for(int j=0; j<nX; j++)
			{
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.getDistance(), _imageWriter.getWidth()
						, _imageWriter.getHeight());
				List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
				//if (intersectionPoints.isEmpty())
				//	_imageWriter.writePixel(j, i, background);
				if(intersectionPoints == null)
					_imageWriter.writePixel(j, i, background);
				else
				{
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
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

	
	private Color calcDiffusive(double kd, double nl, Color il) {
	       if (nl < 0) nl = -nl;
	       return il.scale(nl * kd);
	   }


	   private boolean sign(double val) {
	       return (val > 0d);
	   }
	
	/**
	 * calculate the color of pixel according to the geometry and returns the color of the pixel
	 * 
	 * @param p
	 * @return Color
	 */
	
	private Color calcColor(GeoPoint gp) {
	       Color result = new Color(_scene.getAmbientLight().getIntensity());
	       result = result.add(gp.getGeometry().getEmission());

	       Vector v = gp.getPoint().subtract(_scene.getCamera().getP0()).normalize();
	       Vector n = gp.getGeometry().getNormal(gp.getPoint());

	       Material material = gp.getGeometry().getMaterial();
	       int nShininess = material.getShin();
	       double kd = material.getKD();
	       double ks = material.getKS();
	       if (_scene.getLights() != null) {
	           for (LightSource lightSource : _scene.getLights()) {

	               Vector l = lightSource.getL(gp.getPoint());
	               double nl = alignZero(n.dotProduct(l));
	               double nv = alignZero(n.dotProduct(v));

	               if (sign(nl) == sign(nv)) {
	                   Color ip = lightSource.getIntensity(gp.getPoint());
	                   result = result.add(
	                           calcDiffusive(kd, nl, ip),
	                           calcSpecular(ks, l, n, v, nShininess, ip)
	                   );
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
	
	
	


}

