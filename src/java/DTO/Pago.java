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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findByCodPago", query = "SELECT p FROM Pago p WHERE p.codPago = :codPago"),
    @NamedQuery(name = "Pago.findByCodPagoAtleta", query = "SELECT p FROM Pago p WHERE p.codAtleta = :codAtleta ORDER BY P.codPago DESC"),
    @NamedQuery(name = "Pago.findByFechProxPago", query = "SELECT p FROM Pago p WHERE p.fechProxPago = :fechProxPago"),
    @NamedQuery(name = "Pago.findByFechUltPago", query = "SELECT p FROM Pago p WHERE p.fechUltPago = :fechUltPago"),
    @NamedQuery(name = "Pago.findByImporte", query = "SELECT p FROM Pago p WHERE p.importe = :importe")})
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_pago")
    private Integer codPago;
    @Column(name = "fech_prox_pago")
    @Temporal(TemporalType.DATE)
    private Date fechProxPago;
    @Column(name = "fech_ult_pago")
    @Temporal(TemporalType.DATE)
    private Date fechUltPago;
    @Column(name = "importe")
    private Integer importe;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta")
    @ManyToOne
    private Atleta codAtleta;

    public Pago() {
    }

    public Pago(Integer codPago) {
        this.codPago = codPago;
    }

    public Integer getCodPago() {
        return codPago;
    }

    public void setCodPago(Integer codPago) {
        this.codPago = codPago;
    }

    public Date getFechProxPago() {
        return fechProxPago;
    }

    public void setFechProxPago(Date fechProxPago) {
        this.fechProxPago = fechProxPago;
    }

    public Date getFechUltPago() {
        return fechUltPago;
    }

    public void setFechUltPago(Date fechUltPago) {
        this.fechUltPago = fechUltPago;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
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
        hash += (codPago != null ? codPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.codPago == null && other.codPago != null) || (this.codPago != null && !this.codPago.equals(other.codPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Pago[ codPago=" + codPago + " ]";
    }
    
}
