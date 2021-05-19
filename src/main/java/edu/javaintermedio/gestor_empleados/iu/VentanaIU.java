package edu.javaintermedio.gestor_empleados.iu;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.javaintermedio.gestor_empleados.daos.exceptions.NonexistentEntityException;
import edu.javaintermedio.gestor_empleados.iu.controller.EmpleadoController;
import edu.javaintermedio.gestor_empleados.iu.controller.dto.EmpleadoDTO;
import javax.swing.SwingConstants;

public class VentanaIU extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtLegajo;
	private JTextField txtssn;
	private JTextField txtsueldo;
	private JTextField txthoras;
	private JTextField txtventas;
	private JTextField txtcomision;
	
	
//	private JButton btnGuardar;
//	private JButton btnBuscar;
//	private JButton btnBorrar;
	
	private EmpleadoController empController;
//	private JComboBox<String> desTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIU frame = new VentanaIU();
					frame.setLocationRelativeTo(null); // Centrar ventana
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public VentanaIU() {

		empController = new EmpleadoController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Para saber la resolucion de pantala, y adecuar el marco.
		Toolkit pantalla = Toolkit.getDefaultToolkit();

		Dimension resolucionPantalla = pantalla.getScreenSize();

		int altoP = resolucionPantalla.height;
		int anchoP = resolucionPantalla.width;

		// Atributos de la ventana
		setSize(anchoP / 2, altoP / 2);
		
		setResizable(false);
		
		setTitle("Gestor Empleados");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		// Campo Legajo
		JLabel LabelLegajo = new JLabel("Legajo:");
		LabelLegajo.setHorizontalAlignment(SwingConstants.LEFT);
		LabelLegajo.setBounds(27, 84, 68, 19);
		contentPane.add(LabelLegajo);
		
		txtLegajo = new JTextField();
		txtLegajo.setBounds(99, 84, 193, 20);
		contentPane.add(txtLegajo);
		txtLegajo.setColumns(10);

		// Campo Nombre
		JLabel LabelNombre = new JLabel("Nombre:");
		LabelNombre.setHorizontalAlignment(SwingConstants.LEFT);
		LabelNombre.setBounds(27, 42, 68, 20);
		contentPane.add(LabelNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(99, 42, 193, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		// Campo Apellido
		JLabel LabelApellido = new JLabel("Apellido:");
		LabelApellido.setBounds(384, 42, 80, 20);
		contentPane.add(LabelApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(456, 42, 193, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		// Campo Ssn
		JLabel LabelSsn = new JLabel("Ssn:");
		LabelSsn.setHorizontalAlignment(SwingConstants.LEFT);
		LabelSsn.setBounds(384, 84, 46, 19);
		contentPane.add(LabelSsn);

		txtssn = new JTextField();
		txtssn.setHorizontalAlignment(SwingConstants.CENTER);
		txtssn.setBounds(456, 84, 193, 19);
		contentPane.add(txtssn);
		txtssn.setColumns(10);
		
		// Menu desplegable
		JLabel LabelTipo = new JLabel("Tipo:");
		LabelTipo.setBounds(27, 212, 46, 20);
		contentPane.add(LabelTipo);
		
		JComboBox<String> desTipo = new JComboBox<String>();
		for(String tipo:empController.getTiposEmpleados()) {
			desTipo.addItem(tipo);
		}
		
		desTipo.setBounds(99, 212, 550, 20);
		
		contentPane.add(desTipo);
		
		// Campo Sueldo
		JLabel LabelSueldo = new JLabel("Sueldo:");
		LabelSueldo.setHorizontalAlignment(SwingConstants.LEFT);
		LabelSueldo.setBounds(27, 127, 68, 20);
		contentPane.add(LabelSueldo);
		
		txtsueldo = new JTextField();
		txtsueldo.setColumns(10);
		txtsueldo.setBounds(99, 127, 193, 20);
		contentPane.add(txtsueldo);

		// Campo Horas
		JLabel LabelHoras = new JLabel("Horas:");
		LabelHoras.setBounds(27, 167, 80, 19);
		contentPane.add(LabelHoras);
		
		txthoras = new JTextField();
		txthoras.setColumns(10);
		txthoras.setBounds(99, 167, 193, 20);
		contentPane.add(txthoras);
		
		// Campo Comision
		JLabel LabelComision = new JLabel("Comision:");
		LabelComision.setBounds(384, 167, 80, 19);
		contentPane.add(LabelComision);
		
		txtcomision = new JTextField();
		txtcomision.setColumns(10);
		txtcomision.setBounds(456, 167, 193, 20);
		contentPane.add(txtcomision);
		
		// Campo Ventas
		JLabel LabelVentas = new JLabel("Ventas:");
		LabelVentas.setHorizontalAlignment(SwingConstants.LEFT);
		LabelVentas.setBounds(384, 127, 80, 20);
		contentPane.add(LabelVentas);
		
		txtventas = new JTextField();
		txtventas.setColumns(10);
		txtventas.setBounds(456, 127, 193, 20);
		contentPane.add(txtventas);
		
		// Boton Guardar
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean rta= false;
				if(e.getSource()== btnGuardar) {
			  
					rta =empController.guardar(new EmpleadoDTO(txtLegajo.getText().trim(),
							txtNombre.getText().trim(), 
							txtApellido.getText().trim(), 
							txtssn.getText().trim(), 
							desTipo.getSelectedItem().toString(), 
							txtsueldo.getText().trim(), 
							txthoras.getText().trim(), 
							txtcomision.getText().trim(), 
							txtventas.getText().trim())); 
				}
				if(rta) {
					limpiarCampos();
				}
			}
		});
		btnGuardar.setBounds(113, 282, 89, 23);
		contentPane.add(btnGuardar);
		
		// Boton Borrar
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(473, 282, 89, 23);
		contentPane.add(btnBorrar);
		
		// Boton Buscar
		JButton btnBuscar = new JButton("Buscar");
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String buscar = txtLegajo.getText();
				
				try {
					
					//1-llama al controlador 
					empController.buscarEmp(buscar);
				} catch (NonexistentEntityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				// Limpiador de campo
				txtLegajo.setText("");
			}
			
		});
		btnBuscar.setBounds(294, 282, 89, 23);
		contentPane.add(btnBuscar);
		
		
	}// Fin VentanaUI
	
	public void cargarDatos(EmpleadoDTO empl){
		
		this.txtNombre.setText(empl.getNombre());
		this.txtApellido.setText(empl.getApellido());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
//	public void actionPerformed(ActionEvent evt) {}
//		boolean rta= false;
//		if(evt.getSource()== btnGuardar) {
//	  
//			rta =empController.guardar(new EmpleadoDTO(txtLegajo.getText().trim(),
//					txtNombre.getText().trim(), 
//					txtApellido.getText().trim(), 
//					txtssn.getText().trim(), 
//					desTipo.getSelectedItem().toString(), 
//					txtsueldo.getText().trim(), 
//					txthoras.getText().trim(), 
//					txtcomision.getText().trim(), 
//					txtventas.getText().trim())); 
//		}
//		
//		if(evt.getSource() == btnBuscar) {
//			
//		}
//		
//		if(evt.getSource() == btnBorrar) {
//			
//		}
//		
//		if(rta) {
//			limpiarCampos();
//		}
//		
//	}
//	
	
	 private void limpiarCampos() {
		 txtNombre.setText("");
		 txtApellido.setText("");
		 txtLegajo.setText("");
		 txtssn.setText("");
		 txtsueldo.setText("");
		 txthoras.setText("");
		 txtcomision.setText("");
		 txtventas.setText("");
	 }
}

