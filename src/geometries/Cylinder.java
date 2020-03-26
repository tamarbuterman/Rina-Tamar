package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Cylinder class represents cylinder in 3D Cartesian coordinate
 * system 
 * @author Tamar and Rina
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
	 * @param h hight
	 * @param r radius
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
