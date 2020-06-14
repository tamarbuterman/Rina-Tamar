package geometries;

import java.util.List;

import  primitives.*;

/**
 * Tube class represent pipe in 3D Cartesian coordinate system
 * @author Tamar Rina
 *
 */
public class Tube extends RadialGeometry
{
	/**
	 * Ray 
	 */
	public Ray _axisRay;
	
	/**
	 * 
	 * Tube constructor based on Ray and Point. the point is sent to the father constractor
	 * @param Ray and Point
	 */
	public Tube(Ray axisRay, double r)
	{
		super(r);
		_axisRay = new Ray(axisRay);
	} 

	/**
	 * Tube constructor gets 3 parameters, based on ray point and color
	 * 
	 * @param axisRay
	 * @param r
	 * @param color
	 */
	public Tube(Ray axisRay, double r, Color color)
	{
		this(axisRay, r);
		super._emission = color;
	}
	/**
	 * Tube constructor gets 4 parameters, based on ray point and color which sent to the 3 params constructor and material 
	 * 
	 * @param axisRay
	 * @param r
	 * @param color
	 * @param material
	 */
	public Tube(Ray axisRay, double r, Color color, Material material)
	{
		this(axisRay, r, color);
		super._material = material;
	}

	
	@Override
	public Vector getNormal(Point3D p) {
		Vector v1 = new Vector(_axisRay._direction);
		Vector v2 = new Vector(p.subtract(_axisRay._POO));
		double t = v1.dotProduct(v2);
		Point3D nb = new Point3D(_axisRay._POO.add(_axisRay._direction.scale(t)));
		Vector n = new Vector(nb.subtract(Point3D.ZERO));
		return n;
	}
	
	@Override
	public List<GeoPoint> findIntersections(Ray ray)
	{
		return null;
	}
}
