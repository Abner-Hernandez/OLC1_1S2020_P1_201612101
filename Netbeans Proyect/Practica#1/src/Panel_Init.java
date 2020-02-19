import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Abner Hernandez
 */

public class Panel_Init extends javax.swing.JFrame {
    
    //local variables
    private String path_file;
    private ArrayList<Regular_Expression> regular_expre;
    private ArrayList<Set> sets;
    private ArrayList<Expression> lexemes;
    private int index_lexemes;
    
    
    public Panel_Init() {
        regular_expre = new ArrayList<>();
        sets = new ArrayList<>();
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Principal_Pane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        input_field = new javax.swing.JTextArea();
        input_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        generate_automatons = new javax.swing.JButton();
        analyze_input = new javax.swing.JButton();
        img_panel = new javax.swing.JPanel();
        scroll_image = new javax.swing.JScrollPane();
        image_panel = new javax.swing.JLabel();
        expression_regular = new javax.swing.JComboBox<>();
        expression_label = new javax.swing.JLabel();
        image_type_label = new javax.swing.JLabel();
        images_type = new javax.swing.JComboBox<>();
        graph_image = new javax.swing.JButton();
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

        console.setColumns(20);
        console.setRows(5);
        jScrollPane2.setViewportView(console);

        jLabel1.setText("OutPut Console ");

        generate_automatons.setText("Generate Automatons");
        generate_automatons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_automatonsActionPerformed(evt);
            }
        });

        analyze_input.setText("Analyze Input");
        analyze_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyze_inputActionPerformed(evt);
            }
        });

        scroll_image.setViewportView(image_panel);

        javax.swing.GroupLayout img_panelLayout = new javax.swing.GroupLayout(img_panel);
        img_panel.setLayout(img_panelLayout);
        img_panelLayout.setHorizontalGroup(
            img_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll_image, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );
        img_panelLayout.setVerticalGroup(
            img_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(img_panelLayout.createSequentialGroup()
                .addComponent(scroll_image, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        expression_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        expression_label.setText("Select an expression regular");

        image_type_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        image_type_label.setText("Select an image type");

        images_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tree", "Follows", "Transitions", "Automaton" }));

        graph_image.setText("Graph");
        graph_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graph_imageActionPerformed(evt);
            }
        });

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
                            .addComponent(input_label)
                            .addComponent(jLabel1)
                            .addGroup(Principal_PaneLayout.createSequentialGroup()
                                .addComponent(generate_automatons, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(analyze_input, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(img_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Principal_PaneLayout.createSequentialGroup()
                                .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expression_regular, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(expression_label))
                                .addGap(18, 18, 18)
                                .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(image_type_label)
                                    .addGroup(Principal_PaneLayout.createSequentialGroup()
                                        .addComponent(images_type, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(graph_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        Principal_PaneLayout.setVerticalGroup(
            Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Principal_PaneLayout.createSequentialGroup()
                .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Principal_PaneLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(input_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generate_automatons)
                            .addComponent(analyze_input)))
                    .addGroup(Principal_PaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expression_label)
                            .addComponent(image_type_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Principal_PaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expression_regular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(images_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(graph_image))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(img_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void generate_automatonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_automatonsActionPerformed
        String content = input_field.getText();
        String lexical_expression = "", pattern;
        int size_content = content.length(), pointer = 0, initial_pointer = 0;
        boolean expression = false, set = false;
        
        if(content.charAt(pointer) == '{')
            pointer += 1;
        while(true)
        {
            if(content.charAt(pointer) == '\n')
                break;
            pointer += 1;
        }
        initial_pointer += pointer + 1;
        
        while(true)
        {
            if(content.substring(pointer, pointer+2).equals("//"))
            {
                while(true)
                {
                    if(content.charAt(pointer) == '\n')
                        break;
                    pointer += 1;
                }
                pointer += 1;
                initial_pointer = pointer;
            }else if(content.substring(pointer, pointer+2).equals("<!"))
            {
                while(true)
                {
                    if(content.substring(pointer,pointer+2).equalsIgnoreCase("!>"))
                        break;
                     pointer += 1;
                }
                pointer += 2;
                initial_pointer = pointer;
                while(content.charAt(pointer) == ' ')
                    pointer += 1;
                if(content.charAt(pointer) == '\n')
                    initial_pointer += 1;
            }else if(content.charAt(pointer) == ' ')
            {
                pointer += 1;
                continue;
            }if(content.substring(pointer, pointer + 5).equalsIgnoreCase("conj:"))
            {
                pointer += 5;
                while(content.charAt(pointer) == ' ')
                    pointer += 1;
                initial_pointer = pointer;
                set = true;
            }else if(content.substring(pointer, pointer + 2).equals("->") && expression == false)
            {
                expression = true;
                lexical_expression = "";
                
                while(true)
                {
                    if(pointer == initial_pointer || content.charAt(initial_pointer) == ' ')
                        break;
                    
                    lexical_expression += content.charAt(initial_pointer);
                    initial_pointer += 1;
                }
                pointer += 2;
                while(content.charAt(pointer) == ' ')
                    pointer += 1;
                initial_pointer = pointer;
            }else if(expression && content.charAt(pointer) == ';')
            {
                expression = false;
                pattern = content.substring(initial_pointer, pointer);
                initial_pointer = pointer+1;
                if(set == true)
                    sets.add(new Set(pattern, lexical_expression));
                else
                    regular_expre.add(new Regular_Expression(pattern, lexical_expression));
                set = false;
                
                while(true)
                {
                    if(content.charAt(pointer) == '\n')
                        break;
                    pointer += 1;
                }
                initial_pointer = pointer + 1;
            }   

            pointer +=1;
            if(content.substring(pointer, pointer + 2).equals("%%") && expression == false || size_content <= pointer)
                break;
        }
        pointer += 2;
        while(pointer <= size_content && !content.substring(pointer, pointer + 2).equals("%%"))
            pointer += 1;
        pointer += 2;
        while(true)
        {
            if(content.charAt(pointer) != '\n' || content.charAt(pointer) != ' ')
                break;
            pointer += 1;
        }
        index_lexemes = pointer + 1;
        for (Regular_Expression reg : regular_expre) {
            expression_regular.addItem(reg.lexical_component);
            reg.generate_tree_expression();
        }
        for (Set set1 : sets) {
            set1.analize_pattern();
        }
    }//GEN-LAST:event_generate_automatonsActionPerformed

    private void graph_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graph_imageActionPerformed
        if(expression_regular.getSelectedIndex() != -1)
        {
            ImageIcon image = null;
            String regular_exp = expression_regular.getSelectedItem().toString();
            String type_image = images_type.getSelectedItem().toString();
            for (Regular_Expression rp : regular_expre) {
                if(rp.lexical_component.equals(regular_exp))
                {
                    if(type_image.equalsIgnoreCase("tree"))
                        rp.tree_graph();
                    else if(type_image.equalsIgnoreCase("follows"))
                        rp.graph_table_follows();
                    else if(type_image.equalsIgnoreCase("transitions"))
                        rp.graph_table_transitions();
                    else if(type_image.equalsIgnoreCase("automaton"))
                        rp.graph_automaton();
                }
            }

            for (Regular_Expression regular_Expression : regular_expre) {
                if(regular_Expression.lexical_component.equals(expression_regular.getItemAt(expression_regular.getSelectedIndex())))
                {
                    switch (images_type.getSelectedIndex()) {
                        case 0:
                            image = new ImageIcon(Init.folder + "\\" + regular_Expression.lexical_component +"_tree.jpg");
                            break;
                        case 1:
                            image = new ImageIcon(Init.folder + "\\" + regular_Expression.lexical_component +"_follows.jpg");
                            break;
                        case 2:
                            image = new ImageIcon(Init.folder + "\\" + regular_Expression.lexical_component +"_transitions.jpg");
                            break;
                        case 3:
                            image = new ImageIcon(Init.folder + "\\" + regular_Expression.lexical_component +"_automaton.jpg");
                            break;
                    }

                }
            }

            image.getImage().flush();
            image_panel.setIcon(image);        
        }        
    }//GEN-LAST:event_graph_imageActionPerformed

    private void analyze_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyze_inputActionPerformed
        this.lexemes = new ArrayList<>();
        String content = input_field.getText();
        int size_content = content.length(), initial_pointer = index_lexemes, index = index_lexemes;
        String lexical = "";

        while(true)
        {
            char dat = content.charAt(index);
            switch (dat) {
                case ':':
                    for (int i = initial_pointer; i < index; i++) {
                        if(content.charAt(i) != ' ')
                            lexical += content.charAt(i);
                    }   
                    initial_pointer = index + 1;
                    break;
                case '"':
                    initial_pointer = ++index;
                    while(content.charAt(index) != '"')
                    {
                        index++;
                    }
                    String n = content.substring(initial_pointer, index);
                    lexemes.add(new Expression(lexical,content.substring(initial_pointer, index)));

                    break;
                case '\n':
                    initial_pointer = index + 1;
                    break;
            }
            if(dat == ';')
            {
                lexical = "";
                initial_pointer = index + 1; 
            }
            
            index++;
            if(index == size_content)
                break;
        }
        
        //analize expressions
        analize_Expressions();
        
    }//GEN-LAST:event_analyze_inputActionPerformed

    private void analize_Expressions()
    {
        for (Expression lexeme : lexemes) {
            
            while (true) {                
                Regular_Expression rp = get_expression(lexeme.lexical_component);
                if(rp.lexical_component.equals(""))
                {
                    String txt = console.getText();
                    console.setText(txt + "there isn't a expression to match with the lexical component:" + lexeme.lexical_component + "\n");
                }else
                {
                    rp.analize_lexeme(lexeme.lexeme);
                    break;
                }
            }
        }
    }
    
    private Regular_Expression get_expression(String lexical_component)
    {
        Regular_Expression d = new Regular_Expression("", "");
        for (Regular_Expression rp : regular_expre) {
            if(rp.lexical_component.equals(lexical_component))
                return rp;
        }
        return d;
    }
    
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
    
    public boolean verificate_char_in_set(String terminal, char character)
    {
        for (Set set : sets) {
            if(set.lexical_component.equals(terminal))
                return set.check_element(character);
        }
        return false;
    }
    
    public boolean verificate_if_set(String lexical_component)
    {
        for (Set set : sets) {
            if(set.lexical_component.equals(lexical_component))
                return true;
        }
        return false;
    }
    
    public void write_console(String messaje)
    {
        String txt = console.getText();
        console.setText(txt+messaje+"\n");
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
    private javax.swing.JTextArea console;
    private javax.swing.JLabel expression_label;
    private javax.swing.JComboBox<String> expression_regular;
    private javax.swing.JMenu file_menu;
    private javax.swing.JMenuItem generate_XML;
    private javax.swing.JButton generate_automatons;
    private javax.swing.JButton graph_image;
    private javax.swing.JLabel image_panel;
    private javax.swing.JLabel image_type_label;
    private javax.swing.JComboBox<String> images_type;
    private javax.swing.JPanel img_panel;
    private javax.swing.JTextArea input_field;
    private javax.swing.JLabel input_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem save_as;
    private javax.swing.JScrollPane scroll_image;
    // End of variables declaration//GEN-END:variables
}
