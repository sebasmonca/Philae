/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Controller.exceptions.PreexistingEntityException;
import Persistencia.Entities.EstadoMoldeMaquina;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.Maquina;
import java.util.ArrayList;
import java.util.List;
import Persistencia.Entities.Molde;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class EstadoMoldeMaquinaJpaController implements Serializable {

    public EstadoMoldeMaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoMoldeMaquina estadoMoldeMaquina) throws PreexistingEntityException, Exception {
        if (estadoMoldeMaquina.getMaquinaList() == null) {
            estadoMoldeMaquina.setMaquinaList(new ArrayList<Maquina>());
        }
        if (estadoMoldeMaquina.getMoldeList() == null) {
            estadoMoldeMaquina.setMoldeList(new ArrayList<Molde>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Maquina> attachedMaquinaList = new ArrayList<Maquina>();
            for (Maquina maquinaListMaquinaToAttach : estadoMoldeMaquina.getMaquinaList()) {
                maquinaListMaquinaToAttach = em.getReference(maquinaListMaquinaToAttach.getClass(), maquinaListMaquinaToAttach.getIdMaquina());
                attachedMaquinaList.add(maquinaListMaquinaToAttach);
            }
            estadoMoldeMaquina.setMaquinaList(attachedMaquinaList);
            List<Molde> attachedMoldeList = new ArrayList<Molde>();
            for (Molde moldeListMoldeToAttach : estadoMoldeMaquina.getMoldeList()) {
                moldeListMoldeToAttach = em.getReference(moldeListMoldeToAttach.getClass(), moldeListMoldeToAttach.getIdMolde());
                attachedMoldeList.add(moldeListMoldeToAttach);
            }
            estadoMoldeMaquina.setMoldeList(attachedMoldeList);
            em.persist(estadoMoldeMaquina);
            for (Maquina maquinaListMaquina : estadoMoldeMaquina.getMaquinaList()) {
                EstadoMoldeMaquina oldIdEstadoMoldeMaquinaOfMaquinaListMaquina = maquinaListMaquina.getIdEstadoMoldeMaquina();
                maquinaListMaquina.setIdEstadoMoldeMaquina(estadoMoldeMaquina);
                maquinaListMaquina = em.merge(maquinaListMaquina);
                if (oldIdEstadoMoldeMaquinaOfMaquinaListMaquina != null) {
                    oldIdEstadoMoldeMaquinaOfMaquinaListMaquina.getMaquinaList().remove(maquinaListMaquina);
                    oldIdEstadoMoldeMaquinaOfMaquinaListMaquina = em.merge(oldIdEstadoMoldeMaquinaOfMaquinaListMaquina);
                }
            }
            for (Molde moldeListMolde : estadoMoldeMaquina.getMoldeList()) {
                EstadoMoldeMaquina oldIdEstadoMoldeMaquinaOfMoldeListMolde = moldeListMolde.getIdEstadoMoldeMaquina();
                moldeListMolde.setIdEstadoMoldeMaquina(estadoMoldeMaquina);
                moldeListMolde = em.merge(moldeListMolde);
                if (oldIdEstadoMoldeMaquinaOfMoldeListMolde != null) {
                    oldIdEstadoMoldeMaquinaOfMoldeListMolde.getMoldeList().remove(moldeListMolde);
                    oldIdEstadoMoldeMaquinaOfMoldeListMolde = em.merge(oldIdEstadoMoldeMaquinaOfMoldeListMolde);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoMoldeMaquina(estadoMoldeMaquina.getIdEstadoMoldeMaquina()) != null) {
                throw new PreexistingEntityException("EstadoMoldeMaquina " + estadoMoldeMaquina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoMoldeMaquina estadoMoldeMaquina) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoMoldeMaquina persistentEstadoMoldeMaquina = em.find(EstadoMoldeMaquina.class, estadoMoldeMaquina.getIdEstadoMoldeMaquina());
            List<Maquina> maquinaListOld = persistentEstadoMoldeMaquina.getMaquinaList();
            List<Maquina> maquinaListNew = estadoMoldeMaquina.getMaquinaList();
            List<Molde> moldeListOld = persistentEstadoMoldeMaquina.getMoldeList();
            List<Molde> moldeListNew = estadoMoldeMaquina.getMoldeList();
            List<String> illegalOrphanMessages = null;
            for (Maquina maquinaListOldMaquina : maquinaListOld) {
                if (!maquinaListNew.contains(maquinaListOldMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Maquina " + maquinaListOldMaquina + " since its idEstadoMoldeMaquina field is not nullable.");
                }
            }
            for (Molde moldeListOldMolde : moldeListOld) {
                if (!moldeListNew.contains(moldeListOldMolde)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Molde " + moldeListOldMolde + " since its idEstadoMoldeMaquina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Maquina> attachedMaquinaListNew = new ArrayList<Maquina>();
            for (Maquina maquinaListNewMaquinaToAttach : maquinaListNew) {
                maquinaListNewMaquinaToAttach = em.getReference(maquinaListNewMaquinaToAttach.getClass(), maquinaListNewMaquinaToAttach.getIdMaquina());
                attachedMaquinaListNew.add(maquinaListNewMaquinaToAttach);
            }
            maquinaListNew = attachedMaquinaListNew;
            estadoMoldeMaquina.setMaquinaList(maquinaListNew);
            List<Molde> attachedMoldeListNew = new ArrayList<Molde>();
            for (Molde moldeListNewMoldeToAttach : moldeListNew) {
                moldeListNewMoldeToAttach = em.getReference(moldeListNewMoldeToAttach.getClass(), moldeListNewMoldeToAttach.getIdMolde());
                attachedMoldeListNew.add(moldeListNewMoldeToAttach);
            }
            moldeListNew = attachedMoldeListNew;
            estadoMoldeMaquina.setMoldeList(moldeListNew);
            estadoMoldeMaquina = em.merge(estadoMoldeMaquina);
            for (Maquina maquinaListNewMaquina : maquinaListNew) {
                if (!maquinaListOld.contains(maquinaListNewMaquina)) {
                    EstadoMoldeMaquina oldIdEstadoMoldeMaquinaOfMaquinaListNewMaquina = maquinaListNewMaquina.getIdEstadoMoldeMaquina();
                    maquinaListNewMaquina.setIdEstadoMoldeMaquina(estadoMoldeMaquina);
                    maquinaListNewMaquina = em.merge(maquinaListNewMaquina);
                    if (oldIdEstadoMoldeMaquinaOfMaquinaListNewMaquina != null && !oldIdEstadoMoldeMaquinaOfMaquinaListNewMaquina.equals(estadoMoldeMaquina)) {
                        oldIdEstadoMoldeMaquinaOfMaquinaListNewMaquina.getMaquinaList().remove(maquinaListNewMaquina);
                        oldIdEstadoMoldeMaquinaOfMaquinaListNewMaquina = em.merge(oldIdEstadoMoldeMaquinaOfMaquinaListNewMaquina);
                    }
                }
            }
            for (Molde moldeListNewMolde : moldeListNew) {
                if (!moldeListOld.contains(moldeListNewMolde)) {
                    EstadoMoldeMaquina oldIdEstadoMoldeMaquinaOfMoldeListNewMolde = moldeListNewMolde.getIdEstadoMoldeMaquina();
                    moldeListNewMolde.setIdEstadoMoldeMaquina(estadoMoldeMaquina);
                    moldeListNewMolde = em.merge(moldeListNewMolde);
                    if (oldIdEstadoMoldeMaquinaOfMoldeListNewMolde != null && !oldIdEstadoMoldeMaquinaOfMoldeListNewMolde.equals(estadoMoldeMaquina)) {
                        oldIdEstadoMoldeMaquinaOfMoldeListNewMolde.getMoldeList().remove(moldeListNewMolde);
                        oldIdEstadoMoldeMaquinaOfMoldeListNewMolde = em.merge(oldIdEstadoMoldeMaquinaOfMoldeListNewMolde);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoMoldeMaquina.getIdEstadoMoldeMaquina();
                if (findEstadoMoldeMaquina(id) == null) {
                    throw new NonexistentEntityException("The estadoMoldeMaquina with id " + id + " no longer exists.");
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
            EstadoMoldeMaquina estadoMoldeMaquina;
            try {
                estadoMoldeMaquina = em.getReference(EstadoMoldeMaquina.class, id);
                estadoMoldeMaquina.getIdEstadoMoldeMaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoMoldeMaquina with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Maquina> maquinaListOrphanCheck = estadoMoldeMaquina.getMaquinaList();
            for (Maquina maquinaListOrphanCheckMaquina : maquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoMoldeMaquina (" + estadoMoldeMaquina + ") cannot be destroyed since the Maquina " + maquinaListOrphanCheckMaquina + " in its maquinaList field has a non-nullable idEstadoMoldeMaquina field.");
            }
            List<Molde> moldeListOrphanCheck = estadoMoldeMaquina.getMoldeList();
            for (Molde moldeListOrphanCheckMolde : moldeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoMoldeMaquina (" + estadoMoldeMaquina + ") cannot be destroyed since the Molde " + moldeListOrphanCheckMolde + " in its moldeList field has a non-nullable idEstadoMoldeMaquina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoMoldeMaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoMoldeMaquina> findEstadoMoldeMaquinaEntities() {
        return findEstadoMoldeMaquinaEntities(true, -1, -1);
    }

    public List<EstadoMoldeMaquina> findEstadoMoldeMaquinaEntities(int maxResults, int firstResult) {
        return findEstadoMoldeMaquinaEntities(false, maxResults, firstResult);
    }

    private List<EstadoMoldeMaquina> findEstadoMoldeMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoMoldeMaquina.class));
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

    public EstadoMoldeMaquina findEstadoMoldeMaquina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoMoldeMaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoMoldeMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoMoldeMaquina> rt = cq.from(EstadoMoldeMaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
