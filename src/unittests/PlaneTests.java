package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class PlaneTests {

	/**
	 * Testing Plan
	 * @author Tamar and Rina
	 *
	 */
	@Test
	public void testConstructor()
	{
		
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct vertex

		try {
            new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }
		
        // =============== Boundary Values Tests ==================

		//TC02: Last point = seconed point
		
		try {
            new Plane(new Point3D(0, 0, 1), new Point3D(0,1, 0),
                    new Point3D(0, 1, 0));
        	  fail("Constructed a plane with vertice on a side");
        } catch (IllegalArgumentException e) {}
        

	}

	
	/**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		//Test for normal Plane
		 Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
	                new Point3D(-1, 1, 1));
	        double sqrt3 = Math.sqrt(1d / 3);
	        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	 @Test
	 public void testFindIntersections()
	 {
		 Plane plane = new Plane(new Point3D(1, 0, 0), new Vector (0, 0, 1));
		 
		 // ============ Equivalence Partitions Tests ==============
		// TC01:Ray intersects the plane (1 point)
	        List<Point3D> result = plane.findIntersections(new Ray(new Point3D(-2, 0.5, -1), new Vector(1, 1, 1)));
	        assertEquals("Wrong number of points", 1, result.size());
	        assertEquals("Ray intersects the plane",List.of(new Point3D(2, 0, 0)), result);
	        
	  // TC02:Ray does not intersect the plane (0 points)
	        assertEquals("Ray not intersects the plane", null,
	                plane.findIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 1))));
	        
	     // =============== Boundary Values Tests ==================
	        
	     // ****Group: Ray is parallel to the plane
	     // TC11: Ray included in the plane (0 points)
	        assertEquals("Ray in the plane", null,
	                plane.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(1, 1, 0))));
	        	        
	     // TC12: Ray not included in the plane (0 points)
	        assertEquals("Ray not included in the plane", null,
	                plane.findIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 0))));
	        
	    // ****Group: Ray is orthogonal to the plane
	    //TC13: Ray starts before the plane (1 ptions)
	        result = plane.findIntersections(new Ray(new Point3D(-4, 3, -2), new Vector(0, 0, 1)));
	        assertEquals("Wrong number of points", 1, result.size());
	        assertEquals("Ray is orthogonal to the plane and starts before the plane",List.of(new Point3D(-4, 3, 0)), result);
	        
	   //TC14: Ray starts in the plane (0 ptions)
	        assertEquals("Ray is orthogonal to the plane and starts in the plane", null,
	                plane.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(0, 0, 1))));
	        
	  //TC15: Ray starts after the plane (0 ptions)
	        assertEquals("Ray is orthogonal to the plane and starts after the plane", null,
	                plane.findIntersections(new Ray(new Point3D(3, 1, 1), new Vector(0, 0, 1))));
	        
	 // ****Group: Special cases
	 //TC16:Ray is neither orthogonal nor parallel to and begins at the plane (0 options)
	        assertEquals("Ray begins at the plane", null,
	                plane.findIntersections(new Ray(new Point3D(3, 1, 0), new Vector(0, 1, 1))));
	 //TC17:  
	        assertEquals("Ray begins at the plane", null,
	                plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 2))));
	        
		 
		 
	 }
}
