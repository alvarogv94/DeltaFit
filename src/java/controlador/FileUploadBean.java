/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.AtletaJpaController;
import DTO.Atleta;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadBean")
@RequestScoped
public class FileUploadBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private UploadedFile resume;
    private Atleta atleta;
    private ExternalContext contexto;
    private EntityManagerFactory emf;

    public FileUploadBean() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = (Atleta) contexto.getSessionMap().get("usuActivo");
    }
    
    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }
    
    public UploadedFile getResume() {
        return resume;
    }

    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }

    /*Subida unica*/
    public void uploadResume() throws IOException {

        AtletaJpaController controlAtleta = new AtletaJpaController(emf);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        
        UploadedFile uploadedPhoto = getResume();
        String filePath = (String) servletContext.getRealPath("/").concat("/img/perfil/"+atleta.getCodAtleta()+"/");
        byte[] bytes = null;

        if (null != uploadedPhoto) {
            bytes = uploadedPhoto.getContents();
            String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
            stream.write(bytes);
            stream.close();
            atleta.setFotoPerfil(filename);
            
            try {
                controlAtleta.edit(atleta);               
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tu Foto de Perfil fue Subida Correctamente", ""));
    }

    /*Subida multiple*/
    public void uploadPhoto(FileUploadEvent e) throws IOException {
        
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        UploadedFile uploadedPhoto = e.getFile();
            
        String filePath = (String) servletContext.getRealPath("/").concat("/img/perfil/"+atleta.getCodAtleta()+"/seguimiento/");        
        byte[] bytes = null;

        if (null != uploadedPhoto) {
            bytes = uploadedPhoto.getContents();
            String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
            stream.write(bytes);
            stream.close();
        }

        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tus Fotos fueron subidas Correctamente", ""));
    }

}
