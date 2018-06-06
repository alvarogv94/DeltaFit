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
import DTO.RutinaRecuperacion;
import java.util.ArrayList;
import java.util.List;
import DTO.DietaRecuperacion;
import DTO.Recuperacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class RecuperacionJpaController implements Serializable {

    public RecuperacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recuperacion recuperacion) {
        if (recuperacion.getRutinaRecuperacionList() == null) {
            recuperacion.setRutinaRecuperacionList(new ArrayList<RutinaRecuperacion>());
        }
        if (recuperacion.getDietaRecuperacionList() == null) {
            recuperacion.setDietaRecuperacionList(new ArrayList<DietaRecuperacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta codAtleta = recuperacion.getCodAtleta();
            if (codAtleta != null) {
                codAtleta = em.getReference(codAtleta.getClass(), codAtleta.getCodAtleta());
                recuperacion.setCodAtleta(codAtleta);
            }
            Preparador codPreparador = recuperacion.getCodPreparador();
            if (codPreparador != null) {
                codPreparador = em.getReference(codPreparador.getClass(), codPreparador.getCodPreparador());
                recuperacion.setCodPreparador(codPreparador);
            }
            List<RutinaRecuperacion> attachedRutinaRecuperacionList = new ArrayList<RutinaRecuperacion>();
            for (RutinaRecuperacion rutinaRecuperacionListRutinaRecuperacionToAttach : recuperacion.getRutinaRecuperacionList()) {
                rutinaRecuperacionListRutinaRecuperacionToAttach = em.getReference(rutinaRecuperacionListRutinaRecuperacionToAttach.getClass(), rutinaRecuperacionListRutinaRecuperacionToAttach.getCodRutinaRecuperacion());
                attachedRutinaRecuperacionList.add(rutinaRecuperacionListRutinaRecuperacionToAttach);
            }
            recuperacion.setRutinaRecuperacionList(attachedRutinaRecuperacionList);
            List<DietaRecuperacion> attachedDietaRecuperacionList = new ArrayList<DietaRecuperacion>();
            for (DietaRecuperacion dietaRecuperacionListDietaRecuperacionToAttach : recuperacion.getDietaRecuperacionList()) {
                dietaRecuperacionListDietaRecuperacionToAttach = em.getReference(dietaRecuperacionListDietaRecuperacionToAttach.getClass(), dietaRecuperacionListDietaRecuperacionToAttach.getCodDietaRecuperacion());
                attachedDietaRecuperacionList.add(dietaRecuperacionListDietaRecuperacionToAttach);
            }
            recuperacion.setDietaRecuperacionList(attachedDietaRecuperacionList);
            em.persist(recuperacion);
            if (codAtleta != null) {
                codAtleta.getRecuperacionList().add(recuperacion);
                codAtleta = em.merge(codAtleta);
            }
            if (codPreparador != null) {
                codPreparador.getRecuperacionList().add(recuperacion);
                codPreparador = em.merge(codPreparador);
            }
            for (RutinaRecuperacion rutinaRecuperacionListRutinaRecuperacion : recuperacion.getRutinaRecuperacionList()) {
                Recuperacion oldCodRecuperacionOfRutinaRecuperacionListRutinaRecuperacion = rutinaRecuperacionListRutinaRecuperacion.getCodRecuperacion();
                rutinaRecuperacionListRutinaRecuperacion.setCodRecuperacion(recuperacion);
                rutinaRecuperacionListRutinaRecuperacion = em.merge(rutinaRecuperacionListRutinaRecuperacion);
                if (oldCodRecuperacionOfRutinaRecuperacionListRutinaRecuperacion != null) {
                    oldCodRecuperacionOfRutinaRecuperacionListRutinaRecuperacion.getRutinaRecuperacionList().remove(rutinaRecuperacionListRutinaRecuperacion);
                    oldCodRecuperacionOfRutinaRecuperacionListRutinaRecuperacion = em.merge(oldCodRecuperacionOfRutinaRecuperacionListRutinaRecuperacion);
                }
            }
            for (DietaRecuperacion dietaRecuperacionListDietaRecuperacion : recuperacion.getDietaRecuperacionList()) {
                Recuperacion oldCodRecuperacionOfDietaRecuperacionListDietaRecuperacion = dietaRecuperacionListDietaRecuperacion.getCodRecuperacion();
                dietaRecuperacionListDietaRecuperacion.setCodRecuperacion(recuperacion);
                dietaRecuperacionListDietaRecuperacion = em.merge(dietaRecuperacionListDietaRecuperacion);
                if (oldCodRecuperacionOfDietaRecuperacionListDietaRecuperacion != null) {
                    oldCodRecuperacionOfDietaRecuperacionListDietaRecuperacion.getDietaRecuperacionList().remove(dietaRecuperacionListDietaRecuperacion);
                    oldCodRecuperacionOfDietaRecuperacionListDietaRecuperacion = em.merge(oldCodRecuperacionOfDietaRecuperacionListDietaRecuperacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recuperacion recuperacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recuperacion persistentRecuperacion = em.find(Recuperacion.class, recuperacion.getCodRecuperacion());
            Atleta codAtletaOld = persistentRecuperacion.getCodAtleta();
            Atleta codAtletaNew = recuperacion.getCodAtleta();
            Preparador codPreparadorOld = persistentRecuperacion.getCodPreparador();
            Preparador codPreparadorNew = recuperacion.getCodPreparador();
            List<RutinaRecuperacion> rutinaRecuperacionListOld = persistentRecuperacion.getRutinaRecuperacionList();
            List<RutinaRecuperacion> rutinaRecuperacionListNew = recuperacion.getRutinaRecuperacionList();
            List<DietaRecuperacion> dietaRecuperacionListOld = persistentRecuperacion.getDietaRecuperacionList();
            List<DietaRecuperacion> dietaRecuperacionListNew = recuperacion.getDietaRecuperacionList();
            if (codAtletaNew != null) {
                codAtletaNew = em.getReference(codAtletaNew.getClass(), codAtletaNew.getCodAtleta());
                recuperacion.setCodAtleta(codAtletaNew);
            }
            if (codPreparadorNew != null) {
                codPreparadorNew = em.getReference(codPreparadorNew.getClass(), codPreparadorNew.getCodPreparador());
                recuperacion.setCodPreparador(codPreparadorNew);
            }
            List<RutinaRecuperacion> attachedRutinaRecuperacionListNew = new ArrayList<RutinaRecuperacion>();
            for (RutinaRecuperacion rutinaRecuperacionListNewRutinaRecuperacionToAttach : rutinaRecuperacionListNew) {
                rutinaRecuperacionListNewRutinaRecuperacionToAttach = em.getReference(rutinaRecuperacionListNewRutinaRecuperacionToAttach.getClass(), rutinaRecuperacionListNewRutinaRecuperacionToAttach.getCodRutinaRecuperacion());
                attachedRutinaRecuperacionListNew.add(rutinaRecuperacionListNewRutinaRecuperacionToAttach);
            }
            rutinaRecuperacionListNew = attachedRutinaRecuperacionListNew;
            recuperacion.setRutinaRecuperacionList(rutinaRecuperacionListNew);
            List<DietaRecuperacion> attachedDietaRecuperacionListNew = new ArrayList<DietaRecuperacion>();
            for (DietaRecuperacion dietaRecuperacionListNewDietaRecuperacionToAttach : dietaRecuperacionListNew) {
                dietaRecuperacionListNewDietaRecuperacionToAttach = em.getReference(dietaRecuperacionListNewDietaRecuperacionToAttach.getClass(), dietaRecuperacionListNewDietaRecuperacionToAttach.getCodDietaRecuperacion());
                attachedDietaRecuperacionListNew.add(dietaRecuperacionListNewDietaRecuperacionToAttach);
            }
            dietaRecuperacionListNew = attachedDietaRecuperacionListNew;
            recuperacion.setDietaRecuperacionList(dietaRecuperacionListNew);
            recuperacion = em.merge(recuperacion);
            if (codAtletaOld != null && !codAtletaOld.equals(codAtletaNew)) {
                codAtletaOld.getRecuperacionList().remove(recuperacion);
                codAtletaOld = em.merge(codAtletaOld);
            }
            if (codAtletaNew != null && !codAtletaNew.equals(codAtletaOld)) {
                codAtletaNew.getRecuperacionList().add(recuperacion);
                codAtletaNew = em.merge(codAtletaNew);
            }
            if (codPreparadorOld != null && !codPreparadorOld.equals(codPreparadorNew)) {
                codPreparadorOld.getRecuperacionList().remove(recuperacion);
                codPreparadorOld = em.merge(codPreparadorOld);
            }
            if (codPreparadorNew != null && !codPreparadorNew.equals(codPreparadorOld)) {
                codPreparadorNew.getRecuperacionList().add(recuperacion);
                codPreparadorNew = em.merge(codPreparadorNew);
            }
            for (RutinaRecuperacion rutinaRecuperacionListOldRutinaRecuperacion : rutinaRecuperacionListOld) {
                if (!rutinaRecuperacionListNew.contains(rutinaRecuperacionListOldRutinaRecuperacion)) {
                    rutinaRecuperacionListOldRutinaRecuperacion.setCodRecuperacion(null);
                    rutinaRecuperacionListOldRutinaRecuperacion = em.merge(rutinaRecuperacionListOldRutinaRecuperacion);
                }
            }
            for (RutinaRecuperacion rutinaRecuperacionListNewRutinaRecuperacion : rutinaRecuperacionListNew) {
                if (!rutinaRecuperacionListOld.contains(rutinaRecuperacionListNewRutinaRecuperacion)) {
                    Recuperacion oldCodRecuperacionOfRutinaRecuperacionListNewRutinaRecuperacion = rutinaRecuperacionListNewRutinaRecuperacion.getCodRecuperacion();
                    rutinaRecuperacionListNewRutinaRecuperacion.setCodRecuperacion(recuperacion);
                    rutinaRecuperacionListNewRutinaRecuperacion = em.merge(rutinaRecuperacionListNewRutinaRecuperacion);
                    if (oldCodRecuperacionOfRutinaRecuperacionListNewRutinaRecuperacion != null && !oldCodRecuperacionOfRutinaRecuperacionListNewRutinaRecuperacion.equals(recuperacion)) {
                        oldCodRecuperacionOfRutinaRecuperacionListNewRutinaRecuperacion.getRutinaRecuperacionList().remove(rutinaRecuperacionListNewRutinaRecuperacion);
                        oldCodRecuperacionOfRutinaRecuperacionListNewRutinaRecuperacion = em.merge(oldCodRecuperacionOfRutinaRecuperacionListNewRutinaRecuperacion);
                    }
                }
            }
            for (DietaRecuperacion dietaRecuperacionListOldDietaRecuperacion : dietaRecuperacionListOld) {
                if (!dietaRecuperacionListNew.contains(dietaRecuperacionListOldDietaRecuperacion)) {
                    dietaRecuperacionListOldDietaRecuperacion.setCodRecuperacion(null);
                    dietaRecuperacionListOldDietaRecuperacion = em.merge(dietaRecuperacionListOldDietaRecuperacion);
                }
            }
            for (DietaRecuperacion dietaRecuperacionListNewDietaRecuperacion : dietaRecuperacionListNew) {
                if (!dietaRecuperacionListOld.contains(dietaRecuperacionListNewDietaRecuperacion)) {
                    Recuperacion oldCodRecuperacionOfDietaRecuperacionListNewDietaRecuperacion = dietaRecuperacionListNewDietaRecuperacion.getCodRecuperacion();
                    dietaRecuperacionListNewDietaRecuperacion.setCodRecuperacion(recuperacion);
                    dietaRecuperacionListNewDietaRecuperacion = em.merge(dietaRecuperacionListNewDietaRecuperacion);
                    if (oldCodRecuperacionOfDietaRecuperacionListNewDietaRecuperacion != null && !oldCodRecuperacionOfDietaRecuperacionListNewDietaRecuperacion.equals(recuperacion)) {
                        oldCodRecuperacionOfDietaRecuperacionListNewDietaRecuperacion.getDietaRecuperacionList().remove(dietaRecuperacionListNewDietaRecuperacion);
                        oldCodRecuperacionOfDietaRecuperacionListNewDietaRecuperacion = em.merge(oldCodRecuperacionOfDietaRecuperacionListNewDietaRecuperacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recuperacion.getCodRecuperacion();
                if (findRecuperacion(id) == null) {
                    throw new NonexistentEntityException("The recuperacion with id " + id + " no longer exists.");
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
            Recuperacion recuperacion;
            try {
                recuperacion = em.getReference(Recuperacion.class, id);
                recuperacion.getCodRecuperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recuperacion with id " + id + " no longer exists.", enfe);
            }
            Atleta codAtleta = recuperacion.getCodAtleta();
            if (codAtleta != null) {
                codAtleta.getRecuperacionList().remove(recuperacion);
                codAtleta = em.merge(codAtleta);
            }
            Preparador codPreparador = recuperacion.getCodPreparador();
            if (codPreparador != null) {
                codPreparador.getRecuperacionList().remove(recuperacion);
                codPreparador = em.merge(codPreparador);
            }
            List<RutinaRecuperacion> rutinaRecuperacionList = recuperacion.getRutinaRecuperacionList();
            for (RutinaRecuperacion rutinaRecuperacionListRutinaRecuperacion : rutinaRecuperacionList) {
                rutinaRecuperacionListRutinaRecuperacion.setCodRecuperacion(null);
                rutinaRecuperacionListRutinaRecuperacion = em.merge(rutinaRecuperacionListRutinaRecuperacion);
            }
            List<DietaRecuperacion> dietaRecuperacionList = recuperacion.getDietaRecuperacionList();
            for (DietaRecuperacion dietaRecuperacionListDietaRecuperacion : dietaRecuperacionList) {
                dietaRecuperacionListDietaRecuperacion.setCodRecuperacion(null);
                dietaRecuperacionListDietaRecuperacion = em.merge(dietaRecuperacionListDietaRecuperacion);
            }
            em.remove(recuperacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recuperacion> findRecuperacionEntities() {
        return findRecuperacionEntities(true, -1, -1);
    }

    public List<Recuperacion> findRecuperacionEntities(int maxResults, int firstResult) {
        return findRecuperacionEntities(false, maxResults, firstResult);
    }

    private List<Recuperacion> findRecuperacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recuperacion.class));
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

    public Recuperacion findRecuperacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recuperacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecuperacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recuperacion> rt = cq.from(Recuperacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Recuperacion recuperacionUltimaPreparador(Preparador codPreparador) {
        EntityManager em = getEntityManager();
        List<Recuperacion> lista = null;
        try {

            Query qu = em.createNamedQuery("Recuperacion.findUltimoPlan", Recuperacion.class);
            qu.setParameter("codPreparador", codPreparador);
            lista = qu.getResultList();
        } finally {
            em.close();
        }
        return lista.get(0);
    }

}
