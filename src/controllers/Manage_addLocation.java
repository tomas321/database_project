package controllers;

import models.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import views.Add_location;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomko on 18.4.2017.
 */
public class Manage_addLocation {
    private Add_location addLocation;
    private SessionFactory factory;
    private Integer newObjectID;

    public Manage_addLocation(SessionFactory factory) {
        //System.out.println("got in\n");
        this.factory = factory;
        this.addLocation = new Add_location();
        this.addLocation.setVisible(true);

        Listener listener = new Listener();
        this.addLocation.addAdd_buttonListener(listener);
        this.addLocation.addCancel_buttonListener(listener);
    }

    public Manage_addLocation(SessionFactory factory, Integer newObjectID) {
        this.factory = factory;
        this.newObjectID = newObjectID;
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addLocation.getAdd_button())) {
                newObjectID = addLocation(addLocation.getCity_textFieldText(), addLocation.getStreet_textFieldText());
                addLocation.dispose();
                System.out.println("new id: " + newObjectID);
            }
            if (actionEvent.getSource().equals(addLocation.getCancel_button())) {
                addLocation.dispose();
                newObjectID = -1;
            }
        }
    }

    public Integer addLocation(Object city, Object street) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer locationID = null;
        try {
            tx = session.beginTransaction();
            Location location = new Location();
            location.setCity((String) city);
            location.setStreet((String) street);
            locationID = (Integer) session.save(location);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding location\t" + e.getMessage());
        } finally {
            session.close();
        }
        return locationID;
    }

    public Integer getNewObjectID() {
        return newObjectID;
    }
}
