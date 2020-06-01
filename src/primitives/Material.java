package primitives;

public class Material {

	double _kD;
	double _kS;
	int _nShininess;
	/*
	 * constructor
	 */
	public Material(double kd, double ks, int shininess)
	{
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
	}
	/*
	 * get
	 */
	public double getKD()
	{
		return _kD;
	}
	/*
	 * 
	 */
	public double getKS()
	{
		return _kS;
	}
	/*
	 * 
	 */
	public int getShin()
	{
		return _nShininess;
	}
}
