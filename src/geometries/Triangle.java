package geometries;

import java.util.LinkedList;
import java.util.List;

import  primitives.*;
import static primitives.Util.isZero;


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
		_p1 = new Point3D(p1);
		_p2 = new Point3D(p2);
		_p3 = new Point3D(p3);
		
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray)
	{ 
		List<Point3D> list1 = new LinkedList(super.findIntersections(ray));
		if(list1 == null)
			return null;
		Vector v1 = new Vector(_p1.subtract(ray._POO));
		Vector v2 = new Vector(_p2.subtract(ray._POO));
		Vector v3 = new Vector(_p3.subtract(ray._POO));
		
		Vector N1 = new Vector(v1.crossProduct(v2));
		Vector N2 = new Vector(v2.crossProduct(v3));
		Vector N3 = new Vector(v3.crossProduct(v1));
		
		double t1 = ray._direction.dotProduct(N1);
		if(isZero(t1))
			return null;
		double t2 = ray._direction.dotProduct(N2);
		if(isZero(t2))
			return null;
		double t3 = ray._direction.dotProduct(N3);
		if(isZero(t3))
			return null;
		
		if(t1 >0 && t2 > 0 && t3 > 0 || t1<0 && t2 < 0 && t3 < 0)
			return list1;
		
		return null;
	}
}
