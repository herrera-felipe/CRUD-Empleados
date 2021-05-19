package edu.javaintermedio.gestor_empleados.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Andres Felipe Alzate Herrera
 */



// MAPEO DE LA CLASE
@Entity 
@Table(name="EMPLEADOS") 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_EMP") 
public abstract  class Empleado implements  Serializable{ 
   
    
    private static final long serialVersionUID = 1L;

    @Id 
    @Column(name = "LEGAJO") 
    private long legajo;
    
    @Column(name = "NOMBRE") 
    private String nombre;

    @Column(name = "APELLIDO") 
    private String apellido;
    
    @Column(name = "SSN", nullable = false) 
    private String ssn;

    public Empleado() { 
    }
    
    public Empleado(long legajo, String nombre, String apellido, String ssn) {
        
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ssn = ssn;
    }
    
    public abstract double calcularIngresos();
    
    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    

    @Override
    public String toString() {
    	
        return "\nLegajo: " + legajo + 
               "\nNombre: " + nombre + 
               "\nApellido: " + apellido + 
               "\nNumero seguro social: " + ssn;
    }

}