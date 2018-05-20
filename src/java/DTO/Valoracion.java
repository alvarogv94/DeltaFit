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
@Table(name = "valoracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valoracion.findAll", query = "SELECT v FROM Valoracion v"),
    @NamedQuery(name = "Valoracion.findByCodValoracion", query = "SELECT v FROM Valoracion v WHERE v.codValoracion = :codValoracion"),
    @NamedQuery(name = "Valoracion.findByValoracion", query = "SELECT v FROM Valoracion v WHERE v.valoracion = :valoracion")})
public class Valoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_valoracion")
    private Integer codValoracion;
    @Column(name = "valoracion")
    private Integer valoracion;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;
    @JoinColumn(name = "cod_preparador", referencedColumnName = "cod_preparador")
    @ManyToOne
    private Preparador codPreparador;

    public Valoracion() {
    }

    public Valoracion(Integer codValoracion) {
        this.codValoracion = codValoracion;
    }

    public Integer getCodValoracion() {
        return codValoracion;
    }

    public void setCodValoracion(Integer codValoracion) {
        this.codValoracion = codValoracion;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
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
        hash += (codValoracion != null ? codValoracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.codValoracion == null && other.codValoracion != null) || (this.codValoracion != null && !this.codValoracion.equals(other.codValoracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Valoracion[ codValoracion=" + codValoracion + " ]";
    }
    
}
