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
	 * Sphere constructor based on radius and point
	 * 
	 * @param c point
	 * @param r radius
	 */
	public Sphere(Point3D c, double r)
	{
		super(r);
		_center = c;
	}
	
	/**
	 * Sphere constructor based on point, radius  and color
	 * 
	 * @param c
	 * @param r
	 * @param color
	 */
	public Sphere(Point3D c, double r, Color color)
	{
		super(r);
		_center = c;
		super._emission = color;
	}
	/**
	 * Sphere constructor based on point, radius, color and material
	 * 
	 * @param c
	 * @param r
	 * @param color
	 * @param material
	 */
	public Sphere(Point3D c, double r, Color color, Material material)
	{
		this(c,r, color);
		super._material = material;
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
	public List<GeoPoint> findIntersections(Ray ray) {
		Vector L;
		try
		{
			L = _center.subtract(ray._POO);
		}
		catch(IllegalArgumentException e)
		{
			return List.of(new GeoPoint(this, new Point3D(_center.add(ray._direction.scale(_radius)))));
		}
		double tm = alignZero(ray._direction.dotProduct(L));
		
		//double d = L.length() - tm*tm;
		double d = L.lengthSquared() - tm*tm;
		//double th = alignZero(_radius*_radius - d*d);
		double th = alignZero(_radius*_radius - d);
		if(th<=0)
			return null;
		double th2 = alignZero(Math.sqrt(th));
		if(th2 == 0)
			return null;
		
		double t1 = alignZero(tm - th2);
		double t2 = alignZero(tm + th2);
		if(t1<= 0 && t2<= 0)
			return null;
		LinkedList<GeoPoint> ans = new LinkedList<GeoPoint>();
		if(t1 > 0)
		{
			//Point3D p1 = ray.getPoint(t1);
			GeoPoint gp1 = new GeoPoint(this, ray.getPoint(t1));
			ans.add(gp1);
		}
		
		if(t2 > 0)
		{
			//Point3D p2 = ray.getPoint(t2);
			GeoPoint gp2 = new GeoPoint(this, ray.getPoint(t2));
			ans.add(gp2);
		}
		return ans;
	}
}
