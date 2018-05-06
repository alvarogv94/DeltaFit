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
import DTO.Mensaje;
import DTO.Preparador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class MensajeJpaController implements Serializable {

    public MensajeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mensaje mensaje) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta codAtleta = mensaje.getCodAtleta();
            if (codAtleta != null) {
                codAtleta = em.getReference(codAtleta.getClass(), codAtleta.getCodAtleta());
                mensaje.setCodAtleta(codAtleta);
            }
            Preparador codPreparador = mensaje.getCodPreparador();
            if (codPreparador != null) {
                codPreparador = em.getReference(codPreparador.getClass(), codPreparador.getCodPreparador());
                mensaje.setCodPreparador(codPreparador);
            }
            em.persist(mensaje);
            if (codAtleta != null) {
                codAtleta.getMensajeList().add(mensaje);
                codAtleta = em.merge(codAtleta);
            }
            if (codPreparador != null) {
                codPreparador.getMensajeList().add(mensaje);
                codPreparador = em.merge(codPreparador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mensaje mensaje) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensaje persistentMensaje = em.find(Mensaje.class, mensaje.getCodMensaje());
            Atleta codAtletaOld = persistentMensaje.getCodAtleta();
            Atleta codAtletaNew = mensaje.getCodAtleta();
            Preparador codPreparadorOld = persistentMensaje.getCodPreparador();
            Preparador codPreparadorNew = mensaje.getCodPreparador();
            if (codAtletaNew != null) {
                codAtletaNew = em.getReference(codAtletaNew.getClass(), codAtletaNew.getCodAtleta());
                mensaje.setCodAtleta(codAtletaNew);
            }
            if (codPreparadorNew != null) {
                codPreparadorNew = em.getReference(codPreparadorNew.getClass(), codPreparadorNew.getCodPreparador());
                mensaje.setCodPreparador(codPreparadorNew);
            }
            mensaje = em.merge(mensaje);
            if (codAtletaOld != null && !codAtletaOld.equals(codAtletaNew)) {
                codAtletaOld.getMensajeList().remove(mensaje);
                codAtletaOld = em.merge(codAtletaOld);
            }
            if (codAtletaNew != null && !codAtletaNew.equals(codAtletaOld)) {
                codAtletaNew.getMensajeList().add(mensaje);
                codAtletaNew = em.merge(codAtletaNew);
            }
            if (codPreparadorOld != null && !codPreparadorOld.equals(codPreparadorNew)) {
                codPreparadorOld.getMensajeList().remove(mensaje);
                codPreparadorOld = em.merge(codPreparadorOld);
            }
            if (codPreparadorNew != null && !codPreparadorNew.equals(codPreparadorOld)) {
                codPreparadorNew.getMensajeList().add(mensaje);
                codPreparadorNew = em.merge(codPreparadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mensaje.getCodMensaje();
                if (findMensaje(id) == null) {
                    throw new NonexistentEntityException("The mensaje with id " + id + " no longer exists.");
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
            Mensaje mensaje;
            try {
                mensaje = em.getReference(Mensaje.class, id);
                mensaje.getCodMensaje();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensaje with id " + id + " no longer exists.", enfe);
            }
            Atleta codAtleta = mensaje.getCodAtleta();
            if (codAtleta != null) {
                codAtleta.getMensajeList().remove(mensaje);
                codAtleta = em.merge(codAtleta);
            }
            Preparador codPreparador = mensaje.getCodPreparador();
            if (codPreparador != null) {
                codPreparador.getMensajeList().remove(mensaje);
                codPreparador = em.merge(codPreparador);
            }
            em.remove(mensaje);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mensaje> findMensajeEntities() {
        return findMensajeEntities(true, -1, -1);
    }

    public List<Mensaje> findMensajeEntities(int maxResults, int firstResult) {
        return findMensajeEntities(false, maxResults, firstResult);
    }

    private List<Mensaje> findMensajeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mensaje.class));
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

    public Mensaje findMensaje(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mensaje.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensajeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mensaje> rt = cq.from(Mensaje.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
