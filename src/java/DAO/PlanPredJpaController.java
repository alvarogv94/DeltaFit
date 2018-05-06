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
import DTO.RutinaPlanPred;
import java.util.ArrayList;
import java.util.List;
import DTO.DietaPlanPred;
import DTO.PlanPred;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class PlanPredJpaController implements Serializable {

    public PlanPredJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanPred planPred) {
        if (planPred.getRutinaPlanPredList() == null) {
            planPred.setRutinaPlanPredList(new ArrayList<RutinaPlanPred>());
        }
        if (planPred.getDietaPlanPredList() == null) {
            planPred.setDietaPlanPredList(new ArrayList<DietaPlanPred>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RutinaPlanPred> attachedRutinaPlanPredList = new ArrayList<RutinaPlanPred>();
            for (RutinaPlanPred rutinaPlanPredListRutinaPlanPredToAttach : planPred.getRutinaPlanPredList()) {
                rutinaPlanPredListRutinaPlanPredToAttach = em.getReference(rutinaPlanPredListRutinaPlanPredToAttach.getClass(), rutinaPlanPredListRutinaPlanPredToAttach.getCodRutinaPlanPred());
                attachedRutinaPlanPredList.add(rutinaPlanPredListRutinaPlanPredToAttach);
            }
            planPred.setRutinaPlanPredList(attachedRutinaPlanPredList);
            List<DietaPlanPred> attachedDietaPlanPredList = new ArrayList<DietaPlanPred>();
            for (DietaPlanPred dietaPlanPredListDietaPlanPredToAttach : planPred.getDietaPlanPredList()) {
                dietaPlanPredListDietaPlanPredToAttach = em.getReference(dietaPlanPredListDietaPlanPredToAttach.getClass(), dietaPlanPredListDietaPlanPredToAttach.getCodDietaPlanPred());
                attachedDietaPlanPredList.add(dietaPlanPredListDietaPlanPredToAttach);
            }
            planPred.setDietaPlanPredList(attachedDietaPlanPredList);
            em.persist(planPred);
            for (RutinaPlanPred rutinaPlanPredListRutinaPlanPred : planPred.getRutinaPlanPredList()) {
                PlanPred oldCodPlanPredOfRutinaPlanPredListRutinaPlanPred = rutinaPlanPredListRutinaPlanPred.getCodPlanPred();
                rutinaPlanPredListRutinaPlanPred.setCodPlanPred(planPred);
                rutinaPlanPredListRutinaPlanPred = em.merge(rutinaPlanPredListRutinaPlanPred);
                if (oldCodPlanPredOfRutinaPlanPredListRutinaPlanPred != null) {
                    oldCodPlanPredOfRutinaPlanPredListRutinaPlanPred.getRutinaPlanPredList().remove(rutinaPlanPredListRutinaPlanPred);
                    oldCodPlanPredOfRutinaPlanPredListRutinaPlanPred = em.merge(oldCodPlanPredOfRutinaPlanPredListRutinaPlanPred);
                }
            }
            for (DietaPlanPred dietaPlanPredListDietaPlanPred : planPred.getDietaPlanPredList()) {
                PlanPred oldCodPlanPredOfDietaPlanPredListDietaPlanPred = dietaPlanPredListDietaPlanPred.getCodPlanPred();
                dietaPlanPredListDietaPlanPred.setCodPlanPred(planPred);
                dietaPlanPredListDietaPlanPred = em.merge(dietaPlanPredListDietaPlanPred);
                if (oldCodPlanPredOfDietaPlanPredListDietaPlanPred != null) {
                    oldCodPlanPredOfDietaPlanPredListDietaPlanPred.getDietaPlanPredList().remove(dietaPlanPredListDietaPlanPred);
                    oldCodPlanPredOfDietaPlanPredListDietaPlanPred = em.merge(oldCodPlanPredOfDietaPlanPredListDietaPlanPred);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanPred planPred) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanPred persistentPlanPred = em.find(PlanPred.class, planPred.getCodPlanPred());
            List<RutinaPlanPred> rutinaPlanPredListOld = persistentPlanPred.getRutinaPlanPredList();
            List<RutinaPlanPred> rutinaPlanPredListNew = planPred.getRutinaPlanPredList();
            List<DietaPlanPred> dietaPlanPredListOld = persistentPlanPred.getDietaPlanPredList();
            List<DietaPlanPred> dietaPlanPredListNew = planPred.getDietaPlanPredList();
            List<RutinaPlanPred> attachedRutinaPlanPredListNew = new ArrayList<RutinaPlanPred>();
            for (RutinaPlanPred rutinaPlanPredListNewRutinaPlanPredToAttach : rutinaPlanPredListNew) {
                rutinaPlanPredListNewRutinaPlanPredToAttach = em.getReference(rutinaPlanPredListNewRutinaPlanPredToAttach.getClass(), rutinaPlanPredListNewRutinaPlanPredToAttach.getCodRutinaPlanPred());
                attachedRutinaPlanPredListNew.add(rutinaPlanPredListNewRutinaPlanPredToAttach);
            }
            rutinaPlanPredListNew = attachedRutinaPlanPredListNew;
            planPred.setRutinaPlanPredList(rutinaPlanPredListNew);
            List<DietaPlanPred> attachedDietaPlanPredListNew = new ArrayList<DietaPlanPred>();
            for (DietaPlanPred dietaPlanPredListNewDietaPlanPredToAttach : dietaPlanPredListNew) {
                dietaPlanPredListNewDietaPlanPredToAttach = em.getReference(dietaPlanPredListNewDietaPlanPredToAttach.getClass(), dietaPlanPredListNewDietaPlanPredToAttach.getCodDietaPlanPred());
                attachedDietaPlanPredListNew.add(dietaPlanPredListNewDietaPlanPredToAttach);
            }
            dietaPlanPredListNew = attachedDietaPlanPredListNew;
            planPred.setDietaPlanPredList(dietaPlanPredListNew);
            planPred = em.merge(planPred);
            for (RutinaPlanPred rutinaPlanPredListOldRutinaPlanPred : rutinaPlanPredListOld) {
                if (!rutinaPlanPredListNew.contains(rutinaPlanPredListOldRutinaPlanPred)) {
                    rutinaPlanPredListOldRutinaPlanPred.setCodPlanPred(null);
                    rutinaPlanPredListOldRutinaPlanPred = em.merge(rutinaPlanPredListOldRutinaPlanPred);
                }
            }
            for (RutinaPlanPred rutinaPlanPredListNewRutinaPlanPred : rutinaPlanPredListNew) {
                if (!rutinaPlanPredListOld.contains(rutinaPlanPredListNewRutinaPlanPred)) {
                    PlanPred oldCodPlanPredOfRutinaPlanPredListNewRutinaPlanPred = rutinaPlanPredListNewRutinaPlanPred.getCodPlanPred();
                    rutinaPlanPredListNewRutinaPlanPred.setCodPlanPred(planPred);
                    rutinaPlanPredListNewRutinaPlanPred = em.merge(rutinaPlanPredListNewRutinaPlanPred);
                    if (oldCodPlanPredOfRutinaPlanPredListNewRutinaPlanPred != null && !oldCodPlanPredOfRutinaPlanPredListNewRutinaPlanPred.equals(planPred)) {
                        oldCodPlanPredOfRutinaPlanPredListNewRutinaPlanPred.getRutinaPlanPredList().remove(rutinaPlanPredListNewRutinaPlanPred);
                        oldCodPlanPredOfRutinaPlanPredListNewRutinaPlanPred = em.merge(oldCodPlanPredOfRutinaPlanPredListNewRutinaPlanPred);
                    }
                }
            }
            for (DietaPlanPred dietaPlanPredListOldDietaPlanPred : dietaPlanPredListOld) {
                if (!dietaPlanPredListNew.contains(dietaPlanPredListOldDietaPlanPred)) {
                    dietaPlanPredListOldDietaPlanPred.setCodPlanPred(null);
                    dietaPlanPredListOldDietaPlanPred = em.merge(dietaPlanPredListOldDietaPlanPred);
                }
            }
            for (DietaPlanPred dietaPlanPredListNewDietaPlanPred : dietaPlanPredListNew) {
                if (!dietaPlanPredListOld.contains(dietaPlanPredListNewDietaPlanPred)) {
                    PlanPred oldCodPlanPredOfDietaPlanPredListNewDietaPlanPred = dietaPlanPredListNewDietaPlanPred.getCodPlanPred();
                    dietaPlanPredListNewDietaPlanPred.setCodPlanPred(planPred);
                    dietaPlanPredListNewDietaPlanPred = em.merge(dietaPlanPredListNewDietaPlanPred);
                    if (oldCodPlanPredOfDietaPlanPredListNewDietaPlanPred != null && !oldCodPlanPredOfDietaPlanPredListNewDietaPlanPred.equals(planPred)) {
                        oldCodPlanPredOfDietaPlanPredListNewDietaPlanPred.getDietaPlanPredList().remove(dietaPlanPredListNewDietaPlanPred);
                        oldCodPlanPredOfDietaPlanPredListNewDietaPlanPred = em.merge(oldCodPlanPredOfDietaPlanPredListNewDietaPlanPred);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planPred.getCodPlanPred();
                if (findPlanPred(id) == null) {
                    throw new NonexistentEntityException("The planPred with id " + id + " no longer exists.");
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
            PlanPred planPred;
            try {
                planPred = em.getReference(PlanPred.class, id);
                planPred.getCodPlanPred();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planPred with id " + id + " no longer exists.", enfe);
            }
            List<RutinaPlanPred> rutinaPlanPredList = planPred.getRutinaPlanPredList();
            for (RutinaPlanPred rutinaPlanPredListRutinaPlanPred : rutinaPlanPredList) {
                rutinaPlanPredListRutinaPlanPred.setCodPlanPred(null);
                rutinaPlanPredListRutinaPlanPred = em.merge(rutinaPlanPredListRutinaPlanPred);
            }
            List<DietaPlanPred> dietaPlanPredList = planPred.getDietaPlanPredList();
            for (DietaPlanPred dietaPlanPredListDietaPlanPred : dietaPlanPredList) {
                dietaPlanPredListDietaPlanPred.setCodPlanPred(null);
                dietaPlanPredListDietaPlanPred = em.merge(dietaPlanPredListDietaPlanPred);
            }
            em.remove(planPred);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanPred> findPlanPredEntities() {
        return findPlanPredEntities(true, -1, -1);
    }

    public List<PlanPred> findPlanPredEntities(int maxResults, int firstResult) {
        return findPlanPredEntities(false, maxResults, firstResult);
    }

    private List<PlanPred> findPlanPredEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanPred.class));
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

    public PlanPred findPlanPred(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanPred.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanPredCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanPred> rt = cq.from(PlanPred.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
