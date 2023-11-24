package arreglos;

import clases.Paciente;

import java.io.*;
import java.util.ArrayList;

public class ArregloPacientes {
	
	//  Atributo privado
	private ArrayList <Paciente> paciente;
	//  Constructor
	public ArregloPacientes() {
		paciente = new ArrayList <Paciente> ();
		cargarPacientes();
	}
	//  Operaciones p�blicas b�sicas
	public void adicionar(Paciente x) {
		paciente.add(x);
	}
	public int tamanio() {
		return paciente.size();
	}
	public Paciente obtener(int i) {
		return paciente.get(i);
	}
	public Paciente buscar(int codigoPaciente) {
		for (int i=0; i<tamanio(); i++)
			if (obtener(i).getCodigoPaciente() == codigoPaciente)
				return obtener(i);
		return null;
	}
	public void eliminar(Paciente x) {
		paciente.remove(x);
	}
	public void grabarPacientes() {
		try {
			PrintWriter pw;
			String linea;
			Paciente x;
			pw = new PrintWriter(new FileWriter("pacientes.txt"));
			for (int i=0; i<tamanio(); i++) {
				x = obtener(i);
				linea = x.getCodigoPaciente() + ";" +
						x.getNombres() + ";" +
						x.getApellidos() + ";" +
						x.getTelefono() + ";" +
						x.getDni();
				pw.println(linea);
			}
			pw.close();	
		}
		catch (Exception e) {
		}
	}
	private void cargarPacientes() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int codigoPaciente;
			String nombres, apellidos, telefono, dni;
			br = new BufferedReader(new FileReader("pacientes.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoPaciente = Integer.parseInt(s[0].trim());
				nombres = s[1].trim();
				apellidos = s[2].trim();
				telefono = s[3].trim();
				dni = s[4].trim();
				adicionar(new Paciente(codigoPaciente, nombres, apellidos, telefono, dni));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones p�blicas complementarias
	public int codigoCorrelativo() {
		if (tamanio() == 0)
			return 20001;
		else
			return obtener(tamanio()-1).getCodigoPaciente() + 1;
	}
	
}