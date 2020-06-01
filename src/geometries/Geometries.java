package geometries;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	/**
	 * list of geometries
	 */
	public LinkedList <Intersectable> _geometriesList;
	
	/**
	 * Default constructor
	 */
	public Geometries()
	{
		_geometriesList = new LinkedList<Intersectable>();
	}
	
	/**
	 * constructor
	 * 
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries)
	{
		_geometriesList = new LinkedList<Intersectable>();// create empty list
		add(geometries); //sends to the add function
	}
	
	
	/**
	 * add function to add geometries
	 * 
	 * @param geometries
	 */
	public void add(Intersectable... geometries)
	{
		if (geometries.length > 0 ) 
		{
            _geometriesList.addAll(Arrays.asList(geometries));
        }
	}

	/**
	 * The function returns list of all the intersections points
	 * 
	 * @param ray
	 * @return list of GeoPoint
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray)
	{	
		List<GeoPoint> IntersectionsList = new LinkedList<GeoPoint>(); // create a empty list
		for (Intersectable geometry:_geometriesList)
		{
			List<GeoPoint> TempIntersections = geometry.findIntersections(ray);
			if(TempIntersections != null)
				IntersectionsList.addAll(TempIntersections);
		}
		if (IntersectionsList.isEmpty())
			return null;
		
		return IntersectionsList;
	}

}
