package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * @author DELL
 *
 */
public class Plane implements Geometry
{
	/**
	 * 
	 */
	Point3D _p;
	Vector _normal;
	
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3)
	{
		_p = p1;
		_normal = null;
	}
	
	/**
	 * 
	 * @param p
	 * @param n
	 */
	public Plane(Point3D p, Vector n)
	{
		_p = p;
		_normal = n;
	}

	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return _normal;
	}

}
