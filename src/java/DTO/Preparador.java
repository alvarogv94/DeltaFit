/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "preparador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preparador.findAll", query = "SELECT p FROM Preparador p"),
    @NamedQuery(name = "Preparador.findByCodPreparador", query = "SELECT p FROM Preparador p WHERE p.codPreparador = :codPreparador"),
    @NamedQuery(name = "Preparador.findByNombre", query = "SELECT p FROM Preparador p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Preparador.findByApellidos", query = "SELECT p FROM Preparador p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Preparador.findByLocalidad", query = "SELECT p FROM Preparador p WHERE p.localidad = :localidad"),
    @NamedQuery(name = "Preparador.findByEdad", query = "SELECT p FROM Preparador p WHERE p.edad = :edad"),
    @NamedQuery(name = "Preparador.findBySexo", query = "SELECT p FROM Preparador p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Preparador.findByEmail", query = "SELECT p FROM Preparador p WHERE p.email = :email"),
    @NamedQuery(name = "Preparador.findByNomUsuario", query = "SELECT p FROM Preparador p WHERE p.nomUsuario = :nomUsuario"),
    @NamedQuery(name = "Preparador.findByPass", query = "SELECT p FROM Preparador p WHERE p.pass = :pass"),
    @NamedQuery(name = "Preparador.findByTitulacion", query = "SELECT p FROM Preparador p WHERE p.titulacion = :titulacion"),
    @NamedQuery(name = "Preparador.findByEspecialidad", query = "SELECT p FROM Preparador p WHERE p.especialidad = :especialidad"),
    @NamedQuery(name = "Preparador.findByExperiencia", query = "SELECT p FROM Preparador p WHERE p.experiencia = :experiencia"),
    @NamedQuery(name = "Preparador.findBySobreTi", query = "SELECT p FROM Preparador p WHERE p.sobreTi = :sobreTi"),
    @NamedQuery(name = "Preparador.findByFechIncorporacion", query = "SELECT p FROM Preparador p WHERE p.fechIncorporacion = :fechIncorporacion"),
    @NamedQuery(name = "Preparador.findByFotoPerfil", query = "SELECT p FROM Preparador p WHERE p.fotoPerfil = :fotoPerfil")})
public class Preparador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_preparador")
    private Integer codPreparador;
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
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "pass")
    private String pass;
    @Column(name = "titulacion")
    private String titulacion;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "experiencia")
    private String experiencia;
    @Column(name = "sobre_ti")
    private String sobreTi;
    @Column(name = "fech_incorporacion")
    @Temporal(TemporalType.DATE)
    private Date fechIncorporacion;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @OneToMany(mappedBy = "codPreparador")
    private List<Atleta> atletaList;
    @OneToMany(mappedBy = "codPreparador")
    private List<Recuperacion> recuperacionList;
    @OneToMany(mappedBy = "codPreparador")
    private List<Valoracion> valoracionList;
    @OneToMany(mappedBy = "codPreparador")
    private List<Exito> exitoList;
    @OneToMany(mappedBy = "codPreparador")
    private List<Mensaje> mensajeList;
    @OneToMany(mappedBy = "codPreparador")
    private List<Entreno> entrenoList;

    public Preparador() {
    }

    public Preparador(Integer codPreparador) {
        this.codPreparador = codPreparador;
    }

    public Integer getCodPreparador() {
        return codPreparador;
    }

    public void setCodPreparador(Integer codPreparador) {
        this.codPreparador = codPreparador;
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

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public Date getFechIncorporacion() {
        return fechIncorporacion;
    }

    public void setFechIncorporacion(Date fechIncorporacion) {
        this.fechIncorporacion = fechIncorporacion;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    @XmlTransient
    public List<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(List<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    @XmlTransient
    public List<Recuperacion> getRecuperacionList() {
        return recuperacionList;
    }

    public void setRecuperacionList(List<Recuperacion> recuperacionList) {
        this.recuperacionList = recuperacionList;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    @XmlTransient
    public List<Exito> getExitoList() {
        return exitoList;
    }

    public void setExitoList(List<Exito> exitoList) {
        this.exitoList = exitoList;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @XmlTransient
    public List<Entreno> getEntrenoList() {
        return entrenoList;
    }

    public void setEntrenoList(List<Entreno> entrenoList) {
        this.entrenoList = entrenoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPreparador != null ? codPreparador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preparador)) {
            return false;
        }
        Preparador other = (Preparador) object;
        if ((this.codPreparador == null && other.codPreparador != null) || (this.codPreparador != null && !this.codPreparador.equals(other.codPreparador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Preparador[ codPreparador=" + codPreparador + " ]";
    }
    
}
