/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.OrdenProduccion;
import Persistencia.Entities.TipoMaterialProducto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class TipoMaterialProductoJpaController implements Serializable {

    public TipoMaterialProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMaterialProducto tipoMaterialProducto) {
        if (tipoMaterialProducto.getOrdenProduccionList() == null) {
            tipoMaterialProducto.setOrdenProduccionList(new ArrayList<OrdenProduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OrdenProduccion> attachedOrdenProduccionList = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListOrdenProduccionToAttach : tipoMaterialProducto.getOrdenProduccionList()) {
                ordenProduccionListOrdenProduccionToAttach = em.getReference(ordenProduccionListOrdenProduccionToAttach.getClass(), ordenProduccionListOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionList.add(ordenProduccionListOrdenProduccionToAttach);
            }
            tipoMaterialProducto.setOrdenProduccionList(attachedOrdenProduccionList);
            em.persist(tipoMaterialProducto);
            for (OrdenProduccion ordenProduccionListOrdenProduccion : tipoMaterialProducto.getOrdenProduccionList()) {
                TipoMaterialProducto oldIdTipoMaterialProductoOfOrdenProduccionListOrdenProduccion = ordenProduccionListOrdenProduccion.getIdTipoMaterialProducto();
                ordenProduccionListOrdenProduccion.setIdTipoMaterialProducto(tipoMaterialProducto);
                ordenProduccionListOrdenProduccion = em.merge(ordenProduccionListOrdenProduccion);
                if (oldIdTipoMaterialProductoOfOrdenProduccionListOrdenProduccion != null) {
                    oldIdTipoMaterialProductoOfOrdenProduccionListOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListOrdenProduccion);
                    oldIdTipoMaterialProductoOfOrdenProduccionListOrdenProduccion = em.merge(oldIdTipoMaterialProductoOfOrdenProduccionListOrdenProduccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMaterialProducto tipoMaterialProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMaterialProducto persistentTipoMaterialProducto = em.find(TipoMaterialProducto.class, tipoMaterialProducto.getIdTipoMaterialProducto());
            List<OrdenProduccion> ordenProduccionListOld = persistentTipoMaterialProducto.getOrdenProduccionList();
            List<OrdenProduccion> ordenProduccionListNew = tipoMaterialProducto.getOrdenProduccionList();
            List<String> illegalOrphanMessages = null;
            for (OrdenProduccion ordenProduccionListOldOrdenProduccion : ordenProduccionListOld) {
                if (!ordenProduccionListNew.contains(ordenProduccionListOldOrdenProduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenProduccion " + ordenProduccionListOldOrdenProduccion + " since its idTipoMaterialProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OrdenProduccion> attachedOrdenProduccionListNew = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListNewOrdenProduccionToAttach : ordenProduccionListNew) {
                ordenProduccionListNewOrdenProduccionToAttach = em.getReference(ordenProduccionListNewOrdenProduccionToAttach.getClass(), ordenProduccionListNewOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionListNew.add(ordenProduccionListNewOrdenProduccionToAttach);
            }
            ordenProduccionListNew = attachedOrdenProduccionListNew;
            tipoMaterialProducto.setOrdenProduccionList(ordenProduccionListNew);
            tipoMaterialProducto = em.merge(tipoMaterialProducto);
            for (OrdenProduccion ordenProduccionListNewOrdenProduccion : ordenProduccionListNew) {
                if (!ordenProduccionListOld.contains(ordenProduccionListNewOrdenProduccion)) {
                    TipoMaterialProducto oldIdTipoMaterialProductoOfOrdenProduccionListNewOrdenProduccion = ordenProduccionListNewOrdenProduccion.getIdTipoMaterialProducto();
                    ordenProduccionListNewOrdenProduccion.setIdTipoMaterialProducto(tipoMaterialProducto);
                    ordenProduccionListNewOrdenProduccion = em.merge(ordenProduccionListNewOrdenProduccion);
                    if (oldIdTipoMaterialProductoOfOrdenProduccionListNewOrdenProduccion != null && !oldIdTipoMaterialProductoOfOrdenProduccionListNewOrdenProduccion.equals(tipoMaterialProducto)) {
                        oldIdTipoMaterialProductoOfOrdenProduccionListNewOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListNewOrdenProduccion);
                        oldIdTipoMaterialProductoOfOrdenProduccionListNewOrdenProduccion = em.merge(oldIdTipoMaterialProductoOfOrdenProduccionListNewOrdenProduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoMaterialProducto.getIdTipoMaterialProducto();
                if (findTipoMaterialProducto(id) == null) {
                    throw new NonexistentEntityException("The tipoMaterialProducto with id " + id + " no longer exists.");
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
            TipoMaterialProducto tipoMaterialProducto;
            try {
                tipoMaterialProducto = em.getReference(TipoMaterialProducto.class, id);
                tipoMaterialProducto.getIdTipoMaterialProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMaterialProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdenProduccion> ordenProduccionListOrphanCheck = tipoMaterialProducto.getOrdenProduccionList();
            for (OrdenProduccion ordenProduccionListOrphanCheckOrdenProduccion : ordenProduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMaterialProducto (" + tipoMaterialProducto + ") cannot be destroyed since the OrdenProduccion " + ordenProduccionListOrphanCheckOrdenProduccion + " in its ordenProduccionList field has a non-nullable idTipoMaterialProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoMaterialProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMaterialProducto> findTipoMaterialProductoEntities() {
        return findTipoMaterialProductoEntities(true, -1, -1);
    }

    public List<TipoMaterialProducto> findTipoMaterialProductoEntities(int maxResults, int firstResult) {
        return findTipoMaterialProductoEntities(false, maxResults, firstResult);
    }

    private List<TipoMaterialProducto> findTipoMaterialProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMaterialProducto.class));
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

    public TipoMaterialProducto findTipoMaterialProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMaterialProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMaterialProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMaterialProducto> rt = cq.from(TipoMaterialProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
