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
@Table(name = "ejercicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByCodEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.codEjercicio = :codEjercicio"),
    @NamedQuery(name = "Ejercicio.findByNombre", query = "SELECT e FROM Ejercicio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejercicio.findByImagen", query = "SELECT e FROM Ejercicio e WHERE e.imagen = :imagen"),
    @NamedQuery(name = "Ejercicio.findByMusculo", query = "SELECT e FROM Ejercicio e WHERE e.nombreMusculo = :nombreMusculo"),
    @NamedQuery(name = "Ejercicio.findByDescripcion", query = "SELECT e FROM Ejercicio e WHERE e.descripcion = :descripcion")})
public class Ejercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_ejercicio")
    private Integer codEjercicio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "nombre_musculo", referencedColumnName = "nombre_musculo")
    @ManyToOne
    private Musculo nombreMusculo;

    public Ejercicio() {
    }

    public Ejercicio(Integer codEjercicio) {
        this.codEjercicio = codEjercicio;
    }

    public Integer getCodEjercicio() {
        return codEjercicio;
    }

    public void setCodEjercicio(Integer codEjercicio) {
        this.codEjercicio = codEjercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Musculo getNombreMusculo() {
        return nombreMusculo;
    }

    public void setNombreMusculo(Musculo nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEjercicio != null ? codEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.codEjercicio == null && other.codEjercicio != null) || (this.codEjercicio != null && !this.codEjercicio.equals(other.codEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Ejercicio[ codEjercicio=" + codEjercicio + " ]";
    }
    
}
