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
 * @author dylan
 */
public class Producto {
    private int codigoProducto;
    private String descripcion;
    private double precio;

    public Producto(){}

    public Producto(int codigoProducto, String descripcion, double precio) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.precio = precio;
    }           
    
    public Producto(String param) {
        String[] stringCarga = param.split(";"); //param linea
        
        this.setCodigoProducto(Integer.parseInt(stringCarga[0].trim()));
        this.setDescripcion(stringCarga[1].trim());
        this.setPrecio(stringCarga[2].trim());

    }
       
    
    public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	// Metodos Adicionales

	public String generarStringAlmacenamiento(){
        String stringAlmacenamiento = this.getCodigoProducto() + ";" +
                    this.getDescripcion()+ ";" +
                    this.getPrecio()+ ";" ;
        return stringAlmacenamiento;
    }

}
