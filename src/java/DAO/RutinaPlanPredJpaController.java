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
import DTO.PlanPred;
import DTO.RutinaPlanPred;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class RutinaPlanPredJpaController implements Serializable {

    public RutinaPlanPredJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RutinaPlanPred rutinaPlanPred) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanPred codPlanPred = rutinaPlanPred.getCodPlanPred();
            if (codPlanPred != null) {
                codPlanPred = em.getReference(codPlanPred.getClass(), codPlanPred.getCodPlanPred());
                rutinaPlanPred.setCodPlanPred(codPlanPred);
            }
            em.persist(rutinaPlanPred);
            if (codPlanPred != null) {
                codPlanPred.getRutinaPlanPredList().add(rutinaPlanPred);
                codPlanPred = em.merge(codPlanPred);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RutinaPlanPred rutinaPlanPred) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RutinaPlanPred persistentRutinaPlanPred = em.find(RutinaPlanPred.class, rutinaPlanPred.getCodRutinaPlanPred());
            PlanPred codPlanPredOld = persistentRutinaPlanPred.getCodPlanPred();
            PlanPred codPlanPredNew = rutinaPlanPred.getCodPlanPred();
            if (codPlanPredNew != null) {
                codPlanPredNew = em.getReference(codPlanPredNew.getClass(), codPlanPredNew.getCodPlanPred());
                rutinaPlanPred.setCodPlanPred(codPlanPredNew);
            }
            rutinaPlanPred = em.merge(rutinaPlanPred);
            if (codPlanPredOld != null && !codPlanPredOld.equals(codPlanPredNew)) {
                codPlanPredOld.getRutinaPlanPredList().remove(rutinaPlanPred);
                codPlanPredOld = em.merge(codPlanPredOld);
            }
            if (codPlanPredNew != null && !codPlanPredNew.equals(codPlanPredOld)) {
                codPlanPredNew.getRutinaPlanPredList().add(rutinaPlanPred);
                codPlanPredNew = em.merge(codPlanPredNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rutinaPlanPred.getCodRutinaPlanPred();
                if (findRutinaPlanPred(id) == null) {
                    throw new NonexistentEntityException("The rutinaPlanPred with id " + id + " no longer exists.");
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
            RutinaPlanPred rutinaPlanPred;
            try {
                rutinaPlanPred = em.getReference(RutinaPlanPred.class, id);
                rutinaPlanPred.getCodRutinaPlanPred();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rutinaPlanPred with id " + id + " no longer exists.", enfe);
            }
            PlanPred codPlanPred = rutinaPlanPred.getCodPlanPred();
            if (codPlanPred != null) {
                codPlanPred.getRutinaPlanPredList().remove(rutinaPlanPred);
                codPlanPred = em.merge(codPlanPred);
            }
            em.remove(rutinaPlanPred);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RutinaPlanPred> findRutinaPlanPredEntities() {
        return findRutinaPlanPredEntities(true, -1, -1);
    }

    public List<RutinaPlanPred> findRutinaPlanPredEntities(int maxResults, int firstResult) {
        return findRutinaPlanPredEntities(false, maxResults, firstResult);
    }

    private List<RutinaPlanPred> findRutinaPlanPredEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RutinaPlanPred.class));
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

    public RutinaPlanPred findRutinaPlanPred(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RutinaPlanPred.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutinaPlanPredCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RutinaPlanPred> rt = cq.from(RutinaPlanPred.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
