package Persistencia.Implementacion;

import Persistencia.Controller.EstadoMoldeMaquinaJpaController;
import Persistencia.Entities.EstadoMoldeMaquina;
import java.util.List;
import javax.persistence.Persistence;

public class ImplEstadoMolde {

    private EstadoMoldeMaquinaJpaController estadoMoldeMaquinaJpaController;
    
    public ImplEstadoMolde(){
        try{
            this.estadoMoldeMaquinaJpaController  = new EstadoMoldeMaquinaJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Persistencia.Entities.EstadoMoldeMaquina> findEstadoMoldeMaquinaEntities() {
        return this.estadoMoldeMaquinaJpaController.findEstadoMoldeMaquinaEntities();
    }
    
    public EstadoMoldeMaquina finEstadoById(Integer id){
        return this.estadoMoldeMaquinaJpaController.findEstadoMoldeMaquina(id);
    }
} 
