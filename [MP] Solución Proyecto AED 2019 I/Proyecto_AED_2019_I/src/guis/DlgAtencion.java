package guis;

import libreria.*;

import clases.*;
import arreglos.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DlgAtencion extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgAtencion;
	private JLabel lblCodigoAtencion;
	private JLabel lblCodigoPaciente;
	private JLabel lblCodigoMedicina;
	private JTextField txtCodigoAtencion;
	private JTextField txtCodigoPaciente;
	private JComboBox <String> cboCodigoPaciente;
	private JComboBox <String> cboCodMedicina;
	private JComboBox <String> cboCodigoMedicina;
	private JScrollPane scrollPane;
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton btnAtender;
	private JButton btnProceder;
	private JTable tblConsulta;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtencion dialog = new DlgAtencion();
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
	public DlgAtencion() {
		setResizable(false);
		setTitle("Registro | Atenci�n");
		setBounds(100, 100, 700, 400);
		getContentPane().setLayout(null);
	
		lblImgAtencion = new JLabel();
		lblImgAtencion.setIcon(new ImageIcon("imagenes/dlgAtencion.png"));
		lblImgAtencion.setBounds(400, 10, 89, 89);
		getContentPane().add(lblImgAtencion);
		
		lblCodigoAtencion = new JLabel("C\u00F3digo");
		lblCodigoAtencion.setBounds(10, 10, 60, 23);
		getContentPane().add(lblCodigoAtencion);
		
		lblCodigoPaciente = new JLabel("Paciente");
		lblCodigoPaciente.addMouseListener(this);
		lblCodigoPaciente.setForeground(Color.BLUE);
		lblCodigoPaciente.setBounds(10, 40, 60, 23);
		getContentPane().add(lblCodigoPaciente);
	
		lblCodigoMedicina = new JLabel("Medicina");
		lblCodigoMedicina.addMouseListener(this);
		lblCodigoMedicina.setForeground(Color.BLUE);
		lblCodigoMedicina.setBounds(10, 70, 60, 23);
		getContentPane().add(lblCodigoMedicina);
		
		txtCodigoAtencion = new JTextField();
		txtCodigoAtencion.setBounds(70, 10, 85, 23);
		getContentPane().add(txtCodigoAtencion);
		txtCodigoAtencion.setColumns(10);
		
		txtCodigoPaciente = new JTextField();
		txtCodigoPaciente.setBounds(70, 40, 85, 23);
		getContentPane().add(txtCodigoPaciente);
		txtCodigoPaciente.setColumns(10);
		
		cboCodigoPaciente = new JComboBox <String> ();
		cboCodigoPaciente.addActionListener(this);
		cboCodigoPaciente.addMouseListener(this);
		cboCodigoPaciente.setBounds(190, 40, 100, 23);
		getContentPane().add(cboCodigoPaciente);
		colocarCodigosPacientes();

		cboCodMedicina = new JComboBox <String> ();
		cboCodMedicina.addMouseListener(this);
		cboCodMedicina.setBounds(70, 70, 85, 23);
		getContentPane().add(cboCodMedicina);
		
		cboCodigoMedicina = new JComboBox <String> ();
		cboCodigoMedicina.addMouseListener(this);
		cboCodigoMedicina.setBounds(190, 70, 100, 23);
		getContentPane().add(cboCodigoMedicina);
		
		btnQuitar = new JButton(new ImageIcon("imagenes/quitar.gif"));
		btnQuitar.addActionListener(this);
		btnQuitar.addMouseListener(this);
		btnQuitar.setBounds(161, 70, 23, 23);
		getContentPane().add(btnQuitar);
	
		btnAgregar = new JButton(new ImageIcon("imagenes/agregar.gif"));
		btnAgregar.addActionListener(this);
		btnAgregar.addMouseListener(this);
		btnAgregar.setBounds(296, 70, 23, 23);
		getContentPane().add(btnAgregar);
		
		btnAtender = new JButton("Atender");
		btnAtender.addActionListener(this);
		btnAtender.addMouseListener(this);
		btnAtender.setForeground(Color.BLUE);
		btnAtender.setBounds(535, 10, 150, 23);
		getContentPane().add(btnAtender);
		
		btnProceder = new JButton("Proceder");
		btnProceder.addActionListener(this);
		btnProceder.addMouseListener(this);
		btnProceder.setForeground(Color.BLUE);
		btnProceder.setBounds(189, 10, 102, 23);
		getContentPane().add(btnProceder);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 675, 250);
		getContentPane().add(scrollPane);

		tblConsulta = new JTable();
		tblConsulta.addKeyListener(this);
		tblConsulta.addMouseListener(this);
		tblConsulta.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblConsulta);

		modelo = new DefaultTableModel();
		modelo.addColumn("C�DIGO");
		modelo.addColumn("PACIENTE");
		modelo.addColumn("FECHA CONSULTA");
		modelo.addColumn("HORA CONSULTA");
		modelo.addColumn("A PAGAR");
		modelo.addColumn("ESTADO");
		
		tblConsulta.setModel(modelo);
		
		txtCodigoAtencion.setEditable(false);
		txtCodigoPaciente.setEditable(false);
		btnProceder.setEnabled(false);
		if (aa.tamanio() == 0)
			visibleInvisible(false);
		
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	
	//  Declaraci�n global
	ArregloAtenciones aa = new ArregloAtenciones();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboCodigoPaciente) {
			actionPerformedCboCodigoPaciente(arg0);
		}
		if (arg0.getSource() == btnProceder) {
			actionPerformedBtnProceder(arg0);
		}
		if (arg0.getSource() == btnAtender) {
			actionPerformedBtnAtender(arg0);
		}
		if (arg0.getSource() == btnQuitar) {
			actionPerformedBtnQuitar(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
	}	
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		Atencion a = aa.buscar(leerCodigoAtencion());
		if (a.getEstado() == 0)
			try {
				int codigoMedicina = leerCodigoMedicina();
				int ok = confirmar(obtenerDatosMedicina(codigoMedicina), "� Recetar ?");
				if (ok == 0)
					try {
						int cantidad = Integer.parseInt(confirmarIngreso("Cantidad"));
						ArregloMedicinas am = new ArregloMedicinas();
						Medicina m = am.buscar(codigoMedicina);
						if (cantidad <= m.getStock()) {
							ArregloRecetas ar = new ArregloRecetas(leerNumeroReceta());
							ar.adicionar(new Receta(codigoMedicina, cantidad, m.getPrecioUnitario()));
							ar.grabarReceta();
							m.setStock(m.getStock() - cantidad);
							am.grabarMedicinas();
							a.setApagar(redondear(a.getApagar() + cantidad*m.getPrecioUnitario()));
							aa.grabarAtenciones();
							cboCodMedicina.addItem(cboCodigoMedicina.getSelectedItem().toString());
							cboCodigoMedicina.removeItem(cboCodigoMedicina.getSelectedItem());
							listar();
						}
						else
							mensaje("S�lo hay " + m.getStock() + " unidades en stock");
					}
					catch (Exception e) {
						mensaje("Ingrese CANTIDAD correcta");
					}
			}
			catch (Exception e) {
				mensaje("La Receta del paciente tiene todas las medicinas");
			}
		else
			mensaje("No se puede agregar medicinas porque la Atenci�n est� Pagada");
	}
	protected void actionPerformedBtnQuitar(ActionEvent arg0) {
		Atencion a = aa.buscar(leerCodigoAtencion());
		if (a.getEstado() == 0)
			try {
				int codMedicina = leerCodMedicina();
				int ok = confirmar("� Quitar medicina de la Receta ?");
				if (ok == 0) {
					ArregloRecetas ar = new ArregloRecetas(leerNumeroReceta());
					Receta r = ar.buscar(codMedicina);
					ar.eliminar(r);
					ar.grabarReceta();
					ArregloMedicinas am = new ArregloMedicinas();
					Medicina m = am.buscar(codMedicina);
					m.setStock(m.getStock() + r.getCantidad());
					am.grabarMedicinas();
					a.setApagar(redondear(a.getApagar() - r.getCantidad()*r.getPrecioUnitario()));
					aa.grabarAtenciones();
					cboCodigoMedicina.addItem(cboCodMedicina.getSelectedItem().toString());
					cboCodMedicina.removeItem(cboCodMedicina.getSelectedItem());
					listar();
				}
			}
			catch (Exception e) {
				mensaje("El paciente no cuenta con Receta");
			}
		else
			mensaje("No se puede quitar medicinas porque la Atenci�n est� Pagada");
	}
	protected void actionPerformedBtnAtender(ActionEvent arg0) {
		if (cboCodigoPaciente.getSelectedIndex() < 0)
			mensaje("Todos los pacientes est�n en Atenci�n");
		else {
			visibleInvisible(false);
			btnAtender.setEnabled(false);
			btnProceder.setEnabled(true);
			txtCodigoAtencion.setText("" + aa.codigoCorrelativo());
			txtCodigoPaciente.setText("" + cboCodigoPaciente.getSelectedItem());
		}
	}
	protected void actionPerformedBtnProceder(ActionEvent arg0) {
		int codigoAtencion = leerCodigoAtencion();
		int codigoPaciente = leerCodigoPaciente();
		int ok = confirmar(obtenerDatosPaciente(), "� Registrar Atenci�n ?");
		if (ok == 0) {
			Atencion nueva = new Atencion(codigoAtencion, codigoPaciente, fechaActual(), horaActual(), 100.0, 0);
			aa.adicionar(nueva);
			aa.grabarAtenciones();
			cboCodigoPaciente.removeItem(cboCodigoPaciente.getSelectedItem());
		}
		visibleInvisible(true);
		btnAtender.setEnabled(true);
		btnProceder.setEnabled(false);
		listar();
		editarFila();				
	}
	protected void actionPerformedCboCodigoPaciente(ActionEvent arg0) {
		txtCodigoPaciente.setText("" + cboCodigoPaciente.getSelectedItem());
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		arg0.consume();
		editarFila();		
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == lblCodigoMedicina) {
			mouseClickedLblCodigoMedicina(arg0);
		}
		if (arg0.getSource() == lblCodigoPaciente) {
			mouseClickedLblCodigoPaciente(arg0);
		}
		if (arg0.getSource() == tblConsulta) {
			mouseClickedTblConsulta(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == cboCodMedicina) {
			mouseEnteredCodMedicina(arg0);
		}
		if (arg0.getSource() == cboCodigoMedicina) {
			mouseEnteredCodigoMedicina(arg0);
		}	
		if (arg0.getSource() == cboCodigoPaciente) {
			mouseEnteredCboCodigoPaciente(arg0);
		}	
		if (arg0.getSource() == btnProceder) {
			mouseEnteredBtnProceder(arg0);
		}
		if (arg0.getSource() == btnAtender) {
			mouseEnteredBtnAtender(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			mouseEnteredBtnAgregar(arg0);
		}
		if (arg0.getSource() == btnQuitar) {
			mouseEnteredBtnQuitar(arg0);
		}
		if (arg0.getSource() == lblCodigoMedicina) {
			mouseEnteredLblCodigoMedicina(arg0);
		}
		if (arg0.getSource() == lblCodigoPaciente) {
			mouseEnteredLblCodigoPaciente(arg0);
		}	
		if (arg0.getSource() == tblConsulta) {
			mouseEnteredTblConsulta(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblConsulta(MouseEvent arg0) {
		if (btnProceder.isEnabled())
			visibleInvisible(true);
		btnProceder.setEnabled(false);
		btnAtender.setEnabled(true);
		editarFila();
	}
	protected void mouseClickedLblCodigoPaciente(MouseEvent arg0) {
		mensaje(obtenerDatosPaciente());
	}
	protected void mouseClickedLblCodigoMedicina(MouseEvent arg0) {
		if (cboCodMedicina.getSelectedIndex() < 0)
			mensaje("El paciente no cuenta con Receta");
		else
			mensaje(obtenerDatosReceta(leerCodMedicina()));
	}
	protected void mouseEnteredTblConsulta(MouseEvent arg0) {
		tblConsulta.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredLblCodigoPaciente(MouseEvent arg0) {
		lblCodigoPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredLblCodigoMedicina(MouseEvent arg0) {
		lblCodigoMedicina.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnQuitar(MouseEvent arg0) {
		btnQuitar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAgregar(MouseEvent arg0) {
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAtender(MouseEvent arg0) {
		btnAtender.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnProceder(MouseEvent arg0) {
		btnProceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredCboCodigoPaciente(MouseEvent arg0) {
		cboCodigoPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}	
	protected void mouseEnteredCodigoMedicina(MouseEvent arg0) {
		cboCodigoMedicina.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredCodMedicina(MouseEvent arg0) {
		cboCodMedicina.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  M�todos tipo void (sin par�metros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblConsulta.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(16));  // codigoAtencion
		tcm.getColumn(1).setPreferredWidth(anchoColumna(16));  // codigoPaciente
		tcm.getColumn(2).setPreferredWidth(anchoColumna(20));  // fechaAtencion
		tcm.getColumn(3).setPreferredWidth(anchoColumna(16));  // horaAtencion
		tcm.getColumn(4).setPreferredWidth(anchoColumna(16));  // totalPagar
		tcm.getColumn(5).setPreferredWidth(anchoColumna(16));  // estado
	}
	void colocarCodigosPacientes() {
		ArregloPacientes ap = new ArregloPacientes();
		Paciente p;
		for (int i=0; i<ap.tamanio(); i++) {
			p = ap.obtener(i);
			if (aa.procedeCodigoPacientes(p.getCodigoPaciente()))
				cboCodigoPaciente.addItem("" + p.getCodigoPaciente());
		}
	}
	void distribuirCodigosMedicinas() {
		ArregloRecetas ar = new ArregloRecetas(leerNumeroReceta());
		Receta r;
		cboCodMedicina.removeAllItems();
		for (int i=0; i<ar.tamanio(); i++) {
			r = ar.obtener(i);
			cboCodMedicina.addItem("" + r.getCodigoMedicina());
		}
		ArregloMedicinas am = new ArregloMedicinas();
		Medicina m;
		cboCodigoMedicina.removeAllItems();
		for (int i=0; i<am.tamanio(); i++) {
			m = am.obtener(i);
			if (ar.buscar(m.getCodigoMedicina()) == null)
				cboCodigoMedicina.addItem("" + m.getCodigoMedicina());
		}	
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblConsulta.getSelectedRow();
		if (modelo.getRowCount() == aa.tamanio() - 1)
			posFila = aa.tamanio() - 1;
		if (posFila == aa.tamanio())
			posFila --;
		modelo.setRowCount(0);
		Atencion a;
		for (int i=0; i<aa.tamanio(); i++) {
			a = aa.obtener(i);
			Object[] fila = { a.getCodigoAtencion(),
							  a.getCodigoPaciente(),
							  Fecha.enTextoFecha(a.getFechaAtencion()),
							  a.getHoraAtencion(),
							  a.getApagar(),
							  Lib.estadosConsulta[a.getEstado()] };
			modelo.addRow(fila);
		}
		if (aa.tamanio() > 0)
			tblConsulta.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (aa.tamanio() == 0)
			txtCodigoAtencion.setText("" + aa.codigoCorrelativo());
		else {
			Atencion a = aa.obtener(tblConsulta.getSelectedRow());
			txtCodigoAtencion.setText("" + a.getCodigoAtencion());
			txtCodigoPaciente.setText("" + a.getCodigoPaciente());
			distribuirCodigosMedicinas();
		}
	}
	//  M�todos tipo void (con par�metros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informaci�n", 1);
	}
	void mensaje(String s1, String s2) {
		JOptionPane.showMessageDialog(this, s1, s2, 1);
	}
	void visibleInvisible(boolean sino) {
		lblCodigoMedicina.setVisible(sino);
		cboCodMedicina.setVisible(sino);
		btnQuitar.setVisible(sino);
		cboCodigoMedicina.setVisible(sino);
		btnAgregar.setVisible(sino);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  M�todos que retornan valor (sin par�metros)
	String obtenerDatosPaciente() {
		ArregloPacientes ap = new ArregloPacientes();
	    Paciente p = ap.buscar(leerCodigoPaciente());
	    return "Nombres :  " + p.getNombres() + "\n" +
		       "Apellidos :  " + p.getApellidos();
	}
	int leerCodigoAtencion() {
		return Integer.parseInt(txtCodigoAtencion.getText().trim());
	}
	int leerCodigoPaciente() {
		return Integer.parseInt(txtCodigoPaciente.getText().trim());
	}
	int leerCodMedicina() {
		return Integer.parseInt(cboCodMedicina.getSelectedItem().toString());
	}
	int leerCodigoMedicina() {
		return Integer.parseInt(cboCodigoMedicina.getSelectedItem().toString());
	}
	String leerNumeroReceta() {
		return "" + leerCodigoAtencion();
	}
	String fechaActual() {
		int dd, mm, aa;
		Calendar c = new GregorianCalendar();
		dd = c.get(Calendar.DAY_OF_MONTH);
		mm = c.get(Calendar.MONTH) + 1;
		aa = c.get(Calendar.YEAR);
		return ajustar(dd) + "/" + ajustar(mm) + "/" + aa;
	}
	String horaActual() {
		int hh, mm, ss;
		Calendar c = new GregorianCalendar();
		hh = c.get(Calendar.HOUR_OF_DAY);
		mm = c.get(Calendar.MINUTE);
		ss = c.get(Calendar.SECOND);
		return ajustar(hh) + ":" + ajustar(mm) + ":" + ajustar(ss);
	}
	//  M�todos que retornan valor (con par�metros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	int confirmar(String s1, String s2) {
		return JOptionPane.showConfirmDialog(this, s1, s2, 0, 1, null);
	}
	String confirmarIngreso(String s) {
		return JOptionPane.showInputDialog(this, "", s, 3);
	}
	String ajustar(int numero) {
		return String.format("%02d", numero);
	}
	String obtenerDatosMedicina(int codigoMedicina) {
		ArregloMedicinas am = new ArregloMedicinas();
	    Medicina m = am.buscar(codigoMedicina);
	    return "Nombre :  " + m.getNombre() + "\n" +
		       "Precio unitario :  S/ " + m.getPrecioUnitario();
	}
	String obtenerDatosReceta(int codigoMedicina) {
		ArregloMedicinas am = new ArregloMedicinas();
	    Medicina m = am.buscar(codigoMedicina);
	    ArregloRecetas ar = new ArregloRecetas(leerNumeroReceta());
	    Receta r = ar.buscar(codigoMedicina);
	    return "Nombre :  " + m.getNombre() + "\n" +
	    	   "Cantidad :  " + r.getCantidad() + "\n" +
	           "Precio unitario :  S/ " + r.getPrecioUnitario();
	}
	double redondear(double real) {
		return Math.round(real * 100) / 100.0;
	}
	
}