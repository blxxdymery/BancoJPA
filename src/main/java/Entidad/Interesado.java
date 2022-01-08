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
@Entity(name="interesados")
//@NamedQuery(name = "Cliente.insertCliente",query="INSERT c INTO clientes c (c.dni, c.nombre, c.apellidos, c.fechaNacimiento, c.email) VALUES (:?1,:?2,:?3,:?4,:?5)")


@NamedQueries({
    //QUERY 1: Nombre de todos clientes potenciales que finalmente no se han hecho clientes
    @NamedQuery(name = "Interesado.noCliente", query = "select i.nombre from interesados i where i.nombre not in (select c.nombre from clientes c)"),
    @NamedQuery(name = "Interesado.selectInteresado", query = "SELECT i FROM interesados i")
    
})
@Table (name="interesados")
public class Interesado implements Serializable{
    private static final long SerialVersionUID=1L;
    @Id
    private String dni;
    private String nombre;
    private String email;
    private String motivo;

    /*@OneToMany
    @PrimaryKeyJoinColumn(name="id")
    private Transaccion transaccion;*/

    public Interesado() {
    }

    public Interesado(String dni, String nombre, String email, String motivo) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.motivo = motivo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "Interesado{" + "dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", motivo=" + motivo + '}';
    } 
}
