package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class DirectionalLight to apply directional lighting
 * 
 * @author Rina and Tamar
 *
 */
public class DirectionalLight extends Light implements LightSource
{
	/**
	 * DirectionalLight value
	 */
	private Vector _direction;

	/**
	 * DirectionalLight constructor
	 * 
	 * @param intensity
	 * @param direction
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		_direction = new Vector(direction);
	}

	/**
	 * The get function to get the point's color
	 * 
	 * @param Point - p
	 * @return color
	 */
	@Override
	public Color getIntensity(Point3D p) {
		return super.getIntensity();
	}

	/**
	 * The get function to get the direction of the lighting
	 * 
	 * @param Point - p
	 * @return vector - direction
	 */
	@Override
	public Vector getL(Point3D p) {
		return _direction;
	}

}
