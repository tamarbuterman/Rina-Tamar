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
		_p0 = p0;
		if(vTo.dotProduct(vUp) != 0)
			throw new IllegalArgumentException("The vectors are not orthogonal");
		
		
		_vUp = vUp.normalized();
		_vTo = vTo.normalized();
		_vRight = vUp.crossProduct(vTo).normalized();
	}
	
	/*
	 * @param nX numbre of pixels 
	 * @param nY 
	 * @param j the index of the column
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance,
            double screenWidth, double screenHeight)
	{
		Point3D Pc = _p0.add(_vTo.scale(screenDistance));
	    double Rx = screenWidth / nX;
	    double Ry = screenHeight / nY;
	    double tempY = (j - nY / 2) * Ry - Ry/2;
	    double tempX = (i - nX / 2) * Rx - Rx/2;
	    Vector Vx = _vRight.scale(tempX);
	    Vector Vy = _vRight.scale(tempY);
	    Vector v = Vx.subtract(Vy);
	    Point3D p = Pc.add(v);
	    Ray ray = new Ray(p,_vTo);
	    
		return ray;
	}

}
