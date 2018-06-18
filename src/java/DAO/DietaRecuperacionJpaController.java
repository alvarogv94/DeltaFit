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
import DTO.Recuperacion;
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
            Recuperacion codRecuperacion = dietaRecuperacion.getCodRecuperacion();
            if (codRecuperacion != null) {
                codRecuperacion = em.getReference(codRecuperacion.getClass(), codRecuperacion.getCodRecuperacion());
                dietaRecuperacion.setCodRecuperacion(codRecuperacion);
            }
            em.persist(dietaRecuperacion);
            if (codRecuperacion != null) {
                codRecuperacion.getDietaRecuperacionList().add(dietaRecuperacion);
                codRecuperacion = em.merge(codRecuperacion);
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
            Recuperacion codRecuperacionOld = persistentDietaRecuperacion.getCodRecuperacion();
            Recuperacion codRecuperacionNew = dietaRecuperacion.getCodRecuperacion();
            if (codRecuperacionNew != null) {
                codRecuperacionNew = em.getReference(codRecuperacionNew.getClass(), codRecuperacionNew.getCodRecuperacion());
                dietaRecuperacion.setCodRecuperacion(codRecuperacionNew);
            }
            dietaRecuperacion = em.merge(dietaRecuperacion);
            if (codRecuperacionOld != null && !codRecuperacionOld.equals(codRecuperacionNew)) {
                codRecuperacionOld.getDietaRecuperacionList().remove(dietaRecuperacion);
                codRecuperacionOld = em.merge(codRecuperacionOld);
            }
            if (codRecuperacionNew != null && !codRecuperacionNew.equals(codRecuperacionOld)) {
                codRecuperacionNew.getDietaRecuperacionList().add(dietaRecuperacion);
                codRecuperacionNew = em.merge(codRecuperacionNew);
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
            Recuperacion codRecuperacion = dietaRecuperacion.getCodRecuperacion();
            if (codRecuperacion != null) {
                codRecuperacion.getDietaRecuperacionList().remove(dietaRecuperacion);
                codRecuperacion = em.merge(codRecuperacion);
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
        public DietaRecuperacion dietaByEntreno(Recuperacion codRecuperacion) {
        EntityManager em = getEntityManager();
        List<DietaRecuperacion> lista = null;
        try {

            Query qu = em.createNamedQuery("DietaRecuperacion.findByCodRecuperacion", DietaRecuperacion.class);
            qu.setParameter("codRecuperacion", codRecuperacion);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }
    
}
