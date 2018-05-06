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
import DTO.Recuperacion;
import DTO.RutinaRecuperacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class RutinaRecuperacionJpaController implements Serializable {

    public RutinaRecuperacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RutinaRecuperacion rutinaRecuperacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recuperacion codRecuperacion = rutinaRecuperacion.getCodRecuperacion();
            if (codRecuperacion != null) {
                codRecuperacion = em.getReference(codRecuperacion.getClass(), codRecuperacion.getCodRecuperacion());
                rutinaRecuperacion.setCodRecuperacion(codRecuperacion);
            }
            em.persist(rutinaRecuperacion);
            if (codRecuperacion != null) {
                codRecuperacion.getRutinaRecuperacionList().add(rutinaRecuperacion);
                codRecuperacion = em.merge(codRecuperacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RutinaRecuperacion rutinaRecuperacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RutinaRecuperacion persistentRutinaRecuperacion = em.find(RutinaRecuperacion.class, rutinaRecuperacion.getCodRutinaRecuperacion());
            Recuperacion codRecuperacionOld = persistentRutinaRecuperacion.getCodRecuperacion();
            Recuperacion codRecuperacionNew = rutinaRecuperacion.getCodRecuperacion();
            if (codRecuperacionNew != null) {
                codRecuperacionNew = em.getReference(codRecuperacionNew.getClass(), codRecuperacionNew.getCodRecuperacion());
                rutinaRecuperacion.setCodRecuperacion(codRecuperacionNew);
            }
            rutinaRecuperacion = em.merge(rutinaRecuperacion);
            if (codRecuperacionOld != null && !codRecuperacionOld.equals(codRecuperacionNew)) {
                codRecuperacionOld.getRutinaRecuperacionList().remove(rutinaRecuperacion);
                codRecuperacionOld = em.merge(codRecuperacionOld);
            }
            if (codRecuperacionNew != null && !codRecuperacionNew.equals(codRecuperacionOld)) {
                codRecuperacionNew.getRutinaRecuperacionList().add(rutinaRecuperacion);
                codRecuperacionNew = em.merge(codRecuperacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rutinaRecuperacion.getCodRutinaRecuperacion();
                if (findRutinaRecuperacion(id) == null) {
                    throw new NonexistentEntityException("The rutinaRecuperacion with id " + id + " no longer exists.");
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
            RutinaRecuperacion rutinaRecuperacion;
            try {
                rutinaRecuperacion = em.getReference(RutinaRecuperacion.class, id);
                rutinaRecuperacion.getCodRutinaRecuperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rutinaRecuperacion with id " + id + " no longer exists.", enfe);
            }
            Recuperacion codRecuperacion = rutinaRecuperacion.getCodRecuperacion();
            if (codRecuperacion != null) {
                codRecuperacion.getRutinaRecuperacionList().remove(rutinaRecuperacion);
                codRecuperacion = em.merge(codRecuperacion);
            }
            em.remove(rutinaRecuperacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RutinaRecuperacion> findRutinaRecuperacionEntities() {
        return findRutinaRecuperacionEntities(true, -1, -1);
    }

    public List<RutinaRecuperacion> findRutinaRecuperacionEntities(int maxResults, int firstResult) {
        return findRutinaRecuperacionEntities(false, maxResults, firstResult);
    }

    private List<RutinaRecuperacion> findRutinaRecuperacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RutinaRecuperacion.class));
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

    public RutinaRecuperacion findRutinaRecuperacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RutinaRecuperacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutinaRecuperacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RutinaRecuperacion> rt = cq.from(RutinaRecuperacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
