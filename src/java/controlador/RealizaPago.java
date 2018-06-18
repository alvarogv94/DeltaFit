/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.PagoJpaController;
import DTO.Atleta;
import DTO.Pago;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alvaro
 */
public class RealizaPago {

    private EntityManagerFactory emf;
    private ExternalContext contexto;
    private Atleta atleta;
    private Pago pago;

    /**
     * Creates a new instance of RealizaPago
     */
    public RealizaPago() {
        emf = Persistence.createEntityManagerFactory("DeltaFitPU");
        contexto = FacesContext.getCurrentInstance().getExternalContext();
        atleta = (Atleta) contexto.getSessionMap().get("usuActivo");
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

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public boolean tipoUsuario() {
        if (atleta.getTipoUsuario() != 1) {
            return true;
        } else {
            return false;
        }
    }

    public String debePagar() {
        PagoJpaController pagoControl = new PagoJpaController(emf);
        Pago pago = pagoControl.ultPagoAtleta(atleta);

        DateFormat dfDateMedium = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return dfDateMedium.format(pago.getFechProxPago());
    }

    public void pago() {
        PagoJpaController pagoControl = new PagoJpaController(emf);
        Pago pago = new Pago();

        Date nuevaFecha = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nuevaFecha);
        calendar.add(Calendar.MONTH, 1);

        Date fechaProxPago = calendar.getTime();
        pago.setCodAtleta(atleta);
        pago.setFechUltPago(nuevaFecha);
        pago.setFechProxPago(fechaProxPago);
        
        if (atleta.getTipoUsuario() == 2) {
            pago.setImporte(30);
        }
        if (atleta.getTipoUsuario() == 3) {
            pago.setImporte(40);
        }
        pagoControl.create(pago);
    }

}
