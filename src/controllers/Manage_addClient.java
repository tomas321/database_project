package controllers;

import models.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import views.Add_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomko on 24.4.2017.
 */
public class Manage_addClient {
    private SessionFactory factory;
    private Add_client addClient;

    public Manage_addClient(SessionFactory factory) {
        this.factory = factory;
        this.addClient = new Add_client();
        this.addClient.setVisible(true);

        Listener listener = new Listener();
        this.addClient.addCancel_buttonListener(listener);
        this.addClient.addAdd_buttunListener(listener);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addClient.getAdd_button())) {
                addClient(joinName(addClient.getFirst_name_textFieldText(), addClient.getSurname_textFieldText()),
                        addClient.getPhone_textFieldText(),
                        addClient.getEmail_textFieldText());
                addClient.dispose();
            }
            if (actionEvent.getSource().equals(addClient.getCancel_button())) {
                addClient.dispose();
            }
        }
    }

    private Integer addClient(Object name, Object phone, Object email) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer clientID = null;
        try {
            tx = session.beginTransaction();
            Client client = new Client();
            client.setName((String) name);
            client.setPhone(parsePhone((String) phone));
            client.setEmail((String) email);
            clientID = (Integer) session.save(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding client\t" + e.getMessage());
        } finally {
            session.close();
        }
        return clientID;
    }

    private String joinName(String a, String b) {
        return a + " " + b;
    }

    private String parsePhone(String phone) {
        char[] chars = phone.toCharArray();
        return "(" + chars[0] + chars[1] + chars[2] + ") " + chars[3] + chars[4] + chars[5] + "-" + chars[6] + chars[7] + chars[8] + chars[9];
    }
}
