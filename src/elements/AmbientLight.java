package elements;

import primitives.*;

/**
 * 
 * @author DELL
 *
 */
public class AmbientLight
{
	/**
	 * 
	 */
	Color _intensity;
	
	/**
	 * 
	 * @param iA
	 * @param kA
	 */
	public AmbientLight(Color iA, double kA)
	{
		_intensity = iA.scale(kA);
	}
	
	/**
	 * 
	 * @return
	 */
	public Color GetIntensity() 
	{
		return _intensity;
	}
}
