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
import DTO.Entreno;
import DTO.RutinaEntreno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class RutinaEntrenoJpaController implements Serializable {

    public RutinaEntrenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RutinaEntreno rutinaEntreno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entreno codEntreno = rutinaEntreno.getCodEntreno();
            if (codEntreno != null) {
                codEntreno = em.getReference(codEntreno.getClass(), codEntreno.getCodEntreno());
                rutinaEntreno.setCodEntreno(codEntreno);
            }
            em.persist(rutinaEntreno);
            if (codEntreno != null) {
                codEntreno.getRutinaEntrenoList().add(rutinaEntreno);
                codEntreno = em.merge(codEntreno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RutinaEntreno rutinaEntreno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RutinaEntreno persistentRutinaEntreno = em.find(RutinaEntreno.class, rutinaEntreno.getCodRutinaEntreno());
            Entreno codEntrenoOld = persistentRutinaEntreno.getCodEntreno();
            Entreno codEntrenoNew = rutinaEntreno.getCodEntreno();
            if (codEntrenoNew != null) {
                codEntrenoNew = em.getReference(codEntrenoNew.getClass(), codEntrenoNew.getCodEntreno());
                rutinaEntreno.setCodEntreno(codEntrenoNew);
            }
            rutinaEntreno = em.merge(rutinaEntreno);
            if (codEntrenoOld != null && !codEntrenoOld.equals(codEntrenoNew)) {
                codEntrenoOld.getRutinaEntrenoList().remove(rutinaEntreno);
                codEntrenoOld = em.merge(codEntrenoOld);
            }
            if (codEntrenoNew != null && !codEntrenoNew.equals(codEntrenoOld)) {
                codEntrenoNew.getRutinaEntrenoList().add(rutinaEntreno);
                codEntrenoNew = em.merge(codEntrenoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rutinaEntreno.getCodRutinaEntreno();
                if (findRutinaEntreno(id) == null) {
                    throw new NonexistentEntityException("The rutinaEntreno with id " + id + " no longer exists.");
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
            RutinaEntreno rutinaEntreno;
            try {
                rutinaEntreno = em.getReference(RutinaEntreno.class, id);
                rutinaEntreno.getCodRutinaEntreno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rutinaEntreno with id " + id + " no longer exists.", enfe);
            }
            Entreno codEntreno = rutinaEntreno.getCodEntreno();
            if (codEntreno != null) {
                codEntreno.getRutinaEntrenoList().remove(rutinaEntreno);
                codEntreno = em.merge(codEntreno);
            }
            em.remove(rutinaEntreno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RutinaEntreno> findRutinaEntrenoEntities() {
        return findRutinaEntrenoEntities(true, -1, -1);
    }

    public List<RutinaEntreno> findRutinaEntrenoEntities(int maxResults, int firstResult) {
        return findRutinaEntrenoEntities(false, maxResults, firstResult);
    }

    private List<RutinaEntreno> findRutinaEntrenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RutinaEntreno.class));
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

    public RutinaEntreno findRutinaEntreno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RutinaEntreno.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutinaEntrenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RutinaEntreno> rt = cq.from(RutinaEntreno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<RutinaEntreno> rutinaByEntreno(Entreno codEntreno) {
        EntityManager em = getEntityManager();
        List<RutinaEntreno> lista = null;
        try {

            Query qu = em.createNamedQuery("RutinaEntreno.findByCodEntreno", RutinaEntreno.class);
            qu.setParameter("codEntreno", codEntreno);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista;
    }

}
