package edu.javaintermedio.gestor_empleados.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * Felipe Herrera
 */
// MAPEO DE LA CLASE
@Entity
@DiscriminatorValue(value = "EMPLEADO_POR_COMISION")
public class EmpleadoPorComision extends Empleado implements Serializable {

    @Column(name = "TARIFA_COMISION")
    private double tarifaComision;

    @Column(name = "VENTAS_BRUTAS")
    private double ventasBrutas;

    public EmpleadoPorComision() {

    }

    public EmpleadoPorComision(long legajo, String nombre, String apellido, String ssn, double ventasBrutas, double tarifaComision) {
        super(legajo, nombre, apellido, ssn);
        this.tarifaComision = tarifaComision;
        this.ventasBrutas = ventasBrutas;
    }

    public void setVentasBrutas(double ventasBrutas) {
        this.ventasBrutas = ventasBrutas;
    }

    public double getVentasBrutas() {
        return ventasBrutas;
    }

    public void setTarifaComision(double tarifaComision) {
        this.tarifaComision = tarifaComision;
    }

    public double getTarifaComision() {
        return tarifaComision;
    }

    @Override
    public double calcularIngresos() {
        return getTarifaComision() * getVentasBrutas();
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%, .2f; %s: %.2f",
                "Empleado por comision", super.toString(),
                "ventas brutas", getVentasBrutas(),
                "Tarifa Comision", getTarifaComision());
    }
}
