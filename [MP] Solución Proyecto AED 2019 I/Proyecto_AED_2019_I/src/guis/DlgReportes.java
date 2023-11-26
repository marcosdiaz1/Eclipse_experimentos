/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import clases.Factura;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import libreria.DBUtils;

/**
 *
 * @author dylan
 */
public class DlgReportes extends JDialog {
	
	private JScrollPane scrollPane;
	private JTextArea txtCuerpoReporte;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgReportes dialog = new DlgReportes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgReportes() {
		setResizable(false);
		setTitle("Reporte | Ventas");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 675, 345);
		getContentPane().add(scrollPane);
	
		txtCuerpoReporte = new JTextArea();
		txtCuerpoReporte.setFont(new Font("Monospaced", Font.PLAIN, 15));
                txtCuerpoReporte.setEditable(false);
		scrollPane.setViewportView(txtCuerpoReporte);
		
		listar();
	}
	
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArrayList<Factura> lstFacturas = DBUtils.parsearListaFactura(DBUtils.cargarData("facturas"));
                
		txtCuerpoReporte.setText("");
                imprimir();
                for (Factura factura : lstFacturas) {
                    imprimir("Codigo de Factura :  " + factura.getCodigoFactura());
                    imprimir("Cliente :  " + factura.getCliente().getNombres() + " " + factura.getCliente().getApellidos());
                    imprimir("Vendedor :  " + factura.getVendedor().getNombres() + " " + factura.getVendedor().getApellidos());
                    imprimir("Producto :  " + factura.getProducto().getDescripcion());
                    imprimir("Precio Unitario :  S/." + factura.getPrecio());
                    imprimir("Cantidad :  " + factura.getUnidades());
                    imprimir("Total :  S/." + factura.getTotal());
                    imprimir("════════════════════════════════════════════");
                    imprimir();
                }
	}
        
	void imprimir(String s) {
		txtCuerpoReporte.append(s + "\n");
	}	
}
