package primitives;

/**
 * 
 * @author Rina and Tamar
 *
 */
public class Vector
{
	/**
	 * 
	 */
	Point3D _head;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
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
	 * 
	 * @param x
	 * @param y
	 * @param z
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
	 * 
	 * @param head
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
	 * Copy constructor for vector
	 * @param other
	 */
	public Vector(Vector other)
	{
		_head = other._head;
	}
	
	/**
	 * Vector value getter
	 * @return Vector value
	 */
	public Point3D get()
	{
		return _head.get();
	}
	
	public Vector subtract(Vector other)
	{
		Vector newV = new Vector(_head);
		newV = newV._head.subtract(other._head);
		return newV;
	}
	
	public Vector add(Vector other)
	{
		Vector newV =new Vector(_head);
		newV._head = newV._head.add(other);
		return newV;
	}
	
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
	
	public double dotProduct(Vector other)
	{
		double result = _head._x._coord*other._head._x._coord + _head._y._coord*other._head._y._coord + _head._z._coord*other._head._z._coord;
		return result;
	}
	
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
	
	public double lengthSquared()
	{
		return _head._x._coord * _head._x._coord + _head._y._coord * _head._y._coord + _head._z._coord * _head._z._coord;
	}
	
	public double length()
	{
		return Math.sqrt(lengthSquared());
	}
	
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
