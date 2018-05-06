/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "atleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atleta.findAll", query = "SELECT a FROM Atleta a"),
    @NamedQuery(name = "Atleta.findByCodAtleta", query = "SELECT a FROM Atleta a WHERE a.codAtleta = :codAtleta"),
    @NamedQuery(name = "Atleta.findByNombre", query = "SELECT a FROM Atleta a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Atleta.findByApellidos", query = "SELECT a FROM Atleta a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Atleta.findByLocalidad", query = "SELECT a FROM Atleta a WHERE a.localidad = :localidad"),
    @NamedQuery(name = "Atleta.findByEdad", query = "SELECT a FROM Atleta a WHERE a.edad = :edad"),
    @NamedQuery(name = "Atleta.findBySexo", query = "SELECT a FROM Atleta a WHERE a.sexo = :sexo"),
    @NamedQuery(name = "Atleta.findByNomUsuario", query = "SELECT a FROM Atleta a WHERE a.nomUsuario = :nomUsuario"),
    @NamedQuery(name = "Atleta.findByPass", query = "SELECT a FROM Atleta a WHERE a.pass = :pass"),
    @NamedQuery(name = "Atleta.findByEmail", query = "SELECT a FROM Atleta a WHERE a.email = :email"),
    @NamedQuery(name = "Atleta.findByPesoActual", query = "SELECT a FROM Atleta a WHERE a.pesoActual = :pesoActual"),
    @NamedQuery(name = "Atleta.findByDeporte", query = "SELECT a FROM Atleta a WHERE a.deporte = :deporte"),
    @NamedQuery(name = "Atleta.findByDeporteComplementado", query = "SELECT a FROM Atleta a WHERE a.deporteComplementado = :deporteComplementado"),
    @NamedQuery(name = "Atleta.findByTipoUsuario", query = "SELECT a FROM Atleta a WHERE a.tipoUsuario = :tipoUsuario"),
    @NamedQuery(name = "Atleta.findByObjetivo", query = "SELECT a FROM Atleta a WHERE a.objetivo = :objetivo"),
    @NamedQuery(name = "Atleta.findByComidaNoGusta", query = "SELECT a FROM Atleta a WHERE a.comidaNoGusta = :comidaNoGusta"),
    @NamedQuery(name = "Atleta.findByAlergia", query = "SELECT a FROM Atleta a WHERE a.alergia = :alergia"),
    @NamedQuery(name = "Atleta.findBySuplementacion", query = "SELECT a FROM Atleta a WHERE a.suplementacion = :suplementacion"),
    @NamedQuery(name = "Atleta.findByLesionSi", query = "SELECT a FROM Atleta a WHERE a.lesionSi = :lesionSi"),
    @NamedQuery(name = "Atleta.findByObservacionesAtleta", query = "SELECT a FROM Atleta a WHERE a.observacionesAtleta = :observacionesAtleta")})
public class Atleta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_atleta")
    private Integer codAtleta;
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
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "pass")
    private String pass;
    @Column(name = "email")
    private String email;
    @Column(name = "peso_actual")
    private Integer pesoActual;
    @Column(name = "deporte")
    private String deporte;
    @Column(name = "deporte_complementado")
    private String deporteComplementado;
    @Column(name = "tipo_usuario")
    private Integer tipoUsuario;
    @Column(name = "objetivo")
    private String objetivo;
    @Column(name = "comida_no_gusta")
    private String comidaNoGusta;
    @Column(name = "alergia")
    private String alergia;
    @Column(name = "suplementacion")
    private Integer suplementacion;
    @Column(name = "lesion_si")
    private Integer lesionSi;
    @Column(name = "observaciones_atleta")
    private String observacionesAtleta;
    @JoinColumn(name = "cod_preparador", referencedColumnName = "cod_preparador")
    @ManyToOne
    private Preparador codPreparador;
    @OneToMany(mappedBy = "codAtleta")
    private List<Recuperacion> recuperacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atleta")
    private List<Peso> pesoList;
    @OneToMany(mappedBy = "codAtleta")
    private List<Imagen> imagenList;
    @OneToMany(mappedBy = "codAtleta")
    private List<Valoracion> valoracionList;
    @OneToMany(mappedBy = "codAtleta")
    private List<Pago> pagoList;
    @OneToMany(mappedBy = "codAtleta")
    private List<Exito> exitoList;
    @OneToMany(mappedBy = "codAtleta")
    private List<Mensaje> mensajeList;
    @OneToMany(mappedBy = "codAtleta")
    private List<Entreno> entrenoList;

    public Atleta() {
    }

    public Atleta(Integer codAtleta) {
        this.codAtleta = codAtleta;
    }

    public Integer getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(Integer codAtleta) {
        this.codAtleta = codAtleta;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(Integer pesoActual) {
        this.pesoActual = pesoActual;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getDeporteComplementado() {
        return deporteComplementado;
    }

    public void setDeporteComplementado(String deporteComplementado) {
        this.deporteComplementado = deporteComplementado;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getComidaNoGusta() {
        return comidaNoGusta;
    }

    public void setComidaNoGusta(String comidaNoGusta) {
        this.comidaNoGusta = comidaNoGusta;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public Integer getSuplementacion() {
        return suplementacion;
    }

    public void setSuplementacion(Integer suplementacion) {
        this.suplementacion = suplementacion;
    }

    public Integer getLesionSi() {
        return lesionSi;
    }

    public void setLesionSi(Integer lesionSi) {
        this.lesionSi = lesionSi;
    }

    public String getObservacionesAtleta() {
        return observacionesAtleta;
    }

    public void setObservacionesAtleta(String observacionesAtleta) {
        this.observacionesAtleta = observacionesAtleta;
    }

    public Preparador getCodPreparador() {
        return codPreparador;
    }

    public void setCodPreparador(Preparador codPreparador) {
        this.codPreparador = codPreparador;
    }

    @XmlTransient
    public List<Recuperacion> getRecuperacionList() {
        return recuperacionList;
    }

    public void setRecuperacionList(List<Recuperacion> recuperacionList) {
        this.recuperacionList = recuperacionList;
    }

    @XmlTransient
    public List<Peso> getPesoList() {
        return pesoList;
    }

    public void setPesoList(List<Peso> pesoList) {
        this.pesoList = pesoList;
    }

    @XmlTransient
    public List<Imagen> getImagenList() {
        return imagenList;
    }

    public void setImagenList(List<Imagen> imagenList) {
        this.imagenList = imagenList;
    }

    @XmlTransient
    public List<Valoracion> getValoracionList() {
        return valoracionList;
    }

    public void setValoracionList(List<Valoracion> valoracionList) {
        this.valoracionList = valoracionList;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
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
        hash += (codAtleta != null ? codAtleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atleta)) {
            return false;
        }
        Atleta other = (Atleta) object;
        if ((this.codAtleta == null && other.codAtleta != null) || (this.codAtleta != null && !this.codAtleta.equals(other.codAtleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Atleta[ codAtleta=" + codAtleta + " ]";
    }
    
}
