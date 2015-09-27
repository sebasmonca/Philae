/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.MantenimientoMoldeMaquina;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.Maquina;
import Persistencia.Entities.Molde;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class MantenimientoMoldeMaquinaJpaController implements Serializable {

    public MantenimientoMoldeMaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MantenimientoMoldeMaquina mantenimientoMoldeMaquina) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquina idMaquina = mantenimientoMoldeMaquina.getIdMaquina();
            if (idMaquina != null) {
                idMaquina = em.getReference(idMaquina.getClass(), idMaquina.getIdMaquina());
                mantenimientoMoldeMaquina.setIdMaquina(idMaquina);
            }
            Molde idMolde = mantenimientoMoldeMaquina.getIdMolde();
            if (idMolde != null) {
                idMolde = em.getReference(idMolde.getClass(), idMolde.getIdMolde());
                mantenimientoMoldeMaquina.setIdMolde(idMolde);
            }
            em.persist(mantenimientoMoldeMaquina);
            if (idMaquina != null) {
                idMaquina.getMantenimientoMoldeMaquinaList().add(mantenimientoMoldeMaquina);
                idMaquina = em.merge(idMaquina);
            }
            if (idMolde != null) {
                idMolde.getMantenimientoMoldeMaquinaList().add(mantenimientoMoldeMaquina);
                idMolde = em.merge(idMolde);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MantenimientoMoldeMaquina mantenimientoMoldeMaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MantenimientoMoldeMaquina persistentMantenimientoMoldeMaquina = em.find(MantenimientoMoldeMaquina.class, mantenimientoMoldeMaquina.getIdMantenimientoMoldeMaquina());
            Maquina idMaquinaOld = persistentMantenimientoMoldeMaquina.getIdMaquina();
            Maquina idMaquinaNew = mantenimientoMoldeMaquina.getIdMaquina();
            Molde idMoldeOld = persistentMantenimientoMoldeMaquina.getIdMolde();
            Molde idMoldeNew = mantenimientoMoldeMaquina.getIdMolde();
            if (idMaquinaNew != null) {
                idMaquinaNew = em.getReference(idMaquinaNew.getClass(), idMaquinaNew.getIdMaquina());
                mantenimientoMoldeMaquina.setIdMaquina(idMaquinaNew);
            }
            if (idMoldeNew != null) {
                idMoldeNew = em.getReference(idMoldeNew.getClass(), idMoldeNew.getIdMolde());
                mantenimientoMoldeMaquina.setIdMolde(idMoldeNew);
            }
            mantenimientoMoldeMaquina = em.merge(mantenimientoMoldeMaquina);
            if (idMaquinaOld != null && !idMaquinaOld.equals(idMaquinaNew)) {
                idMaquinaOld.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquina);
                idMaquinaOld = em.merge(idMaquinaOld);
            }
            if (idMaquinaNew != null && !idMaquinaNew.equals(idMaquinaOld)) {
                idMaquinaNew.getMantenimientoMoldeMaquinaList().add(mantenimientoMoldeMaquina);
                idMaquinaNew = em.merge(idMaquinaNew);
            }
            if (idMoldeOld != null && !idMoldeOld.equals(idMoldeNew)) {
                idMoldeOld.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquina);
                idMoldeOld = em.merge(idMoldeOld);
            }
            if (idMoldeNew != null && !idMoldeNew.equals(idMoldeOld)) {
                idMoldeNew.getMantenimientoMoldeMaquinaList().add(mantenimientoMoldeMaquina);
                idMoldeNew = em.merge(idMoldeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mantenimientoMoldeMaquina.getIdMantenimientoMoldeMaquina();
                if (findMantenimientoMoldeMaquina(id) == null) {
                    throw new NonexistentEntityException("The mantenimientoMoldeMaquina with id " + id + " no longer exists.");
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
            MantenimientoMoldeMaquina mantenimientoMoldeMaquina;
            try {
                mantenimientoMoldeMaquina = em.getReference(MantenimientoMoldeMaquina.class, id);
                mantenimientoMoldeMaquina.getIdMantenimientoMoldeMaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimientoMoldeMaquina with id " + id + " no longer exists.", enfe);
            }
            Maquina idMaquina = mantenimientoMoldeMaquina.getIdMaquina();
            if (idMaquina != null) {
                idMaquina.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquina);
                idMaquina = em.merge(idMaquina);
            }
            Molde idMolde = mantenimientoMoldeMaquina.getIdMolde();
            if (idMolde != null) {
                idMolde.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquina);
                idMolde = em.merge(idMolde);
            }
            em.remove(mantenimientoMoldeMaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MantenimientoMoldeMaquina> findMantenimientoMoldeMaquinaEntities() {
        return findMantenimientoMoldeMaquinaEntities(true, -1, -1);
    }

    public List<MantenimientoMoldeMaquina> findMantenimientoMoldeMaquinaEntities(int maxResults, int firstResult) {
        return findMantenimientoMoldeMaquinaEntities(false, maxResults, firstResult);
    }

    private List<MantenimientoMoldeMaquina> findMantenimientoMoldeMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MantenimientoMoldeMaquina.class));
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

    public MantenimientoMoldeMaquina findMantenimientoMoldeMaquina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MantenimientoMoldeMaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getMantenimientoMoldeMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MantenimientoMoldeMaquina> rt = cq.from(MantenimientoMoldeMaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
