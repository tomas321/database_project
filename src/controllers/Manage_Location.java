package controllers;

import models.Location;
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
import java.util.List;

import static main.Constants.MAX_VIEW;
import static main.Constants.VIEW_LIMIT;

/**
 * Created by tomko on 8.4.2017.
 */
public class Manage_Location {
    private SessionFactory factory;
    private Main_window mainWindow;
    private ArrayList locations;
    private int start_pos;
    private int end_pos;
    private int max_view;

    public Manage_Location(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;
//        setPage_interval(0);

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
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Location location = (Location) locations.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemString(location, getEstate_count(location)));
                    new Manage_detail(factory, detailWindow, location.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        ArrayList locations = null;
        Location locationItem = null;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "city", "street"});
                    locations = (ArrayList) listLocations();
                    mainWindow.setResults_labelText(getSize());
                    max_view = (locations.size() <= MAX_VIEW) ? locations.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 'MAX_VIEW' locations in the main window
                        locationItem = (Location) locations.get(i);
                        mainWindow.addItem_table_content(generateItem(locationItem));
                    }
                }
            }
            if (a.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    new Manage_addLocation(factory);
                }
            }
            if (a.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int locationID = ((Location) locations.get(index)).getId();
                    deleteLocation(locationID);
                }
            }
            if (a.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "city", "street"});
                    locations = (ArrayList) searchLocations(mainWindow.getSearch_textfieldText(), mainWindow.getSearch_comboboxItem());
                    mainWindow.setResults_labelText(locations.size());
                    max_view = (locations.size() <= VIEW_LIMIT) ? locations.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        locationItem = (Location) locations.get(i);
                        mainWindow.addItem_table_content(generateItem(locationItem));
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
            query = session.createQuery("SELECT count(id) FROM Location");
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

    public List listLocations() {
        Session session = factory.openSession();
        Transaction tx = null;
        locations = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Location l ORDER BY l.id";
            Query location_query = session.createQuery(sql_query);
            location_query.setMaxResults(MAX_VIEW);
            locations = (ArrayList) location_query.list();
            for (int i = 0; i < 100; i++) {
                Location location = (Location) locations.get(i);
                System.out.print("id: " + location.getId());
                System.out.print("\tcity: " + location.getCity());
                System.out.print("\tstreet: " + location.getStreet());
                System.out.print("\n");
            }
            System.out.println("count: " + locations.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return locations;
    }

    public List searchLocations(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            if (attribute.equals("id")) {
                query = session.createQuery("FROM Location WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", Integer.parseInt((String) filter));
            } else { // else if city or street
                query = session.createQuery("FROM Location WHERE " + attribute + " like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
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
        locations = search_result;
        return search_result;
    }

    public void deleteLocation(Integer locationID) {
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Location WHERE id = :_id");
            query.setParameter("_id", locationID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] location with id " + locationID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
    }

    public long getEstate_count(Location location) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList result;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(e.id) FROM Location l " +
                    "JOIN l.estates e WHERE l.id = :_id GROUP BY l.id");
            query.setParameter("_id", location.getId());
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

    private String generateItemString(Location location, Object estate_count) {
        return "id: " + location.getId() + "\n" +
                "street: " + location.getStreet() + "\n" +
                "city: " + location.getCity() + "\n" +
                "number of estates at this location: " + estate_count;

    }

    private Object[] generateItem(Location location) {
        return new Object[]{location.getId(), location.getCity(), location.getStreet()};
    }
}
