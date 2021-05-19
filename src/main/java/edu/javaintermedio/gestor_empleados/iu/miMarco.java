package edu.javaintermedio.gestor_empleados.iu;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 *
 * @author Andres Felipe Alzate Herrera
 */

public class miMarco extends JFrame {

	// Metodo Constructor
	public miMarco() {

		// Para saber la resolucion de pantala, y adecuar el marco.
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		
		Dimension resolucionPantalla = pantalla.getScreenSize();
		
		int altoP = resolucionPantalla.height;
		int anchoP = resolucionPantalla.width;
		
		// Atributos de la ventana
		setSize(anchoP/2, altoP/2);
		setResizable(false); // Evita que el usuario pueda maximixar.
		setTitle("Gestor Empleados"); // Titulo de la Ventana.

	} // Fin constructor

} // Fin class miMarco
