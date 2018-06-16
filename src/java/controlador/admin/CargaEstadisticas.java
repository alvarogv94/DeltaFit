/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.admin;

import DAO.AtletaJpaController;
import DAO.VisitaJpaController;
import DTO.Atleta;
import DTO.Visita;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Alvaro
 */
public class CargaEstadisticas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        JSONObject obj = new JSONObject();

        int visita = Integer.parseInt(request.getParameter("Visita"));
        
        int opcion1 = 0;
        int opcion2 = 0;
        int opcion3 = 0;

        if (visita == 0) {
            VisitaJpaController visitaControl = new VisitaJpaController(emf);
            List<Visita> listaVisitas = visitaControl.findVisitaEntities();
            for (int i = 0; i < listaVisitas.size(); i++) {

                long valor = dameMinutos(listaVisitas.get(i).getHoraInicio(), listaVisitas.get(i).getHoraFin());
                if (valor >= 0 && valor <= 10) {
                    opcion1++;
                }

                if (valor > 10 && valor <= 20) {
                    opcion2++;
                }
                if (valor > 20) {
                    opcion3++;
                }
            }

            try {
                obj.put("1", opcion1);
                obj.put("2", opcion2);
                obj.put("3", opcion3);

            } catch (JSONException ex) {
                System.out.println(ex.getMessage());
            }
            response.setContentType("application/json");
            response.getWriter().print(obj.toString());
        }

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

    public long dameMinutos(Date hora_inicio, Date hora_fin) {
        long tiempoInicial = hora_inicio.getTime();
        long tiempoFinal = hora_fin.getTime();
        long resta = tiempoFinal - tiempoInicial;
        resta = resta / (1000 * 60);
        return resta;
    }
}
