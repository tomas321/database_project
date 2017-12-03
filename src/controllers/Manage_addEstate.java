package controllers;

import models.Arrangement;
import models.Estate;
import models.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import views.Add_estate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by tomko on 23.4.2017.
 */
public class Manage_addEstate {
    private Add_estate addEstate;
    private SessionFactory factory;
    private Integer newObjectID;

    public Manage_addEstate(SessionFactory factory) {
        this.factory = factory;
        this.addEstate = new Add_estate();
        this.addEstate.setVisible(true);

        Listener listener = new Listener();
        this.addEstate.addAdd_buttonListener(listener);
        this.addEstate.addCancel_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        Integer locationID, arrangementID;
        Manage_addLocation manageAddLocation;
        Manage_addArrangement manageAddArrangement;
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addEstate.getCancel_button())) {
                addEstate.dispose();
                newObjectID = -1;
            }
            if (actionEvent.getSource().equals(addEstate.getAdd_button())) {
                if (addEstate.getLocation_checkBox().isSelected()) {
                    manageAddLocation = new Manage_addLocation(factory, null);
                    locationID = manageAddLocation.addLocation(addEstate.getCity_textFieldText(), addEstate.getStreet_textFieldText());
                } else {
                    locationID = Integer.parseInt(addEstate.getLocation_id_textFieldText());
                }
                if (addEstate.getArrangement_checkBox().isSelected()) {
                    manageAddArrangement = new Manage_addArrangement(factory, null);
                    arrangementID = manageAddArrangement.addArrangement(addEstate.getRooms_comboBox().getSelectedItem(),
                                                                    addEstate.getFloors_comboBox().getSelectedItem(),
                                                                    addEstate.getFurniture_comboBox().getSelectedItem(),
                                                                    addEstate.getBalcony_comboBox().getSelectedItem(),
                                                                    addEstate.getGarden_comboBox().getSelectedItem(),
                                                                    addEstate.getPool_comboBox().getSelectedItem(),
                                                                    addEstate.getToilets_comboBox().getSelectedItem());
                } else {
                    arrangementID = Integer.parseInt(addEstate.getArrangement_id_textFieldText());
                }
                Object date = makeDate(addEstate.getD_build_at_textFieldText(), addEstate.getM_build_at_textFieldText(), addEstate.getY_build_at_textFieldText());
                newObjectID = addEstate(addEstate.getName_textFieldText(),
                        addEstate.getStatus_comboBox().getSelectedItem(),
                        addEstate.getCategory_comboBox().getSelectedItem(),
                        addEstate.getLand_textFieldText(),
                        date,
                        addEstate.getPrice_textFieldText(),
                        locationID,
                        arrangementID);
                addEstate.dispose();
            }
        }
    }

    private Integer addEstate(Object name, Object status, Object category, Object land, Object build_at, Object price, Object location_id, Object arrange_id) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer estateID = null;
        try {
            tx = session.beginTransaction();
            Estate estate = new Estate();
            estate.setName((String) name);
            estate.setStatus(parseStatus((String) status));
            estate.setCategory(parseCategory((String) category));
            estate.setLand(Integer.parseInt((String) land));
            estate.setBuild_at((String) build_at);
            estate.setPrice(Double.parseDouble((String) price));

            List locations = session.createQuery("FROM Location l WHERE l.id = " + location_id).list();
            estate.setLocation((Location) locations.get(0));

            List arrangements = session.createQuery("FROM Arrangement  a WHERE a.id = " + arrange_id).list();
            estate.setArrangement((Arrangement) arrangements.get(0));

            estateID = (Integer) session.save(estate);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding estate\t" + e.getMessage());
        } finally {
            session.close();
        }
        return estateID;
    }

    private int parseStatus(String status) {
        switch (status) {
            case "new":
                return 1;
            case "second hand":
                return 2;
            default:
                return -1;
        }
    }

    private int parseCategory(String category) {
        switch (category) {
            case "hosue":
                return 1;
            case "flat":
                return 2;
            case "villa":
                return 3;
            default:
                return -1;
        }
    }

    private Object makeDate(String day, String month, String year) {
        return year + "-" + month + "-" + day;
    }

    public Integer getNewObjectID() {
        return newObjectID;
    }
}
