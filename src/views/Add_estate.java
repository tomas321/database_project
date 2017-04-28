/*
 * Created by JFormDesigner on Sun Apr 23 13:37:36 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.event.ChangeListener;

/**
 * @author Tomas Bellus
 */
public class Add_estate extends JFrame {
    public Add_estate() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.D_build_at_textField.setDocument(new JTextFieldLimit(2)); // limits the number of characters to be allowed
        this.M_build_at_textField.setDocument(new JTextFieldLimit(2));
        this.Y_build_at_textField.setDocument(new JTextFieldLimit(4));
    }

    public void addCancel_buttonListener(ActionListener a) { this.cancel_button.addActionListener(a); }
    public void addAdd_buttonListener(ActionListener a) { this.add_button.addActionListener(a); }
    public void addLocation_checkboxItemListener(ItemListener l) { this.location_checkBox.addItemListener(l); }
    public void addArrangement_checkboxItemListener(ItemListener l) { this.arrangement_checkBox.addItemListener(l); }


    // assures that a users types in only digits
    private void land_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void price_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void D_build_at_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }    }

    private void M_build_at_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }    }

    private void Y_build_at_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }    }

    private void cancel_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void add_buttonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void location_id_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void arrangement_id_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Stefan Zralok
        label1 = new JLabel();
        label12 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        name_textField = new JTextField();
        label13 = new JLabel();
        city_textField = new JTextField();
        status_comboBox = new JComboBox<>();
        label14 = new JLabel();
        street_textField = new JTextField();
        category_comboBox = new JComboBox<>();
        label15 = new JLabel();
        land_textField = new JTextField();
        label16 = new JLabel();
        rooms_comboBox = new JComboBox<>();
        D_build_at_textField = new JTextField();
        M_build_at_textField = new JTextField();
        Y_build_at_textField = new JTextField();
        label17 = new JLabel();
        floors_comboBox = new JComboBox<>();
        price_textField = new JTextField();
        label18 = new JLabel();
        toilets_comboBox = new JComboBox<>();
        label19 = new JLabel();
        furniture_comboBox = new JComboBox<>();
        label20 = new JLabel();
        balcony_comboBox = new JComboBox<>();
        label9 = new JLabel();
        location_checkBox = new JCheckBox();
        label8 = new JLabel();
        location_id_textField = new JTextField();
        label21 = new JLabel();
        pool_comboBox = new JComboBox<>();
        label10 = new JLabel();
        arrangement_checkBox = new JCheckBox();
        label11 = new JLabel();
        arrangement_id_textField = new JTextField();
        label22 = new JLabel();
        garden_comboBox = new JComboBox<>();
        cancel_button = new JButton();
        add_button = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 58, 0, 32, 99, 31, 75, 57, 48, 54, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("New estate item");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        contentPane.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label12 ----
        label12.setText("Set location");
        label12.setFont(label12.getFont().deriveFont(label12.getFont().getStyle() | Font.BOLD));
        contentPane.add(label12, new GridBagConstraints(6, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("name");
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("status");
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("category");
        contentPane.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("land");
        contentPane.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label6 ----
        label6.setText("build_at");
        contentPane.add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label7 ----
        label7.setText("price");
        contentPane.add(label7, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(name_textField, new GridBagConstraints(1, 1, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label13 ----
        label13.setText("city");
        contentPane.add(label13, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(city_textField, new GridBagConstraints(7, 1, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- status_comboBox ----
        status_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "new",
            "second hand"
        }));
        contentPane.add(status_comboBox, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label14 ----
        label14.setText("street");
        contentPane.add(label14, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(street_textField, new GridBagConstraints(7, 2, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- category_comboBox ----
        category_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "house",
            "flat",
            "villa"
        }));
        contentPane.add(category_comboBox, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label15 ----
        label15.setText("Set arrangement");
        label15.setFont(label15.getFont().deriveFont(label15.getFont().getStyle() | Font.BOLD));
        contentPane.add(label15, new GridBagConstraints(6, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- land_textField ----
        land_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                land_textFieldKeyTyped(e);
            }
        });
        contentPane.add(land_textField, new GridBagConstraints(1, 4, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label16 ----
        label16.setText("rooms");
        contentPane.add(label16, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0,
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
        contentPane.add(rooms_comboBox, new GridBagConstraints(7, 4, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- D_build_at_textField ----
        D_build_at_textField.setToolTipText("DD");
        D_build_at_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                D_build_at_textFieldKeyTyped(e);
            }
        });
        contentPane.add(D_build_at_textField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- M_build_at_textField ----
        M_build_at_textField.setToolTipText("MM");
        M_build_at_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                M_build_at_textFieldKeyTyped(e);
            }
        });
        contentPane.add(M_build_at_textField, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- Y_build_at_textField ----
        Y_build_at_textField.setToolTipText("YYYY");
        Y_build_at_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Y_build_at_textFieldKeyTyped(e);
            }
        });
        contentPane.add(Y_build_at_textField, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label17 ----
        label17.setText("floors");
        contentPane.add(label17, new GridBagConstraints(6, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- floors_comboBox ----
        floors_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "1",
            "2",
            "3"
        }));
        contentPane.add(floors_comboBox, new GridBagConstraints(7, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- price_textField ----
        price_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                price_textFieldKeyTyped(e);
            }
        });
        contentPane.add(price_textField, new GridBagConstraints(1, 6, 4, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label18 ----
        label18.setText("toilets");
        contentPane.add(label18, new GridBagConstraints(6, 6, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- toilets_comboBox ----
        toilets_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "1",
            "2"
        }));
        contentPane.add(toilets_comboBox, new GridBagConstraints(7, 6, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label19 ----
        label19.setText("furniture");
        contentPane.add(label19, new GridBagConstraints(6, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- furniture_comboBox ----
        furniture_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(furniture_comboBox, new GridBagConstraints(7, 7, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label20 ----
        label20.setText("balcony");
        contentPane.add(label20, new GridBagConstraints(6, 8, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- balcony_comboBox ----
        balcony_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(balcony_comboBox, new GridBagConstraints(7, 8, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label9 ----
        label9.setText("new location");
        contentPane.add(label9, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(location_checkBox, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label8 ----
        label8.setText("or");
        contentPane.add(label8, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- location_id_textField ----
        location_id_textField.setToolTipText("location id");
        location_id_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                location_id_textFieldKeyTyped(e);
            }
        });
        contentPane.add(location_id_textField, new GridBagConstraints(4, 9, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label21 ----
        label21.setText("pool");
        contentPane.add(label21, new GridBagConstraints(6, 9, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- pool_comboBox ----
        pool_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(pool_comboBox, new GridBagConstraints(7, 9, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label10 ----
        label10.setText("new arrangement");
        contentPane.add(label10, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(arrangement_checkBox, new GridBagConstraints(2, 10, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label11 ----
        label11.setText("or");
        contentPane.add(label11, new GridBagConstraints(3, 10, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- arrangement_id_textField ----
        arrangement_id_textField.setToolTipText("arrangement id");
        arrangement_id_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                arrangement_id_textFieldKeyTyped(e);
            }
        });
        contentPane.add(arrangement_id_textField, new GridBagConstraints(4, 10, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label22 ----
        label22.setText("garden");
        contentPane.add(label22, new GridBagConstraints(6, 10, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- garden_comboBox ----
        garden_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "with",
            "without"
        }));
        contentPane.add(garden_comboBox, new GridBagConstraints(7, 10, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cancel_button ----
        cancel_button.setText("cancel");
        cancel_button.addActionListener(e -> cancel_buttonActionPerformed(e));
        contentPane.add(cancel_button, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- add_button ----
        add_button.setText("add");
        add_button.addActionListener(e -> add_buttonActionPerformed(e));
        contentPane.add(add_button, new GridBagConstraints(9, 12, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Stefan Zralok
    private JLabel label1;
    private JLabel label12;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField name_textField;
    private JLabel label13;
    private JTextField city_textField;
    private JComboBox<String> status_comboBox;
    private JLabel label14;
    private JTextField street_textField;
    private JComboBox<String> category_comboBox;
    private JLabel label15;
    private JTextField land_textField;
    private JLabel label16;
    private JComboBox<String> rooms_comboBox;
    private JTextField D_build_at_textField;
    private JTextField M_build_at_textField;
    private JTextField Y_build_at_textField;
    private JLabel label17;
    private JComboBox<String> floors_comboBox;
    private JTextField price_textField;
    private JLabel label18;
    private JComboBox<String> toilets_comboBox;
    private JLabel label19;
    private JComboBox<String> furniture_comboBox;
    private JLabel label20;
    private JComboBox<String> balcony_comboBox;
    private JLabel label9;
    private JCheckBox location_checkBox;
    private JLabel label8;
    private JTextField location_id_textField;
    private JLabel label21;
    private JComboBox<String> pool_comboBox;
    private JLabel label10;
    private JCheckBox arrangement_checkBox;
    private JLabel label11;
    private JTextField arrangement_id_textField;
    private JLabel label22;
    private JComboBox<String> garden_comboBox;
    private JButton cancel_button;
    private JButton add_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public String getName_textFieldText() {
        return name_textField.getText();
    }

    public JComboBox<String> getStatus_comboBox() {
        return status_comboBox;
    }

    public JComboBox<String> getCategory_comboBox() {
        return category_comboBox;
    }

    public String getLand_textFieldText() {
        return land_textField.getText();
    }

    public String getM_build_at_textFieldText() {
        return M_build_at_textField.getText();
    }

    public String getD_build_at_textFieldText() {
        return D_build_at_textField.getText();
    }

    public String getY_build_at_textFieldText() {
        return Y_build_at_textField.getText();
    }

    public String getPrice_textFieldText() {
        return price_textField.getText();
    }

    public JButton getCancel_button() {
        return cancel_button;
    }

    public JButton getAdd_button() {
        return add_button;
    }


    public JCheckBox getLocation_checkBox() {
        return location_checkBox;
    }

    public JCheckBox getArrangement_checkBox() {
        return arrangement_checkBox;
    }

    public String getLocation_id_textFieldText() {
        return location_id_textField.getText();
    }

    public String getArrangement_id_textFieldText() {
        return arrangement_id_textField.getText();
    }

    public String getCity_textFieldText() {
        return city_textField.getText();
    }

    public String getStreet_textFieldText() {
        return street_textField.getText();
    }

    public JComboBox<String> getRooms_comboBox() {
        return rooms_comboBox;
    }

    public JComboBox<String> getFloors_comboBox() {
        return floors_comboBox;
    }

    public JComboBox<String> getToilets_comboBox() {
        return toilets_comboBox;
    }

    public JComboBox<String> getFurniture_comboBox() {
        return furniture_comboBox;
    }

    public JComboBox<String> getBalcony_comboBox() {
        return balcony_comboBox;
    }

    public JComboBox<String> getPool_comboBox() {
        return pool_comboBox;
    }

    public JComboBox<String> getGarden_comboBox() {
        return garden_comboBox;
    }
}
