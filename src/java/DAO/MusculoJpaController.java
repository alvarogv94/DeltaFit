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
import DTO.Ejercicio;
import DTO.Musculo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class MusculoJpaController implements Serializable {

    public MusculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Musculo musculo) throws PreexistingEntityException, Exception {
        if (musculo.getEjercicioList() == null) {
            musculo.setEjercicioList(new ArrayList<Ejercicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejercicio> attachedEjercicioList = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListEjercicioToAttach : musculo.getEjercicioList()) {
                ejercicioListEjercicioToAttach = em.getReference(ejercicioListEjercicioToAttach.getClass(), ejercicioListEjercicioToAttach.getCodEjercicio());
                attachedEjercicioList.add(ejercicioListEjercicioToAttach);
            }
            musculo.setEjercicioList(attachedEjercicioList);
            em.persist(musculo);
            for (Ejercicio ejercicioListEjercicio : musculo.getEjercicioList()) {
                Musculo oldNombreMusculoOfEjercicioListEjercicio = ejercicioListEjercicio.getNombreMusculo();
                ejercicioListEjercicio.setNombreMusculo(musculo);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
                if (oldNombreMusculoOfEjercicioListEjercicio != null) {
                    oldNombreMusculoOfEjercicioListEjercicio.getEjercicioList().remove(ejercicioListEjercicio);
                    oldNombreMusculoOfEjercicioListEjercicio = em.merge(oldNombreMusculoOfEjercicioListEjercicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMusculo(musculo.getNombreMusculo()) != null) {
                throw new PreexistingEntityException("Musculo " + musculo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Musculo musculo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Musculo persistentMusculo = em.find(Musculo.class, musculo.getNombreMusculo());
            List<Ejercicio> ejercicioListOld = persistentMusculo.getEjercicioList();
            List<Ejercicio> ejercicioListNew = musculo.getEjercicioList();
            List<Ejercicio> attachedEjercicioListNew = new ArrayList<Ejercicio>();
            for (Ejercicio ejercicioListNewEjercicioToAttach : ejercicioListNew) {
                ejercicioListNewEjercicioToAttach = em.getReference(ejercicioListNewEjercicioToAttach.getClass(), ejercicioListNewEjercicioToAttach.getCodEjercicio());
                attachedEjercicioListNew.add(ejercicioListNewEjercicioToAttach);
            }
            ejercicioListNew = attachedEjercicioListNew;
            musculo.setEjercicioList(ejercicioListNew);
            musculo = em.merge(musculo);
            for (Ejercicio ejercicioListOldEjercicio : ejercicioListOld) {
                if (!ejercicioListNew.contains(ejercicioListOldEjercicio)) {
                    ejercicioListOldEjercicio.setNombreMusculo(null);
                    ejercicioListOldEjercicio = em.merge(ejercicioListOldEjercicio);
                }
            }
            for (Ejercicio ejercicioListNewEjercicio : ejercicioListNew) {
                if (!ejercicioListOld.contains(ejercicioListNewEjercicio)) {
                    Musculo oldNombreMusculoOfEjercicioListNewEjercicio = ejercicioListNewEjercicio.getNombreMusculo();
                    ejercicioListNewEjercicio.setNombreMusculo(musculo);
                    ejercicioListNewEjercicio = em.merge(ejercicioListNewEjercicio);
                    if (oldNombreMusculoOfEjercicioListNewEjercicio != null && !oldNombreMusculoOfEjercicioListNewEjercicio.equals(musculo)) {
                        oldNombreMusculoOfEjercicioListNewEjercicio.getEjercicioList().remove(ejercicioListNewEjercicio);
                        oldNombreMusculoOfEjercicioListNewEjercicio = em.merge(oldNombreMusculoOfEjercicioListNewEjercicio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = musculo.getNombreMusculo();
                if (findMusculo(id) == null) {
                    throw new NonexistentEntityException("The musculo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Musculo musculo;
            try {
                musculo = em.getReference(Musculo.class, id);
                musculo.getNombreMusculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The musculo with id " + id + " no longer exists.", enfe);
            }
            List<Ejercicio> ejercicioList = musculo.getEjercicioList();
            for (Ejercicio ejercicioListEjercicio : ejercicioList) {
                ejercicioListEjercicio.setNombreMusculo(null);
                ejercicioListEjercicio = em.merge(ejercicioListEjercicio);
            }
            em.remove(musculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Musculo> findMusculoEntities() {
        return findMusculoEntities(true, -1, -1);
    }

    public List<Musculo> findMusculoEntities(int maxResults, int firstResult) {
        return findMusculoEntities(false, maxResults, firstResult);
    }

    private List<Musculo> findMusculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Musculo.class));
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

    public Musculo findMusculo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Musculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMusculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Musculo> rt = cq.from(Musculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
