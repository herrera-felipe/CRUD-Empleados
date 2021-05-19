package edu.javaintermedio.gestor_empleados.iu.controller.dto;

public class EmpleadoDTO {

    private String legajo;
    private String nombre;
    private String apellido;
    private String ssn;
    private String tipo;
    private String sueldo;
    private String horas;
    private String tarifa;
    private String ventas;

    public EmpleadoDTO(String legajo, String nombre, String apellido,
            String ssn, String tipo, String sueldo, String horas,
            String tarifa, String ventas) {
        super();
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ssn = ssn;
        this.tipo = tipo;
        this.sueldo = sueldo;
        this.horas = horas;
        this.tarifa = tarifa;
        this.ventas = ventas;
    }

    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSsn() {
        return ssn;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSueldo() {
        return sueldo;
    }

    public String getHoras() {
        return horas;
    }

    public String getTarifa() {
        return tarifa;
    }

    public String getVentas() {
        return ventas;
    }

}
