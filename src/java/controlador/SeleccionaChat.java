/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.ConversacionJpaController;
import DAO.MensajeJpaController;
import DAO.PreparadorJpaController;
import DAO.SeleccionChatAtletaJpaController;
import DTO.Atleta;
import DTO.Conversacion;
import DTO.Mensaje;
import DTO.Preparador;
import DTO.SeleccionChatAtleta;
import java.util.ArrayList;
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
public class SeleccionaChat {

    private HtmlDataTable tabla;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private List<SeleccionChatAtleta> listaChat;
    private Preparador preparador;

    /**
     * Creates a new instance of SeleccionaChat
     */
    public SeleccionaChat() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        preparador = (Preparador) contexto.getSessionMap().get("usuActivo");
    }

    public Long getMensajesNoLeidos(String nomUsu) {
        AtletaJpaController atlControl = new AtletaJpaController(emf);

        Atleta atl = atlControl.atletaByNomUsuario(nomUsu);
        MensajeJpaController mensajeControl = new MensajeJpaController(emf);

        return mensajeControl.mensajesNoLeidos(atl);
    }

    public List<SeleccionChatAtleta> getListaChat() {
        SeleccionChatAtletaJpaController chatControl = new SeleccionChatAtletaJpaController(emf);
        listaChat = chatControl.atletasByPreparador(preparador.getCodPreparador());
        return listaChat;
    }

    public void setListaChat(List<SeleccionChatAtleta> listaChat) {
        this.listaChat = listaChat;
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
        SeleccionChatAtleta seleccion = (SeleccionChatAtleta) tabla.getRowData();

        ConversacionJpaController controlConversacion = new ConversacionJpaController(emf);
        MensajeJpaController controlMensaje = new MensajeJpaController(emf);
        AtletaJpaController controlAtleta = new AtletaJpaController(emf);
        PreparadorJpaController controlPreparador = new PreparadorJpaController(emf);
        Atleta atl = controlAtleta.findAtleta(seleccion.getCodAtleta());
        Preparador prep = controlPreparador.findPreparador(seleccion.getCodPreparador());

        List<Mensaje> mensaje = controlMensaje.chatByUsuarioByPreparador(atl, prep);
        List<Conversacion> chat = new ArrayList<>();

        for (int i = 0; i < mensaje.size(); i++) {
            Conversacion conversacion = new Conversacion();
            conversacion.setCodAtleta(mensaje.get(i).getCodAtleta().getCodAtleta());
            conversacion.setNomUsuario(mensaje.get(i).getCodAtleta().getNomUsuario());
            conversacion.setFotoPerfil(mensaje.get(i).getCodAtleta().getFotoPerfil());
            conversacion.setCodMensaje(mensaje.get(i).getCodMensaje());
            conversacion.setFechaEnvio(mensaje.get(i).getFechaEnvio());
            conversacion.setEstado(mensaje.get(i).getEstado());
            conversacion.setTexto(mensaje.get(i).getTexto());
            conversacion.setCodPreparador(mensaje.get(i).getCodPreparador().getCodPreparador());
            conversacion.setNomUsuarioPrep(mensaje.get(i).getCodPreparador().getNomUsuario());
            conversacion.setFotoPerfilPreparador(mensaje.get(i).getCodPreparador().getFotoPerfil());
            chat.add(conversacion);
        }
        contexto.getSessionMap().put("conversacion", chat);

        for (int i = 0; i < mensaje.size(); i++) {

            if (mensaje.get(i).getEstado() == '3') {
                Mensaje mensajeEdit = new Mensaje();
                mensajeEdit.setCodAtleta(mensaje.get(i).getCodAtleta());
                mensajeEdit.setCodMensaje(mensaje.get(i).getCodMensaje());
                mensajeEdit.setCodPreparador(mensaje.get(i).getCodPreparador());
                mensajeEdit.setEstado('4');
                mensajeEdit.setFechaEnvio(mensaje.get(i).getFechaEnvio());
                mensajeEdit.setTexto(mensaje.get(i).getTexto());
                try {
                    controlMensaje.edit(mensajeEdit);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return "ok";
    }

}
