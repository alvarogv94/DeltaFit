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
@Table(name = "rutina_plan_pred")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutinaPlanPred.findAll", query = "SELECT r FROM RutinaPlanPred r"),
    @NamedQuery(name = "RutinaPlanPred.findByCodRutinaPlanPred", query = "SELECT r FROM RutinaPlanPred r WHERE r.codRutinaPlanPred = :codRutinaPlanPred"),
    @NamedQuery(name = "RutinaPlanPred.findByDia", query = "SELECT r FROM RutinaPlanPred r WHERE r.dia = :dia"),
    @NamedQuery(name = "RutinaPlanPred.findByEjercicio", query = "SELECT r FROM RutinaPlanPred r WHERE r.ejercicio = :ejercicio"),
    @NamedQuery(name = "RutinaPlanPred.findByAnotacion", query = "SELECT r FROM RutinaPlanPred r WHERE r.anotacion = :anotacion"),
    @NamedQuery(name = "RutinaPlanPred.findByOrden", query = "SELECT r FROM RutinaPlanPred r WHERE r.orden = :orden")})
public class RutinaPlanPred implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_rutina_plan_pred")
    private Integer codRutinaPlanPred;
    @Column(name = "dia")
    private Integer dia;
    @Column(name = "ejercicio")
    private String ejercicio;
    @Column(name = "anotacion")
    private String anotacion;
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "cod_plan_pred", referencedColumnName = "cod_plan_pred")
    @ManyToOne
    private PlanPred codPlanPred;

    public RutinaPlanPred() {
    }

    public RutinaPlanPred(Integer codRutinaPlanPred) {
        this.codRutinaPlanPred = codRutinaPlanPred;
    }

    public Integer getCodRutinaPlanPred() {
        return codRutinaPlanPred;
    }

    public void setCodRutinaPlanPred(Integer codRutinaPlanPred) {
        this.codRutinaPlanPred = codRutinaPlanPred;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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
        hash += (codRutinaPlanPred != null ? codRutinaPlanPred.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutinaPlanPred)) {
            return false;
        }
        RutinaPlanPred other = (RutinaPlanPred) object;
        if ((this.codRutinaPlanPred == null && other.codRutinaPlanPred != null) || (this.codRutinaPlanPred != null && !this.codRutinaPlanPred.equals(other.codRutinaPlanPred))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.RutinaPlanPred[ codRutinaPlanPred=" + codRutinaPlanPred + " ]";
    }
    
}
