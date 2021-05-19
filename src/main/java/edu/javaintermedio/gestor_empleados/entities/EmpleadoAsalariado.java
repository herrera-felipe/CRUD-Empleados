package edu.javaintermedio.gestor_empleados.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 *
 * @author Alumnos
 */


// MAPEO DE LA CLASE
@Entity // Definimos esta clase como una entidad
@DiscriminatorValue(value = "EMPLEADO_ASALARIADO" ) // Tipo empleado en este caso "asalariado"
public class EmpleadoAsalariado extends Empleado implements Serializable{  // interfaz serializable para persistir

    //declaracion de variables
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SALARIO_SEMANAL")
    private double salarioSemanal;
    
    

    // constructor por defecto
    public EmpleadoAsalariado() {
    }
    
    //constructor
    public EmpleadoAsalariado(long legajo, String nombre, String apellido, String ssn, double salarioSemanal) {
        super(legajo, nombre, apellido, ssn);
        this.salarioSemanal = salarioSemanal;
    }

    //setter y getter
    public double getSalarioSemanal() {
        return salarioSemanal;
    }

    public void setSalarioSemanal(double salarioSemanal) {
        this.salarioSemanal = salarioSemanal;
    }

    //implemento del metodo abstracto
    @Override
    public double calcularIngresos() {
        return getSalarioSemanal();
    }

    @Override
    public String toString() {
        return String.format("Empleado asalariado: %s%n%s: $%,.2f",super.toString(),"Salario semanal: ", getSalarioSemanal() );
    }


}
