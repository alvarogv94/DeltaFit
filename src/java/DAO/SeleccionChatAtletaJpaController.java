/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Preparador;
import DTO.SeleccionChatAtleta;
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
public class SeleccionChatAtletaJpaController implements Serializable {

    public SeleccionChatAtletaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SeleccionChatAtleta seleccionChatAtleta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(seleccionChatAtleta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSeleccionChatAtleta(seleccionChatAtleta.getCodPreparador()) != null) {
                throw new PreexistingEntityException("SeleccionChatAtleta " + seleccionChatAtleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SeleccionChatAtleta seleccionChatAtleta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            seleccionChatAtleta = em.merge(seleccionChatAtleta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seleccionChatAtleta.getCodPreparador();
                if (findSeleccionChatAtleta(id) == null) {
                    throw new NonexistentEntityException("The seleccionChatAtleta with id " + id + " no longer exists.");
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
            SeleccionChatAtleta seleccionChatAtleta;
            try {
                seleccionChatAtleta = em.getReference(SeleccionChatAtleta.class, id);
                seleccionChatAtleta.getCodPreparador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seleccionChatAtleta with id " + id + " no longer exists.", enfe);
            }
            em.remove(seleccionChatAtleta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SeleccionChatAtleta> findSeleccionChatAtletaEntities() {
        return findSeleccionChatAtletaEntities(true, -1, -1);
    }

    public List<SeleccionChatAtleta> findSeleccionChatAtletaEntities(int maxResults, int firstResult) {
        return findSeleccionChatAtletaEntities(false, maxResults, firstResult);
    }

    private List<SeleccionChatAtleta> findSeleccionChatAtletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SeleccionChatAtleta.class));
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

    public SeleccionChatAtleta findSeleccionChatAtleta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SeleccionChatAtleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeleccionChatAtletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SeleccionChatAtleta> rt = cq.from(SeleccionChatAtleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SeleccionChatAtleta> atletasByPreparador(Integer prep) {
        EntityManager em = getEntityManager();
        List<SeleccionChatAtleta> lista = null;
        try {

            Query qu = em.createNamedQuery("SeleccionChatAtleta.findByCodPreparador", SeleccionChatAtleta.class);
            qu.setParameter("codPreparador", prep);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista;
    }

}
