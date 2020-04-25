package unittests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import geometries.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class GeometriesTest
{
	
	void testFindIntersections() {
		Geometries g = new Geometries();
		
		// =============== Boundary Values Tests ==================
		//TC11: Empty list
		assertEquals("list Empty", null, g.findIntersections(new Ray(new Point3D(3, 1, 0.5), new Vector(1, 1, 0))));
		
		g._geometriesList.add(new Triangle(new Point3D(-1, 0.5, -6), new Point3D(-1, 0, -6), new Point3D(2, 0, -6)));
		g._geometriesList.add(new Plane(new Point3D(1, 0, -4), new Vector (0, 0, 1)));
		g._geometriesList.add(new Sphere(new Point3D(0, 0, 0), 5));	
		
	    //TC12: No shape cut
		assertEquals("Ray not included in the plane", null, g.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));
			
		//TC13: One shape cut		
		List<Point3D> l = g.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals("Ray not included in the plane", 2, l.size());

			
		//TC14: All shapes cut
		l = g.findIntersections(new Ray(new Point3D(1, 0, -8), new Vector(0, 0, 1)));
		assertEquals("Ray not included in the plane", 4, l.size());

		
		// ============ Equivalence Partitions Tests ==============
		//TC01: Some shapes are cut
		l = g.findIntersections(new Ray(new Point3D(1, 0, -5), new Vector(0, 0, 1)));
		assertEquals("Ray not included in the plane", 3, l.size());
	
		
	}

}
