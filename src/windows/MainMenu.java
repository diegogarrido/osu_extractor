package windows;

import java.io.File;
import javax.swing.JFileChooser;

public class MainMenu extends javax.swing.JFrame {

    File sourceD = new File("");
    File destinationD = new File("");

    public MainMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        sourceText = new javax.swing.JTextField();
        destinationText = new javax.swing.JTextField();
        source = new javax.swing.JButton();
        destination = new javax.swing.JButton();
        go = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Osu! mp3 Extractor");
        setResizable(false);

        sourceText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sourceText.setText("Default is in: AppData\\Local\\osu!");

        destinationText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        destinationText.setText("Destination");

        source.setText("Osu! Folder");
        source.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceActionPerformed(evt);
            }
        });

        destination.setText("Destination");
        destination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinationActionPerformed(evt);
            }
        });

        go.setText("Go!");
        go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/windows/Osu!_logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(go, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(destination, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(source, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sourceText)
                            .addComponent(destinationText, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(source, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sourceText))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(destination)
                    .addComponent(destinationText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(go, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceActionPerformed
        jFileChooser1.setDialogTitle("Select osu! folder");
        jFileChooser1.showOpenDialog(this);
        String s = "null";
        try {
            s = "" + jFileChooser1.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {
        }
        if (!s.equals("null")) {
            sourceD = new File(s + "/songs");
            sourceText.setText(sourceD.getAbsolutePath());
        }
    }//GEN-LAST:event_sourceActionPerformed

    private void destinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinationActionPerformed
        jFileChooser1.setDialogTitle("Select destination (will create a new folder named Songs)");
        jFileChooser1.showOpenDialog(this);
        String s = "null";
        try {
            s = "" + jFileChooser1.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {
        }
        if (!s.equals("null")) {
            destinationD = new File(s + "/Songs");
            destinationText.setText(destinationD.getAbsolutePath());
        }
    }//GEN-LAST:event_destinationActionPerformed

    private void goActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goActionPerformed
        this.setEnabled(false);
        new Thread(new Loading(this, sourceD, destinationD)).start();
    }//GEN-LAST:event_goActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton destination;
    private javax.swing.JTextField destinationText;
    private javax.swing.JButton go;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton source;
    private javax.swing.JTextField sourceText;
    // End of variables declaration//GEN-END:variables
}
