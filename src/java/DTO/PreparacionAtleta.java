/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.text.DateFormat;
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
@Table(name = "preparacion_atleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreparacionAtleta.findAll", query = "SELECT p FROM PreparacionAtleta p"),
    @NamedQuery(name = "PreparacionAtleta.findByCodAtleta", query = "SELECT p FROM PreparacionAtleta p WHERE p.codAtleta = :codAtleta"),
    @NamedQuery(name = "PreparacionAtleta.findByNombre", query = "SELECT p FROM PreparacionAtleta p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PreparacionAtleta.findByNomUsuario", query = "SELECT p FROM PreparacionAtleta p WHERE p.nomUsuario = :nomUsuario"),
    @NamedQuery(name = "PreparacionAtleta.findByDeporte", query = "SELECT p FROM PreparacionAtleta p WHERE p.deporte = :deporte"),
    @NamedQuery(name = "PreparacionAtleta.findByObjetivo", query = "SELECT p FROM PreparacionAtleta p WHERE p.objetivo = :objetivo"),
    @NamedQuery(name = "PreparacionAtleta.findByFechUltPago", query = "SELECT p FROM PreparacionAtleta p WHERE p.fechUltPago = :fechUltPago")})
public class PreparacionAtleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_atleta")
    private int codAtleta;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "deporte")
    private String deporte;
    @Column(name = "objetivo")
    private String objetivo;
    @Column(name = "fech_ult_pago")
    @Temporal(TemporalType.DATE)
    private Date fechUltPago;

    public PreparacionAtleta() {
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFechUltPago() {        
        DateFormat dfDateMedium = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return dfDateMedium.format(fechUltPago);
    }

    public void setFechUltPago(Date fechUltPago) {
        this.fechUltPago = fechUltPago;
    }
    
}
