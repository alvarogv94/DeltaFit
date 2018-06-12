/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.DietaPlanPred;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.PlanPred;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class DietaPlanPredJpaController implements Serializable {

    public DietaPlanPredJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DietaPlanPred dietaPlanPred) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanPred codPlanPred = dietaPlanPred.getCodPlanPred();
            if (codPlanPred != null) {
                codPlanPred = em.getReference(codPlanPred.getClass(), codPlanPred.getCodPlanPred());
                dietaPlanPred.setCodPlanPred(codPlanPred);
            }
            em.persist(dietaPlanPred);
            if (codPlanPred != null) {
                codPlanPred.getDietaPlanPredList().add(dietaPlanPred);
                codPlanPred = em.merge(codPlanPred);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DietaPlanPred dietaPlanPred) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DietaPlanPred persistentDietaPlanPred = em.find(DietaPlanPred.class, dietaPlanPred.getCodDietaPlanPred());
            PlanPred codPlanPredOld = persistentDietaPlanPred.getCodPlanPred();
            PlanPred codPlanPredNew = dietaPlanPred.getCodPlanPred();
            if (codPlanPredNew != null) {
                codPlanPredNew = em.getReference(codPlanPredNew.getClass(), codPlanPredNew.getCodPlanPred());
                dietaPlanPred.setCodPlanPred(codPlanPredNew);
            }
            dietaPlanPred = em.merge(dietaPlanPred);
            if (codPlanPredOld != null && !codPlanPredOld.equals(codPlanPredNew)) {
                codPlanPredOld.getDietaPlanPredList().remove(dietaPlanPred);
                codPlanPredOld = em.merge(codPlanPredOld);
            }
            if (codPlanPredNew != null && !codPlanPredNew.equals(codPlanPredOld)) {
                codPlanPredNew.getDietaPlanPredList().add(dietaPlanPred);
                codPlanPredNew = em.merge(codPlanPredNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dietaPlanPred.getCodDietaPlanPred();
                if (findDietaPlanPred(id) == null) {
                    throw new NonexistentEntityException("The dietaPlanPred with id " + id + " no longer exists.");
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
            DietaPlanPred dietaPlanPred;
            try {
                dietaPlanPred = em.getReference(DietaPlanPred.class, id);
                dietaPlanPred.getCodDietaPlanPred();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dietaPlanPred with id " + id + " no longer exists.", enfe);
            }
            PlanPred codPlanPred = dietaPlanPred.getCodPlanPred();
            if (codPlanPred != null) {
                codPlanPred.getDietaPlanPredList().remove(dietaPlanPred);
                codPlanPred = em.merge(codPlanPred);
            }
            em.remove(dietaPlanPred);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DietaPlanPred> findDietaPlanPredEntities() {
        return findDietaPlanPredEntities(true, -1, -1);
    }

    public List<DietaPlanPred> findDietaPlanPredEntities(int maxResults, int firstResult) {
        return findDietaPlanPredEntities(false, maxResults, firstResult);
    }

    private List<DietaPlanPred> findDietaPlanPredEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DietaPlanPred.class));
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

    public DietaPlanPred findDietaPlanPred(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DietaPlanPred.class, id);
        } finally {
            em.close();
        }
    }

    public int getDietaPlanPredCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DietaPlanPred> rt = cq.from(DietaPlanPred.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
        public DietaPlanPred rutinaByEntreno(PlanPred codEntreno) {
        EntityManager em = getEntityManager();
        List<DietaPlanPred> lista = null;
        try {

            Query qu = em.createNamedQuery("DietaPlanPred.findByEntreno", DietaPlanPred.class);
            qu.setParameter("codPlanPred", codEntreno);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }
    
}
