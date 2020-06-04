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
    private int numUsuariosSeguidos;
    private String contrasena;
    private float valoracion;
    private int vecesValorado;
    private int totalValoraciones;
    private String biografia;
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

    @Override
    public UsuarioNoThread actualizarToObj(String comando) {
        //return id + "#" + nickname + "#" + nombre + "#" + apellido1 + "#" + apellido2 + "#" + 
//        fechaAStringCorrecta(fechaCreacion) + "#" + fechaAStringCorrecta(fechaNacimiento) + "#" 
//                + usuariosSeguidos + "#" + numUsuariosSeguidos + "#" + contrasena + "#" + valoracion +
//                "#" + vecesValorado + "#" + totalValoraciones + "#" + biografia;
        String[] datosUsuario;
        datosUsuario = comando.split("#");
        id = Integer.parseInt(datosUsuario[0]);
        nickname = datosUsuario[1];
        nombre = datosUsuario[2];
        apellido1 = datosUsuario[3];
        apellido2 = datosUsuario[4];
        fechaCreacion = u.fechaACAlendarCorrecta(datosUsuario[5]);
        fechaNacimiento = u.fechaACAlendarCorrecta(datosUsuario[6]);
        usuariosSeguidos = datosUsuario[7];
        numUsuariosSeguidos = Integer.parseInt(datosUsuario[8]);
        contrasena = datosUsuario[9];
        valoracion = Float.parseFloat(datosUsuario[10]);
        vecesValorado = Integer.parseInt(datosUsuario[11]);
        totalValoraciones = Integer.parseInt(datosUsuario[12]);
        biografia = datosUsuario[13];

        UsuarioNoThread user = new UsuarioNoThread(nickname, nombre, apellido1, apellido2, fechaNacimiento, usuariosSeguidos, numUsuariosSeguidos, valoracion, vecesValorado, totalValoraciones, biografia);
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
        numUsuariosSeguidos = Integer.parseInt(datos[9]);
        valoracion = Float.parseFloat(datos[10]);
        vecesValorado = Integer.parseInt(datos[11]);
        totalValoraciones = Integer.parseInt(datos[12]);
        biografia = datos[13];

        UsuarioNoThread user = new UsuarioNoThread(id, nickname, nombre, apellido1, apellido2, fechaCreacion, fechaNacimiento, usuariosSeguidos, numUsuariosSeguidos, contrasena, valoracion, vecesValorado, totalValoraciones, biografia);
        return user;
    }

}
