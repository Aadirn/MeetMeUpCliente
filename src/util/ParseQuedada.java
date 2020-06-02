/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import modelo.Quedada;
import modelo.Usuario;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class ParseQuedada {

    private Usuario creador;
    private ArrayList<Usuario> userList = new ArrayList<Usuario>();

    public ParseQuedada() {
    }

    public Quedada parsearQuedada(String queadaString) {
        String[] primeraDivision = queadaString.split("#");

        for (int i = 0; i < primeraDivision.length; i++) {

            switch (i) {
                case 4:

                    String separar = primeraDivision[i];
                    //System.out.println(separar);
                    int inicio = separar.indexOf("[");
                    int fin = separar.indexOf("]");
                    String miniString = separar.substring(inicio + 1, fin).replace(" ", "");
                    System.out.println(miniString + "\n");
                    String[] otraDivision = miniString.split(",");
                    //System.out.println("imbecil que soy");
                    for (int j = 0; j < otraDivision.length; j++) {
                        //System.out.println("PITO");
                        String[] ultimaDivision = otraDivision[j].split("&");
                        System.out.println(ultimaDivision[0]);
                        int id = Integer.parseInt(ultimaDivision[0]);
                        String nick = ultimaDivision[1];
                        String nombre = ultimaDivision[2];
                        String ap1 = ultimaDivision[3];
                        String ap2 = ultimaDivision[4];
                        Usuario fin1 = Usuario.init(id, nick, nombre, ap1, ap2);
                        userList.add(fin1);

                        for (int k = 0; k < ultimaDivision.length; k++) {

                            System.out.println(ultimaDivision[k]);
                        }
                        System.out.println(otraDivision[j] + "\n");
                    }
                    break;
                case 5:
                    String[] usuarioCreador = primeraDivision[i].split("&");
                    int id = Integer.parseInt(usuarioCreador[0]);
                    String nick = usuarioCreador[1];
                    String nombre = usuarioCreador[2];
                    String ap1 = usuarioCreador[3];
                    String ap2 = usuarioCreador[4];
                    creador = Usuario.init(id, nick, nombre, ap1, ap2);

                    break;
                default:
            }
            System.out.println("Iteracion=" + primeraDivision[i] + "\n\n\n\n\n\n");

        }

        System.out.println("LISTA USUARIOS UNIDOS= " + userList.toString());
        System.out.println("USUARIO CREADOR= " + creador.toString());

        return null;
    }

}
