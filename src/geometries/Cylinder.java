package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * 
 * @author DELL
 *
 */
public class Cylinder extends RadialGeometry
{
	/**
	 * 
	 */
	double _hight;
	
	/**
	 * 
	 * @param h
	 * @param r
	 */
	public Cylinder(double h, double r)
	{
		super(r);
		_hight = h;
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
