package controllers;

import models.Open_house;
import models.Sold_estate;
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
                    soldEstates = (ArrayList) listSoldEstates(); // works as a charm :)
                    max_view = (soldEstates.size() <= MAX_VIEW) ? soldEstates.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
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
        }
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
        } finally {
            session.close();
        }
        return soldEstates;
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
