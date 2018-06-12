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
@Table(name = "dieta_plan_pred")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DietaPlanPred.findAll", query = "SELECT d FROM DietaPlanPred d"),
    @NamedQuery(name = "DietaPlanPred.findByEntreno", query = "SELECT d FROM DietaPlanPred d WHERE d.codPlanPred = :codPlanPred"),
    @NamedQuery(name = "DietaPlanPred.findByCodDietaPlanPred", query = "SELECT d FROM DietaPlanPred d WHERE d.codDietaPlanPred = :codDietaPlanPred"),
    @NamedQuery(name = "DietaPlanPred.findByDesayuno", query = "SELECT d FROM DietaPlanPred d WHERE d.desayuno = :desayuno"),
    @NamedQuery(name = "DietaPlanPred.findByMediaManhana", query = "SELECT d FROM DietaPlanPred d WHERE d.mediaManhana = :mediaManhana"),
    @NamedQuery(name = "DietaPlanPred.findByAlmuerzo", query = "SELECT d FROM DietaPlanPred d WHERE d.almuerzo = :almuerzo"),
    @NamedQuery(name = "DietaPlanPred.findByPreEntreno", query = "SELECT d FROM DietaPlanPred d WHERE d.preEntreno = :preEntreno"),
    @NamedQuery(name = "DietaPlanPred.findByPostEntreno", query = "SELECT d FROM DietaPlanPred d WHERE d.postEntreno = :postEntreno"),
    @NamedQuery(name = "DietaPlanPred.findByCena", query = "SELECT d FROM DietaPlanPred d WHERE d.cena = :cena")})
public class DietaPlanPred implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_dieta_plan_pred")
    private Integer codDietaPlanPred;
    @Column(name = "desayuno")
    private String desayuno;
    @Column(name = "media_manhana")
    private String mediaManhana;
    @Column(name = "almuerzo")
    private String almuerzo;
    @Column(name = "pre_entreno")
    private String preEntreno;
    @Column(name = "post_entreno")
    private String postEntreno;
    @Column(name = "cena")
    private String cena;
    @JoinColumn(name = "cod_plan_pred", referencedColumnName = "cod_plan_pred")
    @ManyToOne
    private PlanPred codPlanPred;

    public DietaPlanPred() {
    }

    public DietaPlanPred(Integer codDietaPlanPred) {
        this.codDietaPlanPred = codDietaPlanPred;
    }

    public Integer getCodDietaPlanPred() {
        return codDietaPlanPred;
    }

    public void setCodDietaPlanPred(Integer codDietaPlanPred) {
        this.codDietaPlanPred = codDietaPlanPred;
    }

    public String getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(String desayuno) {
        this.desayuno = desayuno;
    }

    public String getMediaManhana() {
        return mediaManhana;
    }

    public void setMediaManhana(String mediaManhana) {
        this.mediaManhana = mediaManhana;
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

    public PlanPred getCodPlanPred() {
        return codPlanPred;
    }

    public void setCodPlanPred(PlanPred codPlanPred) {
        this.codPlanPred = codPlanPred;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDietaPlanPred != null ? codDietaPlanPred.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DietaPlanPred)) {
            return false;
        }
        DietaPlanPred other = (DietaPlanPred) object;
        if ((this.codDietaPlanPred == null && other.codDietaPlanPred != null) || (this.codDietaPlanPred != null && !this.codDietaPlanPred.equals(other.codDietaPlanPred))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DietaPlanPred[ codDietaPlanPred=" + codDietaPlanPred + " ]";
    }
    
}
