/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import codigo.PrincipalFrame;
import dao.ControllerCreacion;
import dao.UsuarioDaoImp;
import java.awt.Component;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dgall
 */
public class Usuario extends Thread {

    private static final Usuario instancia = new Usuario();

    private Usuario() {
    }

    public static Usuario init() {
        return instancia;
    }

    public static Usuario init(Scanner entradaInfo, Scanner entradaRespuesta, PrintWriter salidaInfo, PrintWriter salidaRespuesta, Component cmpt, JFrame jFrameLogin) {
        instancia.entradaInfo = entradaInfo;
        instancia.salidaInfo = salidaInfo;
        instancia.entradaRespuesta = entradaRespuesta;
        instancia.salidaRespuesta = salidaRespuesta;
        instancia.cmpt = cmpt;
        instancia.jFrameLogin = jFrameLogin;
        return instancia;
    }

    public static ServerSocket escuchador;
    public static Socket conexion;
    private static boolean finalizar = false;
    private Scanner entradaInfo;
    private Scanner entradaRespuesta;
    private PrintWriter salidaInfo;
    private PrintWriter salidaRespuesta;
    private Component cmpt;
    private JFrame jFrameLogin;
    private JFrame jFramePrincipal;

    private static int id;
    private static String nickname;
    private static String nombre;
    private static String apellido1;
    private static String apellido2;
    private static Calendar fechaCreacion;
    private static Calendar fechaNacimiento;
    private static String usuariosSeguidos;
    private static int numUsuariosSeguidos;
    private static String contrasena;
    private static float valoracion;
    private static int vecesValorado;
    private static int totalValoraciones;
    private static String biografia;
    private ArrayList<Quedada> quedadas = new ArrayList<>();

    @Override
    public void run() {
        do {

            System.out.println("Antes de nextLine en cliente");
            String respuesta = entradaRespuesta.nextLine();
            System.out.println("Esta es la respuesta que recibo en el cliente=" + respuesta);
            //Trocear (primero un nivel "#" y luego a segundo nivel "%", no creo que necesite tercer nivel "&")y hacer Switch
            //TIPEADO TROCEO= trozo0=accion // trozo1=respuesta // trozo2=info(si hubiera)
            String[] troceo = respuesta.split("#");
            String accion = troceo[0];
            switch (accion) {
                case "login":
                    respuestaLogin(troceo[1], troceo[2]);
                    break;
                case "crea":
                    respuestaCrea(troceo[1], troceo[2]);
                    break;
                case "quedada":
                    System.out.println("quedada en cliente thread");
                    respuestaQuedada(troceo[1]);
                    break;
                case "unirse":
                    System.out.println("Unirse en cliente");
                    respuestaUnirse(troceo[1]);
                    break;

            }
        } while (!finalizar);
    }

    public static String getBiografia() {
        return biografia;
    }

    public static void setBiografia(String biografia) {
        Usuario.biografia = biografia;
    }

    public static int getNumUsuariosSeguidos() {
        return numUsuariosSeguidos;
    }

    public static void setNumUsuariosSeguidos(int numUsuariosSeguidos) {
        Usuario.numUsuariosSeguidos = numUsuariosSeguidos;
    }

    public JFrame getjFrame() {
        return jFrameLogin;
    }

    public void setjFrame(JFrame jFrameLogin) {
        this.jFrameLogin = jFrameLogin;
    }

    public Component getCmpt() {
        return cmpt;
    }

    public void setCmpt(Component cmpt) {
        this.cmpt = cmpt;
    }

    public Scanner getEntradaInfo() {
        return entradaInfo;
    }

    public void setEntradaInfo(Scanner entradaInfo) {
        this.entradaInfo = entradaInfo;
    }

    public Scanner getEntradaRespuesta() {
        return entradaRespuesta;
    }

    public void setEntradaRespuesta(Scanner entradaRespuesta) {
        this.entradaRespuesta = entradaRespuesta;
    }

    public PrintWriter getSalidaInfo() {
        return salidaInfo;
    }

    public void setSalidaInfo(PrintWriter salidaInfo) {
        this.salidaInfo = salidaInfo;
    }

    public PrintWriter getSalidaRespuesta() {
        return salidaRespuesta;
    }

