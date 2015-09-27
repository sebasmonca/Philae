/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.TiempoProductivo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.TrabajoMaquina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class TiempoProductivoJpaController implements Serializable {

    public TiempoProductivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiempoProductivo tiempoProductivo) {
        if (tiempoProductivo.getTrabajoMaquinaList() == null) {
            tiempoProductivo.setTrabajoMaquinaList(new ArrayList<TrabajoMaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TrabajoMaquina> attachedTrabajoMaquinaList = new ArrayList<TrabajoMaquina>();
            for (TrabajoMaquina trabajoMaquinaListTrabajoMaquinaToAttach : tiempoProductivo.getTrabajoMaquinaList()) {
                trabajoMaquinaListTrabajoMaquinaToAttach = em.getReference(trabajoMaquinaListTrabajoMaquinaToAttach.getClass(), trabajoMaquinaListTrabajoMaquinaToAttach.getIdTrabajoMaquina());
                attachedTrabajoMaquinaList.add(trabajoMaquinaListTrabajoMaquinaToAttach);
            }
            tiempoProductivo.setTrabajoMaquinaList(attachedTrabajoMaquinaList);
            em.persist(tiempoProductivo);
            for (TrabajoMaquina trabajoMaquinaListTrabajoMaquina : tiempoProductivo.getTrabajoMaquinaList()) {
                TiempoProductivo oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListTrabajoMaquina = trabajoMaquinaListTrabajoMaquina.getTiempoProductivoidTiempoProductivo();
                trabajoMaquinaListTrabajoMaquina.setTiempoProductivoidTiempoProductivo(tiempoProductivo);
                trabajoMaquinaListTrabajoMaquina = em.merge(trabajoMaquinaListTrabajoMaquina);
                if (oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListTrabajoMaquina != null) {
                    oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListTrabajoMaquina.getTrabajoMaquinaList().remove(trabajoMaquinaListTrabajoMaquina);
                    oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListTrabajoMaquina = em.merge(oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListTrabajoMaquina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiempoProductivo tiempoProductivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiempoProductivo persistentTiempoProductivo = em.find(TiempoProductivo.class, tiempoProductivo.getIdTiempoProductivo());
            List<TrabajoMaquina> trabajoMaquinaListOld = persistentTiempoProductivo.getTrabajoMaquinaList();
            List<TrabajoMaquina> trabajoMaquinaListNew = tiempoProductivo.getTrabajoMaquinaList();
            List<String> illegalOrphanMessages = null;
            for (TrabajoMaquina trabajoMaquinaListOldTrabajoMaquina : trabajoMaquinaListOld) {
                if (!trabajoMaquinaListNew.contains(trabajoMaquinaListOldTrabajoMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TrabajoMaquina " + trabajoMaquinaListOldTrabajoMaquina + " since its tiempoProductivoidTiempoProductivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<TrabajoMaquina> attachedTrabajoMaquinaListNew = new ArrayList<TrabajoMaquina>();
            for (TrabajoMaquina trabajoMaquinaListNewTrabajoMaquinaToAttach : trabajoMaquinaListNew) {
                trabajoMaquinaListNewTrabajoMaquinaToAttach = em.getReference(trabajoMaquinaListNewTrabajoMaquinaToAttach.getClass(), trabajoMaquinaListNewTrabajoMaquinaToAttach.getIdTrabajoMaquina());
                attachedTrabajoMaquinaListNew.add(trabajoMaquinaListNewTrabajoMaquinaToAttach);
            }
            trabajoMaquinaListNew = attachedTrabajoMaquinaListNew;
            tiempoProductivo.setTrabajoMaquinaList(trabajoMaquinaListNew);
            tiempoProductivo = em.merge(tiempoProductivo);
            for (TrabajoMaquina trabajoMaquinaListNewTrabajoMaquina : trabajoMaquinaListNew) {
                if (!trabajoMaquinaListOld.contains(trabajoMaquinaListNewTrabajoMaquina)) {
                    TiempoProductivo oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListNewTrabajoMaquina = trabajoMaquinaListNewTrabajoMaquina.getTiempoProductivoidTiempoProductivo();
                    trabajoMaquinaListNewTrabajoMaquina.setTiempoProductivoidTiempoProductivo(tiempoProductivo);
                    trabajoMaquinaListNewTrabajoMaquina = em.merge(trabajoMaquinaListNewTrabajoMaquina);
                    if (oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListNewTrabajoMaquina != null && !oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListNewTrabajoMaquina.equals(tiempoProductivo)) {
                        oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListNewTrabajoMaquina.getTrabajoMaquinaList().remove(trabajoMaquinaListNewTrabajoMaquina);
                        oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListNewTrabajoMaquina = em.merge(oldTiempoProductivoidTiempoProductivoOfTrabajoMaquinaListNewTrabajoMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiempoProductivo.getIdTiempoProductivo();
                if (findTiempoProductivo(id) == null) {
                    throw new NonexistentEntityException("The tiempoProductivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiempoProductivo tiempoProductivo;
            try {
                tiempoProductivo = em.getReference(TiempoProductivo.class, id);
                tiempoProductivo.getIdTiempoProductivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiempoProductivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TrabajoMaquina> trabajoMaquinaListOrphanCheck = tiempoProductivo.getTrabajoMaquinaList();
            for (TrabajoMaquina trabajoMaquinaListOrphanCheckTrabajoMaquina : trabajoMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiempoProductivo (" + tiempoProductivo + ") cannot be destroyed since the TrabajoMaquina " + trabajoMaquinaListOrphanCheckTrabajoMaquina + " in its trabajoMaquinaList field has a non-nullable tiempoProductivoidTiempoProductivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tiempoProductivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiempoProductivo> findTiempoProductivoEntities() {
        return findTiempoProductivoEntities(true, -1, -1);
    }

    public List<TiempoProductivo> findTiempoProductivoEntities(int maxResults, int firstResult) {
        return findTiempoProductivoEntities(false, maxResults, firstResult);
    }

    private List<TiempoProductivo> findTiempoProductivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiempoProductivo.class));
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

    public TiempoProductivo findTiempoProductivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiempoProductivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiempoProductivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiempoProductivo> rt = cq.from(TiempoProductivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
