package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	/*
	 * list of geometries
	 */
	List <Intersectable> _geometriesList;
	/*
	 * Default constructor
	 */
	public Geometries()
	{
		_geometriesList = new ArrayList<>();
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
