package primitives;

public class Material {

	/**
	 * Exclusion factor
	 */
	double _kD;
	/**
	 * Exclusion factor
	 */
	double _kS;
	/**
	 * the shininess of the material
	 */
	int _nShininess;
	
	double _kR;
	double _kT;
	
	/**
	 * Material constructor gets 3 parameters 
	 * 
	 * @param kd
	 * @param ks
	 * @param shininess
	 */
	public Material(double kd, double ks, int shininess)
	{
		this(kd,ks,shininess,0,0);

	}
	
	public Material(double kd, double ks, int shininess, double kt, double kr)
	{
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
		_kT = kt;
		_kR = kr;
	}
	
	public void setShininess(int sh)
	{
		_nShininess = sh;
	}
	/**
	 * Material getter returns the kD
	 * 
	 * @return double
	 */
	public double getKD()
	{
		return _kD;
	}
	
	/**
	 * Material getter returns the kS
     * 
	 * @return
	 */
	public double getKS()
	{
		return _kS;
	}
	
	/**
	 * Material getters return the _nShininess
	 * 
	 * @return int
	 */
	public int getShin()
	{
		return _nShininess;
	}
	
	public double getKR()
	{
		return _kR;
	}
	public double getKT()
	{
		return _kT;
	}
}
