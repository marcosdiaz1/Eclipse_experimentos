package guis;

import clases.*;
import arreglos.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgAtencionesPagadas extends JDialog {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtencionesPagadas dialog = new DlgAtencionesPagadas();
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
	public DlgAtencionesPagadas() {
		setResizable(false);
		setTitle("Reporte | Atenciones pagadas");
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
		ArregloAtenciones aa = new ArregloAtenciones();
		ArregloPacientes ap = new ArregloPacientes();
		Atencion a;
		Paciente p;
		txtS.setText("");
		for (int i=0; i<aa.tamanio(); i++) {
			a = aa.obtener(i);
			if (a.getEstado() == 1) {
				p = ap.buscar(a.getCodigoPaciente());
				imprimir("C�digo de atenci�n :  " + a.getCodigoAtencion());
				imprimir("C�digo de paciente :  " + a.getCodigoPaciente());
				imprimir("Nombres            :  " + p.getNombres());
				imprimir("Apellidos          :  " + p.getApellidos());
				imprimir("Fecha de atenci�n  :  " + a.getFechaAtencion()
				                                  + " - " + a.getHoraAtencion());
				imprimir("Total pagado       :  S/ " + formato(a.getApagar()));
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