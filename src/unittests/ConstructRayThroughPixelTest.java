package unittests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

public class ConstructRayThroughPixelTest {
	/**
     *  Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
	@Test
    public void testConstructRayThroughPixel()
    {
		int Nx = 3;
		int Ny = 3;
		int W = 3;
		int H = 3;
		int count =0;
		Camera camera1 = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, 1)), new Vector(new Point3D(0, -1, 0)));
		Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(new Point3D(0, 0, 1)), new Vector(new Point3D(0, -1, 0)));

		//****Group test Sphere
		//TC01: (2 points)
		Sphere sphere1 = new Sphere(new Point3D(0, 0, 3), 1);
		List<GeoPoint> result1 = new LinkedList<GeoPoint>();
		for(int j=0; j<Nx; j++)
		{
			for(int i=0; i<Ny; i++)
			{
				result1 = sphere1.findIntersections(camera1.constructRayThroughPixel(3, 3, j, i, 1, W, H));	
				if(result1 != null)
					count += result1.size();
			}
		}
		assertEquals("Wrong number of points", 2, count);
 
		//TC02:(18 points)
		count =0;
		Sphere sphere2 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
		List<GeoPoint> result2 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result2 = sphere2.findIntersections(camera2.constructRayThroughPixel(3, 3, i, j, 1, W, H));
				if(result2 != null)
					count += result2.size();
			}
		}
		assertEquals("Wrong number of points", 18, count);
		
		//TC03:(10 points)
		count = 0;
		Sphere sphere3 = new Sphere(new Point3D(0, 0, 2), 2);
		List<GeoPoint> result3 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result3 = sphere3.findIntersections(camera2.constructRayThroughPixel(3, 3, i, j, 1, W, H));	
				if(result3 != null)
					count += result3.size();
			}
		}
		assertEquals("Wrong number of points", 10, count);
		
		//TC04:(9 points)
		count = 0;
		Sphere sphere4 = new Sphere(new Point3D(0, 0, 2), 4);
		List<GeoPoint> result4 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result4 = sphere4.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H));
				if(result4 != null)
					count += result4.size();
			}
		}
		assertEquals("Wrong number of points", 9, count);
		
		//TC05: (0 points)
		count = 0;
		Sphere sphere5 = new Sphere(new Point3D(0, 0, -1), 0.5);
		List<GeoPoint> result5 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result5 = sphere5.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H));
				if(result5 != null)
					count += result5.size();
			}
		}
		assertEquals("Wrong number of points", 0, count);
		
		//TC06: (9 points)
		count = 0;
		Plane plane1 = new Plane(new Point3D(0, 0, 4), new Vector(0, 0, 1)); 
		List<GeoPoint> result6 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result6 = plane1.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H));
				if(result6 != null)
					count += result6.size();
			}
		}
		assertEquals("Wrong number of points", 9, count);
		
		
		//TC07: (9 pounts)
		count = 0;
		Plane plane2 =new Plane (new Point3D(0,0,3), new Vector(4,-1,-15));
		List<GeoPoint> result7 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result7 = plane2.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H));
				if(result7 != null)
					count += result7.size();
			}
		}
		assertEquals("Wrong number of points", 9, count);
		
		//TC08: (6 points)
		count = 0;
		Plane plane3 =new Plane(new Point3D(0,2,3), new Vector(0,5,5));
		List<GeoPoint> result8 = new LinkedList<GeoPoint>();
		for(int j=0; j<Nx; j++)
		{
			for(int i=0; i<Ny; i++)
			{
				result8 = plane3.findIntersections(camera1.constructRayThroughPixel(3, 3, j, i, 1, W, H));
				if(result8 != null)
					count += result8.size();
			}
		}
		assertEquals("Wrong number of points", 6, count);
		
		//TC09: (1 points)
		count = 0;
		Triangle triangle1 = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		List<GeoPoint> result9 = new LinkedList<GeoPoint>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result9 = triangle1.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H));
				if(result9 != null)
					count += result9.size();
			}
		}
		assertEquals("Wrong number of points", 1, count);
		
		//TC10: (2 points)
		count = 0;
				Triangle triangle2 = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
				List<GeoPoint> result10 = new LinkedList<GeoPoint>();
				for(int i=0; i<Nx; i++)
				{
					for(int j=0; j<Ny; j++)
					{
						result10 = triangle2.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H));
						if(result10 != null)
							count += result10.size();
					}
				}
				assertEquals("Wrong number of points", 2, count);
		
    }

}



