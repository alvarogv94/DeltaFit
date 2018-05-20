/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Atleta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Preparador;
import DTO.Recuperacion;
import java.util.ArrayList;
import java.util.List;
import DTO.Peso;
import DTO.Imagen;
import DTO.Valoracion;
import DTO.Pago;
import DTO.Exito;
import DTO.Mensaje;
import DTO.Entreno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class AtletaJpaController implements Serializable {

    public AtletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Atleta atleta) {
        if (atleta.getRecuperacionList() == null) {
            atleta.setRecuperacionList(new ArrayList<Recuperacion>());
        }
        if (atleta.getPesoList() == null) {
            atleta.setPesoList(new ArrayList<Peso>());
        }
        if (atleta.getImagenList() == null) {
            atleta.setImagenList(new ArrayList<Imagen>());
        }
        if (atleta.getValoracionList() == null) {
            atleta.setValoracionList(new ArrayList<Valoracion>());
        }
        if (atleta.getPagoList() == null) {
            atleta.setPagoList(new ArrayList<Pago>());
        }
        if (atleta.getExitoList() == null) {
            atleta.setExitoList(new ArrayList<Exito>());
        }
        if (atleta.getMensajeList() == null) {
            atleta.setMensajeList(new ArrayList<Mensaje>());
        }
        if (atleta.getEntrenoList() == null) {
            atleta.setEntrenoList(new ArrayList<Entreno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preparador codPreparador = atleta.getCodPreparador();
            if (codPreparador != null) {
                codPreparador = em.getReference(codPreparador.getClass(), codPreparador.getCodPreparador());
                atleta.setCodPreparador(codPreparador);
            }
            List<Recuperacion> attachedRecuperacionList = new ArrayList<Recuperacion>();
            for (Recuperacion recuperacionListRecuperacionToAttach : atleta.getRecuperacionList()) {
                recuperacionListRecuperacionToAttach = em.getReference(recuperacionListRecuperacionToAttach.getClass(), recuperacionListRecuperacionToAttach.getCodRecuperacion());
                attachedRecuperacionList.add(recuperacionListRecuperacionToAttach);
            }
            atleta.setRecuperacionList(attachedRecuperacionList);
            List<Peso> attachedPesoList = new ArrayList<Peso>();
            for (Peso pesoListPesoToAttach : atleta.getPesoList()) {
                pesoListPesoToAttach = em.getReference(pesoListPesoToAttach.getClass(), pesoListPesoToAttach.getPesoPK());
                attachedPesoList.add(pesoListPesoToAttach);
            }
            atleta.setPesoList(attachedPesoList);
            List<Imagen> attachedImagenList = new ArrayList<Imagen>();
            for (Imagen imagenListImagenToAttach : atleta.getImagenList()) {
                imagenListImagenToAttach = em.getReference(imagenListImagenToAttach.getClass(), imagenListImagenToAttach.getCodImagen());
                attachedImagenList.add(imagenListImagenToAttach);
            }
            atleta.setImagenList(attachedImagenList);
            List<Valoracion> attachedValoracionList = new ArrayList<Valoracion>();
            for (Valoracion valoracionListValoracionToAttach : atleta.getValoracionList()) {
                valoracionListValoracionToAttach = em.getReference(valoracionListValoracionToAttach.getClass(), valoracionListValoracionToAttach.getCodValoracion());
                attachedValoracionList.add(valoracionListValoracionToAttach);
            }
            atleta.setValoracionList(attachedValoracionList);
            List<Pago> attachedPagoList = new ArrayList<Pago>();
            for (Pago pagoListPagoToAttach : atleta.getPagoList()) {
                pagoListPagoToAttach = em.getReference(pagoListPagoToAttach.getClass(), pagoListPagoToAttach.getCodPago());
                attachedPagoList.add(pagoListPagoToAttach);
            }
            atleta.setPagoList(attachedPagoList);
            List<Exito> attachedExitoList = new ArrayList<Exito>();
            for (Exito exitoListExitoToAttach : atleta.getExitoList()) {
                exitoListExitoToAttach = em.getReference(exitoListExitoToAttach.getClass(), exitoListExitoToAttach.getCodExito());
                attachedExitoList.add(exitoListExitoToAttach);
            }
            atleta.setExitoList(attachedExitoList);
            List<Mensaje> attachedMensajeList = new ArrayList<Mensaje>();
            for (Mensaje mensajeListMensajeToAttach : atleta.getMensajeList()) {
                mensajeListMensajeToAttach = em.getReference(mensajeListMensajeToAttach.getClass(), mensajeListMensajeToAttach.getCodMensaje());
                attachedMensajeList.add(mensajeListMensajeToAttach);
            }
            atleta.setMensajeList(attachedMensajeList);
            List<Entreno> attachedEntrenoList = new ArrayList<Entreno>();
            for (Entreno entrenoListEntrenoToAttach : atleta.getEntrenoList()) {
                entrenoListEntrenoToAttach = em.getReference(entrenoListEntrenoToAttach.getClass(), entrenoListEntrenoToAttach.getCodEntreno());
                attachedEntrenoList.add(entrenoListEntrenoToAttach);
            }
            atleta.setEntrenoList(attachedEntrenoList);
            em.persist(atleta);
            if (codPreparador != null) {
                codPreparador.getAtletaList().add(atleta);
                codPreparador = em.merge(codPreparador);
            }
            for (Recuperacion recuperacionListRecuperacion : atleta.getRecuperacionList()) {
                Atleta oldCodAtletaOfRecuperacionListRecuperacion = recuperacionListRecuperacion.getCodAtleta();
                recuperacionListRecuperacion.setCodAtleta(atleta);
                recuperacionListRecuperacion = em.merge(recuperacionListRecuperacion);
                if (oldCodAtletaOfRecuperacionListRecuperacion != null) {
                    oldCodAtletaOfRecuperacionListRecuperacion.getRecuperacionList().remove(recuperacionListRecuperacion);
                    oldCodAtletaOfRecuperacionListRecuperacion = em.merge(oldCodAtletaOfRecuperacionListRecuperacion);
                }
            }
            for (Peso pesoListPeso : atleta.getPesoList()) {
                Atleta oldAtletaOfPesoListPeso = pesoListPeso.getAtleta();
                pesoListPeso.setAtleta(atleta);
                pesoListPeso = em.merge(pesoListPeso);
                if (oldAtletaOfPesoListPeso != null) {
                    oldAtletaOfPesoListPeso.getPesoList().remove(pesoListPeso);
                    oldAtletaOfPesoListPeso = em.merge(oldAtletaOfPesoListPeso);
                }
            }
            for (Imagen imagenListImagen : atleta.getImagenList()) {
                Atleta oldCodAtletaOfImagenListImagen = imagenListImagen.getCodAtleta();
                imagenListImagen.setCodAtleta(atleta);
                imagenListImagen = em.merge(imagenListImagen);
                if (oldCodAtletaOfImagenListImagen != null) {
                    oldCodAtletaOfImagenListImagen.getImagenList().remove(imagenListImagen);
                    oldCodAtletaOfImagenListImagen = em.merge(oldCodAtletaOfImagenListImagen);
                }
            }
            for (Valoracion valoracionListValoracion : atleta.getValoracionList()) {
                Atleta oldCodAtletaOfValoracionListValoracion = valoracionListValoracion.getCodAtleta();
                valoracionListValoracion.setCodAtleta(atleta);
                valoracionListValoracion = em.merge(valoracionListValoracion);
                if (oldCodAtletaOfValoracionListValoracion != null) {
                    oldCodAtletaOfValoracionListValoracion.getValoracionList().remove(valoracionListValoracion);
                    oldCodAtletaOfValoracionListValoracion = em.merge(oldCodAtletaOfValoracionListValoracion);
                }
            }
            for (Pago pagoListPago : atleta.getPagoList()) {
                Atleta oldCodAtletaOfPagoListPago = pagoListPago.getCodAtleta();
                pagoListPago.setCodAtleta(atleta);
                pagoListPago = em.merge(pagoListPago);
                if (oldCodAtletaOfPagoListPago != null) {
                    oldCodAtletaOfPagoListPago.getPagoList().remove(pagoListPago);
                    oldCodAtletaOfPagoListPago = em.merge(oldCodAtletaOfPagoListPago);
                }
            }
            for (Exito exitoListExito : atleta.getExitoList()) {
                Atleta oldCodAtletaOfExitoListExito = exitoListExito.getCodAtleta();
                exitoListExito.setCodAtleta(atleta);
                exitoListExito = em.merge(exitoListExito);
                if (oldCodAtletaOfExitoListExito != null) {
                    oldCodAtletaOfExitoListExito.getExitoList().remove(exitoListExito);
                    oldCodAtletaOfExitoListExito = em.merge(oldCodAtletaOfExitoListExito);
                }
            }
            for (Mensaje mensajeListMensaje : atleta.getMensajeList()) {
                Atleta oldCodAtletaOfMensajeListMensaje = mensajeListMensaje.getCodAtleta();
                mensajeListMensaje.setCodAtleta(atleta);
                mensajeListMensaje = em.merge(mensajeListMensaje);
                if (oldCodAtletaOfMensajeListMensaje != null) {
                    oldCodAtletaOfMensajeListMensaje.getMensajeList().remove(mensajeListMensaje);
                    oldCodAtletaOfMensajeListMensaje = em.merge(oldCodAtletaOfMensajeListMensaje);
                }
            }
            for (Entreno entrenoListEntreno : atleta.getEntrenoList()) {
                Atleta oldCodAtletaOfEntrenoListEntreno = entrenoListEntreno.getCodAtleta();
                entrenoListEntreno.setCodAtleta(atleta);
                entrenoListEntreno = em.merge(entrenoListEntreno);
                if (oldCodAtletaOfEntrenoListEntreno != null) {
                    oldCodAtletaOfEntrenoListEntreno.getEntrenoList().remove(entrenoListEntreno);
                    oldCodAtletaOfEntrenoListEntreno = em.merge(oldCodAtletaOfEntrenoListEntreno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Atleta atleta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta persistentAtleta = em.find(Atleta.class, atleta.getCodAtleta());
            Preparador codPreparadorOld = persistentAtleta.getCodPreparador();
            Preparador codPreparadorNew = atleta.getCodPreparador();
            List<Recuperacion> recuperacionListOld = persistentAtleta.getRecuperacionList();
            List<Recuperacion> recuperacionListNew = atleta.getRecuperacionList();
            List<Peso> pesoListOld = persistentAtleta.getPesoList();
            List<Peso> pesoListNew = atleta.getPesoList();
            List<Imagen> imagenListOld = persistentAtleta.getImagenList();
            List<Imagen> imagenListNew = atleta.getImagenList();
            List<Valoracion> valoracionListOld = persistentAtleta.getValoracionList();
            List<Valoracion> valoracionListNew = atleta.getValoracionList();
            List<Pago> pagoListOld = persistentAtleta.getPagoList();
            List<Pago> pagoListNew = atleta.getPagoList();
            List<Exito> exitoListOld = persistentAtleta.getExitoList();
            List<Exito> exitoListNew = atleta.getExitoList();
            List<Mensaje> mensajeListOld = persistentAtleta.getMensajeList();
            List<Mensaje> mensajeListNew = atleta.getMensajeList();
            List<Entreno> entrenoListOld = persistentAtleta.getEntrenoList();
            List<Entreno> entrenoListNew = atleta.getEntrenoList();
            List<String> illegalOrphanMessages = null;
            for (Peso pesoListOldPeso : pesoListOld) {
                if (!pesoListNew.contains(pesoListOldPeso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Peso " + pesoListOldPeso + " since its atleta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codPreparadorNew != null) {
                codPreparadorNew = em.getReference(codPreparadorNew.getClass(), codPreparadorNew.getCodPreparador());
                atleta.setCodPreparador(codPreparadorNew);
            }
            List<Recuperacion> attachedRecuperacionListNew = new ArrayList<Recuperacion>();
            for (Recuperacion recuperacionListNewRecuperacionToAttach : recuperacionListNew) {
                recuperacionListNewRecuperacionToAttach = em.getReference(recuperacionListNewRecuperacionToAttach.getClass(), recuperacionListNewRecuperacionToAttach.getCodRecuperacion());
                attachedRecuperacionListNew.add(recuperacionListNewRecuperacionToAttach);
            }
            recuperacionListNew = attachedRecuperacionListNew;
            atleta.setRecuperacionList(recuperacionListNew);
            List<Peso> attachedPesoListNew = new ArrayList<Peso>();
            for (Peso pesoListNewPesoToAttach : pesoListNew) {
                pesoListNewPesoToAttach = em.getReference(pesoListNewPesoToAttach.getClass(), pesoListNewPesoToAttach.getPesoPK());
                attachedPesoListNew.add(pesoListNewPesoToAttach);
            }
            pesoListNew = attachedPesoListNew;
            atleta.setPesoList(pesoListNew);
            List<Imagen> attachedImagenListNew = new ArrayList<Imagen>();
            for (Imagen imagenListNewImagenToAttach : imagenListNew) {
                imagenListNewImagenToAttach = em.getReference(imagenListNewImagenToAttach.getClass(), imagenListNewImagenToAttach.getCodImagen());
                attachedImagenListNew.add(imagenListNewImagenToAttach);
            }
            imagenListNew = attachedImagenListNew;
            atleta.setImagenList(imagenListNew);
            List<Valoracion> attachedValoracionListNew = new ArrayList<Valoracion>();
            for (Valoracion valoracionListNewValoracionToAttach : valoracionListNew) {
                valoracionListNewValoracionToAttach = em.getReference(valoracionListNewValoracionToAttach.getClass(), valoracionListNewValoracionToAttach.getCodValoracion());
                attachedValoracionListNew.add(valoracionListNewValoracionToAttach);
            }
            valoracionListNew = attachedValoracionListNew;
            atleta.setValoracionList(valoracionListNew);
            List<Pago> attachedPagoListNew = new ArrayList<Pago>();
            for (Pago pagoListNewPagoToAttach : pagoListNew) {
                pagoListNewPagoToAttach = em.getReference(pagoListNewPagoToAttach.getClass(), pagoListNewPagoToAttach.getCodPago());
                attachedPagoListNew.add(pagoListNewPagoToAttach);
            }
            pagoListNew = attachedPagoListNew;
            atleta.setPagoList(pagoListNew);
            List<Exito> attachedExitoListNew = new ArrayList<Exito>();
            for (Exito exitoListNewExitoToAttach : exitoListNew) {
                exitoListNewExitoToAttach = em.getReference(exitoListNewExitoToAttach.getClass(), exitoListNewExitoToAttach.getCodExito());
                attachedExitoListNew.add(exitoListNewExitoToAttach);
            }
            exitoListNew = attachedExitoListNew;
            atleta.setExitoList(exitoListNew);
            List<Mensaje> attachedMensajeListNew = new ArrayList<Mensaje>();
            for (Mensaje mensajeListNewMensajeToAttach : mensajeListNew) {
                mensajeListNewMensajeToAttach = em.getReference(mensajeListNewMensajeToAttach.getClass(), mensajeListNewMensajeToAttach.getCodMensaje());
                attachedMensajeListNew.add(mensajeListNewMensajeToAttach);
            }
            mensajeListNew = attachedMensajeListNew;
            atleta.setMensajeList(mensajeListNew);
            List<Entreno> attachedEntrenoListNew = new ArrayList<Entreno>();
            for (Entreno entrenoListNewEntrenoToAttach : entrenoListNew) {
                entrenoListNewEntrenoToAttach = em.getReference(entrenoListNewEntrenoToAttach.getClass(), entrenoListNewEntrenoToAttach.getCodEntreno());
                attachedEntrenoListNew.add(entrenoListNewEntrenoToAttach);
            }
            entrenoListNew = attachedEntrenoListNew;
            atleta.setEntrenoList(entrenoListNew);
            atleta = em.merge(atleta);
            if (codPreparadorOld != null && !codPreparadorOld.equals(codPreparadorNew)) {
                codPreparadorOld.getAtletaList().remove(atleta);
                codPreparadorOld = em.merge(codPreparadorOld);
            }
            if (codPreparadorNew != null && !codPreparadorNew.equals(codPreparadorOld)) {
                codPreparadorNew.getAtletaList().add(atleta);
                codPreparadorNew = em.merge(codPreparadorNew);
            }
            for (Recuperacion recuperacionListOldRecuperacion : recuperacionListOld) {
                if (!recuperacionListNew.contains(recuperacionListOldRecuperacion)) {
                    recuperacionListOldRecuperacion.setCodAtleta(null);
                    recuperacionListOldRecuperacion = em.merge(recuperacionListOldRecuperacion);
                }
            }
            for (Recuperacion recuperacionListNewRecuperacion : recuperacionListNew) {
                if (!recuperacionListOld.contains(recuperacionListNewRecuperacion)) {
                    Atleta oldCodAtletaOfRecuperacionListNewRecuperacion = recuperacionListNewRecuperacion.getCodAtleta();
                    recuperacionListNewRecuperacion.setCodAtleta(atleta);
                    recuperacionListNewRecuperacion = em.merge(recuperacionListNewRecuperacion);
                    if (oldCodAtletaOfRecuperacionListNewRecuperacion != null && !oldCodAtletaOfRecuperacionListNewRecuperacion.equals(atleta)) {
                        oldCodAtletaOfRecuperacionListNewRecuperacion.getRecuperacionList().remove(recuperacionListNewRecuperacion);
                        oldCodAtletaOfRecuperacionListNewRecuperacion = em.merge(oldCodAtletaOfRecuperacionListNewRecuperacion);
                    }
                }
            }
            for (Peso pesoListNewPeso : pesoListNew) {
                if (!pesoListOld.contains(pesoListNewPeso)) {
                    Atleta oldAtletaOfPesoListNewPeso = pesoListNewPeso.getAtleta();
                    pesoListNewPeso.setAtleta(atleta);
                    pesoListNewPeso = em.merge(pesoListNewPeso);
                    if (oldAtletaOfPesoListNewPeso != null && !oldAtletaOfPesoListNewPeso.equals(atleta)) {
                        oldAtletaOfPesoListNewPeso.getPesoList().remove(pesoListNewPeso);
                        oldAtletaOfPesoListNewPeso = em.merge(oldAtletaOfPesoListNewPeso);
                    }
                }
            }
            for (Imagen imagenListOldImagen : imagenListOld) {
                if (!imagenListNew.contains(imagenListOldImagen)) {
                    imagenListOldImagen.setCodAtleta(null);
                    imagenListOldImagen = em.merge(imagenListOldImagen);
                }
            }
            for (Imagen imagenListNewImagen : imagenListNew) {
                if (!imagenListOld.contains(imagenListNewImagen)) {
                    Atleta oldCodAtletaOfImagenListNewImagen = imagenListNewImagen.getCodAtleta();
                    imagenListNewImagen.setCodAtleta(atleta);
                    imagenListNewImagen = em.merge(imagenListNewImagen);
                    if (oldCodAtletaOfImagenListNewImagen != null && !oldCodAtletaOfImagenListNewImagen.equals(atleta)) {
                        oldCodAtletaOfImagenListNewImagen.getImagenList().remove(imagenListNewImagen);
                        oldCodAtletaOfImagenListNewImagen = em.merge(oldCodAtletaOfImagenListNewImagen);
                    }
                }
            }
            for (Valoracion valoracionListOldValoracion : valoracionListOld) {
                if (!valoracionListNew.contains(valoracionListOldValoracion)) {
                    valoracionListOldValoracion.setCodAtleta(null);
                    valoracionListOldValoracion = em.merge(valoracionListOldValoracion);
                }
            }
            for (Valoracion valoracionListNewValoracion : valoracionListNew) {
                if (!valoracionListOld.contains(valoracionListNewValoracion)) {
                    Atleta oldCodAtletaOfValoracionListNewValoracion = valoracionListNewValoracion.getCodAtleta();
                    valoracionListNewValoracion.setCodAtleta(atleta);
                    valoracionListNewValoracion = em.merge(valoracionListNewValoracion);
                    if (oldCodAtletaOfValoracionListNewValoracion != null && !oldCodAtletaOfValoracionListNewValoracion.equals(atleta)) {
                        oldCodAtletaOfValoracionListNewValoracion.getValoracionList().remove(valoracionListNewValoracion);
                        oldCodAtletaOfValoracionListNewValoracion = em.merge(oldCodAtletaOfValoracionListNewValoracion);
                    }
                }
            }
            for (Pago pagoListOldPago : pagoListOld) {
                if (!pagoListNew.contains(pagoListOldPago)) {
                    pagoListOldPago.setCodAtleta(null);
                    pagoListOldPago = em.merge(pagoListOldPago);
                }
            }
            for (Pago pagoListNewPago : pagoListNew) {
                if (!pagoListOld.contains(pagoListNewPago)) {
                    Atleta oldCodAtletaOfPagoListNewPago = pagoListNewPago.getCodAtleta();
                    pagoListNewPago.setCodAtleta(atleta);
                    pagoListNewPago = em.merge(pagoListNewPago);
                    if (oldCodAtletaOfPagoListNewPago != null && !oldCodAtletaOfPagoListNewPago.equals(atleta)) {
                        oldCodAtletaOfPagoListNewPago.getPagoList().remove(pagoListNewPago);
                        oldCodAtletaOfPagoListNewPago = em.merge(oldCodAtletaOfPagoListNewPago);
                    }
                }
            }
            for (Exito exitoListOldExito : exitoListOld) {
                if (!exitoListNew.contains(exitoListOldExito)) {
                    exitoListOldExito.setCodAtleta(null);
                    exitoListOldExito = em.merge(exitoListOldExito);
                }
            }
            for (Exito exitoListNewExito : exitoListNew) {
                if (!exitoListOld.contains(exitoListNewExito)) {
                    Atleta oldCodAtletaOfExitoListNewExito = exitoListNewExito.getCodAtleta();
                    exitoListNewExito.setCodAtleta(atleta);
                    exitoListNewExito = em.merge(exitoListNewExito);
                    if (oldCodAtletaOfExitoListNewExito != null && !oldCodAtletaOfExitoListNewExito.equals(atleta)) {
                        oldCodAtletaOfExitoListNewExito.getExitoList().remove(exitoListNewExito);
                        oldCodAtletaOfExitoListNewExito = em.merge(oldCodAtletaOfExitoListNewExito);
                    }
                }
            }
            for (Mensaje mensajeListOldMensaje : mensajeListOld) {
                if (!mensajeListNew.contains(mensajeListOldMensaje)) {
                    mensajeListOldMensaje.setCodAtleta(null);
                    mensajeListOldMensaje = em.merge(mensajeListOldMensaje);
                }
            }
            for (Mensaje mensajeListNewMensaje : mensajeListNew) {
                if (!mensajeListOld.contains(mensajeListNewMensaje)) {
                    Atleta oldCodAtletaOfMensajeListNewMensaje = mensajeListNewMensaje.getCodAtleta();
                    mensajeListNewMensaje.setCodAtleta(atleta);
                    mensajeListNewMensaje = em.merge(mensajeListNewMensaje);
                    if (oldCodAtletaOfMensajeListNewMensaje != null && !oldCodAtletaOfMensajeListNewMensaje.equals(atleta)) {
                        oldCodAtletaOfMensajeListNewMensaje.getMensajeList().remove(mensajeListNewMensaje);
                        oldCodAtletaOfMensajeListNewMensaje = em.merge(oldCodAtletaOfMensajeListNewMensaje);
                    }
                }
            }
            for (Entreno entrenoListOldEntreno : entrenoListOld) {
                if (!entrenoListNew.contains(entrenoListOldEntreno)) {
                    entrenoListOldEntreno.setCodAtleta(null);
                    entrenoListOldEntreno = em.merge(entrenoListOldEntreno);
                }
            }
            for (Entreno entrenoListNewEntreno : entrenoListNew) {
                if (!entrenoListOld.contains(entrenoListNewEntreno)) {
                    Atleta oldCodAtletaOfEntrenoListNewEntreno = entrenoListNewEntreno.getCodAtleta();
                    entrenoListNewEntreno.setCodAtleta(atleta);
                    entrenoListNewEntreno = em.merge(entrenoListNewEntreno);
                    if (oldCodAtletaOfEntrenoListNewEntreno != null && !oldCodAtletaOfEntrenoListNewEntreno.equals(atleta)) {
                        oldCodAtletaOfEntrenoListNewEntreno.getEntrenoList().remove(entrenoListNewEntreno);
                        oldCodAtletaOfEntrenoListNewEntreno = em.merge(oldCodAtletaOfEntrenoListNewEntreno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = atleta.getCodAtleta();
                if (findAtleta(id) == null) {
                    throw new NonexistentEntityException("The atleta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta atleta;
            try {
                atleta = em.getReference(Atleta.class, id);
                atleta.getCodAtleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atleta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Peso> pesoListOrphanCheck = atleta.getPesoList();
            for (Peso pesoListOrphanCheckPeso : pesoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Atleta (" + atleta + ") cannot be destroyed since the Peso " + pesoListOrphanCheckPeso + " in its pesoList field has a non-nullable atleta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Preparador codPreparador = atleta.getCodPreparador();
            if (codPreparador != null) {
                codPreparador.getAtletaList().remove(atleta);
                codPreparador = em.merge(codPreparador);
            }
            List<Recuperacion> recuperacionList = atleta.getRecuperacionList();
            for (Recuperacion recuperacionListRecuperacion : recuperacionList) {
                recuperacionListRecuperacion.setCodAtleta(null);
                recuperacionListRecuperacion = em.merge(recuperacionListRecuperacion);
            }
            List<Imagen> imagenList = atleta.getImagenList();
            for (Imagen imagenListImagen : imagenList) {
                imagenListImagen.setCodAtleta(null);
                imagenListImagen = em.merge(imagenListImagen);
            }
            List<Valoracion> valoracionList = atleta.getValoracionList();
            for (Valoracion valoracionListValoracion : valoracionList) {
                valoracionListValoracion.setCodAtleta(null);
                valoracionListValoracion = em.merge(valoracionListValoracion);
            }
            List<Pago> pagoList = atleta.getPagoList();
            for (Pago pagoListPago : pagoList) {
                pagoListPago.setCodAtleta(null);
                pagoListPago = em.merge(pagoListPago);
            }
            List<Exito> exitoList = atleta.getExitoList();
            for (Exito exitoListExito : exitoList) {
                exitoListExito.setCodAtleta(null);
                exitoListExito = em.merge(exitoListExito);
            }
            List<Mensaje> mensajeList = atleta.getMensajeList();
            for (Mensaje mensajeListMensaje : mensajeList) {
                mensajeListMensaje.setCodAtleta(null);
                mensajeListMensaje = em.merge(mensajeListMensaje);
            }
            List<Entreno> entrenoList = atleta.getEntrenoList();
            for (Entreno entrenoListEntreno : entrenoList) {
                entrenoListEntreno.setCodAtleta(null);
                entrenoListEntreno = em.merge(entrenoListEntreno);
            }
            em.remove(atleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Atleta> findAtletaEntities() {
        return findAtletaEntities(true, -1, -1);
    }

    public List<Atleta> findAtletaEntities(int maxResults, int firstResult) {
        return findAtletaEntities(false, maxResults, firstResult);
    }

    private List<Atleta> findAtletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Atleta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Atleta findAtleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Atleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Atleta> rt = cq.from(Atleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Atleta atletaByNomUsuario(String nomUsuario) {
        EntityManager em = getEntityManager();
        List<Atleta> lista = null;
        try {

            Query qu = em.createNamedQuery("Atleta.findByNomUsuario", Atleta.class);
            qu.setParameter("nomUsuario", nomUsuario);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }

    public Long atletaNumByEmail(String email) {
        EntityManager em = getEntityManager();
        List<Long> lista = null;
        try {

            Query qu = em.createNamedQuery("Atleta.atletaNumByEmail", Atleta.class);
            qu.setParameter("email", email);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }

    public Long atletaNumByNomUsuario(String nomUsuario) {
        EntityManager em = getEntityManager();
        List<Long> lista = null;
        try {

            Query qu = em.createNamedQuery("Atleta.atletaNumByNomUsuario", Atleta.class);
            qu.setParameter("nomUsuario", nomUsuario);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }

}
