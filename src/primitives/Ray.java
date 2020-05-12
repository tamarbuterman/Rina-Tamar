package primitives;

/**
 * class Ray is the basic class representing a vector that doesn't start from the beginning.
 * 
 * @author Rina and Tamar
 *
 */
public class Ray 
{
	/**
	 * Ray values
	 */
	public Point3D _POO;
	public Vector _direction;
	
	/**
	 * Ray constructor receiving a ray value
	 * 
	 * @param Point3D - starting point
	 * @param Vector - direction
	 */
	public Ray(Point3D poo, Vector direction)
	{
		_POO = new Point3D(poo);
		_direction = new Vector (direction.normalized());
	}
	
	/**
	 * Copy constructor for Ray
	 * 
	 * @param Ray
	 */
	public Ray(Ray other)
	{
		_POO = new Point3D(other._POO);
		_direction = new Vector(other._direction);
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return x of point Ray value
	 */
	public double getPointX()
	{
		return _POO.getX();
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return y of point Ray value
	 */
	public double getPointY()
	{
		return _POO.getY();
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return z of point Ray value
	 */
	public double getPointZ()
	{
		return _POO.getZ();
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return x of vector Ray value
	 */
	public double getVectorX()
	{
		return _direction.getX();
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return y of vector Ray value
	 */
	public double getVectorY()
	{
		return _direction.getY();
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return z of vector Ray value
	 */
	public double getVectorZ()
	{
		return _direction.getZ();
	}
	
	public Point3D getPoint(double t)
	{
		return _POO.add(_direction.scale(t));
	}
	
	/*************** Admin *****************/
    @Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray temp = (Ray)obj;
        if(_POO.equals(temp._POO) && _direction.equals(temp._direction))
        	return true;
        return false;
	}
    
    @Override
    public String toString() 
    {
        return "" + _POO.toString()+":"+_direction.toString();
    }
    
    
}
