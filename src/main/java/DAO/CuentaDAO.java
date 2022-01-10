/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Cliente;
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

    public List<Cuenta> seleccionar() {
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        Query q1 = em.createNamedQuery("Cuenta.selectCuentas");
        List<Cuenta> listaCuentas = (List<Cuenta>) q1.getResultList();
        em.close();
        return listaCuentas;
    }

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
    
    public void eliminar(Cuenta cuenta){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //em.getTransaction().begin();
        tx.begin();
        Cuenta cuentaEliminar = em.getReference(Cuenta.class, cuenta.getNumero());
        log.debug("Objeto a eliminar: " + cuenta);
        em.remove(cuentaEliminar);
        //em.getTransaction().commit();
        tx.commit();
        log.debug("Objeto eliminado correctamente " + cuenta);
        em.close(); 
    }
    
    public double mediaSaldoCliente(String dni) {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createNamedQuery("Cuenta.mediaSaldoCliente");
        q1.setParameter(1, dni);
        double saldoMedio = (double) q1.getFirstResult();
        em.close();
        return saldoMedio;
    }
}
