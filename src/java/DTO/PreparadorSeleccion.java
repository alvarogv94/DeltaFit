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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "preparador_seleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreparadorSeleccion.findAll", query = "SELECT p FROM PreparadorSeleccion p"),
    @NamedQuery(name = "PreparadorSeleccion.findByCodPreparadorSeleccion", query = "SELECT p FROM PreparadorSeleccion p WHERE p.codPreparadorSeleccion = :codPreparadorSeleccion"),
    @NamedQuery(name = "PreparadorSeleccion.findByNombre", query = "SELECT p FROM PreparadorSeleccion p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PreparadorSeleccion.findByApellidos", query = "SELECT p FROM PreparadorSeleccion p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "PreparadorSeleccion.findByLocalidad", query = "SELECT p FROM PreparadorSeleccion p WHERE p.localidad = :localidad"),
    @NamedQuery(name = "PreparadorSeleccion.findByEdad", query = "SELECT p FROM PreparadorSeleccion p WHERE p.edad = :edad"),
    @NamedQuery(name = "PreparadorSeleccion.findBySexo", query = "SELECT p FROM PreparadorSeleccion p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "PreparadorSeleccion.findByEmail", query = "SELECT p FROM PreparadorSeleccion p WHERE p.email = :email"),
    @NamedQuery(name = "PreparadorSeleccion.findByTitulacion", query = "SELECT p FROM PreparadorSeleccion p WHERE p.titulacion = :titulacion"),
    @NamedQuery(name = "PreparadorSeleccion.findByEspecialidad", query = "SELECT p FROM PreparadorSeleccion p WHERE p.especialidad = :especialidad"),
    @NamedQuery(name = "PreparadorSeleccion.findByExperiencia", query = "SELECT p FROM PreparadorSeleccion p WHERE p.experiencia = :experiencia"),
    @NamedQuery(name = "PreparadorSeleccion.findBySobreTi", query = "SELECT p FROM PreparadorSeleccion p WHERE p.sobreTi = :sobreTi")})
public class PreparadorSeleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_preparador_seleccion")
    private Integer codPreparadorSeleccion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "sexo")
    private Character sexo;
    @Column(name = "email")
    private String email;
    @Column(name = "titulacion")
    private String titulacion;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "experiencia")
    private String experiencia;
    @Column(name = "sobre_ti")
    private String sobreTi;

    public PreparadorSeleccion() {
    }

    public PreparadorSeleccion(Integer codPreparadorSeleccion) {
        this.codPreparadorSeleccion = codPreparadorSeleccion;
    }

    public Integer getCodPreparadorSeleccion() {
        return codPreparadorSeleccion;
    }

    public void setCodPreparadorSeleccion(Integer codPreparadorSeleccion) {
        this.codPreparadorSeleccion = codPreparadorSeleccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getSobreTi() {
        return sobreTi;
    }

    public void setSobreTi(String sobreTi) {
        this.sobreTi = sobreTi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPreparadorSeleccion != null ? codPreparadorSeleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreparadorSeleccion)) {
            return false;
        }
        PreparadorSeleccion other = (PreparadorSeleccion) object;
        if ((this.codPreparadorSeleccion == null && other.codPreparadorSeleccion != null) || (this.codPreparadorSeleccion != null && !this.codPreparadorSeleccion.equals(other.codPreparadorSeleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.PreparadorSeleccion[ codPreparadorSeleccion=" + codPreparadorSeleccion + " ]";
    }
    
}
