package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;

public class PlaneTests {

/*
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

	

	@Test
	public void testGetNormal() {
		 Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
	                new Point3D(-1, 1, 1));
	        double sqrt3 = Math.sqrt(1d / 3);
	        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
	}

}
