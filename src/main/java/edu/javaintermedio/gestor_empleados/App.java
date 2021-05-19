package edu.javaintermedio.gestor_empleados;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.javaintermedio.gestor_empleados.daos.EmpleadoDAO;
import edu.javaintermedio.gestor_empleados.daos.exceptions.PreexistingEntityException;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoAsalariado;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoBaseMasComision;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoPorComision;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoPorHoras;

/**
 * @author Felipe Herrera
 *
 */
public class App {

    public static void main(String[] args) {

        //----->EntityManagerFctory Base de datos Mysql<-----
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("up_mysql");

        //----EntityManager de la base de datos h2------
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("up_h2");

        // Instanciamos un EmpleadoDAO
        EmpleadoDAO empDAO = new EmpleadoDAO(emf);

        // Empleado Asalariado
        EmpleadoAsalariado empAsalariado = new EmpleadoAsalariado(1589, "Roberto", "Gomez", "123-889", 15000);
        System.out.println("\nGuardando Empleado... " + empAsalariado.toString());

        // Empleado Por Hora
        EmpleadoPorHoras empHoras = new EmpleadoPorHoras(1587, "Jorgelina", "Rodriguez", "158-99-2", 70000, 15);
        System.out.println("\nGuardando Empleado... " + empHoras.toString());

        // EmpleadoPorComision
        EmpleadoPorComision empPorComision = new EmpleadoPorComision(301,
                "Karen", "Price", "444-565-88", 50000, .06);
        System.out.println("\nGuardando Empleado... "
                + empPorComision.toString());

        // Empleado Base Mas Comision
        EmpleadoBaseMasComision empBaseMasComision = new EmpleadoBaseMasComision(1689,
                "Felipe", "Herrera", "222-445-67", 20.5, 60000, 45000);
        System.out.println("\nGuardando Empleado... "
                + empBaseMasComision.toString());

        try {
            // Se crea EmpleadoAsalariado en la Base de datos
            empDAO.create(empAsalariado);
            // Se crea el EmpleadoPorHoras en la Base de datos
            empDAO.create(empHoras);
            // Se crea el EmpleadoPorComision en la Base de datos
            empDAO.create(empPorComision);
            // Se crea el EmpleadoBAseMasComision
            empDAO.create(empBaseMasComision);

        } catch (PreexistingEntityException e) {
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}
