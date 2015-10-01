/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Persistencia.Entities.Cliente;
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
    public static Integer isCliente = 8;
    public static Integer isVerCliente = 9;
    

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        SessionUser.usuario = usuario;
    }
    
    public static void isValidarSessionJFrame(javax.swing.JFrame jframe,Integer is){
        Usuario usuario = SessionUser.getUsuario();
        //Cliente cliente = SessionUser.get
        Boolean res = true;
        if(usuario != null){
            List<Persistencia.Entities.Permisos> listP = usuario.getIdperfil().getPermisosList();
            for (int i = 0; i < listP.size(); i++) {     
                if(listP.get(i).getIdPermisos() == is){
                    res = false;
                    jframe.show();
                }
                if(listP.get(i).getIdPermisos() == isVerUsuario && is == isUsuarios){
                    res = false;
                    ((Ventanas.Usuarios)jframe).permisos();
                    jframe.show();
                }
                
            }
        }
        if (res) {
            JOptionPane.showMessageDialog(null, "!No tines los permisos para entrar a este modulo", "Permiso denegado", JOptionPane.ERROR_MESSAGE);
        }
    }
}
