package controllers;

import models.Agent;
import models.Client;
import models.Open_house;
import org.hibernate.*;
import views.Detail_window;
import views.Main_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static main.Constants.MAX_VIEW;

/**
 * Created by tomko on 18.4.2017.
 */
public class Manage_OpenHouse {
    private SessionFactory factory;
    private Main_window mainWindow;
    private ArrayList openhouses;

    public Manage_OpenHouse(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
        this.mainWindow.addTable_contentMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Open houses")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Open_house openHouse = (Open_house) openhouses.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemInfo(openHouse));
                    new Manage_detail(factory, detailWindow, openHouse.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        ArrayList openhouses = null;
        Open_house openHouseItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Open houses")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "date and time", "location", "agent", "client"});
                    openhouses = (ArrayList) listOpenHouses(); // works as a charm :)
                    max_view = (openhouses.size() <= MAX_VIEW) ? openhouses.size() : MAX_VIEW;
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        openHouseItem = (Open_house) openhouses.get(i);
                        mainWindow.addItem_table_content(generateItem(openHouseItem));
                    }
                }
            }
            if (a.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Open houses")) {
                    new Manage_addOpenhouse(factory);
                }
            }
        }
    }

    public List listOpenHouses() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Open_house o ORDER BY o.id";
            Query agent_query = session.createQuery(sql_query);
            agent_query.setMaxResults(MAX_VIEW);
            openhouses = (ArrayList) agent_query.list();
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

    private String generateItemInfo(Open_house openHouse) {
        return "id: " + openHouse.getId() + "\n" +
                "time and date: " + openHouse.getTime_at() + "\n" +
                "client: " + openHouse.getClient().getName() + "\n" +
                "agent: " + openHouse.getAgent().getName() + "\n" +
                "estate: " + openHouse.getEstate().getName() + "\n" +
                "location: " + openHouse.getEstate().getLocation().getCity() + ", " + openHouse.getEstate().getLocation().getStreet() + "\n" +
                "client id: " + openHouse.getClient().getId() + "\n" +
                "agent id: " + openHouse.getAgent().getId() + "\n" +
                "estate id: " + openHouse.getEstate().getId();
    }

    private String generateItemString(Open_house openHouse) {
        return "id: " + openHouse.getId() + " ; " +
                openHouse.getTime_at();
    }

    private Object[] generateItem(Open_house openHouse) {
        return new Object[]{openHouse.getId(),
                openHouse.getTime_at(),
                openHouse.getEstate().getLocation().getCity() + ", " + openHouse.getEstate().getLocation().getStreet(),
                openHouse.getAgent().getName(),
                openHouse.getClient().getName()
        };
    }
}
