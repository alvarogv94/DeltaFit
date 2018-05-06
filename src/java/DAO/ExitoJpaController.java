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
import DTO.Exito;
import DTO.Preparador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class ExitoJpaController implements Serializable {

    public ExitoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exito exito) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta codAtleta = exito.getCodAtleta();
            if (codAtleta != null) {
                codAtleta = em.getReference(codAtleta.getClass(), codAtleta.getCodAtleta());
                exito.setCodAtleta(codAtleta);
            }
            Preparador codPreparador = exito.getCodPreparador();
            if (codPreparador != null) {
                codPreparador = em.getReference(codPreparador.getClass(), codPreparador.getCodPreparador());
                exito.setCodPreparador(codPreparador);
            }
            em.persist(exito);
            if (codAtleta != null) {
                codAtleta.getExitoList().add(exito);
                codAtleta = em.merge(codAtleta);
            }
            if (codPreparador != null) {
                codPreparador.getExitoList().add(exito);
                codPreparador = em.merge(codPreparador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exito exito) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exito persistentExito = em.find(Exito.class, exito.getCodExito());
            Atleta codAtletaOld = persistentExito.getCodAtleta();
            Atleta codAtletaNew = exito.getCodAtleta();
            Preparador codPreparadorOld = persistentExito.getCodPreparador();
            Preparador codPreparadorNew = exito.getCodPreparador();
            if (codAtletaNew != null) {
                codAtletaNew = em.getReference(codAtletaNew.getClass(), codAtletaNew.getCodAtleta());
                exito.setCodAtleta(codAtletaNew);
            }
            if (codPreparadorNew != null) {
                codPreparadorNew = em.getReference(codPreparadorNew.getClass(), codPreparadorNew.getCodPreparador());
                exito.setCodPreparador(codPreparadorNew);
            }
            exito = em.merge(exito);
            if (codAtletaOld != null && !codAtletaOld.equals(codAtletaNew)) {
                codAtletaOld.getExitoList().remove(exito);
                codAtletaOld = em.merge(codAtletaOld);
            }
            if (codAtletaNew != null && !codAtletaNew.equals(codAtletaOld)) {
                codAtletaNew.getExitoList().add(exito);
                codAtletaNew = em.merge(codAtletaNew);
            }
            if (codPreparadorOld != null && !codPreparadorOld.equals(codPreparadorNew)) {
                codPreparadorOld.getExitoList().remove(exito);
                codPreparadorOld = em.merge(codPreparadorOld);
            }
            if (codPreparadorNew != null && !codPreparadorNew.equals(codPreparadorOld)) {
                codPreparadorNew.getExitoList().add(exito);
                codPreparadorNew = em.merge(codPreparadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exito.getCodExito();
                if (findExito(id) == null) {
                    throw new NonexistentEntityException("The exito with id " + id + " no longer exists.");
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
            Exito exito;
            try {
                exito = em.getReference(Exito.class, id);
                exito.getCodExito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exito with id " + id + " no longer exists.", enfe);
            }
            Atleta codAtleta = exito.getCodAtleta();
            if (codAtleta != null) {
                codAtleta.getExitoList().remove(exito);
                codAtleta = em.merge(codAtleta);
            }
            Preparador codPreparador = exito.getCodPreparador();
            if (codPreparador != null) {
                codPreparador.getExitoList().remove(exito);
                codPreparador = em.merge(codPreparador);
            }
            em.remove(exito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exito> findExitoEntities() {
        return findExitoEntities(true, -1, -1);
    }

    public List<Exito> findExitoEntities(int maxResults, int firstResult) {
        return findExitoEntities(false, maxResults, firstResult);
    }

    private List<Exito> findExitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exito.class));
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

    public Exito findExito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exito.class, id);
        } finally {
            em.close();
        }
    }

    public int getExitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exito> rt = cq.from(Exito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
