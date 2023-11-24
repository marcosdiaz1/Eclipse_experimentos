package clases;

public class Medicina {

	//	Atributos privados
	private int codigoMedicina, stock;
	private String nombre, laboratorio;
	private double precioUnitario;
	//	Constructor
	public Medicina(int codigoMedicina, String nombre, String laboratorio, int stock, double precioUnitario) {
		this.codigoMedicina = codigoMedicina;
		this.nombre = nombre;
		this.laboratorio = laboratorio;
		this.stock = stock;
		this.precioUnitario = precioUnitario;
	}
	
	public void setCodigoMedicina(int codigoMedicina) {
		this.codigoMedicina = codigoMedicina;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}	 	 
	public void setStock(int stock) {
		this.stock = stock;
	}	 
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getCodigoMedicina() {
		return codigoMedicina;
	}
	public String getNombre() {
		return nombre;
	}
	public String getLaboratorio() {
		return laboratorio;
	}	 	 
	public int getStock() {
		return stock;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
}