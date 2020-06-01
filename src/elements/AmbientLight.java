package elements;

import primitives.*;

/**
 * class AmbientLight is the class for expressing and calculating ambient lighting
 * 
 * @author Rina and Tamar
 *
 */
public class AmbientLight extends Light
{	
	/**
	 * AmbientLight constructor
	 * 
	 * @param iA
	 * @param kA
	 */
	public AmbientLight(Color iA, double kA)
	{
		super(iA.scale(kA));
	}
	
}
