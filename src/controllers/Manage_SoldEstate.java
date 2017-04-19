package controllers;

import models.Open_house;
import models.Sold_estate;
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
public class Manage_SoldEstate {
    private SessionFactory factory;
    private Main_window mainWindow;
    private List soldEstates;

    public Manage_SoldEstate(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        ArrayList soldEstates = null;
        Sold_estate soldEstateItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Sold estates")) {
                    mainWindow.getTable_content_listModel().removeAllElements();
                    soldEstates = (ArrayList) listSoldEstates(); // works as a charm :)
                    max_view = (soldEstates.size() <= MAX_VIEW) ? soldEstates.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        soldEstateItem = (Sold_estate) soldEstates.get(i);
                        mainWindow.addTable_content(generateItemString(soldEstateItem));
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

    public Integer addSoldEstate(Object sold_to, Object price, Object sold_date) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer soldEstateID = null;
        try {
            tx = session.beginTransaction();
            Sold_estate soldEstate = new Sold_estate();
            soldEstate.setSold_to((String) sold_to);
            soldEstate.setPrice(Double.parseDouble((String) price));
            soldEstate.setSold_date((String) sold_date);
            soldEstateID = (Integer) session.save(soldEstate);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding sold estate " + e.getMessage());
        } finally {
            session.close();
        }
        return soldEstateID;
    }

    public List listSoldEstates() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.soldEstates = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Sold_estate";
            Query agent_query = session.createQuery(sql_query);
            agent_query.setMaxResults(MAX_VIEW);
            soldEstates = agent_query.list();
            for (int i = 0; i < 100; i++) {
                Sold_estate soldEstate = (Sold_estate) soldEstates.get(i);
                System.out.println(generateItemString(soldEstate));
            }
            System.out.println("count: " + soldEstates.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return soldEstates;
    }

    private String generateItemString(Sold_estate soldEstate) {
        return "id: " + soldEstate.getId() + " ; " +
                soldEstate.getPrice() + "$ ; buyer: " +
                soldEstate.getSold_to() + " ; " +
                soldEstate.getSold_date();
    }
}
