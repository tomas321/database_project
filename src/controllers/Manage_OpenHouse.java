package controllers;

import models.Agent;
import models.Client;
import models.Open_house;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static main.Constants.MAX_VIEW;
import static main.Constants.VIEW_LIMIT;

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
        this.mainWindow.addDelete_buttonListener(listener);
        this.mainWindow.addSearch_buttonListener(listener);
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
                    openhouses = (ArrayList) listOpenHouses();
                    mainWindow.setResults_labelText(getSize());
                    max_view = (openhouses.size() <= MAX_VIEW) ? openhouses.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
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
            if (a.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Open houses")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int houseID = ((Open_house) openhouses.get(index)).getId();
                    deleteOpenHouse(houseID);
                }
            }
            if (a.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Open houses")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "date and time", "location", "agent", "client"});
                    openhouses = (ArrayList) searchOpenHouses(mainWindow.getSearch_textfieldText(), mainWindow.getSearch_comboboxItem());
                    mainWindow.setResults_labelText(openhouses.size());
                    max_view = (openhouses.size() <= VIEW_LIMIT) ? openhouses.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        openHouseItem = (Open_house) openhouses.get(i);
                        mainWindow.addItem_table_content(generateItem(openHouseItem));
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
            query = session.createQuery("SELECT count(id) FROM Open_house ");
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
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return openhouses;
    }

    public List searchOpenHouses(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            if (attribute.equals("id")) {
                query = session.createQuery("FROM Open_house WHERE id = :_filter");
                query.setParameter("_filter", Integer.parseInt((String) filter));
            } else if (attribute.equals("agent")) {
                query = session.createQuery("SELECT o FROM Open_house o JOIN o.agent a WHERE a.name like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("client")) {
                query = session.createQuery("SELECT o FROM Open_house o JOIN o.client c WHERE c.name like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("estate")) {
                query = session.createQuery("SELECT o FROM Open_house o JOIN o.estate e WHERE e.name like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else { // else time at attribute
                query = session.createQuery("FROM Open_house WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", stringToTimestamp((String) filter));
            }
            search_result = (ArrayList) query.list();
            System.out.println("[SEARCH] search query: " + query.getQueryString());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "bad filter string");
        } finally {
            session.close();
        }
        openhouses = search_result;
        return search_result;
    }

    public void deleteOpenHouse(Integer openHouseID) {
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Open_house WHERE id = :_id");
            query.setParameter("_id", openHouseID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] open house with id " + openHouseID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
    }

    private Timestamp stringToTimestamp(String time_at) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date parsedDate = format.parse(time_at);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
