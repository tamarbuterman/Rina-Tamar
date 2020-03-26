package primitives;

/**
 * class Point3D...
 * @author Rina and Tamar
 *
 */
public class Point3D
{
	/**
	 * 
	 */
	public static Point3D ZERO = new Point3D(0,0,0);
	
	/**
	 * 
	 */
	Coordinate _x;
	Coordinate _y;
	Coordinate _z;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z)
	{
		_x = x;
		_y = y;
		_z = z;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
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
	 * @param other
	 */
	public Point3D(Point3D other)
	{
		_x = other._x;
		_y = other._y;
		_z = other._z;
	}
	
	/**
	 * 
	 * @return
	 */
	public Point3D get()//!!!!!!!!!!!
	{
		return this;
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Vector subtract(Point3D other) 
	{
		Vector newV = new Vector(_x._coord - other._x._coord,  _y._coord - other._y._coord, _z._coord - other._z._coord);
		return newV;
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Point3D add(Vector other)
	{
		Point3D newP = new Point3D(_x._coord + other._head._x._coord, _y._coord + other._head._y._coord, _z._coord + other._head._z._coord);
		return newP;
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public double distanceSquared(Point3D other)
	{
		double temp = (_x._coord - other._x._coord)*(_x._coord - other._x._coord) + (_y._coord - other._y._coord)*(_y._coord - other._y._coord) +(_z._coord - other._z._coord)*(_z._coord - other._z._coord);
		return temp;
	}
	
	/**
	 * 
	 * @param other
	 * @return
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


