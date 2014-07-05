/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Пётр
 */
public class PointlessParametres extends javax.swing.JFrame {

    /**
     * Creates new form PointlessParametres
     */
     PointlessColor playerColorDialog; //диалог выбора цвета игрока
     int playerNumber;
    
    public PointlessParametres() {
        playerNumber = 0;
        initComponents();
        setLocationRelativeTo(null);
        if (jTextField1.getText().length() == 0 && jTextField2.getText().length() == 0)
            jButton1.setEnabled(false);
        else jButton1.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Введите необходимые данные");

        jLabel2.setText("Игрок 1");

        jLabel3.setText("Имя:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel4.setText("Цвет:");

        jLabel5.setText("Цвет:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel6.setText("Имя:");

        jLabel7.setText("Игрок 2");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setToolTipText("");
        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });

        jButton1.setText("Сохранить");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jButton1)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        playerColorDialog = new PointlessColor();
        playerNumber = 1;
        playerColorDialog.setVisible(true);
        
        if (jTextField1.getText().length() != 0 && jTextField2.getText().length() != 0) 
            jButton1.setEnabled(true);
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        playerColorDialog = new PointlessColor();
        playerNumber = 2;
        playerColorDialog.setVisible(true);
        
        if (jTextField1.getText().length() != 0 && jTextField2.getText().length() != 0)   
            jButton1.setEnabled(true);
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //если пользователь ввёл имена пользователей и выбрал цвет
            PointlessInterface.player1Color = jTextField3.getBackground(); //устанавливаем цвета
            PointlessInterface.player2Color = jTextField4.getBackground(); //игрокам
            
            PointlessInterface.player1Name = jTextField1.getText(); //устанавливаем имена
            PointlessInterface.player2Name = jTextField2.getText(); //игрокам
            
            if (jTextField3.getBackground().equals(jTextField4.getBackground())) {
                JOptionPane.showMessageDialog(null,"Уоу-уоу-уоу, парни, полегче. В нашей игре два одинаковых цвета не могут вступать в отношения."); //лойс :D
            }
            else {
            try {
                File configFile = new File("config.ini");
                FileWriter wrt = new FileWriter(configFile);
                wrt.write(PointlessInterface.player1Name+"\r\n");
                wrt.write(jTextField3.getBackground()+"\r\n");
                wrt.write(PointlessInterface.player2Name+"\r\n");
                wrt.write(jTextField4.getBackground()+"\r\n");
                wrt.close();
            } catch (IOException ex) {
                Logger.getLogger(PointlessParametres.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!PointlessInterface.isOnlyOptionFlag){
                PointlessInterface.flagToStartTheGame = true;   //сообщаем, что можно начинать игру
            }
            dispose();  //и завершаемся, мы больше не нужны =(
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        if (jTextField1.getText().length() == 0 && jTextField2.getText().length() == 0)  
            jButton1.setEnabled(false);
        else 
            jButton1.setEnabled(true);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        if (jTextField1.getText().length() == 0 && jTextField2.getText().length() == 0)  
            jButton1.setEnabled(false);
        else 
            jButton1.setEnabled(true);
    }//GEN-LAST:event_jTextField2KeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // ебаать сколько кода о_0
        try {
             FileInputStream stringReader = new FileInputStream("config.ini");
             InputStreamReader inputReader = new InputStreamReader(stringReader);
             BufferedReader reader = new BufferedReader(inputReader);
             
             try {
                 jTextField1.setText(reader.readLine()); 
                 PointlessInterface.player1Name = jTextField1.getText(); //устанавливаем имена
                 
                 // Получение цвета первого игрока
                 StringBuffer s = new StringBuffer (reader.readLine());
                 String red = new String();
                 String green = new String();
                 String blue = new String();
                 Integer r = new Integer(0);
                 Integer g = new Integer(0);
                 Integer b = new Integer(0);
                 
                 s.delete(0, 17);
                 int i=0;
                 while (s.charAt(i) != ',') {
                     red = red + s.charAt(i);
                     i++;
                 }
                 
                 s.delete(0, i+3);
                 
                 i=0;
                 while (s.charAt(i) != ',') {
                     green = green + s.charAt(i);
                     i++;
                 }
                 s.delete(0, i+3);
                 
                 i=0;
                 while (s.charAt(i) != ']') {
                     blue = blue + s.charAt(i);
                     i++;
                 }
                 
                 r = Integer.parseInt(red);
                 g = Integer.parseInt(green);
                 b = Integer.parseInt(blue);
                 
                 Color clr = new Color (r,g,b);
                 
                 jTextField3.setBackground(clr);
                 // Получение законечно
                 
                 jTextField2.setText(reader.readLine()); 
                 PointlessInterface.player1Name = jTextField2.getText();
                 
                 // Получение цвета второго игрока
                    
                 red = new String();
                 green = new String();
                 blue = new String();
                 
                 
                 s = new StringBuffer(reader.readLine());
                 s.delete(0, 17);
                 i=0;
                 while (s.charAt(i) != ',') {
                     red = red + s.charAt(i);
                     i++;
                 }
                 
                 s.delete(0, i+3);
                 
                 i=0;
                 while (s.charAt(i) != ',') {
                     green = green + s.charAt(i);
                     i++;
                 }
                 s.delete(0, i+3);
                 
                 i=0;
                 while (s.charAt(i) != ']') {
                     blue = blue + s.charAt(i);
                     i++;
                 }
                 
                 r = Integer.parseInt(red);
                 g = Integer.parseInt(green);
                 b = Integer.parseInt(blue);
                 
                 clr = new Color(r,g,b);
                 
                 jTextField4.setBackground(clr);
                 // Закончено
                 
                 // Добавим всё это в панельку справа
                 jButton1.setEnabled(true);
                 
             } catch (IOException ex) {
                 Logger.getLogger(PointlessParametres.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         } catch (FileNotFoundException ex) {
             Logger.getLogger(PointlessParametres.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(PointlessParametres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PointlessParametres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PointlessParametres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PointlessParametres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PointlessParametres().setVisible(true);
            }
        });
        
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
