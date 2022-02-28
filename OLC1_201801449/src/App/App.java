/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import Analizador.parser;
import Analizador.scanner;
import java.io.StringReader;
import java.util.LinkedList;
import Analizador.TLexemas;
import Analizador.TConjunto;
import Analizador.TExpresiones;
import Analizador.TError;

/**
 *
 * @author magdi
 */
public class App extends javax.swing.JFrame {
    // listas
   
    
    //variables para la lectura de archviso .exp
    public String ruta; // guarda la ruta
    public LineasText lines; // libreria para linea
    public String texto=""; // texto cargado desde archivo
    
    
    private static App instanciaMenu;

    public static App getInstancia() {
        if (instanciaMenu == null) {
            instanciaMenu = new App();
        }
        return instanciaMenu;
    }
    public App() {
        initComponents();
        this.setLocationRelativeTo(null);
         this.setTitle("PROYECTO 1 - ORGANIZACION DE LENGUAJES Y COMPILADORES 1");
        jPanel1.setLayout(new BorderLayout());
        lines = new LineasText();
        jPanel1.add(lines,BorderLayout.WEST);
        jPanel1.add(lines.scrollPane,BorderLayout.CENTER);
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jMenuItemGuardarcomo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Archivo de entrada", jPanel1);

        jButton1.setText("Generar Automata");

        jButton2.setText("Analizar Entrada");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Generar Archivo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu2.setText("Abrir");

        jMenuItemAbrir.setText("Abrir");
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemAbrir);

        jMenuItemGuardar.setText("Guardar");
        jMenu2.add(jMenuItemGuardar);

        jMenuItemGuardarcomo.setText("Guardar como");
        jMenuItemGuardarcomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuardarcomoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemGuardarcomo);

        jMenuBarPrincipal.add(jMenu2);

        setJMenuBar(jMenuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(560, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(234, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        analizarEntrada();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItemGuardarcomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarcomoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemGuardarcomoActionPerformed

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
        JFileChooser abrir = new JFileChooser();
        abrir.showOpenDialog(abrir);
        try{
            this.ruta = abrir.getSelectedFile().getAbsolutePath();
            FileInputStream archivo = new FileInputStream(ruta);
            DataInputStream entrada = new DataInputStream(archivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
            String linea="";
            while((linea = br.readLine())!=null){
                this.texto+= linea+"\n";
            }

            lines.pane.setText(texto);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "!Ocurrió un error al abrir el archivo!");
        }

    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    
    //**********************************METODOS***********************************
    public void analizarEntrada(){
        if(!"".equals(lines.pane.getText())){
            this.texto = lines.pane.getText();
            this.enviarAnalisis(texto);
        }else{
            JOptionPane.showMessageDialog(null, "No hay texto a analizar","Error En Area de Texto",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void enviarAnalisis(String texto){
        
        try{
            System.out.println("Iniciando analisis....");
            scanner scan = new scanner(new BufferedReader(new StringReader(texto)));
            parser parser = new parser(scan);
            parser.parse();
            System.out.println("Finaliza analisis...");
            System.out.println(parser.TablaLexema.get(0).getNombre());
            System.out.println(parser.TablaExpresion.get(0).getExpresion());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemGuardarcomo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables
}
