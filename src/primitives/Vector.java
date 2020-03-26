package primitives;

/**
 * class Vactor is the basic class representing a vector that start from the beginning.
 * 
 * @author Rina and Tamar
 *
 */
public class Vector
{
	/**
	 * Vector values
	 */
	Point3D _head;
	
	/**
	 * Vector constructor receiving a Vector values by Coordinate
	 * 
	 * @param x Coordinate value
	 * @param y Coordinate value
	 * @param z Coordinate value
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z)
	{
		_head._x = x;
		_head._y = y;
		_head._z = z;
		if(_head.equals(Point3D.ZERO))
		{
			throw new IllegalArgumentException("Illegal vector zero");
		}
	}
	
	/**
	 * Vector constructor receiving a Vector values by double
	 * 
	 * @param x double value
	 * @param y double value
	 * @param z double value
	 */
	public Vector(double x, double y, double z)
	{
		Coordinate X = new Coordinate(x);
		Coordinate Y = new Coordinate(y);
		Coordinate Z = new Coordinate(z);
		_head._x = X;
		_head._y = Y;
		_head._z = Z;
		if(_head.equals(Point3D.ZERO))
		{
			throw new IllegalArgumentException("Illegal vector zero");
		}
	}
	
	/**
	 * Vector constructor receiving a Vector value by Point3D
	 * 
	 * @param Point3D value
	 */
	public Vector(Point3D head)
	{
		if(head.equals(Point3D.ZERO))
		{
			throw new IllegalArgumentException("Illegal vector zero");
		}
		_head = head;
	}
	
	/**
	 * Copy constructor for Vector
	 * 
	 * @param Vector value
	 */
	public Vector(Vector other)
	{
		_head = other._head;
	}
	
	/**
	 * Vector value getter
	 * 
	 * @return Vector value
	 */
	public Point3D get()
	{
		return _head.get();
	}
	
	/**
	 * Calculate vector subtraction
	 * 
	 * @param Vector
	 * @return Vector - result
	 */
	public Vector subtract(Vector other)
	{
		Vector newV = new Vector(_head);
		newV = newV._head.subtract(other._head);
		return newV;
	}
	
	/**
	 * Calculate a vector connection
	 * 
	 * @param Vector
	 * @return Vector - result
	 */
	public Vector add(Vector other)
	{
		Vector newV =new Vector(_head);
		newV._head = newV._head.add(other);
		return newV;
	}
	
	/**
	 * Calculate vector multiplier in scalar
	 * 
	 * @param number
	 * @return Vector result
	 */
	public Vector scale(double num)
	{
		Vector newV = new Vector(_head);
		Coordinate x = new Coordinate(_head._x._coord * num);
		Coordinate y = new Coordinate(_head._y._coord * num);
		Coordinate z = new Coordinate(_head._z._coord * num);
		newV._head._x = x;
		newV._head._y = y;
		newV._head._z = z;
		return newV;
	}
	
	/**
	 * Calculate scalar multiplication
	 * 
	 * @param Vector
	 * @return number - result
	 */
	public double dotProduct(Vector other)
	{
		double result = _head._x._coord*other._head._x._coord + _head._y._coord*other._head._y._coord + _head._z._coord*other._head._z._coord;
		return result;
	}
	
	/**
	 * Calculate a vector multiplication
	 * 
	 * @param Vector
	 * @return Vector - result
	 */
	public Vector crossProduct(Vector other)
	{
		Vector newV = new Vector(_head);
		Coordinate x = new Coordinate(_head._y._coord * other._head._z._coord - _head._z._coord * other._head._y._coord);
		Coordinate y = new Coordinate(_head._z._coord * other._head._x._coord - _head._x._coord * other._head._z._coord);
		Coordinate z = new Coordinate(_head._x._coord * other._head._y._coord - _head._y._coord * other._head._x._coord);
		newV._head._x = x;
		newV._head._y = y;
		newV._head._z = z;
		return newV;
	}
	
	/**
	 * Calculate the vector length per second
	 * 
	 * @return number - result
	 */
	public double lengthSquared()
	{
		return _head._x._coord * _head._x._coord + _head._y._coord * _head._y._coord + _head._z._coord * _head._z._coord;
	}
	
	/**
	 * Calculate the vector length
	 * 
	 * @return number - result
	 */
	public double length()
	{
		return Math.sqrt(lengthSquared());
	}
	
	/**
	 * Calculate Normalization Vector
	 * 
	 * @return Vector - result
	 */
	public Vector normalize()
	{
		_head = scale(1/length())._head;
		return this;
	}
	
	public Vector normalized()
	{
		Vector newV = new Vector(_head);
		return newV.normalize();
	}
	
	/*************** Admin *****************/
    @Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector temp = (Vector)obj;
        if(_head.equals(temp._head))
        	return true;
        return false;
	}

    @Override
    public String toString() 
    {
        return "" + _head.toString();
    }
}
