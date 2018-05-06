/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.DietaRecuperacion;
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
public class DietaRecuperacionJpaController implements Serializable {

    public DietaRecuperacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DietaRecuperacion dietaRecuperacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entreno codEntreno = dietaRecuperacion.getCodEntreno();
            if (codEntreno != null) {
                codEntreno = em.getReference(codEntreno.getClass(), codEntreno.getCodEntreno());
                dietaRecuperacion.setCodEntreno(codEntreno);
            }
            em.persist(dietaRecuperacion);
            if (codEntreno != null) {
                codEntreno.getDietaRecuperacionList().add(dietaRecuperacion);
                codEntreno = em.merge(codEntreno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DietaRecuperacion dietaRecuperacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DietaRecuperacion persistentDietaRecuperacion = em.find(DietaRecuperacion.class, dietaRecuperacion.getCodDietaRecuperacion());
            Entreno codEntrenoOld = persistentDietaRecuperacion.getCodEntreno();
            Entreno codEntrenoNew = dietaRecuperacion.getCodEntreno();
            if (codEntrenoNew != null) {
                codEntrenoNew = em.getReference(codEntrenoNew.getClass(), codEntrenoNew.getCodEntreno());
                dietaRecuperacion.setCodEntreno(codEntrenoNew);
            }
            dietaRecuperacion = em.merge(dietaRecuperacion);
            if (codEntrenoOld != null && !codEntrenoOld.equals(codEntrenoNew)) {
                codEntrenoOld.getDietaRecuperacionList().remove(dietaRecuperacion);
                codEntrenoOld = em.merge(codEntrenoOld);
            }
            if (codEntrenoNew != null && !codEntrenoNew.equals(codEntrenoOld)) {
                codEntrenoNew.getDietaRecuperacionList().add(dietaRecuperacion);
                codEntrenoNew = em.merge(codEntrenoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dietaRecuperacion.getCodDietaRecuperacion();
                if (findDietaRecuperacion(id) == null) {
                    throw new NonexistentEntityException("The dietaRecuperacion with id " + id + " no longer exists.");
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
            DietaRecuperacion dietaRecuperacion;
            try {
                dietaRecuperacion = em.getReference(DietaRecuperacion.class, id);
                dietaRecuperacion.getCodDietaRecuperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dietaRecuperacion with id " + id + " no longer exists.", enfe);
            }
            Entreno codEntreno = dietaRecuperacion.getCodEntreno();
            if (codEntreno != null) {
                codEntreno.getDietaRecuperacionList().remove(dietaRecuperacion);
                codEntreno = em.merge(codEntreno);
            }
            em.remove(dietaRecuperacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DietaRecuperacion> findDietaRecuperacionEntities() {
        return findDietaRecuperacionEntities(true, -1, -1);
    }

    public List<DietaRecuperacion> findDietaRecuperacionEntities(int maxResults, int firstResult) {
        return findDietaRecuperacionEntities(false, maxResults, firstResult);
    }

    private List<DietaRecuperacion> findDietaRecuperacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DietaRecuperacion.class));
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

    public DietaRecuperacion findDietaRecuperacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DietaRecuperacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDietaRecuperacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DietaRecuperacion> rt = cq.from(DietaRecuperacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
