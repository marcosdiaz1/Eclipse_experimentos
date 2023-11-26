/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;


import clases.Cliente;
import clases.Factura;
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
                            break;
                        case "productos":
                            stringAlmacenamiento = ((Producto)object).generarStringAlmacenamiento();
                            break;
                        case "clientes":
                            stringAlmacenamiento = ((Cliente)object).generarStringAlmacenamiento();
                            break;
                        case "facturas":
                            stringAlmacenamiento = ((Factura)object).generarStringAlmacenamiento();
                            break;
                    }
                   
                    pw.println(stringAlmacenamiento);
                    
                }
                
                pw.close();
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
                                break;
                            case "productos":
                                lista.add(new Producto(linea));
                                break;
                            case "clientes":
                                lista.add(new Cliente(linea));
                                break;
                            case "facturas":
                                lista.add(new Factura(linea));
                                break;
                        }
                }
                br.close();
            }
        catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
        
        return lista;
    }
    
    public static ArrayList<Object> buscarDataCodigo(String target, String paramBusqueda) {
        ArrayList<Object> matches = new ArrayList();
        switch(target){
            case "vendedores":
                ArrayList<Vendedor> listaVendedor = parsearListaVendedor(cargarData(target));
                for (Vendedor vendedor : listaVendedor) {
                    if(String.valueOf(vendedor.getCodigoVendedor()).contains(paramBusqueda))
                        matches.add(vendedor);
                }
                break;
            case "productos":
                ArrayList<Producto> listaProducto = parsearListaProducto(cargarData(target));
                for (Producto producto : listaProducto) {
                    if(String.valueOf(producto.getCodigoProducto()).contains(paramBusqueda))
                        matches.add(producto);
                }
                break;
            case "clientes":
                ArrayList<Cliente> listaCliente = parsearListaCliente(cargarData(target));
                for (Cliente cliente : listaCliente) {
                    if(String.valueOf(cliente.getCodigoCliente()).contains(paramBusqueda))
                        matches.add(cliente);
                }
                break;
            case "facturas":
                ArrayList<Factura> listaFactura = parsearListaFactura(cargarData(target));
                for (Factura factura : listaFactura) {
                    if(String.valueOf(factura.getCodigoFactura()).contains(paramBusqueda))
                        matches.add(factura);
                }
                break;
        }
        return matches;
    }
    //</editor-fold>
    
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
    
    public static ArrayList<Factura> parsearListaFactura(ArrayList<Object> param){
        ArrayList<Factura> listaFactura = new ArrayList<>();

        for (Object obj : param) {
            if (obj instanceof Factura) {
                Factura factura = (Factura) obj;
                listaFactura.add(factura);
            } else {
                System.out.println("Objeto no es una Factura: " + obj.toString());
            }
        }
        return listaFactura;
    }
    
    public static ArrayList<Producto> parsearListaProducto(ArrayList<Object> param){
        ArrayList<Producto> listaProducto = new ArrayList<>();

        for (Object obj : param) {
            if (obj instanceof Producto) {
                Producto producto = (Producto) obj;
                listaProducto.add(producto);
            } else {
                System.out.println("Objeto no es un Producto: " + obj.toString());
            }
        }
        return listaProducto;
    }
    
    public static ArrayList<Cliente> parsearListaCliente(ArrayList<Object> param){
        ArrayList<Cliente> listaCliente = new ArrayList<>();

        for (Object obj : param) {
            if (obj instanceof Cliente) {
                Cliente cliente = (Cliente) obj;
                listaCliente.add(cliente);
            } else {
                System.out.println("Objeto no es un CLiente: " + obj.toString());
            }
        }
        return listaCliente;
    }
    //</editor-fold>
    
    public static int numeroCorrelativo(ArrayList<Object> lista, String target) {
        switch(target){
            case "vendedores":
                if (lista.size() == 0)
                    return 2001;
		else
                    return ((Vendedor) lista.get(lista.size() - 1)).getCodigoVendedor()  + 1;
            case "productos":
                if (lista.size() == 0)
                    return 3001;
		else
                    return ((Producto) lista.get(lista.size() - 1)).getCodigoProducto()  + 1;
            case "clientes":
                if (lista.size() == 0)
                    return 1001;
		else
                    return ((Cliente) lista.get(lista.size() - 1)).getCodigoCliente()  + 1;
            case "facturas":
                if (lista.size() == 0)
                    return 4001;
		else
                    return ((Factura) lista.get(lista.size() - 1)).getCodigoFactura() + 1;
        }
        return 0;
    }

}
