/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Atleta;
import java.util.ArrayList;
import java.util.List;
import DTO.Recuperacion;
import DTO.Valoracion;
import DTO.Exito;
import DTO.Mensaje;
import DTO.Entreno;
import DTO.Preparador;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class PreparadorJpaController implements Serializable {

    public PreparadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preparador preparador) {
        if (preparador.getAtletaList() == null) {
            preparador.setAtletaList(new ArrayList<Atleta>());
        }
        if (preparador.getRecuperacionList() == null) {
            preparador.setRecuperacionList(new ArrayList<Recuperacion>());
        }
        if (preparador.getValoracionList() == null) {
            preparador.setValoracionList(new ArrayList<Valoracion>());
        }
        if (preparador.getExitoList() == null) {
            preparador.setExitoList(new ArrayList<Exito>());
        }
        if (preparador.getMensajeList() == null) {
            preparador.setMensajeList(new ArrayList<Mensaje>());
        }
        if (preparador.getEntrenoList() == null) {
            preparador.setEntrenoList(new ArrayList<Entreno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : preparador.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getCodAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            preparador.setAtletaList(attachedAtletaList);
            List<Recuperacion> attachedRecuperacionList = new ArrayList<Recuperacion>();
            for (Recuperacion recuperacionListRecuperacionToAttach : preparador.getRecuperacionList()) {
                recuperacionListRecuperacionToAttach = em.getReference(recuperacionListRecuperacionToAttach.getClass(), recuperacionListRecuperacionToAttach.getCodRecuperacion());
                attachedRecuperacionList.add(recuperacionListRecuperacionToAttach);
            }
            preparador.setRecuperacionList(attachedRecuperacionList);
            List<Valoracion> attachedValoracionList = new ArrayList<Valoracion>();
            for (Valoracion valoracionListValoracionToAttach : preparador.getValoracionList()) {
                valoracionListValoracionToAttach = em.getReference(valoracionListValoracionToAttach.getClass(), valoracionListValoracionToAttach.getCodValoracion());
                attachedValoracionList.add(valoracionListValoracionToAttach);
            }
            preparador.setValoracionList(attachedValoracionList);
            List<Exito> attachedExitoList = new ArrayList<Exito>();
            for (Exito exitoListExitoToAttach : preparador.getExitoList()) {
                exitoListExitoToAttach = em.getReference(exitoListExitoToAttach.getClass(), exitoListExitoToAttach.getCodExito());
                attachedExitoList.add(exitoListExitoToAttach);
            }
            preparador.setExitoList(attachedExitoList);
            List<Mensaje> attachedMensajeList = new ArrayList<Mensaje>();
            for (Mensaje mensajeListMensajeToAttach : preparador.getMensajeList()) {
                mensajeListMensajeToAttach = em.getReference(mensajeListMensajeToAttach.getClass(), mensajeListMensajeToAttach.getCodMensaje());
                attachedMensajeList.add(mensajeListMensajeToAttach);
            }
            preparador.setMensajeList(attachedMensajeList);
            List<Entreno> attachedEntrenoList = new ArrayList<Entreno>();
            for (Entreno entrenoListEntrenoToAttach : preparador.getEntrenoList()) {
                entrenoListEntrenoToAttach = em.getReference(entrenoListEntrenoToAttach.getClass(), entrenoListEntrenoToAttach.getCodEntreno());
                attachedEntrenoList.add(entrenoListEntrenoToAttach);
            }
            preparador.setEntrenoList(attachedEntrenoList);
            em.persist(preparador);
            for (Atleta atletaListAtleta : preparador.getAtletaList()) {
                Preparador oldCodPreparadorOfAtletaListAtleta = atletaListAtleta.getCodPreparador();
                atletaListAtleta.setCodPreparador(preparador);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldCodPreparadorOfAtletaListAtleta != null) {
                    oldCodPreparadorOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldCodPreparadorOfAtletaListAtleta = em.merge(oldCodPreparadorOfAtletaListAtleta);
                }
            }
            for (Recuperacion recuperacionListRecuperacion : preparador.getRecuperacionList()) {
                Preparador oldCodPreparadorOfRecuperacionListRecuperacion = recuperacionListRecuperacion.getCodPreparador();
                recuperacionListRecuperacion.setCodPreparador(preparador);
                recuperacionListRecuperacion = em.merge(recuperacionListRecuperacion);
                if (oldCodPreparadorOfRecuperacionListRecuperacion != null) {
                    oldCodPreparadorOfRecuperacionListRecuperacion.getRecuperacionList().remove(recuperacionListRecuperacion);
                    oldCodPreparadorOfRecuperacionListRecuperacion = em.merge(oldCodPreparadorOfRecuperacionListRecuperacion);
                }
            }
            for (Valoracion valoracionListValoracion : preparador.getValoracionList()) {
                Preparador oldCodPreparadorOfValoracionListValoracion = valoracionListValoracion.getCodPreparador();
                valoracionListValoracion.setCodPreparador(preparador);
                valoracionListValoracion = em.merge(valoracionListValoracion);
                if (oldCodPreparadorOfValoracionListValoracion != null) {
                    oldCodPreparadorOfValoracionListValoracion.getValoracionList().remove(valoracionListValoracion);
                    oldCodPreparadorOfValoracionListValoracion = em.merge(oldCodPreparadorOfValoracionListValoracion);
                }
            }
            for (Exito exitoListExito : preparador.getExitoList()) {
                Preparador oldCodPreparadorOfExitoListExito = exitoListExito.getCodPreparador();
                exitoListExito.setCodPreparador(preparador);
                exitoListExito = em.merge(exitoListExito);
                if (oldCodPreparadorOfExitoListExito != null) {
                    oldCodPreparadorOfExitoListExito.getExitoList().remove(exitoListExito);
                    oldCodPreparadorOfExitoListExito = em.merge(oldCodPreparadorOfExitoListExito);
                }
            }
            for (Mensaje mensajeListMensaje : preparador.getMensajeList()) {
                Preparador oldCodPreparadorOfMensajeListMensaje = mensajeListMensaje.getCodPreparador();
                mensajeListMensaje.setCodPreparador(preparador);
                mensajeListMensaje = em.merge(mensajeListMensaje);
                if (oldCodPreparadorOfMensajeListMensaje != null) {
                    oldCodPreparadorOfMensajeListMensaje.getMensajeList().remove(mensajeListMensaje);
                    oldCodPreparadorOfMensajeListMensaje = em.merge(oldCodPreparadorOfMensajeListMensaje);
                }
            }
            for (Entreno entrenoListEntreno : preparador.getEntrenoList()) {
                Preparador oldCodPreparadorOfEntrenoListEntreno = entrenoListEntreno.getCodPreparador();
                entrenoListEntreno.setCodPreparador(preparador);
                entrenoListEntreno = em.merge(entrenoListEntreno);
                if (oldCodPreparadorOfEntrenoListEntreno != null) {
                    oldCodPreparadorOfEntrenoListEntreno.getEntrenoList().remove(entrenoListEntreno);
                    oldCodPreparadorOfEntrenoListEntreno = em.merge(oldCodPreparadorOfEntrenoListEntreno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preparador preparador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preparador persistentPreparador = em.find(Preparador.class, preparador.getCodPreparador());
            List<Atleta> atletaListOld = persistentPreparador.getAtletaList();
            List<Atleta> atletaListNew = preparador.getAtletaList();
            List<Recuperacion> recuperacionListOld = persistentPreparador.getRecuperacionList();
            List<Recuperacion> recuperacionListNew = preparador.getRecuperacionList();
            List<Valoracion> valoracionListOld = persistentPreparador.getValoracionList();
            List<Valoracion> valoracionListNew = preparador.getValoracionList();
            List<Exito> exitoListOld = persistentPreparador.getExitoList();
            List<Exito> exitoListNew = preparador.getExitoList();
            List<Mensaje> mensajeListOld = persistentPreparador.getMensajeList();
            List<Mensaje> mensajeListNew = preparador.getMensajeList();
            List<Entreno> entrenoListOld = persistentPreparador.getEntrenoList();
            List<Entreno> entrenoListNew = preparador.getEntrenoList();
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getCodAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            preparador.setAtletaList(atletaListNew);
            List<Recuperacion> attachedRecuperacionListNew = new ArrayList<Recuperacion>();
            for (Recuperacion recuperacionListNewRecuperacionToAttach : recuperacionListNew) {
                recuperacionListNewRecuperacionToAttach = em.getReference(recuperacionListNewRecuperacionToAttach.getClass(), recuperacionListNewRecuperacionToAttach.getCodRecuperacion());
                attachedRecuperacionListNew.add(recuperacionListNewRecuperacionToAttach);
            }
            recuperacionListNew = attachedRecuperacionListNew;
            preparador.setRecuperacionList(recuperacionListNew);
            List<Valoracion> attachedValoracionListNew = new ArrayList<Valoracion>();
            for (Valoracion valoracionListNewValoracionToAttach : valoracionListNew) {
                valoracionListNewValoracionToAttach = em.getReference(valoracionListNewValoracionToAttach.getClass(), valoracionListNewValoracionToAttach.getCodValoracion());
                attachedValoracionListNew.add(valoracionListNewValoracionToAttach);
            }
            valoracionListNew = attachedValoracionListNew;
            preparador.setValoracionList(valoracionListNew);
            List<Exito> attachedExitoListNew = new ArrayList<Exito>();
            for (Exito exitoListNewExitoToAttach : exitoListNew) {
                exitoListNewExitoToAttach = em.getReference(exitoListNewExitoToAttach.getClass(), exitoListNewExitoToAttach.getCodExito());
                attachedExitoListNew.add(exitoListNewExitoToAttach);
            }
            exitoListNew = attachedExitoListNew;
            preparador.setExitoList(exitoListNew);
            List<Mensaje> attachedMensajeListNew = new ArrayList<Mensaje>();
            for (Mensaje mensajeListNewMensajeToAttach : mensajeListNew) {
                mensajeListNewMensajeToAttach = em.getReference(mensajeListNewMensajeToAttach.getClass(), mensajeListNewMensajeToAttach.getCodMensaje());
                attachedMensajeListNew.add(mensajeListNewMensajeToAttach);
            }
            mensajeListNew = attachedMensajeListNew;
            preparador.setMensajeList(mensajeListNew);
            List<Entreno> attachedEntrenoListNew = new ArrayList<Entreno>();
            for (Entreno entrenoListNewEntrenoToAttach : entrenoListNew) {
                entrenoListNewEntrenoToAttach = em.getReference(entrenoListNewEntrenoToAttach.getClass(), entrenoListNewEntrenoToAttach.getCodEntreno());
                attachedEntrenoListNew.add(entrenoListNewEntrenoToAttach);
            }
            entrenoListNew = attachedEntrenoListNew;
            preparador.setEntrenoList(entrenoListNew);
            preparador = em.merge(preparador);
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setCodPreparador(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Preparador oldCodPreparadorOfAtletaListNewAtleta = atletaListNewAtleta.getCodPreparador();
                    atletaListNewAtleta.setCodPreparador(preparador);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldCodPreparadorOfAtletaListNewAtleta != null && !oldCodPreparadorOfAtletaListNewAtleta.equals(preparador)) {
                        oldCodPreparadorOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldCodPreparadorOfAtletaListNewAtleta = em.merge(oldCodPreparadorOfAtletaListNewAtleta);
                    }
                }
            }
            for (Recuperacion recuperacionListOldRecuperacion : recuperacionListOld) {
                if (!recuperacionListNew.contains(recuperacionListOldRecuperacion)) {
                    recuperacionListOldRecuperacion.setCodPreparador(null);
                    recuperacionListOldRecuperacion = em.merge(recuperacionListOldRecuperacion);
                }
            }
            for (Recuperacion recuperacionListNewRecuperacion : recuperacionListNew) {
                if (!recuperacionListOld.contains(recuperacionListNewRecuperacion)) {
                    Preparador oldCodPreparadorOfRecuperacionListNewRecuperacion = recuperacionListNewRecuperacion.getCodPreparador();
                    recuperacionListNewRecuperacion.setCodPreparador(preparador);
                    recuperacionListNewRecuperacion = em.merge(recuperacionListNewRecuperacion);
                    if (oldCodPreparadorOfRecuperacionListNewRecuperacion != null && !oldCodPreparadorOfRecuperacionListNewRecuperacion.equals(preparador)) {
                        oldCodPreparadorOfRecuperacionListNewRecuperacion.getRecuperacionList().remove(recuperacionListNewRecuperacion);
                        oldCodPreparadorOfRecuperacionListNewRecuperacion = em.merge(oldCodPreparadorOfRecuperacionListNewRecuperacion);
                    }
                }
            }
            for (Valoracion valoracionListOldValoracion : valoracionListOld) {
                if (!valoracionListNew.contains(valoracionListOldValoracion)) {
                    valoracionListOldValoracion.setCodPreparador(null);
                    valoracionListOldValoracion = em.merge(valoracionListOldValoracion);
                }
            }
            for (Valoracion valoracionListNewValoracion : valoracionListNew) {
                if (!valoracionListOld.contains(valoracionListNewValoracion)) {
                    Preparador oldCodPreparadorOfValoracionListNewValoracion = valoracionListNewValoracion.getCodPreparador();
                    valoracionListNewValoracion.setCodPreparador(preparador);
                    valoracionListNewValoracion = em.merge(valoracionListNewValoracion);
                    if (oldCodPreparadorOfValoracionListNewValoracion != null && !oldCodPreparadorOfValoracionListNewValoracion.equals(preparador)) {
                        oldCodPreparadorOfValoracionListNewValoracion.getValoracionList().remove(valoracionListNewValoracion);
                        oldCodPreparadorOfValoracionListNewValoracion = em.merge(oldCodPreparadorOfValoracionListNewValoracion);
                    }
                }
            }
            for (Exito exitoListOldExito : exitoListOld) {
                if (!exitoListNew.contains(exitoListOldExito)) {
                    exitoListOldExito.setCodPreparador(null);
                    exitoListOldExito = em.merge(exitoListOldExito);
                }
            }
            for (Exito exitoListNewExito : exitoListNew) {
                if (!exitoListOld.contains(exitoListNewExito)) {
                    Preparador oldCodPreparadorOfExitoListNewExito = exitoListNewExito.getCodPreparador();
                    exitoListNewExito.setCodPreparador(preparador);
                    exitoListNewExito = em.merge(exitoListNewExito);
                    if (oldCodPreparadorOfExitoListNewExito != null && !oldCodPreparadorOfExitoListNewExito.equals(preparador)) {
                        oldCodPreparadorOfExitoListNewExito.getExitoList().remove(exitoListNewExito);
                        oldCodPreparadorOfExitoListNewExito = em.merge(oldCodPreparadorOfExitoListNewExito);
                    }
                }
            }
            for (Mensaje mensajeListOldMensaje : mensajeListOld) {
                if (!mensajeListNew.contains(mensajeListOldMensaje)) {
                    mensajeListOldMensaje.setCodPreparador(null);
                    mensajeListOldMensaje = em.merge(mensajeListOldMensaje);
                }
            }
            for (Mensaje mensajeListNewMensaje : mensajeListNew) {
                if (!mensajeListOld.contains(mensajeListNewMensaje)) {
                    Preparador oldCodPreparadorOfMensajeListNewMensaje = mensajeListNewMensaje.getCodPreparador();
                    mensajeListNewMensaje.setCodPreparador(preparador);
                    mensajeListNewMensaje = em.merge(mensajeListNewMensaje);
                    if (oldCodPreparadorOfMensajeListNewMensaje != null && !oldCodPreparadorOfMensajeListNewMensaje.equals(preparador)) {
                        oldCodPreparadorOfMensajeListNewMensaje.getMensajeList().remove(mensajeListNewMensaje);
                        oldCodPreparadorOfMensajeListNewMensaje = em.merge(oldCodPreparadorOfMensajeListNewMensaje);
                    }
                }
            }
            for (Entreno entrenoListOldEntreno : entrenoListOld) {
                if (!entrenoListNew.contains(entrenoListOldEntreno)) {
                    entrenoListOldEntreno.setCodPreparador(null);
                    entrenoListOldEntreno = em.merge(entrenoListOldEntreno);
                }
            }
            for (Entreno entrenoListNewEntreno : entrenoListNew) {
                if (!entrenoListOld.contains(entrenoListNewEntreno)) {
                    Preparador oldCodPreparadorOfEntrenoListNewEntreno = entrenoListNewEntreno.getCodPreparador();
                    entrenoListNewEntreno.setCodPreparador(preparador);
                    entrenoListNewEntreno = em.merge(entrenoListNewEntreno);
                    if (oldCodPreparadorOfEntrenoListNewEntreno != null && !oldCodPreparadorOfEntrenoListNewEntreno.equals(preparador)) {
                        oldCodPreparadorOfEntrenoListNewEntreno.getEntrenoList().remove(entrenoListNewEntreno);
                        oldCodPreparadorOfEntrenoListNewEntreno = em.merge(oldCodPreparadorOfEntrenoListNewEntreno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preparador.getCodPreparador();
                if (findPreparador(id) == null) {
                    throw new NonexistentEntityException("The preparador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preparador preparador;
            try {
                preparador = em.getReference(Preparador.class, id);
                preparador.getCodPreparador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preparador with id " + id + " no longer exists.", enfe);
            }
            List<Atleta> atletaList = preparador.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setCodPreparador(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            List<Recuperacion> recuperacionList = preparador.getRecuperacionList();
            for (Recuperacion recuperacionListRecuperacion : recuperacionList) {
                recuperacionListRecuperacion.setCodPreparador(null);
                recuperacionListRecuperacion = em.merge(recuperacionListRecuperacion);
            }
            List<Valoracion> valoracionList = preparador.getValoracionList();
            for (Valoracion valoracionListValoracion : valoracionList) {
                valoracionListValoracion.setCodPreparador(null);
                valoracionListValoracion = em.merge(valoracionListValoracion);
            }
            List<Exito> exitoList = preparador.getExitoList();
            for (Exito exitoListExito : exitoList) {
                exitoListExito.setCodPreparador(null);
                exitoListExito = em.merge(exitoListExito);
            }
            List<Mensaje> mensajeList = preparador.getMensajeList();
            for (Mensaje mensajeListMensaje : mensajeList) {
                mensajeListMensaje.setCodPreparador(null);
                mensajeListMensaje = em.merge(mensajeListMensaje);
            }
            List<Entreno> entrenoList = preparador.getEntrenoList();
            for (Entreno entrenoListEntreno : entrenoList) {
                entrenoListEntreno.setCodPreparador(null);
                entrenoListEntreno = em.merge(entrenoListEntreno);
            }
            em.remove(preparador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preparador> findPreparadorEntities() {
        return findPreparadorEntities(true, -1, -1);
    }

    public List<Preparador> findPreparadorEntities(int maxResults, int firstResult) {
        return findPreparadorEntities(false, maxResults, firstResult);
    }

    private List<Preparador> findPreparadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preparador.class));
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

    public Preparador findPreparador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preparador.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreparadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preparador> rt = cq.from(Preparador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Preparador preparadorByNomUsuario(String nomUsuario) {
        EntityManager em = getEntityManager();
        List<Preparador> lista = null;
        try {

            Query qu = em.createNamedQuery("Preparador.findByNomUsuario", Preparador.class);
            qu.setParameter("nomUsuario", nomUsuario);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }

}
