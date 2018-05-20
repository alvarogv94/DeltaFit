/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Alvaro
 */
@Embeddable
public class PesoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cod_peso")
    private int codPeso;
    @Basic(optional = false)
    @Column(name = "cod_atleta")
    private int codAtleta;

    public PesoPK() {
    }

    public PesoPK(int codPeso, int codAtleta) {
        this.codPeso = codPeso;
        this.codAtleta = codAtleta;
    }

    public int getCodPeso() {
        return codPeso;
    }

    public void setCodPeso(int codPeso) {
        this.codPeso = codPeso;
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPeso;
        hash += (int) codAtleta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PesoPK)) {
            return false;
        }
        PesoPK other = (PesoPK) object;
        if (this.codPeso != other.codPeso) {
            return false;
        }
        if (this.codAtleta != other.codAtleta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.PesoPK[ codPeso=" + codPeso + ", codAtleta=" + codAtleta + " ]";
    }
    
}
