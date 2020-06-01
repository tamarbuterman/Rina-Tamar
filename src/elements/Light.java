package elements;

import primitives.Color;

/**
 * abstract class Light base class to express the lighting of the scene
 * 
 * @author Rina and Tamar
 *
 */
abstract class Light 
{
	/**
	 * Light value
	 */
	protected Color _intensity;
	
	/**
	 * Light constructor
	 * 
	 * @param intensity
	 */
	public Light(Color intensity)
	{
		_intensity = new Color(intensity);
	}
	
	/**
	 * The get function to get the color
	 * 
	 * @return color
	 */
	public Color getIntensity() { return _intensity; }
}
