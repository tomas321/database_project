package controllers;

import models.Arrangement;
import models.Client;
import org.hibernate.*;
import views.Add_arrangement;
import views.Detail_window;
import views.Main_window;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static main.Constants.INTERVAL;
import static main.Constants.MAX_VIEW;

/**
 * Created by tomko on 11.4.2017.
 */
public class Manage_Arrangement {
    private SessionFactory factory;
    private Main_window mainWindow;
    private ArrayList arrangements;
    private int max_view;

    public Manage_Arrangement(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;
        this.arrangements = null;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addSearch_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
        this.mainWindow.addTable_contentMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Arrangement arrangement = (Arrangement) arrangements.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemString(arrangement, getEstate_count(arrangement)));
                    new Manage_detail(factory, detailWindow, arrangement.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        ArrayList search_list = null;
        Arrangement arrangementItem = null;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "floor count", "room count", "toilet count", "balcony", "furniture", "pool", "garden"});
                    arrangements = (ArrayList) listArrangements(); // works as a charm :)
                    max_view = (arrangements.size() <= MAX_VIEW) ? arrangements.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        arrangementItem = (Arrangement) arrangements.get(i);
                        mainWindow.addItem_table_content(generateItem(arrangementItem));
                    }
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
//                    mainWindow.getTable_content_listModel().removeAllElements(); // clear the contents
                    mainWindow.setUp_table_content(new Object[]{"id", "floor count", "room count", "toilet count", "balcony", "furniture", "pool", "garden"});
                    search_list = (ArrayList) searchArrangements(mainWindow.getSearch_textfieldText(), (String) mainWindow.getSearch_comboboxItem());
                    for (Iterator i = search_list.iterator(); i.hasNext(); ) {
                        arrangementItem = (Arrangement) i.next();
                        mainWindow.addItem_table_content(generateItem(arrangementItem));
                    }
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    new Manage_addArrangement(factory);
                }
            }
        }
    }

    public List listArrangements() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.arrangements = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Arrangement a ORDER BY a.id";
            Query arrangement_query = session.createQuery(sql_query);
            arrangement_query.setMaxResults(MAX_VIEW);
            arrangements = (ArrayList) arrangement_query.list();
            for (int i = 0; i < 100; i++) {
                Arrangement arrangement = (Arrangement) arrangements.get(i);
                System.out.print("id: " + arrangement.getId());
                System.out.print("\tfloors: " + arrangement.getFloor_count());
                System.out.print("\trooms: " + arrangement.getRoom_count());
                System.out.print("\ttoilets: " + arrangement.getToilet_count());
//                System.out.print("\testates: " + arrangement.getEstates().size());
//                System.out.print("\tlocations: " + arrangement.getLocations().size());
                System.out.print("\n");
            }
            System.out.println("count: " + arrangements.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return arrangements;
    }

    public List searchArrangements(Object filter, String attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Arrangement item;
        try {
            tx = session.beginTransaction();
            String search_query = (filter instanceof Integer) ? "FROM Arrangement WHERE " + attribute + " = " + filter:
                                                                "FROM Arrangement WHERE " + attribute + " like " + filter;
            Query query = session.createQuery(search_query);
            search_result = (ArrayList) query.list();
            for (Iterator i = search_result.iterator(); i.hasNext();) {
                item = (Arrangement) i.next();
                System.out.println(generateItemString(item));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        arrangements = search_result;
        return search_result;
    }

    private Long getEstate_count(Arrangement arrangement) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long result = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(e.id) FROM Arrangement a " +
                    "JOIN a.estates e WHERE a.id = :_id GROUP BY a.id");
            query.setParameter("_id", arrangement.getId());
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

    public int getListOffset() {
        Arrangement object;
        if (this.arrangements.get(0) != null) {
            object = (Arrangement) this.arrangements.get(0);
            return object.getId() - 1;
        }
        return -1;
    }

    private String generateItemString(Arrangement arrangementItem) {
        return "id: " + arrangementItem.getId() + " ; " +
                arrangementItem.getFloor_count() + " floor(s) ; " +
                arrangementItem.getRoom_count() + " room(s) ; " +
                arrangementItem.getToilet_count() + " toilet(s) " +
                arrangementItem.getBalcony() + " ; " + arrangementItem.getFurniture() + " ; " +
                arrangementItem.getPool() + " ; " + arrangementItem.getGarden();
    }

    private String generateItemString(Arrangement arrangementItem, Object estate_count) {
        return "id: " + arrangementItem.getId() + "\n" +
                "floor(s): " + arrangementItem.getFloor_count() + "\n" +
                "room(s): " + arrangementItem.getRoom_count() + "\n" +
                "toilet(s): " + arrangementItem.getToilet_count() + "\n" +
                "balcony: " + arrangementItem.getBalcony() + "\nfurniture: " + arrangementItem.getFurniture() + "\n" +
                "pool: " + arrangementItem.getPool() + "\ngarden: " + arrangementItem.getGarden() + "\n" +
                "estates with this arrangement: " + estate_count;
    }

    private Object[] generateItem(Arrangement a) {
        return new Object[]{a.getId(), a.getFloor_count(), a.getRoom_count(), a.getToilet_count(), a.getBalcony(), a.getFurniture(), a.getPool(), a.getGarden()};
    }
}
