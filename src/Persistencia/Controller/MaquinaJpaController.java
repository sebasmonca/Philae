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
import Persistencia.Entities.EstadoMoldeMaquina;
import Persistencia.Entities.MantenimientoMoldeMaquina;
import Persistencia.Entities.Maquina;
import java.util.ArrayList;
import java.util.List;
import Persistencia.Entities.ProgramacionMaquina;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class MaquinaJpaController implements Serializable {

    public MaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquina maquina) {
        if (maquina.getMantenimientoMoldeMaquinaList() == null) {
            maquina.setMantenimientoMoldeMaquinaList(new ArrayList<MantenimientoMoldeMaquina>());
        }
        if (maquina.getProgramacionMaquinaList() == null) {
            maquina.setProgramacionMaquinaList(new ArrayList<ProgramacionMaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoMoldeMaquina idEstadoMoldeMaquina = maquina.getIdEstadoMoldeMaquina();
            if (idEstadoMoldeMaquina != null) {
                idEstadoMoldeMaquina = em.getReference(idEstadoMoldeMaquina.getClass(), idEstadoMoldeMaquina.getIdEstadoMoldeMaquina());
                maquina.setIdEstadoMoldeMaquina(idEstadoMoldeMaquina);
            }
            List<MantenimientoMoldeMaquina> attachedMantenimientoMoldeMaquinaList = new ArrayList<MantenimientoMoldeMaquina>();
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach : maquina.getMantenimientoMoldeMaquinaList()) {
                mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach = em.getReference(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach.getClass(), mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach.getIdMantenimientoMoldeMaquina());
                attachedMantenimientoMoldeMaquinaList.add(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach);
            }
            maquina.setMantenimientoMoldeMaquinaList(attachedMantenimientoMoldeMaquinaList);
            List<ProgramacionMaquina> attachedProgramacionMaquinaList = new ArrayList<ProgramacionMaquina>();
            for (ProgramacionMaquina programacionMaquinaListProgramacionMaquinaToAttach : maquina.getProgramacionMaquinaList()) {
                programacionMaquinaListProgramacionMaquinaToAttach = em.getReference(programacionMaquinaListProgramacionMaquinaToAttach.getClass(), programacionMaquinaListProgramacionMaquinaToAttach.getIdProgramacionMaquina());
                attachedProgramacionMaquinaList.add(programacionMaquinaListProgramacionMaquinaToAttach);
            }
            maquina.setProgramacionMaquinaList(attachedProgramacionMaquinaList);
            em.persist(maquina);
            if (idEstadoMoldeMaquina != null) {
                idEstadoMoldeMaquina.getMaquinaList().add(maquina);
                idEstadoMoldeMaquina = em.merge(idEstadoMoldeMaquina);
            }
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina : maquina.getMantenimientoMoldeMaquinaList()) {
                Maquina oldIdMaquinaOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina = mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina.getIdMaquina();
                mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina.setIdMaquina(maquina);
                mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina = em.merge(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina);
                if (oldIdMaquinaOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina != null) {
                    oldIdMaquinaOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina);
                    oldIdMaquinaOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina = em.merge(oldIdMaquinaOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina);
                }
            }
            for (ProgramacionMaquina programacionMaquinaListProgramacionMaquina : maquina.getProgramacionMaquinaList()) {
                Maquina oldMaquinaidMaquinaOfProgramacionMaquinaListProgramacionMaquina = programacionMaquinaListProgramacionMaquina.getMaquinaidMaquina();
                programacionMaquinaListProgramacionMaquina.setMaquinaidMaquina(maquina);
                programacionMaquinaListProgramacionMaquina = em.merge(programacionMaquinaListProgramacionMaquina);
                if (oldMaquinaidMaquinaOfProgramacionMaquinaListProgramacionMaquina != null) {
                    oldMaquinaidMaquinaOfProgramacionMaquinaListProgramacionMaquina.getProgramacionMaquinaList().remove(programacionMaquinaListProgramacionMaquina);
                    oldMaquinaidMaquinaOfProgramacionMaquinaListProgramacionMaquina = em.merge(oldMaquinaidMaquinaOfProgramacionMaquinaListProgramacionMaquina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquina maquina) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquina persistentMaquina = em.find(Maquina.class, maquina.getIdMaquina());
            EstadoMoldeMaquina idEstadoMoldeMaquinaOld = persistentMaquina.getIdEstadoMoldeMaquina();
            EstadoMoldeMaquina idEstadoMoldeMaquinaNew = maquina.getIdEstadoMoldeMaquina();
            List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaListOld = persistentMaquina.getMantenimientoMoldeMaquinaList();
            List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaListNew = maquina.getMantenimientoMoldeMaquinaList();
            List<ProgramacionMaquina> programacionMaquinaListOld = persistentMaquina.getProgramacionMaquinaList();
            List<ProgramacionMaquina> programacionMaquinaListNew = maquina.getProgramacionMaquinaList();
            List<String> illegalOrphanMessages = null;
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListOldMantenimientoMoldeMaquina : mantenimientoMoldeMaquinaListOld) {
                if (!mantenimientoMoldeMaquinaListNew.contains(mantenimientoMoldeMaquinaListOldMantenimientoMoldeMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MantenimientoMoldeMaquina " + mantenimientoMoldeMaquinaListOldMantenimientoMoldeMaquina + " since its idMaquina field is not nullable.");
                }
            }
            for (ProgramacionMaquina programacionMaquinaListOldProgramacionMaquina : programacionMaquinaListOld) {
                if (!programacionMaquinaListNew.contains(programacionMaquinaListOldProgramacionMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramacionMaquina " + programacionMaquinaListOldProgramacionMaquina + " since its maquinaidMaquina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEstadoMoldeMaquinaNew != null) {
                idEstadoMoldeMaquinaNew = em.getReference(idEstadoMoldeMaquinaNew.getClass(), idEstadoMoldeMaquinaNew.getIdEstadoMoldeMaquina());
                maquina.setIdEstadoMoldeMaquina(idEstadoMoldeMaquinaNew);
            }
            List<MantenimientoMoldeMaquina> attachedMantenimientoMoldeMaquinaListNew = new ArrayList<MantenimientoMoldeMaquina>();
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach : mantenimientoMoldeMaquinaListNew) {
                mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach = em.getReference(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach.getClass(), mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach.getIdMantenimientoMoldeMaquina());
                attachedMantenimientoMoldeMaquinaListNew.add(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach);
            }
            mantenimientoMoldeMaquinaListNew = attachedMantenimientoMoldeMaquinaListNew;
            maquina.setMantenimientoMoldeMaquinaList(mantenimientoMoldeMaquinaListNew);
            List<ProgramacionMaquina> attachedProgramacionMaquinaListNew = new ArrayList<ProgramacionMaquina>();
            for (ProgramacionMaquina programacionMaquinaListNewProgramacionMaquinaToAttach : programacionMaquinaListNew) {
                programacionMaquinaListNewProgramacionMaquinaToAttach = em.getReference(programacionMaquinaListNewProgramacionMaquinaToAttach.getClass(), programacionMaquinaListNewProgramacionMaquinaToAttach.getIdProgramacionMaquina());
                attachedProgramacionMaquinaListNew.add(programacionMaquinaListNewProgramacionMaquinaToAttach);
            }
            programacionMaquinaListNew = attachedProgramacionMaquinaListNew;
            maquina.setProgramacionMaquinaList(programacionMaquinaListNew);
            maquina = em.merge(maquina);
            if (idEstadoMoldeMaquinaOld != null && !idEstadoMoldeMaquinaOld.equals(idEstadoMoldeMaquinaNew)) {
                idEstadoMoldeMaquinaOld.getMaquinaList().remove(maquina);
                idEstadoMoldeMaquinaOld = em.merge(idEstadoMoldeMaquinaOld);
            }
            if (idEstadoMoldeMaquinaNew != null && !idEstadoMoldeMaquinaNew.equals(idEstadoMoldeMaquinaOld)) {
                idEstadoMoldeMaquinaNew.getMaquinaList().add(maquina);
                idEstadoMoldeMaquinaNew = em.merge(idEstadoMoldeMaquinaNew);
            }
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina : mantenimientoMoldeMaquinaListNew) {
                if (!mantenimientoMoldeMaquinaListOld.contains(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina)) {
                    Maquina oldIdMaquinaOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina = mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.getIdMaquina();
                    mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.setIdMaquina(maquina);
                    mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina = em.merge(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina);
                    if (oldIdMaquinaOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina != null && !oldIdMaquinaOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.equals(maquina)) {
                        oldIdMaquinaOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina);
                        oldIdMaquinaOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina = em.merge(oldIdMaquinaOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina);
                    }
                }
            }
            for (ProgramacionMaquina programacionMaquinaListNewProgramacionMaquina : programacionMaquinaListNew) {
                if (!programacionMaquinaListOld.contains(programacionMaquinaListNewProgramacionMaquina)) {
                    Maquina oldMaquinaidMaquinaOfProgramacionMaquinaListNewProgramacionMaquina = programacionMaquinaListNewProgramacionMaquina.getMaquinaidMaquina();
                    programacionMaquinaListNewProgramacionMaquina.setMaquinaidMaquina(maquina);
                    programacionMaquinaListNewProgramacionMaquina = em.merge(programacionMaquinaListNewProgramacionMaquina);
                    if (oldMaquinaidMaquinaOfProgramacionMaquinaListNewProgramacionMaquina != null && !oldMaquinaidMaquinaOfProgramacionMaquinaListNewProgramacionMaquina.equals(maquina)) {
                        oldMaquinaidMaquinaOfProgramacionMaquinaListNewProgramacionMaquina.getProgramacionMaquinaList().remove(programacionMaquinaListNewProgramacionMaquina);
                        oldMaquinaidMaquinaOfProgramacionMaquinaListNewProgramacionMaquina = em.merge(oldMaquinaidMaquinaOfProgramacionMaquinaListNewProgramacionMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = maquina.getIdMaquina();
                if (findMaquina(id) == null) {
                    throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.");
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
            Maquina maquina;
            try {
                maquina = em.getReference(Maquina.class, id);
                maquina.getIdMaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquina with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaListOrphanCheck = maquina.getMantenimientoMoldeMaquinaList();
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListOrphanCheckMantenimientoMoldeMaquina : mantenimientoMoldeMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Maquina (" + maquina + ") cannot be destroyed since the MantenimientoMoldeMaquina " + mantenimientoMoldeMaquinaListOrphanCheckMantenimientoMoldeMaquina + " in its mantenimientoMoldeMaquinaList field has a non-nullable idMaquina field.");
            }
            List<ProgramacionMaquina> programacionMaquinaListOrphanCheck = maquina.getProgramacionMaquinaList();
            for (ProgramacionMaquina programacionMaquinaListOrphanCheckProgramacionMaquina : programacionMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Maquina (" + maquina + ") cannot be destroyed since the ProgramacionMaquina " + programacionMaquinaListOrphanCheckProgramacionMaquina + " in its programacionMaquinaList field has a non-nullable maquinaidMaquina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            EstadoMoldeMaquina idEstadoMoldeMaquina = maquina.getIdEstadoMoldeMaquina();
            if (idEstadoMoldeMaquina != null) {
                idEstadoMoldeMaquina.getMaquinaList().remove(maquina);
                idEstadoMoldeMaquina = em.merge(idEstadoMoldeMaquina);
            }
            em.remove(maquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquina> findMaquinaEntities() {
        return findMaquinaEntities(true, -1, -1);
    }

    public List<Maquina> findMaquinaEntities(int maxResults, int firstResult) {
        return findMaquinaEntities(false, maxResults, firstResult);
    }

    private List<Maquina> findMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquina.class));
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

    public Maquina findMaquina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquina> rt = cq.from(Maquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
