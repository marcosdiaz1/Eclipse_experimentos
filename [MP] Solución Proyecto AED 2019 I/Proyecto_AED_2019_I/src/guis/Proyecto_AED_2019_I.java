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

public class Proyecto_AED_2019_I extends JFrame implements ActionListener {

	private JLabel lblFondo;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenu mnMantenimiento;
	private JMenu mnRegistro;
	private JMenu mnPago;
	private JMenu mnReporte;
	private JMenuItem mntmSalir;
        private JMenuItem mntmVendedores;
        
        
	private JMenuItem mntmCama;
	private JMenuItem mntmPaciente;
	private JMenuItem mntmMedicina;	
	private JMenuItem mntmAtencion;
	private JMenuItem mntmInternamiento;
	private JMenuItem mntmAtenciones;
	private JMenuItem mntmInternamientos;
	private JMenuItem mntmAtencionesPendientes;
	private JMenuItem mntmAtencionesPagadas;
	private JMenuItem mntmInternamientosPendientes;
	private JMenuItem mntmInternamientosPagados;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proyecto_AED_2019_I frame = new Proyecto_AED_2019_I();
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
	public Proyecto_AED_2019_I() {
		int ANCHO = 1600, ALTO = 800,
			DX = 6, DY = 52;
		
		setResizable(false);
		setTitle("MN-Global >>> PrimaTaxi >>> Solution Proyecto AED 2019 I - 05/07/2019");
		setIconImage(new ImageIcon("imagenes/PrimaTaxi.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO + DX, ALTO + DY);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		menuProyecto.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");  
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento);
                
                mntmVendedores = new JMenuItem("Vendedores");
		mntmVendedores.addActionListener(this);
		mnMantenimiento.add(mntmVendedores);
                
                //
		
		mntmCama = new JMenuItem("Cama");
		mntmCama.addActionListener(this);
		mnMantenimiento.add(mntmCama);
		
		mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.addActionListener(this);
		mnMantenimiento.add(mntmPaciente);
		
		mntmMedicina = new JMenuItem("Medicina");
		mntmMedicina.addActionListener(this);
		mnMantenimiento.add(mntmMedicina);
		
		mnRegistro = new JMenu("Registro");
		menuProyecto.add(mnRegistro);
		
		mntmAtencion = new JMenuItem("Atencion");
		mntmAtencion.addActionListener(this);
		mnRegistro.add(mntmAtencion);
		
		mntmInternamiento = new JMenuItem("Internamiento");
		mntmInternamiento.addActionListener(this);
		mnRegistro.add(mntmInternamiento);
		
		mnPago = new JMenu("Pago");
		menuProyecto.add(mnPago);
		
		mntmAtenciones = new JMenuItem("Atenciones");
		mntmAtenciones.addActionListener(this);
		mnPago.add(mntmAtenciones);
	
		mntmInternamientos = new JMenuItem("Internamientos");
		mntmInternamientos.addActionListener(this);
		mnPago.add(mntmInternamientos);
		
		mnReporte = new JMenu("Reporte");
		menuProyecto.add(mnReporte);
	
		mntmAtencionesPendientes = new JMenuItem("Atenciones pendientes");
		mntmAtencionesPendientes.addActionListener(this);
		mnReporte.add(mntmAtencionesPendientes);
		
		mntmAtencionesPagadas = new JMenuItem("Atenciones pagadas");
		mntmAtencionesPagadas.addActionListener(this);
		mnReporte.add(mntmAtencionesPagadas);
		
		mntmInternamientosPendientes = new JMenuItem("Internamientos pendientes");
		mntmInternamientosPendientes.addActionListener(this);
		mnReporte.add(mntmInternamientosPendientes);
		
		mntmInternamientosPagados = new JMenuItem("Internamientos pagados");
		mntmInternamientosPagados.addActionListener(this);
		mnReporte.add(mntmInternamientosPagados);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFondo = new JLabel(new ImageIcon("imagenes/imgMenuFondo.jpg"));
		lblFondo.setBounds(0, 0, ANCHO, ALTO);
		getContentPane().add(lblFondo);	
	}

	public void actionPerformed(ActionEvent e) {
                // Nuevo
                if(e.getSource() == mntmVendedores){
                    DlgVendedores dlgVendedores = new DlgVendedores();
                    dlgVendedores.setLocationRelativeTo(this);
                    dlgVendedores.setVisible(true);
                }
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		dispose();
	}
	
}