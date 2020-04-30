package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * 
 * @author DELL
 *
 */
public class Camera {

	Point3D _p0;
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;
	
	public Camera(Point3D p0, Vector vTo, Vector vUp)
	{
		try {
		_p0 = p0;
		if(vTo.dotProduct(vUp) != 0)
			throw ;
		}
		_vUp = vUp.normalized();
		_vTo = vTo.normalized();
		_vRight = vUp.crossProduct(vTo).normalized();
	}
	
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance,
            double screenWidth, double screenHeight)
	{
		
	}

}
