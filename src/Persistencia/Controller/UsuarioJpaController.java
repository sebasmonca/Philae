/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Controller;

import Persistencia.Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persistencia.Entities.Estado;
import Persistencia.Entities.Perfil;
import Persistencia.Entities.TipoDocumento;
import Persistencia.Entities.Usuario;
import Persistencia.Entities.Usuario_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado idEstado = usuario.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                usuario.setIdEstado(idEstado);
            }
            Perfil idperfil = usuario.getIdperfil();
            if (idperfil != null) {
                idperfil = em.getReference(idperfil.getClass(), idperfil.getIdperfil());
                usuario.setIdperfil(idperfil);
            }
            TipoDocumento idTipodocumento = usuario.getIdTipodocumento();
            if (idTipodocumento != null) {
                idTipodocumento = em.getReference(idTipodocumento.getClass(), idTipodocumento.getIdTipodocumento());
                usuario.setIdTipodocumento(idTipodocumento);
            }
            em.persist(usuario);
            if (idEstado != null) {
                idEstado.getUsuarioList().add(usuario);
                idEstado = em.merge(idEstado);
            }
            if (idperfil != null) {
                idperfil.getUsuarioList().add(usuario);
                idperfil = em.merge(idperfil);
            }
            if (idTipodocumento != null) {
                idTipodocumento.getUsuarioList().add(usuario);
                idTipodocumento = em.merge(idTipodocumento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Estado idEstadoOld = persistentUsuario.getIdEstado();
            Estado idEstadoNew = usuario.getIdEstado();
            Perfil idperfilOld = persistentUsuario.getIdperfil();
            Perfil idperfilNew = usuario.getIdperfil();
            TipoDocumento idTipodocumentoOld = persistentUsuario.getIdTipodocumento();
            TipoDocumento idTipodocumentoNew = usuario.getIdTipodocumento();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                usuario.setIdEstado(idEstadoNew);
            }
            if (idperfilNew != null) {
                idperfilNew = em.getReference(idperfilNew.getClass(), idperfilNew.getIdperfil());
                usuario.setIdperfil(idperfilNew);
            }
            if (idTipodocumentoNew != null) {
                idTipodocumentoNew = em.getReference(idTipodocumentoNew.getClass(), idTipodocumentoNew.getIdTipodocumento());
                usuario.setIdTipodocumento(idTipodocumentoNew);
            }
            usuario = em.merge(usuario);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getUsuarioList().remove(usuario);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getUsuarioList().add(usuario);
                idEstadoNew = em.merge(idEstadoNew);
            }
            if (idperfilOld != null && !idperfilOld.equals(idperfilNew)) {
                idperfilOld.getUsuarioList().remove(usuario);
                idperfilOld = em.merge(idperfilOld);
            }
            if (idperfilNew != null && !idperfilNew.equals(idperfilOld)) {
                idperfilNew.getUsuarioList().add(usuario);
                idperfilNew = em.merge(idperfilNew);
            }
            if (idTipodocumentoOld != null && !idTipodocumentoOld.equals(idTipodocumentoNew)) {
                idTipodocumentoOld.getUsuarioList().remove(usuario);
                idTipodocumentoOld = em.merge(idTipodocumentoOld);
            }
            if (idTipodocumentoNew != null && !idTipodocumentoNew.equals(idTipodocumentoOld)) {
                idTipodocumentoNew.getUsuarioList().add(usuario);
                idTipodocumentoNew = em.merge(idTipodocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Estado idEstado = usuario.getIdEstado();
            if (idEstado != null) {
                idEstado.getUsuarioList().remove(usuario);
                idEstado = em.merge(idEstado);
            }
            Perfil idperfil = usuario.getIdperfil();
            if (idperfil != null) {
                idperfil.getUsuarioList().remove(usuario);
                idperfil = em.merge(idperfil);
            }
            TipoDocumento idTipodocumento = usuario.getIdTipodocumento();
            if (idTipodocumento != null) {
                idTipodocumento.getUsuarioList().remove(usuario);
                idTipodocumento = em.merge(idTipodocumento);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public Usuario login(String name,String pass) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Usuario> cq = em.getCriteriaBuilder().createQuery(Usuario.class);
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.where(em.getCriteriaBuilder().equal(rt.get(Usuario_.nombreusuario), name),
                    em.getCriteriaBuilder().equal(rt.get(Usuario_.clave), pass));
            return (em.createQuery(cq)).getSingleResult();
        } finally {
            em.close();
        }
    }
     
     public List<Usuario> buscar(String id, String nombre, String numeroDocumento) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Usuario> cq = em.getCriteriaBuilder().createQuery(Usuario.class);
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.where(em.getCriteriaBuilder().equal(rt.get(Usuario_.idUsuario), Integer.parseInt(id)),
                    em.getCriteriaBuilder().equal(rt.get(Usuario_.nombres), nombre),
                    em.getCriteriaBuilder().equal(rt.get(Usuario_.numerodocumento), numeroDocumento));
            return (em.createQuery(cq)).getResultList();
        } finally {
            em.close();
        }
    }

    
}
