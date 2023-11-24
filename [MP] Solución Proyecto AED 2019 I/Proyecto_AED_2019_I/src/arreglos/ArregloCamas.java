package arreglos;

import clases.Cama;

import java.io.*;
import java.util.ArrayList;

public class ArregloCamas {
	
	//  Atributos privados
	private ArrayList <Cama> cama;
	//  Constructor
	public ArregloCamas() {
		cama = new ArrayList <Cama> ();
		cargarCamas();
	}
	//  Operaciones p�blicas b�sicas
	public void adicionar(Cama x) {
		cama.add(x);
	}
	public int tamanio() {
		return cama.size();
	}
	public Cama obtener(int i) {
		return cama.get(i);
	}
	public Cama buscar(int numeroCama) {
		for (int i=0; i<tamanio(); i++)
			if (obtener(i).getNumeroCama() == numeroCama)
				return obtener(i);
		return null;
	}
	public void eliminar(Cama x) {
		cama.remove(x);
	}
	public void grabarCamas() {
		try {
			PrintWriter pw;
			String linea;
			Cama x;
			pw = new PrintWriter(new FileWriter("camas.txt"));
			for(int i=0; i<tamanio(); i++) {
				x = obtener(i);
				linea = x.getNumeroCama() + ";" +
						x.getCategoria() + ";" +
						x.getPrecioDia() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarCamas() {
		try {
			BufferedReader br;
			String linea;
			String[] s;
			int numeroCama, categoria, estado;
			double precioDia;
			br = new BufferedReader(new FileReader("camas.txt"));
			while ((linea=br.readLine()) != null) {
				s = linea.split(";");
				numeroCama = Integer.parseInt(s[0].trim());
				categoria = Integer.parseInt(s[1].trim());
				precioDia = Double.parseDouble(s[2].trim());
				estado = Integer.parseInt(s[3].trim());
				adicionar(new Cama(numeroCama, categoria, precioDia, estado));
			}
			br.close();	
		}
		catch (Exception e) {
		}
	}
	//  Operaciones p�blicas complementarias
	public int numeroCorrelativo() {
		if (tamanio() == 0)
			return 10001;
		else
			return obtener(tamanio()-1).getNumeroCama() + 1;
	}

}