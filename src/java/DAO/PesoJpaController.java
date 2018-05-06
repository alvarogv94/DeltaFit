/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Atleta;
import DTO.Peso;
import DTO.PesoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class PesoJpaController implements Serializable {

    public PesoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Peso peso) throws PreexistingEntityException, Exception {
        if (peso.getPesoPK() == null) {
            peso.setPesoPK(new PesoPK());
        }
        peso.getPesoPK().setCodAtleta(peso.getAtleta().getCodAtleta());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta atleta = peso.getAtleta();
            if (atleta != null) {
                atleta = em.getReference(atleta.getClass(), atleta.getCodAtleta());
                peso.setAtleta(atleta);
            }
            em.persist(peso);
            if (atleta != null) {
                atleta.getPesoList().add(peso);
                atleta = em.merge(atleta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeso(peso.getPesoPK()) != null) {
                throw new PreexistingEntityException("Peso " + peso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Peso peso) throws NonexistentEntityException, Exception {
        peso.getPesoPK().setCodAtleta(peso.getAtleta().getCodAtleta());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Peso persistentPeso = em.find(Peso.class, peso.getPesoPK());
            Atleta atletaOld = persistentPeso.getAtleta();
            Atleta atletaNew = peso.getAtleta();
            if (atletaNew != null) {
                atletaNew = em.getReference(atletaNew.getClass(), atletaNew.getCodAtleta());
                peso.setAtleta(atletaNew);
            }
            peso = em.merge(peso);
            if (atletaOld != null && !atletaOld.equals(atletaNew)) {
                atletaOld.getPesoList().remove(peso);
                atletaOld = em.merge(atletaOld);
            }
            if (atletaNew != null && !atletaNew.equals(atletaOld)) {
                atletaNew.getPesoList().add(peso);
                atletaNew = em.merge(atletaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PesoPK id = peso.getPesoPK();
                if (findPeso(id) == null) {
                    throw new NonexistentEntityException("The peso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PesoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Peso peso;
            try {
                peso = em.getReference(Peso.class, id);
                peso.getPesoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peso with id " + id + " no longer exists.", enfe);
            }
            Atleta atleta = peso.getAtleta();
            if (atleta != null) {
                atleta.getPesoList().remove(peso);
                atleta = em.merge(atleta);
            }
            em.remove(peso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Peso> findPesoEntities() {
        return findPesoEntities(true, -1, -1);
    }

    public List<Peso> findPesoEntities(int maxResults, int firstResult) {
        return findPesoEntities(false, maxResults, firstResult);
    }

    private List<Peso> findPesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Peso.class));
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

    public Peso findPeso(PesoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Peso.class, id);
        } finally {
            em.close();
        }
    }

    public int getPesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Peso> rt = cq.from(Peso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
