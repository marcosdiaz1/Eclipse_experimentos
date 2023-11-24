package arreglos;

import clases.Internamiento;

import java.io.*;
import java.util.ArrayList;

public class ArregloInternamientos {
	//  Atributo privado
	private ArrayList <Internamiento> internamiento;
    //  Constructor
	public ArregloInternamientos() {
		internamiento = new ArrayList <Internamiento> ();
		cargarInternamientos();
	}
	//  Operaciones p�blicas b�sicas
	public void adicionar(Internamiento x) {
		internamiento.add(x);
	}
	public int tamanio() {
		return internamiento.size();
	}
	public Internamiento obtener(int i) {
		return internamiento.get(i);
	}
	public Internamiento buscar(int codigoInternamiento) {
		for (int i=0; i<tamanio(); i++)
			if (obtener(i).getCodigoInternamiento() == codigoInternamiento)
				return obtener(i);
		return null;
	}
	public void eliminar(Internamiento x) {
		internamiento.remove(x);
	}
	public void grabarInternamientos() {
		try {
			PrintWriter pw;
			String linea;
			Internamiento x;
			pw = new PrintWriter(new FileWriter("internamientos.txt"));
			for (int i=0; i<tamanio(); i++) {
				x = obtener(i);
				linea =	x.getCodigoInternamiento() + ";" +
						x.getCodigoPaciente() + ";" +
						x.getNumeroCama() + ";" +
						x.getFechaIngreso() + ";" +
						x.getHoraIngreso() + ";" +
						x.getFechaSalida() + ";" +
						x.getHoraSalida() + ";" +
						x.getTotalPagar() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	public void cargarInternamientos() {
		try {
			BufferedReader br;
			String linea, fechaIngreso, horaIngreso, fechaSalida, horaSalida;
			String[] s;
			int codigoInternamiento, codigoPaciente, numeroCama, estado;
			double totalPagar;
			br = new BufferedReader(new FileReader("internamientos.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				codigoInternamiento = Integer.parseInt(s[0].trim());
				codigoPaciente = Integer.parseInt(s[1].trim());
				numeroCama = Integer.parseInt(s[2].trim());
				fechaIngreso = s[3].trim();
				horaIngreso = s[4].trim();
				fechaSalida = s[5].trim();
				horaSalida = s[6].trim();
				totalPagar = Double.parseDouble(s[7].trim());
				estado = Integer.parseInt(s[8].trim());
				adicionar(new Internamiento(codigoInternamiento, codigoPaciente, numeroCama, fechaIngreso, 
						                    horaIngreso, fechaSalida, horaSalida, totalPagar, estado));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones p�blicas complementarias
	public int codigoCorrelativo() {
		if (tamanio() == 0)
			return 50001;
		else
			return obtener(tamanio()-1).getCodigoInternamiento() + 1;
	}
	public boolean procedeCodigoPaciente(int codigoPaciente) {
		for (int i=tamanio()-1; i>=0; i--)
			if (obtener(i).getCodigoPaciente() == codigoPaciente)
				if (obtener(i).getEstado() == 0)
					return false;
				else
					return true;
		return true;
	}
	
}