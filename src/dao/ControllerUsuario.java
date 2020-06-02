/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Usuario;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class ControllerUsuario {

    private UsuarioNoThread user;

    public ControllerUsuario() {
    }

    public void registrar(String cadenaInfoRegistrar) {
        user = new UsuarioNoThread();
        UsuarioICrud dao = new UsuarioDaoImp();
        CreaUsuariosI crear = new CreaUsuarios();
        user = crear.registrarToObj(cadenaInfoRegistrar);
        dao.registrar(user);
    }

    public void actualizar(String cadenaInfoActualizar) {
        user = new UsuarioNoThread();
        UsuarioICrud dao = new UsuarioDaoImp();
        CreaUsuariosI crear = new CreaUsuarios();
        user = crear.actualizarToObj(cadenaInfoActualizar);
        dao.actualizar(user);
    }

    public void eliminar(int idUsuarioDelete) {
        UsuarioICrud dao = new UsuarioDaoImp();
        dao.eliminar(idUsuarioDelete);
    }

    public ArrayList<UsuarioNoThread> obtenerTodos() {

        ArrayList<UsuarioNoThread> usuarios = new ArrayList<>();
        UsuarioICrud dao = new UsuarioDaoImp();
        usuarios = dao.obtener();

        return usuarios;

    }

    public UsuarioNoThread obtenerUnico(int id) {
        UsuarioNoThread u;
        UsuarioICrud dao = new UsuarioDaoImp();
        u = dao.obtenerUnico(id);

        return u;
    }

}
