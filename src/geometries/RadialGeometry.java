package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Abstract class which have radius
 * @author Tamar and Rina
 *
 */
public abstract class RadialGeometry implements Geometry
{
	/**
	 * 
	 */
	double _radius;
	
	/**
	 * RadialGeometry constractor, sets the radius for the shape
	 * @param r radius
	 */
	public RadialGeometry(double r)
	{
		_radius = r;
	}
	
	/**
	 * Copy constractor gets other shape and copy the radius
	 * @param other RadialGeometry 
	 */
	public RadialGeometry(RadialGeometry other)
	{
		_radius = other._radius;
	}
	
	/**
	 * Return the radius
	 * @return
	 */
	public double get()
	{
		return _radius;
	}
	
	@Override
	public Vector getNormal(Point3D p)
	{
		return null;
	}
}
