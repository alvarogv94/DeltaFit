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
@Table(name = "visita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v"),
    @NamedQuery(name = "Visita.findByCodVisita", query = "SELECT v FROM Visita v WHERE v.codVisita = :codVisita"),
    @NamedQuery(name = "Visita.findByPais", query = "SELECT v FROM Visita v WHERE v.pais = :pais"),
    @NamedQuery(name = "Visita.findByHoraInicio", query = "SELECT v FROM Visita v WHERE v.horaInicio = :horaInicio"),
    @NamedQuery(name = "Visita.findByHoraFin", query = "SELECT v FROM Visita v WHERE v.horaFin = :horaFin")})
public class Visita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_visita")
    private Integer codVisita;
    @Column(name = "pais")
    private String pais;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.DATE)
    private Date horaInicio;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.DATE)
    private Date horaFin;

    public Visita() {
    }

    public Visita(Integer codVisita) {
        this.codVisita = codVisita;
    }

    public Integer getCodVisita() {
        return codVisita;
    }

    public void setCodVisita(Integer codVisita) {
        this.codVisita = codVisita;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVisita != null ? codVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.codVisita == null && other.codVisita != null) || (this.codVisita != null && !this.codVisita.equals(other.codVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Visita[ codVisita=" + codVisita + " ]";
    }
    
}
