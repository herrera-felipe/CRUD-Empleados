package edu.javaintermedio.gestor_empleados.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Felipe Herrera
 */

// MAPEO DE CLASE
@Entity // Clase como entidad
@DiscriminatorValue(value = "EMPLEADO_BASE_POR_COMISION") // Tipo empleado " BASE POR COMISION"
public class EmpleadoBaseMasComision extends EmpleadoPorComision implements Serializable{ // interfaz serializable para persistir

    @Column(name = "SALARIO_BASE")
    private double salarioBase; // salario base por semana

    //constructor por defecto
    public EmpleadoBaseMasComision() {
    }
    
    //constructor
    public EmpleadoBaseMasComision(long legajo, String nombre, String apellido, String ssn, double ventasBrutas, double tarifaComision, double salarioBase) {
        super(legajo, nombre, apellido, ssn, tarifaComision, ventasBrutas);
       
        this.salarioBase = salarioBase;
    }

    //getter y setter
    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    //implemento del metodo abstracto
    @Override
    public double calcularIngresos() {
        return getSalarioBase() + super.calcularIngresos();
    }

    @Override
    public String toString() {
        return String.format("%s %s; %s: $%,.2f",
                "Con salario Base",super.toString(),
                "salario Base",getSalarioBase());
    }

}

