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
 * @author truchas
 */
public class Cliente {
    private int codigoCliente;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String dni;

    public Cliente(){
        nombres = "";
        apellidos = "";
        telefono = "";
        dni = "";
    }

    public Cliente(int codigoCliente, String nombres, String apellidos, String telefono, String dni ) {
        this.codigoCliente = codigoCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
    }           
    

    public Cliente(String param) {
        String[] stringCarga = param.split(";");
        
        this.setCodigoCliente(Integer.parseInt(stringCarga[0].trim()));
        this.setNombres(stringCarga[1].trim());
        this.setApellidos(stringCarga[2].trim());
        this.setTelefono(stringCarga[3].trim());
        this.setDni(stringCarga[4].trim());
    }
    
    public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	 // Metodos Adicionales

	public String generarStringAlmacenamiento(){
        String stringAlmacenamiento = this.getCodigoCliente() + ";" +
                    this.getNombres()+ ";" +
                    this.getApellidos()+ ";" +
                    this.getTelefono()+ ";" +
                    this.getDni();
        return stringAlmacenamiento;
    }
    
}
