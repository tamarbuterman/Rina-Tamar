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
	 * 
	 */
	String _name;
	Color _background;
	AmbientLight _ambientLight;
	Geometries _geometries;
	Camera _camera;
	double _distance;
	List<LightSource> _lights;
	
	Plane _focalPlane;
	
	public Scene(String name)
	{
		_name = name;
		_geometries = new Geometries();
		_lights = new LinkedList<LightSource>();
		
	}
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
		
		
		//setFocalPlane(dis);
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
		Point3D p1 = _camera.getP0().add(_camera.getVto().scale(d));
		Point3D p2 = p1.add(_camera.getVup());
		Point3D p3 = p1.add(_camera.getVright());
		_focalPlane = new Plane(p1, p2, p3);
	}
	
	public Plane getFocalPlane()
	{
		return _focalPlane;
	}
}
