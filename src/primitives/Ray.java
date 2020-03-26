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
	Point3D _POO;
	Vector _direction;
	
	/**
	 * Ray constructor receiving a ray value
	 * 
	 * @param Point3D - starting point
	 * @param Vector - direction
	 */
	public Ray(Point3D poo, Vector direction)
	{
		_POO = poo;
		_direction = direction;
	}
	
	/**
	 * Copy constructor for Ray
	 * 
	 * @param Ray
	 */
	public Ray(Ray other)
	{
		_POO = other._POO;
		_direction = other._direction;
	}
	
	/**
	 * Ray value getter
	 * 
	 * @return Ray value
	 */
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public Ray get()
	{
		return null;
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
