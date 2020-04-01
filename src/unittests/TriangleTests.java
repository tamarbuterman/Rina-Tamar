package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;

class TriangleTests {

	@Test
	void testGetNormal() {
		Triangle p1 = new Triangle(new Point3D(1,2,3), new Point3D(3, 4, 5), new Point3D(3,7,9));
		
		// ============ Equivalence Partitions Tests ==============
		Vector temp = new Vector(2/Math.sqrt(104), -8/Math.sqrt(104), 6/Math.sqrt(104));
		assertEquals("getNormal() result is not expected", temp, p1.getNormal(new Point3D(0, 0, 0)));
		
	}

}
