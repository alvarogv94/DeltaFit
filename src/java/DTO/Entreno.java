/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.text.DateFormat;
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
@Table(name = "entreno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entreno.findAll", query = "SELECT e FROM Entreno e"),
    @NamedQuery(name = "Entreno.findByCodEntreno", query = "SELECT e FROM Entreno e WHERE e.codEntreno = :codEntreno"),
    @NamedQuery(name = "Entreno.findByFechaProxima", query = "SELECT e FROM Entreno e WHERE e.fechaProxima = :fechaProxima"),
    @NamedQuery(name = "Entreno.findUltimoPlan", query = "SELECT e FROM Entreno e WHERE e.codPreparador = :codPreparador ORDER BY e.codEntreno DESC"),
    @NamedQuery(name = "Entreno.findByAnotacion", query = "SELECT e FROM Entreno e WHERE e.anotacion = :anotacion")})
public class Entreno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_entreno")
    private Integer codEntreno;
    @Column(name = "fecha_proxima")
    @Temporal(TemporalType.DATE)
    private Date fechaProxima;
    @Column(name = "anotacion")
    private String anotacion;
    @OneToMany(mappedBy = "codEntreno")
    private List<DietaEntreno> dietaEntrenoList;
    @OneToMany(mappedBy = "codEntreno")
    private List<RutinaEntreno> rutinaEntrenoList;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;
    @JoinColumn(name = "cod_preparador", referencedColumnName = "cod_preparador")
    @ManyToOne
    private Preparador codPreparador;

    public Entreno() {
    }

    public Entreno(Integer codEntreno) {
        this.codEntreno = codEntreno;
    }

    public Integer getCodEntreno() {
        return codEntreno;
    }

    public void setCodEntreno(Integer codEntreno) {
        this.codEntreno = codEntreno;
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

    @XmlTransient
    public List<DietaEntreno> getDietaEntrenoList() {
        return dietaEntrenoList;
    }

    public void setDietaEntrenoList(List<DietaEntreno> dietaEntrenoList) {
        this.dietaEntrenoList = dietaEntrenoList;
    }

    @XmlTransient
    public List<RutinaEntreno> getRutinaEntrenoList() {
        return rutinaEntrenoList;
    }

    public void setRutinaEntrenoList(List<RutinaEntreno> rutinaEntrenoList) {
        this.rutinaEntrenoList = rutinaEntrenoList;
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
        hash += (codEntreno != null ? codEntreno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entreno)) {
            return false;
        }
        Entreno other = (Entreno) object;
        if ((this.codEntreno == null && other.codEntreno != null) || (this.codEntreno != null && !this.codEntreno.equals(other.codEntreno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Entreno[ codEntreno=" + codEntreno + " ]";
    }

    public String getFech() {

        if (fechaProxima != null) {
            DateFormat dfDateMedium = DateFormat.getDateInstance(DateFormat.MEDIUM);
            return dfDateMedium.format(fechaProxima);
        } else {
            return "";
        }

    }

}
