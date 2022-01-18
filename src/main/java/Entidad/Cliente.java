/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author marolt
 */
@Entity(name = "clientes")
@NamedQueries({
    //@NamedQuery(name = "Cliente.insertCliente",query="INSERT c INTO clientes c (c.dni, c.nombre, c.apellidos, c.fechaNacimiento, c.email) VALUES (:?1,:?2,:?3,:?4,:?5)")
    @NamedQuery(name = "Cliente.selectCliente", query = "SELECT c FROM clientes c"),
    //QUERY 3: Nombre de todos los clientes con cuentas que tienen un saldo mayor a uno determinado
    @NamedQuery(name = "Cliente.clientesSaldoMayor", query = "SELECT distinct concat(cli.nombre, ' ', cli.apellidos) FROM clientes cli INNER JOIN cuentas cue ON cli.dni = cue.dni_cliente WHERE cue.saldoActual > ?1")
})

@Table(name = "clientes")
public class Cliente implements Serializable { //extends Interesado ???? @id ene el otro

    private static final long SerialVersionUID = 1L;
    @Id
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String motivo;
    private String direccion;
    private Date fechaNacimiento;
    private String user;
    private String password;

    /*
    //COMPROBAR
    @OneToMany(cascade=CascadeType.REMOVE)
    private Cuenta cuenta;
     */
    public Cliente() {
    }

    public Cliente(String dni, String nombre, String apellidos, String email, String motivo, String direccion, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.motivo = motivo;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        user = "";
        password = "";
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Cliente{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", motivo=" + motivo + ", direccion=" + direccion + ", fechaNacimiento=" + fechaNacimiento + ", user=" + user + ", password=" + password + '}';
    }

    /**
     * Método que genera un usuario con las primeras 3 letras del nombre y los
     * primeros 3 números del dni
     *
     * @param cliente el cliente al que se le generara el user
     */
    public static void generarUser(Cliente cliente) {
        String user;
        user = cliente.getNombre().substring(0, 3) + cliente.getDni().substring(0, 3);
        cliente.setUser(user);
    }

    /**
     * Método que genera una contraseña numérica aletoria
     *
     * @param cliente el cliente al que se le generara la contraseña
     */
    public static void generarPassword(Cliente cliente) {
        int num = (int) (Math.random() * 100000000);
        String pass = String.valueOf(num);
        cliente.setPassword(pass);
    }
}
