/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Coordinate;
import primitives.Point3D;
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
		// ============ Boundary Value Analysis ==============
        // test zero vector c-tor with coordinates
        try {
            new Vector(new Coordinate(0), new Coordinate(0), new Coordinate(0));
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 * 
	 */
	@Test
	public void testVectorDoubleDoubleDouble() {
		// ============ Boundary Value Analysis ==============
        // test zero vector c-tor with double
        try {
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Point3D)}.
	 */
	@Test
	public void testVectorPoint3D() {
		// ============ Boundary Value Analysis ==============
        // test zero vector c-tor with Point3D
        try {
            new Vector(new Point3D(0, 0, 0));
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
	}	

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {		
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    Vector v3 = new Vector(3, 6, 9);
	    
	    // ============ Equivalence Partitions Tests ==============
		Vector temp = v1.subtract(v2);
		assertEquals("", temp, v3);
		
		// =============== Boundary Values Tests ==================
		try {
		        temp.subtract(v3);
		        fail("subtruct() for parallel vectors does not throw an exception");
		    } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    Vector v3 = new Vector(-1, -2, -3);
	    
	    // ============ Equivalence Partitions Tests ==============
		Vector temp = v1.add(v2);
		assertEquals("add() result is not expected", temp, v3);
		
		// =============== Boundary Values Tests ==================
		try {
		        v1.add(v3);
		        fail("add() for parallel vectors does not throw an exception");
		    } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(1, 2, 3);
	    Vector v2 = new Vector(-2, -4, -6);
	    
	    // ============ Equivalence Partitions Tests ==============
		Vector temp = v1.scale(-2);
		assertEquals("scale() result is not expected", temp, v2);
		
		// =============== Boundary Values Tests ==================
		try {
            v1.scale(0);
            fail("scale() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
		
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        
     // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(0, 3, -2);
        double re = v1.dotProduct(v3); 
        assertTrue("dotProduct() result is not expected", re == 0);       
        re = v3.dotProduct(v2);
        assertTrue("dotProduct() result is not expected", re == 0);
        
        // ============ Equivalence Partitions Tests ==============
        double result = v1.dotProduct(v2);
        assertTrue("dotProduct() result is not expected", result == -19);
        result = v3.dotProduct(v2);
        assertTrue("dotProduct() result is not expected", result == -19);
        
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", vr.dotProduct(v1) == 0);
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", vr.dotProduct(v3) == 0);

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		Vector v = new Vector(1, 2, 3);
		
        // ============ Equivalence Partitions Tests ==============
        assertTrue("ERROR: lengthSquared() wrong value", v.lengthSquared() == 14);
        
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		Vector v = new Vector(0, 3, 4);
		
		// ============ Equivalence Partitions Tests ==============
		assertTrue("ERROR: length() wrong value", v.lengthSquared() == 5);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(v1);
		
		// ============ Equivalence Partitions Tests ==============
		Vector v2Normalize = v2.normalize();
		assertEquals ("ERROR: normalize() function creates a new vector", v2, v2Normalize);
		assertTrue("ERROR: normalize() result is not a unit vector", v2Normalize.length() - 1 == 0);
		
		
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        
     // ============ Equivalence Partitions Tests ==============
        assertFalse("ERROR: normalizated() function does not create a new vector", u == v);
	}

	/**
	 * Test method for {@link primitives.Vector#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(1, 2, 3);
		Vector v3 = new Vector(-1, -2, -3);
		
		// ============ Equivalence Partitions Tests ==============
		assertTrue("ERROR: equals() result is not a unit vector", v1.equals(v2));
		assertFalse("ERROR: equals() result is not a unit vector", v1.equals(v3));
	}

}
