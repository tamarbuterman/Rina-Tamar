package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight
{
	private Vector _direction;

	public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector direction) {
		super(intensity, position, kC, kL, kQ);
		_direction = new Vector(direction);
	}
	
	@Override
	public Vector getL(Point3D p) {
		return _direction;
	}

	  public Color getIntensity(Point3D p) {
	        double projection = _direction.dotProduct(getL(p));

	        if (Util.isZero(projection)) {
	            return Color.BLACK;
	        }
	        double factor = Math.max(0, projection);
	        Color pointlightIntensity = super.getIntensity(p);

	       // if (_concentration != 1) {
	      //      factor = Math.pow(factor, _concentration);
	        //}

	        return (pointlightIntensity.scale(factor));
	    }

}
