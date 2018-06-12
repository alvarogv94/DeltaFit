/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.DietaEntreno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Entreno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class DietaEntrenoJpaController implements Serializable {

    public DietaEntrenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DietaEntreno dietaEntreno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entreno codEntreno = dietaEntreno.getCodEntreno();
            if (codEntreno != null) {
                codEntreno = em.getReference(codEntreno.getClass(), codEntreno.getCodEntreno());
                dietaEntreno.setCodEntreno(codEntreno);
            }
            em.persist(dietaEntreno);
            if (codEntreno != null) {
                codEntreno.getDietaEntrenoList().add(dietaEntreno);
                codEntreno = em.merge(codEntreno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DietaEntreno dietaEntreno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DietaEntreno persistentDietaEntreno = em.find(DietaEntreno.class, dietaEntreno.getCodDietaEntreno());
            Entreno codEntrenoOld = persistentDietaEntreno.getCodEntreno();
            Entreno codEntrenoNew = dietaEntreno.getCodEntreno();
            if (codEntrenoNew != null) {
                codEntrenoNew = em.getReference(codEntrenoNew.getClass(), codEntrenoNew.getCodEntreno());
                dietaEntreno.setCodEntreno(codEntrenoNew);
            }
            dietaEntreno = em.merge(dietaEntreno);
            if (codEntrenoOld != null && !codEntrenoOld.equals(codEntrenoNew)) {
                codEntrenoOld.getDietaEntrenoList().remove(dietaEntreno);
                codEntrenoOld = em.merge(codEntrenoOld);
            }
            if (codEntrenoNew != null && !codEntrenoNew.equals(codEntrenoOld)) {
                codEntrenoNew.getDietaEntrenoList().add(dietaEntreno);
                codEntrenoNew = em.merge(codEntrenoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dietaEntreno.getCodDietaEntreno();
                if (findDietaEntreno(id) == null) {
                    throw new NonexistentEntityException("The dietaEntreno with id " + id + " no longer exists.");
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
            DietaEntreno dietaEntreno;
            try {
                dietaEntreno = em.getReference(DietaEntreno.class, id);
                dietaEntreno.getCodDietaEntreno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dietaEntreno with id " + id + " no longer exists.", enfe);
            }
            Entreno codEntreno = dietaEntreno.getCodEntreno();
            if (codEntreno != null) {
                codEntreno.getDietaEntrenoList().remove(dietaEntreno);
                codEntreno = em.merge(codEntreno);
            }
            em.remove(dietaEntreno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DietaEntreno> findDietaEntrenoEntities() {
        return findDietaEntrenoEntities(true, -1, -1);
    }

    public List<DietaEntreno> findDietaEntrenoEntities(int maxResults, int firstResult) {
        return findDietaEntrenoEntities(false, maxResults, firstResult);
    }

    private List<DietaEntreno> findDietaEntrenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DietaEntreno.class));
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

    public DietaEntreno findDietaEntreno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DietaEntreno.class, id);
        } finally {
            em.close();
        }
    }

    public int getDietaEntrenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DietaEntreno> rt = cq.from(DietaEntreno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public DietaEntreno dietaByEntreno(Entreno codEntreno) {
        EntityManager em = getEntityManager();
        List<DietaEntreno> lista = null;
        try {

            Query qu = em.createNamedQuery("DietaEntreno.findByCodEntreno", DietaEntreno.class);
            qu.setParameter("codEntreno", codEntreno);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }
}
