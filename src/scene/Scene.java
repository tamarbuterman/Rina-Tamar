package scene;


import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene
{
	String _name;
	Color _background;
	AmbientLight _ambientLight;
	Geometries _geometries;
	Camera _camera;
	double _distance;
	
	public Scene(String name)
	{
		_name = name;
		_geometries = new Geometries();
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
	}
	public void addGeometries(Intersectable... geometries) 
	{
		_geometries.add(geometries);
	}
}
