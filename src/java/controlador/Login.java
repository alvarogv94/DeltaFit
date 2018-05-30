/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.PreparadorJpaController;
import DTO.Atleta;
import DTO.Preparador;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
public class Login {

    private String nomUsuario;
    private String pass;
    private EntityManagerFactory emf;
    private String mensaje;
    private static boolean login;
    private ExternalContext contexto;

    /**
     * Creates a new instance of Login
     */
    public Login() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
    }

    public EntityManagerFactory getEmf() {
        return emf;
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

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        Login.login = login;
    }

    public ExternalContext getContexto() {
        return contexto;
    }

    public void setContexto(ExternalContext contexto) {
        this.contexto = contexto;
    }

    public void logout() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ((HttpSession) ctx.getSession(false)).invalidate();
            login = false;
            redireccionar("../index.jsp");
        } catch (Exception ex) {
        }
    }

    public String login() {
        /*Este metodo será llamado desde el boton del login para entrar a tu perfil
         Comprobará si el usuario introducido es correcto, verificara la contraseña 
         y si es correcto almacenaremos que tipo de usuario es y lo subirá a la sesion
         */
        String resultado = "";
        Atleta atl = compruebaAtleta();
        Preparador prep = compruebaPreparador();
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        String url = "";
        if (atl != null) {
            if (atl.getPass().equals(getPass())) {
                contexto.getSessionMap().put("usuActivo", atl);
                contexto.getSessionMap().put("tipoUsuario", "atleta");
                login = true;
                url = "atleta/inicio.jsp";
                redireccionar(url);
            }
        } else {
            if (prep != null) {
                if (prep.getPass().equals(getPass())) {
                    contexto.getSessionMap().put("usuActivo", prep);
                    contexto.getSessionMap().put("tipoUsuario", "preparador");
                    login = true;
                    url = "preparador/inicio.jsp";
                    redireccionar(url);
                }
            } else {
                mensaje = "Error al entrar revise sus datos.";
                resultado = "no";
            }
        }
        
        
        return resultado;
    }

    public Atleta compruebaAtleta() {

        AtletaJpaController atletaControl = new AtletaJpaController(emf);

        Atleta atleta;
        try {
            atleta = atletaControl.atletaByNomUsuario(getNomUsuario());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            atleta = null;
        }
        return atleta;

    }

    public Preparador compruebaPreparador() {

        PreparadorJpaController preparadorControl = new PreparadorJpaController(emf);
        Preparador preparador;
        try {
            preparador = preparadorControl.preparadorByNomUsuario(getNomUsuario());
        } catch (Exception ex) {
            preparador = null;
            System.out.println(ex.getMessage());
        }
        return preparador;

    }

    public void redireccionar(String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (Exception ex) {
        }
    }

}
