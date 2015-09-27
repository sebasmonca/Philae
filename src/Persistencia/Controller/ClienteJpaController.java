/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.IllegalOrphanException;
import Persistencia.Controller.exceptions.NonexistentEntityException;
import Persistencia.Entities.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.TipoDocumento;
import Persistencia.Entities.OrdenProduccion;
import java.util.ArrayList;
import java.util.List;
import Persistencia.Entities.Molde;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getOrdenProduccionList() == null) {
            cliente.setOrdenProduccionList(new ArrayList<OrdenProduccion>());
        }
        if (cliente.getMoldeList() == null) {
            cliente.setMoldeList(new ArrayList<Molde>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento idTipodocumento = cliente.getIdTipodocumento();
            if (idTipodocumento != null) {
                idTipodocumento = em.getReference(idTipodocumento.getClass(), idTipodocumento.getIdTipodocumento());
                cliente.setIdTipodocumento(idTipodocumento);
            }
            List<OrdenProduccion> attachedOrdenProduccionList = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListOrdenProduccionToAttach : cliente.getOrdenProduccionList()) {
                ordenProduccionListOrdenProduccionToAttach = em.getReference(ordenProduccionListOrdenProduccionToAttach.getClass(), ordenProduccionListOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionList.add(ordenProduccionListOrdenProduccionToAttach);
            }
            cliente.setOrdenProduccionList(attachedOrdenProduccionList);
            List<Molde> attachedMoldeList = new ArrayList<Molde>();
            for (Molde moldeListMoldeToAttach : cliente.getMoldeList()) {
                moldeListMoldeToAttach = em.getReference(moldeListMoldeToAttach.getClass(), moldeListMoldeToAttach.getIdMolde());
                attachedMoldeList.add(moldeListMoldeToAttach);
            }
            cliente.setMoldeList(attachedMoldeList);
            em.persist(cliente);
            if (idTipodocumento != null) {
                idTipodocumento.getClienteList().add(cliente);
                idTipodocumento = em.merge(idTipodocumento);
            }
            for (OrdenProduccion ordenProduccionListOrdenProduccion : cliente.getOrdenProduccionList()) {
                Cliente oldDClienteOfOrdenProduccionListOrdenProduccion = ordenProduccionListOrdenProduccion.getDCliente();
                ordenProduccionListOrdenProduccion.setDCliente(cliente);
                ordenProduccionListOrdenProduccion = em.merge(ordenProduccionListOrdenProduccion);
                if (oldDClienteOfOrdenProduccionListOrdenProduccion != null) {
                    oldDClienteOfOrdenProduccionListOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListOrdenProduccion);
                    oldDClienteOfOrdenProduccionListOrdenProduccion = em.merge(oldDClienteOfOrdenProduccionListOrdenProduccion);
                }
            }
            for (Molde moldeListMolde : cliente.getMoldeList()) {
                Cliente oldIdClienteOfMoldeListMolde = moldeListMolde.getIdCliente();
                moldeListMolde.setIdCliente(cliente);
                moldeListMolde = em.merge(moldeListMolde);
                if (oldIdClienteOfMoldeListMolde != null) {
                    oldIdClienteOfMoldeListMolde.getMoldeList().remove(moldeListMolde);
                    oldIdClienteOfMoldeListMolde = em.merge(oldIdClienteOfMoldeListMolde);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            TipoDocumento idTipodocumentoOld = persistentCliente.getIdTipodocumento();
            TipoDocumento idTipodocumentoNew = cliente.getIdTipodocumento();
            List<OrdenProduccion> ordenProduccionListOld = persistentCliente.getOrdenProduccionList();
            List<OrdenProduccion> ordenProduccionListNew = cliente.getOrdenProduccionList();
            List<Molde> moldeListOld = persistentCliente.getMoldeList();
            List<Molde> moldeListNew = cliente.getMoldeList();
            List<String> illegalOrphanMessages = null;
            for (OrdenProduccion ordenProduccionListOldOrdenProduccion : ordenProduccionListOld) {
                if (!ordenProduccionListNew.contains(ordenProduccionListOldOrdenProduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdenProduccion " + ordenProduccionListOldOrdenProduccion + " since its DCliente field is not nullable.");
                }
            }
            for (Molde moldeListOldMolde : moldeListOld) {
                if (!moldeListNew.contains(moldeListOldMolde)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Molde " + moldeListOldMolde + " since its idCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipodocumentoNew != null) {
                idTipodocumentoNew = em.getReference(idTipodocumentoNew.getClass(), idTipodocumentoNew.getIdTipodocumento());
                cliente.setIdTipodocumento(idTipodocumentoNew);
            }
            List<OrdenProduccion> attachedOrdenProduccionListNew = new ArrayList<OrdenProduccion>();
            for (OrdenProduccion ordenProduccionListNewOrdenProduccionToAttach : ordenProduccionListNew) {
                ordenProduccionListNewOrdenProduccionToAttach = em.getReference(ordenProduccionListNewOrdenProduccionToAttach.getClass(), ordenProduccionListNewOrdenProduccionToAttach.getIdOrdenProduccion());
                attachedOrdenProduccionListNew.add(ordenProduccionListNewOrdenProduccionToAttach);
            }
            ordenProduccionListNew = attachedOrdenProduccionListNew;
            cliente.setOrdenProduccionList(ordenProduccionListNew);
            List<Molde> attachedMoldeListNew = new ArrayList<Molde>();
            for (Molde moldeListNewMoldeToAttach : moldeListNew) {
                moldeListNewMoldeToAttach = em.getReference(moldeListNewMoldeToAttach.getClass(), moldeListNewMoldeToAttach.getIdMolde());
                attachedMoldeListNew.add(moldeListNewMoldeToAttach);
            }
            moldeListNew = attachedMoldeListNew;
            cliente.setMoldeList(moldeListNew);
            cliente = em.merge(cliente);
            if (idTipodocumentoOld != null && !idTipodocumentoOld.equals(idTipodocumentoNew)) {
                idTipodocumentoOld.getClienteList().remove(cliente);
                idTipodocumentoOld = em.merge(idTipodocumentoOld);
            }
            if (idTipodocumentoNew != null && !idTipodocumentoNew.equals(idTipodocumentoOld)) {
                idTipodocumentoNew.getClienteList().add(cliente);
                idTipodocumentoNew = em.merge(idTipodocumentoNew);
            }
            for (OrdenProduccion ordenProduccionListNewOrdenProduccion : ordenProduccionListNew) {
                if (!ordenProduccionListOld.contains(ordenProduccionListNewOrdenProduccion)) {
                    Cliente oldDClienteOfOrdenProduccionListNewOrdenProduccion = ordenProduccionListNewOrdenProduccion.getDCliente();
                    ordenProduccionListNewOrdenProduccion.setDCliente(cliente);
                    ordenProduccionListNewOrdenProduccion = em.merge(ordenProduccionListNewOrdenProduccion);
                    if (oldDClienteOfOrdenProduccionListNewOrdenProduccion != null && !oldDClienteOfOrdenProduccionListNewOrdenProduccion.equals(cliente)) {
                        oldDClienteOfOrdenProduccionListNewOrdenProduccion.getOrdenProduccionList().remove(ordenProduccionListNewOrdenProduccion);
                        oldDClienteOfOrdenProduccionListNewOrdenProduccion = em.merge(oldDClienteOfOrdenProduccionListNewOrdenProduccion);
                    }
                }
            }
            for (Molde moldeListNewMolde : moldeListNew) {
                if (!moldeListOld.contains(moldeListNewMolde)) {
                    Cliente oldIdClienteOfMoldeListNewMolde = moldeListNewMolde.getIdCliente();
                    moldeListNewMolde.setIdCliente(cliente);
                    moldeListNewMolde = em.merge(moldeListNewMolde);
                    if (oldIdClienteOfMoldeListNewMolde != null && !oldIdClienteOfMoldeListNewMolde.equals(cliente)) {
                        oldIdClienteOfMoldeListNewMolde.getMoldeList().remove(moldeListNewMolde);
                        oldIdClienteOfMoldeListNewMolde = em.merge(oldIdClienteOfMoldeListNewMolde);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdenProduccion> ordenProduccionListOrphanCheck = cliente.getOrdenProduccionList();
            for (OrdenProduccion ordenProduccionListOrphanCheckOrdenProduccion : ordenProduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the OrdenProduccion " + ordenProduccionListOrphanCheckOrdenProduccion + " in its ordenProduccionList field has a non-nullable DCliente field.");
            }
            List<Molde> moldeListOrphanCheck = cliente.getMoldeList();
            for (Molde moldeListOrphanCheckMolde : moldeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Molde " + moldeListOrphanCheckMolde + " in its moldeList field has a non-nullable idCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoDocumento idTipodocumento = cliente.getIdTipodocumento();
            if (idTipodocumento != null) {
                idTipodocumento.getClienteList().remove(cliente);
                idTipodocumento = em.merge(idTipodocumento);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
