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
import Persistencia.Entities.Cliente;
import Persistencia.Entities.EstadoMoldeMaquina;
import Persistencia.Entities.MaterialMolde;
import Persistencia.Entities.MantenimientoMoldeMaquina;
import Persistencia.Entities.Molde;
import java.util.ArrayList;
import java.util.List;
import Persistencia.Entities.OrdenProduccion;
import Persistencia.Entities.ProgramacionMaquina;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class MoldeJpaController implements Serializable {

    public MoldeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Molde molde) {
        if (molde.getMantenimientoMoldeMaquinaList() == null) {
            molde.setMantenimientoMoldeMaquinaList(new ArrayList<MantenimientoMoldeMaquina>());
        }
        if (molde.getOrdenProduccionList() == null) {
            molde.setOrdenProduccionList(new ArrayList<OrdenProduccion>());
        }
        if (molde.getProgramacionMaquinaList() == null) {
            molde.setProgramacionMaquinaList(new ArrayList<ProgramacionMaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = molde.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                molde.setIdCliente(idCliente);
            }
            EstadoMoldeMaquina idEstadoMoldeMaquina = molde.getIdEstadoMoldeMaquina();
            if (idEstadoMoldeMaquina != null) {
                idEstadoMoldeMaquina = em.getReference(idEstadoMoldeMaquina.getClass(), idEstadoMoldeMaquina.getIdEstadoMoldeMaquina());
                molde.setIdEstadoMoldeMaquina(idEstadoMoldeMaquina);
            }
            MaterialMolde idMaterialMolde = molde.getIdMaterialMolde();
            if (idMaterialMolde != null) {
                idMaterialMolde = em.getReference(idMaterialMolde.getClass(), idMaterialMolde.getIdMaterialMolde());
                molde.setIdMaterialMolde(idMaterialMolde);
            }
            List<MantenimientoMoldeMaquina> attachedMantenimientoMoldeMaquinaList = new ArrayList<MantenimientoMoldeMaquina>();
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach : molde.getMantenimientoMoldeMaquinaList()) {
                mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach = em.getReference(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach.getClass(), mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach.getIdMantenimientoMoldeMaquina());
                attachedMantenimientoMoldeMaquinaList.add(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquinaToAttach);
            }
            molde.setMantenimientoMoldeMaquinaList(attachedMantenimientoMoldeMaquinaList);
            List<OrdenProduccion> attachedOrdenProduccionList = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListOrdenProduccionToAttach : molde.getOrdenProduccionList()) {
                ordenProduccionListOrdenProduccionToAttach = em.getReference(ordenProduccionListOrdenProduccionToAttach.getClass(), ordenProduccionListOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionList.add(ordenProduccionListOrdenProduccionToAttach);
            }
            molde.setOrdenProduccionList(attachedOrdenProduccionList);
            List<ProgramacionMaquina> attachedProgramacionMaquinaList = new ArrayList<ProgramacionMaquina>();
            for (ProgramacionMaquina programacionMaquinaListProgramacionMaquinaToAttach : molde.getProgramacionMaquinaList()) {
                programacionMaquinaListProgramacionMaquinaToAttach = em.getReference(programacionMaquinaListProgramacionMaquinaToAttach.getClass(), programacionMaquinaListProgramacionMaquinaToAttach.getIdProgramacionMaquina());
                attachedProgramacionMaquinaList.add(programacionMaquinaListProgramacionMaquinaToAttach);
            }
            molde.setProgramacionMaquinaList(attachedProgramacionMaquinaList);
            em.persist(molde);
            if (idCliente != null) {
                idCliente.getMoldeList().add(molde);
                idCliente = em.merge(idCliente);
            }
            if (idEstadoMoldeMaquina != null) {
                idEstadoMoldeMaquina.getMoldeList().add(molde);
                idEstadoMoldeMaquina = em.merge(idEstadoMoldeMaquina);
            }
            if (idMaterialMolde != null) {
                idMaterialMolde.getMoldeList().add(molde);
                idMaterialMolde = em.merge(idMaterialMolde);
            }
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina : molde.getMantenimientoMoldeMaquinaList()) {
                Molde oldIdMoldeOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina = mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina.getIdMolde();
                mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina.setIdMolde(molde);
                mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina = em.merge(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina);
                if (oldIdMoldeOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina != null) {
                    oldIdMoldeOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquinaListMantenimientoMoldeMaquina);
                    oldIdMoldeOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina = em.merge(oldIdMoldeOfMantenimientoMoldeMaquinaListMantenimientoMoldeMaquina);
                }
            }
            for (OrdenProduccion ordenProduccionListOrdenProduccion : molde.getOrdenProduccionList()) {
                Molde oldIdMoldeOfOrdenProduccionListOrdenProduccion = ordenProduccionListOrdenProduccion.getIdMolde();
                ordenProduccionListOrdenProduccion.setIdMolde(molde);
                ordenProduccionListOrdenProduccion = em.merge(ordenProduccionListOrdenProduccion);
                if (oldIdMoldeOfOrdenProduccionListOrdenProduccion != null) {
                    oldIdMoldeOfOrdenProduccionListOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListOrdenProduccion);
                    oldIdMoldeOfOrdenProduccionListOrdenProduccion = em.merge(oldIdMoldeOfOrdenProduccionListOrdenProduccion);
                }
            }
            for (ProgramacionMaquina programacionMaquinaListProgramacionMaquina : molde.getProgramacionMaquinaList()) {
                Molde oldMoldeidMoldeOfProgramacionMaquinaListProgramacionMaquina = programacionMaquinaListProgramacionMaquina.getMoldeidMolde();
                programacionMaquinaListProgramacionMaquina.setMoldeidMolde(molde);
                programacionMaquinaListProgramacionMaquina = em.merge(programacionMaquinaListProgramacionMaquina);
                if (oldMoldeidMoldeOfProgramacionMaquinaListProgramacionMaquina != null) {
                    oldMoldeidMoldeOfProgramacionMaquinaListProgramacionMaquina.getProgramacionMaquinaList().remove(programacionMaquinaListProgramacionMaquina);
                    oldMoldeidMoldeOfProgramacionMaquinaListProgramacionMaquina = em.merge(oldMoldeidMoldeOfProgramacionMaquinaListProgramacionMaquina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Molde molde) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Molde persistentMolde = em.find(Molde.class, molde.getIdMolde());
            Cliente idClienteOld = persistentMolde.getIdCliente();
            Cliente idClienteNew = molde.getIdCliente();
            EstadoMoldeMaquina idEstadoMoldeMaquinaOld = persistentMolde.getIdEstadoMoldeMaquina();
            EstadoMoldeMaquina idEstadoMoldeMaquinaNew = molde.getIdEstadoMoldeMaquina();
            MaterialMolde idMaterialMoldeOld = persistentMolde.getIdMaterialMolde();
            MaterialMolde idMaterialMoldeNew = molde.getIdMaterialMolde();
            List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaListOld = persistentMolde.getMantenimientoMoldeMaquinaList();
            List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaListNew = molde.getMantenimientoMoldeMaquinaList();
            List<OrdenProduccion> ordenProduccionListOld = persistentMolde.getOrdenProduccionList();
            List<OrdenProduccion> ordenProduccionListNew = molde.getOrdenProduccionList();
            List<ProgramacionMaquina> programacionMaquinaListOld = persistentMolde.getProgramacionMaquinaList();
            List<ProgramacionMaquina> programacionMaquinaListNew = molde.getProgramacionMaquinaList();
            List<String> illegalOrphanMessages = null;
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListOldMantenimientoMoldeMaquina : mantenimientoMoldeMaquinaListOld) {
                if (!mantenimientoMoldeMaquinaListNew.contains(mantenimientoMoldeMaquinaListOldMantenimientoMoldeMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MantenimientoMoldeMaquina " + mantenimientoMoldeMaquinaListOldMantenimientoMoldeMaquina + " since its idMolde field is not nullable.");
                }
            }
            for (OrdenProduccion ordenProduccionListOldOrdenProduccion : ordenProduccionListOld) {
                if (!ordenProduccionListNew.contains(ordenProduccionListOldOrdenProduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenProduccion " + ordenProduccionListOldOrdenProduccion + " since its idMolde field is not nullable.");
                }
            }
            for (ProgramacionMaquina programacionMaquinaListOldProgramacionMaquina : programacionMaquinaListOld) {
                if (!programacionMaquinaListNew.contains(programacionMaquinaListOldProgramacionMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramacionMaquina " + programacionMaquinaListOldProgramacionMaquina + " since its moldeidMolde field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                molde.setIdCliente(idClienteNew);
            }
            if (idEstadoMoldeMaquinaNew != null) {
                idEstadoMoldeMaquinaNew = em.getReference(idEstadoMoldeMaquinaNew.getClass(), idEstadoMoldeMaquinaNew.getIdEstadoMoldeMaquina());
                molde.setIdEstadoMoldeMaquina(idEstadoMoldeMaquinaNew);
            }
            if (idMaterialMoldeNew != null) {
                idMaterialMoldeNew = em.getReference(idMaterialMoldeNew.getClass(), idMaterialMoldeNew.getIdMaterialMolde());
                molde.setIdMaterialMolde(idMaterialMoldeNew);
            }
            List<MantenimientoMoldeMaquina> attachedMantenimientoMoldeMaquinaListNew = new ArrayList<MantenimientoMoldeMaquina>();
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach : mantenimientoMoldeMaquinaListNew) {
                mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach = em.getReference(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach.getClass(), mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach.getIdMantenimientoMoldeMaquina());
                attachedMantenimientoMoldeMaquinaListNew.add(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquinaToAttach);
            }
            mantenimientoMoldeMaquinaListNew = attachedMantenimientoMoldeMaquinaListNew;
            molde.setMantenimientoMoldeMaquinaList(mantenimientoMoldeMaquinaListNew);
            List<OrdenProduccion> attachedOrdenProduccionListNew = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListNewOrdenProduccionToAttach : ordenProduccionListNew) {
                ordenProduccionListNewOrdenProduccionToAttach = em.getReference(ordenProduccionListNewOrdenProduccionToAttach.getClass(), ordenProduccionListNewOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionListNew.add(ordenProduccionListNewOrdenProduccionToAttach);
            }
            ordenProduccionListNew = attachedOrdenProduccionListNew;
            molde.setOrdenProduccionList(ordenProduccionListNew);
            List<ProgramacionMaquina> attachedProgramacionMaquinaListNew = new ArrayList<ProgramacionMaquina>();
            for (ProgramacionMaquina programacionMaquinaListNewProgramacionMaquinaToAttach : programacionMaquinaListNew) {
                programacionMaquinaListNewProgramacionMaquinaToAttach = em.getReference(programacionMaquinaListNewProgramacionMaquinaToAttach.getClass(), programacionMaquinaListNewProgramacionMaquinaToAttach.getIdProgramacionMaquina());
                attachedProgramacionMaquinaListNew.add(programacionMaquinaListNewProgramacionMaquinaToAttach);
            }
            programacionMaquinaListNew = attachedProgramacionMaquinaListNew;
            molde.setProgramacionMaquinaList(programacionMaquinaListNew);
            molde = em.merge(molde);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getMoldeList().remove(molde);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getMoldeList().add(molde);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idEstadoMoldeMaquinaOld != null && !idEstadoMoldeMaquinaOld.equals(idEstadoMoldeMaquinaNew)) {
                idEstadoMoldeMaquinaOld.getMoldeList().remove(molde);
                idEstadoMoldeMaquinaOld = em.merge(idEstadoMoldeMaquinaOld);
            }
            if (idEstadoMoldeMaquinaNew != null && !idEstadoMoldeMaquinaNew.equals(idEstadoMoldeMaquinaOld)) {
                idEstadoMoldeMaquinaNew.getMoldeList().add(molde);
                idEstadoMoldeMaquinaNew = em.merge(idEstadoMoldeMaquinaNew);
            }
            if (idMaterialMoldeOld != null && !idMaterialMoldeOld.equals(idMaterialMoldeNew)) {
                idMaterialMoldeOld.getMoldeList().remove(molde);
                idMaterialMoldeOld = em.merge(idMaterialMoldeOld);
            }
            if (idMaterialMoldeNew != null && !idMaterialMoldeNew.equals(idMaterialMoldeOld)) {
                idMaterialMoldeNew.getMoldeList().add(molde);
                idMaterialMoldeNew = em.merge(idMaterialMoldeNew);
            }
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina : mantenimientoMoldeMaquinaListNew) {
                if (!mantenimientoMoldeMaquinaListOld.contains(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina)) {
                    Molde oldIdMoldeOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina = mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.getIdMolde();
                    mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.setIdMolde(molde);
                    mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina = em.merge(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina);
                    if (oldIdMoldeOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina != null && !oldIdMoldeOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.equals(molde)) {
                        oldIdMoldeOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina.getMantenimientoMoldeMaquinaList().remove(mantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina);
                        oldIdMoldeOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina = em.merge(oldIdMoldeOfMantenimientoMoldeMaquinaListNewMantenimientoMoldeMaquina);
                    }
                }
            }
            for (OrdenProduccion ordenProduccionListNewOrdenProduccion : ordenProduccionListNew) {
                if (!ordenProduccionListOld.contains(ordenProduccionListNewOrdenProduccion)) {
                    Molde oldIdMoldeOfOrdenProduccionListNewOrdenProduccion = ordenProduccionListNewOrdenProduccion.getIdMolde();
                    ordenProduccionListNewOrdenProduccion.setIdMolde(molde);
                    ordenProduccionListNewOrdenProduccion = em.merge(ordenProduccionListNewOrdenProduccion);
                    if (oldIdMoldeOfOrdenProduccionListNewOrdenProduccion != null && !oldIdMoldeOfOrdenProduccionListNewOrdenProduccion.equals(molde)) {
                        oldIdMoldeOfOrdenProduccionListNewOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListNewOrdenProduccion);
                        oldIdMoldeOfOrdenProduccionListNewOrdenProduccion = em.merge(oldIdMoldeOfOrdenProduccionListNewOrdenProduccion);
                    }
                }
            }
            for (ProgramacionMaquina programacionMaquinaListNewProgramacionMaquina : programacionMaquinaListNew) {
                if (!programacionMaquinaListOld.contains(programacionMaquinaListNewProgramacionMaquina)) {
                    Molde oldMoldeidMoldeOfProgramacionMaquinaListNewProgramacionMaquina = programacionMaquinaListNewProgramacionMaquina.getMoldeidMolde();
                    programacionMaquinaListNewProgramacionMaquina.setMoldeidMolde(molde);
                    programacionMaquinaListNewProgramacionMaquina = em.merge(programacionMaquinaListNewProgramacionMaquina);
                    if (oldMoldeidMoldeOfProgramacionMaquinaListNewProgramacionMaquina != null && !oldMoldeidMoldeOfProgramacionMaquinaListNewProgramacionMaquina.equals(molde)) {
                        oldMoldeidMoldeOfProgramacionMaquinaListNewProgramacionMaquina.getProgramacionMaquinaList().remove(programacionMaquinaListNewProgramacionMaquina);
                        oldMoldeidMoldeOfProgramacionMaquinaListNewProgramacionMaquina = em.merge(oldMoldeidMoldeOfProgramacionMaquinaListNewProgramacionMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = molde.getIdMolde();
                if (findMolde(id) == null) {
                    throw new NonexistentEntityException("The molde with id " + id + " no longer exists.");
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
            Molde molde;
            try {
                molde = em.getReference(Molde.class, id);
                molde.getIdMolde();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The molde with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaListOrphanCheck = molde.getMantenimientoMoldeMaquinaList();
            for (MantenimientoMoldeMaquina mantenimientoMoldeMaquinaListOrphanCheckMantenimientoMoldeMaquina : mantenimientoMoldeMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Molde (" + molde + ") cannot be destroyed since the MantenimientoMoldeMaquina " + mantenimientoMoldeMaquinaListOrphanCheckMantenimientoMoldeMaquina + " in its mantenimientoMoldeMaquinaList field has a non-nullable idMolde field.");
            }
            List<OrdenProduccion> ordenProduccionListOrphanCheck = molde.getOrdenProduccionList();
            for (OrdenProduccion ordenProduccionListOrphanCheckOrdenProduccion : ordenProduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Molde (" + molde + ") cannot be destroyed since the OrdenProduccion " + ordenProduccionListOrphanCheckOrdenProduccion + " in its ordenProduccionList field has a non-nullable idMolde field.");
            }
            List<ProgramacionMaquina> programacionMaquinaListOrphanCheck = molde.getProgramacionMaquinaList();
            for (ProgramacionMaquina programacionMaquinaListOrphanCheckProgramacionMaquina : programacionMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Molde (" + molde + ") cannot be destroyed since the ProgramacionMaquina " + programacionMaquinaListOrphanCheckProgramacionMaquina + " in its programacionMaquinaList field has a non-nullable moldeidMolde field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idCliente = molde.getIdCliente();
            if (idCliente != null) {
                idCliente.getMoldeList().remove(molde);
                idCliente = em.merge(idCliente);
            }
            EstadoMoldeMaquina idEstadoMoldeMaquina = molde.getIdEstadoMoldeMaquina();
            if (idEstadoMoldeMaquina != null) {
                idEstadoMoldeMaquina.getMoldeList().remove(molde);
                idEstadoMoldeMaquina = em.merge(idEstadoMoldeMaquina);
            }
            MaterialMolde idMaterialMolde = molde.getIdMaterialMolde();
            if (idMaterialMolde != null) {
                idMaterialMolde.getMoldeList().remove(molde);
                idMaterialMolde = em.merge(idMaterialMolde);
            }
            em.remove(molde);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Molde> findMoldeEntities() {
        return findMoldeEntities(true, -1, -1);
    }

    public List<Molde> findMoldeEntities(int maxResults, int firstResult) {
        return findMoldeEntities(false, maxResults, firstResult);
    }

    private List<Molde> findMoldeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Molde.class));
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

    public Molde findMolde(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Molde.class, id);
        } finally {
            em.close();
        }
    }

    public int getMoldeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Molde> rt = cq.from(Molde.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
