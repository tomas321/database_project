package controllers;

import models.Client;
import org.hibernate.*;
import views.Detail_window;
import views.ErrorMessage;
import views.Main_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static main.Constants.MAX_VIEW;
import static main.Constants.VIEW_LIMIT;

/**
 * Created by tomko on 18.4.2017.
 */
public class Manage_Client {
    private SessionFactory factory;
    private Main_window mainWindow;
    private ArrayList clients;

    public Manage_Client(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
        this.mainWindow.addDelete_buttonListener(listener);
        this.mainWindow.addSearch_buttonListener(listener);
        this.mainWindow.addTable_contentMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Client client = (Client) clients.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemString(client, get_num_of_openHouses(client)));
                    new Manage_detail(factory, detailWindow, client.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        Client clientItem = null;
        ArrayList clients = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "name", "phone", "e-mail"});
                    clients = (ArrayList) listClients(); // works as a charm :)
                    mainWindow.setResults_labelText(getSize());
                    max_view = (clients.size() <= MAX_VIEW) ? clients.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        clientItem = (Client) clients.get(i);
                        mainWindow.addItem_table_content(generateItem(clientItem));
                    }
                }
            }
            if (a.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
                    new Manage_addClient(factory);
                }
            }
            if (a.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int client_id = ((Client) clients.get(index)).getId();
                    deleteClient(client_id);
                }
            }
            if (a.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "name", "phone", "e-mail"});
                    clients = (ArrayList) searchClients(mainWindow.getSearch_textfieldText(), mainWindow.getSearch_comboboxItem());
                    mainWindow.setResults_labelText(clients.size());
                    max_view = (clients.size() <= VIEW_LIMIT) ? clients.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) {
                        clientItem = (Client) clients.get(i);
                        mainWindow.addItem_table_content(generateItem(clientItem));
                    }
                }
            }
        }
    }

    public Long getSize() {
        Session session = factory.openSession();
        Transaction tx = null;
        Long result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("SELECT count(id) FROM Client");
            result = (Long) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "get amount error");
        } finally {
            session.close();
        }
        return result;
    }

    public List listClients() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.clients = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Client c ORDER BY c.id";
            Query client_query = session.createQuery(sql_query);
            client_query.setMaxResults(MAX_VIEW);
            clients = (ArrayList) client_query.list();
            for (int i = 0; i < 100; i++) {
                Client client = (Client) clients.get(i);
                System.out.println(generateItemString(client));
            }
            System.out.println("count: " + clients.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return clients;
    }

    public void deleteClient(Integer clientID){
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Client WHERE id = :_id");
            query.setParameter("_id", clientID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] client with id " + clientID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
    }

    public List searchClients(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Client item;
        String search_query;
        Query query;
        try {
            tx = session.beginTransaction();
            if (attribute.equals("id")) {
                search_query = "FROM Client WHERE id = :c_filter";
                query = session.createQuery(search_query);
                query.setParameter("c_filter", Integer.parseInt((String) filter));
            } else {
                search_query = "FROM Client WHERE " + attribute + " like :c_filter";
                query = session.createQuery(search_query);
                query.setParameter("c_filter", "%" + filter + "%");
            }
            System.out.println("[SEARCH] query: " + search_query);
            search_result = (ArrayList) query.list();
            //System.out.println("list size: " + search_result.size());
            for (Iterator i = search_result.iterator(); i.hasNext();) {
                item = (Client) i.next();
                System.out.println(generateItemString(item));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "bad filter string");
        } finally {
            session.close();
        }
        clients = search_result;
        return search_result;
    }

    /**
     * select count(o.id)
     * from clients c
     * join open_houses o on o.client_id = c.id
     * where c.id = 15000
     * group by c.name
     */

    public long get_num_of_openHouses(Client client) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList result;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(o.id) FROM Client c " +
                    "JOIN c.open_houses o WHERE c.id = :clientID GROUP BY c.name"); //returns the number of open_houses linked to given Client
            query.setParameter("clientID", client.getId());
            result = (ArrayList) query.list();
            if (result.size() > 0) count = (long) result.get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    private String generateItemString(Client client, Object num_of_openHouses) {
        return "id: " + client.getId() + "\n" +
                "name: " + client.getName() + "\n" +
                "phone: " + client.getPhone() + "\n" +
                "e-mail: " + client.getEmail() + "\n" +
                "number of open houses: " + num_of_openHouses;
    }

    private String generateItemString(Client client) {
        return "id: " + client.getId() + " ; " +
                client.getName() + " ; " +
                client.getPhone() + " ; " +
                client.getEmail();
    }

    private Object[] generateItem(Client client) {
        return new Object[]{client.getId(), client.getName(), client.getPhone(), client.getEmail()};
    }
}
