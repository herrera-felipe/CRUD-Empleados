package edu.javaintermedio.gestor_empleados.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.javaintermedio.gestor_empleados.daos.EmpleadoDAO;
import edu.javaintermedio.gestor_empleados.daos.exceptions.NonexistentEntityException;
import edu.javaintermedio.gestor_empleados.db.TipoEmpleado;
import edu.javaintermedio.gestor_empleados.entities.Empleado;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoAsalariado;

/**
 * Clase service de empleado. Esta clase tiene la responsabilidad de manejar la
 * logica del negocio.
 * 
 * @author Gaston Cangemi
 *
 */
public class EmpleadoService {

	private EntityManagerFactory emf;
	private EmpleadoDAO empDAO;

	// Constructor por defecto donde iniciliazamos nuesta undad de persistencia
	public EmpleadoService() {
		super();
		emf = Persistence.createEntityManagerFactory("up_h2");
		empDAO = new EmpleadoDAO(emf);
	}

	/**
	 * Metodo para guardar un empleado
	 * 
	 * @param empleado
	 * @return
	 */
	public boolean guardar(Empleado empleado) {

		try {

			empDAO.create(empleado);
			System.out.println("Guardando Empleado:" + empleado.toString());
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;

	}

	/**
	 * Metodo para buscar un empleado
	 * 
	 * @param legajo
	 * @return
	 * @throws NonexistentEntityException 
	 */
	public Empleado buscarEmpleado(long legajo) throws NonexistentEntityException {
		
		
		EmpleadoAsalariado empA = new EmpleadoAsalariado();
		// 3- Validar datos
		if (legajo == empA.getLegajo()){
				
			return empDAO.findEmpleado(legajo);
		}
		else 
		
		
		// 4- Llamada al Dao para buscar emp
		empDAO.findEmpleado(legajo);
		
		return empDAO.findEmpleado(legajo);
		
	}

	/**
	 * Metodo para actualizar los datos de un empleado.
	 * 
	 * @param empleado
	 * @return
	 */
	public Empleado editarEmpleado(Empleado empleado) {

		try {
			empDAO.edit(empleado);
			return buscarEmpleado(empleado.getLegajo());
		} catch (NonexistentEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean borrarEmpleado(long legajo) {

		try {
			empDAO.destroy(legajo);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo que devuelve los tipos de empleado para este caso simulamos que
	 * los empleados son obtenidos de la base a partir de un Enum.
	 * 
	 * @return
	 */
	public List<String> getTiposEmpleados() {
		List<String> tipos = new ArrayList<String>();
		tipos.add(TipoEmpleado.EmpleadoAsalarido.name());
		tipos.add(TipoEmpleado.EmpleadoBaseMasComision.name());
		tipos.add(TipoEmpleado.EmpleadoPorComision.name());
		tipos.add(TipoEmpleado.EmpleadoPorHora.name());

		return tipos;
	}

}
