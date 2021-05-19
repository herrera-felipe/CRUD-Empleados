package edu.javaintermedio.gestor_empleados.iu.controller;

import edu.javaintermedio.gestor_empleados.daos.exceptions.NonexistentEntityException;
import edu.javaintermedio.gestor_empleados.db.TipoEmpleado;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoAsalariado;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoBaseMasComision;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoPorComision;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoPorHoras;
import edu.javaintermedio.gestor_empleados.iu.MensajeError;
import edu.javaintermedio.gestor_empleados.iu.controller.dto.EmpleadoDTO;
import edu.javaintermedio.gestor_empleados.services.EmpleadoService;

public class EmpleadoController {

    private EmpleadoService empService;

    public EmpleadoController() {
        super();
        empService = new EmpleadoService();
    }

    // Metodo para Guardar Empleado
    public boolean guardar(EmpleadoDTO empDTO) {

        boolean rta = false;

        if (validacionBasica(empDTO)) {

            if (empDTO.getTipo().equals(TipoEmpleado.EmpleadoAsalarido.name())) {

                if (!isNumber(empDTO.getSueldo())) {

                    MensajeError.lanzarMensaje("El sueldo debe ser un valor numérico");

                    System.out.println("El sueldo debe ser un valor numérico");

                    rta = false;
                } else {

                    EmpleadoAsalariado empAsalariado = new EmpleadoAsalariado(
                            Long.parseLong(empDTO.getLegajo()),
                            empDTO.getNombre(),
                            empDTO.getApellido(),
                            empDTO.getSsn(),
                            Double.parseDouble(empDTO.getSueldo()));

                    rta = empService.guardar(empAsalariado);

                }
            }

            // Caso EmpleadoBaseMasComision
            if (empDTO.getTipo().equals(
                    TipoEmpleado.EmpleadoBaseMasComision.name())) {

                if (!isNumber(empDTO.getVentas()) || !isNumber(empDTO.getTarifa()) || !isNumber(empDTO.getSueldo())) {

                    // Enviar mensaje al usuario
                    MensajeError.lanzarMensaje("Las ventas, tarifa o sueldo deben ser numericos.");

                    System.out.println("Las ventas, tarifa o sueldo deben ser numericos.");

                    rta = false;
                } else {
                    EmpleadoBaseMasComision empBaseMasComision = new EmpleadoBaseMasComision(Long.parseLong(empDTO.getLegajo()),
                            empDTO.getNombre(),
                            empDTO.getApellido(),
                            empDTO.getSsn(),
                            Double.parseDouble(empDTO.getVentas()),
                            Double.parseDouble(empDTO.getTarifa()),
                            Double.parseDouble(empDTO.getSueldo()));

                    rta = empService.guardar(empBaseMasComision);

                }

            }

            // Caso EmpleadoPorComision
            if (empDTO.getTipo().equals(TipoEmpleado.EmpleadoPorComision.name())) {

                if (!isNumber(empDTO.getVentas()) || !isNumber(empDTO.getTarifa())) {

                    // Enviar mensaje al usuario
                    MensajeError.lanzarMensaje("Las ventas, tarifa  deben ser numericos.");

                    System.out.println("Las ventas, tarifa  deben ser numericos.");

                    rta = false;
                } else {

                    EmpleadoPorComision empPorComision = new EmpleadoPorComision(Long.parseLong(empDTO.getLegajo()),
                            empDTO.getNombre(),
                            empDTO.getApellido(),
                            empDTO.getSsn(),
                            Double.parseDouble(empDTO.getVentas()),
                            Double.parseDouble(empDTO.getTarifa()));

                    rta = empService.guardar(empPorComision);

                }

            }
            // Caso EmpleadoPorHora
            if (empDTO.getTipo().equals(TipoEmpleado.EmpleadoPorHora.name())) {

                if (!isNumber(empDTO.getHoras())) {

                    MensajeError.lanzarMensaje("Las horas deben ser un valor numerico.");

                    System.out.println("Las horas deben ser un valor numerico.");

                    rta = false;
                } else {

                    EmpleadoPorHoras empHoras = new EmpleadoPorHoras(Long.parseLong(empDTO.getLegajo()),
                            empDTO.getNombre(),
                            empDTO.getApellido(),
                            empDTO.getSsn(),
                            Double.parseDouble(empDTO.getSueldo()),
                            Double.parseDouble(empDTO.getHoras()));

                    rta = empService.guardar(empHoras);

                }

            }

        }

        return rta;

    }

    private boolean validacionBasica(EmpleadoDTO empDTO) {
        boolean rta = true;

        // Si no es un valor numerico decimal lanzo error
        if (!isNumber(empDTO.getLegajo())) {

            // Mostrar error al usuario con una ventana
            MensajeError.lanzarMensaje("El legajo debe ser un valor numérico");
            System.out.println("El legajo debe ser un valor numérico");
            rta = false;
        }

        return rta;

    }

    private boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Metodo para Buscar Empleado
    public EmpleadoDTO buscarEmp(String buscar) throws NonexistentEntityException {

        Long buscar2 = Long.parseLong(buscar); // Parseo de string a long arg buscar

        empService.buscarEmpleado(buscar2); // 2-llamar a service

        return null;
    }

    /**
     * Metodo que devuelve la lista de empleados para ser llenados en el combo
     * empleado.
     *
     * @return
     */
    public String[] getTiposEmpleados() {

        String[] listaTipos = new String[empService.getTiposEmpleados().size()];
        listaTipos = empService.getTiposEmpleados().toArray(listaTipos);

        return listaTipos;

    }

}
