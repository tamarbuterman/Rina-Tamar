package geometries;



import java.util.List;
import primitives.*;

/**
 * interface Intersectable
 * 
 * @author Rina and Tamar 
 *
 */
public interface Intersectable
{
	/**
	 * Function for finding the Intersections points of the shapes with the ray
	 * 
	 * @param ray
	 * @return list of geopoint
	 */
	public List<GeoPoint> findIntersections(Ray ray);
	
	/**
	 * static class to express the shape of the shape
	 * 
	 * @author Rina and Tamar
	 *
	 */
	public static class GeoPoint 
	{
	    public Geometry geometry;
	    public Point3D point;
	    
	    
	    /**
	     * GeoPoint constructor
	     * 
	     * @param geometery
	     * @param point
	     */
	    public GeoPoint(Geometry geo, Point3D p)
	    {
	    	geometry = geo;
	    	point = new Point3D(p);
	  
	    }
	    
	   // public boolean equals() {}
	    public Point3D getPoint() {return point;}
	    public Color getEmission() {return geometry._emission; }
	    public Geometry getGeometry() {return geometry;}
	    
	}


}
