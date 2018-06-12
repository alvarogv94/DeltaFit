/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.PlanPredJpaController;
import DTO.Atleta;
import DTO.PlanPred;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class SeleccionaPlanPredeterminado {

    private HtmlDataTable tabla;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private Atleta atleta;

    /**
     * Creates a new instance of SeleccionaPlanPredeterminadoo
     */
    public SeleccionaPlanPredeterminado() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = (Atleta) contexto.getSessionMap().get("usuActivo");
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ExternalContext getContexto() {
        return contexto;
    }

    public void setContexto(ExternalContext contexto) {
        this.contexto = contexto;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public void cambiaPlan() {
        PlanPred planElegido = (PlanPred) tabla.getRowData();
        atleta.setCodEntrenamiento(planElegido.getCodPlanPred());
        AtletaJpaController controlAtleta = new AtletaJpaController(emf);
        try {
            controlAtleta.edit(atleta);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<PlanPred> getDamePlan() {
        PlanPredJpaController controlPlan = new PlanPredJpaController(emf);
        return controlPlan.findPlanPredEntities();
    }
}
