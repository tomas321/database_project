/*
 * Created by JFormDesigner on Sun Apr 16 23:18:07 CEST 2017
 */

package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Tomas Bellus
 */
public class Detail_window extends JFrame {
    public Detail_window() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tomas Bellus
        scrollPane1 = new JScrollPane();
        detail_textArea = new JTextArea();
        table_label = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- detail_textArea ----
            detail_textArea.setEditable(false);
            scrollPane1.setViewportView(detail_textArea);
        }

        //---- table_label ----
        table_label.setText("Table: ");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                        .addComponent(table_label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                    .addGap(109, 109, 109))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(table_label)
                    .addGap(35, 35, 35)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addGap(108, 108, 108))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Tomas Bellus
    private JScrollPane scrollPane1;
    private JTextArea detail_textArea;
    private JLabel table_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void setTable_labelText(String text) {
        this.table_label.setText(text);
    }

    public JTextArea getDetail_textArea() {
        return detail_textArea;
    }
}
