package guis;

import libreria.Lib;

import clases.Cama;
import arreglos.ArregloCamas;
import clases.Vendedor;
import clases.Factura;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
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
import java.util.ArrayList;
import java.util.Arrays;
import libreria.DBUtils;

public class DIgFactura extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgCama;
        //
	private JLabel lblCodigoFactura;
	private JLabel lblCodigoProducto;
	private JLabel lblCodigoVendedor;
        private JLabel lblUnidades;
        private JLabel lblPrecio;
        //
	private JTextField txtCodigoFactura;
	private JTextField txtCodigoProducto;
	private JTextField txtCodigoVendedor;
        private JTextField txtUnidades;
        private JTextField txtPrecio;
        //
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblFacturas;
	private DefaultTableModel modelo;
        private ArrayList<Factura> lstFacturas = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DIgFactura dialog = new DIgFactura();
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
	public DIgFactura() {
            setResizable(false);
            setTitle("Mantenimiento | Facturas");
            setBounds(100, 100, 1600, 800);
            getContentPane().setLayout(null);

            lblImgCama = new JLabel();
            lblImgCama.setIcon(new ImageIcon("imagenes/dlgCama.png"));
            lblImgCama.setBounds(1225, 10, 89, 89);
            getContentPane().add(lblImgCama);

            definirInputsMantenimiento();
            definirBotones();
            definirColumnasGrilla();
            

            scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 140, 1575, 550);
            getContentPane().add(scrollPane);

            tblFacturas = new JTable();
            tblFacturas.addKeyListener(this);
            tblFacturas.addMouseListener(this);
            tblFacturas.setFillsViewportHeight(true);
            scrollPane.setViewportView(tblFacturas);           

            tblFacturas.setModel(modelo);
            ajustarAnchoColumnas();
            listar();
            editarFila();
            habilitarEntradas(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Inicializacion">
    private void definirInputsMantenimiento(){
        lblCodigoFactura = new JLabel("Codigo");
        lblCodigoFactura.setBounds(10, 10, 110, 23);
        getContentPane().add(lblCodigoFactura);

        lblCodigoProducto = new JLabel("Codigo del producto");
        lblCodigoProducto.setBounds(10, 40, 70, 23);
        getContentPane().add(lblCodigoProducto);

        lblCodigoVendedor = new JLabel("Codigo del vendedor");
        lblCodigoVendedor.setBounds(10, 70, 70, 23);
        getContentPane().add(lblCodigoVendedor);

        lblUnidades = new JLabel("Unidades");
        lblUnidades.setBounds(350, 40, 70, 23);
        getContentPane().add(lblUnidades);
        
        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(350, 70, 70, 23);
        getContentPane().add(lblPrecio);

        txtCodigoFactura = new JTextField();
        txtCodigoFactura.setBounds(90, 10, 200, 23);
        getContentPane().add(txtCodigoFactura);
        txtCodigoFactura.setColumns(10);
        txtCodigoFactura.setEditable(false);
        
        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(90, 40, 200, 23);
        getContentPane().add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);
        
        txtCodigoVendedor = new JTextField();
        txtCodigoVendedor.setBounds(90, 70, 200, 23);
        getContentPane().add(txtCodigoVendedor);
        txtCodigoVendedor.setColumns(10);
        
        txtUnidades = new JTextField();
        txtUnidades.setBounds(430, 40, 200, 23);
        getContentPane().add(txtUnidades);
        txtUnidades.setColumns(10);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(430, 70, 200, 23);
        getContentPane().add(txtPrecio);
        txtPrecio.setColumns(10);

        
    }
    
    private void definirColumnasGrilla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo Factura");
        modelo.addColumn("Codigo Producto");
        modelo.addColumn("Codigo Vendedor");
        modelo.addColumn("Unidades");
        modelo.addColumn("Precio");
    }
    
    private void definirBotones(){
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(this);
        btnAceptar.addMouseListener(this);
        btnAceptar.setForeground(Color.BLUE);
        btnAceptar.setBounds(700, 10, 150, 23);
        getContentPane().add(btnAceptar);
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        btnAdicionar.addMouseListener(this);
        btnAdicionar.setForeground(Color.BLUE);
        btnAdicionar.setBounds(1350, 10, 200, 23);
        getContentPane().add(btnAdicionar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.addMouseListener(this);
        btnModificar.setForeground(Color.BLUE);
        btnModificar.setBounds(1350, 40, 200, 23);
        getContentPane().add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.addMouseListener(this);
        btnEliminar.setForeground(Color.BLUE);
        btnEliminar.setBounds(1350, 70, 200, 23);
        getContentPane().add(btnEliminar);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Genericos">
    public int calcularCorrelativo(){
        return DBUtils.numeroCorrelativo(new ArrayList(lstFacturas) , "facturas");
    }
    void listar() {
        lstFacturas = DBUtils.parsearListaFacturas(DBUtils.cargarData("facturas"));
        int posFila = 0;
        if (modelo.getRowCount() > 0)
                posFila = tblVendedores.getSelectedRow();
        if (modelo.getRowCount() == lstFacturas.size()- 1)
                posFila = lstFacturas.size() - 1;
        if (posFila == lstFacturas.size())
                posFila --;
        modelo.setRowCount(0);
        Vendedor x;
        for (int i=0; i<lstFacturas.size(); i++) {
                x = lstFacturas.get(i);
                Object[] fila = { x.getCodigoFactura(), x.getCodigoProducto(), x.CodigoVendedor(), x.getUnidades(), x.getPrecio()};
                modelo.addRow(fila);
        }
        if (lstFacturas.size() > 0)
                tblFacturas.getSelectionModel().setSelectionInterval(posFila, posFila);
    }
    void guardar() {
            DBUtils.grabarData(new ArrayList(lstFacturas), "facturas");
    }
    //</editor-fold>
    
    void editarFila() {
        if (lstFacturas.size() == 0)
                limpieza();
        else {
                /*Factura factura = lstFacturas.get(tblFacturas.getSelectedRow());
                txtCodigoFactura.setText(String.valueOf(factura.getCodigoFactura()));
                txtCodigoProducto.setText(String.valueOf(factura.getProducto()));
                txtCodigoVendedor.setText(String.valueOf(factura.getVendedor()));
                txtUnidades.setText(String.valueOf(factura.getUnidades()));
                txtPrecio.setText(String.valueOf(factura.getPrecio()));*/
        }
    }
    void limpieza() {
            txtCodigoFactura.setText(String.valueOf(calcularCorrelativo()));
    }
    
    public void actionPerformed(ActionEvent arg) {
            /*if (arg.getSource() == cboCategoria) {
                    actionPerformedCboCategoria(arg);
            }*/
            if (arg.getSource() == btnAceptar) {
                    actionPerformedBtnAceptar(arg);
            }
            if (arg.getSource() == btnEliminar) {
                    actionPerformedBtnEliminar(arg);
            }
            if (arg.getSource() == btnModificar) {
                    actionPerformedBtnModificar(arg);
            }
            if (arg.getSource() == btnAdicionar) {
                    actionPerformedBtnAdicionar(arg);
            }
    }
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		btnAdicionar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(true);
		limpieza();
		habilitarEntradas(true);
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (lstFacturas.size()== 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existes");	
		}
		else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (lstFacturas.size()== 0)
			mensaje("No existes");
		else {
			editarFila();
			habilitarEntradas(false);
                        Factura facturaEliminar = new Factura();
                        for (Factura factura : lstFacturas) {
                            if(factura.getCodigoFactura() == Integer.parseInt(txtCodigoFactura.getText().trim()))
                                facturaEliminar = factura;
                        }
                        
                        lstFacturas.remove(facturaEliminar);
			guardar();
                        listar();
                        editarFila();
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
                
                int codigoFactura = Integer.parseInt(txtCodigoFactura.getText().trim());
                int codigoProducto = Integer.parseInt(txtCodigoProducto.getText().trim());
                int codigoVendedor = Integer.parseInt(txtCodigoVendedor.getText().trim());
                String unidades = txtUnidades.getText().trim();
                String precio = txtPrecio.getText().trim();

		
		if (btnAdicionar.isEnabled() == false) {
			Factura factura = new Factura(codigoFactura, 
                                codigoProducto, 
                                codigoVendedor, 
                                unidades,
                                precio);
                        lstFacturas.add(factura);
			guardar();
			btnAdicionar.setEnabled(true);
		}
		if (btnModificar.isEnabled() == false) {
                        Factura facturaModificar = new Factura();
                        for (Factura factura : lstFactura) {
                            if(factura.getCodigoFactura() == Integer.parseInt(txtCodigoFactura.getText().trim()))
                                facturaModificar = factura;
                        }
                        facturaModificar.setCodigoProducto(CodigoProducto);
                        facturaModificar.setCodigoVendedor(CodigoVendedor);
                        facturaModificar.setUnidades(unidades);
                        facturaModificar.setPrecio(precio);
			guardar();
			btnModificar.setEnabled(true);
		}
		listar();
		habilitarEntradas(false);
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
		if (arg0.getSource() == tblVendedores) {
			mouseClickedTblCama(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			mouseEnteredBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			mouseEnteredBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			mouseEnteredBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			mouseEnteredBtnAdicionar(arg0);
		}
		if (arg0.getSource() == tblVendedores) {
			mouseEnteredTblVendedores(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblCama(MouseEvent arg0) {
		habilitarEntradas(false);
		habilitarBotones(true);
		editarFila();
	}
	protected void mouseEnteredTblVendedores(MouseEvent arg0) {
		tblVendedores.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAdicionar(MouseEvent arg0) {
		btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnModificar(MouseEvent arg0) {
		btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnEliminar(MouseEvent arg0) {
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAceptar(MouseEvent arg0) {
		btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  M�todos tipo void (sin par�metros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblFacturas.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25)); 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(25));  
                tcm.getColumn(4).setPreferredWidth(anchoColumna(25));
                tcm.getColumn(5).setPreferredWidth(anchoColumna(25));
	}
	
	//  M�todos tipo void (con par�metros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informacion", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	void habilitarEntradas(boolean flag) {
		btnAceptar.setEnabled(flag);
	}
	void habilitarBotones(boolean flag) {
		btnAdicionar.setEnabled(flag);
		btnModificar.setEnabled(flag);
	}
	
	//  M�todos que retornan valor (con par�metros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
}