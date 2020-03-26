package primitives;

import static primitives.Util.isZero;

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
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z)
	{
		_head._x._coord = x;
		_head._y._coord = y;
		_head._z._coord = z;
		
	}
	
	/**
	 * 
	 * @param head
	 */
	public Vector(Point3D head)
	{
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
		return _head;
	}
	
	public Vector subtract(Vector other)
	{
		Vector newV = this;
		newV._head = _head.subtract(other._head);
		return newV;
	}
	
	public Vector add(Vector other)
	{
		Vector newV = this;
		newV._head.add(other._head);
		return newV;
	}
	
	public Vector scale(double num)
	{
		Vector newV = this;
		newV._head._x._coord = _head._x._coord * num;
		newV._head._y._coord *= num;
		newV._head._z._coord *= num;
		return newV;
	}
	
	public double dotProduct(Vector other)
	{
		double result = _head._x._coord*other._head._x._coord + _head._y._coord*other._head._y._coord + _head._z._coord*other._head._z._coord;
		return result;
	}
	
	public Vector crossProduct(Vector other)
	{
		Vector newV = this;
		newV._head._x._coord = _head._y._coord * other._head._z._coord - _head._z._coord * other._head._y._coord;
		newV._head._y._coord = _head._z._coord * other._head._x._coord - _head._x._coord * other._head._z._coord;
		newV._head._z._coord = _head._x._coord * other._head._y._coord - _head._y._coord * other._head._x._coord;
		return newV;
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
