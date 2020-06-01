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
	protected Material _material;
	
	/*
	 * constructor
	 */
	public Geometry(Color emission)
	{
		_emission = new Color(emission);
		_material = new Material(0,0,0);
	}
	public Geometry()
	{
		this(Color.BLACK);
		_material = new Material(0,0,0);

	}
	/*
	 * return the emission 
	 */
	/*
	 * 
	 */
	public Geometry(Color emission, Material material)
	{
		_emission = new Color(emission);
		_material = new Material(material.getKD(),material.getKS(),material.getShin());
	}
	
	public Color getEmission() {return _emission;}
	abstract public Vector getNormal(Point3D p);
    public Material getMaterial() {return _material;}
}
