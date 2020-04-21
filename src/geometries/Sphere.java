package geometries;

import java.util.LinkedList;
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

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		Vector L = _center.subtract(ray._POO);
		double tm = L.dotProduct(ray._direction);
		double d = Math.sqrt(L.length() - tm*tm);
		if (d > _radius)
			return null;
		double th = Math.sqrt(_radius*_radius - d*d);
		LinkedList<Point3D> ans = new LinkedList<Point3D>();
		double t1 = tm - th;
		if(t1 > 0)
		{
			Point3D p1 = _center.add(ray._direction.scale(t1));
			ans.add(p1);
		}
		double t2 = tm + th;
		if(t2 > 0)
		{
			Point3D p2 = _center.add(ray._direction.scale(t2));
			ans.add(p2);
		}
		return ans;
	}
}
