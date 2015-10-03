/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Implementacion;
   
import Persistencia.Controller.UsuarioJpaController;
import Persistencia.Entities.Usuario;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Sebas
 */
public class ImplUsuario {
    private UsuarioJpaController usuarioJpaController;
    
    public ImplUsuario(){
        try {
            this.usuarioJpaController = new UsuarioJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public List<Usuario> finUsuariosEntities() {
        return this.usuarioJpaController.findUsuarioEntities();
    }
    public Usuario finUsuario(Integer id) {
        return this.usuarioJpaController.findUsuario(id);
    }
    public Usuario createUsuario(Usuario usuario){
        try{
            this.usuarioJpaController.create(usuario);
            return usuario;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
     public Persistencia.Entities.Usuario editarUsuario(Persistencia.Entities.Usuario usuario) {
        try {
            this.usuarioJpaController.edit(usuario);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Persistencia.Entities.Usuario elimiarUsuario(Persistencia.Entities.Usuario usuario) {
        try {
            this.usuarioJpaController.destroy(usuario.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Persistencia.Entities.Usuario login(String name,String pass) {
        try {
            if(!name.equals("") && !pass.equals(""))
               return this.usuarioJpaController.login(name,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Persistencia.Entities.Usuario> buscar(String id,String nombre,String numeroDocumento) {
        try {
            return this.usuarioJpaController.buscar(id,nombre,numeroDocumento);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
