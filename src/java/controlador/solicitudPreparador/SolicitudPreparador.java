/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.solicitudPreparador;

import DAO.DeporteJpaController;
import DAO.PreparadorSeleccionJpaController;
import DTO.Deporte;
import DTO.PreparadorSeleccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class SolicitudPreparador {

    private PreparadorSeleccion solicitud;
    private EntityManagerFactory emf;
    private String resultadoAlta;
    private String clase;
    private ArrayList<String> listaDeporte;

    /**
     * Creates a new instance of SolicitudPreparador
     */
    public SolicitudPreparador() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        solicitud = new PreparadorSeleccion();
        listaDeporte = new ArrayList<>();
        DeporteJpaController deporteControl = new DeporteJpaController(emf);
        List<Deporte> listaDeportes = deporteControl.findDeporteEntities();
        for (int i = 0; i < listaDeportes.size(); i++) {
            listaDeporte.add(listaDeportes.get(i).getNombre());
        }
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getResultadoAlta() {
        return resultadoAlta;
    }

    public void setResultadoAlta(String resultadoAlta) {
        this.resultadoAlta = resultadoAlta;
    }

    public PreparadorSeleccion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(PreparadorSeleccion solicitud) {
        this.solicitud = solicitud;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ArrayList<String> getListaDeporte() {
        return listaDeporte;
    }

    public void setListaDeporte(ArrayList<String> listaDeporte) {
        this.listaDeporte = listaDeporte;
    }
    
    public String registro() {

        String resultado;
        PreparadorSeleccionJpaController prepSeleccionControl = new PreparadorSeleccionJpaController(emf);

        try {
            prepSeleccionControl.create(solicitud);
            resultado = "ok";
            resultadoAlta = "La solicitud se hizo correctamente.";
            clase = "ok";
        } catch (Exception e) {
            resultado = "no";
            clase = "no";
            resultadoAlta = "Hubo un error al realizar la solicitud, inténtelo de nuevo o más tarde.";
        }
        return resultado;
    }

}
