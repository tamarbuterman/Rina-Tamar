package geometries;

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
	 * 
	 */
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}
}
