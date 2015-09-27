package Clases;

public class Datos {

    public boolean validarUsuario(String usuario, String clave) {
        if (usuario.equals("admin") && clave.equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

}
