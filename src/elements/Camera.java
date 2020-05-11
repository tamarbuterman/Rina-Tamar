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
    /*
     * camara location
    */
	Point3D _p0;
	/*
	 * camera orientation vectors 
	 */
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;
	
	/*
	 * @param p0 point of camara locetion
	 * @param vTo, vUp orientation vectors
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp)
	{
		_p0 = p0;
		if(vTo.dotProduct(vUp) != 0)//if they are not orthogonal
			throw new IllegalArgumentException("The vectors are not orthogonal");
		
		
		_vUp = vUp.normalized();
		_vTo = vTo.normalized();
		_vRight = vUp.crossProduct(vTo).normalized();
	}
	
	/*
	 * @param nX numbre of pixels 
	 * @param nY 
	 * @param j the index of the column
	 * @param i the index of the row
	 * @param screenDistance distance of the screen
	 * @param screenWidth screen width
	 * @param screenHeight screen height
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance,
            double screenWidth, double screenHeight)
	{
		//the center point of the center pixel
		Point3D Pc = _p0.add(_vTo.scale(screenDistance));
		//width of pixel
	    double Rx = screenWidth / nX;
	    //height of pixel
	    double Ry = screenHeight / nY;
	    double tempY = (j - nY / 2) * Ry - Ry/2;
	    double tempX = (i - nX / 2) * Rx - Rx/2;
	    Vector Vx = _vRight.scale(tempX);
	    Vector Vy = _vRight.scale(tempY);
	    Vector v = Vx.subtract(Vy);
	    //the center point of the pixel
	    Point3D p = Pc.add(v);
	    Ray ray = new Ray(p,_vTo);
	    
		return ray;
	}
	public Point3D getP0()
	{
		return _p0;
	}

}
