/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.admin;

import DAO.DietaPlanPredJpaController;
import DAO.EjercicioJpaController;
import DAO.MusculoJpaController;
import DAO.PlanPredJpaController;
import DAO.RutinaPlanPredJpaController;
import DTO.DietaPlanPred;
import DTO.Ejercicio;
import DTO.Musculo;
import DTO.PlanPred;
import DTO.RutinaPlanPred;
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
public class AltaPlanPred {
    
    private PlanPred nuevoPlan;
    private RutinaPlanPred nuevaRutina;
    private List<RutinaPlanPred> listaEjercicios;
    private DietaPlanPred nuevaDieta;
    private List<DietaPlanPred> listaNuevaDieta;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private int orden;
    private int dia;
    private String ejercicioAlternativo;
    private String errorEjercicio;
    private String clase;
    private List<Musculo> listaMusculo;
    private List<Ejercicio> listaEjerciciosMusculo;
    private String musculo;
    private String deporte;
    private String objetivo;
    private Integer cod;

    /**
     * Creates a new instance of AltaPlanPred
     */
    public AltaPlanPred() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        //Variables para controlar cada ejercicio, y saber el orden y el dia
        dia = 1;
        orden = 1;

        //objeto de error
        errorEjercicio = "";
        clase = "";
        
        MusculoJpaController musculoControl = new MusculoJpaController(emf);
        listaMusculo = musculoControl.findMusculoEntities();
        nuevoPlan = new PlanPred();
        nuevaRutina = new RutinaPlanPred();
        nuevaDieta = new DietaPlanPred();
        listaEjercicios = new ArrayList<>();
        listaNuevaDieta = new ArrayList<>();
        
        PlanPredJpaController pControl = new PlanPredJpaController(emf);
        PlanPred ult = pControl.ultPlan();
        cod = ult.getCodPlanPred() + 1;
        nuevoPlan.setCodPlanPred(cod);
    }
    
    public Integer getCod() {
        return cod;
    }
    
    public void setCod(Integer cod) {
        this.cod = cod;
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
    
    public List<DietaPlanPred> getListaNuevaDieta() {
        return listaNuevaDieta;
    }
    
    public void setListaNuevaDieta(List<DietaPlanPred> listaNuevaDieta) {
        this.listaNuevaDieta = listaNuevaDieta;
    }
    
    public String getMusculo() {
        return musculo;
    }
    
    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }
    
    public List<Musculo> getListaMusculo() {
        return listaMusculo;
    }
    
    public void setListaMusculo(List<Musculo> listaMusculo) {
        this.listaMusculo = listaMusculo;
    }
    
    public List<Ejercicio> getListaEjerciciosMusculo() {
        return listaEjerciciosMusculo;
    }
    
    public void setListaEjerciciosMusculo(List<Ejercicio> listaEjerciciosMusculo) {
        this.listaEjerciciosMusculo = listaEjerciciosMusculo;
    }
    
    public String getErrorEjercicio() {
        return errorEjercicio;
    }
    
    public void setErrorEjercicio(String errorEjercicio) {
        this.errorEjercicio = errorEjercicio;
    }
    
    public String getClase() {
        return clase;
    }
    
    public void setClase(String clase) {
        this.clase = clase;
    }
    
    public String getEjercicioAlternativo() {
        return ejercicioAlternativo;
    }
    
    public void setEjercicioAlternativo(String ejercicioAlternativo) {
        this.ejercicioAlternativo = ejercicioAlternativo;
    }
    
    public PlanPred getNuevoPlan() {
        return nuevoPlan;
    }
    
    public void setNuevoPlan(PlanPred nuevoPlan) {
        this.nuevoPlan = nuevoPlan;
    }
    
    public RutinaPlanPred getNuevaRutina() {
        return nuevaRutina;
    }
    
    public void setNuevaRutina(RutinaPlanPred nuevaRutina) {
        this.nuevaRutina = nuevaRutina;
    }
    
    public DietaPlanPred getNuevaDieta() {
        return nuevaDieta;
    }
    
    public void setNuevaDieta(DietaPlanPred nuevaDieta) {
        this.nuevaDieta = nuevaDieta;
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
    
    public List<RutinaPlanPred> getListaEjercicios() {
        return listaEjercicios;
    }
    
    public void setListaEjercicios(List<RutinaPlanPred> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }
    
    public int getOrden() {
        return orden;
    }
    
    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    public int getDia() {
        return dia;
    }
    
    public void setDia(int dia) {
        this.dia = dia;
    }

    //Añadira la dieta al plan de entreno
    public void añadeDieta() {
        nuevaDieta.setCodPlanPred(nuevoPlan);
    }

    //metodo que cambiará al siguiente dia, y empezara de nuevo el orden
    public void siguienteDia() {
        orden = 1;
        dia++;
    }
    
    public void saveDatos() {
        
    }
    
    public void añadeEjercicioPlan() {
        if (!nuevaRutina.getEjercicio().equals("")) {
            if (!ejercicioAlternativo.equals("")) {
                nuevaRutina.setEjercicio(ejercicioAlternativo);
            }
            nuevaRutina.setDia(dia);
            nuevaRutina.setOrden(orden);
            nuevaRutina.setCodPlanPred(nuevoPlan);
            listaEjercicios.add(nuevaRutina);
            orden++;
            nuevaRutina = new RutinaPlanPred();
            errorEjercicio = "Ejercicio Añadido Correctamente.";
            clase = "okEjercicio";
        } else {
            errorEjercicio = "Debes seleccionar algún ejercicio";
            clase = "errorEjercicio";
        }
    }

    //metodo que recibe un evento, y cambiara un select een funcion del valor del select de los musculos
    public void cambio(ValueChangeEvent evento) {
        
        EjercicioJpaController ejercicioControl = new EjercicioJpaController(emf);
        
        musculo = (String) evento.getNewValue();
        
        listaEjerciciosMusculo = ejercicioControl.ejerciciosByMusculo(musculo);
    }
    
    public String volver() {
        MusculoJpaController musculoControl = new MusculoJpaController(emf);
        listaMusculo = musculoControl.findMusculoEntities();

        //Objetos donde almacenaremos cada recuperacion y cada entreno
        nuevaRutina = new RutinaPlanPred();
        nuevoPlan = new PlanPred();
        nuevaDieta = new DietaPlanPred();

        //Listas donde iremos almacenando cada ejercicio
        listaEjercicios = new ArrayList<>();
        listaEjerciciosMusculo = new ArrayList<>();

        //Variables para controlar cada ejercicio, y saber el orden y el dia
        dia = 1;
        orden = 1;
        errorEjercicio = "";
        return "vuelve";
    }
    
    public String guardaPlan() {
        
        PlanPredJpaController planControl = new PlanPredJpaController(emf);
        DietaPlanPredJpaController dietaControl = new DietaPlanPredJpaController(emf);
        RutinaPlanPredJpaController rutinaControl = new RutinaPlanPredJpaController(emf);

        nuevoPlan.setDeporte(deporte);
        nuevoPlan.setObjetivo(objetivo);
        planControl.create(nuevoPlan);
        
        for (int i = 0; i < listaEjercicios.size(); i++) {
            rutinaControl.create(listaEjercicios.get(i));
        }
        
        listaNuevaDieta.add(nuevaDieta);        
        for (int i = 0; i < listaNuevaDieta.size(); i++) {
            dietaControl.create(listaNuevaDieta.get(i));
        }
        
        return "ok";
    }
}
