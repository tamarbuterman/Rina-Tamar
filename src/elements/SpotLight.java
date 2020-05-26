package elements;

import primitives.Color;
import primitives.Point3D;
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

}
