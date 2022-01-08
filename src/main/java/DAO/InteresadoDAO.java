/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.CuentaDAO.log;
import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.Interesado;
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
public class InteresadoDAO {
    static Logger log = LogManager.getRootLogger();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientePU");

    public InteresadoDAO() {
    }

    public List<Interesado> seleccionar() {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createNamedQuery("Interesado.selectInteresado");
        List<Interesado> listaInteresados = (List<Interesado>) q1.getResultList();
        em.close();
        return listaInteresados;
    }

    public void insertar(Interesado interesado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        log.debug("Objeto a persistir: " + interesado);
        em.persist(interesado);
        tx.commit();
        log.debug("Objeto persistido correctamente " + interesado);
        em.close();
    }
    
    public void eliminar(Interesado interesado){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //em.getTransaction().begin();
        tx.begin();
        Interesado interesadoEliminar = em.getReference(Interesado.class, interesado.getDni());
        log.debug("Objeto a eliminar: " + interesado);
        
        em.remove(interesadoEliminar);
        //em.getTransaction().commit();
        tx.commit();
        log.debug("Objeto eliminado correctamente " + interesado);
        em.close(); 
    }
    
    public List<Interesado> seleccionarNoCliente(){
        EntityManager em = emf.createEntityManager();
        Query q2 = em.createNamedQuery("Interesado.noCliente");
        List<Interesado> listaNoClientes = (List<Interesado>) q2.getResultList();
        em.close();
        return listaNoClientes;
    }  
}
