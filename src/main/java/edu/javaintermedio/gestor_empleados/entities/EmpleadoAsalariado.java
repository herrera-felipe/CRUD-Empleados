package edu.javaintermedio.gestor_empleados.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Felipe Herrera
 */
// MAPEO DE LA CLASE
@Entity
@DiscriminatorValue(value = "EMPLEADO_ASALARIADO")
public class EmpleadoAsalariado extends Empleado implements Serializable {  // interfaz serializable para persistir

    private static final long serialVersionUID = 1L;

    @Column(name = "SALARIO_SEMANAL")
    private double salarioSemanal;

    public EmpleadoAsalariado() {
    }

    public EmpleadoAsalariado(long legajo, String nombre, String apellido, String ssn, double salarioSemanal) {
        super(legajo, nombre, apellido, ssn);
        this.salarioSemanal = salarioSemanal;
    }

    public double getSalarioSemanal() {
        return salarioSemanal;
    }

    public void setSalarioSemanal(double salarioSemanal) {
        this.salarioSemanal = salarioSemanal;
    }

    @Override
    public double calcularIngresos() {
        return getSalarioSemanal();
    }

    @Override
    public String toString() {
        return String.format("Empleado asalariado: %s%n%s: $%,.2f", super.toString(), "Salario semanal: ", getSalarioSemanal());
    }

}
