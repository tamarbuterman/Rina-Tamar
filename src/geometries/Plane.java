package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Plane class represent plan in 3D Cartesian coordinate
 * system
 * 
 * @author Tamat and Rina
 *
 */
public class Plane implements Geometry
{
	/**
	 * Point and vector 
	 */
	Point3D _p;
	Vector _normal;
	
	/**
	 * Plane constractor, gets 3 points
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3)
	{
		_p = p1;
		double x  = p2.getX() - p1.getX();
		double y = p2.getY() - p1.getY();
		double z = p2.getZ() - p1.getZ();
		Vector v1 = new Vector(x,y,z);
		double x1  = p3.getX() - p1.getX();
		double y1 = p3.getY() - p1.getY();
		double z1 = p3.getZ() - p1.getZ();
		Vector v2 = new Vector(x1, y1, z1);
		_normal = (v1.crossProduct(v2)).normalize();
		
        
	}
	
	/**
	 *  Seconed constractor, gets vector and point
	 * @param p point
	 * @param n vector
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
