/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.MensajeJpaController;
import DTO.Atleta;
import DTO.Conversacion;
import DTO.Mensaje;
import DTO.Preparador;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class Chat {

    private Mensaje mensaje;
    private Atleta atleta;
    private Preparador preparador;
    private ExternalContext contexto;
    private EntityManagerFactory emf;
    private String tipoUsuario;
    private HtmlDataTable tabla;

    /**
     * Creates a new instance of Chat
     */
    public Chat() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        tipoUsuario = (String) contexto.getSessionMap().get("tipoUsuario");
        if (tipoUsuario.equals("preparador")) {
            preparador = (Preparador) contexto.getSessionMap().get("usuActivo");
        } else {
            atleta = (Atleta) contexto.getSessionMap().get("usuActivo");
        }
        mensaje = new Mensaje();
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Preparador getPreparador() {
        return preparador;
    }

    public void setPreparador(Preparador preparador) {
        this.preparador = preparador;
    }

    public ExternalContext getContexto() {
        return contexto;
    }

    public void setContexto(ExternalContext contexto) {
        this.contexto = contexto;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String mandaMensajeAtleta() {

        MensajeJpaController controlMensaje = new MensajeJpaController(emf);
        Preparador prep = atleta.getCodPreparador();

        mensaje.setFechaEnvio(new Date());
        char estado = '3';
        mensaje.setEstado(estado);
        mensaje.setCodAtleta(atleta);
        mensaje.setCodPreparador(prep);

        controlMensaje.create(mensaje);
        return "ok";
    }

    public String mandaMensajePreparador() {

        ArrayList<Conversacion> conver = (ArrayList<Conversacion>) contexto.getSessionMap().get("conversacion");
        MensajeJpaController controlMensaje = new MensajeJpaController(emf);
        AtletaJpaController controlAtleta = new AtletaJpaController(emf);

        Atleta atl = controlAtleta.atletaByNomUsuario(conver.get(0).getNomUsuario());
        Preparador prep = atl.getCodPreparador();

        Date date = new Date();
        mensaje.setFechaEnvio(date);
        char estado = '1';
        mensaje.setEstado(estado);
        mensaje.setCodAtleta(atl);
        mensaje.setCodPreparador(preparador);
        Conversacion conversacion = new Conversacion();
        conversacion.setCodAtleta(atl.getCodAtleta());
        conversacion.setNomUsuario(atl.getNomUsuario());
        conversacion.setFotoPerfil(atl.getFotoPerfil());
        conversacion.setCodMensaje(0);
        conversacion.setFechaEnvio(date);
        conversacion.setEstado(estado);
        conversacion.setTexto(mensaje.getTexto());
        conversacion.setCodPreparador(preparador.getCodPreparador());
        conversacion.setNomUsuarioPrep(preparador.getNomUsuario());
        conversacion.setFotoPerfilPreparador(preparador.getFotoPerfil());
        conver.add(conversacion);
        controlMensaje.create(mensaje);
        contexto.getSessionMap().put("conversacion",conver);
        return "ok";
    }

}
