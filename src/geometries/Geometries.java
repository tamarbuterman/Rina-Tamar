package geometries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	/*
	 * list of geometries
	 */
	LinkedList <Intersectable> _geometriesList;
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
		_geometriesList = new LinkedList<Intersectable>();
		add(geometries);
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
	public List<Point3D> findIntersections(Ray ray)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
