/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "imagen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i"),
    @NamedQuery(name = "Imagen.findByCodImagen", query = "SELECT i FROM Imagen i WHERE i.codImagen = :codImagen"),
    @NamedQuery(name = "Imagen.findByNombreImagen", query = "SELECT i FROM Imagen i WHERE i.nombreImagen = :nombreImagen"),
    @NamedQuery(name = "Imagen.findByFecha", query = "SELECT i FROM Imagen i WHERE i.fecha = :fecha")})
public class Imagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_imagen")
    private Integer codImagen;
    @Column(name = "nombre_imagen")
    private String nombreImagen;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;

    public Imagen() {
    }

    public Imagen(Integer codImagen) {
        this.codImagen = codImagen;
    }

    public Integer getCodImagen() {
        return codImagen;
    }

    public void setCodImagen(Integer codImagen) {
        this.codImagen = codImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Atleta getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(Atleta codAtleta) {
        this.codAtleta = codAtleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codImagen != null ? codImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.codImagen == null && other.codImagen != null) || (this.codImagen != null && !this.codImagen.equals(other.codImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Imagen[ codImagen=" + codImagen + " ]";
    }
    
}
