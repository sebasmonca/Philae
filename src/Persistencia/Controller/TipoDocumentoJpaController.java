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
import Persistencia.Entities.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import Persistencia.Entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getClienteList() == null) {
            tipoDocumento.setClienteList(new ArrayList<Cliente>());
        }
        if (tipoDocumento.getUsuarioList() == null) {
            tipoDocumento.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : tipoDocumento.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdCliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            tipoDocumento.setClienteList(attachedClienteList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : tipoDocumento.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            tipoDocumento.setUsuarioList(attachedUsuarioList);
            em.persist(tipoDocumento);
            for (Cliente clienteListCliente : tipoDocumento.getClienteList()) {
                TipoDocumento oldIdTipodocumentoOfClienteListCliente = clienteListCliente.getIdTipodocumento();
                clienteListCliente.setIdTipodocumento(tipoDocumento);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldIdTipodocumentoOfClienteListCliente != null) {
                    oldIdTipodocumentoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldIdTipodocumentoOfClienteListCliente = em.merge(oldIdTipodocumentoOfClienteListCliente);
                }
            }
            for (Usuario usuarioListUsuario : tipoDocumento.getUsuarioList()) {
                TipoDocumento oldIdTipodocumentoOfUsuarioListUsuario = usuarioListUsuario.getIdTipodocumento();
                usuarioListUsuario.setIdTipodocumento(tipoDocumento);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdTipodocumentoOfUsuarioListUsuario != null) {
                    oldIdTipodocumentoOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdTipodocumentoOfUsuarioListUsuario = em.merge(oldIdTipodocumentoOfUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getIdTipodocumento());
            List<Cliente> clienteListOld = persistentTipoDocumento.getClienteList();
            List<Cliente> clienteListNew = tipoDocumento.getClienteList();
            List<Usuario> usuarioListOld = persistentTipoDocumento.getUsuarioList();
            List<Usuario> usuarioListNew = tipoDocumento.getUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cliente " + clienteListOldCliente + " since its idTipodocumento field is not nullable.");
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its idTipodocumento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdCliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            tipoDocumento.setClienteList(clienteListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            tipoDocumento.setUsuarioList(usuarioListNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    TipoDocumento oldIdTipodocumentoOfClienteListNewCliente = clienteListNewCliente.getIdTipodocumento();
                    clienteListNewCliente.setIdTipodocumento(tipoDocumento);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldIdTipodocumentoOfClienteListNewCliente != null && !oldIdTipodocumentoOfClienteListNewCliente.equals(tipoDocumento)) {
                        oldIdTipodocumentoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldIdTipodocumentoOfClienteListNewCliente = em.merge(oldIdTipodocumentoOfClienteListNewCliente);
                    }
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    TipoDocumento oldIdTipodocumentoOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdTipodocumento();
                    usuarioListNewUsuario.setIdTipodocumento(tipoDocumento);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdTipodocumentoOfUsuarioListNewUsuario != null && !oldIdTipodocumentoOfUsuarioListNewUsuario.equals(tipoDocumento)) {
                        oldIdTipodocumentoOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdTipodocumentoOfUsuarioListNewUsuario = em.merge(oldIdTipodocumentoOfUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getIdTipodocumento();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getIdTipodocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cliente> clienteListOrphanCheck = tipoDocumento.getClienteList();
            for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoDocumento (" + tipoDocumento + ") cannot be destroyed since the Cliente " + clienteListOrphanCheckCliente + " in its clienteList field has a non-nullable idTipodocumento field.");
            }
            List<Usuario> usuarioListOrphanCheck = tipoDocumento.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoDocumento (" + tipoDocumento + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable idTipodocumento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
