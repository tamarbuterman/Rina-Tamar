package geometries;

import static primitives.Util.isZero;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
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
		_p = new Point3D(p1);
		
		Vector v1 = new Vector(p2.subtract(p1));
		Vector v2 = new Vector(p3.subtract(p1));
		_normal = (v1.crossProduct(v2)).normalize();
		
        
	}
	
	/**
	 *  Seconed constractor, gets vector and point
	 * @param p point
	 * @param n vector
	 */
	public Plane(Point3D p, Vector n)
	{
		_p = new Point3D(p);
		_normal = new Vector(n);
	}

	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return _normal;
	}


	@Override
	public List<Point3D> findIntersections(Ray ray) {
		try 
		{
			Vector p0 = new Vector(ray._POO.subtract(_p));
		}
		catch(IllegalArgumentException e)
		{
			return null;
		}
		double nv = _normal.dotProduct(ray._direction);
		if(isZero(nv))
		{
			return null;
		}
		double t = _normal.dotProduct(ray._POO.subtract(_p))/nv;
		if(t < 0)
			return null;
		Point3D p = ray.getPoint(t);
		LinkedList<Point3D> ans = new LinkedList<Point3D>();
		ans.add(p);
		return ans;
		
	}

}
