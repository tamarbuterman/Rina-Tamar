package geometries;

import java.util.List;

import primitives.*;

/**
 * Geometries interface, represent all the geometries
 * @author Rina and Tamar
 *
 */
public abstract class Geometry implements Intersectable  
{
	protected Color _emission;
	
	/*
	 * constructor
	 */
	public Geometry(Color emission)
	{
		_emission = new Color(emission);
	}
	public Geometry()
	{
		this(Color.BLACK);
	}
	/*
	 * return the emission 
	 */
	public Color getEmission() {return _emission;}
	abstract public Vector getNormal(Point3D p);

}
