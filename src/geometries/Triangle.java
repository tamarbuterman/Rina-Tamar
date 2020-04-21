package geometries;

import java.util.LinkedList;
import java.util.List;

import  primitives.*;

/**
 * Triangle class is heiress from Plan class, and represent a Triangle using plan and points
 * 
 *@author Tamar and Rina
 *
 */
public class Triangle extends Plane
{
	/**
	 * Triangle constructor based on 3 point, the points are sent to the father constractor 
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3)
	{
		super(p1, p2, p3);
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		LinkedList<Point3D> list1 = Plane.findIntersections(ray);
		return null;
	}
}
