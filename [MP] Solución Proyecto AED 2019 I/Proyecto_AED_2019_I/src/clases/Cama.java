package clases;

public class Cama {
	//	Atributos privados
	private int numeroCama, categoria, estado;
	private double precioDia;
	//	Constructor
	public Cama(int numeroCama, int categoria, double precioDia, int estado) {
		this.numeroCama = numeroCama;
		this.categoria = categoria;
		this.precioDia = precioDia;
		this.estado = estado;
	}

	public void setNumeroCama(int numeroCama) {
		this.numeroCama = numeroCama;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public void setPrecioDia(double precioDia) {
		this.precioDia = precioDia;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getNumeroCama() {
		return numeroCama;
	}
	public int getCategoria() {
		return categoria;
	}
	public double getPrecioDia() {
		return precioDia;
	}
	public int getEstado() {
		return estado;
	}
	
}