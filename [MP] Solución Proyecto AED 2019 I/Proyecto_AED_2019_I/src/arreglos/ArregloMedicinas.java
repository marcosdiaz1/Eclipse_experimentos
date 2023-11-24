package arreglos;

import clases.Medicina;

import java.io.*;
import java.util.ArrayList;

public class ArregloMedicinas {
	
	//	Atributos privado
	private ArrayList <Medicina> medicina;
	//  Constructor
	public ArregloMedicinas() {
		medicina = new ArrayList <Medicina> ();
		cargarMedicinas();
	}
	//  Operaciones p�blicas b�sicas
	public void adicionar(Medicina x) {
		medicina.add(x);
	}
	public int tamanio() {
		return medicina.size();
	}
	public Medicina obtener(int i) {
		return medicina.get(i);
	}
	public Medicina buscar(int codigoMedicina) {
		for (int i=0; i<tamanio(); i++)
			if (obtener(i).getCodigoMedicina() == codigoMedicina)
				return obtener(i);
		return null;
	}
	public void eliminar(Medicina x) {
		medicina.remove(x);
	}
	public void grabarMedicinas() {
		try {
			PrintWriter pw;
			String linea;
			Medicina x;
			pw = new PrintWriter(new FileWriter("medicinas.txt"));
			for (int i=0; i<tamanio(); i++) {
				x = obtener(i);
				linea = x.getCodigoMedicina() + ";" +
						x.getNombre() + ";" +
						x.getLaboratorio() + ";" +
						x.getStock() + ";" +
						x.getPrecioUnitario();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {	
		}
	}
	private void cargarMedicinas() {
		try {
			BufferedReader br;
			String linea, nombre, laboratorio;
			String[] s;
			int codigoMedicina, stock;
			double precioUnitario;
			br = new BufferedReader(new FileReader("medicinas.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoMedicina = Integer.parseInt(s[0].trim());
				nombre = s[1].trim();
				laboratorio = s[2].trim();
				stock = Integer.parseInt(s[3].trim());
				precioUnitario = Double.parseDouble(s[4].trim());
				adicionar(new Medicina(codigoMedicina, nombre, laboratorio, stock, precioUnitario));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones p�blicas complementarias
	public int codigoCorrelativo() {
		if (tamanio() == 0) {
			return 30001;
		}
		else
			return obtener(tamanio()-1).getCodigoMedicina() + 1;
	}
	
}