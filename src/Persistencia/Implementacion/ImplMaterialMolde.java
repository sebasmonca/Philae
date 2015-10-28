package Persistencia.Implementacion;

import Persistencia.Controller.MaterialMoldeJpaController;
import Persistencia.Entities.MaterialMolde;
import java.util.List;
import javax.persistence.Persistence;

public class ImplMaterialMolde {

    private MaterialMoldeJpaController materialMoldeJpaController;

    public ImplMaterialMolde() {
        try {
            this.materialMoldeJpaController = new MaterialMoldeJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MaterialMolde finMaterialMoldeById(Integer id) {
        return this.materialMoldeJpaController.findMaterialMolde(id);
    }

    public List<Persistencia.Entities.MaterialMolde> findMaterialMolde() {
        return this.materialMoldeJpaController.findMaterialMoldeEntities();
    }
    public MaterialMolde finMaterialMolde(Integer id){
     return this.materialMoldeJpaController.findMaterialMolde(id);
    }

    public Persistencia.Entities.MaterialMolde findMaterialMoldeById(Integer id) {
        return this.materialMoldeJpaController.findMaterialMolde(id);
    }

    public MaterialMolde createMaterialMolde(Persistencia.Entities.MaterialMolde materialmolde) {
        try {
            this.materialMoldeJpaController.create(materialmolde);
            return materialmolde;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Persistencia.Entities.MaterialMolde editarmaterialMolde(Persistencia.Entities.MaterialMolde materialMolde) {
        try {
            this.materialMoldeJpaController.edit(materialMolde);
            return materialMolde;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Persistencia.Entities.MaterialMolde> findMaterialMoldeEntities() {
        return this.materialMoldeJpaController.findMaterialMoldeEntities();
    }
    public  Persistencia.Entities.MaterialMolde elimiarMaterialMolde(Persistencia.Entities.MaterialMolde materialMolde){
      try {
            this.materialMoldeJpaController.destroy(materialMolde.getIdMaterialMolde());
        } catch (Exception e) {
            e.printStackTrace();
        }
      return null;
    }
     public List<Persistencia.Entities.MaterialMolde> buscar(String id,String descripcion) {
        try {
            return this.materialMoldeJpaController.buscar(id,descripcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
}
