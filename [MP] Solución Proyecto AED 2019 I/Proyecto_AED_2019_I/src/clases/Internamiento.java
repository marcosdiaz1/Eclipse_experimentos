package clases;

public class Internamiento {
	//	Atributos privados
	private int codigoInternamiento, codigoPaciente, numeroCama, estado;
	private String fechaIngreso, horaIngreso, fechaSalida, horaSalida;
	private double totalPagar;
	//	Constructor
	public Internamiento(int codigoInternamiento, int codigoPaciente, int numeroCama,
			             String fechaIngreso, String horaIngreso, String fechaSalida,
			             String horaSalida, double totalPagar, int estado) {
		this.codigoInternamiento = codigoInternamiento;
		this.codigoPaciente = codigoPaciente;
		this.numeroCama = numeroCama;
		this.fechaIngreso = fechaIngreso;
		this.horaIngreso = horaIngreso;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.totalPagar = totalPagar;
		this.estado = estado;
	}

	public void setCodigoInternamiento(int codigoInternamiento) {
		this.codigoInternamiento = codigoInternamiento;
	}
	public void setCodigoPaciente(int codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	public void setNumeroCama(int numeroCama) {
		this.numeroCama = numeroCama;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCodigoInternamiento() {
		return codigoInternamiento;
	}
	public int getCodigoPaciente() {
		return codigoPaciente;
	}
	public int getNumeroCama() {
		return numeroCama;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public String getHoraIngreso() {
		return horaIngreso;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public double getTotalPagar() {
		return totalPagar;
	}
	public int getEstado() {
		return estado;
	}

}