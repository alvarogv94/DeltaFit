/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.admin;

import DAO.AtletaJpaController;
import DAO.PreparadorJpaController;
import DAO.PreparadorSeleccionJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Atleta;
import DTO.Preparador;
import DTO.PreparadorSeleccion;
import java.util.Date;
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
public class DatosTablas {

    private HtmlDataTable tabla;
    private EntityManagerFactory emf;
    private ExternalContext contexto;

    /**
     * Creates a new instance of DatosTablas
     */
    public DatosTablas() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
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

    public List<Atleta> dameAtletas() {

        AtletaJpaController controlAtleta = new AtletaJpaController(emf);
        return controlAtleta.findAtletaEntities();
    }

    public List<Preparador> damePreparadores() {

        PreparadorJpaController controlPrep = new PreparadorJpaController(emf);
        List<Preparador> lista = controlPrep.findPreparadorEntities();
        lista.remove(0);
        return lista;
    }
    
    public List<PreparadorSeleccion> dameSeleccion() {
        PreparadorSeleccionJpaController controlSel = new PreparadorSeleccionJpaController(emf);
        return controlSel.findPreparadorSeleccionEntities();
    }

    public void eliminaPreparador() {
        PreparadorJpaController controlPrep = new PreparadorJpaController(emf);
        Preparador preparador = (Preparador) tabla.getRowData();
        try {
            controlPrep.destroy(preparador.getCodPreparador());
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminaAtleta() {
        AtletaJpaController controlAtl = new AtletaJpaController(emf);
        Atleta atleta = (Atleta) tabla.getRowData();
        try {
            controlAtl.destroy(atleta.getCodAtleta());
        } catch (NonexistentEntityException | IllegalOrphanException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void contrataPreparador() {
        PreparadorSeleccionJpaController controlSel = new PreparadorSeleccionJpaController(emf);
        PreparadorJpaController controlPrep = new PreparadorJpaController(emf);
        
        PreparadorSeleccion prep = (PreparadorSeleccion) tabla.getRowData();       

        Preparador preparador = new Preparador();
        preparador.setNombre(prep.getNombre());
        preparador.setApellidos(prep.getApellidos());
        preparador.setLocalidad(prep.getLocalidad());
        preparador.setEdad(prep.getEdad());
        preparador.setSexo(prep.getSexo());
        preparador.setEmail(prep.getEmail());
        preparador.setNomUsuario(prep.getNombre()+"123");
        preparador.setPass(prep.getNombre()+"123");
        preparador.setTitulacion(prep.getTitulacion());
        preparador.setExperiencia(prep.getExperiencia());
        preparador.setEspecialidad(prep.getEspecialidad());
        preparador.setFechIncorporacion(new Date());
        preparador.setFotoPerfil("");
        preparador.setSobreTi(prep.getSobreTi());
        
        try {
            controlSel.destroy(prep.getCodPreparadorSeleccion());
        } catch (NonexistentEntityException ex) {
            System.out.println(ex.getMessage());
        }
     
        controlPrep.create(preparador);
    }
}
