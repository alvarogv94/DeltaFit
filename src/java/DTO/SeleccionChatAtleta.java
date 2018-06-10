/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "seleccion_chat_atleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeleccionChatAtleta.findAll", query = "SELECT s FROM SeleccionChatAtleta s"),
    @NamedQuery(name = "SeleccionChatAtleta.findByCodPreparador", query = "SELECT s FROM SeleccionChatAtleta s WHERE s.codPreparador = :codPreparador"),
    @NamedQuery(name = "SeleccionChatAtleta.findByCodAtleta", query = "SELECT s FROM SeleccionChatAtleta s WHERE s.codAtleta = :codAtleta"),
    @NamedQuery(name = "SeleccionChatAtleta.findByNomUsuario", query = "SELECT s FROM SeleccionChatAtleta s WHERE s.nomUsuario = :nomUsuario"),
    @NamedQuery(name = "SeleccionChatAtleta.findByFotoPerfil", query = "SELECT s FROM SeleccionChatAtleta s WHERE s.fotoPerfil = :fotoPerfil")})
public class SeleccionChatAtleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cod_preparador")
    private Integer codPreparador;
    @Basic(optional = false)
    @Column(name = "cod_atleta")
    private int codAtleta;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    public SeleccionChatAtleta() {
    }

    public Integer getCodPreparador() {
        return codPreparador;
    }

    public void setCodPreparador(Integer codPreparador) {
        this.codPreparador = codPreparador;
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
    
}
