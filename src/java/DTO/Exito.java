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
@Table(name = "exito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exito.findAll", query = "SELECT e FROM Exito e"),
    @NamedQuery(name = "Exito.findByCodExito", query = "SELECT e FROM Exito e WHERE e.codExito = :codExito"),
    @NamedQuery(name = "Exito.findByExito", query = "SELECT e FROM Exito e WHERE e.exito = :exito"),
    @NamedQuery(name = "Exito.findByDescripcion", query = "SELECT e FROM Exito e WHERE e.descripcion = :descripcion")})
public class Exito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_exito")
    private Integer codExito;
    @Column(name = "exito")
    private Integer exito;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;
    @JoinColumn(name = "cod_preparador", referencedColumnName = "cod_preparador")
    @ManyToOne
    private Preparador codPreparador;

    public Exito() {
    }

    public Exito(Integer codExito) {
        this.codExito = codExito;
    }

    public Integer getCodExito() {
        return codExito;
    }

    public void setCodExito(Integer codExito) {
        this.codExito = codExito;
    }

    public Integer getExito() {
        return exito;
    }

    public void setExito(Integer exito) {
        this.exito = exito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codExito != null ? codExito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exito)) {
            return false;
        }
        Exito other = (Exito) object;
        if ((this.codExito == null && other.codExito != null) || (this.codExito != null && !this.codExito.equals(other.codExito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Exito[ codExito=" + codExito + " ]";
    }
    
}
