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
import Persistencia.Entities.ColorProducto;
import Persistencia.Entities.ConfiguracionProducto;
import Persistencia.Entities.Molde;
import Persistencia.Entities.OrdenProduccion;
import Persistencia.Entities.TipoMaterialProducto;
import Persistencia.Entities.ProgramacionMaquina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class OrdenProduccionJpaController implements Serializable {

    public OrdenProduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenProduccion ordenProduccion) {
        if (ordenProduccion.getProgramacionMaquinaList() == null) {
            ordenProduccion.setProgramacionMaquinaList(new ArrayList<ProgramacionMaquina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente DCliente = ordenProduccion.getDCliente();
            if (DCliente != null) {
                DCliente = em.getReference(DCliente.getClass(), DCliente.getIdCliente());
                ordenProduccion.setDCliente(DCliente);
            }
            ColorProducto idColorProducto = ordenProduccion.getIdColorProducto();
            if (idColorProducto != null) {
                idColorProducto = em.getReference(idColorProducto.getClass(), idColorProducto.getIdColorProducto());
                ordenProduccion.setIdColorProducto(idColorProducto);
            }
            ConfiguracionProducto idConfiguracionProducto = ordenProduccion.getIdConfiguracionProducto();
            if (idConfiguracionProducto != null) {
                idConfiguracionProducto = em.getReference(idConfiguracionProducto.getClass(), idConfiguracionProducto.getIdConfiguracionProducto());
                ordenProduccion.setIdConfiguracionProducto(idConfiguracionProducto);
            }
            Molde idMolde = ordenProduccion.getIdMolde();
            if (idMolde != null) {
                idMolde = em.getReference(idMolde.getClass(), idMolde.getIdMolde());
                ordenProduccion.setIdMolde(idMolde);
            }
            TipoMaterialProducto idTipoMaterialProducto = ordenProduccion.getIdTipoMaterialProducto();
            if (idTipoMaterialProducto != null) {
                idTipoMaterialProducto = em.getReference(idTipoMaterialProducto.getClass(), idTipoMaterialProducto.getIdTipoMaterialProducto());
                ordenProduccion.setIdTipoMaterialProducto(idTipoMaterialProducto);
            }
            List<ProgramacionMaquina> attachedProgramacionMaquinaList = new ArrayList<ProgramacionMaquina>();
            for (ProgramacionMaquina programacionMaquinaListProgramacionMaquinaToAttach : ordenProduccion.getProgramacionMaquinaList()) {
                programacionMaquinaListProgramacionMaquinaToAttach = em.getReference(programacionMaquinaListProgramacionMaquinaToAttach.getClass(), programacionMaquinaListProgramacionMaquinaToAttach.getIdProgramacionMaquina());
                attachedProgramacionMaquinaList.add(programacionMaquinaListProgramacionMaquinaToAttach);
            }
            ordenProduccion.setProgramacionMaquinaList(attachedProgramacionMaquinaList);
            em.persist(ordenProduccion);
            if (DCliente != null) {
                DCliente.getOrdenProduccionList().add(ordenProduccion);
                DCliente = em.merge(DCliente);
            }
            if (idColorProducto != null) {
                idColorProducto.getOrdenProduccionList().add(ordenProduccion);
                idColorProducto = em.merge(idColorProducto);
            }
            if (idConfiguracionProducto != null) {
                idConfiguracionProducto.getOrdenProduccionList().add(ordenProduccion);
                idConfiguracionProducto = em.merge(idConfiguracionProducto);
            }
            if (idMolde != null) {
                idMolde.getOrdenProduccionList().add(ordenProduccion);
                idMolde = em.merge(idMolde);
            }
            if (idTipoMaterialProducto != null) {
                idTipoMaterialProducto.getOrdenProduccionList().add(ordenProduccion);
                idTipoMaterialProducto = em.merge(idTipoMaterialProducto);
            }
            for (ProgramacionMaquina programacionMaquinaListProgramacionMaquina : ordenProduccion.getProgramacionMaquinaList()) {
                OrdenProduccion oldIdOrdenProduccionOfProgramacionMaquinaListProgramacionMaquina = programacionMaquinaListProgramacionMaquina.getIdOrdenProduccion();
                programacionMaquinaListProgramacionMaquina.setIdOrdenProduccion(ordenProduccion);
                programacionMaquinaListProgramacionMaquina = em.merge(programacionMaquinaListProgramacionMaquina);
                if (oldIdOrdenProduccionOfProgramacionMaquinaListProgramacionMaquina != null) {
                    oldIdOrdenProduccionOfProgramacionMaquinaListProgramacionMaquina.getProgramacionMaquinaList().remove(programacionMaquinaListProgramacionMaquina);
                    oldIdOrdenProduccionOfProgramacionMaquinaListProgramacionMaquina = em.merge(oldIdOrdenProduccionOfProgramacionMaquinaListProgramacionMaquina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenProduccion ordenProduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenProduccion persistentOrdenProduccion = em.find(OrdenProduccion.class, ordenProduccion.getIdOrdenProduccion());
            Cliente DClienteOld = persistentOrdenProduccion.getDCliente();
            Cliente DClienteNew = ordenProduccion.getDCliente();
            ColorProducto idColorProductoOld = persistentOrdenProduccion.getIdColorProducto();
            ColorProducto idColorProductoNew = ordenProduccion.getIdColorProducto();
            ConfiguracionProducto idConfiguracionProductoOld = persistentOrdenProduccion.getIdConfiguracionProducto();
            ConfiguracionProducto idConfiguracionProductoNew = ordenProduccion.getIdConfiguracionProducto();
            Molde idMoldeOld = persistentOrdenProduccion.getIdMolde();
            Molde idMoldeNew = ordenProduccion.getIdMolde();
            TipoMaterialProducto idTipoMaterialProductoOld = persistentOrdenProduccion.getIdTipoMaterialProducto();
            TipoMaterialProducto idTipoMaterialProductoNew = ordenProduccion.getIdTipoMaterialProducto();
            List<ProgramacionMaquina> programacionMaquinaListOld = persistentOrdenProduccion.getProgramacionMaquinaList();
            List<ProgramacionMaquina> programacionMaquinaListNew = ordenProduccion.getProgramacionMaquinaList();
            List<String> illegalOrphanMessages = null;
            for (ProgramacionMaquina programacionMaquinaListOldProgramacionMaquina : programacionMaquinaListOld) {
                if (!programacionMaquinaListNew.contains(programacionMaquinaListOldProgramacionMaquina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramacionMaquina " + programacionMaquinaListOldProgramacionMaquina + " since its idOrdenProduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (DClienteNew != null) {
                DClienteNew = em.getReference(DClienteNew.getClass(), DClienteNew.getIdCliente());
                ordenProduccion.setDCliente(DClienteNew);
            }
            if (idColorProductoNew != null) {
                idColorProductoNew = em.getReference(idColorProductoNew.getClass(), idColorProductoNew.getIdColorProducto());
                ordenProduccion.setIdColorProducto(idColorProductoNew);
            }
            if (idConfiguracionProductoNew != null) {
                idConfiguracionProductoNew = em.getReference(idConfiguracionProductoNew.getClass(), idConfiguracionProductoNew.getIdConfiguracionProducto());
                ordenProduccion.setIdConfiguracionProducto(idConfiguracionProductoNew);
            }
            if (idMoldeNew != null) {
                idMoldeNew = em.getReference(idMoldeNew.getClass(), idMoldeNew.getIdMolde());
                ordenProduccion.setIdMolde(idMoldeNew);
            }
            if (idTipoMaterialProductoNew != null) {
                idTipoMaterialProductoNew = em.getReference(idTipoMaterialProductoNew.getClass(), idTipoMaterialProductoNew.getIdTipoMaterialProducto());
                ordenProduccion.setIdTipoMaterialProducto(idTipoMaterialProductoNew);
            }
            List<ProgramacionMaquina> attachedProgramacionMaquinaListNew = new ArrayList<ProgramacionMaquina>();
            for (ProgramacionMaquina programacionMaquinaListNewProgramacionMaquinaToAttach : programacionMaquinaListNew) {
                programacionMaquinaListNewProgramacionMaquinaToAttach = em.getReference(programacionMaquinaListNewProgramacionMaquinaToAttach.getClass(), programacionMaquinaListNewProgramacionMaquinaToAttach.getIdProgramacionMaquina());
                attachedProgramacionMaquinaListNew.add(programacionMaquinaListNewProgramacionMaquinaToAttach);
            }
            programacionMaquinaListNew = attachedProgramacionMaquinaListNew;
            ordenProduccion.setProgramacionMaquinaList(programacionMaquinaListNew);
            ordenProduccion = em.merge(ordenProduccion);
            if (DClienteOld != null && !DClienteOld.equals(DClienteNew)) {
                DClienteOld.getOrdenProduccionList().remove(ordenProduccion);
                DClienteOld = em.merge(DClienteOld);
            }
            if (DClienteNew != null && !DClienteNew.equals(DClienteOld)) {
                DClienteNew.getOrdenProduccionList().add(ordenProduccion);
                DClienteNew = em.merge(DClienteNew);
            }
            if (idColorProductoOld != null && !idColorProductoOld.equals(idColorProductoNew)) {
                idColorProductoOld.getOrdenProduccionList().remove(ordenProduccion);
                idColorProductoOld = em.merge(idColorProductoOld);
            }
            if (idColorProductoNew != null && !idColorProductoNew.equals(idColorProductoOld)) {
                idColorProductoNew.getOrdenProduccionList().add(ordenProduccion);
                idColorProductoNew = em.merge(idColorProductoNew);
            }
            if (idConfiguracionProductoOld != null && !idConfiguracionProductoOld.equals(idConfiguracionProductoNew)) {
                idConfiguracionProductoOld.getOrdenProduccionList().remove(ordenProduccion);
                idConfiguracionProductoOld = em.merge(idConfiguracionProductoOld);
            }
            if (idConfiguracionProductoNew != null && !idConfiguracionProductoNew.equals(idConfiguracionProductoOld)) {
                idConfiguracionProductoNew.getOrdenProduccionList().add(ordenProduccion);
                idConfiguracionProductoNew = em.merge(idConfiguracionProductoNew);
            }
            if (idMoldeOld != null && !idMoldeOld.equals(idMoldeNew)) {
                idMoldeOld.getOrdenProduccionList().remove(ordenProduccion);
                idMoldeOld = em.merge(idMoldeOld);
            }
            if (idMoldeNew != null && !idMoldeNew.equals(idMoldeOld)) {
                idMoldeNew.getOrdenProduccionList().add(ordenProduccion);
                idMoldeNew = em.merge(idMoldeNew);
            }
            if (idTipoMaterialProductoOld != null && !idTipoMaterialProductoOld.equals(idTipoMaterialProductoNew)) {
                idTipoMaterialProductoOld.getOrdenProduccionList().remove(ordenProduccion);
                idTipoMaterialProductoOld = em.merge(idTipoMaterialProductoOld);
            }
            if (idTipoMaterialProductoNew != null && !idTipoMaterialProductoNew.equals(idTipoMaterialProductoOld)) {
                idTipoMaterialProductoNew.getOrdenProduccionList().add(ordenProduccion);
                idTipoMaterialProductoNew = em.merge(idTipoMaterialProductoNew);
            }
            for (ProgramacionMaquina programacionMaquinaListNewProgramacionMaquina : programacionMaquinaListNew) {
                if (!programacionMaquinaListOld.contains(programacionMaquinaListNewProgramacionMaquina)) {
                    OrdenProduccion oldIdOrdenProduccionOfProgramacionMaquinaListNewProgramacionMaquina = programacionMaquinaListNewProgramacionMaquina.getIdOrdenProduccion();
                    programacionMaquinaListNewProgramacionMaquina.setIdOrdenProduccion(ordenProduccion);
                    programacionMaquinaListNewProgramacionMaquina = em.merge(programacionMaquinaListNewProgramacionMaquina);
                    if (oldIdOrdenProduccionOfProgramacionMaquinaListNewProgramacionMaquina != null && !oldIdOrdenProduccionOfProgramacionMaquinaListNewProgramacionMaquina.equals(ordenProduccion)) {
                        oldIdOrdenProduccionOfProgramacionMaquinaListNewProgramacionMaquina.getProgramacionMaquinaList().remove(programacionMaquinaListNewProgramacionMaquina);
                        oldIdOrdenProduccionOfProgramacionMaquinaListNewProgramacionMaquina = em.merge(oldIdOrdenProduccionOfProgramacionMaquinaListNewProgramacionMaquina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordenProduccion.getIdOrdenProduccion();
                if (findOrdenProduccion(id) == null) {
                    throw new NonexistentEntityException("The ordenProduccion with id " + id + " no longer exists.");
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
            OrdenProduccion ordenProduccion;
            try {
                ordenProduccion = em.getReference(OrdenProduccion.class, id);
                ordenProduccion.getIdOrdenProduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenProduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProgramacionMaquina> programacionMaquinaListOrphanCheck = ordenProduccion.getProgramacionMaquinaList();
            for (ProgramacionMaquina programacionMaquinaListOrphanCheckProgramacionMaquina : programacionMaquinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrdenProduccion (" + ordenProduccion + ") cannot be destroyed since the ProgramacionMaquina " + programacionMaquinaListOrphanCheckProgramacionMaquina + " in its programacionMaquinaList field has a non-nullable idOrdenProduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente DCliente = ordenProduccion.getDCliente();
            if (DCliente != null) {
                DCliente.getOrdenProduccionList().remove(ordenProduccion);
                DCliente = em.merge(DCliente);
            }
            ColorProducto idColorProducto = ordenProduccion.getIdColorProducto();
            if (idColorProducto != null) {
                idColorProducto.getOrdenProduccionList().remove(ordenProduccion);
                idColorProducto = em.merge(idColorProducto);
            }
            ConfiguracionProducto idConfiguracionProducto = ordenProduccion.getIdConfiguracionProducto();
            if (idConfiguracionProducto != null) {
                idConfiguracionProducto.getOrdenProduccionList().remove(ordenProduccion);
                idConfiguracionProducto = em.merge(idConfiguracionProducto);
            }
            Molde idMolde = ordenProduccion.getIdMolde();
            if (idMolde != null) {
                idMolde.getOrdenProduccionList().remove(ordenProduccion);
                idMolde = em.merge(idMolde);
            }
            TipoMaterialProducto idTipoMaterialProducto = ordenProduccion.getIdTipoMaterialProducto();
            if (idTipoMaterialProducto != null) {
                idTipoMaterialProducto.getOrdenProduccionList().remove(ordenProduccion);
                idTipoMaterialProducto = em.merge(idTipoMaterialProducto);
            }
            em.remove(ordenProduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenProduccion> findOrdenProduccionEntities() {
        return findOrdenProduccionEntities(true, -1, -1);
    }

    public List<OrdenProduccion> findOrdenProduccionEntities(int maxResults, int firstResult) {
        return findOrdenProduccionEntities(false, maxResults, firstResult);
    }

    private List<OrdenProduccion> findOrdenProduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenProduccion.class));
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

    public OrdenProduccion findOrdenProduccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenProduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenProduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenProduccion> rt = cq.from(OrdenProduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
