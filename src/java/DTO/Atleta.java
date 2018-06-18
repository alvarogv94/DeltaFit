/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.AtletaJpaController;
import DAO.DietaEntrenoJpaController;
import DAO.DietaPlanPredJpaController;
import DAO.DietaRecuperacionJpaController;
import DAO.EntrenoJpaController;
import DAO.MensajeJpaController;
import DAO.PlanPredJpaController;
import DAO.PreparadorJpaController;
import DAO.RecuperacionJpaController;
import DAO.RutinaEntrenoJpaController;
import DAO.RutinaPlanPredJpaController;
import DAO.RutinaRecuperacionJpaController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
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
    @NamedQuery(name = "Atleta.findByAltura", query = "SELECT a FROM Atleta a WHERE a.altura = :altura"),
    @NamedQuery(name = "Atleta.findByDeporte", query = "SELECT a FROM Atleta a WHERE a.deporte = :deporte"),
    @NamedQuery(name = "Atleta.findByDeporteComplementado", query = "SELECT a FROM Atleta a WHERE a.deporteComplementado = :deporteComplementado"),
    @NamedQuery(name = "Atleta.findByTipoUsuario", query = "SELECT a FROM Atleta a WHERE a.tipoUsuario = :tipoUsuario"),
    @NamedQuery(name = "Atleta.findByObjetivo", query = "SELECT a FROM Atleta a WHERE a.objetivo = :objetivo"),
    @NamedQuery(name = "Atleta.findByComidaNoGusta", query = "SELECT a FROM Atleta a WHERE a.comidaNoGusta = :comidaNoGusta"),
    @NamedQuery(name = "Atleta.findByAlergia", query = "SELECT a FROM Atleta a WHERE a.alergia = :alergia"),
    @NamedQuery(name = "Atleta.findBySuplementacion", query = "SELECT a FROM Atleta a WHERE a.suplementacion = :suplementacion"),
    @NamedQuery(name = "Atleta.findByLesionSi", query = "SELECT a FROM Atleta a WHERE a.lesionSi = :lesionSi"),
    @NamedQuery(name = "Atleta.findByEnfermedad", query = "SELECT a FROM Atleta a WHERE a.enfermedad = :enfermedad"),
    @NamedQuery(name = "Atleta.findByObservacionesAtleta", query = "SELECT a FROM Atleta a WHERE a.observacionesAtleta = :observacionesAtleta"),
    @NamedQuery(name = "Atleta.findByFotoPerfil", query = "SELECT a FROM Atleta a WHERE a.fotoPerfil = :fotoPerfil"),
    @NamedQuery(name = "Atleta.atletaNumByEmail", query = "SELECT count(a) FROM Atleta a WHERE a.email = :email"),
    @NamedQuery(name = "Atleta.atletaNumByNomUsuario", query = "SELECT count(a) FROM Atleta a WHERE a.nomUsuario = :nomUsuario")})
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_actual")
    private BigDecimal pesoActual;
    @Column(name = "altura")
    private BigDecimal altura;
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
    @Column(name = "enfermedad")
    private String enfermedad;
    @Column(name = "observaciones_atleta")
    private String observacionesAtleta;
    @Column(name = "foto_perfil")
    private String fotoPerfil;
    @Column(name = "cod_entrenamiento")
    private Integer codEntrenamiento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codAtleta")
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

    public BigDecimal getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(BigDecimal pesoActual) {
        this.pesoActual = pesoActual;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
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

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getObservacionesAtleta() {
        return observacionesAtleta;
    }

    public void setObservacionesAtleta(String observacionesAtleta) {
        this.observacionesAtleta = observacionesAtleta;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getCodEntrenamiento() {
        return codEntrenamiento;
    }

    public void setCodEntrenamiento(Integer codEntrenamiento) {
        this.codEntrenamiento = codEntrenamiento;
    }

    public Preparador getCodPreparador() {
        return codPreparador;
    }

    public void setCodPreparador(Preparador codPreparador) {
        this.codPreparador = codPreparador;
    }

    @XmlTransient
    public List<Recuperacion> getRecuperacionList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        RecuperacionJpaController recuperacionControl = new RecuperacionJpaController(emf);
        return recuperacionControl.findRecuperacionEntities();
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        EntrenoJpaController entrenoControl = new EntrenoJpaController(emf);
        return entrenoControl.findEntrenoEntities();
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

    public boolean fotoSi() {
        if (!fotoPerfil.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fotoNo() {
        if (fotoPerfil.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public Long getMensajesNoLeidosCant() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");

        ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();

        MensajeJpaController mensajecontrol = new MensajeJpaController(emf);
        AtletaJpaController atletaControl = new AtletaJpaController(emf);
        return mensajecontrol.mensajesNoLeidosAtleta(this);
    }

    public List<Conversacion> getMensajesNoLeidos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");

        MensajeJpaController controlMensaje = new MensajeJpaController(emf);
        PreparadorJpaController controlPreparador = new PreparadorJpaController(emf);
        Preparador prep = controlPreparador.findPreparador(this.getCodPreparador().getCodPreparador());

        List<Mensaje> mensaje = controlMensaje.chatByUsuarioByPreparador(this, prep);
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

        for (int i = 0; i < mensaje.size(); i++) {

            if (mensaje.get(i).getEstado() == '1') {
                Mensaje mensajeEdit = new Mensaje();
                mensajeEdit.setCodAtleta(mensaje.get(i).getCodAtleta());
                mensajeEdit.setCodMensaje(mensaje.get(i).getCodMensaje());
                mensajeEdit.setCodPreparador(mensaje.get(i).getCodPreparador());
                mensajeEdit.setEstado('2');
                mensajeEdit.setFechaEnvio(mensaje.get(i).getFechaEnvio());
                mensajeEdit.setTexto(mensaje.get(i).getTexto());
                try {
                    controlMensaje.edit(mensajeEdit);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return chat;
    }

    public String getFech(Date date) {

        DateFormat dfDateMedium = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return dfDateMedium.format(date);
    }

    public DietaEntreno getUltDieta() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        DietaEntrenoJpaController dietaControl = new DietaEntrenoJpaController(emf);
        int ultimo = entrenoList.size() - 1;
        try {
            Entreno codEntreno = entrenoList.get(ultimo);
            return dietaControl.dietaByEntreno(codEntreno);

        } catch (ArrayIndexOutOfBoundsException ex) {
            return new DietaEntreno();
        }
    }

    public List<RutinaEntreno> getUltEntreno() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        RutinaEntrenoJpaController rutinaControl = new RutinaEntrenoJpaController(emf);
        int ultimo = entrenoList.size() - 1;
        try {
            Entreno codEntreno = entrenoList.get(ultimo);
            return rutinaControl.rutinaByEntreno(codEntreno);

        } catch (ArrayIndexOutOfBoundsException ex) {
            return new ArrayList<RutinaEntreno>();

        }
    }

    public List<RutinaRecuperacion> getUltRec() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        RutinaRecuperacionJpaController recuperacionControl = new RutinaRecuperacionJpaController(emf);
        int ultimo = recuperacionList.size() - 1;
        try {
            Recuperacion codEntreno = recuperacionList.get(ultimo);
            return recuperacionControl.rutinaByEntreno(codEntreno);

        } catch (ArrayIndexOutOfBoundsException ex) {
            return new ArrayList<RutinaRecuperacion>();

        }
    }

    public DietaRecuperacion getUltDietaRec() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        DietaRecuperacionJpaController dietaControl = new DietaRecuperacionJpaController(emf);
        int ultimo = recuperacionList.size() - 1;
        try {

            Recuperacion codEntreno = recuperacionList.get(ultimo);
            return dietaControl.dietaByEntreno(codEntreno);

        } catch (ArrayIndexOutOfBoundsException ex) {
            return new DietaRecuperacion();
        }
    }

    public DietaPlanPred getUltDietaPred() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        DietaPlanPredJpaController dietaControl = new DietaPlanPredJpaController(emf);
        PlanPredJpaController controlPlanPred = new PlanPredJpaController(emf);
        PlanPred plan = controlPlanPred.findPlanPred(codEntrenamiento);
        try {
            return dietaControl.rutinaByEntreno(plan);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return new DietaPlanPred();
        }
    }

    public List<RutinaPlanPred> getUltEntrenoPred() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        RutinaPlanPredJpaController rutinaControl = new RutinaPlanPredJpaController(emf);
        PlanPredJpaController controlPlanPred = new PlanPredJpaController(emf);
        PlanPred plan = controlPlanPred.findPlanPred(codEntrenamiento);
        try {
            return rutinaControl.rutinaByEntreno(plan);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return new ArrayList<RutinaPlanPred>();
        }
    }

    public PlanPred getUltPlan() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        RutinaPlanPredJpaController rutinaControl = new RutinaPlanPredJpaController(emf);
        PlanPredJpaController controlPlanPred = new PlanPredJpaController(emf);
        PlanPred plan = controlPlanPred.findPlanPred(codEntrenamiento);
        return plan;
    }
}
