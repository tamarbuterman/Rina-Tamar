package geometries;

import  primitives.*;

/**
 * 
 * @author DELL
 *
 */
public class Sphere extends RadialGeometry
{
	/**
	 * 
	 */
	Point3D _center;
	
	/**
	 * 
	 * @param c
	 * @param r
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
