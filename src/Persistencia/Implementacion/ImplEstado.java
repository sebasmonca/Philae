/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Implementacion;

import Persistencia.Controller.EstadoJpaController;
import Persistencia.Entities.Estado;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Sebas
 */
public class ImplEstado {
     private EstadoJpaController estadoJpaController;
     
    public ImplEstado(){
        try {
            this.estadoJpaController = new EstadoJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Estado> findEstadoEntities() {
        return this.estadoJpaController.findEstadoEntities();
    }
    public Estado finEstadoById(Integer id){
        return this.estadoJpaController.findEstado(id);
    }

    public Persistencia.Entities.Estado createEstado(Persistencia.Entities.Estado estado) {
        Boolean res = true;
        try{
            this.estadoJpaController.create(estado);
            return estado;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }    
     public Persistencia.Entities.Estado editEstado(Persistencia.Entities.Estado estado) {
        try {
            this.estadoJpaController.edit(estado);
            return estado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public Persistencia.Entities.Estado elimiarEstado(Persistencia.Entities.Estado estado) {
        try {
            this.estadoJpaController.destroy(estado.getIdEstado());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Estado> buscar(String id, String descripcion) {
        try {
            return this.estadoJpaController.buscar(id,descripcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
