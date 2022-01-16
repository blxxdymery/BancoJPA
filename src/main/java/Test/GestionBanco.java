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

    public static void main(String[] args) {
        ClienteDAO clidao = new ClienteDAO();
        CuentaDAO cudao = new CuentaDAO();
        InteresadoDAO intdao = new InteresadoDAO();
        OperacionDAO opdao = new OperacionDAO();

        List<Cliente> listaClientes = clidao.seleccionar();
        List<Cuenta> listaCuentas = cudao.seleccionar();
        List<Interesado> listaInteresados = intdao.seleccionar();
        List<Operacion> listaOperaciones = opdao.seleccionar();

        Interesado interesado = null;
        Cliente cliente = null;
        Cuenta cuenta = null;
        Operacion operacion = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        

        //GET FECHA ACTUAL PARA OPERACIONES
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String date1 = dtf.format(localDate);
        java.sql.Date fechaActual = java.sql.Date.valueOf(date1);

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("OBTENCIÓN DE INFORMES DE SU BANCO DE CONFIANZA");
            System.out.println("1. Informe de los clientes potenciales que finalmente no se han hecho clientes");
            System.out.println("2. Saldo medio de las cuentas de un cliente");
            System.out.println("3. Informe de los clientes con cuentas con saldos mayores a X");
            System.out.println("4. Informe de las operaciones de un tipo concreto realizadas por un cliente");
            System.out.println("5. OPERACIONES DE SU BANCO DE CONFIANZA");
            System.out.println("6. Salir");
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
                    String dni = "";

                    System.out.println("-LISTADO DE CLIENTES:");
                    it = listaClientes.listIterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    System.out.println("Introduzca el dni del cliente de interés:");
                    dni = teclado.nextLine();

                    for (int i = 0; i < listaClientes.size(); i++) {
                        if (listaClientes.get(i).getDni().equals(dni)) {
                            cliente = listaClientes.get(i);
                        }
                    }
                    double saldoMedio = cudao.mediaSaldoCliente(dni);
                    //VA MAL DA 0
                    System.out.println("*Saldo medio del cliente " + cliente.getNombre() + ": " + saldoMedio);
                    System.out.println();
                    break;
                case 3:
                    //  Se repite el mismo cliente
                    double saldo = 0;
                    System.out.println("Introduce la cantidad de saldo:");
                    saldo = teclado.nextDouble();
                    teclado.nextLine();
                    List<String> listaClientesSaldoMayor = clidao.saldoMayor(saldo);

                    System.out.println("*Listado de clientes con saldo mayor a " + saldo + ":");
                    it = listaClientesSaldoMayor.listIterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("-LISTADO DE CLIENTES:");
                    it = listaClientes.listIterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }

                    System.out.println("Introduzca el dni del cliente de interés:");
                    dni = teclado.nextLine();
                    System.out.println("Introduzca el tipo de operación (compra con tarjeta, reintegro, cargo en cuenta, abono):");
                    String oper = teclado.nextLine();

                    List<Operacion> listaOpCliente = opdao.seleccionarTipoOperacion(dni, oper);
                    System.out.println("*Listado de operaciones de " + oper + " del cliente:");
                    it = listaOpCliente.listIterator();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    System.out.println();
                    break;
                case 5:
                    int op = 0;
                    do {
                        System.out.println("-INSERTAR");
                        System.out.println("1. Cliente potencial");
                        System.out.println("2. Cliente");
                        System.out.println("3. Cuenta");
                        System.out.println("4. Operacion");
                        System.out.println("-BORRAR");
                        System.out.println("5. Cliente potencial");
                        System.out.println("6. Cliente");
                        System.out.println("7. Cuenta");
                        System.out.println("8. Operacion");
                        System.out.println("-9. SALIR");
                        op = teclado.nextInt();
                        teclado.nextLine();
                        switch (op) {
                            case 1: //si va
                                interesado = new Interesado();
                                System.out.println("Dni:");
                                interesado.setDni(teclado.nextLine());
                                System.out.println("Nombre:");
                                interesado.setNombre(teclado.nextLine());
                                System.out.println("Email:");
                                interesado.setEmail(teclado.nextLine());
                                System.out.println("Motivo:");
                                interesado.setMotivo(teclado.nextLine());
                                intdao.insertar(interesado);
                                break;
                            case 2:
                                //HACER SUBMENU X SI EXISTE EN CLENTES POTENCIALES
                                cliente = new Cliente();
                                System.out.println("Dni:");
                                cliente.setDni(teclado.nextLine());
                                System.out.println("Nombre:");
                                cliente.setNombre(teclado.nextLine());
                                System.out.println("Email:");
                                cliente.setEmail(teclado.nextLine());
                                System.out.println("Motivo:");
                                cliente.setMotivo(teclado.nextLine());
                                System.out.println("Dirección:");
                                cliente.setDireccion(teclado.nextLine());
                                System.out.println("Fecha de nacimiento(yyyy-mm-dd):");
                                //ERROR EN FECHA: SE PONE FECHA ACTUAL                                
                                Date date = new Date();
                                String fechaNac = teclado.nextLine();
                                fechaNac = simpleDateFormat.format(date);
                                java.sql.Date fechaN = java.sql.Date.valueOf(fechaNac);
                                cliente.setFechaNacimiento(fechaN);
                                Entidad.Cliente.generarUser(cliente);
                                Entidad.Cliente.generarPassword(cliente);
                                clidao.insertar(cliente);
                                break;
                            case 3:
                                System.out.println("INSERTAR UNA CUENTA PARA UN CLIENTE");
                                System.out.println("-LISTADO DE CLIENTES:");
                                it = listaClientes.listIterator();
                                while (it.hasNext()) {
                                    System.out.println(it.next());
                                }
                                System.out.println("Introduzca el dni del cliente de interés:");
                                dni = teclado.nextLine();
                                
                                System.out.println("Alias:");
                                String alias = teclado.nextLine();
                                System.out.println("Saldo inicial:");
                                Double saldoInicial = teclado.nextDouble(); 
                                teclado.nextLine();
                                cuenta = new Cuenta(alias, saldoInicial, dni); 
                                cudao.insertar(cuenta);
                                break;
                            case 4:
                                System.out.println("-LISTADO DE CLIENTES:");
                                it = listaClientes.listIterator();
                                while (it.hasNext()) {
                                    System.out.println(it.next());
                                }
                                System.out.println("Introduzca el dni del cliente de interés:");
                                dni = teclado.nextLine();
                                
                                List<Cuenta> listaCuentasCliente = cudao.seleccionarCuentasCliente(dni);
                                it = listaCuentasCliente.listIterator();
                                while (it.hasNext()) {
                                    System.out.println(it.next());
                                }
                                System.out.println("Introduzca numero de la cuenta de interés:");
                                String numero = teclado.nextLine();

                                System.out.println("Tipo de operacion(compra con tarjeta, reintegro, cargo en cuenta, abono):");
                                String tipo = teclado.nextLine();
                                System.out.println("Cantidad:");
                                double cantidad = teclado.nextDouble(); teclado.nextLine();
                                operacion = new Operacion(fechaActual, tipo, cantidad, numero);
                                opdao.insertar(operacion);
                                break;
                            case 5:
                                for (int i = 0; i < listaInteresados.size(); i++) {
                                    if (listaInteresados.get(i).getDni().equals("54367325G")) {
                                        intdao.eliminar(listaInteresados.get(i));
                                    }
                                }
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;

                        }
                    } while (op != 9);
            }
        } while (opcion != 6);
    }
}
