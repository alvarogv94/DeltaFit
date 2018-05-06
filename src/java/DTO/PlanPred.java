/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "plan_pred")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanPred.findAll", query = "SELECT p FROM PlanPred p"),
    @NamedQuery(name = "PlanPred.findByCodPlanPred", query = "SELECT p FROM PlanPred p WHERE p.codPlanPred = :codPlanPred"),
    @NamedQuery(name = "PlanPred.findByDeporte", query = "SELECT p FROM PlanPred p WHERE p.deporte = :deporte"),
    @NamedQuery(name = "PlanPred.findByObjetivo", query = "SELECT p FROM PlanPred p WHERE p.objetivo = :objetivo")})
public class PlanPred implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_plan_pred")
    private Integer codPlanPred;
    @Column(name = "deporte")
    private String deporte;
    @Column(name = "objetivo")
    private String objetivo;
    @OneToMany(mappedBy = "codPlanPred")
    private List<RutinaPlanPred> rutinaPlanPredList;
    @OneToMany(mappedBy = "codPlanPred")
    private List<DietaPlanPred> dietaPlanPredList;

    public PlanPred() {
    }

    public PlanPred(Integer codPlanPred) {
        this.codPlanPred = codPlanPred;
    }

    public Integer getCodPlanPred() {
        return codPlanPred;
    }

    public void setCodPlanPred(Integer codPlanPred) {
        this.codPlanPred = codPlanPred;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @XmlTransient
    public List<RutinaPlanPred> getRutinaPlanPredList() {
        return rutinaPlanPredList;
    }

    public void setRutinaPlanPredList(List<RutinaPlanPred> rutinaPlanPredList) {
        this.rutinaPlanPredList = rutinaPlanPredList;
    }

    @XmlTransient
    public List<DietaPlanPred> getDietaPlanPredList() {
        return dietaPlanPredList;
    }

    public void setDietaPlanPredList(List<DietaPlanPred> dietaPlanPredList) {
        this.dietaPlanPredList = dietaPlanPredList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPlanPred != null ? codPlanPred.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanPred)) {
            return false;
        }
        PlanPred other = (PlanPred) object;
        if ((this.codPlanPred == null && other.codPlanPred != null) || (this.codPlanPred != null && !this.codPlanPred.equals(other.codPlanPred))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.PlanPred[ codPlanPred=" + codPlanPred + " ]";
    }
    
}
