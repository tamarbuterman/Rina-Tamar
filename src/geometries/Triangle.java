package geometries;

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
	 * Triangle constractor based on 3 point, the points are sent to the father constractor 
	 * @param p1 first point
	 * @param p2 seconed point
	 * @param p3 third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3)
	{
		super(p1, p2, p3);
	}
}
