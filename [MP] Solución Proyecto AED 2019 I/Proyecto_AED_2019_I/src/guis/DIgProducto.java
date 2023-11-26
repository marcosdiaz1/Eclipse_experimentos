package guis;

import libreria.Lib;

import clases.Producto;

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

public class DIgProducto extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgCama;
        //
	private JLabel lblCodigoProducto;
	private JLabel lblDescripcion;
	private JLabel lblUnidades;
	private JLabel lblPrecio;
        //
	private JTextField txtCodigoProducto;
	private JTextField txtDescripcion;
	private JTextField txtUnidades;
	private JTextField txtPrecio;
        //
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblProductos;
	private DefaultTableModel modelo;
        private ArrayList<Producto> lstProductos = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DIgProducto dialog = new DIgProducto();
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
	public DIgProducto() {
            setResizable(false);
            setTitle("Mantenimiento | Productos");
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

            tblProductos = new JTable();
            tblProductos.addKeyListener(this);
            tblProductos.addMouseListener(this);
            tblProductos.setFillsViewportHeight(true);
            scrollPane.setViewportView(tblProductos);           

            tblProductos.setModel(modelo);
            ajustarAnchoColumnas();
            listar();
            editarFila();
            habilitarEntradas(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Inicializacion">
    private void definirInputsMantenimiento(){
        lblCodigoProducto = new JLabel("Codigo");
        lblCodigoProducto.setBounds(10, 10, 110, 23);
        getContentPane().add(lblCodigoProducto);

        lblCodigoProducto = new JLabel("Categoria");
        lblCodigoProducto.setBounds(10, 40, 70, 23);
        getContentPane().add(lblCodigoProducto);

        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setBounds(350, 10, 70, 23);
        getContentPane().add(lblDescripcion);
        
        lblUnidades= new JLabel("Unidades");
        lblUnidades.setBounds(300, 60, 90, 23);
        getContentPane().add(lblDescripcion);
        
        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(350, 70, 70, 23);
        getContentPane().add(lblPrecio);

        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(90, 10, 200, 23);
        getContentPane().add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);
        txtCodigoProducto.setEditable(false);
        
        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(90, 40, 200, 23);
        getContentPane().add(txtDescripcion);
        txtDescripcion.setColumns(10);
        
        txtUnidades = new JTextField();
        txtUnidades.setBounds(380, 70, 100, 23);
        getContentPane().add(txtUnidades);
        txtUnidades.setColumns(10);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(430, 70, 200, 23);
        getContentPane().add(txtPrecio);
        txtPrecio.setColumns(10);
        
    }
    
    private void definirColumnasGrilla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("CodigoProducto");
        modelo.addColumn("Descripcion");
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
        return DBUtils.numeroCorrelativo(new ArrayList<>(lstProductos) , "Producto");
    }
    void listar() {
        lstProductos = DBUtils.parsearListaProducto(DBUtils.cargarData("productos"));
        int posFila = 0;
        if (modelo.getRowCount() > 0)
                posFila = tblProductos.getSelectedRow();
        if (modelo.getRowCount() == lstProductos.size()- 1)
                posFila = lstProductos.size() - 1;
        if (posFila == lstProductos.size())
                posFila --;
        modelo.setRowCount(0);
        Producto x;
        for (int i=0; i<lstProductos.size(); i++) {
                x = lstProductos.get(i);
                Object[] fila = { x.getCodigoProducto(), x.getDescripcion(), x.getUnidades(), x.getPrecio()};
                modelo.addRow(fila);
        }
        if (lstProductos.size() > 0)
                tblProductos.getSelectionModel().setSelectionInterval(posFila, posFila);
    }
    void guardar() {
            DBUtils.grabarData(new ArrayList<>(lstProductos), "producto");
    }
    //</editor-fold>
    
    void editarFila() {
        if (lstProductos.size() == 0)
                limpieza();
        else {
        	int selectedRow = tblProductos.getSelectedRow();
        	
        	if(selectedRow!=-1 && selectedRow < lstProductos.size()) {
                Producto producto = lstProductos.get(selectedRow);
                txtCodigoProducto.setText(String.valueOf(producto.getCodigoProducto()));
                txtDescripcion.setText(producto.getDescripcion());
                txtUnidades.setText(producto.getUnidades());
                txtPrecio.setText(String.valueOf(producto.getPrecio()));
        } else {
        	limpieza();
        }
        }
        
    }
    void limpieza() {
            txtCodigoProducto.setText(String.valueOf(calcularCorrelativo()));
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
		if (lstProductos.size()== 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen");	
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
		if (lstProductos.size()== 0)
			mensaje("No existes");
		else {
			editarFila();
			habilitarEntradas(false);
                        Producto productoEliminar = new Producto();
                        for (Producto producto : lstProductos) {
                            if(producto.getCodigoProducto() == Integer.parseInt(txtCodigoProducto.getText().trim()))
                                productoEliminar = producto;
                        }
                        
                        lstProductos.remove(productoEliminar);
			guardar();
                        listar();
                        editarFila();
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
                
                int codigoProducto = Integer.parseInt(txtCodigoProducto.getText().trim());
                int descripcion = Integer.parseInt(txtDescripcion.getText().trim());
                int unidades = Integer.parseInt(txtUnidades.getText().trim());
                double precio = Double.parseDouble(txtPrecio.getText().trim());

		
		if (btnAdicionar.isEnabled() == false) {
			Producto producto = new Producto(codigoProducto, descripcion, unidades, precio);
                        lstProductos.add(producto);
			guardar();
			btnAdicionar.setEnabled(true);
		}
		if (btnModificar.isEnabled() == false) {
                        Producto productoModificar = new Producto();
                        for (Producto producto : lstProductos) {
                            if(producto.getCodigoProducto() == Integer.parseInt(txtCodigoProducto.getText().trim()))
                                productoModificar = producto;
                            break;
                        }
                        productoModificar.setUnidades(Integer.parseInt(unidades));
                        productoModificar.setDescripcion(String(descripcion));
                        productoModificar.setPrecio(Double.parseDouble(precio));
                        
			guardar();
			btnModificar.setEnabled(true);
		}
		listar();
		habilitarEntradas(false);
	}
        
	private String String(int descripcion) {
		// TODO Auto-generated method stub
		return null;
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
		if (arg0.getSource() == tblProductos) {
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
		if (arg0.getSource() == tblProductos) {
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
		tblProductos.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		TableColumnModel tcm = tblProductos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25));
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