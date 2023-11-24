package arreglos;

import clases.Atencion;

import java.io.*;
import java.util.ArrayList;

public class ArregloAtenciones {
	//  Atributos privados
	private ArrayList <Atencion> atencion;
	//  Constructor
	public ArregloAtenciones() {
		atencion = new ArrayList <Atencion> ();
		cargarAtenciones();
	}
	//  Operaciones p�blicas b�sicas
	public void adicionar(Atencion x) {
		atencion.add(x);
	}
	public int tamanio() {
		return atencion.size();
	}
	public Atencion obtener(int i) {
		return atencion.get(i);
	}
	public Atencion buscar(int codigoAtencion) {
		for (int i=0; i<tamanio(); i++)
			if (obtener(i).getCodigoAtencion() == codigoAtencion)
				return obtener(i);
		return null;
	}
	public void eliminar(Atencion x) {
		atencion.remove(x);
	} 
	public void grabarAtenciones() {
		try {
			PrintWriter pw;
			String linea;
			Atencion x;
			pw = new PrintWriter(new FileWriter("atenciones.txt"));
			for (int i=0; i<tamanio(); i++) {
				x = obtener(i);
				linea =	x.getCodigoAtencion() + ";" +
						x.getCodigoPaciente() + ";" +
						x.getFechaAtencion() + ";" +
						x.getHoraAtencion() + ";" +
						x.getApagar() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarAtenciones() {
		try {
			BufferedReader br;
			String linea, fechaAtencion, horaAtencion;
			String[] s;
			int codigoAtencion, codigoPaciente, estado;
			double totalPagar;
			br = new BufferedReader(new FileReader("atenciones.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				codigoAtencion = Integer.parseInt(s[0].trim());
				codigoPaciente = Integer.parseInt(s[1].trim());
				fechaAtencion = s[2].trim();
				horaAtencion = s[3].trim();
				totalPagar = Double.parseDouble(s[4].trim());
				estado = Integer.parseInt(s[5].trim());
				adicionar(new Atencion(codigoAtencion, codigoPaciente,
						               fechaAtencion, horaAtencion, totalPagar, estado));
			}
			br.close();
		}
		catch (Exception e) {	
		}
	}
	//  Operaciones p�blicas complementarias
	public int codigoCorrelativo() {
		if (tamanio() == 0)
			return 40001;
		else
			return obtener(tamanio()-1).getCodigoAtencion() + 1;
	}
	public boolean procedeCodigoPacientes(int codigoPaciente) {
		for (int i=tamanio()-1; i>=0; i--)
			if (obtener(i).getCodigoPaciente() == codigoPaciente  &&  obtener(i).getEstado() == 0)
				return false;
		return true;
	}
	
}