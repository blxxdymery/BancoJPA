/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Operacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author marolt
 */
public class OperacionDAO {

    static Logger log = LogManager.getRootLogger();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientePU");
    CuentaDAO cuentaDao = new CuentaDAO();

    public OperacionDAO() {
    }

    /**
     * Método que aplica la query select de las operaciones de la BD
     *
     * @return lista de todas las operaciones
     */
    public List<Operacion> seleccionar() {
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        Query q1 = em.createNamedQuery("Operacion.selectOperaciones");
        List<Operacion> listaOperaciones = (List<Operacion>) q1.getResultList();
        em.close();
        return listaOperaciones;
    }

    /**
     * Método que aplica la query de insert operacion a la BD
     *
     * @param operacion objeto operacion a insertar
     */
    public void insertar(Operacion operacion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        log.debug("Objeto a persistir: " + operacion);
        em.persist(operacion);
        tx.commit();
        log.debug("Objeto persistido correctamente " + operacion);
        em.close();
        //ewDao.update(ewallet, producto, cantidad);
    }

    /**
     * Método que aplica la query delete de una operación de la BD.
     *
     * @param operacion la operacion a eliminar
     */
    public void eliminar(Operacion operacion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Operacion operacionEliminar = em.getReference(Operacion.class, operacion.getId());
        log.debug("Objeto a eliminar: " + operacion);
        em.remove(operacionEliminar);
        tx.commit();
        log.debug("Objeto eliminado correctamente " + operacion);
        em.close();
    }

    /**
     * Método que aplica la query 4 que devuelve la lista de operaciones de un
     * tipo concreto de un cliente en específico.
     *
     * @param dni dni del cliente
     * @param tipo tipo de operacion
     * @return devuelve la lista de operaciones de ese tipo
     */
    public List<Operacion> seleccionarTipoOperacion(String dni, String tipo) {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createNamedQuery("Operacion.selectOperacionesTipo");
        q1.setParameter(1, dni);
        q1.setParameter(2, tipo);
        List<Operacion> listaOperacionesCliente = (List<Operacion>) q1.getResultList();
        em.close();
        return listaOperacionesCliente;
    }
}
