/*
 * Created by JFormDesigner on Sat Apr 08 20:59:34 CEST 2017
 */

package views;

import sun.applet.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author unknown
 */
public class Main_window extends JFrame {
    private DefaultTableModel table_content_model;

    public Main_window() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        this.content_table.removeEditor();
    }

    public void addTable_jcomboboxListener(ItemListener i) { this.table_jcombobox.addItemListener(i); }
    public void addChoose_table_buttonListener(ActionListener a) { this.choose_table_button.addActionListener(a); }
    public void addSearch_buttonListener(ActionListener a) { this.search_button.addActionListener(a); }
    public void addAddItem_buttonListener(ActionListener a) { this.addItem_button.addActionListener(a); }
    public void addTable_contentMouseListener(MouseAdapter m) { this.content_table.addMouseListener(m); }
    public void addDelete_buttonListener(ActionListener a) { this.deleteItem_button.addActionListener(a); }

    private void table_contentValueChanged(ListSelectionEvent e) {
        // TODO add your code here
    }

    private void table_jcomboboxItemStateChanged(ItemEvent e) {
        // TODO add your code here
    }

    private void choose_table_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void search_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void addItem_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void deleteItem_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        table_content_model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // restrict table editing
                return false;
            }
        };
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Stefan Zralok
        table_jscrollpane = new JScrollPane();
        content_table = new JTable(table_content_model);
        table_label = new JLabel();
        table_jcombobox = new JComboBox<>();
        choose_table_button = new JButton();
        page_label = new JLabel();
        search_textfield = new JTextField();
        search_label = new JLabel();
        search_combobox = new JComboBox();
        search_button = new JButton();
        addItem_button = new JButton();
        deleteItem_button = new JButton();
        results_label = new JLabel();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== table_jscrollpane ========
        {
            table_jscrollpane.setViewportView(content_table);
        }

        //---- table_label ----
        table_label.setText("Table");

        //---- table_jcombobox ----
        table_jcombobox.setModel(new DefaultComboBoxModel<>(new String[] {
            "<select>",
            "Estates",
            "Locations",
            "Arrangements",
            "Open houses",
            "Clients",
            "Agents",
            "Sold estates"
        }));
        table_jcombobox.addItemListener(e -> table_jcomboboxItemStateChanged(e));

        //---- choose_table_button ----
        choose_table_button.setText("display");
        choose_table_button.addActionListener(e -> choose_table_buttonActionPerformed(e));

        //---- page_label ----
        page_label.setHorizontalAlignment(SwingConstants.CENTER);

        //---- search_label ----
        search_label.setText("search");

        //---- search_button ----
        search_button.setText("serach by");
        search_button.addActionListener(e -> search_buttonActionPerformed(e));

        //---- addItem_button ----
        addItem_button.setText("add new");
        addItem_button.addActionListener(e -> addItem_buttonActionPerformed(e));

        //---- deleteItem_button ----
        deleteItem_button.setText("delete");
        deleteItem_button.addActionListener(e -> deleteItem_buttonActionPerformed(e));

        //---- results_label ----
        results_label.setText("number of records: ");

        //---- label1 ----
        label1.setText("date format e.g. 2009-09-12");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(table_jscrollpane, GroupLayout.Alignment.CENTER)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(table_label)
                                .addComponent(search_label))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(search_textfield, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(table_jcombobox, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(choose_table_button)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(search_combobox, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(search_button)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(label1)))
                            .addGap(0, 150, Short.MAX_VALUE)))
                    .addGap(85, 85, 85))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(288, 288, 288)
                    .addComponent(page_label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(results_label)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
                    .addComponent(addItem_button)
                    .addGap(18, 18, 18)
                    .addComponent(deleteItem_button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                    .addGap(88, 88, 88))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(table_label)
                        .addComponent(table_jcombobox)
                        .addComponent(choose_table_button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(search_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_label)
                        .addComponent(search_combobox)
                        .addComponent(search_button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(8, 8, 8)
                    .addComponent(table_jscrollpane, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(page_label)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteItem_button)
                        .addComponent(addItem_button)
                        .addComponent(results_label))
                    .addGap(84, 84, 84))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Stefan Zralok
    private JScrollPane table_jscrollpane;
    private JTable content_table;
    private JLabel table_label;
    private JComboBox<String> table_jcombobox;
    private JButton choose_table_button;
    private JLabel page_label;
    private JTextField search_textfield;
    private JLabel search_label;
    private JComboBox search_combobox;
    private JButton search_button;
    private JButton addItem_button;
    private JButton deleteItem_button;
    private JLabel results_label;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JScrollPane getTable_jscrollpane() {
        return table_jscrollpane;
    }

    public JTable getContent_table() {
        return content_table;
    }

    public JLabel getTable_label() {
        return table_label;
    }

    public JComboBox<String> getTable_jcombobox() {
        return table_jcombobox;
    }

    public JButton getChoose_table_button() {
        return choose_table_button;
    }

    public JButton getSearch_button() {
        return search_button;
    }

    public JButton getAddItem_button() {
        return addItem_button;
    }

    public JButton getDeleteItem_button() {
        return deleteItem_button;
    }

    public void setUp_table_content(Object[] columns) {
        if (table_content_model.getRowCount() > 0) {
            for (int i = table_content_model.getRowCount() - 1; i > -1; i--) {
                table_content_model.removeRow(i);
            }
        }
        table_content_model.setColumnIdentifiers(columns);
    }

    public void addItem_table_content(Object[] newRow) {
        this.table_content_model.addRow(newRow);
    }

    public DefaultTableModel getTable_content_model() {
        return table_content_model;
    }

    public JLabel getPage_label() {
        return page_label;
    }

    public void setPage_label(String page_label) {
        this.page_label.setText(page_label);
    }

    public String getSearch_textfieldText() {
        return this.search_textfield.getText();
    }

    public Object getSearch_comboboxItem() {
        return search_combobox.getSelectedItem();
    }

    public void set_search_comboboxItems(String[] list) {
        this.search_combobox.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            this.search_combobox.addItem(list[i]);
        }
    }

    public void setResults_labelText(Object number) {
        this.results_label.setText("number of records: "  + number);
    }
}
