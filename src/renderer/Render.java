package renderer;

import java.util.List;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

public class Render 
{
	ImageWriter _imageWriter;
	Scene _scene;
	
	public Render(ImageWriter imageWriter, Scene scene)
	{
		_imageWriter = imageWriter;
		_scene = scene;
	}
	
	public ImageWriter getImageWriter()
	{
		return _imageWriter;
	}
	
	public void renderImage()
	{
		Camera camera = _scene.getCamera();
		Intersectable geometries = _scene.getGeometries();
		java.awt.Color background = _scene.getBackground().getColor();
		int nX = _imageWriter.getNx();
		int nY = _imageWriter.getNy();
		for (int i=0; i<nX; i++)
		{
			for(int j=0; j<nY; j++)
			{
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.getDistance(), _imageWriter.getWidth()
						, _imageWriter.getHeight());
				List<Point3D> intersectionPoints = geometries.findIntersections(ray);
				if (intersectionPoints.isEmpty())
					_imageWriter.writePixel(j, i, background);
				else
				{
					Point3D closestPoint = getClosestPoint(intersectionPoints);
					_imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
		}


	}
	public Color calcColor(Point3D p)
	{
		return _scene.getAmbientLight().GetIntensity();
	}
	public Point3D getClosestPoint(List<Point3D> points)//:Point3D
	{
		double distance = Double.MAX_VALUE;
		Point3D p0 = _scene.getCamera().getP0();
		Point3D minDistancePoint = null;
		for(Point3D point:points)
		{
			if(p0.distance(point) < distance)
			{
				minDistancePoint = new Point3D(point);
				distance = p0.distance(point);
			}
		}
		return minDistancePoint;
	}
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
}
