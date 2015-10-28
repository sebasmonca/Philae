
package Persistencia.Implementacion;

import Persistencia.Controller.MoldeJpaController;
import Persistencia.Entities.Estado;
import javax.persistence.Persistence;
import java.util.List;
import Persistencia.Entities.Molde;


public class ImplMolde {
    
    private   MoldeJpaController moldeJpaController;
    
    public ImplMolde(){
       try {
           this.moldeJpaController=new MoldeJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
           
           } catch (Exception e) {
            e.printStackTrace();
        }
       
    
    }
    public List<Molde>  findMoldeEntities(){
        return this.moldeJpaController.findMoldeEntities();
    }
    public Molde finMolde(Integer id){
        return this.moldeJpaController.findMolde(id);        
    }
    
    
    public Persistencia.Entities.Molde   editMolde(Persistencia.Entities.Molde molde){
        try{
            this.moldeJpaController.edit(molde);
            return molde;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public Persistencia.Entities.Molde elimiarMolde(Persistencia.Entities.Molde molde) {
        try {
            this.moldeJpaController.destroy(molde.getIdMolde());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public List<Molde> buscar(String id, String nombreMolde) {
        try {
            return this.moldeJpaController.buscar(id,nombreMolde);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public Molde createMolde(Molde molde){
           try{
              this.moldeJpaController.create(molde);
              return molde;
           }catch(Exception e){
            e.printStackTrace();
        }  
           
           return null;
           
     }
}
