/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;

/**
 *
 * @author Jarod
 */
public class PantallaTablero extends javax.swing.JFrame {

    /**
     * Creates new form PantallaTablero
     */
    public PantallaTablero() {
        
        initComponents();
        
    }
    
    public void _init_(int x, int y) {
        this.butArray = new JButton[n][n];
        this.fichasJugadores = new JButton[y][x];
        
        
        pnlFichas.setLayout(new GridLayout(n, n,1,1));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                butArray[i][j] = new JButton();
                butArray[i][j].setBackground(new Color(255,255,255));
                butArray[i][j].setPreferredSize(new Dimension(45,45));
                //butArray[i][j].setFont(new Font("Arial", Font.PLAIN, 7));
                pnlFichas.add(butArray[i][j]);
            }
            
        }
        pnlFichasJugadores.setLayout(new GridLayout(y, x,3,6));
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                fichasJugadores[i][j] = new JButton();
                fichasJugadores[i][j].setBackground(new Color(255,255,127));
                fichasJugadores[i][j].setPreferredSize(new Dimension(45,45));
                //butArray[i][j].setFont(new Font("Arial", Font.PLAIN, 7));
                pnlFichasJugadores.add(fichasJugadores[i][j]);
            }
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFichasJugadores = new javax.swing.JPanel();
        btnSiguiente = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        pnlFichas = new javax.swing.JPanel();
        lblTurno = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlFichasJugadores.setBackground(new java.awt.Color(240, 40, 240));

        javax.swing.GroupLayout pnlFichasJugadoresLayout = new javax.swing.GroupLayout(pnlFichasJugadores);
        pnlFichasJugadores.setLayout(pnlFichasJugadoresLayout);
        pnlFichasJugadoresLayout.setHorizontalGroup(
            pnlFichasJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        pnlFichasJugadoresLayout.setVerticalGroup(
            pnlFichasJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        btnSiguiente.setText("Siguiente");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        pnlFichas.setBackground(new java.awt.Color(140, 240, 240));

        javax.swing.GroupLayout pnlFichasLayout = new javax.swing.GroupLayout(pnlFichas);
        pnlFichas.setLayout(pnlFichasLayout);
        pnlFichasLayout.setHorizontalGroup(
            pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        pnlFichasLayout.setVerticalGroup(
            pnlFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(pnlFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(pnlFichasJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSiguiente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(pnlFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnSiguiente)
                                .addGap(38, 38, 38)
                                .addComponent(btnSalir)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addComponent(pnlFichasJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaTablero().setVisible(true);
            }
        });
    }

    public void setN(int n) {
        this.n = n;
    }

   
    
    
    
    public int n;
    public JButton[][] fichasJugadores;
    public JButton[][] butArray;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnSalir;
    public javax.swing.JButton btnSiguiente;
    public javax.swing.JLabel lblTurno;
    public javax.swing.JPanel pnlFichas;
    public javax.swing.JPanel pnlFichasJugadores;
    // End of variables declaration//GEN-END:variables
}
