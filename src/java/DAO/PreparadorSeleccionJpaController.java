/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.PreparadorSeleccion;
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
public class PreparadorSeleccionJpaController implements Serializable {

    public PreparadorSeleccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PreparadorSeleccion preparadorSeleccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(preparadorSeleccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PreparadorSeleccion preparadorSeleccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            preparadorSeleccion = em.merge(preparadorSeleccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preparadorSeleccion.getCodPreparadorSeleccion();
                if (findPreparadorSeleccion(id) == null) {
                    throw new NonexistentEntityException("The preparadorSeleccion with id " + id + " no longer exists.");
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
            PreparadorSeleccion preparadorSeleccion;
            try {
                preparadorSeleccion = em.getReference(PreparadorSeleccion.class, id);
                preparadorSeleccion.getCodPreparadorSeleccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preparadorSeleccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(preparadorSeleccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PreparadorSeleccion> findPreparadorSeleccionEntities() {
        return findPreparadorSeleccionEntities(true, -1, -1);
    }

    public List<PreparadorSeleccion> findPreparadorSeleccionEntities(int maxResults, int firstResult) {
        return findPreparadorSeleccionEntities(false, maxResults, firstResult);
    }

    private List<PreparadorSeleccion> findPreparadorSeleccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PreparadorSeleccion.class));
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

    public PreparadorSeleccion findPreparadorSeleccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PreparadorSeleccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreparadorSeleccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PreparadorSeleccion> rt = cq.from(PreparadorSeleccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
