/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DTO.Atleta;
import DTO.Entreno;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alvaro
 */
public class ExportarPlan {

    private HtmlDataTable tabla;
    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private Atleta atleta;

    /**
     * Creates a new instance of ExportarPlan
     */
    public ExportarPlan() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = (Atleta) contexto.getSessionMap().get("usuActivo");
    }

    public HtmlDataTable getTabla() {
        return tabla;
    }

    public void setTabla(HtmlDataTable tabla) {
        this.tabla = tabla;
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

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public void exportaEntreno() {
        Entreno entreno = (Entreno) tabla.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline=filename=" + entreno.getFech() + ".pdf");
        Document document = new Document();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
        document.open();

        Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, BaseColor.BLACK);
        Font fuentetitulo = new Font(Font.FontFamily.HELVETICA, 25, Font.NORMAL, BaseColor.BLACK);

        Phrase encabezado = new Phrase("Entreno del usuario" + atleta.getNomUsuario() + " para la fecha " + entreno.getFech());
        encabezado.setFont(fuenteNormal);

        Phrase titulo = new Phrase("Anotacion del Entreno " + entreno.getAnotacion());
        titulo.setFont(fuentetitulo);

        Phrase tituloTabla = new Phrase("Entreno");
        tituloTabla.setFont(fuentetitulo);
        PdfPTable tablaEntreno = new PdfPTable(4);

        for (int i = 0; i < entreno.getRutinaEntrenoList().size(); i++) {
            PdfPCell celda = new PdfPCell(new Phrase(entreno.getRutinaEntrenoList().get(i).getDia()));
            PdfPCell celda2 = new PdfPCell(new Phrase(entreno.getRutinaEntrenoList().get(i).getOrden()));
            PdfPCell celda3 = new PdfPCell(new Phrase(entreno.getRutinaEntrenoList().get(i).getEjercicio()));
            PdfPCell celda4 = new PdfPCell(new Phrase(entreno.getRutinaEntrenoList().get(i).getAnotacion()));

            tablaEntreno.addCell(celda);
            tablaEntreno.addCell(celda2);
            tablaEntreno.addCell(celda3);
            tablaEntreno.addCell(celda4);
        }

        try {
            document.add(encabezado);
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
        // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        try {
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        context.responseComplete();
    }

    public void exportaRecuperacion() {

    }

}