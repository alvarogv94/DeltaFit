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
@Table(name = "dieta_recuperacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DietaRecuperacion.findAll", query = "SELECT d FROM DietaRecuperacion d"),
    @NamedQuery(name = "DietaRecuperacion.findByCodDietaRecuperacion", query = "SELECT d FROM DietaRecuperacion d WHERE d.codDietaRecuperacion = :codDietaRecuperacion"),
    @NamedQuery(name = "DietaRecuperacion.findByDesayuno", query = "SELECT d FROM DietaRecuperacion d WHERE d.desayuno = :desayuno"),
    @NamedQuery(name = "DietaRecuperacion.findByMediaManhana", query = "SELECT d FROM DietaRecuperacion d WHERE d.mediaManhana = :mediaManhana"),
    @NamedQuery(name = "DietaRecuperacion.findByAlmuerzo", query = "SELECT d FROM DietaRecuperacion d WHERE d.almuerzo = :almuerzo"),
    @NamedQuery(name = "DietaRecuperacion.findByCodRecuperacion", query = "SELECT d FROM DietaRecuperacion d WHERE d.codRecuperacion = :codRecuperacion"),
    @NamedQuery(name = "DietaRecuperacion.findByCena", query = "SELECT d FROM DietaRecuperacion d WHERE d.cena = :cena")})
public class DietaRecuperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_dieta_recuperacion")
    private Integer codDietaRecuperacion;
    @Column(name = "desayuno")
    private String desayuno;
    @Column(name = "media_manhana")
    private String mediaManhana;
    @Column(name = "almuerzo")
    private String almuerzo;
    @Column(name = "cena")
    private String cena;
    @JoinColumn(name = "cod_recuperacion", referencedColumnName = "cod_recuperacion")
    @ManyToOne
    private Recuperacion codRecuperacion;

    public DietaRecuperacion() {
    }

    public DietaRecuperacion(Integer codDietaRecuperacion) {
        this.codDietaRecuperacion = codDietaRecuperacion;
    }

    public Integer getCodDietaRecuperacion() {
        return codDietaRecuperacion;
    }

    public void setCodDietaRecuperacion(Integer codDietaRecuperacion) {
        this.codDietaRecuperacion = codDietaRecuperacion;
    }

    public String getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(String desayuno) {
        this.desayuno = desayuno;
    }

    public String getMediaManhana() {
        return mediaManhana;
    }

    public void setMediaManhana(String mediaManhana) {
        this.mediaManhana = mediaManhana;
    }

    public String getAlmuerzo() {
        return almuerzo;
    }

    public void setAlmuerzo(String almuerzo) {
        this.almuerzo = almuerzo;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
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
        hash += (codDietaRecuperacion != null ? codDietaRecuperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DietaRecuperacion)) {
            return false;
        }
        DietaRecuperacion other = (DietaRecuperacion) object;
        if ((this.codDietaRecuperacion == null && other.codDietaRecuperacion != null) || (this.codDietaRecuperacion != null && !this.codDietaRecuperacion.equals(other.codDietaRecuperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DietaRecuperacion[ codDietaRecuperacion=" + codDietaRecuperacion + " ]";
    }
    
}
