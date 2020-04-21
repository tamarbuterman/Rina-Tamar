package geometries;

import java.util.List;

import primitives.*;

/**
 * Geometries interface, represent all the geometries
 * @author Rina and Tamar
 *
 */
public interface Geometry extends Intersectable  
{
	public Vector getNormal(Point3D p);

}
