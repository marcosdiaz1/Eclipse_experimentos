package guis;

import libreria.Lib;

import clases.Cliente;

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

public class DlgCliente extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgCama;
        //
	private JLabel lblCodigoCliente;
	private JLabel lblNombres;
	private JLabel lblApellidos;
        private JLabel lblTelefono;
        private JLabel lblDni;
        //
	private JTextField txtCodigoCliente;
	private JTextField txtNombres;
        private JTextField txtApellidos;
        private JTextField txtTelefono;
        private JTextField txtDni;
        //
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblClientes;
	private DefaultTableModel modelo;
        private ArrayList<Cliente> lstClientes = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgCliente dialog = new DlgCliente();
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
	public DlgCliente() {
            setResizable(false);
            setTitle("Mantenimiento | Clientes");
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

            tblClientes = new JTable();
            tblClientes.addKeyListener(this);
            tblClientes.addMouseListener(this);
            tblClientes.setFillsViewportHeight(true);
            scrollPane.setViewportView(tblClientes);           

            tblClientes.setModel(modelo);
            ajustarAnchoColumnas();
            listar();
            editarFila();
            habilitarEntradas(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Inicializacion">
    private void definirInputsMantenimiento(){
        lblCodigoCliente = new JLabel("Codigo");
        lblCodigoCliente.setBounds(10, 10, 110, 23);
        getContentPane().add(lblCodigoCliente);

        lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(10, 70, 70, 23);
        getContentPane().add(lblNombres);

        lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(350, 10, 70, 23);
        getContentPane().add(lblApellidos);
        
        lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(350, 40, 70, 23);
        getContentPane().add(lblTelefono);
        
        lblDni = new JLabel("Dni");
        lblDni.setBounds(350, 70, 70, 23);
        getContentPane().add(lblDni);

        txtCodigoCliente = new JTextField();
        txtCodigoCliente.setBounds(90, 10, 200, 23);
        getContentPane().add(txtCodigoCliente);
        txtCodigoCliente.setColumns(10);
        txtCodigoCliente.setEditable(false);
        
        txtNombres = new JTextField();
        txtNombres.setBounds(90, 70, 200, 23);
        getContentPane().add(txtNombres);
        txtNombres.setColumns(10);
        

        txtApellidos = new JTextField();
        txtApellidos.setBounds(430, 10, 200, 23);
        getContentPane().add(txtApellidos);
        txtApellidos.setColumns(10);
        
        txtTelefono = new JTextField();
        txtTelefono.setBounds(430, 40, 200, 23);
        getContentPane().add(txtTelefono);
        txtTelefono.setColumns(10);
        
        txtDni = new JTextField();
        txtDni.setBounds(430, 70, 200, 23);
        getContentPane().add(txtDni);
        txtDni.setColumns(10);

        
    }
    
    private void definirColumnasGrilla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Telefonos");
        modelo.addColumn("Dni");
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
        return DBUtils.numeroCorrelativo(new ArrayList<>(lstClientes) , "clientes");
    }
    void listar() {
        lstClientes = DBUtils.parsearListaCliente(DBUtils.cargarData("clientes"));
        int posFila = 0;
        if (modelo.getRowCount() > 0)
                posFila = tblClientes.getSelectedRow();
        if (modelo.getRowCount() == lstClientes.size()- 1)
                posFila = lstClientes.size() - 1;
        if (posFila == lstClientes.size())
                posFila --;
        modelo.setRowCount(0);
        Cliente x;
        for (int i=0; i<lstClientes.size(); i++) {
                x = lstClientes.get(i);
                Object[] fila = { x.getCodigoCliente(), x.getNombres(),x.getApellidos(), x.getTelefono(), x.getDni()};
                modelo.addRow(fila);
        }
        if (lstClientes.size() > 0)
                tblClientes.getSelectionModel().setSelectionInterval(posFila, posFila);
    }
    void guardar() {
            DBUtils.grabarData(new ArrayList<>(lstClientes), "clientes");
    }
    //</editor-fold>
    
    void editarFila() {
        if (lstClientes.size() == 0)
                limpieza();
        else {
                Cliente cliente = lstClientes.get(tblClientes.getSelectedRow());
                txtCodigoCliente.setText(String.valueOf(cliente.getCodigoCliente()));
                txtNombres.setText(cliente.getNombres());
                txtApellidos.setText(cliente.getApellidos());
                txtTelefono.setText(cliente.getTelefono());
                txtDni.setText(cliente.getDni());
        }
    }
    void limpieza() {
            txtCodigoCliente.setText(String.valueOf(calcularCorrelativo()));
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
		if (lstClientes.size()== 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existe");	
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
		if (lstClientes.size()== 0)
			mensaje("No existe");
		else {
			editarFila();
			habilitarEntradas(false);
                        Cliente clienteEliminar = new Cliente();
                        for (Cliente cliente : lstClientes) {
                            if(cliente.getCodigoCliente() == Integer.parseInt(txtCodigoCliente.getText().trim()))
                                clienteEliminar = cliente;
                        }
                        
                        lstClientes.remove(clienteEliminar);
			guardar();
                        listar();
                        editarFila();
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
                
                int CodigoCliente = Integer.parseInt(txtCodigoCliente.getText().trim());
                String nombres = txtNombres.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String dni = txtDni.getText().trim();

		
		if (btnAdicionar.isEnabled() == false) {
			Cliente cliente = new Cliente(CodigoCliente,
                                nombres, 
                                apellidos,
                                telefono,
                                dni);
                        lstClientes.add(cliente);
			guardar();
			btnAdicionar.setEnabled(true);
		}
		if (btnModificar.isEnabled() == false) {
                        Cliente clienteModificar = new Cliente();
                        for (Cliente cliente : lstClientes) {
                            if(cliente.getCodigoCliente() == Integer.parseInt(txtCodigoCliente.getText().trim()))
                                clienteModificar = cliente;
                        }
                        clienteModificar.setNombres(nombres);
                        clienteModificar.setApellidos(apellidos);
                        clienteModificar.setTelefono(telefono);
                        clienteModificar.setDni(dni);
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
		if (arg0.getSource() == tblClientes) {
			mouseEnteredTblClientes(arg0);
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
		if (arg0.getSource() == tblClientes) {
			mouseEnteredTblClientes(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseEnteredTblClientes(MouseEvent arg0) {
		habilitarEntradas(false);
		habilitarBotones(true);
		editarFila();
	}
	protected void mouseEnteredTblVendedores(MouseEvent arg0) {
		tblClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		TableColumnModel tcm = tblClientes.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25)); 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(25));  
                tcm.getColumn(4).setPreferredWidth(anchoColumna(25));
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