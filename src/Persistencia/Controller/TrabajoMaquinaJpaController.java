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
import Persistencia.Entities.ProgramacionMaquina;
import Persistencia.Entities.TiempoInproductivo;
import Persistencia.Entities.TiempoProductivo;
import Persistencia.Entities.TrabajoMaquina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sebas
 */
public class TrabajoMaquinaJpaController implements Serializable {

    public TrabajoMaquinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TrabajoMaquina trabajoMaquina) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionMaquina programacionMaquinaidProgramacionMaquina = trabajoMaquina.getProgramacionMaquinaidProgramacionMaquina();
            if (programacionMaquinaidProgramacionMaquina != null) {
                programacionMaquinaidProgramacionMaquina = em.getReference(programacionMaquinaidProgramacionMaquina.getClass(), programacionMaquinaidProgramacionMaquina.getIdProgramacionMaquina());
                trabajoMaquina.setProgramacionMaquinaidProgramacionMaquina(programacionMaquinaidProgramacionMaquina);
            }
            TiempoInproductivo tiempoInproductivoidTiempoInproductivo = trabajoMaquina.getTiempoInproductivoidTiempoInproductivo();
            if (tiempoInproductivoidTiempoInproductivo != null) {
                tiempoInproductivoidTiempoInproductivo = em.getReference(tiempoInproductivoidTiempoInproductivo.getClass(), tiempoInproductivoidTiempoInproductivo.getIdTiempoInproductivo());
                trabajoMaquina.setTiempoInproductivoidTiempoInproductivo(tiempoInproductivoidTiempoInproductivo);
            }
            TiempoProductivo tiempoProductivoidTiempoProductivo = trabajoMaquina.getTiempoProductivoidTiempoProductivo();
            if (tiempoProductivoidTiempoProductivo != null) {
                tiempoProductivoidTiempoProductivo = em.getReference(tiempoProductivoidTiempoProductivo.getClass(), tiempoProductivoidTiempoProductivo.getIdTiempoProductivo());
                trabajoMaquina.setTiempoProductivoidTiempoProductivo(tiempoProductivoidTiempoProductivo);
            }
            em.persist(trabajoMaquina);
            if (programacionMaquinaidProgramacionMaquina != null) {
                programacionMaquinaidProgramacionMaquina.getTrabajoMaquinaList().add(trabajoMaquina);
                programacionMaquinaidProgramacionMaquina = em.merge(programacionMaquinaidProgramacionMaquina);
            }
            if (tiempoInproductivoidTiempoInproductivo != null) {
                tiempoInproductivoidTiempoInproductivo.getTrabajoMaquinaList().add(trabajoMaquina);
                tiempoInproductivoidTiempoInproductivo = em.merge(tiempoInproductivoidTiempoInproductivo);
            }
            if (tiempoProductivoidTiempoProductivo != null) {
                tiempoProductivoidTiempoProductivo.getTrabajoMaquinaList().add(trabajoMaquina);
                tiempoProductivoidTiempoProductivo = em.merge(tiempoProductivoidTiempoProductivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TrabajoMaquina trabajoMaquina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TrabajoMaquina persistentTrabajoMaquina = em.find(TrabajoMaquina.class, trabajoMaquina.getIdTrabajoMaquina());
            ProgramacionMaquina programacionMaquinaidProgramacionMaquinaOld = persistentTrabajoMaquina.getProgramacionMaquinaidProgramacionMaquina();
            ProgramacionMaquina programacionMaquinaidProgramacionMaquinaNew = trabajoMaquina.getProgramacionMaquinaidProgramacionMaquina();
            TiempoInproductivo tiempoInproductivoidTiempoInproductivoOld = persistentTrabajoMaquina.getTiempoInproductivoidTiempoInproductivo();
            TiempoInproductivo tiempoInproductivoidTiempoInproductivoNew = trabajoMaquina.getTiempoInproductivoidTiempoInproductivo();
            TiempoProductivo tiempoProductivoidTiempoProductivoOld = persistentTrabajoMaquina.getTiempoProductivoidTiempoProductivo();
            TiempoProductivo tiempoProductivoidTiempoProductivoNew = trabajoMaquina.getTiempoProductivoidTiempoProductivo();
            if (programacionMaquinaidProgramacionMaquinaNew != null) {
                programacionMaquinaidProgramacionMaquinaNew = em.getReference(programacionMaquinaidProgramacionMaquinaNew.getClass(), programacionMaquinaidProgramacionMaquinaNew.getIdProgramacionMaquina());
                trabajoMaquina.setProgramacionMaquinaidProgramacionMaquina(programacionMaquinaidProgramacionMaquinaNew);
            }
            if (tiempoInproductivoidTiempoInproductivoNew != null) {
                tiempoInproductivoidTiempoInproductivoNew = em.getReference(tiempoInproductivoidTiempoInproductivoNew.getClass(), tiempoInproductivoidTiempoInproductivoNew.getIdTiempoInproductivo());
                trabajoMaquina.setTiempoInproductivoidTiempoInproductivo(tiempoInproductivoidTiempoInproductivoNew);
            }
            if (tiempoProductivoidTiempoProductivoNew != null) {
                tiempoProductivoidTiempoProductivoNew = em.getReference(tiempoProductivoidTiempoProductivoNew.getClass(), tiempoProductivoidTiempoProductivoNew.getIdTiempoProductivo());
                trabajoMaquina.setTiempoProductivoidTiempoProductivo(tiempoProductivoidTiempoProductivoNew);
            }
            trabajoMaquina = em.merge(trabajoMaquina);
            if (programacionMaquinaidProgramacionMaquinaOld != null && !programacionMaquinaidProgramacionMaquinaOld.equals(programacionMaquinaidProgramacionMaquinaNew)) {
                programacionMaquinaidProgramacionMaquinaOld.getTrabajoMaquinaList().remove(trabajoMaquina);
                programacionMaquinaidProgramacionMaquinaOld = em.merge(programacionMaquinaidProgramacionMaquinaOld);
            }
            if (programacionMaquinaidProgramacionMaquinaNew != null && !programacionMaquinaidProgramacionMaquinaNew.equals(programacionMaquinaidProgramacionMaquinaOld)) {
                programacionMaquinaidProgramacionMaquinaNew.getTrabajoMaquinaList().add(trabajoMaquina);
                programacionMaquinaidProgramacionMaquinaNew = em.merge(programacionMaquinaidProgramacionMaquinaNew);
            }
            if (tiempoInproductivoidTiempoInproductivoOld != null && !tiempoInproductivoidTiempoInproductivoOld.equals(tiempoInproductivoidTiempoInproductivoNew)) {
                tiempoInproductivoidTiempoInproductivoOld.getTrabajoMaquinaList().remove(trabajoMaquina);
                tiempoInproductivoidTiempoInproductivoOld = em.merge(tiempoInproductivoidTiempoInproductivoOld);
            }
            if (tiempoInproductivoidTiempoInproductivoNew != null && !tiempoInproductivoidTiempoInproductivoNew.equals(tiempoInproductivoidTiempoInproductivoOld)) {
                tiempoInproductivoidTiempoInproductivoNew.getTrabajoMaquinaList().add(trabajoMaquina);
                tiempoInproductivoidTiempoInproductivoNew = em.merge(tiempoInproductivoidTiempoInproductivoNew);
            }
            if (tiempoProductivoidTiempoProductivoOld != null && !tiempoProductivoidTiempoProductivoOld.equals(tiempoProductivoidTiempoProductivoNew)) {
                tiempoProductivoidTiempoProductivoOld.getTrabajoMaquinaList().remove(trabajoMaquina);
                tiempoProductivoidTiempoProductivoOld = em.merge(tiempoProductivoidTiempoProductivoOld);
            }
            if (tiempoProductivoidTiempoProductivoNew != null && !tiempoProductivoidTiempoProductivoNew.equals(tiempoProductivoidTiempoProductivoOld)) {
                tiempoProductivoidTiempoProductivoNew.getTrabajoMaquinaList().add(trabajoMaquina);
                tiempoProductivoidTiempoProductivoNew = em.merge(tiempoProductivoidTiempoProductivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = trabajoMaquina.getIdTrabajoMaquina();
                if (findTrabajoMaquina(id) == null) {
                    throw new NonexistentEntityException("The trabajoMaquina with id " + id + " no longer exists.");
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
            TrabajoMaquina trabajoMaquina;
            try {
                trabajoMaquina = em.getReference(TrabajoMaquina.class, id);
                trabajoMaquina.getIdTrabajoMaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajoMaquina with id " + id + " no longer exists.", enfe);
            }
            ProgramacionMaquina programacionMaquinaidProgramacionMaquina = trabajoMaquina.getProgramacionMaquinaidProgramacionMaquina();
            if (programacionMaquinaidProgramacionMaquina != null) {
                programacionMaquinaidProgramacionMaquina.getTrabajoMaquinaList().remove(trabajoMaquina);
                programacionMaquinaidProgramacionMaquina = em.merge(programacionMaquinaidProgramacionMaquina);
            }
            TiempoInproductivo tiempoInproductivoidTiempoInproductivo = trabajoMaquina.getTiempoInproductivoidTiempoInproductivo();
            if (tiempoInproductivoidTiempoInproductivo != null) {
                tiempoInproductivoidTiempoInproductivo.getTrabajoMaquinaList().remove(trabajoMaquina);
                tiempoInproductivoidTiempoInproductivo = em.merge(tiempoInproductivoidTiempoInproductivo);
            }
            TiempoProductivo tiempoProductivoidTiempoProductivo = trabajoMaquina.getTiempoProductivoidTiempoProductivo();
            if (tiempoProductivoidTiempoProductivo != null) {
                tiempoProductivoidTiempoProductivo.getTrabajoMaquinaList().remove(trabajoMaquina);
                tiempoProductivoidTiempoProductivo = em.merge(tiempoProductivoidTiempoProductivo);
            }
            em.remove(trabajoMaquina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TrabajoMaquina> findTrabajoMaquinaEntities() {
        return findTrabajoMaquinaEntities(true, -1, -1);
    }

    public List<TrabajoMaquina> findTrabajoMaquinaEntities(int maxResults, int firstResult) {
        return findTrabajoMaquinaEntities(false, maxResults, firstResult);
    }

    private List<TrabajoMaquina> findTrabajoMaquinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TrabajoMaquina.class));
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

    public TrabajoMaquina findTrabajoMaquina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TrabajoMaquina.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajoMaquinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TrabajoMaquina> rt = cq.from(TrabajoMaquina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
