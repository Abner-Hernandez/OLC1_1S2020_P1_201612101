import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Abner Hernandez
 */
public class Panel_Init extends javax.swing.JFrame {
    
    public Panel_Init() {
        initComponents();
    }

    //local variables
    private String path_file;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Principal_Pane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        input_field = new javax.swing.JTextArea();
        input_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        generate_automatons = new javax.swing.JButton();
        analyze_input = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        file_menu = new javax.swing.JMenu();
        open = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        save_as = new javax.swing.JMenuItem();
        generate_XML = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        input_field.setColumns(20);
        input_field.setRows(5);
        jScrollPane1.setViewportView(input_field);

        input_label.setText("Input File");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel1.setText("OutPut Console ");

        generate_automatons.setText("Generate Automatons");

        analyze_input.setText("Analyze Input");

        javax.swing.GroupLayout Principal_PaneLayout = new javax.swing.GroupLayout(Principal_Pane);
        Principal_Pane.setLayout(Principal_PaneLayout);
        Principal_PaneLayout.setHorizontalGroup(
            Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Principal_PaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(Principal_PaneLayout.createSequentialGroup()
                        .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(input_label)
                            .addComponent(jLabel1)
                            .addGroup(Principal_PaneLayout.createSequentialGroup()
                                .addComponent(generate_automatons, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(analyze_input, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 361, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Principal_PaneLayout.setVerticalGroup(
            Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Principal_PaneLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(input_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generate_automatons)
                    .addComponent(analyze_input))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        file_menu.setText("File");

        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        file_menu.add(open);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        file_menu.add(save);

        save_as.setText("Save As");
        save_as.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_asActionPerformed(evt);
            }
        });
        file_menu.add(save_as);

        generate_XML.setText("Generate XML");
        file_menu.add(generate_XML);

        jMenuBar1.add(file_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        open_file();
    }//GEN-LAST:event_openActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(path_file != null)
            save_file(path_file);
        else
            JOptionPane.showMessageDialog(null, "The file doesn't exist, you need to save as it", "Warning!",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_saveActionPerformed

    private void save_asActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_asActionPerformed
        JFileChooser file=new JFileChooser();
        file.showSaveDialog(this);
        File file_save = file.getSelectedFile();
        save_file(file_save.toString() + ".er");
        path_file = file_save.toString() + ".er";
    }//GEN-LAST:event_save_asActionPerformed

    private void open_file()
    {
        String aux = "";   
        String text = "";
        try
        {
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("er", "er");
        file.setFileFilter(filter);
        file.showOpenDialog(this);
         
        /**open selected file*/
        File abre = file.getSelectedFile();
        path_file = abre.toString();

        if(abre != null)
        {     
            FileReader archivos = new FileReader(abre);
            BufferedReader lee = new BufferedReader(archivos);
            while((aux = lee.readLine())!=null)
            {
                text += aux+ "\n";
            }
            lee.close();
        }    
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,ex+"" + "\nCould not open file", "Warning!",JOptionPane.WARNING_MESSAGE);
        }
        input_field.setText(text);
    }
    
    private void save_file(String name_file)
    {
        try
        {
            if(name_file != null)
            {
                FileWriter file_save = new FileWriter(name_file);
                file_save.write(input_field.getText());
                file_save.close();
                JOptionPane.showMessageDialog(null, "The file has been saved", "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(HeadlessException | IOException ex)
        {
            JOptionPane.showMessageDialog(null, "The file hasn't been saved", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    
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
            java.util.logging.Logger.getLogger(Panel_Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel_Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel_Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel_Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel_Init().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Principal_Pane;
    private javax.swing.JButton analyze_input;
    private javax.swing.JMenu file_menu;
    private javax.swing.JMenuItem generate_XML;
    private javax.swing.JButton generate_automatons;
    private javax.swing.JTextArea input_field;
    private javax.swing.JLabel input_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem save_as;
    // End of variables declaration//GEN-END:variables
}
