package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Tube
 * @author Tamar and Rina
 *
 */

class TubeTests {
	
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
	@Test
	void testGetNormal() {
		Tube t1 = new Tube(new Ray(new Point3D(0,0,0), new Vector(1,0,0)), 3);
		
		// ============ Equivalence Partitions Tests ==============
				//Test for Tube Plane
		Vector v = new Vector(2, 0, 0); 
		assertTrue("",v.equals(t1.getNormal(new Point3D(2,4,8))));
		 
	}

}
