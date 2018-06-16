/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Atleta;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class ModificaDatosAtleta {

    private Atleta atleta;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private String lesion;

    /**
     * Creates a new instance of ModificaDatosAtleta
     */
    public ModificaDatosAtleta() {

        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = (Atleta) contexto.getSessionMap().get("usuActivo");
        if (atleta.getLesionSi() == 0) {
            lesion = "No estás Lesionado";
        } else if (atleta.getLesionSi() == 1) {
            lesion = "Estás Lesionado";
        }
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
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

    public String getLesion() {
        return lesion;
    }

    public void setLesion(String lesion) {
        this.lesion = lesion;
    }

    public void cambiaLesion() {

        AtletaJpaController atletaControl = new AtletaJpaController(emf);

        //Atleta atl = atletaControl.findAtleta(atleta.getCodAtleta());
        Integer estado = 0;
        if (atleta.getLesionSi() == 0) {
            estado = 1;
            atleta.setLesionSi(estado);
            lesion = "Ahora estás Lesionado";
        }else if (atleta.getLesionSi() == 1) {
            atleta.setLesionSi(estado);
            lesion = "No estás Lesionado";
        }

        try {
            atletaControl.edit(atleta);
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
