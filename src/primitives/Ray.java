package primitives;

/**
 * 
 * @author Rina and Tamar
 *
 */
public class Ray 
{
	/**
	 * 
	 */
	Point3D _POO;
	Vector _direction;
	
	/**
	 * 
	 * @param poo
	 * @param direction
	 */
	public Ray(Point3D poo, Vector direction)
	{
		_POO = poo;
		_direction = direction;
	}
	
	/**
	 * Copy constructor for Ray
	 * @param other
	 */
	public Ray(Ray other)
	{
		_POO = other._POO;
		_direction = other._direction;
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public get()
	{
		
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
