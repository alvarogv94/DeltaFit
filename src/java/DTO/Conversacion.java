/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "conversacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversacion.findAll", query = "SELECT c FROM Conversacion c"),
    @NamedQuery(name = "Conversacion.findByCodAtleta", query = "SELECT c FROM Conversacion c WHERE c.codAtleta = :codAtleta"),
    @NamedQuery(name = "Conversacion.findByNomUsuario", query = "SELECT c FROM Conversacion c WHERE c.nomUsuario = :nomUsuario"),
    @NamedQuery(name = "Conversacion.findByFotoPerfil", query = "SELECT c FROM Conversacion c WHERE c.fotoPerfil = :fotoPerfil"),
    @NamedQuery(name = "Conversacion.findByCodMensaje", query = "SELECT c FROM Conversacion c WHERE c.codMensaje = :codMensaje"),
    @NamedQuery(name = "Conversacion.findByFechaEnvio", query = "SELECT c FROM Conversacion c WHERE c.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "Conversacion.findByEstado", query = "SELECT c FROM Conversacion c WHERE c.estado = :estado"),
    @NamedQuery(name = "Conversacion.findByTexto", query = "SELECT c FROM Conversacion c WHERE c.texto = :texto"),
    @NamedQuery(name = "Conversacion.findByCodPreparador", query = "SELECT c FROM Conversacion c WHERE c.codPreparador = :codPreparador"),
    @NamedQuery(name = "Conversacion.findByNomUsuarioPrep", query = "SELECT c FROM Conversacion c WHERE c.nomUsuarioPrep = :nomUsuarioPrep"),
    @NamedQuery(name = "Conversacion.findByFotoPerfilPreparador", query = "SELECT c FROM Conversacion c WHERE c.fotoPerfilPreparador = :fotoPerfilPreparador")})
public class Conversacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_atleta")
    private int codAtleta;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @Basic(optional = false)
    @Column(name = "cod_mensaje")
    private int codMensaje;
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "estado")
    private Character estado;
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "cod_preparador")
    private int codPreparador;
    @Column(name = "nom_usuario_prep")
    private String nomUsuarioPrep;
    @Column(name = "foto_perfil_preparador")
    private String fotoPerfilPreparador;

    public Conversacion() {
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public int getCodMensaje() {
        return codMensaje;
    }

    public void setCodMensaje(int codMensaje) {
        this.codMensaje = codMensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCodPreparador() {
        return codPreparador;
    }

    public void setCodPreparador(int codPreparador) {
        this.codPreparador = codPreparador;
    }

    public String getNomUsuarioPrep() {
        return nomUsuarioPrep;
    }

    public void setNomUsuarioPrep(String nomUsuarioPrep) {
        this.nomUsuarioPrep = nomUsuarioPrep;
    }

    public String getFotoPerfilPreparador() {
        return fotoPerfilPreparador;
    }

    public void setFotoPerfilPreparador(String fotoPerfilPreparador) {
        this.fotoPerfilPreparador = fotoPerfilPreparador;
    }
    
}
