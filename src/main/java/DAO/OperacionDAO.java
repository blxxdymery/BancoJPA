/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Cuenta;
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

    public List<Operacion> seleccionar() {
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        Query q1 = em.createNamedQuery("Operacion.selectOperaciones");
        List<Operacion> listaOperaciones = (List<Operacion>) q1.getResultList();
        em.close();
        return listaOperaciones;
    }

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
    
    public void eliminar(Operacion operacion){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //em.getTransaction().begin();
        tx.begin();
        Operacion operacionEliminar = em.getReference(Operacion.class, operacion.getId());
        log.debug("Objeto a eliminar: " + operacion);
        em.remove(operacionEliminar);
        //em.getTransaction().commit();
        tx.commit();
        log.debug("Objeto eliminado correctamente " + operacion);
        em.close(); 
    }
    
    public List<Operacion> seleccionarTipoOperacion(String dni, String tipo){
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createNamedQuery("Operacion.selectOperacionesTipo");
        q1.setParameter(1, dni);
        q1.setParameter(2, tipo);
        List<Operacion> listaOperacionesCliente = (List<Operacion>) q1.getResultList();
        em.close();
        return listaOperacionesCliente;
    }
}
