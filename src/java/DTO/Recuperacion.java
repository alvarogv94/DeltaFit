/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "recuperacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recuperacion.findAll", query = "SELECT r FROM Recuperacion r"),
    @NamedQuery(name = "Recuperacion.findByCodRecuperacion", query = "SELECT r FROM Recuperacion r WHERE r.codRecuperacion = :codRecuperacion"),
    @NamedQuery(name = "Recuperacion.findByFechaProxima", query = "SELECT r FROM Recuperacion r WHERE r.fechaProxima = :fechaProxima"),
    @NamedQuery(name = "Recuperacion.findUltimoPlan", query = "SELECT r FROM Recuperacion r WHERE r.codPreparador = :codPreparador ORDER BY r.codRecuperacion DESC"),
    @NamedQuery(name = "Recuperacion.findByAnotacion", query = "SELECT r FROM Recuperacion r WHERE r.anotacion = :anotacion")})
public class Recuperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_recuperacion")
    private Integer codRecuperacion;
    @Column(name = "fecha_proxima")
    @Temporal(TemporalType.DATE)
    private Date fechaProxima;
    @Column(name = "anotacion")
    private String anotacion;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;
    @JoinColumn(name = "cod_preparador", referencedColumnName = "cod_preparador")
    @ManyToOne
    private Preparador codPreparador;
    @OneToMany(mappedBy = "codRecuperacion")
    private List<RutinaRecuperacion> rutinaRecuperacionList;
    @OneToMany(mappedBy = "codRecuperacion")
    private List<DietaRecuperacion> dietaRecuperacionList;

    public Recuperacion() {
    }

    public Recuperacion(Integer codRecuperacion) {
        this.codRecuperacion = codRecuperacion;
    }

    public Integer getCodRecuperacion() {
        return codRecuperacion;
    }

    public void setCodRecuperacion(Integer codRecuperacion) {
        this.codRecuperacion = codRecuperacion;
    }

    public Date getFechaProxima() {
        return fechaProxima;
    }

    public void setFechaProxima(Date fechaProxima) {
        this.fechaProxima = fechaProxima;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public Atleta getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(Atleta codAtleta) {
        this.codAtleta = codAtleta;
    }

    public Preparador getCodPreparador() {
        return codPreparador;
    }

    public void setCodPreparador(Preparador codPreparador) {
        this.codPreparador = codPreparador;
    }

    @XmlTransient
    public List<RutinaRecuperacion> getRutinaRecuperacionList() {
        return rutinaRecuperacionList;
    }

    public void setRutinaRecuperacionList(List<RutinaRecuperacion> rutinaRecuperacionList) {
        this.rutinaRecuperacionList = rutinaRecuperacionList;
    }

    @XmlTransient
    public List<DietaRecuperacion> getDietaRecuperacionList() {
        return dietaRecuperacionList;
    }

    public void setDietaRecuperacionList(List<DietaRecuperacion> dietaRecuperacionList) {
        this.dietaRecuperacionList = dietaRecuperacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRecuperacion != null ? codRecuperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recuperacion)) {
            return false;
        }
        Recuperacion other = (Recuperacion) object;
        if ((this.codRecuperacion == null && other.codRecuperacion != null) || (this.codRecuperacion != null && !this.codRecuperacion.equals(other.codRecuperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Recuperacion[ codRecuperacion=" + codRecuperacion + " ]";
    }
    
}
