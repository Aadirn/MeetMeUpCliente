/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.PrintWriter;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class ThreadActualizar extends Thread {

    private Usuario user;
    private static final ThreadActualizar instancia = new ThreadActualizar();

    private ThreadActualizar() {
    }

    public static ThreadActualizar init() {
        return instancia;
    }

    public static ThreadActualizar init(Usuario user) {
        instancia.user = user;
        return instancia;
    }

    @Override
    public void run() {
    }

    public void actualizar(Usuario userExt) {

        PrintWriter pW = userExt.getSalidaInfo();
        System.out.println("Thread actualizar==>" + userExt.getBiografia());
        System.out.println("Thread actualizar==>" + userExt.toStringCompletoAlmohadilla());
        pW.print("actualiza%" + userExt.toStringCompletoAlmohadilla() + "\r\n");
        pW.flush();

    }

}
