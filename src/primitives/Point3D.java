package primitives;

/**
 * class Point3D is the basic class representing a point in space.
 * 
 * @author Rina and Tamar
 */
public class Point3D
{
	/**
	 * 
	 */
	public static Point3D ZERO = new Point3D(0,0,0);
	
	/**
	 * Point3D values
	 */
	Coordinate _x;
	Coordinate _y;
	Coordinate _z;
	
	/**
	 * Point3D constructor receiving a Point3D values by Coordinate
	 * 
	 * @param x Coordinate value
	 * @param y Coordinate value
	 * @param z Coordinate value
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z)
	{
		_x = x;
		_y = y;
		_z = z;
	}
	
	/**
	 * Point3D constructor receiving a Point3D values by double
	 * 
	 * @param x double value
	 * @param y double value
	 * @param z double value
	 */
	public Point3D(double x, double y, double z)
	{
		Coordinate X = new Coordinate(x);
		Coordinate Y = new Coordinate(y);
		Coordinate Z = new Coordinate(z);
		_x = X;
		_y = Y;
		_z = Z;
	}
	
	/**
	 * Copy constructor for Point3D
	 * 
	 * @param Point3D value
	 */
	public Point3D(Point3D other)
	{
		_x = other._x;
		_y = other._y;
		_z = other._z;
	}
	
	/**
	 * Point3D values getter
	 * 
	 * @return ???
	 */
	public Point3D get()//!!!!!!!!!!!
	{
		return this;
	}
	
	/**
	 * Calculate vector subtraction
	 * 
	 * @param Point3D value
	 * @return Vector - result
	 */
	public Vector subtract(Point3D other) 
	{
		Vector newV = new Vector(_x._coord - other._x._coord,  _y._coord - other._y._coord, _z._coord - other._z._coord);
		return newV;
	}
	
	/**
	 * Calculate vector addition to a point
	 * 
	 * @param Vector value
	 * @return Point3D - result
	 */
	public Point3D add(Vector other)
	{
		Point3D newP = new Point3D(_x._coord + other._head._x._coord, _y._coord + other._head._y._coord, _z._coord + other._head._z._coord);
		return newP;
	}
	
	/**
	 * Calculate distance between 2 points per second
	 * 
	 * @param Point3D
	 * @return double - distance per second
	 */
	public double distanceSquared(Point3D other)
	{
		double temp = (_x._coord - other._x._coord)*(_x._coord - other._x._coord) + (_y._coord - other._y._coord)*(_y._coord - other._y._coord) +(_z._coord - other._z._coord)*(_z._coord - other._z._coord);
		return temp;
	}
	
	/**
	 * Calculate distance between 2 points
	 * 
	 * @param Point3D 
	 * @return double - distance
	 */
	public double distance(Point3D other)
	{
		return Math.sqrt(distanceSquared(other)); 
	}
	
	/*************** Admin *****************/
    @Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D temp = (Point3D)obj;
        if(_x.equals(temp._x) && _y.equals(temp._y) && _z.equals(temp._z) )
        	return true;
        return false;
	}
    
    @Override
    public String toString() 
    {
        return "(" +_x.toString()+","+_y.toString()+","+_z.toString()+")";
    }

}

