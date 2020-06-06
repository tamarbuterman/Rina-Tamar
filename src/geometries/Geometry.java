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
	/**
	 * Geometry values
	 */
	protected Color _emission;
	protected Material _material;
	
	/**
	 * Geometry constructor
	 * @param emission
	 */
	public Geometry(Color emission)
	{
		_emission = new Color(emission);
		_material = new Material(0,0,0);
	}
	
	/**
	 * default constructor
	 */
	public Geometry()
	{
		this(Color.BLACK);
		_material = new Material(0,0,0);

	}

	/**
	 * Geometry constructor 
	 * @param emission
	 * @param material
	 */
	public Geometry(Color emission, Material material)
	{
		_emission = new Color(emission);
		_material = new Material(material.getKD(),material.getKS(),material.getShin());
	}
	
	/**
	 * function to get emission 
	 * 
	 * @return color
	 */
	public Color getEmission() {return _emission;}
	/**
	 * abstract function to get normal
	 * 
	 * @param point
	 * @return vector
	 */
	abstract public Vector getNormal(Point3D p);
	/**
	 * function to get material
	 * 
	 * @return material
	 */
    public Material getMaterial() {return _material;}
    //public void setEmission()
}
