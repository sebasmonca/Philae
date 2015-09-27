/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Implementacion;

import Persistencia.Controller.PermisosJpaController;
import Ventanas.Permisos;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Sebas
 */
public class ImplPermisos {

    private PermisosJpaController permisosJpaController;

    public ImplPermisos() {
        try {
            this.permisosJpaController = new PermisosJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Persistencia.Entities.Permisos> findPermisosEntities(){
        return this.permisosJpaController.findPermisosEntities();
    }
    
    public Persistencia.Entities.Permisos findPermisosesById(Integer id){
        return this.permisosJpaController.findPermisos(id);
    }

    public Persistencia.Entities.Permisos createPermisos(Persistencia.Entities.Permisos permisos) {
        try {
            this.permisosJpaController.create(permisos);
            return permisos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Persistencia.Entities.Permisos editarPermisos(Persistencia.Entities.Permisos permisos) {
        try {
            this.permisosJpaController.edit(permisos);
            return permisos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public Persistencia.Entities.Permisos elimiarPermisos(Persistencia.Entities.Permisos permisos) {
        try {
            this.permisosJpaController.destroy(permisos.getIdPermisos());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Persistencia.Entities.Permisos> buscar(String id,String descripcion) {
        try {
            return this.permisosJpaController.buscar(id,descripcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
