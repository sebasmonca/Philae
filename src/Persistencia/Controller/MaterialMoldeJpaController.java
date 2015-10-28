/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.MaterialMolde;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.Molde;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yuri
 */
public class MaterialMoldeJpaController implements Serializable {

    public MaterialMoldeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MaterialMolde materialMolde) {
        if (materialMolde.getMoldeList() == null) {
            materialMolde.setMoldeList(new ArrayList<Molde>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Molde> attachedMoldeList = new ArrayList<Molde>();
            for (Molde moldeListMoldeToAttach : materialMolde.getMoldeList()) {
                moldeListMoldeToAttach = em.getReference(moldeListMoldeToAttach.getClass(), moldeListMoldeToAttach.getIdMolde());
                attachedMoldeList.add(moldeListMoldeToAttach);
            }
            materialMolde.setMoldeList(attachedMoldeList);
            em.persist(materialMolde);
            for (Molde moldeListMolde : materialMolde.getMoldeList()) {
                MaterialMolde oldIdMaterialMoldeOfMoldeListMolde = moldeListMolde.getIdMaterialMolde();
                moldeListMolde.setIdMaterialMolde(materialMolde);
                moldeListMolde = em.merge(moldeListMolde);
                if (oldIdMaterialMoldeOfMoldeListMolde != null) {
                    oldIdMaterialMoldeOfMoldeListMolde.getMoldeList().remove(moldeListMolde);
                    oldIdMaterialMoldeOfMoldeListMolde = em.merge(oldIdMaterialMoldeOfMoldeListMolde);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MaterialMolde materialMolde) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MaterialMolde persistentMaterialMolde = em.find(MaterialMolde.class, materialMolde.getIdMaterialMolde());
            List<Molde> moldeListOld = persistentMaterialMolde.getMoldeList();
            List<Molde> moldeListNew = materialMolde.getMoldeList();
            List<String> illegalOrphanMessages = null;
            for (Molde moldeListOldMolde : moldeListOld) {
                if (!moldeListNew.contains(moldeListOldMolde)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Molde " + moldeListOldMolde + " since its idMaterialMolde field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Molde> attachedMoldeListNew = new ArrayList<Molde>();
            for (Molde moldeListNewMoldeToAttach : moldeListNew) {
                moldeListNewMoldeToAttach = em.getReference(moldeListNewMoldeToAttach.getClass(), moldeListNewMoldeToAttach.getIdMolde());
                attachedMoldeListNew.add(moldeListNewMoldeToAttach);
            }
            moldeListNew = attachedMoldeListNew;
            materialMolde.setMoldeList(moldeListNew);
            materialMolde = em.merge(materialMolde);
            for (Molde moldeListNewMolde : moldeListNew) {
                if (!moldeListOld.contains(moldeListNewMolde)) {
                    MaterialMolde oldIdMaterialMoldeOfMoldeListNewMolde = moldeListNewMolde.getIdMaterialMolde();
                    moldeListNewMolde.setIdMaterialMolde(materialMolde);
                    moldeListNewMolde = em.merge(moldeListNewMolde);
                    if (oldIdMaterialMoldeOfMoldeListNewMolde != null && !oldIdMaterialMoldeOfMoldeListNewMolde.equals(materialMolde)) {
                        oldIdMaterialMoldeOfMoldeListNewMolde.getMoldeList().remove(moldeListNewMolde);
                        oldIdMaterialMoldeOfMoldeListNewMolde = em.merge(oldIdMaterialMoldeOfMoldeListNewMolde);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materialMolde.getIdMaterialMolde();
                if (findMaterialMolde(id) == null) {
                    throw new NonexistentEntityException("The materialMolde with id " + id + " no longer exists.");
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
            MaterialMolde materialMolde;
            try {
                materialMolde = em.getReference(MaterialMolde.class, id);
                materialMolde.getIdMaterialMolde();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materialMolde with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Molde> moldeListOrphanCheck = materialMolde.getMoldeList();
            for (Molde moldeListOrphanCheckMolde : moldeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MaterialMolde (" + materialMolde + ") cannot be destroyed since the Molde " + moldeListOrphanCheckMolde + " in its moldeList field has a non-nullable idMaterialMolde field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(materialMolde);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MaterialMolde> findMaterialMoldeEntities() {
        return findMaterialMoldeEntities(true, -1, -1);
    }

    public List<MaterialMolde> findMaterialMoldeEntities(int maxResults, int firstResult) {
        return findMaterialMoldeEntities(false, maxResults, firstResult);
    }

    private List<MaterialMolde> findMaterialMoldeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MaterialMolde.class));
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

    public MaterialMolde findMaterialMolde(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MaterialMolde.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialMoldeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MaterialMolde> rt = cq.from(MaterialMolde.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Persistencia.Entities.MaterialMolde> buscar(String id, String descripcion) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<MaterialMolde> cq = em.getCriteriaBuilder().createQuery(MaterialMolde.class);
            Root<MaterialMolde> rt = cq.from(MaterialMolde.class);
            cq.where(em.getCriteriaBuilder().equal(rt.get("idMateralMolde"), Integer.parseInt(id)),
                    em.getCriteriaBuilder().equal(rt.get("descripcion"), descripcion));
            return (em.createQuery(cq)).getResultList();
        } finally {
            em.close();
        }
    }
}
