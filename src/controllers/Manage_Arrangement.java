package controllers;

import models.Arrangement;
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
        this.mainWindow.addDelete_buttonListener(listener);
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
        ArrayList arrangements = null;
        Arrangement arrangementItem = null;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "floor count", "room count", "toilet count", "balcony", "furniture", "pool", "garden"});
                    arrangements = (ArrayList) listArrangements();
                    mainWindow.setResults_labelText(getSize());
                    max_view = (arrangements.size() <= MAX_VIEW) ? arrangements.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        arrangementItem = (Arrangement) arrangements.get(i);
                        mainWindow.addItem_table_content(generateItem(arrangementItem));
                    }
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "floor count", "room count", "toilet count", "balcony", "furniture", "pool", "garden"});
                    arrangements = (ArrayList) searchArrangements(mainWindow.getSearch_textfieldText(), (String) mainWindow.getSearch_comboboxItem());
                    mainWindow.setResults_labelText(arrangements.size());
                    max_view = (arrangements.size() <= VIEW_LIMIT) ? arrangements.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) {
                        arrangementItem = (Arrangement) arrangements.get(i);
                        mainWindow.addItem_table_content(generateItem(arrangementItem));
                    }
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    new Manage_addArrangement(factory);
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Arrangements")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int arrID = ((Arrangement) arrangements.get(index)).getId();
                    deleteArrangement(arrID);
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
            query = session.createQuery("SELECT count(id) FROM Arrangement ");
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

    public List listArrangements() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.arrangements = null;
        try {
            tx = session.beginTransaction();
            // do whatever
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
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return arrangements;
    }

    public void deleteArrangement(Integer arrangementID) {
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Arrangement WHERE id = :_id");
            query.setParameter("_id", arrangementID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] location with id " + arrangementID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
    }

    public List searchArrangements(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Arrangement item;
        Query query;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("FROM Arrangement WHERE " + attribute + " = :_filter");
            if (attribute.equals("id") || attribute.equals("rooms") || attribute.equals("floors") || attribute.equals("toilets"))
                query.setParameter("_filter", Integer.parseInt((String) filter));
            else query.setParameter("_filter", parseBoolean((String) filter));

            search_result = (ArrayList) query.list();
//            for (Iterator i = search_result.iterator(); i.hasNext();) {
//                item = (Arrangement) i.next();
//                System.out.println(generateItemString(item));
//            }
            System.out.println("[SEARCH] search query: " + query.getQueryString());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "bad filter string");
        } finally {
            session.close();
        }
        arrangements = search_result;
        return search_result;
    }

    private long getEstate_count(Arrangement arrangement) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList result;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(e.id) FROM Arrangement a " +
                    "JOIN a.estates e WHERE a.id = :_id GROUP BY a.id");
            query.setParameter("_id", arrangement.getId());
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

    private boolean parseBoolean(String input) {
        if (input.equals("with") || input.equals("1") || input.equals("true")) return true;
        else if (input.equals("without") || input.equals("0") || input.equals("false")) return false;
        return false;
    }
}
