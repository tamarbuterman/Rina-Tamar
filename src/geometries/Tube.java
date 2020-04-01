package geometries;

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
	Ray _axisRay;
	
	/**
	 * 
	 * Tube constructor based on Ray and Point. the point is sent to the father constractor
	 * @param Ray and Point
	 */
	public Tube(Ray axisRay, double r)
	{
		super(r);
		_axisRay = axisRay;
	}
	
	
	@Override
	public Vector getNormal(Point3D p) {
		Vector v1 = _axisRay._direction;
		Vector v2 = p.subtract(_axisRay._POO);
		double t = v1.dotProduct(v2);
		Point3D nb = _axisRay._POO.add(_axisRay._direction.scale(t));
		Vector n = nb.subtract(Point3D.ZERO);
		return n;
	}
}
