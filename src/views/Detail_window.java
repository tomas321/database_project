/*
 * Created by JFormDesigner on Sun Apr 16 23:18:07 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.ActionListener;
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

    public void addUdate_buttonListener(ActionListener a) { this.update_button.addActionListener(a); }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tomas Bellus
        scrollPane1 = new JScrollPane();
        detail_textArea = new JTextArea();
        table_label = new JLabel();
        label1 = new JLabel();
        edit_comboBox = new JComboBox();
        edit_textField = new JTextField();
        update_button = new JButton();

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

        //---- label1 ----
        label1.setText("EDIT");

        //---- update_button ----
        update_button.setText("update");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(18, 18, 18)
                            .addComponent(edit_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(edit_textField, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(update_button)
                            .addContainerGap(264, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                .addComponent(table_label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                            .addGap(109, 109, 109))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap(382, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(edit_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(update_button)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(table_label)
                            .addGap(35, 35, 35)
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label1)
                                .addComponent(edit_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addGap(70, 70, 70))
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
    private JLabel label1;
    private JComboBox edit_comboBox;
    private JTextField edit_textField;
    private JButton update_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void setTable_labelText(String text) {
        this.table_label.setText(text);
    }

    public String getTable() {
        return table_label.getText();
    }

    public JComboBox getEdit_comboBox() {
        return edit_comboBox;
    }

    public Object getEdit_textField() {
        return edit_textField.getText();
    }

    public JTextArea getDetail_textArea() {
        return detail_textArea;
    }

    public JButton getUpdate_button() {
        return update_button;
    }

    public void setEdit_comboBox(String[] items) {
        this.edit_comboBox.removeAllItems();
        for (int i = 0; i < items.length; i++)
            this.edit_comboBox.addItem(items[i]);
    }
}
