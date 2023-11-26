/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;


import clases.Producto;
import clases.Vendedor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author dylan
 */
public class DBUtils {
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Genericos">
    public static void grabarData(ArrayList<Object> lista, String target) {
        try {                    
                PrintWriter pw = new PrintWriter(new FileWriter(target+ ".txt"));
                for (Object object : lista) {
                    String stringAlmacenamiento = "";
                    switch(target){
                        case "vendedores":
                            stringAlmacenamiento = ((Vendedor)object).generarStringAlmacenamiento();
                    }
                   
                    pw.println(stringAlmacenamiento);
                    pw.close();
                }
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Object> cargarData(String target) {
        ArrayList<Object> lista = new ArrayList<>();
        try {               
                File file = new File(target+".txt");
                if(!file.exists())
                    file.createNewFile();
                String linea;
                String[] StringCarga;
                BufferedReader br = new BufferedReader(new FileReader(target+".txt"));
                while ((linea=br.readLine()) != null) {
                        switch(target){
                            case "vendedores":
                                lista.add(new Vendedor(linea));
                        }
                }
                br.close();
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return lista;
    }
    
    public static ArrayList<Object> buscarDataCodigo(String target, String paramBusqueda) {
        ArrayList<Object> matches = new ArrayList();
        switch(target){
            case "vendedores":
                ArrayList<Vendedor> lista = parsearListaVendedor(cargarData(target));
                for (Vendedor vendedor : lista) {
                    if(String.valueOf(vendedor.getCodigoVendedor()).contains(paramBusqueda))
                        matches.add(vendedor);
                }
        }
        return matches;
    }
    //<editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos De Parseo Lista">
    public static ArrayList<Vendedor> parsearListaVendedor(ArrayList<Object> param){
        ArrayList<Vendedor> listaVendedor = new ArrayList<>();

        for (Object obj : param) {
            if (obj instanceof Vendedor) {
                Vendedor vendedor = (Vendedor) obj;
                listaVendedor.add(vendedor);
            } else {
                System.out.println("Objeto no es un Vendedor: " + obj.toString());
            }
        }
        return listaVendedor;
    }
    //<editor-fold>
    
    public static int numeroCorrelativo(ArrayList<Object> lista, String target) {
        switch(target){
            case "vendedores":
                if (lista.size() == 0)
                    return 2001;
		else
                    return ((Vendedor) lista.get(lista.size() - 1)).getCodigoVendedor();
        }
        return 0;
    }

	public static ArrayList<Producto> parsearListaProducto(ArrayList<Object> cargarData) {
		// TODO Auto-generated method stub
		return null;
	} 
}
