/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.PreparadorJpaController;
import DTO.Preparador;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class ModificaDatosPreparador {

    private Preparador preparador;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private String titulacion;
    private String experiencia;
    private String sobreTi;

    /**
     * Creates a new instance of ModificaDatosPreparador
     */
    public ModificaDatosPreparador() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        preparador = (Preparador) contexto.getSessionMap().get("usuActivo");
    }

    public String getSobreTi() {
        return sobreTi;
    }

    public void setSobreTi(String sobreTi) {
        this.sobreTi = sobreTi;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public Preparador getPreparador() {
        return preparador;
    }

    public void setPreparador(Preparador preparador) {
        this.preparador = preparador;
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

    public void addTitulacion() {
        PreparadorJpaController controlPrep = new PreparadorJpaController(emf);
        String titulacionActual = preparador.getTitulacion();
        String nuevaTitulacion = titulacionActual + " . " + titulacion;
        preparador.setTitulacion(nuevaTitulacion);

        try {
            controlPrep.edit(preparador);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addExperiencia() {
        PreparadorJpaController controlPrep = new PreparadorJpaController(emf);
        String experienciaActual = preparador.getExperiencia();
        String nuevaExperiencia = experienciaActual + " . " + experiencia;
        preparador.setTitulacion(nuevaExperiencia);

        try {
            controlPrep.edit(preparador);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addSobreTi() {
        PreparadorJpaController controlPrep = new PreparadorJpaController(emf);
        preparador.setSobreTi(sobreTi);

        try {
            controlPrep.edit(preparador);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