    public void setSalidaRespuesta(PrintWriter salidaRespuesta) {
        this.salidaRespuesta = salidaRespuesta;
    }

    public static ServerSocket getEscuchador() {
        return escuchador;
    }

    public static void setEscuchador(ServerSocket escuchador) {
        Usuario.escuchador = escuchador;
    }

    public static Socket getConexion() {
        return conexion;
    }

    public static void setConexion(Socket conexion) {
        Usuario.conexion = conexion;
    }

    public static boolean isFinalizar() {
        return finalizar;
    }

    public static void setFinalizar(boolean finalizar) {
        Usuario.finalizar = finalizar;
    }

    //USUARIO COMPLETO v
    public static Usuario init(int id, String nickname, String nombre, String apellido1, String apellido2, Calendar fechaCreacion, Calendar fechaNacimiento, String usuariosSeguidos, int numUsuariosSeguidos, String contrasena, float valoracion, int vecesValorado, int totalValoraciones, String biografia) {
        instancia.id = id;
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        instancia.fechaCreacion = fechaCreacion;
        instancia.fechaNacimiento = fechaNacimiento;
        instancia.usuariosSeguidos = usuariosSeguidos;
        instancia.numUsuariosSeguidos = numUsuariosSeguidos;
        instancia.contrasena = contrasena;
        instancia.valoracion = valoracion;
        instancia.vecesValorado = vecesValorado;
        instancia.totalValoraciones = totalValoraciones;
        instancia.biografia = biografia;
        return instancia;
    }

    //(TEMPORAL)Igual no temporal y esto es visualizacion reducida v
    public static Usuario init(int id, String nickname, String nombre, String apellido1, String apellido2) {
        instancia.id = id;
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        return instancia;
    }

    //USUARIO NUEVO v
    public static Usuario init(String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String contrasena) {
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        instancia.fechaNacimiento = fechaNacimiento;
        instancia.contrasena = contrasena;
        return instancia;
    }

    //USUARIO INFORMACION v
    public static Usuario init(String nickname, String nombre, String apellido1, String apellido2, Calendar fechaNacimiento, String usuariosSeguidos, int numUsuariosSeguidos, float valoracion, int vecesValorado, int totalValoraciones, String biografia) {
        instancia.nickname = nickname;
        instancia.nombre = nombre;
        instancia.apellido1 = apellido1;
        instancia.apellido2 = apellido2;
        instancia.fechaNacimiento = fechaNacimiento;
        instancia.usuariosSeguidos = usuariosSeguidos;
        instancia.numUsuariosSeguidos = numUsuariosSeguidos;
        instancia.valoracion = valoracion;
        instancia.vecesValorado = vecesValorado;
        instancia.totalValoraciones = totalValoraciones;
        instancia.biografia = biografia;
        return instancia;
    }

    public ArrayList<Quedada> getQuedadas() {
        return quedadas;
    }

    public void setQuedadas(ArrayList<Quedada> quedadas) {
        this.quedadas = quedadas;
    }

    public int getVecesValorado() {
        return vecesValorado;
    }

    public void setVecesValorado(int vecesValorado) {
        instancia.vecesValorado = vecesValorado;
    }

    public int getTotalValoraciones() {
        return totalValoraciones;
    }

    public void setTotalValoraciones(int totalValoraciones) {
        instancia.totalValoraciones = totalValoraciones;
    }

