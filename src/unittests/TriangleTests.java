package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Triangle
 * @author Tamar and Rina
 *
 */

class TriangleTests
{

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
	@Test
	void testGetNormal() {
		Triangle p1 = new Triangle(new Point3D(1,2,3), new Point3D(3, 4, 5), new Point3D(3,7,9));
		
		// ============ Equivalence Partitions Tests ==============
		//Test for triangle Plane
		Vector temp = new Vector(2/Math.sqrt(104), -8/Math.sqrt(104), 6/Math.sqrt(104));
		assertEquals("getNormal() result is not expected", temp, p1.getNormal(new Point3D(0, 0, 0)));
		
	}
	
	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
    public void testFindIntersections() {
		Triangle triangle = new Triangle(new Point3D(0, 0, 0), new Point3D(0, 3, 0), new Point3D(3, 0, 0));
		
		// ============ Equivalence Partitions Tests ==============
		// TC01:Ray inside triangle (1 options)
		List<Point3D> result = triangle.findIntersections(new Ray(new Point3D(-1, 8, -5), new Vector(2, -7, 5)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray intersects the triangle",List.of(new Point3D(1, 1, 0)), result);
        //---------------------------------------------
        
        //TC02:Ray outside against edge (0 options)
        assertEquals("Ray not included in the triangle", null,
                triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(1, 4, 1))));
        //----------------------------------------
        
        //TC03:Ray outside against vertex (0 options)
        assertEquals("Ray not included in the triangle", null,
                triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(1, 2, 1))));
        //---------------------------------------------------
        
     // =============== Boundary Values Tests ==================
        
     //TC11:Ray on edge (0 options)
        assertEquals("Ray not included in the triangle", null,
                triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(2, 4, 1))));
        //--------------------------
        
    //TC12:Ray in vertex (0 options)
        assertEquals("Ray not included in the triangle", null,
                triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(2, 3, 1))));
        //------------------------
        
    //TC13: Ray on edge's continuation (0 options)
        assertEquals("Ray not included in the triangle", null,
                triangle.findIntersections(new Ray(new Point3D(-2, -3, -1), new Vector(6, 2, 1))));
        //----------------------------
		
	}

}
