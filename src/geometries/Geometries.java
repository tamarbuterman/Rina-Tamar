package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	/*
	 * list of geometries
	 */
	List <Intersectable> list;
	/*
	 * Default constructor
	 */
	public Geometries()
	{
		list = new LinkedList();
	}
	/*
	 * constructor
	 */
	public Geometries(Intersectable... geometries)
	{
		
	}
	/*
	 * add function
	 */
	public void add(Intersectable... geometries)
	{
		
	}

	
	@Override
	public List<Point3D> findIntersections(Ray ray)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
