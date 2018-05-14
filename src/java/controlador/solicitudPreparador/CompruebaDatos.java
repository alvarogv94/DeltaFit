/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.solicitudPreparador;

import DAO.PreparadorSeleccionJpaController;
import DTO.PreparadorSeleccion;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;

/**
 * Este Servlet recibira una peticion ajax de la peticion Preparador Seleccion,
 * que comprobara los datos: email El metodo compruebaEmail puede tener dos
 * salidas: True: que existe el usuario y por lo tanto no se podria dar el ata,
 * y False: lo contrario
 *
 * @author Alvaro
 */
public class CompruebaDatos extends HttpServlet {

    public void proceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        boolean acceso = false;

        String email = request.getParameter("Email");
        System.out.println(email);
        try {
            //Leemos el objeto JSON y almacenamos su valor en la variable de email
            acceso = compruebaEmail(email);

            //Preparamos la salida del objeto JSON
            JsonObject object = new JsonObject();
            object.addProperty("Email", acceso);

            String jsonS = object.toString();
            out.println(jsonS);

            //----------------------------------------------------------**/
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        proceso(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        proceso(req, res);

    }

    public boolean compruebaEmail(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");

        PreparadorSeleccionJpaController control = new PreparadorSeleccionJpaController(emf);
        Long resultado = control.preparadorByEmailNum(email);

        //Si es distinto a nulo, significa que ya existe alguien
        if (resultado == 0) {
            return false;
        } else {
            return true;
        }
    }
}
