package geometries;

import java.util.List;

import  primitives.*;

/**
 * Sphere class represents sphere in 3D Cartesian coordinate
 * system
 * @author Tamar and Rina
 *
 */
public class Sphere extends RadialGeometry
{
	/**
	 * The center point of the sphere
	 */
	Point3D _center;
	
	/**
	 * Sphere constractor
	 * @param c point
	 * @param r radius
	 */
	public Sphere(Point3D c, double r)
	{
		super(r);
		_center = c;
	}
	
	/**
	 * get sphere normal
	 * @param p point on the sphere
	 * @return normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v1 = p.subtract(_center);
		return v1.normalize();
	}
	
	public List<Point3D> findIntsersections(Ray ray)
	{
		return null;
	}
}
