package clases;

public class Atencion {

	//	Atributos privados
	private int codigoAtencion, codigoPaciente, estado;
	private String fechaAtencion, horaAtencion;
	private double aPagar;
	//	Constructor
	public Atencion(int codigoAtencion, int codigoPaciente, String fechaAtencion,
			        String horaAtencion, double aPagar, int estado) {
		this.codigoAtencion = codigoAtencion;
		this.codigoPaciente = codigoPaciente;
		this.fechaAtencion = fechaAtencion;
		this.horaAtencion = horaAtencion;
		this.aPagar = aPagar;
		this.estado = estado;
	}

	public void setCodigoAtencion(int codigoAtencion) {
		this.codigoAtencion = codigoAtencion;
	}
	public void setCodigoPaciente(int codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	public void setFechaAtencion(String fechaConsulta) {
		this.fechaAtencion = fechaConsulta;
	}
	public void setHoraAtencion(String horaAtencion) {
		this.horaAtencion = horaAtencion;
	}
	public void setApagar(double aPagar) {
		this.aPagar = aPagar;
	}
	public void setEstado(int estado) {
		this.estado=estado;
	}
	public int getCodigoAtencion() {
		return codigoAtencion;
	}
	public int getCodigoPaciente() {
		return codigoPaciente;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public String getHoraAtencion() {
		return horaAtencion;
	}
	public double getApagar() {
		return aPagar;
	}
	public int getEstado() {
		return estado;
	}
	
}