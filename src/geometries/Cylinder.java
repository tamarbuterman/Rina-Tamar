package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents cylinder in 3D Cartesian coordinate
 * system 
 * @author Tamar and Rina
 *
 */
public class Cylinder extends Tube
{
	/**
	 * Cylinder value
	 */
	double _hight;
	
	/**
	 * Cylinder constructor
	 * 
	 * @param h hight
	 * @param r radius
	 */
	public Cylinder(double h, double r, Ray axisRay)
	{
		super(axisRay, r);
		_hight = h;
	}
	
	/**
	 * Cylinder constructor with color
	 * 
	 * @param h
	 * @param r
	 * @param axisRay
	 * @param color
	 */
	public Cylinder(double h, double r, Ray axisRay, Color color)
	{
		super(axisRay, r, color);
		_hight = h;

	}
	
	/**
	 * Cylinder constructor with color and material
	 * 
	 * @param h
	 * @param r
	 * @param axisRay
	 * @param color
	 * @param material
	 */
	public Cylinder(double h, double r, Ray axisRay, Color color, Material material)
	{
		this(h,r,axisRay,color);
		super._material = material;
	}
	
	/**
	 * function for getting the normal
	 * 
	 * @param point
	 * @return vector
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v1 = _axisRay._direction;
		Vector v2 = p.subtract(_axisRay._POO);
		double t = v1.dotProduct(v2);
		if(t<0.0001 && t>-0.0001)
			return v1;
		Point3D nb = _axisRay._POO.add(_axisRay._direction.scale(t));
		Vector n = nb.subtract(Point3D.ZERO);
		return n;
	}
}
