/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Implementacion;

import Persistencia.Controller.ClienteJpaController;
import Persistencia.Entities.Cliente;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Sebas
 */
public class ImplCliente {

    public static Cliente findTipoDocumentoById(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    private ClienteJpaController clienteJpaController ;

    public ImplCliente() {
        try {
            this.clienteJpaController = new ClienteJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    
    public List<Cliente> finClienteEntities(){
        return this.clienteJpaController.findClienteEntities();
    }
    
    public Cliente finCliente(Integer id){
        return this.clienteJpaController.findCliente(id);        
    }
    
    public Cliente createCliente(Cliente cliente){
        try {
            this.clienteJpaController.create(cliente);
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Persistencia.Entities.Cliente editarCliente(Persistencia.Entities.Cliente cliente){
        try {
            this.clienteJpaController.edit(cliente);
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;      
    }
    
    public Persistencia.Entities.Cliente eliminarCliente(Persistencia.Entities.Cliente cliente){
        try {
            this.clienteJpaController.destroy(cliente.getIdCliente());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Persistencia.Entities.Cliente> buscar (String id, String razonSocial){
        try {
            return this.clienteJpaController.buscar(id,razonSocial);
        } catch (Exception e) {
            return null;
        }
    }
}
