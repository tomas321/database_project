package controllers;

import models.Estate;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static main.Constants.MAX_VIEW;
import static main.Constants.VIEW_LIMIT;

/**
 * Created by tomko on 17.4.2017.
 */
public class Manage_Estate {
    private SessionFactory factory;
    private Main_window mainWindow;
    private ArrayList estates;
    private int max_view;

    public Manage_Estate(SessionFactory factory, Main_window mainWindow) {
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
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Estate estate = (Estate) estates.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemInfo(estate, get_num_of_openHouses(estate)));
                    new Manage_detail(factory, detailWindow, estate.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        Estate estateItem;
        ArrayList estates = null;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "build at", "category", "status", "land", "price", "city", "room count"});
                    estates = (ArrayList) listEstates();
                    mainWindow.setResults_labelText(getSize());
                    max_view = (estates.size() <= MAX_VIEW) ? estates.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        estateItem = (Estate) estates.get(i);
                        mainWindow.addItem_table_content(generateItem(estateItem));
                    }
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    new Manage_addEstate(factory);
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int estate_id = ((Estate) estates.get(index)).getId();
                    deleteEstate(estate_id);
                }
            }
            if (actionEvent.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "build at", "category", "status", "land", "price", "city", "room count"});
                    estates = (ArrayList) searchEstates(mainWindow.getSearch_textfieldText(), mainWindow.getSearch_comboboxItem());
                    mainWindow.setResults_labelText(estates.size());
                    max_view = (estates.size() <= VIEW_LIMIT) ? estates.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        estateItem = (Estate) estates.get(i);
                        mainWindow.addItem_table_content(generateItem(estateItem));
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
            query = session.createQuery("SELECT count(id) FROM Estate");
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

    public List listEstates() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.estates = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Estate e ORDER BY e.id";
            Query estate_query = session.createQuery(sql_query);
            estate_query.setMaxResults(MAX_VIEW);
            estates = (ArrayList) estate_query.list();
            for (int i = 0; i < 100; i++) {
                Estate estate = (Estate) estates.get(i);
                System.out.println(generateItemString(estate));
            }
            System.out.println("count: " + estates.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return estates;
    }

    public List searchEstates(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            if (attribute.equals("id") || attribute.equals("land")) {
                query = session.createQuery("FROM Estate WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", Integer.parseInt((String) filter));
            } else if (attribute.equals("name")) {
                query = session.createQuery("FROM Estate WHERE " + attribute + " like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("build_at")) {
                query = session.createQuery("FROM Estate WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", stringToDate((String) filter));
            } else if (attribute.equals("category")) {
                query = session.createQuery("FROM Estate WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", parseCategory((String) filter));
            } else if (attribute.equals("status")) {
                query = session.createQuery("FROM Estate WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", parseStatus((String) filter));
            } else if (attribute.equals("city")) {
                query = session.createQuery("SELECT e FROM Estate e JOIN e.location l WHERE l.city like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("rooms")) {
                query = session.createQuery("SELECT e FROM Estate e JOIN e.arrangement a WHERE a.room_count = :_filter");
                query.setParameter("_filter", Integer.parseInt((String) filter));
            }
            else { // else price of type float
                query = session.createQuery("FROM Estate WHERE " + attribute + " = :_filter");
                query.setParameter("_filter", Float.parseFloat((String) filter));
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
        estates = search_result;
        return search_result;
    }

    public void deleteEstate(Integer estateID){
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Estate WHERE id = :_id");
            query.setParameter("_id", estateID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] estate with id " + estateID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
    }

    private long get_num_of_openHouses(Estate estate){
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList result;
        long count = 0;
        Query query;
        try{
            tx = session.beginTransaction();
            query = session.createQuery("SELECT count(o.id) FROM Estate e " +
                    "JOIN e.open_houses o WHERE e.id = :_id GROUP BY e.id");
            query.setParameter("_id", estate.getId());
            result = (ArrayList) query.list();
            if (result.size() > 0) count = (long) result.get(0);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return count;
    }

    private Date stringToDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer parseStatus(String status) {
        switch (status) {
            case "new":
                return 1;
            case "second hand":
                return 2;
            default:
                return 1; // make default as new
        }
    }

    private Integer parseCategory(String category) {
        switch (category) {
            case "hosue":
                return 1;
            case "flat":
                return 2;
            case "villa":
                return 3;
            default:
                return 1; // make default as house
        }
    }

    private String generateItemString(Estate estate) {
        Location location = estate.getLocation();
        return "id: " + estate.getId() + " ; " +
                estate.getName() + " ; from " +
                estate.getBuild_at() + " ; " +
                estate.parseCategory(estate.getCategory()) + " ; " +
                estate.parseStatus(estate.getStatus()) + " ; " +
                estate.getLand() + " m2 ; " +
                estate.getPrice() + "$";
    }

    private String generateItemInfo(Estate estate, Object house_count) {
        return "id: " + estate.getId() + "\n" +
                "name: " + estate.getName() + "\n" +
                "build at: " + estate.getBuild_at() + "\n" +
                "category: " + estate.parseCategory(estate.getCategory()) + "\n" +
                "status: " + estate.parseStatus(estate.getStatus()) + "\n" +
                "land: " + estate.getLand() + " m2 \n" +
                "price: " + estate.getPrice() + " euros\n" +
                "number of open houses: " + house_count + "\n" +
                "additional info:\n" +
                "arrangement id: " + estate.getArrangement().getId() + "\n" +
                "location id: " + estate.getLocation().getId();
    }

    private Object[] generateItem(Estate estate) {
        return new Object[]{estate.getId(),
                estate.getBuild_at(),
                estate.parseCategory(estate.getCategory()),
                estate.parseStatus(estate.getStatus()),
                estate.getLand(),
                estate.getPrice(),
                estate.getLocation().getCity(),
                estate.getArrangement().getRoom_count()
        };
    }
}
