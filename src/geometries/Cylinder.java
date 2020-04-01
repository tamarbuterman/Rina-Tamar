package geometries;

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
	 * 
	 */
	double _hight;
	
	/**
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
	 * 
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
