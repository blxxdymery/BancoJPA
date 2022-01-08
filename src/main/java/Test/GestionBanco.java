package Test;

import DAO.ClienteDAO;
import DAO.CuentaDAO;
import DAO.InteresadoDAO;
import DAO.OperacionDAO;
import Entidad.Cliente;
import Entidad.Cuenta;
import Entidad.Interesado;
import Entidad.Operacion;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class GestionBanco {
    public static void main(String[] args){
        ClienteDAO clidao = new ClienteDAO();
        CuentaDAO cudao = new CuentaDAO();
        InteresadoDAO intdao = new InteresadoDAO();
        OperacionDAO opdao = new OperacionDAO();
        
        List<Cliente> listaClientes = clidao.seleccionar();
        List<Cuenta> listaCuentas = cudao.seleccionar();
        List<Interesado> listaInteresados = intdao.seleccionar();
        List<Operacion> listaOperaciones = opdao.seleccionar();

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        do{
            System.out.println("OBTENCIÓN DE INFORMES DE SU BANCO DE CONFIANZA");
            System.out.println("1. Informe de los clientes potenciales que finalmente no se han hecho clientes");
            System.out.println("2. Saldo medio de las cuentas de un cliente");
            System.out.println("3. Informe de los clientes con cuentas con saldos mayores a X");
            System.out.println("4. Informe de las operaciones de un tipo concreto realizadas por un cliente");
            System.out.println("5. Salir");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    //  query 1
                    List<Interesado> listaNoClientes = intdao.seleccionarNoCliente();
                    System.out.println("*Listado de interesados que no se han hecho clientes:");
                    ListIterator it = listaNoClientes.listIterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    System.out.println();
                    break;
                case 2:
                    //  query 2
                    Cliente cliente = null;
                    String dni = "";
                    
                    System.out.println("-LISTADO DE CLIENTES:");
                    it = listaClientes.listIterator();
                    while (it.hasNext())
                        System.out.println(it.next());
                    System.out.println("Introduzca el dni del cliente de interés:");
                    dni = teclado.nextLine();
                    
                    for (int i = 0; i < listaClientes.size(); i++) {
                        if (listaClientes.get(i).getDni().equals(dni)) {
                            cliente = listaClientes.get(i);
                            double saldoMedio = cudao.mediaSaldoCliente(cliente);
                            //VA MAL DA 0
                            System.out.println("*Saldo medio del cliente " + cliente.getNombre() + ": " + saldoMedio);

                            //tirar excepcion si no??
                        }
                    }
                    System.out.println();
                    break;
                case 3:
                    //  query 3 DA ERROR
                    double saldo = 0;
                    System.out.println("Introduce la cantidad de saldo:");
                    saldo = teclado.nextDouble();
                    teclado.nextLine();
                    List<String> listaClientesSaldoMayor = clidao.saldoMayor(saldo);
                    
                    System.out.println("*Listado de clientes con saldo mayor a "+saldo+":");
                    it = listaClientesSaldoMayor.listIterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    break;
            }
        }while(opcion!=5);
        

        
        //GET FECHA ACTUAL PARA OPERACIONES
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String date1 = dtf.format(localDate);
        java.sql.Date fechaActual = java.sql.Date.valueOf(date1);
        
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
     
        
        //DELETE
        for (int i = 0; i < listaInteresados.size(); i++) {
            if (listaInteresados.get(i).getDni().equals("54367325G")) {
                intdao.eliminar(listaInteresados.get(i));
            }
        }
     
        //SELECT
        ListIterator it = listaClientes.listIterator();
        while(it.hasNext())
            System.out.println(it.next());
        it = listaCuentas.listIterator();
        while(it.hasNext())
            System.out.println(it.next());
            */
    }
}