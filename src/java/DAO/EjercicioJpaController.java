/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Ejercicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Musculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class EjercicioJpaController implements Serializable {

    public EjercicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejercicio ejercicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Musculo nombreMusculo = ejercicio.getNombreMusculo();
            if (nombreMusculo != null) {
                nombreMusculo = em.getReference(nombreMusculo.getClass(), nombreMusculo.getNombreMusculo());
                ejercicio.setNombreMusculo(nombreMusculo);
            }
            em.persist(ejercicio);
            if (nombreMusculo != null) {
                nombreMusculo.getEjercicioList().add(ejercicio);
                nombreMusculo = em.merge(nombreMusculo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejercicio ejercicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejercicio persistentEjercicio = em.find(Ejercicio.class, ejercicio.getCodEjercicio());
            Musculo nombreMusculoOld = persistentEjercicio.getNombreMusculo();
            Musculo nombreMusculoNew = ejercicio.getNombreMusculo();
            if (nombreMusculoNew != null) {
                nombreMusculoNew = em.getReference(nombreMusculoNew.getClass(), nombreMusculoNew.getNombreMusculo());
                ejercicio.setNombreMusculo(nombreMusculoNew);
            }
            ejercicio = em.merge(ejercicio);
            if (nombreMusculoOld != null && !nombreMusculoOld.equals(nombreMusculoNew)) {
                nombreMusculoOld.getEjercicioList().remove(ejercicio);
                nombreMusculoOld = em.merge(nombreMusculoOld);
            }
            if (nombreMusculoNew != null && !nombreMusculoNew.equals(nombreMusculoOld)) {
                nombreMusculoNew.getEjercicioList().add(ejercicio);
                nombreMusculoNew = em.merge(nombreMusculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ejercicio.getCodEjercicio();
                if (findEjercicio(id) == null) {
                    throw new NonexistentEntityException("The ejercicio with id " + id + " no longer exists.");
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
            Ejercicio ejercicio;
            try {
                ejercicio = em.getReference(Ejercicio.class, id);
                ejercicio.getCodEjercicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejercicio with id " + id + " no longer exists.", enfe);
            }
            Musculo nombreMusculo = ejercicio.getNombreMusculo();
            if (nombreMusculo != null) {
                nombreMusculo.getEjercicioList().remove(ejercicio);
                nombreMusculo = em.merge(nombreMusculo);
            }
            em.remove(ejercicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejercicio> findEjercicioEntities() {
        return findEjercicioEntities(true, -1, -1);
    }

    public List<Ejercicio> findEjercicioEntities(int maxResults, int firstResult) {
        return findEjercicioEntities(false, maxResults, firstResult);
    }

    private List<Ejercicio> findEjercicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejercicio.class));
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

    public Ejercicio findEjercicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejercicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjercicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejercicio> rt = cq.from(Ejercicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Ejercicio> ejerciciosByMusculo(String nombreMusculo) {
        EntityManager em = getEntityManager();
        List<Ejercicio> lista = null;
        try {

            Query qu = em.createNamedQuery("Ejercicio.findByMusculo", Ejercicio.class);
            Musculo musculo = new Musculo(nombreMusculo);
            qu.setParameter("nombreMusculo", musculo);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista;
    }

}
