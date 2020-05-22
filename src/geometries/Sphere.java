package geometries;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.alignZero;

import  primitives.*;
import static primitives.Util.isZero;

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
		Vector v1 = new Vector(p.subtract(_center));
		return v1.normalized();
	}

	
	
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		Vector L;
		try
		{
		L = _center.subtract(ray._POO);
		
		}
		catch(IllegalArgumentException e)
		{
			return List.of(new Point3D(_center.add(ray._direction.scale(_radius))));
		}
		double tm = alignZero(ray._direction.dotProduct(L));
		
		double d = L.length() - tm*tm;
		double th = alignZero(_radius*_radius - d*d);
		if(th<=0)
			return null;
		double th2 = alignZero(Math.sqrt(th));
		if(th2 == 0)
			return null;
		LinkedList<Point3D> ans = new LinkedList<Point3D>();
		
		double t1 = alignZero(tm - th2);
		if(t1 > 0)
		{
			Point3D p1 = ray.getPoint(t1);
			ans.add(p1);
		}
		double t2 = alignZero(tm + th2);
		if(t2 > 0)
		{
			Point3D p2 = ray.getPoint(t2);
			ans.add(p2);
		}
		return ans;
	}
}
