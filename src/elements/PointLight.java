package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource
{
	protected Point3D _position;
	protected double _kC, _kL, _kQ;

	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		_position = new Point3D(position);
		_kC = kC;
		_kL = kL;
		_kQ = kQ;
	}

	@Override
	public Color getIntensity(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getL(Point3D p) {
		 if (p.equals(_position)) {
	            return null;
	        }
	        return p.subtract(_position).normalize();
	    }
	}

}
