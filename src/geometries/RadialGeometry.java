package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * @author DELL
 *
 */
public abstract class RadialGeometry implements Geometry
{
	/**
	 * 
	 */
	double _radius;
	
	/**
	 * 
	 * @param r
	 */
	public RadialGeometry(double r)
	{
		_radius = r;
	}
	
	/**
	 * 
	 * @param other
	 */
	public RadialGeometry(RadialGeometry other)
	{
		_radius = other._radius;
	}
	
	/**
	 * 
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
