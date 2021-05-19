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

import edu.javaintermedio.gestor_empleados.iu.controller.EmpleadoController;
import edu.javaintermedio.gestor_empleados.iu.controller.dto.EmpleadoDTO;



public class ventanaGaston extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField legajoField;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField ssn;
	private JTextField sueldoField;
	private JTextField tarifaComisionField;
	private JTextField vtasBrutasField;
	private JTextField horasField;
	
	private JButton btnGuardar;
	private JButton btnBuscar;
	private JButton btnBorrar;
	
	private JComboBox<String> comboEmpleado;
	private EmpleadoController empleadoController;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaGaston frame = new ventanaGaston();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null); // Centrar ventana
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaGaston() {
		empleadoController = new EmpleadoController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Para saber la resolucion de pantala, y adecuar el marco.
				Toolkit pantalla = Toolkit.getDefaultToolkit();
				
				Dimension resolucionPantalla = pantalla.getScreenSize();
				
				int altoP = resolucionPantalla.height;
				int anchoP = resolucionPantalla.width;
				
				// Atributos de la ventana
				setSize(anchoP/2, altoP/2);
//		setBounds(100, 100, 541, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setTitle("Gestión Empleados");
		
		JLabel lblNewLabel = new JLabel("Legajo:");
		lblNewLabel.setBounds(10, 14, 50, 14);
		contentPane.add(lblNewLabel);
		
		legajoField = new JTextField();
		legajoField.setBounds(70, 11, 371, 20);
		contentPane.add(legajoField);
		legajoField.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(11, 40, 49, 14);
		contentPane.add(lblNombre);
		
		nombreField = new JTextField();
		nombreField.setBounds(70, 37, 371, 20);
		contentPane.add(nombreField);
		nombreField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(11, 66, 49, 14);
		contentPane.add(lblApellido);
		
		apellidoField = new JTextField();
		apellidoField.setBounds(70, 63, 371, 20);
		contentPane.add(apellidoField);
		apellidoField.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 130, 50, 14);
		contentPane.add(lblTipo);
			
		setContentPane(contentPane);
		
	
		 comboEmpleado = new JComboBox<String>();
		for(String tipo:empleadoController.getTiposEmpleados()) {
			comboEmpleado.addItem(tipo);
		}
	
		comboEmpleado.setBounds(70, 127, 371, 20);
		
		contentPane.add(comboEmpleado);
		
	    btnGuardar = new JButton("Guardar");
	    btnGuardar.addActionListener(this);
	    
	   
		btnGuardar.setBounds(10, 409, 89, 23);
		contentPane.add(btnGuardar);
		
		btnBuscar = new JButton("Buscar");
		
		btnBuscar.setBounds(178, 409, 89, 23);
		contentPane.add(btnBuscar);
		
		btnBorrar = new JButton("Borrar");
		
		btnBorrar.setBounds(334, 409, 89, 23);
		contentPane.add(btnBorrar);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(10, 105, 42, 14);
		contentPane.add(lblSsn);
		
		ssn= new JTextField();
		ssn.setBounds(70, 96, 371, 20);
		contentPane.add(ssn);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(10, 197, 46, 14);
		contentPane.add(lblSueldo);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(10, 238, 46, 14);
		contentPane.add(lblHoras);
		
		JLabel lblVentasBrutas = new JLabel("Ventas:");
		lblVentasBrutas.setBounds(10, 277, 80, 14);
		contentPane.add(lblVentasBrutas);
		
		JLabel lblTarifaComision = new JLabel("Comisión:");
		lblTarifaComision.setBounds(6, 319, 54, 14);
		contentPane.add(lblTarifaComision);
		
		sueldoField = new JTextField();
		sueldoField.setBounds(70, 194, 86, 20);
		contentPane.add(sueldoField);
		
		
		tarifaComisionField = new JTextField();
		tarifaComisionField.setBounds(70, 316, 86, 20);
		contentPane.add(tarifaComisionField);
		
		horasField = new JTextField();
		horasField.setBounds(70, 235, 86, 20);
		contentPane.add(horasField);
		
		vtasBrutasField = new JTextField();
		vtasBrutasField.setBounds(70, 274, 86, 20);
		contentPane.add(vtasBrutasField);
		
		
		
		
		
	
		
	}
	
	

	public void actionPerformed(ActionEvent evt) {
		boolean rta= false;
		if(evt.getSource()== btnGuardar) {
	  rta =empleadoController.guardar(new EmpleadoDTO(legajoField.getText().trim(),
   				 nombreField.getText().trim(),
   				 apellidoField.getText().trim(),
   				 ssn.getText().trim(),
   				 comboEmpleado.getSelectedItem().toString(),
   				 sueldoField.getText().trim(),
   				 horasField.getText().trim(), 
   				 tarifaComisionField.getText().trim(), 
   				 vtasBrutasField.getText().trim())); 
		}
		
		if(evt.getSource() == btnBuscar) {
			
		}
		
		if(evt.getSource() == btnBorrar) {
			
		}
		
		if(rta) {
			limpiarCampos();
		}
		
		
		
	}
	
	
 private void limpiarCampos() {
	 nombreField.setText("");
	 apellidoField.setText("");
	 legajoField.setText("");
	 ssn.setText("");
	 sueldoField.setText("");
	 horasField.setText("");
	 tarifaComisionField.setText("");
	 vtasBrutasField.setText("");
 }
}



