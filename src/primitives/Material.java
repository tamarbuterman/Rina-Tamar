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
	
	double kR;
	double kT;
	
	/**
	 * Material constructor gets 3 parameters 
	 * 
	 * @param kd
	 * @param ks
	 * @param shininess
	 */
	public Material(double kd, double ks, int shininess)
	{
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
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
		return kR;
	}
	public double getKT()
	{
		return kT;
	}
}
