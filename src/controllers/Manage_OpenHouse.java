package controllers;

import models.Agent;
import models.Open_house;
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
public class Manage_OpenHouse {
    private SessionFactory factory;
    private Main_window mainWindow;
    private List openhouses;

    public Manage_OpenHouse(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        ArrayList openhouses = null;
        Open_house openHouseItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Open houses")) {
                    mainWindow.getTable_content_listModel().removeAllElements();
                    openhouses = (ArrayList) listOpenHouses(); // works as a charm :)
                    max_view = (openhouses.size() <= MAX_VIEW) ? openhouses.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        openHouseItem = (Open_house) openhouses.get(i);
                        mainWindow.addTable_content(generateItemString(openHouseItem));
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

    public Integer addOpenHouse(Object time_at) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer openHouseID = null;
        try {
            tx = session.beginTransaction();
            Open_house openHouse = new Open_house();
            openHouse.setTime_at((String) time_at);
            openHouseID = (Integer) session.save(openHouse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding open house " + e.getMessage());
        } finally {
            session.close();
        }
        return openHouseID;
    }

    public List listOpenHouses() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.openhouses = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Open_house";
            Query agent_query = session.createQuery(sql_query);
            agent_query.setMaxResults(MAX_VIEW);
            openhouses = agent_query.list();
            for (int i = 0; i < 100; i++) {
                Open_house openHouse = (Open_house) openhouses.get(i);
                System.out.println(generateItemString(openHouse));
            }
            System.out.println("count: " + openhouses.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return openhouses;
    }

    private String generateItemString(Open_house openHouse) {
        return "id: " + openHouse.getId() + " ; " +
                openHouse.getTime_at();
    }
}
