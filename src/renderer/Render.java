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


			}
		}


	}
	public Color calcColor(Point3D p);
	public Point3D getClosetPoint(List<Point3D> points): Point3D
	public void printGrid(int interval, java.awt.Color color)
}
