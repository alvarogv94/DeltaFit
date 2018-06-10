/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DAO.PesoJpaController;
import DTO.Atleta;
import DTO.Peso;
import DTO.PesoPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Alvaro
 */
public class AltaPeso extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String pesoParametro = request.getParameter("Peso");
        BigDecimal pesoPar = new BigDecimal(pesoParametro.replaceAll(",", ""));

        JSONObject obj = new JSONObject();
        HttpSession sesion = request.getSession();
        Atleta atleta = (Atleta) sesion.getAttribute("usuActivo");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        PesoJpaController controlPeso = new PesoJpaController(emf);
        AtletaJpaController controlAtleta = new AtletaJpaController(emf);
        
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH) + 1;

        Peso peso = new Peso();
        Long codL = null;
        try {
            codL = controlPeso.pesoCodByAtleta(atleta.getCodAtleta());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        //Aqui le sumamos 1 al ultimo codigo de peso de un atleta
        Integer cod = Integer.valueOf(codL.intValue()) + 1;
        
        PesoPK pesoPk = new PesoPK(cod, atleta.getCodAtleta());
        peso.setPesoPK(pesoPk);
        peso.setAtleta(atleta);
        peso.setPeso(pesoPar);
        peso.setMes(mes);

        try {
            controlPeso.create(peso);
            atleta.setPesoActual(pesoPar);
            controlAtleta.edit(atleta);
            obj.put("ok", 1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        response.setContentType("application/json");
        response.getWriter().print(obj.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
