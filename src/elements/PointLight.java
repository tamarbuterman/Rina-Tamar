package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *  class PointLight to apply point lighting
 * 
 * @author Rina and Tamar
 *
 */
public class PointLight extends Light implements LightSource
{
	/**
	 * PointLight values
	 */
	protected Point3D _position;
	protected double _kC, _kL, _kQ;

	/**
	 * PointLight constructor
	 * 
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		_position = new Point3D(position);
		_kC = kC;
		_kL = kL;
		_kQ = kQ;
	}

	/**
	 * The get function to get color
	 * 
	 * @param Point - p
	 * @return color
	 */
	@Override
	public Color getIntensity() 
	{
		return super.getIntensity();
	}
	
    @Override
    public Color getIntensity(Point3D p)
    {
        //double dsquared = p.distanceSquared(_position);
        //double d = p.distance(_position);
    	double d = getDistance(p);
    	double dsquared = d*d;

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }
	/**
	 * The get function to get the direction of the lighting
	 * 
	 * @param Point - p
	 * @return vector - direction
	 */
	@Override
	public Vector getL(Point3D p) {
		 if (p.equals(_position)) {
	            return null;
	        }
	        return p.subtract(_position).normalize();
	}
	/**
	 * 
	 */
	@Override
	public double getDistance(Point3D point)
	{
		return _position.distance(point);
	}

}

