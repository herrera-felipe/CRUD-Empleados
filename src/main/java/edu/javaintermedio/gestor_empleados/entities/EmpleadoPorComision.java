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
@Entity // Clase como entidad
@DiscriminatorValue(value = "EMPLEADO_POR_COMISION") // Tipo empleado "POR COMISION"
public class EmpleadoPorComision extends Empleado implements Serializable {

    //declaracion de variables
    @Column(name = "TARIFA_COMISION") // Nombre columna
    private double tarifaComision;
    
    @Column(name = "VENTAS_BRUTAS") // Nombre columna
    private double ventasBrutas;

    //constructor por defecto
    public EmpleadoPorComision() {
    }

    //constructor
    public EmpleadoPorComision(long legajo, String nombre, String apellido, String ssn, double ventasBrutas, double tarifaComision) {
        super(legajo, nombre, apellido, ssn);
        this.tarifaComision = tarifaComision;
        this.ventasBrutas = ventasBrutas;
    }

    //getter y setter
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

    //implemento del metodo abstracto
    @Override
    public double calcularIngresos() {
        return getTarifaComision() * getVentasBrutas();
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%, .2f; %s: %.2f", 
                "Empleado por comision",super.toString(),
                "ventas brutas", getVentasBrutas(),
                "Tarifa Comision",getTarifaComision());
    }
}
