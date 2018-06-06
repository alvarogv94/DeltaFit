/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.PreparacionAtleta;
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
public class PreparacionAtletaJpaController implements Serializable {

    public PreparacionAtletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PreparacionAtleta preparacionAtleta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(preparacionAtleta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreparacionAtleta(preparacionAtleta.getCodAtleta()) != null) {
                throw new PreexistingEntityException("PreparacionAtleta " + preparacionAtleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PreparacionAtleta preparacionAtleta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            preparacionAtleta = em.merge(preparacionAtleta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = preparacionAtleta.getCodAtleta();
                if (findPreparacionAtleta(id) == null) {
                    throw new NonexistentEntityException("The preparacionAtleta with id " + id + " no longer exists.");
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
            PreparacionAtleta preparacionAtleta;
            try {
                preparacionAtleta = em.getReference(PreparacionAtleta.class, id);
                preparacionAtleta.getCodAtleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preparacionAtleta with id " + id + " no longer exists.", enfe);
            }
            em.remove(preparacionAtleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PreparacionAtleta> findPreparacionAtletaEntities() {
        return findPreparacionAtletaEntities(true, -1, -1);
    }

    public List<PreparacionAtleta> findPreparacionAtletaEntities(int maxResults, int firstResult) {
        return findPreparacionAtletaEntities(false, maxResults, firstResult);
    }

    private List<PreparacionAtleta> findPreparacionAtletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PreparacionAtleta.class));
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

    public PreparacionAtleta findPreparacionAtleta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PreparacionAtleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreparacionAtletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PreparacionAtleta> rt = cq.from(PreparacionAtleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
