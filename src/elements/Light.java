package elements;

import primitives.Color;

abstract class Light 
{
	protected Color _intensity;
	
	public Light(Color intensity)
	{
		_intensity = new Color(intensity);
	}
	
	public Color getIntensity() { return _intensity; }
}
