package controllers;

import models.Sold_estate;
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
 * Created by tomko on 18.4.2017.
 */
public class Manage_SoldEstate {
    private SessionFactory factory;
    private Main_window mainWindow;
    private ArrayList soldEstates;

    public Manage_SoldEstate(SessionFactory factory, Main_window mainWindow) {
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
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Sold estates")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Sold_estate soldEstate = (Sold_estate) soldEstates.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemInfo(soldEstate));
                    new Manage_detail(factory, detailWindow, soldEstate.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        ArrayList soldEstates = null;
        Sold_estate soldEstateItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Sold estates")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "price", "sold to", "sold at", "sold by agent"});
                    soldEstates = (ArrayList) listSoldEstates();
                    mainWindow.setResults_labelText(getSize());
                    max_view = (soldEstates.size() <= MAX_VIEW) ? soldEstates.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        soldEstateItem = (Sold_estate) soldEstates.get(i);
                        mainWindow.addItem_table_content(generateItem(soldEstateItem));
                    }
                }
            }
            if (a.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Sold estates")) {
                    new Manage_addSoldestate(factory);
                }
            }
            if (a.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Sold estates")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int soldID = ((Sold_estate) soldEstates.get(index)).getId();
                    deleteSoldEstate(soldID);
                }
            }
            if (a.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Sold estates")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "price", "sold to", "sold at", "sold by agent"});
                    soldEstates = (ArrayList) searchSoldEstates(mainWindow.getSearch_textfieldText(), mainWindow.getSearch_comboboxItem());
                    mainWindow.setResults_labelText(soldEstates.size());
                    max_view = (soldEstates.size() <= VIEW_LIMIT) ? soldEstates.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) {
                        soldEstateItem = (Sold_estate) soldEstates.get(i);
                        mainWindow.addItem_table_content(generateItem(soldEstateItem));
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
            query = session.createQuery("SELECT count(id) FROM Sold_estate");
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

    public List listSoldEstates() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Sold_estate";
            Query agent_query = session.createQuery(sql_query);
            agent_query.setMaxResults(MAX_VIEW);
            soldEstates = (ArrayList) agent_query.list();
            for (int i = 0; i < 100; i++) {
                Sold_estate soldEstate = (Sold_estate) soldEstates.get(i);
                System.out.println(generateItemString(soldEstate));
            }
            System.out.println("count: " + soldEstates.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return soldEstates;
    }

    public List searchSoldEstates(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            if (attribute.equals("id")) {
                query = session.createQuery("FROM Sold_estate WHERE id = :_filter");
                query.setParameter("_filter", Integer.parseInt((String) filter));
            } else if (attribute.equals("sold_to")) {
                query = session.createQuery("FROM Sold_estate WHERE sold_to like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("agent")) {
                query = session.createQuery("SELECT s FROM Sold_estate s JOIN s.agent a WHERE a.name like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("sold_date")) {
                query = session.createQuery("FROM Sold_estate WHERE sold_date = :_filter");
                query.setParameter("_filter", stringToDate((String) filter));
            }
            else { // else if price
                query = session.createQuery("FROM Sold_estate WHERE price = :_filter");
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
        soldEstates = search_result;
        return search_result;
    }

    public void deleteSoldEstate(Integer soldEstateID) {
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Sold_estate WHERE id = :_id");
            query.setParameter("_id", soldEstateID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] sold estate with id " + soldEstateID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
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

    private String generateItemInfo(Sold_estate soldEstate) {
        return "id: " + soldEstate.getId() + "\n" +
                "price: " + soldEstate.getPrice() + " euros\n" +
                "buyer: " + soldEstate.getSold_to() + "\n" +
                "agent: " + soldEstate.getAgent().getName() + "\n" +
                "sold at: " + soldEstate.getSold_date() + "\n" +
                "agent id: " + soldEstate.getAgent().getId();
    }

    private String generateItemString(Sold_estate soldEstate) {
        return "id: " + soldEstate.getId() + " ; " +
                soldEstate.getPrice() + "$ ; buyer: " +
                soldEstate.getSold_to() + " ; " +
                soldEstate.getSold_date();
    }

    private Object[] generateItem(Sold_estate soldEstate) {
        return new Object[]{soldEstate.getId(), soldEstate.getPrice(), soldEstate.getSold_to(), soldEstate.getSold_date(), soldEstate.getAgent().getName()};
    }
}
