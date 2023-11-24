package guis;

import clases.*;
import arreglos.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DlgAtenciones extends JDialog implements ActionListener, MouseListener {
	
	private JLabel lblCodigoAtencion;
	private JComboBox <String> cboCodigoAtencion;
	private JButton btnPagar;
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtenciones dialog = new DlgAtenciones();
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
	public DlgAtenciones() {
		setResizable(false);
		setTitle("Pago | Atenciones");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		lblCodigoAtencion = new JLabel("Atenci�n");
		lblCodigoAtencion.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoAtencion);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(this);
		btnPagar.addMouseListener(this);
		btnPagar.setForeground(Color.BLUE);
		btnPagar.setBounds(535, 10, 150, 23);
		getContentPane().add(btnPagar);

		cboCodigoAtencion = new JComboBox <String> ();
		cboCodigoAtencion.addActionListener(this);
		cboCodigoAtencion.addMouseListener(this);
		cboCodigoAtencion.setBounds(70, 10, 100, 23);
		getContentPane().add(cboCodigoAtencion);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 675, 310);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		colocarCodigosConsultas();
	}
	
	//  Declaraci�n global
	ArregloAtenciones aa = new ArregloAtenciones();
	ArregloPacientes ap = new ArregloPacientes();

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			actionPerformedBtnPagar(arg0);
		}
		if (arg0.getSource() == cboCodigoAtencion) {
			actionPerformedCboCodigoAtencion(arg0);
		}
	}
	protected void actionPerformedCboCodigoAtencion(ActionEvent arg0) {
		txtS.setText("");
		if (cboCodigoAtencion.getSelectedIndex() >= 0)
			listar();
	}
	protected void actionPerformedBtnPagar(ActionEvent arg0) {
		if (cboCodigoAtencion.getSelectedIndex() >= 0) { 
			int ok = confirmar("� Desea pagar Atenci�n ?");
			if (ok == 0) {
				Atencion a = aa.buscar(leerCodigoAtencion());
				a.setEstado(1);
				aa.grabarAtenciones();
				cboCodigoAtencion.removeItem(cboCodigoAtencion.getSelectedItem());
			}
		}
		else
			mensaje("No existen atenciones pendientes de pago");
	}	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			mouseEnteredBtnPagar(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseEnteredBtnPagar(MouseEvent arg0) {
		btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  M�todos tipo void (sin par�metros)
	void colocarCodigosConsultas() {
		Atencion a;
		for (int i=0; i<aa.tamanio(); i++) {
			a = aa.obtener(i);
			if (a.getEstado() == 0)
				cboCodigoAtencion.addItem("" + a.getCodigoAtencion());
		}	
	}
	void imprimir() {
		imprimir("");
	}
	void listar() {
		Atencion a = aa.buscar(leerCodigoAtencion());
		Paciente p = ap.buscar(a.getCodigoPaciente());
		imprimir("Paciente  :  " + p.getCodigoPaciente());
		imprimir("Nombres   :  " + p.getNombres());
		imprimir("Apellidos :  " + p.getApellidos());
		imprimir();
		imprimir("1) COSTO DE ATENCI�N S/ 100.00");
		imprimir();
		ArregloRecetas ar = new ArregloRecetas("" + leerCodigoAtencion());
		Receta r;
		imprimir("2) RECETA");
		imprimir("              Cantidad     Precio    Importe");
		double importePago;
		for (int i=0; i<ar.tamanio(); i++) {
			r = ar.obtener(i);
			importePago = r.getCantidad() * r.getPrecioUnitario();
			ArregloMedicinas am = new ArregloMedicinas();
			Medicina m = am.buscar(r.getCodigoMedicina());
			imprimir("   " + formato(m.getNombre()) + formato(r.getCantidad()) + 
					         formato(r.getPrecioUnitario()) + formato(importePago));
		}
		imprimir();
		imprimir("3) TOTAL A PAGAR S/ " + formato(a.getApagar()));
	}
	//  M�todos tipo void (con par�metros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informaci�n", 1);
	}
	//  M�todos que retornan valor (sin par�metros)
	int leerCodigoAtencion() {
		return Integer.parseInt(cboCodigoAtencion.getSelectedItem().toString());
	}
	//  M�todos que retornan valor (con par�metros)
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	String formato(String cadena) {
		return String.format("%-15s", cadena);
	}
	String formato(int entero) {
		return String.format("%-10d", entero);
	}
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	
}