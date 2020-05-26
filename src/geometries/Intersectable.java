package geometries;



import java.util.List;
import primitives.*;

public interface Intersectable
{
	
	public List<GeoPoint> findIntersections(Ray ray);
	/*
	 * GeoPoint class 
	 */
	public static class GeoPoint 
	{
	    public Geometry geometry;
	    public Point3D point;
	    
	    /*
	     * parameter constructor
	     */
	    public GeoPoint(Geometry geo, Point3D p)
	    {
	    	geometry = geo;
	    	point = new Point3D(p);
	  
	    }
	   // public boolean equals() {}
	    public Point3D getPoint() {return point;}
	    public Color getEmission() {return geometry._emission; }
	}


}
