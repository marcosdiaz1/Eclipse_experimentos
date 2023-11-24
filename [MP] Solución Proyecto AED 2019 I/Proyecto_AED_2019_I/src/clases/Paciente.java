package clases;

public class Paciente {
	//	Atributos privados
	private int codigoPaciente;
	private String nombres, apellidos, telefono, dni;
	//	Constructor
	public Paciente(int codigoPaciente, String nombres, String apellidos, String telefono, String dni) {
		this.codigoPaciente = codigoPaciente;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
	}

	public void setCodigoPaciente(int codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getCodigoPaciente() {
		return codigoPaciente;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDni() {
		return dni;
	}
	
}