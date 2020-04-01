package unittests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

/**
 * Testing Sphere
 * @author Tamar and Rina
 */

class SphereTests {


    /**
     * Test method for {@link geometries.Sphere#getNormal(Point3D)}.
     */
	@Test
	void testGetNormal() {
		Sphere s1 = new Sphere(new Point3D(0, 0, 0), 5);
		
		// ============ Equivalence Partitions Tests ==============
		Vector temp = new Vector(0, 4, 0);
		assertEquals(temp, s1.getNormal(new Point3D(0, 4, 0)), "getNormal() result is not expected");
	}

}
