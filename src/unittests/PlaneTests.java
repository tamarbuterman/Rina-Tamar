package unittests;

import static org.junit.Assert.*;

import primitives.Vector;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;

public class PlaneTests {

	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNormal() {
		Plane p1 = new Plane(new Point3D(1,2,3), new Point3D(3, 4, 5), new Point3D(3,7,9));
		Plane p2 = new Plane(new Point3D(1, 2, 3), new Vector(1, 2, 3));		
		
		// ============ Equivalence Partitions Tests ==============
		Vector temp = new Vector(2/Math.sqrt(104), -8/Math.sqrt(104), 6/Math.sqrt(104));
		assertEquals("getNormal() result is not expected", temp, p1.getNormal(new Point3D(0, 0, 0)));
		assertEquals("getNormal() result is not expected", new Vector(1, 2, 3), p2.getNormal(new Point3D(0, 0, 0)));
		
		// =============== Boundary Values Tests ==================
		Plane p3 = new Plane(new Point3D(1,2,3), new Point3D(1, 2, 3), new Point3D(3,7,9));
		try {
		        p3.getNormal(new Point3D(1,1,1));
		        fail("getNormal() for parallel vectors does not throw an exception");
		    } catch (Exception e) {}
	}

}
