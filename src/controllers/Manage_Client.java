package controllers;

import models.Client;
import models.Location;
import org.hibernate.*;
import views.Main_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static main.Constants.MAX_VIEW;

/**
 * Created by tomko on 18.4.2017.
 */
public class Manage_Client {
    private SessionFactory factory;
    private Main_window mainWindow;
    private List clients;

    public Manage_Client(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        ArrayList clients = null;
        Client clientItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
                    mainWindow.getTable_content_listModel().removeAllElements();
                    clients = (ArrayList) listClients(); // works as a charm :)
                    max_view = (clients.size() <= MAX_VIEW) ? clients.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        clientItem = (Client) clients.get(i);
                        mainWindow.addTable_content(generateItemString(clientItem));
                    }
                }
            }
//            if (a.getSource().equals(mainWindow.getAddItem_button())) {
//                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
//                    new Manage_addClient(factory);
//                }
//            }
        }
    }

    public Integer addClient() {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer clientID = null;
        try {
            tx = session.beginTransaction();
            Location location = new Location();

            clientID = (Integer) session.save(location);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding client " + e.getMessage());
        } finally {
            session.close();
        }
        return clientID;
    }

    public List listClients() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.clients = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Client";
            Query client_query = session.createQuery(sql_query);
            client_query.setMaxResults(MAX_VIEW);
            clients = client_query.list();
            for (int i = 0; i < 100; i++) {
                Client client = (Client) clients.get(i);
                System.out.println(generateItemString(client));
            }
            System.out.println("count: " + clients.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return clients;
    }

    private String generateItemString(Client client) {
        return "id: " + client.getId() + " ; " +
                client.getName() + " ; " +
                client.getPhone() + " ; " +
                client.getEmail();
    }
}
