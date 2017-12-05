package windows;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JOptionPane;

public class Loading extends javax.swing.JFrame implements Runnable {

    Thread t;
    Component p;
    File path;
    String objective;

    public Loading(Component parent, File source, File dest) {
        p = parent;
        path = source;
        objective = dest.getAbsolutePath();
        dest.mkdirs();
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        this.setVisible(true);
        cargar();
        p.setEnabled(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        progress = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generando nuevos cursos...");
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);
        setResizable(false);

        bar.setToolTipText("");

        jLabel1.setText("Extracting... (999/999)");

        progress.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel1)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargar() {
        try {
            String[] names = path.list();
            bar.setMaximum(names.length);
            for (int i = 0; i < names.length; i++) {
                try {
                    names[i] = names[i].split(" ", 2)[1];
                } catch (Exception e) {
                }
            }
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                bar.setValue(i);
                File[] data = files[i].listFiles();
                boolean first = true;
                int cont = 1;
                for (int j = 0; j < data.length; j++) {
                    if (data[j].isDirectory()) {
                        File[] subData = data[j].listFiles();
                        for (int k = 0; k < subData.length; k++) {
                            if (subData[k].getAbsolutePath().contains(".mp3")) {
                                if (first) {
                                    try {
                                        progress.setText("Copying " + names[i]);
                                        jLabel1.setText("Extracting... (" + i + "/" + files.length + ")");
                                        copyFile(subData[k], new File(objective + "\\" + names[i] + ".mp3"));
                                        first = false;
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    try {
                                        progress.setText("Copying " + names[i] + " (" + cont + ")");
                                        jLabel1.setText("Extracting... (" + i + "/" + files.length + ")");
                                        copyFile(subData[k], new File(objective + "\\" + names[i] + " (" + cont++ + ")" + ".mp3"));
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    } else if (data[j].getAbsolutePath().contains(".mp3")) {
                        if (first) {
                            try {
                                progress.setText("Copying " + names[i]);
                                jLabel1.setText("Extracting... (" + i + "/" + files.length + ")");
                                copyFile(data[j], new File(objective + "\\" + names[i] + ".mp3"));
                                first = false;
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            try {
                                progress.setText("Copying " + names[i] + " (" + cont + ")");
                                jLabel1.setText("Extracting... (" + i + "/" + files.length + ")");
                                copyFile(data[j], new File(objective + "\\" + names[i] + " (" + cont++ + ")" + ".mp3"));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }

    private void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        if (source.length() > 147000) {
            if (!dest.isFile()) {
                is = new FileInputStream(source);
                try {
                    os = new FileOutputStream(dest);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                } finally {
                    if (os != null) {
                        os.close();
                    }
                }
            } else {
                progress.setText("Already Extracted!");
            }
        } else {
            progress.setText("File Ignored!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel progress;
    // End of variables declaration//GEN-END:variables
}
