package controllers;

import models.Arrangement;
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
import java.util.Date;
import java.util.List;

import static main.Constants.MAX_VIEW;

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
                    detailWindow.getDetail_textArea().setText(generateItemInfo(estate));
                    new Manage_detail(factory, detailWindow, estate.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        Estate estateItem;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "build at", "category", "status", "land", "price", "city", "room count"});
                    estates = (ArrayList) listEstates(); // works as a charm :)
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
                    //deleteEstate(estate_id);
                }
            }
        }
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
        } finally {
            session.close();
        }
        return estates;
    }

    public void deleteEstate(Integer estateID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Estate estate = (Estate) session.get(Estate.class, estateID);
            session.delete(estate);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            System.out.println("estate with id " + estateID + " deleted.");
            session.close();
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

    private String generateItemInfo(Estate estate) {
        return "id: " + estate.getId() + "\n" +
                "name: " + estate.getName() + "\n" +
                "build at: " + estate.getBuild_at() + "\n" +
                "category: " + estate.parseCategory(estate.getCategory()) + "\n" +
                "status: " + estate.parseStatus(estate.getStatus()) + "\n" +
                "land: " + estate.getLand() + " m2 \n" +
                "price: " + estate.getPrice() + " euros\n" +
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
