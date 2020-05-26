package geometries;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Plane class represent plan in 3D Cartesian coordinate
 * system
 * 
 * @author Tamat and Rina
 *
 */
public class Plane extends Geometry
{
	/**
	 * Point and vector 
	 */
	Point3D _p;
	Vector _normal;
	
	/**
	 * Plane constractor, gets 3 points
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3)
	{
		_p = new Point3D(p1);
		
		Vector v1 = new Vector(p2.subtract(p1));
		Vector v2 = new Vector(p3.subtract(p1));
		_normal = (v1.crossProduct(v2)).normalize();
		
        
	}
	
	public Color getEmiision()
	{
		return 
	}
	
	/**
	 *  Seconed constractor, gets vector and point
	 * @param p point
	 * @param n vector
	 */
	public Plane(Point3D p, Vector n)
	{
		_p = new Point3D(p);
		_normal = new Vector(n);
	}

	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return _normal;
	}


	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		try 
		{
			Vector p0 = new Vector(_p.subtract(ray._POO));
		}
		catch(IllegalArgumentException e)
		{
			return null;
		}
		double nv = _normal.dotProduct(ray._direction);
		if(isZero(nv))
		{
			return null;
		}
		double t = alignZero(_normal.dotProduct(_p.subtract(ray._POO))/nv);
        //double tdist = alignZero(maxDistance - t);

		if(t <= 0 )
			return null;
		
		return List.of(new GeoPoint(this, ray.getPoint(t)));
		/*Point3D p = ray.getPoint(t);
		LinkedList<GeoPoint> ans = new LinkedList<GeoPoint>();
		ans.add(p);
		return ans;*/
		
	}

}
