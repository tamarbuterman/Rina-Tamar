package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource
{
	private Vector _direction;

	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		_direction = new Vector(direction);
	}

	@Override
	public Color getIntensity(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getL(Point3D p) {
		return _direction;
	}

}
