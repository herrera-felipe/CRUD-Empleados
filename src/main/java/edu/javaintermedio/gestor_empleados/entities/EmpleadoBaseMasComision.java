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
@Entity
@DiscriminatorValue(value = "EMPLEADO_BASE_POR_COMISION")
public class EmpleadoBaseMasComision extends EmpleadoPorComision implements Serializable {

    @Column(name = "SALARIO_BASE")
    private double salarioBase;

    public EmpleadoBaseMasComision() {
    }

    public EmpleadoBaseMasComision(long legajo, String nombre, String apellido, String ssn, double ventasBrutas, double tarifaComision, double salarioBase) {
        super(legajo, nombre, apellido, ssn, tarifaComision, ventasBrutas);

        this.salarioBase = salarioBase;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    @Override
    public double calcularIngresos() {
        return getSalarioBase() + super.calcularIngresos();
    }

    @Override
    public String toString() {
        return String.format("%s %s; %s: $%,.2f",
                "Con salario Base", super.toString(),
                "salario Base", getSalarioBase());
    }

}
