/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.admin;

import DAO.EjercicioJpaController;
import DAO.MusculoJpaController;
import DTO.Ejercicio;
import DTO.Musculo;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class AddNuevoEjercicio {

    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private List<Musculo> listaMusculos;
    private Ejercicio ejercicio;
    private String error;
    private String clase;
    private String nombreMusculo;

    /**
     * Creates a new instance of AddNuevoEjercicio
     */
    public AddNuevoEjercicio() {

        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        MusculoJpaController musculoControl = new MusculoJpaController(emf);
        listaMusculos = musculoControl.findMusculoEntities();
        ejercicio = new Ejercicio();
    }

    public String getNombreMusculo() {
        return nombreMusculo;
    }

    public void setNombreMusculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }

    public String getError() {
        return error;
    }

    public String getClase() {
        return clase;
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

    public List<Musculo> getListaMusculos() {
        return listaMusculos;
    }

    public void setListaMusculos(List<Musculo> listaMusculos) {
        this.listaMusculos = listaMusculos;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public void altaEjercicio() {

        EjercicioJpaController ejercicioControl = new EjercicioJpaController(emf);
        MusculoJpaController musculoControl = new MusculoJpaController(emf);
        Musculo musc = musculoControl.findMusculo(nombreMusculo);
        ejercicio.setImagen("");
        ejercicio.setNombreMusculo(musc);
        
        try {
            ejercicioControl.create(ejercicio);
            error = "Alta del nuevo ejercicio correcta.";
            clase = "ok";

        } catch (Exception ex) {
            error = "Error al dar de alta el nuevo ejercicio.";
            clase = "no";
        }
    }
}
