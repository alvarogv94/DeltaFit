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
import DTO.Imagen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class ImagenJpaController implements Serializable {

    public ImagenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagen imagen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta codAtleta = imagen.getCodAtleta();
            if (codAtleta != null) {
                codAtleta = em.getReference(codAtleta.getClass(), codAtleta.getCodAtleta());
                imagen.setCodAtleta(codAtleta);
            }
            em.persist(imagen);
            if (codAtleta != null) {
                codAtleta.getImagenList().add(imagen);
                codAtleta = em.merge(codAtleta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagen imagen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagen persistentImagen = em.find(Imagen.class, imagen.getCodImagen());
            Atleta codAtletaOld = persistentImagen.getCodAtleta();
            Atleta codAtletaNew = imagen.getCodAtleta();
            if (codAtletaNew != null) {
                codAtletaNew = em.getReference(codAtletaNew.getClass(), codAtletaNew.getCodAtleta());
                imagen.setCodAtleta(codAtletaNew);
            }
            imagen = em.merge(imagen);
            if (codAtletaOld != null && !codAtletaOld.equals(codAtletaNew)) {
                codAtletaOld.getImagenList().remove(imagen);
                codAtletaOld = em.merge(codAtletaOld);
            }
            if (codAtletaNew != null && !codAtletaNew.equals(codAtletaOld)) {
                codAtletaNew.getImagenList().add(imagen);
                codAtletaNew = em.merge(codAtletaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imagen.getCodImagen();
                if (findImagen(id) == null) {
                    throw new NonexistentEntityException("The imagen with id " + id + " no longer exists.");
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
            Imagen imagen;
            try {
                imagen = em.getReference(Imagen.class, id);
                imagen.getCodImagen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagen with id " + id + " no longer exists.", enfe);
            }
            Atleta codAtleta = imagen.getCodAtleta();
            if (codAtleta != null) {
                codAtleta.getImagenList().remove(imagen);
                codAtleta = em.merge(codAtleta);
            }
            em.remove(imagen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagen> findImagenEntities() {
        return findImagenEntities(true, -1, -1);
    }

    public List<Imagen> findImagenEntities(int maxResults, int firstResult) {
        return findImagenEntities(false, maxResults, firstResult);
    }

    private List<Imagen> findImagenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Imagen.class));
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

    public Imagen findImagen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagen.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Imagen> rt = cq.from(Imagen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
