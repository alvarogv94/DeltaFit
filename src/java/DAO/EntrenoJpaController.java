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
import DTO.DietaEntreno;
import java.util.ArrayList;
import java.util.List;
import DTO.RutinaEntreno;
import DTO.DietaRecuperacion;
import DTO.Entreno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class EntrenoJpaController implements Serializable {

    public EntrenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entreno entreno) {
        if (entreno.getDietaEntrenoList() == null) {
            entreno.setDietaEntrenoList(new ArrayList<DietaEntreno>());
        }
        if (entreno.getRutinaEntrenoList() == null) {
            entreno.setRutinaEntrenoList(new ArrayList<RutinaEntreno>());
        }
        if (entreno.getDietaRecuperacionList() == null) {
            entreno.setDietaRecuperacionList(new ArrayList<DietaRecuperacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Atleta codAtleta = entreno.getCodAtleta();
            if (codAtleta != null) {
                codAtleta = em.getReference(codAtleta.getClass(), codAtleta.getCodAtleta());
                entreno.setCodAtleta(codAtleta);
            }
            Preparador codPreparador = entreno.getCodPreparador();
            if (codPreparador != null) {
                codPreparador = em.getReference(codPreparador.getClass(), codPreparador.getCodPreparador());
                entreno.setCodPreparador(codPreparador);
            }
            List<DietaEntreno> attachedDietaEntrenoList = new ArrayList<DietaEntreno>();
            for (DietaEntreno dietaEntrenoListDietaEntrenoToAttach : entreno.getDietaEntrenoList()) {
                dietaEntrenoListDietaEntrenoToAttach = em.getReference(dietaEntrenoListDietaEntrenoToAttach.getClass(), dietaEntrenoListDietaEntrenoToAttach.getCodDietaEntreno());
                attachedDietaEntrenoList.add(dietaEntrenoListDietaEntrenoToAttach);
            }
            entreno.setDietaEntrenoList(attachedDietaEntrenoList);
            List<RutinaEntreno> attachedRutinaEntrenoList = new ArrayList<RutinaEntreno>();
            for (RutinaEntreno rutinaEntrenoListRutinaEntrenoToAttach : entreno.getRutinaEntrenoList()) {
                rutinaEntrenoListRutinaEntrenoToAttach = em.getReference(rutinaEntrenoListRutinaEntrenoToAttach.getClass(), rutinaEntrenoListRutinaEntrenoToAttach.getCodRutinaEntreno());
                attachedRutinaEntrenoList.add(rutinaEntrenoListRutinaEntrenoToAttach);
            }
            entreno.setRutinaEntrenoList(attachedRutinaEntrenoList);
            List<DietaRecuperacion> attachedDietaRecuperacionList = new ArrayList<DietaRecuperacion>();
            for (DietaRecuperacion dietaRecuperacionListDietaRecuperacionToAttach : entreno.getDietaRecuperacionList()) {
                dietaRecuperacionListDietaRecuperacionToAttach = em.getReference(dietaRecuperacionListDietaRecuperacionToAttach.getClass(), dietaRecuperacionListDietaRecuperacionToAttach.getCodDietaRecuperacion());
                attachedDietaRecuperacionList.add(dietaRecuperacionListDietaRecuperacionToAttach);
            }
            entreno.setDietaRecuperacionList(attachedDietaRecuperacionList);
            em.persist(entreno);
            if (codAtleta != null) {
                codAtleta.getEntrenoList().add(entreno);
                codAtleta = em.merge(codAtleta);
            }
            if (codPreparador != null) {
                codPreparador.getEntrenoList().add(entreno);
                codPreparador = em.merge(codPreparador);
            }
            for (DietaEntreno dietaEntrenoListDietaEntreno : entreno.getDietaEntrenoList()) {
                Entreno oldCodEntrenoOfDietaEntrenoListDietaEntreno = dietaEntrenoListDietaEntreno.getCodEntreno();
                dietaEntrenoListDietaEntreno.setCodEntreno(entreno);
                dietaEntrenoListDietaEntreno = em.merge(dietaEntrenoListDietaEntreno);
                if (oldCodEntrenoOfDietaEntrenoListDietaEntreno != null) {
                    oldCodEntrenoOfDietaEntrenoListDietaEntreno.getDietaEntrenoList().remove(dietaEntrenoListDietaEntreno);
                    oldCodEntrenoOfDietaEntrenoListDietaEntreno = em.merge(oldCodEntrenoOfDietaEntrenoListDietaEntreno);
                }
            }
            for (RutinaEntreno rutinaEntrenoListRutinaEntreno : entreno.getRutinaEntrenoList()) {
                Entreno oldCodEntrenoOfRutinaEntrenoListRutinaEntreno = rutinaEntrenoListRutinaEntreno.getCodEntreno();
                rutinaEntrenoListRutinaEntreno.setCodEntreno(entreno);
                rutinaEntrenoListRutinaEntreno = em.merge(rutinaEntrenoListRutinaEntreno);
                if (oldCodEntrenoOfRutinaEntrenoListRutinaEntreno != null) {
                    oldCodEntrenoOfRutinaEntrenoListRutinaEntreno.getRutinaEntrenoList().remove(rutinaEntrenoListRutinaEntreno);
                    oldCodEntrenoOfRutinaEntrenoListRutinaEntreno = em.merge(oldCodEntrenoOfRutinaEntrenoListRutinaEntreno);
                }
            }
            for (DietaRecuperacion dietaRecuperacionListDietaRecuperacion : entreno.getDietaRecuperacionList()) {
                Entreno oldCodEntrenoOfDietaRecuperacionListDietaRecuperacion = dietaRecuperacionListDietaRecuperacion.getCodEntreno();
                dietaRecuperacionListDietaRecuperacion.setCodEntreno(entreno);
                dietaRecuperacionListDietaRecuperacion = em.merge(dietaRecuperacionListDietaRecuperacion);
                if (oldCodEntrenoOfDietaRecuperacionListDietaRecuperacion != null) {
                    oldCodEntrenoOfDietaRecuperacionListDietaRecuperacion.getDietaRecuperacionList().remove(dietaRecuperacionListDietaRecuperacion);
                    oldCodEntrenoOfDietaRecuperacionListDietaRecuperacion = em.merge(oldCodEntrenoOfDietaRecuperacionListDietaRecuperacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entreno entreno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entreno persistentEntreno = em.find(Entreno.class, entreno.getCodEntreno());
            Atleta codAtletaOld = persistentEntreno.getCodAtleta();
            Atleta codAtletaNew = entreno.getCodAtleta();
            Preparador codPreparadorOld = persistentEntreno.getCodPreparador();
            Preparador codPreparadorNew = entreno.getCodPreparador();
            List<DietaEntreno> dietaEntrenoListOld = persistentEntreno.getDietaEntrenoList();
            List<DietaEntreno> dietaEntrenoListNew = entreno.getDietaEntrenoList();
            List<RutinaEntreno> rutinaEntrenoListOld = persistentEntreno.getRutinaEntrenoList();
            List<RutinaEntreno> rutinaEntrenoListNew = entreno.getRutinaEntrenoList();
            List<DietaRecuperacion> dietaRecuperacionListOld = persistentEntreno.getDietaRecuperacionList();
            List<DietaRecuperacion> dietaRecuperacionListNew = entreno.getDietaRecuperacionList();
            if (codAtletaNew != null) {
                codAtletaNew = em.getReference(codAtletaNew.getClass(), codAtletaNew.getCodAtleta());
                entreno.setCodAtleta(codAtletaNew);
            }
            if (codPreparadorNew != null) {
                codPreparadorNew = em.getReference(codPreparadorNew.getClass(), codPreparadorNew.getCodPreparador());
                entreno.setCodPreparador(codPreparadorNew);
            }
            List<DietaEntreno> attachedDietaEntrenoListNew = new ArrayList<DietaEntreno>();
            for (DietaEntreno dietaEntrenoListNewDietaEntrenoToAttach : dietaEntrenoListNew) {
                dietaEntrenoListNewDietaEntrenoToAttach = em.getReference(dietaEntrenoListNewDietaEntrenoToAttach.getClass(), dietaEntrenoListNewDietaEntrenoToAttach.getCodDietaEntreno());
                attachedDietaEntrenoListNew.add(dietaEntrenoListNewDietaEntrenoToAttach);
            }
            dietaEntrenoListNew = attachedDietaEntrenoListNew;
            entreno.setDietaEntrenoList(dietaEntrenoListNew);
            List<RutinaEntreno> attachedRutinaEntrenoListNew = new ArrayList<RutinaEntreno>();
            for (RutinaEntreno rutinaEntrenoListNewRutinaEntrenoToAttach : rutinaEntrenoListNew) {
                rutinaEntrenoListNewRutinaEntrenoToAttach = em.getReference(rutinaEntrenoListNewRutinaEntrenoToAttach.getClass(), rutinaEntrenoListNewRutinaEntrenoToAttach.getCodRutinaEntreno());
                attachedRutinaEntrenoListNew.add(rutinaEntrenoListNewRutinaEntrenoToAttach);
            }
            rutinaEntrenoListNew = attachedRutinaEntrenoListNew;
            entreno.setRutinaEntrenoList(rutinaEntrenoListNew);
            List<DietaRecuperacion> attachedDietaRecuperacionListNew = new ArrayList<DietaRecuperacion>();
            for (DietaRecuperacion dietaRecuperacionListNewDietaRecuperacionToAttach : dietaRecuperacionListNew) {
                dietaRecuperacionListNewDietaRecuperacionToAttach = em.getReference(dietaRecuperacionListNewDietaRecuperacionToAttach.getClass(), dietaRecuperacionListNewDietaRecuperacionToAttach.getCodDietaRecuperacion());
                attachedDietaRecuperacionListNew.add(dietaRecuperacionListNewDietaRecuperacionToAttach);
            }
            dietaRecuperacionListNew = attachedDietaRecuperacionListNew;
            entreno.setDietaRecuperacionList(dietaRecuperacionListNew);
            entreno = em.merge(entreno);
            if (codAtletaOld != null && !codAtletaOld.equals(codAtletaNew)) {
                codAtletaOld.getEntrenoList().remove(entreno);
                codAtletaOld = em.merge(codAtletaOld);
            }
            if (codAtletaNew != null && !codAtletaNew.equals(codAtletaOld)) {
                codAtletaNew.getEntrenoList().add(entreno);
                codAtletaNew = em.merge(codAtletaNew);
            }
            if (codPreparadorOld != null && !codPreparadorOld.equals(codPreparadorNew)) {
                codPreparadorOld.getEntrenoList().remove(entreno);
                codPreparadorOld = em.merge(codPreparadorOld);
            }
            if (codPreparadorNew != null && !codPreparadorNew.equals(codPreparadorOld)) {
                codPreparadorNew.getEntrenoList().add(entreno);
                codPreparadorNew = em.merge(codPreparadorNew);
            }
            for (DietaEntreno dietaEntrenoListOldDietaEntreno : dietaEntrenoListOld) {
                if (!dietaEntrenoListNew.contains(dietaEntrenoListOldDietaEntreno)) {
                    dietaEntrenoListOldDietaEntreno.setCodEntreno(null);
                    dietaEntrenoListOldDietaEntreno = em.merge(dietaEntrenoListOldDietaEntreno);
                }
            }
            for (DietaEntreno dietaEntrenoListNewDietaEntreno : dietaEntrenoListNew) {
                if (!dietaEntrenoListOld.contains(dietaEntrenoListNewDietaEntreno)) {
                    Entreno oldCodEntrenoOfDietaEntrenoListNewDietaEntreno = dietaEntrenoListNewDietaEntreno.getCodEntreno();
                    dietaEntrenoListNewDietaEntreno.setCodEntreno(entreno);
                    dietaEntrenoListNewDietaEntreno = em.merge(dietaEntrenoListNewDietaEntreno);
                    if (oldCodEntrenoOfDietaEntrenoListNewDietaEntreno != null && !oldCodEntrenoOfDietaEntrenoListNewDietaEntreno.equals(entreno)) {
                        oldCodEntrenoOfDietaEntrenoListNewDietaEntreno.getDietaEntrenoList().remove(dietaEntrenoListNewDietaEntreno);
                        oldCodEntrenoOfDietaEntrenoListNewDietaEntreno = em.merge(oldCodEntrenoOfDietaEntrenoListNewDietaEntreno);
                    }
                }
            }
            for (RutinaEntreno rutinaEntrenoListOldRutinaEntreno : rutinaEntrenoListOld) {
                if (!rutinaEntrenoListNew.contains(rutinaEntrenoListOldRutinaEntreno)) {
                    rutinaEntrenoListOldRutinaEntreno.setCodEntreno(null);
                    rutinaEntrenoListOldRutinaEntreno = em.merge(rutinaEntrenoListOldRutinaEntreno);
                }
            }
            for (RutinaEntreno rutinaEntrenoListNewRutinaEntreno : rutinaEntrenoListNew) {
                if (!rutinaEntrenoListOld.contains(rutinaEntrenoListNewRutinaEntreno)) {
                    Entreno oldCodEntrenoOfRutinaEntrenoListNewRutinaEntreno = rutinaEntrenoListNewRutinaEntreno.getCodEntreno();
                    rutinaEntrenoListNewRutinaEntreno.setCodEntreno(entreno);
                    rutinaEntrenoListNewRutinaEntreno = em.merge(rutinaEntrenoListNewRutinaEntreno);
                    if (oldCodEntrenoOfRutinaEntrenoListNewRutinaEntreno != null && !oldCodEntrenoOfRutinaEntrenoListNewRutinaEntreno.equals(entreno)) {
                        oldCodEntrenoOfRutinaEntrenoListNewRutinaEntreno.getRutinaEntrenoList().remove(rutinaEntrenoListNewRutinaEntreno);
                        oldCodEntrenoOfRutinaEntrenoListNewRutinaEntreno = em.merge(oldCodEntrenoOfRutinaEntrenoListNewRutinaEntreno);
                    }
                }
            }
            for (DietaRecuperacion dietaRecuperacionListOldDietaRecuperacion : dietaRecuperacionListOld) {
                if (!dietaRecuperacionListNew.contains(dietaRecuperacionListOldDietaRecuperacion)) {
                    dietaRecuperacionListOldDietaRecuperacion.setCodEntreno(null);
                    dietaRecuperacionListOldDietaRecuperacion = em.merge(dietaRecuperacionListOldDietaRecuperacion);
                }
            }
            for (DietaRecuperacion dietaRecuperacionListNewDietaRecuperacion : dietaRecuperacionListNew) {
                if (!dietaRecuperacionListOld.contains(dietaRecuperacionListNewDietaRecuperacion)) {
                    Entreno oldCodEntrenoOfDietaRecuperacionListNewDietaRecuperacion = dietaRecuperacionListNewDietaRecuperacion.getCodEntreno();
                    dietaRecuperacionListNewDietaRecuperacion.setCodEntreno(entreno);
                    dietaRecuperacionListNewDietaRecuperacion = em.merge(dietaRecuperacionListNewDietaRecuperacion);
                    if (oldCodEntrenoOfDietaRecuperacionListNewDietaRecuperacion != null && !oldCodEntrenoOfDietaRecuperacionListNewDietaRecuperacion.equals(entreno)) {
                        oldCodEntrenoOfDietaRecuperacionListNewDietaRecuperacion.getDietaRecuperacionList().remove(dietaRecuperacionListNewDietaRecuperacion);
                        oldCodEntrenoOfDietaRecuperacionListNewDietaRecuperacion = em.merge(oldCodEntrenoOfDietaRecuperacionListNewDietaRecuperacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entreno.getCodEntreno();
                if (findEntreno(id) == null) {
                    throw new NonexistentEntityException("The entreno with id " + id + " no longer exists.");
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
            Entreno entreno;
            try {
                entreno = em.getReference(Entreno.class, id);
                entreno.getCodEntreno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entreno with id " + id + " no longer exists.", enfe);
            }
            Atleta codAtleta = entreno.getCodAtleta();
            if (codAtleta != null) {
                codAtleta.getEntrenoList().remove(entreno);
                codAtleta = em.merge(codAtleta);
            }
            Preparador codPreparador = entreno.getCodPreparador();
            if (codPreparador != null) {
                codPreparador.getEntrenoList().remove(entreno);
                codPreparador = em.merge(codPreparador);
            }
            List<DietaEntreno> dietaEntrenoList = entreno.getDietaEntrenoList();
            for (DietaEntreno dietaEntrenoListDietaEntreno : dietaEntrenoList) {
                dietaEntrenoListDietaEntreno.setCodEntreno(null);
                dietaEntrenoListDietaEntreno = em.merge(dietaEntrenoListDietaEntreno);
            }
            List<RutinaEntreno> rutinaEntrenoList = entreno.getRutinaEntrenoList();
            for (RutinaEntreno rutinaEntrenoListRutinaEntreno : rutinaEntrenoList) {
                rutinaEntrenoListRutinaEntreno.setCodEntreno(null);
                rutinaEntrenoListRutinaEntreno = em.merge(rutinaEntrenoListRutinaEntreno);
            }
            List<DietaRecuperacion> dietaRecuperacionList = entreno.getDietaRecuperacionList();
            for (DietaRecuperacion dietaRecuperacionListDietaRecuperacion : dietaRecuperacionList) {
                dietaRecuperacionListDietaRecuperacion.setCodEntreno(null);
                dietaRecuperacionListDietaRecuperacion = em.merge(dietaRecuperacionListDietaRecuperacion);
            }
            em.remove(entreno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entreno> findEntrenoEntities() {
        return findEntrenoEntities(true, -1, -1);
    }

    public List<Entreno> findEntrenoEntities(int maxResults, int firstResult) {
        return findEntrenoEntities(false, maxResults, firstResult);
    }

    private List<Entreno> findEntrenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entreno.class));
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

    public Entreno findEntreno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entreno.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntrenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entreno> rt = cq.from(Entreno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
