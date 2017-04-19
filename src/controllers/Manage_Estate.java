package controllers;

import models.Arrangement;
import models.Estate;
import models.Location;
import org.hibernate.*;
import views.Main_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static main.Constants.MAX_VIEW;

/**
 * Created by tomko on 17.4.2017.
 */
public class Manage_Estate {
    private SessionFactory factory;
    private Main_window mainWindow;
    private List estates;
    private int max_view;

    public Manage_Estate(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        ArrayList estates = null;
        Estate estateItem;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Estates")) {
                    mainWindow.getTable_content_listModel().removeAllElements();
                    estates = (ArrayList) listEstates(); // works as a charm :)
                    max_view = (estates.size() <= MAX_VIEW) ? estates.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        estateItem = (Estate) estates.get(i);
                        mainWindow.addTable_content(generateItemString(estateItem));
                    }
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
            String sql_query = "FROM Estate";
            Query estate_query = session.createQuery(sql_query);
            estate_query.setMaxResults(MAX_VIEW);
            estates = estate_query.list();
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
}
