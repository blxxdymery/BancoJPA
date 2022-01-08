/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author marolt
 */
@Entity(name="operaciones")
@Table (name="operaciones")
@NamedQuery(name = "Operacion.selectOperaciones",query="SELECT o FROM operaciones o")
public class Operacion implements Serializable{
    @Id
    private int id;
    private Date fecha;
    private String tipo;
    private String numero_cuenta;
    
    //MUCHAS OPERACIONES EN 1 CUENTA
    @ManyToOne(targetEntity=Entidad.Cuenta.class, cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn(name="numero_cuenta")
    private Cuenta cuenta;

    public Operacion() {
    }

    public Operacion(Date fecha, String tipo, String numero_cuenta) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.numero_cuenta = numero_cuenta;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", fecha=" + fecha + ", tipo=" + tipo + ", numero_cuenta=" + numero_cuenta + '}';
    }  
}
