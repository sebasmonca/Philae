/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.ConfiguracionProducto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.OrdenProduccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class ConfiguracionProductoJpaController implements Serializable {

    public ConfiguracionProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConfiguracionProducto configuracionProducto) {
        if (configuracionProducto.getOrdenProduccionList() == null) {
            configuracionProducto.setOrdenProduccionList(new ArrayList<OrdenProduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OrdenProduccion> attachedOrdenProduccionList = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListOrdenProduccionToAttach : configuracionProducto.getOrdenProduccionList()) {
                ordenProduccionListOrdenProduccionToAttach = em.getReference(ordenProduccionListOrdenProduccionToAttach.getClass(), ordenProduccionListOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionList.add(ordenProduccionListOrdenProduccionToAttach);
            }
            configuracionProducto.setOrdenProduccionList(attachedOrdenProduccionList);
            em.persist(configuracionProducto);
            for (OrdenProduccion ordenProduccionListOrdenProduccion : configuracionProducto.getOrdenProduccionList()) {
                ConfiguracionProducto oldIdConfiguracionProductoOfOrdenProduccionListOrdenProduccion = ordenProduccionListOrdenProduccion.getIdConfiguracionProducto();
                ordenProduccionListOrdenProduccion.setIdConfiguracionProducto(configuracionProducto);
                ordenProduccionListOrdenProduccion = em.merge(ordenProduccionListOrdenProduccion);
                if (oldIdConfiguracionProductoOfOrdenProduccionListOrdenProduccion != null) {
                    oldIdConfiguracionProductoOfOrdenProduccionListOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListOrdenProduccion);
                    oldIdConfiguracionProductoOfOrdenProduccionListOrdenProduccion = em.merge(oldIdConfiguracionProductoOfOrdenProduccionListOrdenProduccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConfiguracionProducto configuracionProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConfiguracionProducto persistentConfiguracionProducto = em.find(ConfiguracionProducto.class, configuracionProducto.getIdConfiguracionProducto());
            List<OrdenProduccion> ordenProduccionListOld = persistentConfiguracionProducto.getOrdenProduccionList();
            List<OrdenProduccion> ordenProduccionListNew = configuracionProducto.getOrdenProduccionList();
            List<String> illegalOrphanMessages = null;
            for (OrdenProduccion ordenProduccionListOldOrdenProduccion : ordenProduccionListOld) {
                if (!ordenProduccionListNew.contains(ordenProduccionListOldOrdenProduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenProduccion " + ordenProduccionListOldOrdenProduccion + " since its idConfiguracionProducto field is not nullable.");
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
            configuracionProducto.setOrdenProduccionList(ordenProduccionListNew);
            configuracionProducto = em.merge(configuracionProducto);
            for (OrdenProduccion ordenProduccionListNewOrdenProduccion : ordenProduccionListNew) {
                if (!ordenProduccionListOld.contains(ordenProduccionListNewOrdenProduccion)) {
                    ConfiguracionProducto oldIdConfiguracionProductoOfOrdenProduccionListNewOrdenProduccion = ordenProduccionListNewOrdenProduccion.getIdConfiguracionProducto();
                    ordenProduccionListNewOrdenProduccion.setIdConfiguracionProducto(configuracionProducto);
                    ordenProduccionListNewOrdenProduccion = em.merge(ordenProduccionListNewOrdenProduccion);
                    if (oldIdConfiguracionProductoOfOrdenProduccionListNewOrdenProduccion != null && !oldIdConfiguracionProductoOfOrdenProduccionListNewOrdenProduccion.equals(configuracionProducto)) {
                        oldIdConfiguracionProductoOfOrdenProduccionListNewOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListNewOrdenProduccion);
                        oldIdConfiguracionProductoOfOrdenProduccionListNewOrdenProduccion = em.merge(oldIdConfiguracionProductoOfOrdenProduccionListNewOrdenProduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = configuracionProducto.getIdConfiguracionProducto();
                if (findConfiguracionProducto(id) == null) {
                    throw new NonexistentEntityException("The configuracionProducto with id " + id + " no longer exists.");
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
            ConfiguracionProducto configuracionProducto;
            try {
                configuracionProducto = em.getReference(ConfiguracionProducto.class, id);
                configuracionProducto.getIdConfiguracionProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The configuracionProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdenProduccion> ordenProduccionListOrphanCheck = configuracionProducto.getOrdenProduccionList();
            for (OrdenProduccion ordenProduccionListOrphanCheckOrdenProduccion : ordenProduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConfiguracionProducto (" + configuracionProducto + ") cannot be destroyed since the OrdenProduccion " + ordenProduccionListOrphanCheckOrdenProduccion + " in its ordenProduccionList field has a non-nullable idConfiguracionProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(configuracionProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConfiguracionProducto> findConfiguracionProductoEntities() {
        return findConfiguracionProductoEntities(true, -1, -1);
    }

    public List<ConfiguracionProducto> findConfiguracionProductoEntities(int maxResults, int firstResult) {
        return findConfiguracionProductoEntities(false, maxResults, firstResult);
    }

    private List<ConfiguracionProducto> findConfiguracionProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConfiguracionProducto.class));
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

    public ConfiguracionProducto findConfiguracionProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConfiguracionProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getConfiguracionProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConfiguracionProducto> rt = cq.from(ConfiguracionProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
