package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * class SpotLight to apply spot lighting
 * 
 * @author Rina and Tamar
 *
 */
public class SpotLight extends PointLight
{
	/**
	 * SpotLight value
	 */
	private Vector _direction;
    private double _concentration;
    
    /**
     * 
     * @param colorIntensity
     * @param position
     * @param direction
     * @param kC
     * @param kL
     * @param kQ
     * @param concentration
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration) {
        super(colorIntensity, position, kC, kL, kQ);
        this._direction = new Vector(direction).normalized();
        this._concentration = concentration;
    }
    
	/**
	 * SpotLight constructor
	 * 
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param direction
	 */
	public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector direction) 
	{
		super(intensity, position, kC, kL, kQ);
		_direction = new Vector(direction);
	}
	
	/**
	 * The get function to get the direction of the lighting
	 * 
	 * @param Point - p
	 * @return vector - direction
	 */
	//@Override
	//public Vector getL(Point3D p) {
	//	return _direction;
	//}

	/**
	 * The get function to get color
	 * 
	 * @param Point - p
	 * @return color
	 */
	@Override
	  public Color getIntensity(Point3D p) {
	        double projection = _direction.dotProduct(getL(p));

	        if (Util.isZero(projection)) {
	            return Color.BLACK;
	        }
	        double factor = Math.max(0, projection);
	        Color pointlightIntensity = super.getIntensity(p);

	        if (_concentration != 1) {
	          factor = Math.pow(factor, _concentration);
	        }

	        return (pointlightIntensity.scale(factor));
	    }
	


}
