/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "peso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peso.findAll", query = "SELECT p FROM Peso p"),
    @NamedQuery(name = "Peso.findByCodPeso", query = "SELECT p FROM Peso p WHERE p.pesoPK.codPeso = :codPeso"),
    @NamedQuery(name = "Peso.findByPeso", query = "SELECT p FROM Peso p WHERE p.peso = :peso"),
    @NamedQuery(name = "Peso.findByMes", query = "SELECT p FROM Peso p WHERE p.mes = :mes"),
    @NamedQuery(name = "Peso.ultCodPesoAtleta", query = "SELECT count(p) FROM Peso p WHERE p.pesoPK.codAtleta = :codAtleta"),
    @NamedQuery(name = "Peso.findByCodAtleta", query = "SELECT p FROM Peso p WHERE p.pesoPK.codAtleta = :codAtleta")})
public class Peso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PesoPK pesoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "mes")
    private Integer mes;
    @JoinColumn(name = "cod_atleta", referencedColumnName = "cod_atleta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Atleta atleta;

    public Peso() {
    }

    public Peso(PesoPK pesoPK) {
        this.pesoPK = pesoPK;
    }

    public Peso(int codPeso, int codAtleta) {
        this.pesoPK = new PesoPK(codPeso, codAtleta);
    }

    public PesoPK getPesoPK() {
        return pesoPK;
    }

    public void setPesoPK(PesoPK pesoPK) {
        this.pesoPK = pesoPK;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pesoPK != null ? pesoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peso)) {
            return false;
        }
        Peso other = (Peso) object;
        if ((this.pesoPK == null && other.pesoPK != null) || (this.pesoPK != null && !this.pesoPK.equals(other.pesoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Peso[ pesoPK=" + pesoPK + " ]";
    }

}
