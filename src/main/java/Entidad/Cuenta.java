/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.persistence.*;

@Entity(name="cuentas")

@NamedQueries({
    @NamedQuery(name = "Cuenta.selectCuentas",query="SELECT c FROM cuentas c"),
    //QUERY 2 = Saldo medio de las cuentas para un cliente determinado
    @NamedQuery(name = "Cuenta.mediaSaldoCliente", query = "SELECT AVG(c.saldoActual) FROM cuentas c WHERE c.dni_cliente=?1") 
})
@Table (name="cuentas")
public class Cuenta implements Serializable{
    private static final long SerialVersionUID=1L;
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String numero;
    private String alias;
    private double saldoActual;
    private double saldoRetenido;
    private double saldoMinimo;
    private String dni_cliente;
    
    //MUCHAS CUENTAS A 1 CLIENTE
    @ManyToOne(targetEntity=Entidad.Cliente.class, cascade = CascadeType.PERSIST) //, mappedBy = "ewallets"
    @PrimaryKeyJoinColumn(name="dni_cliente")
    private Cliente titular;

    public Cuenta() {
    }

    public Cuenta(String dni_cliente) {
        numero = generarNumero();
        alias = "cuentaPrincipal";
        saldoActual = 10;
        saldoMinimo = 5;
        this.dni_cliente = dni_cliente;
    }

    public Cuenta(String alias, double saldoActual, String dni_cliente) {
        numero = generarNumero();
        this.alias = alias;
        this.saldoActual = saldoActual;
        saldoRetenido = 0;
        saldoMinimo = 5;
        this.dni_cliente = dni_cliente;
    }
    
    

    public Cuenta(String alias, double saldoActual, double saldoRetenido, double saldoMinimo, String dni_cliente) {
        numero = generarNumero();
        this.alias = alias;
        this.saldoActual = saldoActual;
        this.saldoRetenido = saldoRetenido;
        this.saldoMinimo = saldoMinimo;
        this.dni_cliente = dni_cliente;
    }

    public String getNumero() {
        return numero;
    }

    public String getAlias() {
        return alias;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getSaldoRetenido() {
        return saldoRetenido;
    }

    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public void setSaldoRetenido(double saldoRetenido) {
        this.saldoRetenido = saldoRetenido;
    }

    public void setSaldoMinimo(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numero=" + numero + ", alias=" + alias + ", saldoActual=" + saldoActual + ", saldoRetenido=" + saldoRetenido + ", saldoMinimo=" + saldoMinimo + ", dni_cliente=" + dni_cliente + '}';
    }
    
    //HACER UN METODO PARA GENERAR UN NUMERO RANDOM DE 20 CIFRAS    
    public String generarNumero() {
        char[] chars = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }
    
}