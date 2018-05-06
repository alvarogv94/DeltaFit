/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "musculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musculo.findAll", query = "SELECT m FROM Musculo m"),
    @NamedQuery(name = "Musculo.findByNombreMusculo", query = "SELECT m FROM Musculo m WHERE m.nombreMusculo = :nombreMusculo")})
public class Musculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_musculo")
    private String nombreMusculo;
    @OneToMany(mappedBy = "nombreMusculo")
    private List<Ejercicio> ejercicioList;

    public Musculo() {
    }

    public Musculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }

    public String getNombreMusculo() {
        return nombreMusculo;
    }

    public void setNombreMusculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }

    @XmlTransient
    public List<Ejercicio> getEjercicioList() {
        return ejercicioList;
    }

    public void setEjercicioList(List<Ejercicio> ejercicioList) {
        this.ejercicioList = ejercicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreMusculo != null ? nombreMusculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musculo)) {
            return false;
        }
        Musculo other = (Musculo) object;
        if ((this.nombreMusculo == null && other.nombreMusculo != null) || (this.nombreMusculo != null && !this.nombreMusculo.equals(other.nombreMusculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Musculo[ nombreMusculo=" + nombreMusculo + " ]";
    }
    
}
