/*
 * Created by JFormDesigner on Mon Apr 17 00:08:43 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Tomas Bellus
 */
public class Add_arrangement extends JFrame {
    public Add_arrangement() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        rooms_comboBox = new JComboBox<>();
        floors_comboBox = new JComboBox<>();
        furniture_comboBox = new JComboBox<>();
        balcony_comboBox = new JComboBox<>();
        garden_comboBox = new JComboBox<>();
        pool_comboBox = new JComboBox<>();
        toilets_comboBox = new JComboBox<>();
        cancel_button = new JButton();
        add_button = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("New arrangement item");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        contentPane.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("rooms");
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("floors");
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("furniture");
        contentPane.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("balcony");
        contentPane.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label6 ----
        label6.setText("garden");
        contentPane.add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label7 ----
        label7.setText("pool");
        contentPane.add(label7, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label8 ----
        label8.setText("toilets");
        contentPane.add(label8, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- rooms_comboBox ----
        rooms_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7"
        }));
        contentPane.add(rooms_comboBox, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- floors_comboBox ----
        floors_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "1",
            "2",
            "3"
        }));
        contentPane.add(floors_comboBox, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- furniture_comboBox ----
        furniture_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(furniture_comboBox, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- balcony_comboBox ----
        balcony_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        balcony_comboBox.setEditable(true);
        contentPane.add(balcony_comboBox, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- garden_comboBox ----
        garden_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(garden_comboBox, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- pool_comboBox ----
        pool_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(pool_comboBox, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- toilets_comboBox ----
        toilets_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "1",
            "2"
        }));
        contentPane.add(toilets_comboBox, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- cancel_button ----
        cancel_button.setText("cancel");
        cancel_button.addActionListener(e -> cancel_buttonActionPerformed(e));
        contentPane.add(cancel_button, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- add_button ----
        add_button.setText("add");
        add_button.addActionListener(e -> add_buttonActionPerformed(e));
        contentPane.add(add_button, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
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
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JComboBox<String> rooms_comboBox;
    private JComboBox<String> floors_comboBox;
    private JComboBox<String> furniture_comboBox;
    private JComboBox<String> balcony_comboBox;
    private JComboBox<String> garden_comboBox;
    private JComboBox<String> pool_comboBox;
    private JComboBox<String> toilets_comboBox;
    private JButton cancel_button;
    private JButton add_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JButton getCancel_button() {
        return cancel_button;
    }

    public JButton getAdd_button() {
        return add_button;
    }

    public JComboBox<String> getRooms_comboBox() {
        return rooms_comboBox;
    }

    public JComboBox<String> getFloors_comboBox() {
        return floors_comboBox;
    }

    public JComboBox<String> getFurniture_comboBox() {
        return furniture_comboBox;
    }

    public JComboBox<String> getBalcony_comboBox() {
        return balcony_comboBox;
    }

    public JComboBox<String> getGarden_comboBox() {
        return garden_comboBox;
    }

    public JComboBox<String> getPool_comboBox() {
        return pool_comboBox;
    }

    public JComboBox<String> getToilets_comboBox() {
        return toilets_comboBox;
    }
}
