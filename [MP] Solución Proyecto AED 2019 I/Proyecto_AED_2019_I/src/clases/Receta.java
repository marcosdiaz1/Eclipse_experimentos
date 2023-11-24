package clases;

public class Receta {
	
	//  Atributos privados
	private int codigoMedicina, cantidad;
	private double precioUnitario;	
	//  Constructor
    public Receta(int codigoMedicina, int cantidad, double precioUnitario) {
        this.codigoMedicina = codigoMedicina;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
	public void setCodigoMedicina(int codigoMedicina) {
		this.codigoMedicina = codigoMedicina;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getCodigoMedicina() {
		return codigoMedicina;
	}
	public int getCantidad() {
		return cantidad;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
}