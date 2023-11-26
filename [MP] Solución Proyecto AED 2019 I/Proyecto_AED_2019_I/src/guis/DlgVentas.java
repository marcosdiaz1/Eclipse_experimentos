package guis;

import clases.Cliente;
import clases.Factura;
import clases.Producto;
import libreria.Lib;

import clases.Vendedor;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletionService;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import libreria.Autocomplete;
import libreria.DBUtils;

public class DlgVentas extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgCama;
        //
        private JLabel lblCabeceraTitulo;
        private JLabel lblCabeceraNroSerie;
        private JLabel lblCuerpoProducto;
        private JLabel lblCuerpoUnidades;
        private JLabel lblCuerpoPrecio;
        private JLabel lblCuerpoVendedor;
        private JLabel lblCuerpoCliente;
        
	private JLabel lblCodigoVendedor;
	private JLabel lblCategoria;
	private JLabel lblNombres;
	private JLabel lblApellidos;
        private JLabel lblTelefono;
        private JLabel lblDni;
        //
        private JTextField txtCabeceraNroSerie;
        private JTextField txtCuerpoProducto;
        private JTextField txtCuerpoUnidades;
        private JTextField txtCuerpoPrecio;
        private JTextField txtCuerpoCliente;
        private JTextField txtCuerpoVendedor;
        private JTextField txtCuerpoTotal;
        //
	private JScrollPane scrollPane;
        private JPanel jPanelCabecera;
        private JPanel jPanelCuerpo;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblVentas;
	private DefaultTableModel modelo;
        private ArrayList<Factura> lstFacturas = new ArrayList<>();
        private CompletionService<?> completionService;
        private boolean flagClienteExiste = false;
        private boolean flagProductoExiste = false;
        private boolean flagVendedorExiste = false;
        ArrayList<String> txtCuerpoProductoKeyWords = new ArrayList<String>();
        ArrayList<String> txtCuerpoClienteKeyWords = new ArrayList<String>();
        ArrayList<String> txtCuerpoVendedorKeyWords = new ArrayList<String>();
        Autocomplete autoCompleteProducto;
        Autocomplete autoCompleteCliente;
        Autocomplete autoCompleteVendedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgVentas dialog = new DlgVentas();
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
	public DlgVentas() {
            setResizable(false);
            setTitle("Mantenimiento | Ventas");
            setBounds(100, 100, 1600, 800);
            getContentPane().setLayout(null);

            //definirInputsMantenimiento();
            definirBotones();
            definirColumnasGrilla();
            //Cabecera
            jPanelCabecera = new JPanel();
            jPanelCabecera.setBounds(500,10,600,150);
            jPanelCabecera.setBorder(BorderFactory.createLineBorder(Color.black));
            definirCabecera();
            getContentPane().add(jPanelCabecera);   
            
            //Cuerpo
            jPanelCuerpo = new JPanel();
            jPanelCuerpo.setBounds(10,200,800,270);
            jPanelCuerpo.setBorder(BorderFactory.createLineBorder(Color.black));
            definirCuerpoBusqueda();
            getContentPane().add(jPanelCuerpo);

            //Grilla
            scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 500, 1575, 250);
            getContentPane().add(scrollPane);

            tblVentas = new JTable();
            tblVentas.addKeyListener(this);
            tblVentas.addMouseListener(this);
            tblVentas.setFillsViewportHeight(true);
            scrollPane.setViewportView(tblVentas);           

            tblVentas.setModel(modelo);
            ajustarAnchoColumnas();
            listar();
            editarFila();
            habilitarEntradas(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Inicializacion">
    private void definirCabecera(){
        lblImgCama = new JLabel();
        lblImgCama.setIcon(new ImageIcon("imagenes/dlgCama.png"));
        lblImgCama.setBounds(550, 33, 89, 89);
        getContentPane().add(lblImgCama);
        
        lblCabeceraTitulo = new JLabel("Punto de Venta I.E.P. Ernesto Velit Ruiz");
        lblCabeceraTitulo.setBounds(700, 33, 300, 23);
        getContentPane().add(lblCabeceraTitulo);
        
        lblCabeceraNroSerie = new JLabel("Nro Serie");
        lblCabeceraNroSerie.setBounds(700, 77, 300, 23);
        getContentPane().add(lblCabeceraNroSerie);
        
        txtCabeceraNroSerie = new JTextField();
        txtCabeceraNroSerie.setBounds(780, 77, 100, 23);
        getContentPane().add(txtCabeceraNroSerie);
        txtCabeceraNroSerie.setColumns(10);
        txtCabeceraNroSerie.setEditable(false);
    }
    
    private void definirCuerpoBusqueda(){
        
        lblCuerpoProducto = new JLabel("Cod. Producto: ");
        lblCuerpoProducto.setBounds(25, 240, 300, 23);
        getContentPane().add(lblCuerpoProducto);
        
        lblCuerpoPrecio = new JLabel("Precio(S/.): ");
        lblCuerpoPrecio.setBounds(25, 320, 300, 23);
        getContentPane().add(lblCuerpoPrecio);
        
        lblCuerpoUnidades = new JLabel("Unidades: ");
        lblCuerpoUnidades.setBounds(25, 400, 300, 23);
        getContentPane().add(lblCuerpoUnidades);
        
        txtCuerpoProducto = new JTextField();
        txtCuerpoProducto.setBounds(150, 240, 100, 23);
        getContentPane().add(txtCuerpoProducto);
        txtCuerpoProducto.setColumns(10);
        
        txtCuerpoProducto.setFocusTraversalKeysEnabled(false);
        autoCompleteProducto = new Autocomplete(txtCuerpoProducto, txtCuerpoProductoKeyWords);
        txtCuerpoProducto.getDocument().addDocumentListener(autoCompleteProducto);
        txtCuerpoProducto.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "commit");
        txtCuerpoProducto.getActionMap().put("commit", autoCompleteProducto.new CommitAction());
        txtCuerpoProducto.addKeyListener(this);
        
        txtCuerpoPrecio = new JTextField();
        txtCuerpoPrecio.setBounds(150, 320, 100, 23);
        getContentPane().add(txtCuerpoPrecio);
        txtCuerpoPrecio.setColumns(10);
        
        txtCuerpoUnidades = new JTextField();
        txtCuerpoUnidades.setBounds(150, 400, 100, 23);
        getContentPane().add(txtCuerpoUnidades);
        txtCuerpoUnidades.setColumns(10);
        
        
        lblCuerpoCliente = new JLabel("Cod. Cliente: ");
        lblCuerpoCliente.setBounds(300, 240, 300, 23);
        getContentPane().add(lblCuerpoCliente);
        
        lblCuerpoVendedor = new JLabel("Cod. Vendedor: ");
        lblCuerpoVendedor.setBounds(300, 320, 300, 23);
        getContentPane().add(lblCuerpoVendedor);
        
        txtCuerpoCliente = new JTextField();
        txtCuerpoCliente.setBounds(450, 240, 100, 23);
        getContentPane().add(txtCuerpoCliente);
        txtCuerpoCliente.setColumns(10);
        
        txtCuerpoCliente.setFocusTraversalKeysEnabled(false);
        autoCompleteCliente = new Autocomplete(txtCuerpoCliente, txtCuerpoClienteKeyWords);
        txtCuerpoCliente.getDocument().addDocumentListener(autoCompleteCliente);
        txtCuerpoCliente.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "commit");
        txtCuerpoCliente.getActionMap().put("commit", autoCompleteCliente.new CommitAction());
        txtCuerpoCliente.addKeyListener(this);
        
        txtCuerpoVendedor = new JTextField();
        txtCuerpoVendedor.setBounds(450, 320, 100, 23);
        getContentPane().add(txtCuerpoVendedor);
        txtCuerpoVendedor.setColumns(10);
        
        txtCuerpoVendedor.setFocusTraversalKeysEnabled(false);
        autoCompleteVendedor = new Autocomplete(txtCuerpoVendedor, txtCuerpoVendedorKeyWords);
        txtCuerpoVendedor.getDocument().addDocumentListener(autoCompleteVendedor);
        txtCuerpoVendedor.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "commit");
        txtCuerpoVendedor.getActionMap().put("commit", autoCompleteVendedor.new CommitAction());
        txtCuerpoVendedor.addKeyListener(this);
        
        
        
        txtCuerpoTotal = new JTextField();
        txtCuerpoTotal.setBounds(150, 240, 100, 23);
        txtCuerpoTotal.setVisible(false);
        getContentPane().add(txtCuerpoTotal);
        
        
    }
    
    private void definirColumnasGrilla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("Nro");
        modelo.addColumn("Cliente");
        modelo.addColumn("Vendedor");
        modelo.addColumn("Cod. Producto");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
    }
    
    private void definirBotones(){
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(this);
        btnAceptar.addMouseListener(this);
        btnAceptar.setForeground(Color.BLUE);
        btnAceptar.setBounds(620, 240, 150, 23);
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
        lstFacturas = DBUtils.parsearListaFactura(DBUtils.cargarData("facturas"));
        int posFila = 0;
        if (modelo.getRowCount() > 0)
                posFila = tblVentas.getSelectedRow();
        if (modelo.getRowCount() == lstFacturas.size()- 1)
                posFila = lstFacturas.size() - 1;
        if (posFila == lstFacturas.size())
                posFila --;
        modelo.setRowCount(0);
        Factura x;
        for (int i=0; i<lstFacturas.size(); i++) {
                x = lstFacturas.get(i);
                Object[] fila = { i+1, x.getCliente().getNombres() + " " + x.getCliente().getApellidos(), x.getVendedor().getNombres() + " " + x.getVendedor().getApellidos(), x.getProducto().getDescripcion(),"S/."+x.getPrecio(), x.getUnidades(), "S/."+x.getTotal()};
                modelo.addRow(fila);
        }
        if (lstFacturas.size() > 0)
                tblVentas.getSelectionModel().setSelectionInterval(posFila, posFila);
    }
    void guardar() {
            DBUtils.grabarData(new ArrayList(lstFacturas), "facturas");
    }
    //</editor-fold>
    
    void editarFila() {
        if (lstFacturas.size() == 0)
                limpieza();
        else {
                Factura factura = lstFacturas.get(tblVentas.getSelectedRow());
                txtCuerpoProducto.setText(String.valueOf(factura.getProducto().getCodigoProducto()));
                txtCuerpoCliente.setText(String.valueOf(factura.getCliente().getCodigoCliente()));
                txtCuerpoVendedor.setText(String.valueOf(factura.getVendedor().getCodigoVendedor()));
                txtCuerpoPrecio.setText(String.valueOf(factura.getPrecio()));
                txtCuerpoUnidades.setText(String.valueOf(factura.getUnidades()));
                txtCuerpoTotal.setText(String.valueOf(factura.getTotal()));
                txtCabeceraNroSerie.setText(String.valueOf(factura.getCodigoFactura()));
        }
    }
    void limpieza() {
            txtCabeceraNroSerie.setText(String.valueOf(calcularCorrelativo()));
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
			mensaje("No existen camas");	
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
			mensaje("No existen camas");
		else {
			editarFila();
			habilitarEntradas(false);
                        Factura facturaEliminar = new Factura();
                        for (Factura factura : lstFacturas) {
                            if(factura.getCodigoFactura()== Integer.parseInt(txtCabeceraNroSerie.getText().trim()))
                                facturaEliminar = factura;
                        }
                        
                        lstFacturas.remove(facturaEliminar);
			guardar();
                        listar();
                        editarFila();
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
                int codigoFactura = Integer.parseInt(txtCabeceraNroSerie.getText().trim());
                int codigoProducto = Integer.parseInt(txtCuerpoProducto.getText().trim());
                int codigoCliente = Integer.parseInt(txtCuerpoCliente.getText().trim());
                int codigoVendedor = Integer.parseInt(txtCuerpoVendedor.getText().trim());
                double precio = Double.parseDouble(txtCuerpoPrecio.getText().trim());
                int unidades = Integer.parseInt(txtCuerpoUnidades.getText().trim());
                //Calcular Total
                txtCuerpoTotal.setText(String.valueOf(precio*unidades));
                double total = Double.parseDouble(txtCuerpoTotal.getText().trim());

		
		if (btnAdicionar.isEnabled() == false) {
			Factura factura = new Factura();
                        factura.setCodigoFactura(codigoFactura);
                        factura.setProducto(new Producto());
                        factura.getProducto().setCodigoProducto(codigoProducto);
                        
                        factura.setCliente(new Cliente());
                        factura.getCliente().setCodigoCliente(codigoCliente);
                        
                        factura.setVendedor(new Vendedor());
                        factura.getVendedor().setCodigoVendedor(codigoVendedor);
                        
                        factura.setPrecio(precio);
                        factura.setUnidades(unidades);
                        factura.setTotal(total);
                        lstFacturas.add(factura);
			guardar();
			btnAdicionar.setEnabled(true);
		}
		if (btnModificar.isEnabled() == false) {
                        for (Factura factura : lstFacturas) {
                            if(factura.getCodigoFactura() == Integer.parseInt(txtCabeceraNroSerie.getText().trim())){
                                factura.setProducto(new Producto());
                                factura.getProducto().setCodigoProducto(codigoProducto);

                                factura.setCliente(new Cliente());
                                factura.getCliente().setCodigoCliente(codigoCliente);

                                factura.setVendedor(new Vendedor());
                                factura.getVendedor().setCodigoVendedor(codigoVendedor);

                                factura.setPrecio(precio);
                                factura.setUnidades(unidades);
                                factura.setTotal(total);
                            }                                
                        }
			guardar();
			btnModificar.setEnabled(true);
		}
		listar();
		habilitarEntradas(false);
	}
        
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg) {
            if (arg.getSource() == txtCuerpoProducto) {
                String textoBusquedaProductos = txtCuerpoProducto.getText();
                System.out.println("Texto ingresado: " + textoBusquedaProductos);
                if(!textoBusquedaProductos.isEmpty()){
                    
                    ArrayList<Producto> listaProductos = DBUtils.parsearListaProducto(DBUtils.buscarDataCodigo("productos", textoBusquedaProductos));
                    txtCuerpoProductoKeyWords.clear();
                    for (Producto producto : listaProductos) {                        
                        txtCuerpoProductoKeyWords.add(String.valueOf(producto.getCodigoProducto()));
                    }                 
                    
                }  
            }
            else if (arg.getSource() == txtCuerpoCliente) {
                String textoBusquedaClientes = txtCuerpoCliente.getText();
                System.out.println("Texto ingresado: " + textoBusquedaClientes);
                if(!textoBusquedaClientes.isEmpty()){
                    ArrayList<Cliente> listaClientes = DBUtils.parsearListaCliente(DBUtils.buscarDataCodigo("clientes", textoBusquedaClientes));
                    txtCuerpoClienteKeyWords.clear();
                    for (Cliente cliente : listaClientes) {                        
                        txtCuerpoClienteKeyWords.add(String.valueOf(cliente.getCodigoCliente()));
                    }                    
                    
                }
            }
            else if (arg.getSource() == txtCuerpoVendedor) {
                String textoBusquedaVendedores = txtCuerpoVendedor.getText();
                System.out.println("Texto ingresado: " + textoBusquedaVendedores);
                if(!textoBusquedaVendedores.isEmpty()){
                    ArrayList<Vendedor> listaVendedores = DBUtils.parsearListaVendedor(DBUtils.buscarDataCodigo("vendedores", textoBusquedaVendedores));
                    txtCuerpoVendedorKeyWords.clear();
                    for (Vendedor vendedor : listaVendedores) {                        
                        txtCuerpoVendedorKeyWords.add(String.valueOf(vendedor.getCodigoVendedor()));
                    }                    
                }
            }
            else{
                arg.consume();
		editarFila();	
            }
			
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblVentas) {
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
		if (arg0.getSource() == tblVentas) {
			mouseEnteredTblVentas(arg0);
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
	protected void mouseEnteredTblVentas(MouseEvent arg0) {
		tblVentas.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		TableColumnModel tcm = tblVentas.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25)); 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(25));  
                tcm.getColumn(4).setPreferredWidth(anchoColumna(25));
                tcm.getColumn(5).setPreferredWidth(anchoColumna(25));
                tcm.getColumn(6).setPreferredWidth(anchoColumna(25));
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