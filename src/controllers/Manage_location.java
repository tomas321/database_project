package controllers;

import models.Location;
import org.hibernate.*;
import views.Main_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static main.Constants.INTERVAL;
import static main.Constants.MAX_VIEW;

/**
 * Created by tomko on 8.4.2017.
 */
public class Manage_location {
    private SessionFactory factory;
    private Main_window mainWindow;
    private List locations;
    private int start_pos;
    private int end_pos;
    private int max_view;

    public Manage_location(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;
//        setPage_interval(0);

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        ArrayList locations = null;
        Location locationItem = null;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    mainWindow.getTable_content_listModel().removeAllElements();
                    locations = (ArrayList) listLocations(); // works as a charm :)
                    max_view = (locations.size() <= MAX_VIEW) ? locations.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        locationItem = (Location) locations.get(i);
                        mainWindow.addTable_content(locationItem.getId() + " ; " + locationItem.getCity() + " ; " + locationItem.getStreet());
                    }
                }
            }
            if (a.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Locations")) {
                    new Manage_addLocation(factory);
                }
            }
//            if (a.getSource().equals(mainWindow.getNext_button())) {
//                if (setNext_page(locations.size()))
//                    // is execute only if next page is set correctly
//                    mainWindow.getChoose_table_button().doClick(); // doClick() is for programmatically clicking a button
//            }
//            if (a.getSource().equals(mainWindow.getPrevious_button())) {
//                if (setPrevious_page())
//                    mainWindow.getChoose_table_button().doClick();
//            }
        }
    }

    public Integer addLocation(Object city, Object street) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer locationID = null;
        try {
            tx = session.beginTransaction();
            Location location = new Location();
            location.setStreet((String) street);
            location.setCity((String) city);
            locationID = (Integer) session.save(location);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("\nfuck\n");
        } finally {
            session.close();
        }
        return locationID;
    }

    public List listLocations() {
        Session session = factory.openSession();
        Transaction tx = null;
        locations = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Location";
            Query location_query = session.createQuery(sql_query);
            location_query.setMaxResults(MAX_VIEW);
            locations = location_query.list();
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
}
