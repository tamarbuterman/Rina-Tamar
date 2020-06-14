package scene;



import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import primitives.Color;
import primitives.Point3D;

public class Scene
{
	/**
	 * Scene fields
	 */
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	/**
	 * geometries list
	 */
	private Geometries _geometries;
	private Camera _camera;
	private double _distance;
	/**
	 * lights list
	 */
	private List<LightSource> _lights;
	
	/**
	 * depth of field
	 */
	private double _focalPlaneDistance;
	
	/**
	 * scene constructor
	 * @param name
	 */
	public Scene(String name)
	{
		_name = name;
		_geometries = new Geometries();
		_lights = new LinkedList<LightSource>();
		
	}
	/**
	 * getter and set function
	 * 
	 */
	public String getName()
	{
		return _name;
	}
	public Color getBackground()
	{
		return _background;
	}
	public AmbientLight getAmbientLight()
	{
		return _ambientLight;
	}
	public Geometries getGeometries()
	{
		return _geometries;
	}
	public Camera getCamera()
	{
		return _camera;
	}
	public double getDistance()
	{
		return _distance;
	}
	public void setAmbientLight(AmbientLight ambient)
	{
		_ambientLight = ambient;
	}
	public void setBackground(Color color)
	{
		_background = color;
	}
	public void setCamera(Camera camera)
	{
		_camera = camera;
	}
	public void setDistance(double dis)
	{
		_distance = dis;		
	}
	public void addGeometries(Intersectable... geometries) 
	{
		_geometries.add(geometries);
	}
	public List<LightSource> getLights()
	{
		return _lights;
	}
	public void addLights(LightSource... lights)
	{
		int size = lights.length;
		for(int i=0; i<size; i++)
		{
			_lights.add(lights[i]);
		}
	}
	
	
	public void setFocalPlane(double d)
	{
		_focalPlaneDistance = d;
	}
	
	public double getFocalPlaneDistance()
	{
		return _focalPlaneDistance;
	}
}
