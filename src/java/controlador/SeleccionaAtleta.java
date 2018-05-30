/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DTO.Atleta;
import DTO.Preparador;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class SeleccionaAtleta {

    private Preparador prep;
    private Atleta atleta;
    private HtmlDataTable tabla;
    private EntityManagerFactory emf;
    private ExternalContext contexto;

    /**
     * Creates a new instance of SeleccionaAtleta
     */
    public SeleccionaAtleta() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = new Atleta();
        prep = (Preparador) contexto.getSessionMap().get("usuActivo");
    }

    public Preparador getPrep() {
        return prep;
    }

    public void setPrep(Preparador prep) {
        this.prep = prep;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
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

    public String seleccionaAtleta() {
        
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        Atleta atl = (Atleta) tabla.getRowData();
        contexto.getSessionMap().put("atletaPreparacion", atl);

        return "ok";
    }
}
