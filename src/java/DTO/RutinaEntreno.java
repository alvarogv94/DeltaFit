/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "rutina_entreno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutinaEntreno.findAll", query = "SELECT r FROM RutinaEntreno r"),
    @NamedQuery(name = "RutinaEntreno.findByCodRutinaEntreno", query = "SELECT r FROM RutinaEntreno r WHERE r.codRutinaEntreno = :codRutinaEntreno"),
    @NamedQuery(name = "RutinaEntreno.findByDia", query = "SELECT r FROM RutinaEntreno r WHERE r.dia = :dia"),
    @NamedQuery(name = "RutinaEntreno.findByEjercicio", query = "SELECT r FROM RutinaEntreno r WHERE r.ejercicio = :ejercicio"),
    @NamedQuery(name = "RutinaEntreno.findByAnotacion", query = "SELECT r FROM RutinaEntreno r WHERE r.anotacion = :anotacion"),
    @NamedQuery(name = "RutinaEntreno.findByCodEntreno", query = "SELECT r FROM RutinaEntreno r WHERE r.codEntreno = :codEntreno"),
    @NamedQuery(name = "RutinaEntreno.findByOrden", query = "SELECT r FROM RutinaEntreno r WHERE r.orden = :orden")})
public class RutinaEntreno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_rutina_entreno")
    private Integer codRutinaEntreno;
    @Column(name = "dia")
    private Integer dia;
    @Column(name = "ejercicio")
    private String ejercicio;
    @Column(name = "anotacion")
    private String anotacion;
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "cod_entreno", referencedColumnName = "cod_entreno")
    @ManyToOne
    private Entreno codEntreno;

    public RutinaEntreno() {
    }

    public RutinaEntreno(Integer codRutinaEntreno) {
        this.codRutinaEntreno = codRutinaEntreno;
    }

    public Integer getCodRutinaEntreno() {
        return codRutinaEntreno;
    }

    public void setCodRutinaEntreno(Integer codRutinaEntreno) {
        this.codRutinaEntreno = codRutinaEntreno;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Entreno getCodEntreno() {
        return codEntreno;
    }

    public void setCodEntreno(Entreno codEntreno) {
        this.codEntreno = codEntreno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRutinaEntreno != null ? codRutinaEntreno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutinaEntreno)) {
            return false;
        }
        RutinaEntreno other = (RutinaEntreno) object;
        if ((this.codRutinaEntreno == null && other.codRutinaEntreno != null) || (this.codRutinaEntreno != null && !this.codRutinaEntreno.equals(other.codRutinaEntreno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.RutinaEntreno[ codRutinaEntreno=" + codRutinaEntreno + " ]";
    }

}
