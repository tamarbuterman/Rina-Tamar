package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

class TubeTests {

	@Test
	void testGetNormal() {
		Tube t1 = new Tube(new Ray(new Point3D(0,0,0), new Vector(1,2,3)), 3);
		Tube t2 = new Tube(new Ray(new Point3D(7,5,6), new Vector(1,2,3)), 3);
		
		// ============ Equivalence Partitions Tests ==============
		assertEquals("", new Point3D(34, 68, 102), t1.getNormal(new Point3D(2,4,8)));
		
		// =============== Boundary Values Tests ==================
		try {
		         t2.getNormal(new Point3D(7, 5, 6));
		        fail("subtruct() for parallel vectors does not throw an exception");
		    } catch (Exception e) {}
	}

}
