package unittests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import elements.Camera;
import elements.Ray;
import geometries.Sphere;
import primitives.*;

/**
 * Testing Camera Class
 * @author Dan
 *
 */
public class CameraTest {

    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test
    public void testConstructRayThroughPixel() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        
        // ============ Equivalence Partitions Tests ==============
        // TC01: 3X3 Corner (0,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6));
        
        // TC02: 4X4 Corner (0,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-3, -3, 10)),
                camera.constructRayThroughPixel(4, 4, 0, 0, 10, 8, 8));

        // TC03: 4X4 Side (0,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -3, 10)),
                camera.constructRayThroughPixel(4, 4, 1, 0, 10, 8, 8));

        // TC04: 4X4 Inside (1,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-1, -1, 10)),
                camera.constructRayThroughPixel(4, 4, 1, 1, 10, 8, 8));
        
        // =============== Boundary Values Tests ==================
        // TC11: 3X3 Center (1,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, 0, 10)),
                camera.constructRayThroughPixel(3, 3, 1, 1, 10, 6, 6));

        // TC12: 3X3 Center of Upper Side (0,1)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(0, -2, 10)),
                camera.constructRayThroughPixel(3, 3, 1, 0, 10, 6, 6));

        // TC13: 3X3 Center of Left Side (1,0)
        assertEquals("Bad ray", new Ray(Point3D.ZERO, new Vector(-2, 0, 10)),
                camera.constructRayThroughPixel(3, 3, 0, 1, 10, 6, 6));
        
    }
    
    /**
     * 
     */
	@Test
    public void testConstructRayThroughPixel()
    {
		int Nx = 3;
		int Ny = 3;
		int W = 3;
		int H = 3;
		Camera camera1 = new Camera(new Point3D(0, 0, 0), new Vector(new Point3D(0, 0, 1)), new Vector(new Point3D(0, 1, 0)));
		Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(new Point3D(0, 0, 1)), new Vector(new Point3D(0, 1, 0)));

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
 
		//TC02:
		Sphere sphere2 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
		List<Point3D> result2 = new LinkedList<Point3D>();
		for(int i=0; i<Nx; i++)
		{
			for(int j=0; j<Ny; j++)
			{
				result2.addAll(sphere2.findIntersections(camera1.constructRayThroughPixel(3, 3, i, j, 1, W, H)));		
			}
		}
		assertEquals("Wrong number of points", 2, result2.size());
		
		//TC03:
		
		//TC04:
		
		//TC05:
		
		//TC06:
		
    }

}
