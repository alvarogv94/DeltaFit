/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.DietaEntrenoJpaController;
import DAO.DietaRecuperacionJpaController;
import DAO.EjercicioJpaController;
import DAO.EntrenoJpaController;
import DAO.MusculoJpaController;
import DAO.RecuperacionJpaController;
import DAO.RutinaEntrenoJpaController;
import DAO.RutinaRecuperacionJpaController;
import DTO.Atleta;
import DTO.DietaEntreno;
import DTO.DietaRecuperacion;
import DTO.Ejercicio;
import DTO.Entreno;
import DTO.Musculo;
import DTO.Preparador;
import DTO.Recuperacion;
import DTO.RutinaEntreno;
import DTO.RutinaRecuperacion;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class RealizaPreparacion {

    private EntityManagerFactory emf;
    private ExternalContext contexto;

    private Preparador preparador;
    private Entreno entreno;
    private Recuperacion recuperacion;

    //Plan de entreno de no lesionados
    private DietaEntreno dietaEntreno;
    private RutinaEntreno rutinaEntreno;

    //Plan de recuperacion
    private RutinaRecuperacion rutinaRecuperacion;
    private DietaRecuperacion dietaRecuperacion;

    //Datos para la rutina
    private int dia;
    private String ejercicio;
    private int orden;

    /*Aqui guardaremos los ejercicios que se vayan añadiendo*/
    private List<RutinaEntreno> listaEntreno;
    private List<RutinaRecuperacion> listaEntrenoLesion;

    //Objetos de utilidad
    private Atleta atleta;
    private List<Musculo> listaMusculo;
    private List<Ejercicio> listaEjercicios;
    private MusculoJpaController musculoControl;
    private String musculo;

    //Objetos de error
    private String errorEjercicio;
    private String clase;

    /**
     * Creates a new instance of Preparacion
     */
    public RealizaPreparacion() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = (Atleta) contexto.getSessionMap().get("atletaPreparacion");
        preparador = (Preparador) contexto.getSessionMap().get("usuActivo");
        musculoControl = new MusculoJpaController(emf);
        listaMusculo = musculoControl.findMusculoEntities();

        //Objetos donde almacenaremos cada recuperacion y cada entreno
        recuperacion = new Recuperacion();
        entreno = new Entreno();

        //Listas donde iremos almacenando cada ejercicio
        listaEntreno = new ArrayList<>();
        listaEntrenoLesion = new ArrayList<>();

        //Objetos donde iremos almacenando la dieta
        dietaEntreno = new DietaEntreno();
        dietaRecuperacion = new DietaRecuperacion();

        //Objetos donde iremos almacenando cada ejercicio
        rutinaEntreno = new RutinaEntreno();
        rutinaRecuperacion = new RutinaRecuperacion();

        //Variables para controlar cada ejercicio, y saber el orden y el dia
        dia = 1;
        orden = 1;

        //objeto de error
        errorEjercicio = "";
        clase = "";
    }

    public Preparador getPreparador() {
        return preparador;
    }

    public void setPreparador(Preparador preparador) {
        this.preparador = preparador;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getErrorEjercicio() {
        return errorEjercicio;
    }

    public void setErrorEjercicio(String errorEjercicio) {
        this.errorEjercicio = errorEjercicio;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setListaEntreno(List<RutinaEntreno> listaEntreno) {
        this.listaEntreno = listaEntreno;
    }

    public void setListaEntrenoLesion(List<RutinaRecuperacion> listaEntrenoLesion) {
        this.listaEntrenoLesion = listaEntrenoLesion;
    }

    public int getOrden() {
        return orden;
    }

    public int getDia() {
        return dia;
    }

    public List<RutinaEntreno> getListaEntreno() {
        return listaEntreno;
    }

    public List<RutinaRecuperacion> getListaEntrenoLesion() {
        return listaEntrenoLesion;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public MusculoJpaController getMusculoControl() {
        return musculoControl;
    }

    public void setMusculoControl(MusculoJpaController musculoControl) {
        this.musculoControl = musculoControl;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public List<Musculo> getListaMusculo() {
        return listaMusculo;
    }

    public void setListaMusculo(List<Musculo> listaMusculo) {
        this.listaMusculo = listaMusculo;
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

    public Entreno getEntreno() {
        return entreno;
    }

    public void setEntreno(Entreno entreno) {
        this.entreno = entreno;
    }

    public Recuperacion getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(Recuperacion recuperacion) {
        this.recuperacion = recuperacion;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public DietaEntreno getDietaEntreno() {
        return dietaEntreno;
    }

    public void setDietaEntreno(DietaEntreno dietaEntreno) {
        this.dietaEntreno = dietaEntreno;
    }

    public RutinaEntreno getRutinaEntreno() {
        return rutinaEntreno;
    }

    public void setRutinaEntreno(RutinaEntreno rutinaEntreno) {
        this.rutinaEntreno = rutinaEntreno;
    }

    public RutinaRecuperacion getRutinaRecuperacion() {
        return rutinaRecuperacion;
    }

    public void setRutinaRecuperacion(RutinaRecuperacion rutinaRecuperacion) {
        this.rutinaRecuperacion = rutinaRecuperacion;
    }

    public DietaRecuperacion getDietaRecuperacion() {
        return dietaRecuperacion;
    }

    public void setDietaRecuperacion(DietaRecuperacion dietaRecuperacion) {
        this.dietaRecuperacion = dietaRecuperacion;
    }

    public boolean lesionNo() {
        if (atleta.getLesionSi() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean lesionSi() {
        if (atleta.getLesionSi() == 1) {
            return true;
        } else {
            return false;
        }
    }

    //metodo que recibe un evento, y cambiara un select een funcion del valor del select de los musculos
    public void cambio(ValueChangeEvent evento) {

        EjercicioJpaController ejercicioControl = new EjercicioJpaController(emf);

        musculo = (String) evento.getNewValue();

        listaEjercicios = ejercicioControl.ejerciciosByMusculo(musculo);

    }

    //Añadira la dieta al plan de entreno
    public void añadeDieta() {

        if (atleta.getLesionSi() == 1) {
            //Aqui entrará si esta lesionado

        } else {
            //Si no esta lesionado aqui

        }

    }

    //Añade un ejercicio a la lista de rutinaRecuperacion
    public void añadeEjercicioLesion() {

        if (!rutinaRecuperacion.getEjercicio().equals("")) {
            rutinaRecuperacion.setDia(dia);
            rutinaRecuperacion.setOrden(orden);
            listaEntrenoLesion.add(rutinaRecuperacion);
            orden++;
            rutinaRecuperacion = new RutinaRecuperacion();
            errorEjercicio = "Ejercicio Añadido Correctamente.";
            clase = "okEjercicio";
        } else {
            errorEjercicio = "Debes seleccionar algún ejercicio";
            clase = "errorEjercicio";
        }
    }

    //Añadira un ejercicio a la lista de rutinaEntreno
    public void añadeEjercicioNoLesion() {

        if (!rutinaEntreno.getEjercicio().equals("")) {
            rutinaEntreno.setDia(dia);
            rutinaEntreno.setOrden(orden);
            listaEntreno.add(rutinaEntreno);
            orden++;
            rutinaEntreno = new RutinaEntreno();
            errorEjercicio = "Ejercicio Añadido Correctamente.";
            clase = "okEjercicio";
        } else {
            errorEjercicio = "Debes seleccionar algún ejercicio";
            clase = "errorEjercicio";
        }

    }

    //metodo que cambiará al siguiente dia, y empezara de nuevo el orden
    public void siguienteDia() {
        orden = 1;
        dia++;
    }

    public String guardaPlanLesion() {
        /*
         Este metodo guardara el plan completo en el atleta lesionado        
         */

        //Damos de alta un nuevo plan de recuperacion por el preparador activo en la sesion
        RecuperacionJpaController recuperacionControl = new RecuperacionJpaController(emf);
        recuperacion.setCodPreparador(preparador);
        recuperacion.setCodAtleta(atleta);
        recuperacionControl.create(recuperacion);

        //Y recuperamos dicho plan para poder recuperar su codigo de recuperacion y añadirselo tanto a la dieta como al entreno
        Recuperacion ultimaRecuperacion = recuperacionControl.recuperacionUltimaPreparador(preparador);
        
        DietaRecuperacionJpaController dietaControl = new DietaRecuperacionJpaController(emf);
        dietaRecuperacion.setCodRecuperacion(ultimaRecuperacion);
        dietaControl.create(dietaRecuperacion);

        RutinaRecuperacionJpaController rutinaControl = new RutinaRecuperacionJpaController(emf);

        for (int i = 0; i < listaEntrenoLesion.size(); i++) {
            listaEntrenoLesion.get(i).setCodRecuperacion(ultimaRecuperacion);
            rutinaControl.create(listaEntrenoLesion.get(i));
        }
        return "ok";
    }

    public String guardaPlanLesionNo() {
        /*
         Este metodo guardara el plan completo en el atleta que no esta lesionado
         */

        //Damos de alta un nuevo entreno por el preparador activo en la sesion
        EntrenoJpaController entrenoControl = new EntrenoJpaController(emf);
        entreno.setCodPreparador(preparador);
        entreno.setCodAtleta(atleta);
        entrenoControl.create(entreno);

        //Y recuperamos dicho plan para poder recuperar su codigo de recuperacion y añadirselo tanto a la dieta como al entreno
        Entreno ultimoEntreno = entrenoControl.entrenoUltimaPreparador(preparador);
        
        DietaEntrenoJpaController dietaControl = new DietaEntrenoJpaController(emf);
        dietaEntreno.setCodEntreno(ultimoEntreno);
        dietaControl.create(dietaEntreno);
        
        RutinaEntrenoJpaController rutinaControl = new RutinaEntrenoJpaController(emf);
        
        for(int i = 0; i < listaEntreno.size(); i++) {
            listaEntreno.get(i).setCodEntreno(ultimoEntreno);
            rutinaControl.create(listaEntreno.get(i));            
        }
        
        return "ok";
    }
}
