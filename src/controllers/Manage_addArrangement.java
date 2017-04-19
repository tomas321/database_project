package controllers;

import models.Arrangement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import views.Add_arrangement;
import views.Main_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomko on 17.4.2017.
 */
public class Manage_addArrangement {
    private Add_arrangement addArrangement;
    private SessionFactory factory;

    public Manage_addArrangement(SessionFactory factory) {
        this.addArrangement = new Add_arrangement(); // create window object
        this.addArrangement.setVisible(true);
        this.factory = factory;

        Listener listener = new Listener();
        this.addArrangement.addAdd_buttonListener(listener);
        this.addArrangement.addCancel_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addArrangement.getAdd_button())) {
                addArrangement(addArrangement.getRooms_comboBox().getSelectedItem(),
                            addArrangement.getFloors_comboBox().getSelectedItem(),
                            addArrangement.getFurniture_comboBox().getSelectedItem(),
                            addArrangement.getBalcony_comboBox().getSelectedItem(),
                            addArrangement.getGarden_comboBox().getSelectedItem(),
                            addArrangement.getPool_comboBox().getSelectedItem(),
                            addArrangement.getToilets_comboBox().getSelectedItem());
                addArrangement.dispose();
            }
            if (actionEvent.getSource().equals(addArrangement.getCancel_button())) {
                addArrangement.dispose();
            }
        }
    }

    public Integer addArrangement(Object room_count, Object floor_count, Object furniture, Object balcony, Object garden, Object pool, Object toilets) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer arrangementID = null;
        try {
            tx = session.beginTransaction();
            Arrangement arrangement = new Arrangement();
            arrangement.setRoom_count(Integer.parseInt((String) room_count));
            arrangement.setFloor_count(Integer.parseInt((String) floor_count));
            arrangement.setFurniture(parseBoolean((String) furniture));
            arrangement.setBalcony(parseBoolean((String) balcony));
            arrangement.setGarden(parseBoolean((String) garden));
            arrangement.setPool(parseBoolean((String) pool));
            arrangement.setToilet_count(Integer.parseInt((String) toilets));
            arrangementID = (Integer) session.save(arrangement);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding arrangement\t" + e.getMessage());
        } finally {
            session.close();
        }
        return arrangementID;
    }

    public boolean parseBoolean(String input) {
        return (input.equals("with"));
    }
}
