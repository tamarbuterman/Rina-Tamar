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
	 * vetrex1 
	 */
	Point3D _p1;
	
	/**
	 * vertex 2
	 */
	Point3D _p2;
	
	/**
	 * vertex 3
	 */
	Point3D _p3;
	
	/**
	 * Triangle constructor based on 3 point, the points are sent to the father constractor 
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3)
	{
		super(p1, p2, p3);
		_p1 = p1;
		_p2 = p2;
		_p3 = p3;
		
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		List<Point3D> list1 = super.findIntersections(ray);
		if(list1 == null)
			return null;
		Vector v1 = _p1.subtract(ray._POO);
		Vector v2 = _p2.subtract(ray._POO);
		Vector v3 = _p3.subtract(ray._POO);
		
		Vector N1 = v1.crossProduct(v2);
		Vector N2 = v2.crossProduct(v3);
		Vector N3 = v3.crossProduct(v1);
		
		//??????????????????????????????????
		return null;
	}
}
