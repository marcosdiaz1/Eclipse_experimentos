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
    private Producto producto;
    private Vendedor vendedor;
    private Cliente cliente;
    private int unidades;
    private double precio;
    private double total;

    public Factura(){}    

    public Factura(int codigoFactura, Producto producto, Vendedor vendedor, Cliente cliente, int unidades, double precio, double total) {
        this.codigoFactura = codigoFactura;
        this.producto = producto;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.unidades = unidades;
        this.precio = precio;
        this.total = total;
    }

    public Factura(String param) {
        String[] stringCarga = param.split(";");
        
        this.setCodigoFactura(Integer.parseInt(stringCarga[0].trim()));
        ArrayList<Producto> lstProducto = DBUtils.parsearListaProducto(DBUtils.buscarDataCodigo("productos", String.valueOf(stringCarga[1].trim())));
        if(lstProducto.size() == 1){
            this.producto = lstProducto.get(0);
        }
        else{
            this.producto = new Producto();
        }
        
        ArrayList<Vendedor> lstVendedor = DBUtils.parsearListaVendedor(DBUtils.buscarDataCodigo("vendedores", String.valueOf(stringCarga[2].trim())));
        if(lstVendedor.size() == 1){
            this.vendedor = lstVendedor.get(0);
        }
        else{
            this.vendedor = new Vendedor();
        }
        
        ArrayList<Cliente> lstCliente = DBUtils.parsearListaCliente(DBUtils.buscarDataCodigo("clientes", String.valueOf(stringCarga[3].trim())));
        if(lstCliente.size() == 1){
            this.cliente = lstCliente.get(0);
        }
        else{
            this.cliente = new Cliente();
        }
        this.setUnidades(Integer.parseInt(stringCarga[4].trim()));
        this.setPrecio(Double.parseDouble(stringCarga[5].trim()));
        this.setTotal(Double.parseDouble(stringCarga[6].trim()));
        
        
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
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
                    this.getProducto().getCodigoProducto()+ ";" +
                    this.getVendedor().getCodigoVendedor()+ ";" +
                    this.getCliente().getCodigoCliente()+ ";" +
                    this.getUnidades()+ ";" +
                    this.getPrecio()+ ";" +
                    this.getTotal()+ ";" ;
        return stringAlmacenamiento;
    }       
        
    
}
