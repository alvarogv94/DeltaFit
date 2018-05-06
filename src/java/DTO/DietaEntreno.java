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
@Table(name = "dieta_entreno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DietaEntreno.findAll", query = "SELECT d FROM DietaEntreno d"),
    @NamedQuery(name = "DietaEntreno.findByCodDietaEntreno", query = "SELECT d FROM DietaEntreno d WHERE d.codDietaEntreno = :codDietaEntreno"),
    @NamedQuery(name = "DietaEntreno.findByDesayuno", query = "SELECT d FROM DietaEntreno d WHERE d.desayuno = :desayuno"),
    @NamedQuery(name = "DietaEntreno.findByMediaMa\u00f1ana", query = "SELECT d FROM DietaEntreno d WHERE d.mediaMa\u00f1ana = :mediaMa\u00f1ana"),
    @NamedQuery(name = "DietaEntreno.findByAlmuerzo", query = "SELECT d FROM DietaEntreno d WHERE d.almuerzo = :almuerzo"),
    @NamedQuery(name = "DietaEntreno.findByPreEntreno", query = "SELECT d FROM DietaEntreno d WHERE d.preEntreno = :preEntreno"),
    @NamedQuery(name = "DietaEntreno.findByPostEntreno", query = "SELECT d FROM DietaEntreno d WHERE d.postEntreno = :postEntreno"),
    @NamedQuery(name = "DietaEntreno.findByCena", query = "SELECT d FROM DietaEntreno d WHERE d.cena = :cena")})
public class DietaEntreno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_dieta_entreno")
    private Integer codDietaEntreno;
    @Column(name = "desayuno")
    private String desayuno;
    @Column(name = "media_ma\u00f1ana")
    private String mediaMañana;
    @Column(name = "almuerzo")
    private String almuerzo;
    @Column(name = "pre_entreno")
    private String preEntreno;
    @Column(name = "post_entreno")
    private String postEntreno;
    @Column(name = "cena")
    private String cena;
    @JoinColumn(name = "cod_entreno", referencedColumnName = "cod_entreno")
    @ManyToOne
    private Entreno codEntreno;

    public DietaEntreno() {
    }

    public DietaEntreno(Integer codDietaEntreno) {
        this.codDietaEntreno = codDietaEntreno;
    }

    public Integer getCodDietaEntreno() {
        return codDietaEntreno;
    }

    public void setCodDietaEntreno(Integer codDietaEntreno) {
        this.codDietaEntreno = codDietaEntreno;
    }

    public String getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(String desayuno) {
        this.desayuno = desayuno;
    }

    public String getMediaMañana() {
        return mediaMañana;
    }

    public void setMediaMañana(String mediaMañana) {
        this.mediaMañana = mediaMañana;
    }

    public String getAlmuerzo() {
        return almuerzo;
    }

    public void setAlmuerzo(String almuerzo) {
        this.almuerzo = almuerzo;
    }

    public String getPreEntreno() {
        return preEntreno;
    }

    public void setPreEntreno(String preEntreno) {
        this.preEntreno = preEntreno;
    }

    public String getPostEntreno() {
        return postEntreno;
    }

    public void setPostEntreno(String postEntreno) {
        this.postEntreno = postEntreno;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public Entreno getCodEntreno() {
        return codEntreno;
    }

    public void setCodEntreno(Entreno codEntreno) {
        this.codEntreno = codEntreno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDietaEntreno != null ? codDietaEntreno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DietaEntreno)) {
            return false;
        }
        DietaEntreno other = (DietaEntreno) object;
        if ((this.codDietaEntreno == null && other.codDietaEntreno != null) || (this.codDietaEntreno != null && !this.codDietaEntreno.equals(other.codDietaEntreno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DietaEntreno[ codDietaEntreno=" + codDietaEntreno + " ]";
    }
    
}
