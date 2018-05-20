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
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findByCodMensaje", query = "SELECT m FROM Mensaje m WHERE m.codMensaje = :codMensaje"),
    @NamedQuery(name = "Mensaje.findByFechaEnvio", query = "SELECT m FROM Mensaje m WHERE m.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "Mensaje.findByTexto", query = "SELECT m FROM Mensaje m WHERE m.texto = :texto")})
public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_mensaje")
    private Integer codMensaje;
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "texto")
    private String texto;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;
    @JoinColumn(name = "cod_preparador", referencedColumnName = "cod_preparador")
    @ManyToOne
    private Preparador codPreparador;

    public Mensaje() {
    }

    public Mensaje(Integer codMensaje) {
        this.codMensaje = codMensaje;
    }

    public Integer getCodMensaje() {
        return codMensaje;
    }

    public void setCodMensaje(Integer codMensaje) {
        this.codMensaje = codMensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
        hash += (codMensaje != null ? codMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.codMensaje == null && other.codMensaje != null) || (this.codMensaje != null && !this.codMensaje.equals(other.codMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Mensaje[ codMensaje=" + codMensaje + " ]";
    }

}
