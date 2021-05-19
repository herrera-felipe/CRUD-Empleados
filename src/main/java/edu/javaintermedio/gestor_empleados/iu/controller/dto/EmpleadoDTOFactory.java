package edu.javaintermedio.gestor_empleados.iu.controller.dto;

import edu.javaintermedio.gestor_empleados.entities.Empleado;

public class EmpleadoDTOFactory {

    public static EmpleadoDTO getEmpleadoDTO(Empleado empleado) {

        EmpleadoDTO dto = new EmpleadoDTO(String.valueOf(empleado.getLegajo()),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getSsn(), null, null, null, null, null);

        return dto;
    }

}
