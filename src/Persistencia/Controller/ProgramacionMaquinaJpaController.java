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
import Persistencia.Entities.Maquina;
import Persistencia.Entities.Molde;
import Persistencia.Entities.OrdenProduccion;
import Persistencia.Entities.ProgramacionMaquina;
import Persistencia.Entities.TrabajoMaquina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class ProgramacionMaquinaJpaController implements Serializable {

    public ProgramacionMaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionMaquina programacionMaquina) {
        if (programacionMaquina.getTrabajoMaquinaList() == null) {
            programacionMaquina.setTrabajoMaquinaList(new ArrayList<TrabajoMaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquina maquinaidMaquina = programacionMaquina.getMaquinaidMaquina();
            if (maquinaidMaquina != null) {
                maquinaidMaquina = em.getReference(maquinaidMaquina.getClass(), maquinaidMaquina.getIdMaquina());
                programacionMaquina.setMaquinaidMaquina(maquinaidMaquina);
            }
            Molde moldeidMolde = programacionMaquina.getMoldeidMolde();
            if (moldeidMolde != null) {
                moldeidMolde = em.getReference(moldeidMolde.getClass(), moldeidMolde.getIdMolde());
                programacionMaquina.setMoldeidMolde(moldeidMolde);
            }
            OrdenProduccion idOrdenProduccion = programacionMaquina.getIdOrdenProduccion();
            if (idOrdenProduccion != null) {
                idOrdenProduccion = em.getReference(idOrdenProduccion.getClass(), idOrdenProduccion.getIdOrdenProduccion());
                programacionMaquina.setIdOrdenProduccion(idOrdenProduccion);
            }
            List<TrabajoMaquina> attachedTrabajoMaquinaList = new ArrayList<TrabajoMaquina>();
            for (TrabajoMaquina trabajoMaquinaListTrabajoMaquinaToAttach : programacionMaquina.getTrabajoMaquinaList()) {
                trabajoMaquinaListTrabajoMaquinaToAttach = em.getReference(trabajoMaquinaListTrabajoMaquinaToAttach.getClass(), trabajoMaquinaListTrabajoMaquinaToAttach.getIdTrabajoMaquina());
                attachedTrabajoMaquinaList.add(trabajoMaquinaListTrabajoMaquinaToAttach);
            }
            programacionMaquina.setTrabajoMaquinaList(attachedTrabajoMaquinaList);
            em.persist(programacionMaquina);
            if (maquinaidMaquina != null) {
                maquinaidMaquina.getProgramacionMaquinaList().add(programacionMaquina);
                maquinaidMaquina = em.merge(maquinaidMaquina);
            }
            if (moldeidMolde != null) {
                moldeidMolde.getProgramacionMaquinaList().add(programacionMaquina);
                moldeidMolde = em.merge(moldeidMolde);
            }
            if (idOrdenProduccion != null) {
                idOrdenProduccion.getProgramacionMaquinaList().add(programacionMaquina);
                idOrdenProduccion = em.merge(idOrdenProduccion);
            }
            for (TrabajoMaquina trabajoMaquinaListTrabajoMaquina : programacionMaquina.getTrabajoMaquinaList()) {
                ProgramacionMaquina oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListTrabajoMaquina = trabajoMaquinaListTrabajoMaquina.getProgramacionMaquinaidProgramacionMaquina();
                trabajoMaquinaListTrabajoMaquina.setProgramacionMaquinaidProgramacionMaquina(programacionMaquina);
                trabajoMaquinaListTrabajoMaquina = em.merge(trabajoMaquinaListTrabajoMaquina);
                if (oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListTrabajoMaquina != null) {
                    oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListTrabajoMaquina.getTrabajoMaquinaList().remove(trabajoMaquinaListTrabajoMaquina);
                    oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListTrabajoMaquina = em.merge(oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListTrabajoMaquina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramacionMaquina programacionMaquina) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionMaquina persistentProgramacionMaquina = em.find(ProgramacionMaquina.class, programacionMaquina.getIdProgramacionMaquina());
            Maquina maquinaidMaquinaOld = persistentProgramacionMaquina.getMaquinaidMaquina();
            Maquina maquinaidMaquinaNew = programacionMaquina.getMaquinaidMaquina();
            Molde moldeidMoldeOld = persistentProgramacionMaquina.getMoldeidMolde();
            Molde moldeidMoldeNew = programacionMaquina.getMoldeidMolde();
            OrdenProduccion idOrdenProduccionOld = persistentProgramacionMaquina.getIdOrdenProduccion();
            OrdenProduccion idOrdenProduccionNew = programacionMaquina.getIdOrdenProduccion();
            List<TrabajoMaquina> trabajoMaquinaListOld = persistentProgramacionMaquina.getTrabajoMaquinaList();
            List<TrabajoMaquina> trabajoMaquinaListNew = programacionMaquina.getTrabajoMaquinaList();
            List<String> illegalOrphanMessages = null;
            for (TrabajoMaquina trabajoMaquinaListOldTrabajoMaquina : trabajoMaquinaListOld) {
                if (!trabajoMaquinaListNew.contains(trabajoMaquinaListOldTrabajoMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TrabajoMaquina " + trabajoMaquinaListOldTrabajoMaquina + " since its programacionMaquinaidProgramacionMaquina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (maquinaidMaquinaNew != null) {
                maquinaidMaquinaNew = em.getReference(maquinaidMaquinaNew.getClass(), maquinaidMaquinaNew.getIdMaquina());
                programacionMaquina.setMaquinaidMaquina(maquinaidMaquinaNew);
            }
            if (moldeidMoldeNew != null) {
                moldeidMoldeNew = em.getReference(moldeidMoldeNew.getClass(), moldeidMoldeNew.getIdMolde());
                programacionMaquina.setMoldeidMolde(moldeidMoldeNew);
            }
            if (idOrdenProduccionNew != null) {
                idOrdenProduccionNew = em.getReference(idOrdenProduccionNew.getClass(), idOrdenProduccionNew.getIdOrdenProduccion());
                programacionMaquina.setIdOrdenProduccion(idOrdenProduccionNew);
            }
            List<TrabajoMaquina> attachedTrabajoMaquinaListNew = new ArrayList<TrabajoMaquina>();
            for (TrabajoMaquina trabajoMaquinaListNewTrabajoMaquinaToAttach : trabajoMaquinaListNew) {
                trabajoMaquinaListNewTrabajoMaquinaToAttach = em.getReference(trabajoMaquinaListNewTrabajoMaquinaToAttach.getClass(), trabajoMaquinaListNewTrabajoMaquinaToAttach.getIdTrabajoMaquina());
                attachedTrabajoMaquinaListNew.add(trabajoMaquinaListNewTrabajoMaquinaToAttach);
            }
            trabajoMaquinaListNew = attachedTrabajoMaquinaListNew;
            programacionMaquina.setTrabajoMaquinaList(trabajoMaquinaListNew);
            programacionMaquina = em.merge(programacionMaquina);
            if (maquinaidMaquinaOld != null && !maquinaidMaquinaOld.equals(maquinaidMaquinaNew)) {
                maquinaidMaquinaOld.getProgramacionMaquinaList().remove(programacionMaquina);
                maquinaidMaquinaOld = em.merge(maquinaidMaquinaOld);
            }
            if (maquinaidMaquinaNew != null && !maquinaidMaquinaNew.equals(maquinaidMaquinaOld)) {
                maquinaidMaquinaNew.getProgramacionMaquinaList().add(programacionMaquina);
                maquinaidMaquinaNew = em.merge(maquinaidMaquinaNew);
            }
            if (moldeidMoldeOld != null && !moldeidMoldeOld.equals(moldeidMoldeNew)) {
                moldeidMoldeOld.getProgramacionMaquinaList().remove(programacionMaquina);
                moldeidMoldeOld = em.merge(moldeidMoldeOld);
            }
            if (moldeidMoldeNew != null && !moldeidMoldeNew.equals(moldeidMoldeOld)) {
                moldeidMoldeNew.getProgramacionMaquinaList().add(programacionMaquina);
                moldeidMoldeNew = em.merge(moldeidMoldeNew);
            }
            if (idOrdenProduccionOld != null && !idOrdenProduccionOld.equals(idOrdenProduccionNew)) {
                idOrdenProduccionOld.getProgramacionMaquinaList().remove(programacionMaquina);
                idOrdenProduccionOld = em.merge(idOrdenProduccionOld);
            }
            if (idOrdenProduccionNew != null && !idOrdenProduccionNew.equals(idOrdenProduccionOld)) {
                idOrdenProduccionNew.getProgramacionMaquinaList().add(programacionMaquina);
                idOrdenProduccionNew = em.merge(idOrdenProduccionNew);
            }
            for (TrabajoMaquina trabajoMaquinaListNewTrabajoMaquina : trabajoMaquinaListNew) {
                if (!trabajoMaquinaListOld.contains(trabajoMaquinaListNewTrabajoMaquina)) {
                    ProgramacionMaquina oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListNewTrabajoMaquina = trabajoMaquinaListNewTrabajoMaquina.getProgramacionMaquinaidProgramacionMaquina();
                    trabajoMaquinaListNewTrabajoMaquina.setProgramacionMaquinaidProgramacionMaquina(programacionMaquina);
                    trabajoMaquinaListNewTrabajoMaquina = em.merge(trabajoMaquinaListNewTrabajoMaquina);
                    if (oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListNewTrabajoMaquina != null && !oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListNewTrabajoMaquina.equals(programacionMaquina)) {
                        oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListNewTrabajoMaquina.getTrabajoMaquinaList().remove(trabajoMaquinaListNewTrabajoMaquina);
                        oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListNewTrabajoMaquina = em.merge(oldProgramacionMaquinaidProgramacionMaquinaOfTrabajoMaquinaListNewTrabajoMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionMaquina.getIdProgramacionMaquina();
                if (findProgramacionMaquina(id) == null) {
                    throw new NonexistentEntityException("The programacionMaquina with id " + id + " no longer exists.");
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
            ProgramacionMaquina programacionMaquina;
            try {
                programacionMaquina = em.getReference(ProgramacionMaquina.class, id);
                programacionMaquina.getIdProgramacionMaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionMaquina with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TrabajoMaquina> trabajoMaquinaListOrphanCheck = programacionMaquina.getTrabajoMaquinaList();
            for (TrabajoMaquina trabajoMaquinaListOrphanCheckTrabajoMaquina : trabajoMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProgramacionMaquina (" + programacionMaquina + ") cannot be destroyed since the TrabajoMaquina " + trabajoMaquinaListOrphanCheckTrabajoMaquina + " in its trabajoMaquinaList field has a non-nullable programacionMaquinaidProgramacionMaquina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Maquina maquinaidMaquina = programacionMaquina.getMaquinaidMaquina();
            if (maquinaidMaquina != null) {
                maquinaidMaquina.getProgramacionMaquinaList().remove(programacionMaquina);
                maquinaidMaquina = em.merge(maquinaidMaquina);
            }
            Molde moldeidMolde = programacionMaquina.getMoldeidMolde();
            if (moldeidMolde != null) {
                moldeidMolde.getProgramacionMaquinaList().remove(programacionMaquina);
                moldeidMolde = em.merge(moldeidMolde);
            }
            OrdenProduccion idOrdenProduccion = programacionMaquina.getIdOrdenProduccion();
            if (idOrdenProduccion != null) {
                idOrdenProduccion.getProgramacionMaquinaList().remove(programacionMaquina);
                idOrdenProduccion = em.merge(idOrdenProduccion);
            }
            em.remove(programacionMaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramacionMaquina> findProgramacionMaquinaEntities() {
        return findProgramacionMaquinaEntities(true, -1, -1);
    }

    public List<ProgramacionMaquina> findProgramacionMaquinaEntities(int maxResults, int firstResult) {
        return findProgramacionMaquinaEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionMaquina> findProgramacionMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProgramacionMaquina.class));
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

    public ProgramacionMaquina findProgramacionMaquina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionMaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProgramacionMaquina> rt = cq.from(ProgramacionMaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
