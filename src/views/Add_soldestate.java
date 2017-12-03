/*
 * Created by JFormDesigner on Tue Apr 25 00:42:43 CEST 2017
 */

package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Tomas Bellus
 */
public class Add_soldestate extends JFrame {
    public Add_soldestate() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.D_date_textField.setDocument(new JTextFieldLimit(2));
        this.M_date_textField.setDocument(new JTextFieldLimit(2));
        this.Y_date_textField.setDocument(new JTextFieldLimit(4));
    }

    public void addAdd_buttunListener(ActionListener a) { this.add_button.addActionListener(a); }
    public void addCancel_buttonListener(ActionListener a) { this.cancel_button.addActionListener(a); }

    private void price_textFieldKeyTyped(KeyEvent e) {
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Tomas Bellus
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        price_textField = new JTextField();
        sold_to_textField = new JTextField();
        D_date_textField = new JTextField();
        M_date_textField = new JTextField();
        Y_date_textField = new JTextField();
        label5 = new JLabel();
        sold_by_textField = new JTextField();
        cancel_button = new JButton();
        add_button = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 53, 59, 104, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("New sold estate");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));
        contentPane.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label2 ----
        label2.setText("price");
        contentPane.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("sold to");
        contentPane.add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- label4 ----
        label4.setText("date");
        contentPane.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));

        //---- price_textField ----
        price_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                price_textFieldKeyTyped(e);
            }
        });
        contentPane.add(price_textField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(sold_to_textField, new GridBagConstraints(1, 2, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- D_date_textField ----
        D_date_textField.setToolTipText("DD");
        D_date_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                D_date_textFieldKeyTyped(e);
            }
        });
        contentPane.add(D_date_textField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
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
        contentPane.add(M_date_textField, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
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
        contentPane.add(Y_date_textField, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- label5 ----
        label5.setText("sold by");
        contentPane.add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(sold_by_textField, new GridBagConstraints(1, 4, 3, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //---- cancel_button ----
        cancel_button.setText("cancel");
        contentPane.add(cancel_button, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 5), 0, 0));

        //---- add_button ----
        add_button.setText("add");
        contentPane.add(add_button, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
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
    private JTextField price_textField;
    private JTextField sold_to_textField;
    private JTextField D_date_textField;
    private JTextField M_date_textField;
    private JTextField Y_date_textField;
    private JLabel label5;
    private JTextField sold_by_textField;
    private JButton cancel_button;
    private JButton add_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public String getPrice_textFieldText() {
        return price_textField.getText();
    }

    public String getSold_to_textFieldText() {
        return sold_to_textField.getText();
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

    public String getSold_by_textFieldText() {
        return sold_by_textField.getText();
    }

    public JButton getCancel_button() {
        return cancel_button;
    }

    public JButton getAdd_button() {
        return add_button;
    }
}