    public void setContrasena(String contrasena) {
        instancia.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdUsuario() {
        return id;
    }

    public void setId(int id) {
        instancia.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        instancia.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        instancia.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        instancia.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        instancia.apellido2 = apellido2;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        instancia.fechaCreacion = fechaCreacion;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        instancia.fechaNacimiento = fechaNacimiento;
    }

    public String getUsuariosSeguidos() {
        return usuariosSeguidos;
    }

    public void setUsuariosSeguidos(String usuariosSeguidos) {
        instancia.usuariosSeguidos = usuariosSeguidos;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        instancia.valoracion = valoracion;

    }

    public JFrame getjFrameLogin() {
        return jFrameLogin;
    }

    public void setjFrameLogin(JFrame jFrameLogin) {
        this.jFrameLogin = jFrameLogin;
    }

    public JFrame getjFramePrincipal() {
        return jFramePrincipal;
    }

    public void setjFramePrincipal(JFrame jFramePrincipal) {
        this.jFramePrincipal = jFramePrincipal;
    }

    public String fechaAStringCorrecta(Calendar fecha) {
        if (fecha == null) {
            return null;
        } else {
            String fechaString = fecha.get(Calendar.YEAR) + "-" + (fecha.get(Calendar.MONTH)) + "-" + fecha.get(Calendar.DAY_OF_MONTH);

            //System.out.println(fechaCalendar.get(Calendar.DAY_OF_MONTH)+ " " + fechaCalendar.get(Calendar.MONTH)+" "+fechaCalendar.get(Calendar.YEAR));
            return fechaString;
        }

    }

    public Calendar fechaACAlendarCorrecta(String fecha) {
        if (fecha == null) {
            return null;
        } else {
            Calendar fechaCalendar = Calendar.getInstance();
            String[] fechaDividida = new String[2];

            fechaDividida = fecha.split("-");

            int year = Integer.parseInt(fechaDividida[0]);
            int month = Integer.parseInt(fechaDividida[1]);
            int day = Integer.parseInt(fechaDividida[2]);

            fechaCalendar.set(Calendar.YEAR, year);
            fechaCalendar.set(Calendar.MONTH, month);
            fechaCalendar.set(Calendar.DAY_OF_MONTH, day);

            return fechaCalendar;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (instancia == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (instancia.id != other.id) {
            return false;
        }
        if (!Objects.equals(instancia.nickname, other.nickname)) {
            return false;
        }
        return true;
    }

    public String toStringCompleto() {
        return id + "&" + nickname + "&" + nombre + "&" + apellido1 + "&" + apellido2 + "&" + fechaAStringCorrecta(fechaCreacion) + "&" + fechaAStringCorrecta(fechaNacimiento) + "&" + usuariosSeguidos + "&" + numUsuariosSeguidos + "&" + contrasena + "&" + valoracion + "&" + vecesValorado + "&" + totalValoraciones + "&" + biografia;
    }

    public String toStringCompletoAlmohadilla() {
        return id + "#" + nickname + "#" + nombre + "#" + apellido1 + "#" + apellido2 + "#" + fechaAStringCorrecta(fechaCreacion) + "#" + fechaAStringCorrecta(fechaNacimiento) + "#" + usuariosSeguidos + "#" + numUsuariosSeguidos + "#" + contrasena + "#" + valoracion + "#" + vecesValorado + "#" + totalValoraciones + "#" + biografia;
    }

    public String toStringsSoloId() {
        return id + "";
    }

    /*@Override
    public String toString() {
        return id + "&" + nickname + "&" + nombre + "&" + apellido1 + "&" + apellido2 + "&" + fechaAStringCorrecta(fechaNacimiento) + "&" + contrasena;
    }*/
    @Override
    public String toString() {
        return id + "=" + nickname + "=" + nombre + "=" + apellido1 + "=" + apellido2 + "=" + fechaAStringCorrecta(fechaNacimiento) + "=" + contrasena;
    }

    private void respuestaCrea(String respuesta, String id) {
        switch (respuesta) {
            case "true":
                System.out.println("Cuenta creada con exito");
                System.out.println(respuesta);
                System.out.println(id + "");
                instancia.setId(Integer.parseInt(id));
                obtenerPorId(Integer.parseInt(id));
                System.out.println(instancia.toStringCompleto());
                //Abrir el inicio
                PrincipalFrame pF = new PrincipalFrame(instancia);
                pF.setVisible(true);
                jFrameLogin.dispose();
                break;
            case "false":
                //Mensaje de advertencia
                JOptionPane.showMessageDialog(cmpt, "No se ha podido crear la cuenta. El usuario ya existe", "Error al Crear Cuenta", JOptionPane.ERROR_MESSAGE);

                break;
        }
    }

    private void respuestaLogin(String respuesta, String info) {
        System.out.println(info);

        switch (respuesta) {
            case "true":
                System.out.println("Cuenta logeada con exito");

                UsuarioNoThread u = creaUsuarioLogin(info);
                System.out.println("Dentro de Respuesta login ==>" + u.toStringCompleto());
                instancia.setId(u.getIdUsuario());
                instancia.setNickname(u.getNickname());
                instancia.setNombre(u.getNombre());
                instancia.setApellido1(u.getApellido1());
                instancia.setApellido2(u.getApellido2());
                instancia.setFechaCreacion(u.getFechaCreacion());
                instancia.setFechaNacimiento(u.getFechaNacimiento());
                instancia.setUsuariosSeguidos(u.getUsuariosSeguidos());
                instancia.setNumUsuariosSeguidos(u.getNumUsuariosSeguidos());
                instancia.setContrasena(u.getContrasena());
                instancia.setValoracion(u.getValoracion());
                instancia.setVecesValorado(u.getVecesValorado());
                instancia.setTotalValoraciones(u.getTotalValoraciones());
                instancia.setBiografia(u.getBiografia());

                System.out.println("Dentro de Login User==>" + instancia.toStringCompleto());

                //Abrir el inicio
                PrincipalFrame pF = new PrincipalFrame(instancia);
                pF.setVisible(true);
                jFrameLogin.dispose();

                //CAMBIO LOS VALORES DE LAS VARIABLES DEL HILO PORQUE ES MI USUARIO
                //Pasarle al inicio el usuario para que ya tenga mi usuario para usar cokmo quiera en todo el programa
                //Cambiar usuarioNoThread para que no tenga lo del init, init solo lo uso en hilos, preferiblemente
                break;
            case "false":
                //Mensaje de advertencia
                JOptionPane.showMessageDialog(cmpt, "No se ha podido crear la cuenta", "Error al Crear Cuenta", JOptionPane.ERROR_MESSAGE);

                break;
        }
    }

    private UsuarioNoThread creaUsuarioLogin(String info) {
        ControllerCreacion cntr = new ControllerCreacion();
        return cntr.creaObjUsuarioLogeo(info);

    }

    private void respuestaQuedada(String quedada) {
        //return nombreQuedada + "$" + numeroAsistentes + "$" + motivoQuedada + "$" + numeroUsuariosUnidos + "$" + creador[return id + "$" + nickname + "$" + nombre + "$" + apellido1 + "$" + apellido2 + "$" + fechaAStringCorrecta(fechaNacimiento) + "$" + contrasena;] + "$" + hora + "$" direccion + fechaAStringCorrecta(creacionQuedada);
        System.out.println(quedada);
        String[] division = quedada.split("=");
        //System.out.println("FIJATE AQUIIIIII==>" + division.length);
        String nombreQuedada = division[0];
        int numeroAsistentes = Integer.parseInt(division[1]);
        String motivoQuedada = division[2];
        int numeroUsuariosUnidos = Integer.parseInt(division[3]);
        int idCreador = Integer.parseInt(division[4]);
        String nicknameCreador = division[5];
        String nombreCreador = division[6];
        String apellido1Creador = division[7];
        String apellido2Creador = division[8];
        String fechaNacimientoCreador = division[9];
        String contrasenaCreador = division[10];//No usar
        String hora = division[11];
        String direccion = division[12];
        Calendar creacionQuedada = instancia.fechaACAlendarCorrecta(division[13]);
        int numQuedadas = Integer.parseInt(division[14]);

        UsuarioNoThread u = new UsuarioNoThread(idCreador, nicknameCreador, nombreCreador, apellido1Creador, apellido2Creador);
        Quedada q = new Quedada(nombreQuedada, numeroAsistentes, motivoQuedada, numeroUsuariosUnidos, u, hora, direccion, creacionQuedada);
        q.setNumeroQuedadas(numQuedadas);
        quedadas.add(q);
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHHHHHHH---->" + quedadas.size());
        jFramePrincipal.dispose();
        System.out.println("fallas aqui?");
        PrincipalFrame pF = new PrincipalFrame(instancia, quedadas);
        pF.setVisible(true);

    }

    private void obtenerPorId(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/meetmeup", "root", "1234");
            String sql = "SELECT nick_usuario,AES_DECRYPT(password_usuario, 'admin'),nombre_usuario, apellido1_usuario, apellido2_usuario, fecha_creacion_usuario,fecha_nacimiento_usuario from usuarios WHERE id_usuario=" + id;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                instancia.setNickname(rs.getString("nick_usuario"));
                instancia.setContrasena(rs.getString("AES_DECRYPT(password_usuario, 'admin')"));
                instancia.setNombre(rs.getString("nombre_usuario"));
                instancia.setApellido1(rs.getString("apellido1_usuario"));
                instancia.setApellido2(rs.getString("apellido2_usuario"));
                instancia.setFechaCreacion(instancia.fechaACAlendarCorrecta(rs.getString("fecha_creacion_usuario")));
                instancia.setFechaNacimiento(instancia.fechaACAlendarCorrecta(rs.getString("fecha_nacimiento_usuario")));
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void respuestaUnirse(String unirse) {
        //idUnido + "=" + nickUnido + "=" + idCreador + "=" + nickCreador;

        int idUnido;
        String nickUnido;
        int idCreador;
        String nickCreador;
        String[] dividir = unirse.split("=");
        idUnido = Integer.parseInt(dividir[0]);
        nickUnido = dividir[1];
        idCreador = Integer.parseInt(dividir[2]);
        nickCreador = dividir[3];

        UsuarioNoThread u = obtenerDeBd(idUnido);

        for (int i = 0; i < quedadas.size(); i++) {
            Quedada q = quedadas.get(i);

            String buscaNick = q.getCreador().getNickname();
            if (buscaNick.equals(nickCreador)) {
                ArrayList<UsuarioNoThread> users = q.getUsuariosUnidos();
                int numUnidos = q.getNumeroUsuariosUnidos();
                users.add(u);
                q.setUsuariosUnidos(users);
                q.setNumeroUsuariosUnidos(numUnidos + 1);

            }

        }
        jFramePrincipal.dispose();
        PrincipalFrame pF = new PrincipalFrame(instancia, quedadas);
        pF.setVisible(true);

    }

    private UsuarioNoThread obtenerDeBd(int idUnido) {
        Statement stm = null;
        ResultSet rs = null;
        String fecha;

        String sql = "SELECT id_usuario,nick_usuario,AES_DECRYPT(password_usuario, 'admin'),nombre_usuario,apellido1_usuario,apellido2_usuario,fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos,num_usuarios_seguidos,valoracion_total,veces_valorado,biografia from usuarios WHERE id_usuario=" + idUnido;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/meetmeup", "root", "1234");
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if(rs.next()){
            UsuarioNoThread u = new UsuarioNoThread();
            u.setId(rs.getInt("id_usuario"));
            u.setNickname(rs.getString("nick_usuario"));
            u.setContrasena(encriptar(rs.getString("AES_DECRYPT(password_usuario, 'admin')")));
            u.setNombre(rs.getString("nombre_usuario"));
            u.setApellido1(rs.getString("apellido1_usuario"));
            u.setApellido2(rs.getString("apellido2_usuario"));

            fecha = rs.getString("fecha_creacion_usuario");
            u.setFechaCreacion(crearCalendarDeString(fecha));

            fecha = rs.getString("fecha_nacimiento_usuario");
            u.setFechaNacimiento(crearCalendarDeString(fecha));
            u.setUsuariosSeguidos(rs.getString("usuarios_seguidos"));
            u.setNumUsuariosSeguidos(rs.getInt("num_usuarios_seguidos"));
            u.setVecesValorado(rs.getInt("veces_valorado"));
            u.setTotalValoraciones(rs.getInt("valoracion_total"));
            u.setBiografia(rs.getString("biografia"));

            stm.close();
            rs.close();
            conn.close();
            return u;
            }
        } catch (SQLException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    private static String desencriptar(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    private Calendar crearCalendarDeString(String s) {
        Calendar fechaCreacion = Calendar.getInstance();
        String[] fechaDividida = new String[2];

        fechaDividida = s.split("-");

        int year = Integer.parseInt(fechaDividida[0]);
        int month = Integer.parseInt(fechaDividida[1]);
        int day = Integer.parseInt(fechaDividida[2]);

        fechaCreacion.set(Calendar.YEAR, year);
        fechaCreacion.set(Calendar.MONTH, month);
        fechaCreacion.set(Calendar.DAY_OF_MONTH, day);

        return fechaCreacion;
    }

}
