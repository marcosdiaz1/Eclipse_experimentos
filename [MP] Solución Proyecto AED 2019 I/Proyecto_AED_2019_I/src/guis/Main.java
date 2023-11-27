package guis;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame implements ActionListener {

	private JLabel lblFondo;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenu mnMantenimiento;
	private JMenu mnReporte;
	private JMenuItem mntmSalir;
        private JMenuItem mntmVentas;
        private JMenuItem mntmVendedores; 
        private JMenuItem mntmClientes; 
        private JMenuItem mntmProductos; 
	private JMenuItem mntmReporteVentas;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		int ANCHO = 1600, ALTO = 800,
			DX = 6, DY = 52;
		
		setResizable(false);
		setTitle("Solution Proyecto 2023");
		setIconImage(new ImageIcon("imagenes/PrimaTaxi.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO + DX, ALTO + DY);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		menuProyecto.add(mnArchivo);
                
                mntmVentas = new JMenuItem("POS");  
		mntmVentas.addActionListener(this);
		mnArchivo.add(mntmVentas);
		
		mntmSalir = new JMenuItem("Salir");  
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento);
                
                mntmVendedores = new JMenuItem("Vendedores");
		mntmVendedores.addActionListener(this);
		mnMantenimiento.add(mntmVendedores);
               
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(this);
		mnMantenimiento.add(mntmClientes);
		
		mntmProductos = new JMenuItem("Productos");
		mntmProductos.addActionListener(this);
		mnMantenimiento.add(mntmProductos);
		
		mnReporte = new JMenu("Reporte");
		menuProyecto.add(mnReporte);
	
		mntmReporteVentas = new JMenuItem("Reporte de Ventas");
		mntmReporteVentas.addActionListener(this);
		mnReporte.add(mntmReporteVentas);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFondo = new JLabel(new ImageIcon("imagenes/imgMenuFondo.jpg"));
		lblFondo.setBounds(0, 0, ANCHO, ALTO);
		getContentPane().add(lblFondo);	
	}

	public void actionPerformed(ActionEvent e) {
                if(e.getSource() == mntmVendedores){
                    DlgVendedores dlg = new DlgVendedores();
                    dlg.setLocationRelativeTo(this);
                    dlg.setVisible(true);
                }
                if(e.getSource() == mntmClientes){
                    DlgCliente dlg = new DlgCliente();
                    dlg.setLocationRelativeTo(this);
                    dlg.setVisible(true);
                }
                if(e.getSource() == mntmProductos){
                    DlgProducto dlg = new DlgProducto();
                    dlg.setLocationRelativeTo(this);
                    dlg.setVisible(true);
                }
                
                if(e.getSource() == mntmVentas){
                    DlgVentas dlg = new DlgVentas();
                    dlg.setLocationRelativeTo(this);
                    dlg.setVisible(true);
                }
                
                if(e.getSource() == mntmReporteVentas){
                    DlgReportes dlg = new DlgReportes();
                    dlg.setLocationRelativeTo(this);
                    dlg.setVisible(true);
                }
                
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		dispose();
	}
	
}