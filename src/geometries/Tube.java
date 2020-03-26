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
		// TODO Auto-generated method stub
		return null;
	}
}
