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
public class Vendedor {
    private int codigoVendedor;
    private int categoria;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String dni;

    public Vendedor(){}

    public Vendedor(int codigoVendedor, int categoria, String nombres, String apellidos, String telefono, String dni ) {
        this.codigoVendedor = codigoVendedor;
        this.categoria = categoria;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
    }
            
    

    public Vendedor(String param) {
        String[] stringCarga = param.split(";");
        
        this.setCodigoVendedor(Integer.parseInt(stringCarga[0].trim()));
        this.setCategoria(Integer.parseInt(stringCarga[1].trim()));
        this.setNombres(stringCarga[2].trim());
        this.setApellidos(stringCarga[3].trim());
        this.setTelefono(stringCarga[4].trim());
        this.setDni(stringCarga[5].trim());
    }
    
    

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoCliente) {
        this.codigoVendedor = codigoCliente;
    }
    
    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
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
        String stringAlmacenamiento = this.getCodigoVendedor() + ";" +
                    this.getCategoria()+ ";" +
                    this.getNombres()+ ";" +
                    this.getApellidos()+ ";" +
                    this.getTelefono()+ ";" +
                    this.getDni();
        return stringAlmacenamiento;
    }
}
