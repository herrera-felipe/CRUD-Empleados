package edu.javaintermedio.gestor_empleados.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Felipe Herrera
 */

// Mapeo de la clase
@Entity // Clase a entidad
@DiscriminatorValue(value = "EMPLEASDO_POR_HORAS") //Tipo empleado "POR HORAS"
public class EmpleadoPorHoras extends Empleado implements Serializable{ // Interfaz serializable para persistir

    @Column(name = "HORAS")
    private double horas;
    
    @Column(name = "SUELDO")
    private double sueldo;

    //constructor por defecto
    public EmpleadoPorHoras() {
    }

    //constructor
    public EmpleadoPorHoras(long legajo, String nombre, String apellido, String ssn, double sueldo, double horas) {
        super(legajo, nombre, apellido, ssn);
        this.horas = horas;
        this.sueldo = sueldo;
    }

    //getters y setters
    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    //implemento del metodo abstracto
    @Override
    public double calcularIngresos() {
        if (getHoras() <= 40) {
            return getSueldo() * getHoras();
        } else {
            return 40 * getSueldo() + (getHoras() - 40) * getSueldo() * 1.5;
        }
    }

    
    @Override
    public String toString() {
        return String.format("Empleado por horas: %s%n%s: $%,.2f; %s: %,.2f", super.toString(),"Sueldo por hora",getSueldo(),"horas trabajadas", getHoras());
    }


    
}
