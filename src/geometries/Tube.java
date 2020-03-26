package geometries;

import  primitives.*;

/**
 * Tube class represent 
 * @author DELL
 *
 */
public class Tube extends RadialGeometry
{
	/**
	 * 
	 */
	Ray _axisRay;
	
	/**
	 * 
	 * @param axisRay
	 * @param r
	 */
	public Tube(Ray axisRay, double r)
	{
		super(r);
		_axisRay = axisRay;
	}
	
	/**
	 * 
	 */
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}
}
