package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Rina and Tamar
 *
 */
public class Camera {
	/**
	 * camara location
	 */
	Point3D _p0;
	
	/**
	 * camera orientation vectors
	 */
	Vector _vUp;
	Vector _vTo;
	Vector _vRight;
	
	/**
	 * depth of field
	 */
	 double _widthSh;
	 double _heightSh;

	
	/**
	 * camera constructor
	 * 
	 *@param p0 point of camara locetion
	 *@param vTo, vUp orientation vectors
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp)
	{
		_p0 = new Point3D(p0);
		if(vTo.dotProduct(vUp) != 0)//if they are not orthogonal
			throw new IllegalArgumentException("The vectors are not orthogonal");
		
		
		_vUp = new Vector(vUp.normalized());
		_vTo = new Vector(vTo.normalized());
		_vRight = new Vector(vTo.crossProduct(vUp).normalized());
		
		_widthSh=0;
		_heightSh=0;
	}
	
	/**
	 * camera constructor
	 * 
	 * @param p0 point of camara locetion
	 * @param vTo, vUp orientation vectors
	 * @param widthSh heightSh camera aperture size
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp, double widthSh, double heightSh)
	{
		this(p0, vTo, vUp);
		_widthSh = widthSh;
		_heightSh = heightSh;
	}
	
	
	/**
	 * Function to through rays
	 * 
	 * @param nX number of pixels 
	 * @param nY 
	 * @param j the index of the column
	 * @param i the index of the row
	 * @param screenDistance distance of the screen
	 * @param screenWidth screen width
	 * @param screenHeight screen height
	 * @return ray
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance,
            double screenWidth, double screenHeight)
	{
		if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }
		
		Point3D p = new Point3D(getCenterOfPixel(nX, nY, j, i, screenDistance, screenWidth, screenHeight));
	    Vector v = new Vector(p.subtract(_p0));
	    //the center point of the pixel
	    Ray ray = new Ray(_p0,v);
	    
		return ray;
	}
	
	public List<Ray> constructRaysThroughPixel(List<Point3D> points)
	{	
		List<Ray> rays = new LinkedList<Ray>();
		for(Point3D point:points)
		{
			 Vector v = new Vector(point.subtract(_p0));
			  rays.add(new Ray(_p0,v));
		}
	    
		return rays;
	}
	public void setP0(Point3D p)
	{
		_p0 = p;
	}
	
	/**
	 * Get function for the point where the camera is located
	 * 
	 * @return point
	 */
	public Point3D getP0()
	{
		return _p0;
	}
	
	
	public double getWidthSh()
	{
		return _widthSh;
	}
	
	public double getHeightSh()
	{
		return _heightSh;
	}
	
	public Vector getVto()
	{
		return _vTo;
	}
	
	public Vector getVup()
	{
		return _vUp;
	}
	
	public Vector getVright()
	{
		return _vRight;
	}
	
	/**
	 * Finds the center point of a particular pixel
	 * 
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return the center point
	 */
	public Point3D getCenterOfPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{
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
			    return p;
	}
	
	/**
	 * 
	 * @param centerPixel
	 * @param nX
	 * @param nY
	 * @param screenWidth
	 * @param screenHeight
	 * @return
	 */
	public List<Point3D> getPointsPixel(Point3D centerPixel, int nX, int nY, double width, double height)
	{
		//width of pixel
	    double Rx = width / nX;
	    //height of pixel
	    double Ry = height / nY;

		Vector v1 = getVup().scale(Ry/2);
		Vector v2 = getVright().scale(Rx/2);
			    
	    List<Point3D> points = new LinkedList<Point3D>();
		points.add(centerPixel.add(v1).add(v2));
		points.add(centerPixel.add(v1.scale(-1)).add(v2.scale(-1)));
		points.add(centerPixel.add(v1.scale(-1)).add(v2));
		points.add(centerPixel.add(v1).add(v2.scale(-1)));    
		return points;
	}

}
