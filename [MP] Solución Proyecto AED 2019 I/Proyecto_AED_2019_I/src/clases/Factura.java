/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author alexis2
 */
public class Factura {
    private int codigoFactura;
    private ArrayList<Producto> producto;
    private Vendedor vendedor;
    private int unidades;
    private double precio;

    public Factura(){}           
    

    public Factura(int codigoFactura, ArrayList<Producto> producto, Vendedor vendedor, int unidades, double precio) {
		super();
		this.codigoFactura = codigoFactura;
		this.producto = producto;
		this.vendedor = vendedor;
		this.unidades = unidades;
		this.precio = precio;
	}

	public Factura(String param) {
        String[] stringCarga = param.split(";");
        
        this.setCodigoFactura(Integer.parseInt(stringCarga[0].trim()));
        this.setProducto(Integer.parseInt(stringCarga[1].trim()));
        this.setVendedor(Integer.parseInt(stringCarga[2].trim()));
        this.setUnidades(Integer.parseInt(stringCarga[3].trim()));
        this.setPrecio(Integer.parseInt(stringCarga[4].trim()));
    }
    	
   
    public int getCodigoFactura() {
		return codigoFactura;
	}


	public void setCodigoFactura(int codigoFactura) {
		this.codigoFactura = codigoFactura;
	}


	public ArrayList<Producto> getProducto() {
		return producto;
	}


	public void setProducto(Producto x) {
		this.producto = producto;
	}


	public Vendedor getVendedor(int i) {
		return vendedor;
	}


	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}


	public int getUnidades() {
		return unidades;
	}


	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}

	// Metodos Adicionales

	public String generarStringAlmacenamiento(){
        String stringAlmacenamiento = this.getCodigoFactura() + ";" +
                    this.getProducto()+ ";" +
                    this.getVendedor()+ ";" +
                    this.getUnidades()+ ";" +
                    this.getprecio ()+ ";" ;
        return stringAlmacenamiento;
    }
    
}
