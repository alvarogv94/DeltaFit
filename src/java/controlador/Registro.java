/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.DeporteJpaController;
import DAO.PagoJpaController;
import DAO.PreparadorJpaController;
import DTO.Atleta;
import DTO.Deporte;
import DTO.Pago;
import DTO.Preparador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;

/**
 *
 * @author Alvaro
 */
public class Registro {

    private Atleta atleta;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private String objetivoOtro;
    private String textoAlergia;
    private String comidaNoGusta;
    private String enfermedad;
    private String deporteComplementado;
    private String pass1;
    private String pass2;
    private String resultadoPass;
    private ArrayList<String> listaDeporte;
    private String resultadoAlta;
    private String clase;
    private String resultadoEmail;
    private String resultadoUsu;

    /**
     * Creates a new instance of Registro
     */
    public Registro() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = new Atleta();
        listaDeporte = new ArrayList<>();
        DeporteJpaController deporteControl = new DeporteJpaController(emf);
        List<Deporte> listaDeportes = deporteControl.findDeporteEntities();
        for (int i = 0; i < listaDeportes.size(); i++) {
            listaDeporte.add(listaDeportes.get(i).getNombre());
        }
    }

    public String getResultadoEmail() {
        return resultadoEmail;
    }

    public void setResultadoEmail(String resultadoEmail) {
        this.resultadoEmail = resultadoEmail;
    }

    public String getResultadoUsu() {
        return resultadoUsu;
    }

    public void setResultadoUsu(String resultadoUsu) {
        this.resultadoUsu = resultadoUsu;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getDeporteComplementado() {
        return deporteComplementado;
    }

    public void setDeporteComplementado(String deporteComplementado) {
        this.deporteComplementado = deporteComplementado;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getComidaNoGusta() {
        return comidaNoGusta;
    }

    public void setComidaNoGusta(String comidaNoGusta) {
        this.comidaNoGusta = comidaNoGusta;
    }

    public String getObjetivoOtro() {
        return objetivoOtro;
    }

    public void setObjetivoOtro(String objetivoOtro) {
        this.objetivoOtro = objetivoOtro;
    }

    public String getTextoAlergia() {
        return textoAlergia;
    }

    public void setTextoAlergia(String textoAlergia) {
        this.textoAlergia = textoAlergia;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
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

    public String getResultadoPass() {
        return resultadoPass;
    }

    public void setResultadoPass(String resultadoPass) {
        this.resultadoPass = resultadoPass;
    }

    public ArrayList<String> getListaDeporte() {
        return listaDeporte;
    }

    public void setListaDeporte(ArrayList<String> listaDeporte) {
        this.listaDeporte = listaDeporte;
    }

    public String getResultadoAlta() {
        return resultadoAlta;
    }

    public void setResultadoAlta(String resultadoAlta) {
        this.resultadoAlta = resultadoAlta;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String registro() {
        /*Este metodo será llamado desde el boton del registro, la comprobación
         de campos de que sean correctos se hará en la parte del cliente
         */

        AtletaJpaController controlAtleta = new AtletaJpaController(emf);
        String resultado;

        Long email = controlAtleta.atletaNumByEmail(atleta.getEmail());
        Long nomUsu = controlAtleta.atletaNumByNomUsuario(atleta.getNomUsuario());

        if (nomUsu != 0) {
            resultadoUsu = "Ya hay un Usuario con ese Nombre de Usuario";
            resultado = "no";
        } else {
            if (email != 0) {
                resultadoEmail = "Ya hay un Usuario con ese Email.";
                resultado = "no";
            } else {
                if (pass1.equals(pass2)) {
                    /*Como las contraseñas coinciden se la asignamos al atleta*/
                    atleta.setPass(pass1);
                    /*Comprobamos si el usuario ha escrito algun campo mas que no lo controle el objeto atleta, y se lo asignamos a atleta*/
                    if (!objetivoOtro.equals("")) {
                        atleta.setObjetivo(objetivoOtro);
                    }

                    if (!textoAlergia.equals("")) {
                        atleta.setAlergia(textoAlergia);
                    }

                    if (!comidaNoGusta.equals("")) {
                        atleta.setComidaNoGusta(comidaNoGusta);
                    }

                    if (!enfermedad.equals("")) {
                        atleta.setEnfermedad(enfermedad);
                    }

                    if (!deporteComplementado.equals("")) {
                        atleta.setDeporteComplementado(deporteComplementado);
                    }

                    try {

                        /*si el usuario es de tipo 2 o 3 le asignamos un preparador aleatoriamente*/
                        if (atleta.getTipoUsuario() == 2 || atleta.getTipoUsuario() == 3) {
                            PreparadorJpaController controlPreparador = new PreparadorJpaController(emf);
                            List<Preparador> todosPreparadores = controlPreparador.findPreparadorEntities();
                            int preparadorAleatorio = (int) Math.floor(Math.random() * todosPreparadores.size() + 1);
                            Preparador preparador = todosPreparadores.get(preparadorAleatorio);
                            atleta.setCodPreparador(preparador);
                        }

                        if (atleta.getTipoUsuario() == 1) {
                            PreparadorJpaController controlPreparador = new PreparadorJpaController(emf);
                            List<Preparador> todosPreparadores = controlPreparador.findPreparadorEntities();
                            Preparador preparador = todosPreparadores.get(0);
                            atleta.setCodPreparador(preparador);
                        }
                        controlAtleta.create(atleta);
                        PagoJpaController pagoControl = new PagoJpaController(emf);
                        Pago pago = new Pago();
                        pago.setCodAtleta(atleta);
                        pago.setFechUltPago(new Date());

                        Date fecha = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fecha); // Configuramos la fecha que se recibe

                        calendar.add(Calendar.DAY_OF_YEAR, 28);  // numero de días a añadir, o restar en caso de días<0
                        fecha = calendar.getTime();
                        pago.setFechProxPago(fecha);
                        pagoControl.create(pago);
                        resultado = "ok";
                        resultadoAlta = "El Registro se hizo correctamente.";
                        clase = "ok";

                    } catch (Exception ex) {
                        resultado = "no";
                        clase = "no";
                    }

                } else {
                    clase = "no";
                    resultadoAlta = "Hubo un error al realizar el Registro, inténtelo de nuevo o más tarde.";
                    resultadoPass = "Las contraseñas deben coincidir";
                    resultado = "no";
                }
            }
        }
        return resultado;
    }

}
