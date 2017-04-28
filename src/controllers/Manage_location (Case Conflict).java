package controllers;

import models.Estate;
import models.Location;
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

import static main.Constants.INTERVAL;
import static main.Constants.MAX_VIEW;

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
                    locations = (ArrayList) listLocations(); // works as a charm :)
                    max_view = (locations.size() <= MAX_VIEW) ? locations.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
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
        }
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
        } finally {
            session.close();
        }
        return locations;
    }

    public Long getEstate_count(Location location) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long result = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(e.id) FROM Location l " +
                    "JOIN l.estates e WHERE l.id = :_id GROUP BY l.id");
            query.setParameter("_id", location.getId());
            result = (Long) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    private String generateItemString(Location location, Object estate_count) {
        return "id: " + location.getId() + "\n" +
                "street: " + location.getStreet() + "\n" +
                "city: " + location.getCity() + "\n" +
                "number of estates at this location: " + estate_count;

    }

    public boolean setNext_page(int max) {
        if (!(this.end_pos + INTERVAL + 1 > max) || this.start_pos + INTERVAL < max) {
            setPage_interval(this.start_pos + INTERVAL);
            if (this.end_pos > max) this.end_pos = max;
            return true;
        } else {
            return false;
        }
    }

    public boolean setPrevious_page() {
        if (!(this.start_pos - INTERVAL < 0)) {
            setPage_interval(this.start_pos - INTERVAL);
            return true;
        } else {
            return false;
        }
    }

    public void setPage_interval(int start_pos) {
        this.start_pos = start_pos;
        this.end_pos = start_pos + INTERVAL;
    }

    private Object[] generateItem(Location location) {
        return new Object[]{location.getId(), location.getCity(), location.getStreet()};
    }
}
