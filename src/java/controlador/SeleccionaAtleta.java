/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.PreparacionAtletaJpaController;
import DTO.Atleta;
import DTO.PreparacionAtleta;
import DTO.Preparador;
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
public class SeleccionaAtleta {

    private List<PreparacionAtleta> listaAtletas;
    private PreparacionAtletaJpaController listaControl;
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
        listaControl = new PreparacionAtletaJpaController(emf);
        listaAtletas = listaControl.findPreparacionAtletaEntities();
    }

    public List<PreparacionAtleta> getListaAtletas() {
        return listaAtletas;
    }

    public void setListaAtletas(List<PreparacionAtleta> listaAtletas) {
        this.listaAtletas = listaAtletas;
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
        AtletaJpaController atletaControl = new AtletaJpaController(emf);
        PreparacionAtleta atlPrep = (PreparacionAtleta) tabla.getRowData();
        Atleta atl = atletaControl.atletaByNomUsuario(atlPrep.getNomUsuario());
        contexto.getSessionMap().put("atletaPreparacion", atl);

        return "ok";
    }
}
