/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Implementacion;

import Persistencia.Controller.PerfilJpaController;
import Persistencia.Entities.Perfil;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Sebas
 */
public class ImplPerfil {
    private PerfilJpaController perfilJpaController;
    
    public ImplPerfil() {
        try {
            this.perfilJpaController = new PerfilJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public List<Perfil> findPerfilEntities() {
        return this.perfilJpaController.findPerfilEntities();
    }
    public Perfil findPerfilById(Integer id){
        return this.perfilJpaController.findPerfil(id);
    }
    public Persistencia.Entities.Perfil createPerfil(Persistencia.Entities.Perfil perfil) {
         Boolean res = true;
        try{
            this.perfilJpaController.create(perfil);
            return perfil;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
      public Persistencia.Entities.Perfil editPerfil(Persistencia.Entities.Perfil perfil) {
        try {
            this.perfilJpaController.edit(perfil);
            return perfil;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public Persistencia.Entities.Perfil elimiarPerfil(Persistencia.Entities.Perfil perfil) {
        try {
            this.perfilJpaController.destroy(perfil.getIdperfil());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Persistencia.Entities.Perfil> buscar(String id,String descripcion) {
        try {
            return this.perfilJpaController.buscar(id,descripcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
