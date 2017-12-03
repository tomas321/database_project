/*
 * Created by JFormDesigner on Mon Apr 24 23:17:56 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Tomas Bellus
 */
public class Add_openhouse extends JFrame {
    public Add_openhouse() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.D_date_textField.setDocument(new JTextFieldLimit(2));
        this.M_date_textField.setDocument(new JTextFieldLimit(2));
        this.Y_date_textField.setDocument(new JTextFieldLimit(4));
        this.H_time_textField.setDocument(new JTextFieldLimit(2));
        this.M_time_textField.setDocument(new JTextFieldLimit(2));
    }

    public void addAdd_buttunListener(ActionListener a) { this.add_button.addActionListener(a); }
    public void addCancel_buttonListener(ActionListener a) { this.cancel_button.addActionListener(a); }

    private void H_time_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void M_time_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void D_date_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void M_date_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void Y_date_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }
    }

    private void pick_estate_textFieldKeyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            e.consume();
        }    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tomas Bellus
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        H_time_textField = new JTextField();
        M_time_textField = new JTextField();
        D_date_textField = new JTextField();
        M_date_textField = new JTextField();
        Y_date_textField = new JTextField();
        client_textField = new JTextField();
        label4 = new JLabel();
        label6 = new JLabel();
        label5 = new JLabel();
        agent_textField = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        picked_estate_label = new JLabel();
        pick_estate_textField = new JTextField();
        cancel_button = new JButton();
        add_button = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {43, 56, 59, 0, 79, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 15, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("New open house");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        contentPane.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("time");
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("date");
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- H_time_textField ----
        H_time_textField.setToolTipText("HH");
        H_time_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                H_time_textFieldKeyTyped(e);
            }
        });
        contentPane.add(H_time_textField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- M_time_textField ----
        M_time_textField.setToolTipText("MM");
        M_time_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                M_time_textFieldKeyTyped(e);
            }
        });
        contentPane.add(M_time_textField, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- D_date_textField ----
        D_date_textField.setToolTipText("DD");
        D_date_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                D_date_textFieldKeyTyped(e);
            }
        });
        contentPane.add(D_date_textField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- M_date_textField ----
        M_date_textField.setToolTipText("MM");
        M_date_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                M_date_textFieldKeyTyped(e);
            }
        });
        contentPane.add(M_date_textField, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- Y_date_textField ----
        Y_date_textField.setToolTipText("YYYY");
        Y_date_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Y_date_textFieldKeyTyped(e);
            }
        });
        contentPane.add(Y_date_textField, new GridBagConstraints(3, 2, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(client_textField, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("client");
        contentPane.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label6 ----
        label6.setText("(name)");
        contentPane.add(label6, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label5 ----
        label5.setText("agent");
        contentPane.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(agent_textField, new GridBagConstraints(1, 4, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label7 ----
        label7.setText("(name)");
        contentPane.add(label7, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label8 ----
        label8.setText("estate");
        contentPane.add(label8, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label9 ----
        label9.setText("pick by id:");
        label9.setFont(label9.getFont().deriveFont(label9.getFont().getStyle() | Font.ITALIC));
        contentPane.add(label9, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(picked_estate_label, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- pick_estate_textField ----
        pick_estate_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                pick_estate_textFieldKeyTyped(e);
            }
        });
        contentPane.add(pick_estate_textField, new GridBagConstraints(3, 5, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- cancel_button ----
        cancel_button.setText("cancel");
        contentPane.add(cancel_button, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- add_button ----
        add_button.setText("add");
        contentPane.add(add_button, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,
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
    private JTextField H_time_textField;
    private JTextField M_time_textField;
    private JTextField D_date_textField;
    private JTextField M_date_textField;
    private JTextField Y_date_textField;
    private JTextField client_textField;
    private JLabel label4;
    private JLabel label6;
    private JLabel label5;
    private JTextField agent_textField;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel picked_estate_label;
    private JTextField pick_estate_textField;
    private JButton cancel_button;
    private JButton add_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public String getH_time_textFieldText() {
        return H_time_textField.getText();
    }

    public String getM_time_textFieldText() {
        return M_time_textField.getText();
    }

    public String getD_date_textFieldText() {
        return D_date_textField.getText();
    }

    public String getM_date_textFieldText() {
        return M_date_textField.getText();
    }

    public String getY_date_textFieldText() {
        return Y_date_textField.getText();
    }

    public String getClient_textFieldText() {
        return client_textField.getText();
    }

    public String getAgent_textFieldText() {
        return agent_textField.getText();
    }

    public String getPick_estate_textFieldText() {
        return pick_estate_textField.getText();
    }

    public JButton getCancel_button() {
        return cancel_button;
    }

    public JButton getAdd_button() {
        return add_button;
    }


}
