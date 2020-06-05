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
public class ThreadAuxSwing extends Thread {

    private Usuario user;
    private static final ThreadAuxSwing instancia = new ThreadAuxSwing();
    private boolean finalizar;

    private ThreadAuxSwing() {
    }

    public static ThreadAuxSwing init() {
        return instancia;
    }

    public static ThreadAuxSwing init(Usuario user) {
        instancia.user = user;
        return instancia;
    }

    @Override
    public void run() {
        finalizar = true;
        while (finalizar) {
            
            //System.out.println("-----------------------Funciono--------------------------\n");

        }

    }

    public void actualizar(Usuario userExt) {

        PrintWriter pW = userExt.getSalidaInfo();
        System.out.println("Thread actualizar==>" + userExt.getBiografia());
        System.out.println("Thread actualizar==>" + userExt.toStringCompletoAlmohadilla());
        pW.print("actualiza%" + userExt.toStringCompletoAlmohadilla() + "\r\n");
        pW.flush();

    }

    public void crearQuedada(Usuario userExt, Quedada quedada) {

        PrintWriter pW = userExt.getSalidaInfo();
        System.out.println("Thread quedada==>" + quedada.toStringSinLista());
        pW.print("quedada%" + quedada.toStringSinLista() + "\r\n");
        pW.flush();
        //finalizar=false;

    }

    public void finalizar() {
        finalizar = false;
    }

}
