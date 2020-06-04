/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.Quedada;
import modelo.Usuario;
import static modelo.Usuario.init;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class Pruebilla {

    private static ArrayList<Quedada> ekisde = new ArrayList<Quedada>();
    private static ArrayList<Usuario> ekisdos = new ArrayList<Usuario>();
    private static ArrayList<Usuario> listaFinalQuedada = new ArrayList<>();
    private static Usuario creador;

    public static void main(String[] args) {
        //crearDatosPrueba();
        //joptionPrueba();
        pruebaUsuarios();
    }

    //public static void crearDatosPrueba() {
////IMPORTANTE FIJARME EN COMO LO SACA EL .toString
//        Usuario u1 = init(1, "Laponia", "Donpa", "Tri", "Cio");
//        Usuario u2 = init(2, "Cuck", "Senior", "Joba", "Soma");
//        Usuario u3 = init(3, "Sabroson", "Lara", "Coma", "Punto");
//        Usuario u4 = init(4, "Cancuckpicha", "Laura", "Lame", "Ortos");
//
//        Quedada q1 = new Quedada("Juju", 12, "XQSi", 34, ekisdos, u1, "Yokese", Calendar.getInstance());
//
//        ekisdos.add(u1);
//        ekisdos.add(u2);
//        ekisdos.add(u3);
//        ekisdos.add(u4);
//
//        //System.out.println(q1.toString());
//        //USAR ESTO PARA CREAR UNA CLASE/INTEFAZ CON CLASE PARA OBTENER
//        //LOS USUARIOS QUE SE UNEN A UNA QUEDADA Y EL CREADOR DE LA MISMA
//        String[] f = q1.toString().split("#");
//
//        for (int i = 0; i < f.length; i++) {
//
//            switch (i) {
//                case 4:
//
//                    String separar = f[i];
//                    //System.out.println(separar);
//                    int inicio = separar.indexOf("[");
//                    int fin = separar.indexOf("]");
//                    String miniString = separar.substring(inicio + 1, fin).replace(" ", "");
//                    System.out.println(miniString + "\n");
//                    String[] otraDivision = miniString.split(",");
//                    //System.out.println("imbecil que soy");
//                    for (int j = 0; j < otraDivision.length; j++) {
//                        //System.out.println("PITO");
//                        String[] ultimaDivision = otraDivision[j].split("&");
//                        System.out.println(ultimaDivision[0]);
//                        int id = Integer.parseInt(ultimaDivision[0]);
//                        String nick = ultimaDivision[1];
//                        String nombre = ultimaDivision[2];
//                        String ap1 = ultimaDivision[3];
//                        String ap2 = ultimaDivision[4];
//                        Usuario fin1 = Usuario.init(id, nick, nombre, ap1, ap2);
//                        listaFinalQuedada.add(fin1);
//
//                        for (int k = 0; k < ultimaDivision.length; k++) {
//
//                            System.out.println(ultimaDivision[k]);
//                        }
//                        System.out.println(otraDivision[j] + "\n");
//                    }
//                    break;
//                case 5:
//                    String[] usuarioCreador = f[i].split("&");
//                    int id = Integer.parseInt(usuarioCreador[0]);
//                    String nick = usuarioCreador[1];
//                    String nombre = usuarioCreador[2];
//                    String ap1 = usuarioCreador[3];
//                    String ap2 = usuarioCreador[4];
//                    creador = Usuario.init(id, nick, nombre, ap1, ap2);
//
//                    break;
//                default:
//            }
//            System.out.println("Iteracion=" + f[i] + "\n\n\n\n\n\n");
//
//        }
//
//        System.out.println("LISTA USUARIOS UNIDOS= " + listaFinalQuedada.toString());
//        System.out.println("USUARIO CREADOR= " + creador.toString());
//
//        /*System.out.println(ekisde.toString() + "\n");
//        u1.setFechaNacimiento(Calendar.getInstance());
//        u1.setContrasena("jajamiranabos");
//        System.out.println(u1.toString());*/
//    }
    private static void joptionPrueba() {
    }

    private static void pruebaUsuarios() {
        UsuarioNoThread u1 = new UsuarioNoThread(1, "u1", "u1", "u1", "u1");
        //UsuarioNoThread u2 = new UsuarioNoThread(2, "u2", "u2", "us", "u2", Calendar.getInstance(), Calendar.getInstance(), "2", "u2", 2, 2, 2);

        System.out.println(u1.toStringCompleto() + "\n\n\n");
        //System.out.println(u2.toStringCompleto()+"\n\n\n");
    }

}
