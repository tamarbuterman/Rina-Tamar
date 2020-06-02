package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface LightSource exercise a light source
 * 
 * @author Rina and Tamar
 *
 */
public interface LightSource
{
	/**
	 * The get function to get the point's color
	 * 
	 * @param p
	 * @return color
	 */
	public Color getIntensity(Point3D p);
	/**
	 * The get function to get the direction of the lighting to the point
	 * 
	 * @param p
	 * @return vector-direction
	 */
	public Vector getL(Point3D p);
/**
 * 
 * @param point
 * @return
 */
	double getDistance(Point3D point);



}
