package views;

import javax.swing.*;

/**
 * Created by tomko on 26.4.2017.
 */
public class ErrorMessage {
    private String msg;

    public ErrorMessage(JFrame frame, String msg) {
        this.msg = msg;
        JOptionPane.showMessageDialog(frame, msg, "Inane error", JOptionPane.ERROR_MESSAGE);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
