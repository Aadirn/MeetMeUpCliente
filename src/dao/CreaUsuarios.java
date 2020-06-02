/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Calendar;
import modelo.Usuario;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class CreaUsuarios implements CreaUsuariosI {

    private int id;
    private String nickname;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Calendar fechaCreacion;
    private Calendar fechaNacimiento;
    private String usuariosSeguidos;
    private String contrasena;
    private float valoracion;
    private int vecesValorado;
    private int totalValoraciones;
    private UsuarioNoThread u = new UsuarioNoThread();

    public UsuarioNoThread registrarToObj(String comando) {
        //nick_usuario,password_usuario,nombre_usuario,apellido1_usuario,apellido2_usuario,fecha_nacimiento_usuario
        String[] datosUsuario;
        datosUsuario = comando.split("#");
        //String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String contrasena
        nickname = datosUsuario[0];
        contrasena = datosUsuario[1];
        nombre = datosUsuario[2];
        apellido1 = datosUsuario[3];
        apellido2 = datosUsuario[4];
        fechaNacimiento = u.fechaACAlendarCorrecta(datosUsuario[5]);

        UsuarioNoThread user = new UsuarioNoThread(nickname, nombre, apellido1, apellido2, fechaNacimiento, contrasena);
        return user;

    }

    public UsuarioNoThread actualizarToObj(String comando) {
        //nick_usuario+, nombre_usuario+ "', apellido1_usuario "', apellido2_usuario='"', fecha_nacimiento_usuario=', valoraciones='" + "', veces_valorado='" ++ "', valoracion_total='
        String[] datosUsuario;
        datosUsuario = comando.split("#");
        nickname = datosUsuario[0];
        nombre = datosUsuario[1];
        apellido1 = datosUsuario[2];
        apellido2 = datosUsuario[3];
        fechaNacimiento = u.fechaACAlendarCorrecta(datosUsuario[4]);
        valoracion = Float.parseFloat(datosUsuario[5]);
        vecesValorado = Integer.parseInt(datosUsuario[6]);
        totalValoraciones = Integer.parseInt(datosUsuario[7]);

        UsuarioNoThread user = new UsuarioNoThread(nickname, nombre, apellido1, apellido2, fechaNacimiento, usuariosSeguidos, valoracion, vecesValorado, totalValoraciones);
        return user;

    }

    @Override
    public UsuarioNoThread logeoToObj(String comando) {
        System.out.println("logeoToObj==>" + comando);
        String[] datos = comando.split("%");
        id = Integer.parseInt(datos[0]);
        nickname = datos[1];
        contrasena = datos[2];
        nombre = datos[3];
        apellido1 = datos[4];
        apellido2 = datos[5];
        fechaCreacion = u.fechaACAlendarCorrecta(datos[6]);
        fechaNacimiento = u.fechaACAlendarCorrecta(datos[7]);
        usuariosSeguidos = datos[8];
        valoracion = Float.parseFloat(datos[9]);
        vecesValorado = Integer.parseInt(datos[10]);
        totalValoraciones = Integer.parseInt(datos[11]);
        
        UsuarioNoThread user = new UsuarioNoThread(id, nickname, nombre, apellido1, apellido2, fechaCreacion, fechaNacimiento, usuariosSeguidos, contrasena, valoracion, vecesValorado, totalValoraciones);
        return user;
    }

}
