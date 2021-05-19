package edu.javaintermedio.gestor_empleados.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.javaintermedio.gestor_empleados.daos.exceptions.NonexistentEntityException;
import edu.javaintermedio.gestor_empleados.daos.exceptions.PreexistingEntityException;
import edu.javaintermedio.gestor_empleados.entities.Empleado;
import edu.javaintermedio.gestor_empleados.entities.EmpleadoAsalariado;

/**
 *
 * @author Felipe Herrera
 */
public class EmpleadoDAO implements Serializable {

    private Connection con = null;

    private static final long serialVersionUID = 1L;

    public EmpleadoDAO(EntityManagerFactory emf) {

        this.emf = emf;

        // ----> CONEXION MYSQL <----
//        String DRIVERJDBC = "com.mysql.jdbc.Driver";
//        String URL = "jdbc:mysql://localhost:3306/empdb?serverTimezone=UTC";
//        String USER = "root";
//        String PASS = "root";

        // ---> CONEXION H2 <---
        String URL_h2 = "jdbc:h2:mem:test";
        String USER_h2 = "sa";
        String PASS_h2 = "";

        try {
            con = DriverManager.getConnection(URL_h2, USER_h2, PASS_h2);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Metodo para persistir un empleado en la base de datos.A nivel SQL seria
     * un INSERT
     *
     * @param empleado
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Empleado empleado) throws PreexistingEntityException, Exception { // este metodo create espera un objeto empleado
        EntityManager em = null;

        try {
            em = getEntityManager(); // Cuando se ejecuta este metodo, llama al EntityManager
            em.getTransaction().begin(); // a su vez crea una Transaccion 
            em.persist(empleado);   // llama al metodo persistir del EntityManager pasandole un obj. empleado
            em.getTransaction().commit();  // Si se ejecuta correctamente hacemos el commit, caso contrario lanzara la excepcion
        } catch (Exception ex) {
            if (findEmpleado(empleado.getLegajo()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Metodo para actualizar datos de un empleado, a nivel SQL seria un UPDATE
     *
     * @param empleado
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            empleado = em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = empleado.getLegajo();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Borra un registro de empleado en base de datos, a nivel de SQL seria un
     * DELETE
     *
     * @param id
     * @throws NonexistentEntityException
     */
    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getLegajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

//    public List<Empleado> findEmpleadoEntities() {
//        return findEmpleadoEntities(true, -1, -1);
//    }
//
//    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
//        return findEmpleadoEntities(false, maxResults, firstResult);
//    }
//
//    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Empleado.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }
    
    public Empleado findEmpleado(long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();

        try (Statement stmt = con.createStatement()) {

            String query = "SELECT * FROM empleados WHERE legajo = " + id;
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                return null;
            }
            //long legajo, String nombre, String apellido, String ssn, double salarioSemanal
            return (new EmpleadoAsalariado(rs.getLong("LEGAJO"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDO"),
                    rs.getString("SSN"),
                    rs.getDouble("SALRAIO_SEMANAL")));
        } catch (SQLException se) {
//            se.printStackTrace();
            throw new NonexistentEntityException("Error finding employee in DAO", se);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
