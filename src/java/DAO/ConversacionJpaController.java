/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Conversacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alvaro
 */
public class ConversacionJpaController implements Serializable {

    public ConversacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conversacion conversacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(conversacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConversacion(conversacion.getCodAtleta()) != null) {
                throw new PreexistingEntityException("Conversacion " + conversacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conversacion conversacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            conversacion = em.merge(conversacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = conversacion.getCodAtleta();
                if (findConversacion(id) == null) {
                    throw new NonexistentEntityException("The conversacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conversacion conversacion;
            try {
                conversacion = em.getReference(Conversacion.class, id);
                conversacion.getCodAtleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conversacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(conversacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conversacion> findConversacionEntities() {
        return findConversacionEntities(true, -1, -1);
    }

    public List<Conversacion> findConversacionEntities(int maxResults, int firstResult) {
        return findConversacionEntities(false, maxResults, firstResult);
    }

    private List<Conversacion> findConversacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conversacion.class));
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

    public Conversacion findConversacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conversacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getConversacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conversacion> rt = cq.from(Conversacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
