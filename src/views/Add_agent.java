/*
 * Created by JFormDesigner on Mon Apr 24 22:10:35 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Tomas Bellus
 */
public class Add_agent extends JFrame {
    public Add_agent() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.D_started_at_textField.setDocument(new JTextFieldLimit(2));
        this.M_started_at_textField.setDocument(new JTextFieldLimit(2));
        this.Y_started_at_textField.setDocument(new JTextFieldLimit(4));
        this.phone_textField.setDocument(new JTextFieldLimit(10));
    }

    public void addAdd_buttonListener(ActionListener a) { this.add_button.addActionListener(a); }
    public void addCancel_buttonListener(ActionListener a) { this.cancel_button.addActionListener(a); }

    private void phone_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void D_started_at_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void M_started_at_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void Y_started_at_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tomas Bellus
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        cancel_button = new JButton();
        first_name_textField = new JTextField();
        surname_textField = new JTextField();
        phone_textField = new JTextField();
        label6 = new JLabel();
        D_started_at_textField = new JTextField();
        M_started_at_textField = new JTextField();
        Y_started_at_textField = new JTextField();
        rating_comboBox = new JComboBox<>();
        add_button = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 59, 63, 70, 64, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("New agent item");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        contentPane.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("name");
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("phone");
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("started at");
        contentPane.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label5 ----
        label5.setText("rating");
        contentPane.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- cancel_button ----
        cancel_button.setText("cancel");
        contentPane.add(cancel_button, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- first_name_textField ----
        first_name_textField.setToolTipText("first name");
        contentPane.add(first_name_textField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- surname_textField ----
        surname_textField.setToolTipText("surname");
        contentPane.add(surname_textField, new GridBagConstraints(3, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- phone_textField ----
        phone_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                phone_textFieldKeyTyped(e);
            }
        });
        contentPane.add(phone_textField, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label6 ----
        label6.setText("e.g. 0901222333");
        contentPane.add(label6, new GridBagConstraints(3, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- D_started_at_textField ----
        D_started_at_textField.setToolTipText("DD");
        D_started_at_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                D_started_at_textFieldKeyTyped(e);
            }
        });
        contentPane.add(D_started_at_textField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- M_started_at_textField ----
        M_started_at_textField.setToolTipText("MM");
        M_started_at_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                M_started_at_textFieldKeyTyped(e);
            }
        });
        contentPane.add(M_started_at_textField, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- Y_started_at_textField ----
        Y_started_at_textField.setToolTipText("YYYY");
        Y_started_at_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Y_started_at_textFieldKeyTyped(e);
            }
        });
        contentPane.add(Y_started_at_textField, new GridBagConstraints(3, 3, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- rating_comboBox ----
        rating_comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "amateur",
            "satisfactory",
            "good",
            "persuasive",
            "experienced"
        }));
        contentPane.add(rating_comboBox, new GridBagConstraints(1, 4, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- add_button ----
        add_button.setText("add");
        contentPane.add(add_button, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0,
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
    private JButton cancel_button;
    private JTextField first_name_textField;
    private JTextField surname_textField;
    private JTextField phone_textField;
    private JLabel label6;
    private JTextField D_started_at_textField;
    private JTextField M_started_at_textField;
    private JTextField Y_started_at_textField;
    private JComboBox<String> rating_comboBox;
    private JButton add_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JButton getCancel_button() {
        return cancel_button;
    }

    public String getFirst_name_textFieldText() {
        return first_name_textField.getText();
    }

    public String getSurname_textFieldText() {
        return surname_textField.getText();
    }

    public String getPhone_textFieldText() {
        return phone_textField.getText();
    }

    public String getD_started_at_textFieldText() {
        return D_started_at_textField.getText();
    }

    public String getM_started_at_textFieldText() {
        return M_started_at_textField.getText();
    }

    public String getY_started_at_textFieldText() {
        return Y_started_at_textField.getText();
    }

    public JComboBox<String> getRating_comboBox() {
        return rating_comboBox;
    }

    public JButton getAdd_button() {
        return add_button;
    }
}
