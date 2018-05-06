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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "deporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deporte.findAll", query = "SELECT d FROM Deporte d"),
    @NamedQuery(name = "Deporte.findByCodDeporte", query = "SELECT d FROM Deporte d WHERE d.codDeporte = :codDeporte"),
    @NamedQuery(name = "Deporte.findByNombre", query = "SELECT d FROM Deporte d WHERE d.nombre = :nombre")})
public class Deporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_deporte")
    private Integer codDeporte;
    @Column(name = "nombre")
    private String nombre;

    public Deporte() {
    }

    public Deporte(Integer codDeporte) {
        this.codDeporte = codDeporte;
    }

    public Integer getCodDeporte() {
        return codDeporte;
    }

    public void setCodDeporte(Integer codDeporte) {
        this.codDeporte = codDeporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDeporte != null ? codDeporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deporte)) {
            return false;
        }
        Deporte other = (Deporte) object;
        if ((this.codDeporte == null && other.codDeporte != null) || (this.codDeporte != null && !this.codDeporte.equals(other.codDeporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Deporte[ codDeporte=" + codDeporte + " ]";
    }
    
}
