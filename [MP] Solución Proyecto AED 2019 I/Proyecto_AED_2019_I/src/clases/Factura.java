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
import libreria.DBUtils;

/**
 *
 * @author alexis2
 */
public class Factura {
    private int codigoFactura;
    private int codigoProducto;
    private int codigoVendedor;
    private int codigoCliente;
    private ArrayList<Producto> producto;
    private Vendedor vendedor;
    private Cliente cliente;
    private int unidades;
    private double precio;

    public Factura(){}    

    public Factura(int codigoFactura, int codigoProducto, int codigoVendedor, int codigoCliente, ArrayList<Producto> producto, Vendedor vendedor, Cliente cliente, int unidades, double precio) {
        this.codigoFactura = codigoFactura;
        this.codigoProducto = codigoProducto;
        this.codigoVendedor = codigoVendedor;
        this.codigoCliente = codigoCliente;
        this.producto = producto;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.unidades = unidades;
        this.precio = precio;
    }

    public Factura(String param) {
        String[] stringCarga = param.split(";");
        
        this.setCodigoFactura(Integer.parseInt(stringCarga[0].trim()));
        this.setCodigoProducto(Integer.parseInt(stringCarga[1].trim()));
        this.setCodigoCliente(Integer.parseInt(stringCarga[2].trim()));
        this.setUnidades(Integer.parseInt(stringCarga[3].trim()));
        this.setPrecio(Integer.parseInt(stringCarga[4].trim()));
        
        this.setProducto(DBUtils.parsearListaProducto(DBUtils.buscarDataCodigo("productos", String.valueOf(this.getCodigoProducto()))));
        
        ArrayList<Vendedor> lstVendedor = DBUtils.parsearListaVendedor(DBUtils.buscarDataCodigo("vendedores", String.valueOf(this.getCodigoVendedor())));
        if(lstVendedor.size() == 1){
            this.vendedor = lstVendedor.get(0);
        }
        
        ArrayList<Cliente> lstCliente = DBUtils.parsearListaCliente(DBUtils.buscarDataCodigo("clientes", String.valueOf(this.getCodigoCliente())));
        if(lstVendedor.size() == 1){
            this.cliente = lstCliente.get(0);
        }
        
        
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public ArrayList<Producto> getProducto() {
        return producto;
    }

    public void setProducto(ArrayList<Producto> producto) {
        this.producto = producto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
                    this.getCodigoProducto()+ ";" +
                    this.getCodigoVendedor()+ ";" +
                    this.getCodigoCliente()+ ";" +
                    this.getUnidades()+ ";" +
                    this.getPrecio()+ ";" ;
        return stringAlmacenamiento;
    }       
        
    
}
