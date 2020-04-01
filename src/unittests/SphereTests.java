package unittests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

class SphereTests {

	@Test
	void testGetNormal() {
		Sphere s1 = new Sphere(new Point3D(0, 0, 0), 5);
		
		// ============ Equivalence Partitions Tests ==============
		Vector temp = new Vector(0, 4, 0);
		assertEquals(temp, s1.getNormal(new Point3D(0, 4, 0)), "getNormal() result is not expected");
		
		// =============== Boundary Values Tests ==================
		try {
				s1.getNormal(new Point3D(0,0,6));
		        fail("getNormal() for parallel vectors does not throw an exception");
		    } catch (Exception e) {}
	}

}
