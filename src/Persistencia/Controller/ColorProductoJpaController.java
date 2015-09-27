/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.ColorProducto;
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
public class ColorProductoJpaController implements Serializable {

    public ColorProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ColorProducto colorProducto) {
        if (colorProducto.getOrdenProduccionList() == null) {
            colorProducto.setOrdenProduccionList(new ArrayList<OrdenProduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OrdenProduccion> attachedOrdenProduccionList = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListOrdenProduccionToAttach : colorProducto.getOrdenProduccionList()) {
                ordenProduccionListOrdenProduccionToAttach = em.getReference(ordenProduccionListOrdenProduccionToAttach.getClass(), ordenProduccionListOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionList.add(ordenProduccionListOrdenProduccionToAttach);
            }
            colorProducto.setOrdenProduccionList(attachedOrdenProduccionList);
            em.persist(colorProducto);
            for (OrdenProduccion ordenProduccionListOrdenProduccion : colorProducto.getOrdenProduccionList()) {
                ColorProducto oldIdColorProductoOfOrdenProduccionListOrdenProduccion = ordenProduccionListOrdenProduccion.getIdColorProducto();
                ordenProduccionListOrdenProduccion.setIdColorProducto(colorProducto);
                ordenProduccionListOrdenProduccion = em.merge(ordenProduccionListOrdenProduccion);
                if (oldIdColorProductoOfOrdenProduccionListOrdenProduccion != null) {
                    oldIdColorProductoOfOrdenProduccionListOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListOrdenProduccion);
                    oldIdColorProductoOfOrdenProduccionListOrdenProduccion = em.merge(oldIdColorProductoOfOrdenProduccionListOrdenProduccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ColorProducto colorProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ColorProducto persistentColorProducto = em.find(ColorProducto.class, colorProducto.getIdColorProducto());
            List<OrdenProduccion> ordenProduccionListOld = persistentColorProducto.getOrdenProduccionList();
            List<OrdenProduccion> ordenProduccionListNew = colorProducto.getOrdenProduccionList();
            List<String> illegalOrphanMessages = null;
            for (OrdenProduccion ordenProduccionListOldOrdenProduccion : ordenProduccionListOld) {
                if (!ordenProduccionListNew.contains(ordenProduccionListOldOrdenProduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenProduccion " + ordenProduccionListOldOrdenProduccion + " since its idColorProducto field is not nullable.");
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
            colorProducto.setOrdenProduccionList(ordenProduccionListNew);
            colorProducto = em.merge(colorProducto);
            for (OrdenProduccion ordenProduccionListNewOrdenProduccion : ordenProduccionListNew) {
                if (!ordenProduccionListOld.contains(ordenProduccionListNewOrdenProduccion)) {
                    ColorProducto oldIdColorProductoOfOrdenProduccionListNewOrdenProduccion = ordenProduccionListNewOrdenProduccion.getIdColorProducto();
                    ordenProduccionListNewOrdenProduccion.setIdColorProducto(colorProducto);
                    ordenProduccionListNewOrdenProduccion = em.merge(ordenProduccionListNewOrdenProduccion);
                    if (oldIdColorProductoOfOrdenProduccionListNewOrdenProduccion != null && !oldIdColorProductoOfOrdenProduccionListNewOrdenProduccion.equals(colorProducto)) {
                        oldIdColorProductoOfOrdenProduccionListNewOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListNewOrdenProduccion);
                        oldIdColorProductoOfOrdenProduccionListNewOrdenProduccion = em.merge(oldIdColorProductoOfOrdenProduccionListNewOrdenProduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colorProducto.getIdColorProducto();
                if (findColorProducto(id) == null) {
                    throw new NonexistentEntityException("The colorProducto with id " + id + " no longer exists.");
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
            ColorProducto colorProducto;
            try {
                colorProducto = em.getReference(ColorProducto.class, id);
                colorProducto.getIdColorProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The colorProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdenProduccion> ordenProduccionListOrphanCheck = colorProducto.getOrdenProduccionList();
            for (OrdenProduccion ordenProduccionListOrphanCheckOrdenProduccion : ordenProduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ColorProducto (" + colorProducto + ") cannot be destroyed since the OrdenProduccion " + ordenProduccionListOrphanCheckOrdenProduccion + " in its ordenProduccionList field has a non-nullable idColorProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(colorProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ColorProducto> findColorProductoEntities() {
        return findColorProductoEntities(true, -1, -1);
    }

    public List<ColorProducto> findColorProductoEntities(int maxResults, int firstResult) {
        return findColorProductoEntities(false, maxResults, firstResult);
    }

    private List<ColorProducto> findColorProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ColorProducto.class));
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

    public ColorProducto findColorProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ColorProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getColorProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ColorProducto> rt = cq.from(ColorProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
