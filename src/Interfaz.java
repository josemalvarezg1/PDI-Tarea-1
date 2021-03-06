import java.awt.*;
import java.io.*;
import javax.swing.*;
       
/**
 *
 * @author José Manuel Alvarez García - CI 25038805
 */

public class Interfaz extends javax.swing.JFrame {

    Image bitmapImage = null; 
    JLabel label = new JLabel("",SwingConstants.CENTER);
    
    public Interfaz() {   
        //Inicialmente los botones no pueden ser seleccionados
        initComponents();
        negative.setEnabled(false);
        left90.setEnabled(false);
        left270.setEnabled(false);
        right90.setEnabled(false);
        right180.setEnabled(false);
        right270.setEnabled(false);
        verticalFlip.setEnabled(false);
        horizontalFlip.setEnabled(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        negative = new javax.swing.JButton();
        right90 = new javax.swing.JButton();
        left90 = new javax.swing.JButton();
        right180 = new javax.swing.JButton();
        right270 = new javax.swing.JButton();
        left270 = new javax.swing.JButton();
        browseFile = new javax.swing.JButton();
        imageName = new javax.swing.JLabel();
        verticalFlip = new javax.swing.JButton();
        horizontalFlip = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tarea #1 ");

        negative.setText("Negativo");
        negative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negativeActionPerformed(evt);
            }
        });

        right90.setText("Rotar 90º Derecha");
        right90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                right90ActionPerformed(evt);
            }
        });

        left90.setText("Rotar 90º Izquierda");
        left90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                left90ActionPerformed(evt);
            }
        });

        right180.setText("Rotar 180º");
        right180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                right180ActionPerformed(evt);
            }
        });

        right270.setText("Rotar 270º Derecha");
        right270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                right270ActionPerformed(evt);
            }
        });

        left270.setText("Rotar 270º Izquierda");
        left270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                left270ActionPerformed(evt);
            }
        });

        browseFile.setText("Examinar...");
        browseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseFileActionPerformed(evt);
            }
        });

        imageName.setText("Ninguna imagen cargada.");

        verticalFlip.setText("Espejo Vertical");
        verticalFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalFlipActionPerformed(evt);
            }
        });

        horizontalFlip.setText("Espejo Horizontal");
        horizontalFlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horizontalFlipActionPerformed(evt);
            }
        });

        scrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(browseFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageName)
                        .addGap(0, 488, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(left270, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(verticalFlip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(right270, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(right180, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(left90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(right90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(negative, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(horizontalFlip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseFile)
                    .addComponent(imageName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(negative, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(right90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(left90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(right180, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(right270, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(left270, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(verticalFlip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(horizontalFlip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseFileActionPerformed
        //Se carga la imagen examinándola desde un directorio
        bitmapImage = null;
        JFileChooser browsedFile = new JFileChooser();
        browsedFile.showOpenDialog(this);
        File file = browsedFile.getSelectedFile();    
        if (file != null) {
            String pathImageFile = file.getAbsolutePath();  
            imageName.setText(file.getName());  //Se muestra el nombre del archivo al cargarlo 
            try {
                bitmapImage = processImage.loadImage(pathImageFile,label);
            } catch (FileNotFoundException ex) {}        
            if (bitmapImage == null) {
                //Si la imagen cargada es corrupta o si se cargo algún otro archivo que no sea un bitmap de 1, 4, 8, 16 o 24 bits, se muestra un mensaje de error y los botones no pueden ser seleccionados
                label.setVisible(false);
                negative.setEnabled(false);
                left90.setEnabled(false);
                left270.setEnabled(false);
                right90.setEnabled(false);
                right180.setEnabled(false);
                right270.setEnabled(false);
                verticalFlip.setEnabled(false);
                horizontalFlip.setEnabled(false);            
            } else {   
                //En caso contrario, se muestra la imagen y se activan los botones
                label.setIcon(new ImageIcon(bitmapImage));        
                label.setVisible(true);
                scrollPane1.add(label);
                negative.setEnabled(true);
                left90.setEnabled(true);
                left270.setEnabled(true);
                right90.setEnabled(true);
                right180.setEnabled(true);
                right270.setEnabled(true);
                verticalFlip.setEnabled(true);
                horizontalFlip.setEnabled(true);        
            }
        }
    }//GEN-LAST:event_browseFileActionPerformed

    private void negativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negativeActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.invertImage(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label);  
        }
    }//GEN-LAST:event_negativeActionPerformed

    private void right90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_right90ActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.flip90DegreesRight(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_right90ActionPerformed

    private void left90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_left90ActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.flip90DegreesLeft(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_left90ActionPerformed

    private void right180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_right180ActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.flip180Degrees(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_right180ActionPerformed

    private void right270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_right270ActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.flip270DegreesRight(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_right270ActionPerformed

    private void left270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_left270ActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.flip270DegreesLeft(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_left270ActionPerformed

    private void verticalFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalFlipActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.verticalFlip(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_verticalFlipActionPerformed

    private void horizontalFlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horizontalFlipActionPerformed
        if (bitmapImage != null) {
            bitmapImage = processImage.horizontalFlip(processImage.toBufferedImage(bitmapImage));
            label.setIcon(new ImageIcon(bitmapImage));
            scrollPane1.add(label); 
        }
    }//GEN-LAST:event_horizontalFlipActionPerformed
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseFile;
    private javax.swing.JButton horizontalFlip;
    private javax.swing.JLabel imageName;
    private javax.swing.JButton left270;
    private javax.swing.JButton left90;
    private javax.swing.JButton negative;
    private javax.swing.JButton right180;
    private javax.swing.JButton right270;
    private javax.swing.JButton right90;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton verticalFlip;
    // End of variables declaration//GEN-END:variables
}
