package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

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
		_p0 = new Point3D(p0);
		if(vTo.dotProduct(vUp) != 0)//if they are not orthogonal
			throw new IllegalArgumentException("The vectors are not orthogonal");
		
		
		_vUp = new Vector(vUp.normalized());
		_vTo = new Vector(vTo.normalized());
		_vRight = new Vector(vTo.crossProduct(vUp).normalized());
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
		if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }
		
		//the center point of the center pixel
		Point3D Pc = new Point3D(_p0.add(_vTo.scale(screenDistance)));
		//width of pixel
	    double Rx = screenWidth / nX;
	    //height of pixel
	    double Ry = screenHeight / nY;
	    double tempY = ((i - nY / 2d) * Ry + Ry/2d);
	    double tempX = ((j - nX / 2d) * Rx + Rx/2d);
	    Point3D p = new Point3D(Pc);
	    if(!isZero(tempX))
	    {
	    	 Vector Vx = new Vector(_vRight.scale(tempX));
	    	p = p.add(Vx);
	    }
	    if(!isZero(tempY))
	    {
	    	Vector Vy = new Vector(_vUp.scale(-tempY));
	    	p = p.add(Vy);
	    }
	    Vector v = new Vector(p.subtract(_p0));
	    //the center point of the pixel
	    Ray ray = new Ray(_p0,v);
	    
		return ray;
	}
	public Point3D getP0()
	{
		return _p0;
	}

}
