/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Cuenta;
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
public class CuentaDAO {

    static Logger log = LogManager.getRootLogger();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientePU");

    public CuentaDAO() {
    }

    /**
     * Método que aplica la query select de las cuentas de la BD
     *
     * @return lista de todas las cuentas
     */
    public List<Cuenta> seleccionar() {
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        Query q1 = em.createNamedQuery("Cuenta.selectCuentas");
        List<Cuenta> listaCuentas = (List<Cuenta>) q1.getResultList();
        em.close();
        return listaCuentas;
    }

    /**
     * Método que aplica una query select de las cuentas de un Cliente concreto
     *
     * @return lista de las cuentas del cliente
     */
    public List<Cuenta> seleccionarCuentasCliente(String dni) {
        EntityManager em = emf.createEntityManager();
        Query q2 = em.createNamedQuery("Cuenta.selectCuentasCliente");
        q2.setParameter(1, dni);
        List<Cuenta> listaCuentas = (List<Cuenta>) q2.getResultList();
        em.close();
        return listaCuentas;
    }

    /**
     * Método que aplica la query de insert cuenta a la BD
     *
     * @param cuenta objeto cuenta a insertar
     */
    public void insertar(Cuenta cuenta) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        log.debug("Objeto a persistir: " + cuenta);
        em.persist(cuenta);
        tx.commit();
        log.debug("Objeto persistido correctamente " + cuenta);
        em.close();
    }

    /**
     * Método que aplica la query delete de una cuenta de la BD. Elimina on
     * cascade todas sus operaciones adyacentes.
     *
     * @param cuenta la cuenta a eliminar
     */
    public void eliminar(Cuenta cuenta) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Cuenta cuentaEliminar = em.getReference(Cuenta.class, cuenta.getNumero());
        log.debug("Objeto a eliminar: " + cuenta);
        em.remove(cuentaEliminar);
        tx.commit();
        log.debug("Objeto eliminado correctamente " + cuenta);
        log.debug("Operaciones de la cuenta eliminadas correctamente");
        em.close();
    }

    /**
     * Método de la query 2 que saca el saldo medio del saldo de todas las
     * cuentas de un cliente
     *
     * @param dni dni del cliente de las cuentas
     * @return devuelve un double con el saldo medio
     */
    public double mediaSaldoCliente(String dni) {
        EntityManager em = emf.createEntityManager();
        Query q3 = em.createNamedQuery("Cuenta.mediaSaldoCliente");
        q3.setParameter(1, dni);
        double saldoMedio = (double) q3.getSingleResult();
        em.close();
        return saldoMedio;
    }
}
