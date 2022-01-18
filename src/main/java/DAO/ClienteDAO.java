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
public class ClienteDAO {
    static Logger log = LogManager.getRootLogger();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientePU");
    CuentaDAO cuentaDAO = new CuentaDAO();
    Cuenta cuenta;

    public ClienteDAO() {
    }

    /**
     * Método que aplica la query select de los clientes de la BD
     * @return lista de todos los clientes
     */
    public List<Cliente> seleccionar() {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createNamedQuery("Cliente.selectCliente");
        List<Cliente> listaClientes = (List<Cliente>) q1.getResultList();
        em.close();
        return listaClientes;
    }

    /**
     * Método que aplica la query de insert cliente a la BD
     * @param cliente objeto cliente a insertar
     */
    public void insertar(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        cuenta = new Cuenta(cliente.getDni());
        tx.begin();
        log.debug("Objeto a persistir: " + cliente + cuenta);
        em.persist(cliente);
        em.persist(cuenta);
        tx.commit();
        log.debug("Objeto persistido correctamente " + cliente + cuenta);
        em.close();
    }
    
    /**
     * Método que aplica la query delete de un cliente de la BD. Elimina on
     * cascade todas sus cuentas y operaciones adyacentes.
     *
     * @param cliente el cliente a eliminar
     */
    public void eliminar(Cliente cliente){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Cliente clienteEliminar = em.getReference(Cliente.class, cliente.getDni());
        log.debug("Objeto a eliminar: " + cliente);
        em.remove(clienteEliminar);
        tx.commit();
        log.debug("Objeto eliminado correctamente " + cliente);
        log.debug("Cuentas y operaciones del cliente eliminadas correctamente");
        em.close(); 
    }

    /**
     * Método de la query número 3 que lista las cuentas con más de cierta cantidad de saldo.
     * @param saldo cantidad de dinero
     * @return devuelve la lista de nombres de clientes con cuentas con saldo mayor al param saldo
     */
    public List<String> saldoMayor(double saldo) {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createNamedQuery("Cliente.clientesSaldoMayor");
        q1.setParameter(1, saldo);
        List<String> listaNombres = (List<String>) q1.getResultList();
        em.close();
        return listaNombres;
    }
}
