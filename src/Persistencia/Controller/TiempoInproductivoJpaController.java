/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.TiempoInproductivo;
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
public class TiempoInproductivoJpaController implements Serializable {

    public TiempoInproductivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiempoInproductivo tiempoInproductivo) {
        if (tiempoInproductivo.getTrabajoMaquinaList() == null) {
            tiempoInproductivo.setTrabajoMaquinaList(new ArrayList<TrabajoMaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TrabajoMaquina> attachedTrabajoMaquinaList = new ArrayList<TrabajoMaquina>();
            for (TrabajoMaquina trabajoMaquinaListTrabajoMaquinaToAttach : tiempoInproductivo.getTrabajoMaquinaList()) {
                trabajoMaquinaListTrabajoMaquinaToAttach = em.getReference(trabajoMaquinaListTrabajoMaquinaToAttach.getClass(), trabajoMaquinaListTrabajoMaquinaToAttach.getIdTrabajoMaquina());
                attachedTrabajoMaquinaList.add(trabajoMaquinaListTrabajoMaquinaToAttach);
            }
            tiempoInproductivo.setTrabajoMaquinaList(attachedTrabajoMaquinaList);
            em.persist(tiempoInproductivo);
            for (TrabajoMaquina trabajoMaquinaListTrabajoMaquina : tiempoInproductivo.getTrabajoMaquinaList()) {
                TiempoInproductivo oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListTrabajoMaquina = trabajoMaquinaListTrabajoMaquina.getTiempoInproductivoidTiempoInproductivo();
                trabajoMaquinaListTrabajoMaquina.setTiempoInproductivoidTiempoInproductivo(tiempoInproductivo);
                trabajoMaquinaListTrabajoMaquina = em.merge(trabajoMaquinaListTrabajoMaquina);
                if (oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListTrabajoMaquina != null) {
                    oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListTrabajoMaquina.getTrabajoMaquinaList().remove(trabajoMaquinaListTrabajoMaquina);
                    oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListTrabajoMaquina = em.merge(oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListTrabajoMaquina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiempoInproductivo tiempoInproductivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiempoInproductivo persistentTiempoInproductivo = em.find(TiempoInproductivo.class, tiempoInproductivo.getIdTiempoInproductivo());
            List<TrabajoMaquina> trabajoMaquinaListOld = persistentTiempoInproductivo.getTrabajoMaquinaList();
            List<TrabajoMaquina> trabajoMaquinaListNew = tiempoInproductivo.getTrabajoMaquinaList();
            List<String> illegalOrphanMessages = null;
            for (TrabajoMaquina trabajoMaquinaListOldTrabajoMaquina : trabajoMaquinaListOld) {
                if (!trabajoMaquinaListNew.contains(trabajoMaquinaListOldTrabajoMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TrabajoMaquina " + trabajoMaquinaListOldTrabajoMaquina + " since its tiempoInproductivoidTiempoInproductivo field is not nullable.");
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
            tiempoInproductivo.setTrabajoMaquinaList(trabajoMaquinaListNew);
            tiempoInproductivo = em.merge(tiempoInproductivo);
            for (TrabajoMaquina trabajoMaquinaListNewTrabajoMaquina : trabajoMaquinaListNew) {
                if (!trabajoMaquinaListOld.contains(trabajoMaquinaListNewTrabajoMaquina)) {
                    TiempoInproductivo oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListNewTrabajoMaquina = trabajoMaquinaListNewTrabajoMaquina.getTiempoInproductivoidTiempoInproductivo();
                    trabajoMaquinaListNewTrabajoMaquina.setTiempoInproductivoidTiempoInproductivo(tiempoInproductivo);
                    trabajoMaquinaListNewTrabajoMaquina = em.merge(trabajoMaquinaListNewTrabajoMaquina);
                    if (oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListNewTrabajoMaquina != null && !oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListNewTrabajoMaquina.equals(tiempoInproductivo)) {
                        oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListNewTrabajoMaquina.getTrabajoMaquinaList().remove(trabajoMaquinaListNewTrabajoMaquina);
                        oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListNewTrabajoMaquina = em.merge(oldTiempoInproductivoidTiempoInproductivoOfTrabajoMaquinaListNewTrabajoMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiempoInproductivo.getIdTiempoInproductivo();
                if (findTiempoInproductivo(id) == null) {
                    throw new NonexistentEntityException("The tiempoInproductivo with id " + id + " no longer exists.");
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
            TiempoInproductivo tiempoInproductivo;
            try {
                tiempoInproductivo = em.getReference(TiempoInproductivo.class, id);
                tiempoInproductivo.getIdTiempoInproductivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiempoInproductivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TrabajoMaquina> trabajoMaquinaListOrphanCheck = tiempoInproductivo.getTrabajoMaquinaList();
            for (TrabajoMaquina trabajoMaquinaListOrphanCheckTrabajoMaquina : trabajoMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TiempoInproductivo (" + tiempoInproductivo + ") cannot be destroyed since the TrabajoMaquina " + trabajoMaquinaListOrphanCheckTrabajoMaquina + " in its trabajoMaquinaList field has a non-nullable tiempoInproductivoidTiempoInproductivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tiempoInproductivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiempoInproductivo> findTiempoInproductivoEntities() {
        return findTiempoInproductivoEntities(true, -1, -1);
    }

    public List<TiempoInproductivo> findTiempoInproductivoEntities(int maxResults, int firstResult) {
        return findTiempoInproductivoEntities(false, maxResults, firstResult);
    }

    private List<TiempoInproductivo> findTiempoInproductivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiempoInproductivo.class));
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

    public TiempoInproductivo findTiempoInproductivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiempoInproductivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiempoInproductivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiempoInproductivo> rt = cq.from(TiempoInproductivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
