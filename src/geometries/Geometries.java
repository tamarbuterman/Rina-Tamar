package geometries;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	/*
	 * list of geometries
	 */
	public LinkedList <Intersectable> _geometriesList;
	/*
	 * Default constructor
	 */
	public Geometries()
	{
		_geometriesList = new LinkedList<Intersectable>();
	}
	/*
	 * constructor
	 */
	public Geometries(Intersectable... geometries)
	{
		_geometriesList = new LinkedList<Intersectable>();// create empty list
		add(geometries); //sends to the add function
	}
	
	/*
	 * add function
	 */
	public void add(Intersectable... geometries)
	{
		if (geometries.length > 0 ) 
		{
            _geometriesList.addAll(Arrays.asList(geometries));
        }
	}

	
	@Override
	/*
	 * The function returns list of all the intersections points
	 */
	public List<Point3D> findIntersections(Ray ray)
	{
		// TODO Auto-generated method stub
		Iterator<Intersectable> itr = _geometriesList.iterator(); // geometeriesList's list
		List<Point3D> IntersectionsList = new LinkedList<Point3D>(); // create a empty list
		while (itr.hasNext())
		{
			IntersectionsList.addAll(((Geometries) itr).findIntersections(ray));
			
		}
		if (IntersectionsList.isEmpty())
			return null;
		
		return IntersectionsList;
	}

}
