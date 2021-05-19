package edu.javaintermedio.gestor_empleados.iu;

import javax.swing.JFrame;

/**
 *
 * @author Andres Felipe Alzate Herrera
 */

public class ventana {

	public static void main(String[] args) {
		// Intancia del marco
		miMarco marco1 = new miMarco();

		marco1.setVisible(true);
		marco1.setLocationRelativeTo(null); // Centrar ventana
		marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	} // Fin main

} // Fin class ventana
