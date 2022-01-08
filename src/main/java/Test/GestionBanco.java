package Test;

import DAO.ClienteDAO;
import DAO.CuentaDAO;
import DAO.InteresadoDAO;
import DAO.OperacionDAO;
import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.Interesado;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class GestionBanco {
    public static void main(String[] args){
        ClienteDAO clidao = new ClienteDAO();
        CuentaDAO cudao = new CuentaDAO();
        InteresadoDAO intdao = new InteresadoDAO();
        OperacionDAO opdao = new OperacionDAO();
        
        List<Cliente> listaClientes = clidao.seleccionar();
        List<Cuenta> listaCuentas = cudao.seleccionar();
        List<Interesado> listaInteresados = intdao.seleccionar();
        
        /* GESTION DE FECHAS
        //GET FECHA ACTUAL
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String date1 = dtf.format(localDate);
        java.sql.Date fechaActual = java.sql.Date.valueOf(date1);
        */
        
        //INSERT
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String sFecha = "1989-01-12";
        sFecha = simpleDateFormat.format(date);
        java.sql.Date fechaN = java.sql.Date.valueOf(sFecha); 

        
        /*Interesado interesado;
        interesado = new Interesado("56783566T", "Sergio Gomez", "serggomez@gmail.com", "Quiero probar su banco");
        intdao.insertar(interesado);
        
        Cliente cli;
        cli = new Cliente("56783566T", "Sergio Gomez", "serggomez@gmail.com", "Quiero probar su banco", "c/ Madrid, 2", fechaN);
        Entidad.Cliente.generarUser(cli);
        Entidad.Cliente.generarPassword(cli);
        clidao.insertar(cli);
        */
        
        //DELETE
        for (int i = 0; i < listaInteresados.size(); i++) {
            if (listaInteresados.get(i).getDni().equals("54367325G")) {
                intdao.eliminar(listaInteresados.get(i));
            }
        }
        
        
        
        
        /*
        
        
        
        
        //SELECT
        ListIterator it = listaClientes.listIterator();
        while(it.hasNext())
            System.out.println(it.next());
        it = listaCuentas.listIterator();
        while(it.hasNext())
            System.out.println(it.next());
        
        //RESULTADOS QUERYS
        //  query 1
        List<Interesado> listaNoClientes = intdao.seleccionarNoCliente();
        it = listaNoClientes.listIterator();
        while(it.hasNext())
            System.out.println(it.next());
        
        //  query 2
        Cliente cliente = null;
        String dni = "21795002H";
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getDni().equals(dni)) {
                cliente = listaClientes.get(i);
            }
        }
        double saldoMedio = cudao.mediaSaldoCliente(cliente);
        //VA MAL DA 0
        System.out.println("Saldo medio del cliente "+cliente.getNombre()+": "+saldoMedio);
        
        
        /*
        //  query 3
        double saldo = 1000;
        List<String> listaClientesSaldoMayor = clidao.saldoMayor(saldo);
        it = listaClientesSaldoMayor.listIterator();
        while(it.hasNext())
            System.out.println(it.next());
        */
        
        
    }
}