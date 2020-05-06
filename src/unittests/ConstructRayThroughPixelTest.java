package unittests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
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
		Camera camera1 = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, 1)), new Vector(new Point3D(0, -1, 0)));
		Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(new Point3D(0, 0, 1)), new Vector(new Point3D(0, -1, 0)));

		//****Group test Sphere
		//TC01: (2 points)
		Sphere sphere1 = new Sphere(new Point3D(0, 0, 3), 1);
		List<Point3D> result1 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result1.addAll(sphere1.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 2, result1.size());
 
		//TC02:(18 points)
		Sphere sphere2 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
		List<Point3D> result2 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result2.addAll(sphere2.findIntersections(camera2.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 18, result2.size());
		
		//TC03:(10 points)
		Sphere sphere3 = new Sphere(new Point3D(0, 0, 2), 2);
		List<Point3D> result3 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result3.addAll(sphere3.findIntersections(camera2.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 10, result3.size());
		
		//TC04:(9 points)
		Sphere sphere4 = new Sphere(new Point3D(0, 0, 2), 4);
		List<Point3D> result4 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result4.addAll(sphere4.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 9, result4.size());
		
		//TC05: (0 points)
		Sphere sphere5 = new Sphere(new Point3D(0, 0, -1), 0.5);
		List<Point3D> result5 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result5.addAll(sphere5.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 0, result5.size());
		
		//TC06: (9 points)
		Plane plane1 = new Plane(new Point3D(0, 0, 4), new Vector(0, 0, 1)); 
		List<Point3D> result6 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result6.addAll(plane1.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 9, result6.size());
		
		
		//TC07: (9 pounts)
		Plane plane2 =new Plane (new Point3D(0,0,3), new Vector(4,-1,-15));
		List<Point3D> result7 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result7.addAll(plane2.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 9, result7.size());
		
		//TC08: (6 points)
		Plane plane3 =new Plane(new Point3D(0,2,3), new Vector(0,5,5));
		List<Point3D> result8 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result8.addAll(plane3.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 8, result8.size());
		
		//TC09: (1 points)
		Triangle triangle1 = new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
		List<Point3D> result9 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result9.addAll(triangle1.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 1, result9.size());
		
		//TC10: (2 points)
				Triangle triangle2 = new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
				List<Point3D> result10 = new LinkedList<Point3D>();
				for(int i=0; i<Nx; i++)
				{
					for(int j=0; j<Ny; j++)
					{
						result10.addAll(triangle2.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
					}
				}
				assertEquals("Wrong number of points", 2, result10.size());
		
    }

}



