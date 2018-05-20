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
@Table(name = "rutina_recuperacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutinaRecuperacion.findAll", query = "SELECT r FROM RutinaRecuperacion r"),
    @NamedQuery(name = "RutinaRecuperacion.findByCodRutinaRecuperacion", query = "SELECT r FROM RutinaRecuperacion r WHERE r.codRutinaRecuperacion = :codRutinaRecuperacion"),
    @NamedQuery(name = "RutinaRecuperacion.findByDia", query = "SELECT r FROM RutinaRecuperacion r WHERE r.dia = :dia"),
    @NamedQuery(name = "RutinaRecuperacion.findByEjercicio", query = "SELECT r FROM RutinaRecuperacion r WHERE r.ejercicio = :ejercicio"),
    @NamedQuery(name = "RutinaRecuperacion.findByAnotacion", query = "SELECT r FROM RutinaRecuperacion r WHERE r.anotacion = :anotacion"),
    @NamedQuery(name = "RutinaRecuperacion.findByOrden", query = "SELECT r FROM RutinaRecuperacion r WHERE r.orden = :orden")})
public class RutinaRecuperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_rutina_recuperacion")
    private Integer codRutinaRecuperacion;
    @Column(name = "dia")
    private Integer dia;
    @Column(name = "ejercicio")
    private String ejercicio;
    @Column(name = "anotacion")
    private String anotacion;
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "cod_recuperacion", referencedColumnName = "cod_recuperacion")
    @ManyToOne
    private Recuperacion codRecuperacion;

    public RutinaRecuperacion() {
    }

    public RutinaRecuperacion(Integer codRutinaRecuperacion) {
        this.codRutinaRecuperacion = codRutinaRecuperacion;
    }

    public Integer getCodRutinaRecuperacion() {
        return codRutinaRecuperacion;
    }

    public void setCodRutinaRecuperacion(Integer codRutinaRecuperacion) {
        this.codRutinaRecuperacion = codRutinaRecuperacion;
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

    public Recuperacion getCodRecuperacion() {
        return codRecuperacion;
    }

    public void setCodRecuperacion(Recuperacion codRecuperacion) {
        this.codRecuperacion = codRecuperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRutinaRecuperacion != null ? codRutinaRecuperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutinaRecuperacion)) {
            return false;
        }
        RutinaRecuperacion other = (RutinaRecuperacion) object;
        if ((this.codRutinaRecuperacion == null && other.codRutinaRecuperacion != null) || (this.codRutinaRecuperacion != null && !this.codRutinaRecuperacion.equals(other.codRutinaRecuperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.RutinaRecuperacion[ codRutinaRecuperacion=" + codRutinaRecuperacion + " ]";
    }
    
}
