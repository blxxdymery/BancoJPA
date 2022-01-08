/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.CuentaDAO.log;
import Entidad.Cliente;
import Entidad.Cuenta;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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

    public List<Cliente> seleccionar() {
        EntityManager em = emf.createEntityManager();
        //EntityTransaction tx = em.getTransaction();
        Query q1 = em.createNamedQuery("Cliente.selectCliente");
        List<Cliente> listaClientes = (List<Cliente>) q1.getResultList();
        em.close();
        return listaClientes;
    }

    public void insertar(Cliente cliente) {
        //solo 1 commit para la creación de cliente y ewallet
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
    
    //IGUAL NO HACE FALTA TANTO LIO CON EL CASCADE
    public void eliminar(Cliente cliente, List<Cuenta> listaCuentas){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        List<Cuenta> cuentasCliente = new ArrayList<>();;
        String dniCliente = cliente.getDni();
        
        tx.begin();
        Cliente clienteEliminar = em.getReference(Cliente.class, cliente.getDni());
        Cuenta cuentaEliminar;
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getDni_cliente().equals(dniCliente)) {
                cuentaEliminar = em.getReference(Cuenta.class, listaCuentas.get(i).getNumero());
                cuentasCliente.add(cuentaEliminar);
            }
        }
        log.debug("Objeto a eliminar: " + cliente);
        System.out.println("Procedemos a la eliminación de sus cuentas...");
        ListIterator it = cuentasCliente.listIterator();
        while(it.hasNext()){
            em.remove(it.next());
        }
        System.out.println("Cuentas eliminadas correctamente");
        System.out.println("Procedemos a la eliminación del cliente");
        em.remove(clienteEliminar);
        tx.commit();
        log.debug("Objeto eliminado correctamente " + cuenta);
        em.close(); 
    }
    
    public void eliminarCascade(Cliente cliente){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //em.getTransaction().begin();
        tx.begin();
        Cliente clienteEliminar = em.getReference(Cliente.class, cliente.getDni());
        log.debug("Objeto a eliminar: " + cliente);
        em.remove(clienteEliminar);
        //em.getTransaction().commit();
        tx.commit();
        log.debug("Objeto eliminado correctamente " + cliente + " sus cuentas");
        em.close(); 
    }
    
    //DA ERROR EL PARAMETRO ?????????
    public List<String> saldoMayor(double saldo) {
        EntityManager em = emf.createEntityManager();
        Query q2 = em.createNamedQuery("Cliente.clientesSaldoMayor");
        List<String> listaNombres = (List<String>) q2.getResultList();
        em.close();
        return listaNombres;
    }
}
