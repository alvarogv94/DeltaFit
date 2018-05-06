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
import DTO.Atleta;
import DTO.Preparador;
import DTO.Valoracion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class ValoracionJpaController implements Serializable {

    public ValoracionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Valoracion valoracion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta codAtleta = valoracion.getCodAtleta();
            if (codAtleta != null) {
                codAtleta = em.getReference(codAtleta.getClass(), codAtleta.getCodAtleta());
                valoracion.setCodAtleta(codAtleta);
            }
            Preparador codPreparador = valoracion.getCodPreparador();
            if (codPreparador != null) {
                codPreparador = em.getReference(codPreparador.getClass(), codPreparador.getCodPreparador());
                valoracion.setCodPreparador(codPreparador);
            }
            em.persist(valoracion);
            if (codAtleta != null) {
                codAtleta.getValoracionList().add(valoracion);
                codAtleta = em.merge(codAtleta);
            }
            if (codPreparador != null) {
                codPreparador.getValoracionList().add(valoracion);
                codPreparador = em.merge(codPreparador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Valoracion valoracion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Valoracion persistentValoracion = em.find(Valoracion.class, valoracion.getCodValoracion());
            Atleta codAtletaOld = persistentValoracion.getCodAtleta();
            Atleta codAtletaNew = valoracion.getCodAtleta();
            Preparador codPreparadorOld = persistentValoracion.getCodPreparador();
            Preparador codPreparadorNew = valoracion.getCodPreparador();
            if (codAtletaNew != null) {
                codAtletaNew = em.getReference(codAtletaNew.getClass(), codAtletaNew.getCodAtleta());
                valoracion.setCodAtleta(codAtletaNew);
            }
            if (codPreparadorNew != null) {
                codPreparadorNew = em.getReference(codPreparadorNew.getClass(), codPreparadorNew.getCodPreparador());
                valoracion.setCodPreparador(codPreparadorNew);
            }
            valoracion = em.merge(valoracion);
            if (codAtletaOld != null && !codAtletaOld.equals(codAtletaNew)) {
                codAtletaOld.getValoracionList().remove(valoracion);
                codAtletaOld = em.merge(codAtletaOld);
            }
            if (codAtletaNew != null && !codAtletaNew.equals(codAtletaOld)) {
                codAtletaNew.getValoracionList().add(valoracion);
                codAtletaNew = em.merge(codAtletaNew);
            }
            if (codPreparadorOld != null && !codPreparadorOld.equals(codPreparadorNew)) {
                codPreparadorOld.getValoracionList().remove(valoracion);
                codPreparadorOld = em.merge(codPreparadorOld);
            }
            if (codPreparadorNew != null && !codPreparadorNew.equals(codPreparadorOld)) {
                codPreparadorNew.getValoracionList().add(valoracion);
                codPreparadorNew = em.merge(codPreparadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = valoracion.getCodValoracion();
                if (findValoracion(id) == null) {
                    throw new NonexistentEntityException("The valoracion with id " + id + " no longer exists.");
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
            Valoracion valoracion;
            try {
                valoracion = em.getReference(Valoracion.class, id);
                valoracion.getCodValoracion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The valoracion with id " + id + " no longer exists.", enfe);
            }
            Atleta codAtleta = valoracion.getCodAtleta();
            if (codAtleta != null) {
                codAtleta.getValoracionList().remove(valoracion);
                codAtleta = em.merge(codAtleta);
            }
            Preparador codPreparador = valoracion.getCodPreparador();
            if (codPreparador != null) {
                codPreparador.getValoracionList().remove(valoracion);
                codPreparador = em.merge(codPreparador);
            }
            em.remove(valoracion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Valoracion> findValoracionEntities() {
        return findValoracionEntities(true, -1, -1);
    }

    public List<Valoracion> findValoracionEntities(int maxResults, int firstResult) {
        return findValoracionEntities(false, maxResults, firstResult);
    }

    private List<Valoracion> findValoracionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Valoracion.class));
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

    public Valoracion findValoracion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Valoracion.class, id);
        } finally {
            em.close();
        }
    }

    public int getValoracionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Valoracion> rt = cq.from(Valoracion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
