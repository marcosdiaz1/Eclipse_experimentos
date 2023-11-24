package guis;

import libreria.Lib;

import clases.*;
import arreglos.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgInternamientosPendientes extends JDialog {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgInternamientosPendientes dialog = new DlgInternamientosPendientes();
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
	public DlgInternamientosPendientes() {
		setResizable(false);
		setTitle("Reporte | Internamientos pendientes");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 675, 345);
		getContentPane().add(scrollPane);
	
		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		listar();
	}
	
	//  M�todos tipo void (sin par�metros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloInternamientos ai = new ArregloInternamientos();
		ArregloPacientes ap = new ArregloPacientes();
		ArregloCamas ac = new ArregloCamas();
		Internamiento i;
		Paciente p;
		Cama c;
		txtS.setText("");
		for (int j=0; j<ai.tamanio(); j++) {
			i = ai.obtener(j);
			if (i.getEstado() == 0) {
				p = ap.buscar(i.getCodigoPaciente());
				c = ac.buscar(i.getNumeroCama());
				imprimir("C�digo de internamiento :  " + i.getCodigoInternamiento());
				imprimir("C�digo de paciente      :  " + p.getCodigoPaciente());
				imprimir("Nombres                 :  " + p.getNombres());
				imprimir("Apellidos               :  " + p.getApellidos());
				imprimir("N�mero de cama          :  " + i.getNumeroCama());
				imprimir("Categor�a               :  " + Lib.categoriasCama[c.getCategoria()]);
				imprimir("Precio por d�a          :  S/ " + formato(c.getPrecioDia()));
				imprimir("Fecha de ingreso        :  " + i.getFechaIngreso()
				                                       + " - " + i.getHoraIngreso());
				imprimir();
			}
		}
	}
	//  M�todos tipo void (con par�metros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	//  M�todos que retornan valor (con par�metros)
	String formato(double real) {
		return String.format(Locale.US, "%-10.2f", real);
	}
	
}