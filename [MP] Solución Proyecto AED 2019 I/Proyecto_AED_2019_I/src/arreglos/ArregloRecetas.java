package arreglos;

import clases.Receta;

import java.io.*;
import java.util.ArrayList;

public class ArregloRecetas {
	
	//  Atributos privados
	private ArrayList <Receta> receta;
	private String numeroReceta;
	//  Constructor
    public ArregloRecetas(String numeroReceta) {
    	this.numeroReceta = numeroReceta;
    	receta = new ArrayList <Receta> ();
		cargarReceta();   	
    }   
	//  Operaciones publicas basicas
	public void adicionar(Receta x) {
		receta.add(x);
	}
    public int tamanio() {
		return receta.size();
	}
	public Receta obtener(int i) {
		return receta.get(i);
	}
	public Receta buscar(int codigoMedicina) {
		for (int i=0; i<tamanio(); i++)
			if (obtener(i).getCodigoMedicina() == codigoMedicina)
				return obtener(i);
		return null;
	}
	public void eliminar(Receta x) {
		receta.remove(x);
	}
	public void grabarReceta() {
		try {
			PrintWriter pw;
			String linea;
			Receta x;
			pw = new PrintWriter(new FileWriter(numeroReceta + ".txt"));
			for (int i=0; i<tamanio(); i++) {
				x = obtener(i);
				linea = x.getCodigoMedicina() + ";" +
						x.getCantidad() + ";" +
						x.getPrecioUnitario();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarReceta() {
		try {
			BufferedReader br;
			String linea; 
			String[] s;
			int codigoMedicina, cantidad;
			double precioUnitario;
			br = new BufferedReader(new FileReader(numeroReceta + ".txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigoMedicina = Integer.parseInt(s[0].trim());
				cantidad = Integer.parseInt(s[1].trim());
				precioUnitario = Double.parseDouble(s[2].trim());
				adicionar(new Receta(codigoMedicina, cantidad, precioUnitario));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	
}