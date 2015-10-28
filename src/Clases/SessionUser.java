/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Persistencia.Entities.Cliente;
import Persistencia.Entities.Molde;
import Persistencia.Entities.Permisos;
import Persistencia.Entities.Usuario;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebas
 */
public class SessionUser {
    
    
    public static Usuario usuario;    
    public static Integer isTipodocumento = 2;
    public static Integer isPermisos = 3;
    public static Integer isPerfil = 4;
    public static Integer isEstado = 5;
    public static Integer isUsuarios = 6;
    public static Integer isVerUsuario = 7;
    
    public static Cliente cliente;
    public static Integer isCliente = 8;
    public static Integer isVerCliente = 9;
    
    public static Molde molde;
    public static Integer isMolde = 10;
    public static Integer isVerMolde = 11;
    
    

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        SessionUser.usuario = usuario;
    }
    
    public static Cliente getCliente(){
        return cliente;
    }
    
    public static void  setCliente(Cliente cliente){
        SessionUser.cliente = cliente;
    }
    
     public static Molde getMolde(){
        return molde;
    }
    
    public static void  setMolde(Molde molde){
        SessionUser.molde = molde;
    }
    
    public static void isValidarSessionJFrame(javax.swing.JFrame jframe,Integer is){
        Usuario usuario = SessionUser.getUsuario();                
        Boolean res = false;
        if(usuario != null){
            List<Persistencia.Entities.Permisos> listP = usuario.getIdperfil().getPermisosList();
            for (Permisos permiso : listP) {
                if(permiso.getIdPermisos() == is){
                    res = true;
                    jframe.show();
                }
            }
        }
        
        if (!res) {
            jframe.show();
            ((Ventanas.Usuarios)jframe).permisos();
            JOptionPane.showMessageDialog(null, "!No tines los permisos para entrar a este modulo", "Permiso denegado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
