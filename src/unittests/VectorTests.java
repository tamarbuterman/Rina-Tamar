/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

/**
 * Unit tests for primitives.Vector class
 * @author Rina and Tamar
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Coordinate, primitives.Coordinate, primitives.Coordinate)}.
	 */
	@Test
	public void testVectorCoordinateCoordinateCoordinate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 * 
	 *-------------------------------------------------------------------
	 */
	@Test
	public void testVectorDoubleDoubleDouble() {
		
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Point3D)}.
	 */
	@Test
	public void testVectorPoint3D() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Vector)}.
	 */
	@Test
	public void testVectorVector() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#get()}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		fail("Not yet implemented");
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    Vector v3 = new Vector(3, 6, 9);
		Vector temp = v1.subtract(v2);
		assertEquals("", temp, v3);
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    Vector v3 = new Vector(-1, -2, -3);
		Vector temp = v1.add(v2);
		assertEquals("", temp, v3);
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		fail("Not yet implemented");
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
		Vector temp = v1.scale(-2);
		assertEquals("", temp, v2);
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(v1);
		Vector v2Normalize = v2.normalize();
		assertEquals ("ERROR: normalize() function creates a new vector", v1, v2Normalize);
		assertTrue("ERROR: normalize() result is not a unit vector", v2Normalize.length() - 1 == 0);
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        assertFalse("ERROR: normalizated() function does not create a new vector", u == v);
	}

	/**
	 * Test method for {@link primitives.Vector#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
