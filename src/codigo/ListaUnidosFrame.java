/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import dao.UsuarioDaoImp;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import modelo.UsuarioNoThread;

/**
 *
 * @author VSPC-ETERNALSTORM2V5
 */
public class ListaUnidosFrame extends javax.swing.JFrame {

    private ArrayList<UsuarioNoThread> unidos = new ArrayList<>();
    private DefaultListModel<String> dLM = new DefaultListModel<>();
    private int id;
    private String nick;

    /**
     * Creates new form ListaUnidosFrame
     */
    public ListaUnidosFrame() {
        initComponents();
    }

    public ListaUnidosFrame(ArrayList<UsuarioNoThread> unidos) {
        initComponents();
        centrar();
        this.unidos = unidos;
        crearLista();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listUnidos = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        listUnidos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUnidosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listUnidos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listUnidosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUnidosValueChanged
        if (!evt.getValueIsAdjusting()) {
            System.out.println("HE SELECCIONADO A " + listUnidos.getSelectedValue());
            UsuarioNoThread user = obtenerDeBd();
            PerfilAjenoFrame pAF = new PerfilAjenoFrame(user);
            pAF.setVisible(true);
        } else {
        }
    }//GEN-LAST:event_listUnidosValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaUnidosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaUnidosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaUnidosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaUnidosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaUnidosFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listUnidos;
    // End of variables declaration//GEN-END:variables

    private void crearLista() {
        if (!unidos.isEmpty()) {
            String primera = unidos.toString().replace('[', ' ');
            String segunda = primera.replace(']', ' ');
            String tercera = segunda.replaceAll(" ", "");
            System.out.println(tercera);

            String[] division = tercera.split(",");
            for (int i = 0; i < division.length; i++) {
                String[] f2 = division[i].split("=");
                id = Integer.parseInt(f2[0]);
                nick = f2[1];
                dLM.add(i, nick);
            }

            listUnidos.setModel(dLM);
        } else {
            listUnidos.setModel(dLM);
        }

    }

    private UsuarioNoThread obtenerDeBd() {
        Statement stm = null;
        ResultSet rs = null;
        String fecha;
        String vecesValorado;
        String totalValorado;

        String sql = "SELECT id_usuario,nick_usuario,AES_DECRYPT(password_usuario, 'admin'),nombre_usuario,apellido1_usuario,apellido2_usuario,fecha_creacion_usuario,fecha_nacimiento_usuario,usuarios_seguidos,num_usuarios_seguidos,valoracion_total,veces_valorado,biografia from usuarios WHERE id_usuario=" + id;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/meetmeup", "root", "1234");
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if (rs.next()) {
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

                vecesValorado = rs.getString("veces_valorado");
                u.setVecesValorado(Integer.parseInt(vecesValorado));

                totalValorado = rs.getString("valoracion_total");
                u.setTotalValoraciones(Integer.parseInt(totalValorado));

                String valoracion = calcularValoracion(totalValorado, vecesValorado);
                u.setValoracion(Float.parseFloat(valoracion));
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

    private void centrar() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private String calcularValoracion(String valoracionTotal, String vecesValorado) {
        System.out.println("Calcula Valoracion-->" + valoracionTotal);
        if ("0".equals(valoracionTotal) && "0".equals(vecesValorado)) {
            System.out.println("No hay valoraciones\n");
            return "0";
        }
        float valoracionTotalFloat = Float.parseFloat(valoracionTotal);
        float vecesValoradoFloat = Float.parseFloat(vecesValorado);

        float valoracion = valoracionTotalFloat / vecesValoradoFloat;
        String valoracionString = valoracion + "";
        System.out.println(valoracionString + "\n");

        return valoracionString;

    }
}
