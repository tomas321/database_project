/*
 * Created by JFormDesigner on Tue Apr 18 14:14:19 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Tomas Bellus
 */
public class Add_location extends JFrame {
    public Add_location() {
        initComponents();
    }

    public void addCancel_buttonListener(ActionListener a) { this.cancel_button.addActionListener(a); }
    public void addAdd_buttonListener(ActionListener a) { this.add_button.addActionListener(a); }

    private void cancel_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void add_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tomas Bellus
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        city_textField = new JTextField();
        street_textField = new JTextField();
        cancel_button = new JButton();
        add_button = new JButton();

        //======== this ========
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("New location item");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        contentPane.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("city");
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("street");
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(city_textField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(street_textField, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- cancel_button ----
        cancel_button.setText("cancel");
        cancel_button.addActionListener(e -> cancel_buttonActionPerformed(e));
        contentPane.add(cancel_button, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- add_button ----
        add_button.setText("add");
        add_button.addActionListener(e -> add_buttonActionPerformed(e));
        contentPane.add(add_button, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Tomas Bellus
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField city_textField;
    private JTextField street_textField;
    private JButton cancel_button;
    private JButton add_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JButton getCancel_button() {
        return cancel_button;
    }

    public JButton getAdd_button() {
        return add_button;
    }

    public String getCity_textFieldText() {
        return city_textField.getText();
    }

    public String getStreet_textFieldText() {
        return street_textField.getText();
    }
}
