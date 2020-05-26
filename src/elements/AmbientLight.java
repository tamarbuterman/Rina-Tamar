package elements;

import primitives.*;

/**
 * 
 * @author DELL
 *
 */
public class AmbientLight extends Light
{	
	/**
	 * 
	 * @param iA
	 * @param kA
	 */
	public AmbientLight(Color iA, double kA)
	{
		super(iA.scale(kA));
	}
	
}
