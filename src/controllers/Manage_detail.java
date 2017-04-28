package controllers;

import models.*;
import org.hibernate.*;
import views.Detail_window;
import views.ErrorMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.Constants.*;

/**
 * Created by tomko on 28.4.2017.
 */
public class Manage_detail {
    private Detail_window detailWindow;
    private SessionFactory factory;
    private Integer itemId;

    /** the current state is
     * update works
     * select works
     * search by in client works
     * delete doesnt work
     * insert works
     * detail + edit works
     */

    public Manage_detail(SessionFactory factory, Detail_window detailWindow, Integer item_id) {
        this.detailWindow = detailWindow;
        this.factory = factory;
        this.itemId = item_id;
        this.fill_combobox();

        Listener listener = new Listener();
        this.detailWindow.addUdate_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            update_attribute((String) detailWindow.getEdit_comboBox().getSelectedItem(), detailWindow.getEdit_textField());
        }
    }

    private void update_attribute(String attribute, Object value) {
        Session session = factory.openSession();
        Transaction tx = null;
        Query query;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            if (this.detailWindow.getTable().equals("Arrangements")) {
                if (attribute.equals("rooms") || attribute.equals("toilets") || attribute.equals("floors")) { // if integer
                    query = session.createQuery("UPDATE Arrangement SET " + attribute + " = :new_value WHERE id = :_id");
                    query.setParameter("new_value", Integer.parseInt((String) value));
                } else { // else is boolean
                    Integer bool = parseBoolValues((String) value);
                    query = session.createQuery("UPDATE Arrangement SET " + attribute + " = :new_value WHERE id = :_id");
                    query.setParameter("new_value", bool);
                }
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();

            } else if (this.detailWindow.getTable().equals("Locations")) {
                query = session.createQuery("UPDATE Location SET " + attribute + " = :new_value WHERE id = :_id");
                query.setParameter("new_value", value);
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();

            } else if (this.detailWindow.getTable().equals("Estates")) {
                if (attribute.equals("arrangement_id") || attribute.equals("location_id") || attribute.equals("land")) {
                    query = session.createQuery("UPDATE Estate SET " + attribute + " = :new_value WHERE id = :_id");
                    Object new_object = session.get(myGetClass(attribute), Integer.parseInt((String) value));
                    query.setParameter("new_value", new_object);
                } else if (attribute.equals("name") || attribute.equals("build_at")) {
                    query = session.createQuery("UPDATE Estate SET " + attribute + " = :new_value WHERE id = :_id");
                    query.setParameter("new_value", value);
                } else if (attribute.equals("price")) {
                    query = session.createQuery("UPDATE Estate SET price = :new_value WHERE id = :_id");
                    query.setParameter("new_value", Float.parseFloat((String) value));
                } else if (attribute.equals("status")) {
                    query = session.createQuery("UPDATE Estate SET status = :new_value WHERE id = :_id");
                    query.setParameter("new_value", parseStatus((String) value));
                } else { // else category
                    query = session.createQuery("UPDATE Estate SET category = :new_value WHERE id = :_id");
                    query.setParameter("new_value", parseCategory((String) value));
                }
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();

            } else if (this.detailWindow.getTable().equals("Open houses")) {
                if (attribute.equals("client_id") || attribute.equals("estate_id") || attribute.equals("agent_id")) {
                    query = session.createQuery("UPDATE Open_house SET " + attribute + " = :new_value WHERE id = :_id");
                    Object new_object = session.get(myGetClass(attribute), Integer.parseInt((String) value));
                    query.setParameter("new_value", new_object);
                } else {
                    query = session.createQuery("UPDATE Open_house SET time_at = :new_value WHERE id = :_id");
                    query.setParameter("new_value", value);
                }
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();

            } else if (this.detailWindow.getTable().equals("Clients")) {
                query = session.createQuery("UPDATE Client SET " + attribute + " = :new_value WHERE id = :_id");
                query.setParameter("new_value", value);
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();

            } else if (this.detailWindow.getTable().equals("Agents")) {
                if (attribute.equals("rating")) {
                    query = session.createQuery("UPDATE Agent SET rating = :new_value WHERE id = :_id");
                    query.setParameter("new_value", parseRating((String) value));
                } else {
                    query = session.createQuery("UPDATE Agent SET " + attribute + " = :new_value WHERE id = :_id");
                    query.setParameter("new_value", value);
                }
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();

            } else if (this.detailWindow.getTable().equals("Sold estates")) {
                if (attribute.equals("agent_id")) {
                    query = session.createQuery("UPDATE Sold_estate SET agent = :new_value WHERE id = :_id");
                    Agent agent = (Agent) session.get(Agent.class, Integer.parseInt((String) value)); // get new agent with given id
                    query.setParameter("new_value", agent);
                } else if (attribute.equals("price")) {
                    query = session.createQuery("UPDATE Sold_estate SET price = :new_value WHERE id = :_id");
                    query.setParameter("new_value", Float.parseFloat((String) value));
                } else {
                    query = session.createQuery("UPDATE Sold_estate SET " + attribute + " = :new_value WHERE id = :_id");
                    query.setParameter("new_value", value);
                }
                query.setParameter("_id", itemId);
                affected = query.executeUpdate();
            }

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(detailWindow, "bad update.");
        }finally {
            session.close();
            System.out.println("[UPDATE] rows affected: " + affected);
        }
    }

    private Class myGetClass(String input) {
        switch (input) {
            case "arrangement_id":
                return Arrangement.class;
            case "client_id":
                return Client.class;
            case  "agent_id":
                return Agent.class;
            case "location_id":
                return Location.class;
            case "estate_id":
                return Estate.class;
            default:
                return null;
        }
    }

    private Integer parseCategory(String categ) {
        switch (categ) {
            case "house":
                return 1;
            case "1":
                return 1;
            case "flat":
                return 2;
            case "2":
                return 2;
            case "villa":
                return 3;
            case "3":
                return 3;
            default:
                return 0;
        }
    }

    private Integer parseStatus(String status) {
        switch (status) {
            case "new":
                return 1;
            case "1":
                return 1;
            case "second hand":
                return 2;
            case "2":
                return 2;
            default:
                return 0;
        }
    }

    private Integer parseRating(String rating) {
        switch (rating) {
            case "amateur":
                return 1;
            case "satisfactory":
                return 2;
            case "good":
                return 3;
            case "persuasive":
                return 4;
            case "experienced":
                return 5;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            default:
                return 0;
        }
    }

    private Integer parseBoolValues(String val) {
        switch (val) {
            case "with":
                return 1;
            case "has":
                return 1;
            case "1":
                return 1;
            case "without":
                return 0;
            case "0":
                return 0;
            default:
                return -1;
        }
    }

    private void fill_combobox() {
        if (this.detailWindow.getTable().equals("Arrangements")) {
            detailWindow.setEdit_comboBox(ARRANGEMENT_ITEMS_EDITABLE);
        } else if (this.detailWindow.getTable().equals("Locations")) {
            detailWindow.setEdit_comboBox(LOCATION_ITEMS_EDITABLE);
        } else if (this.detailWindow.getTable().equals("Estates")) {
            detailWindow.setEdit_comboBox(ESTATE_ITEMS_EDITABLE);
        } else if (this.detailWindow.getTable().equals("Open houses")) {
            detailWindow.setEdit_comboBox(OPEN_HOUSE_ITEMS_EDITABLE);
        } else if (this.detailWindow.getTable().equals("Clients")) {
            detailWindow.setEdit_comboBox(CLIENT_ITEMS_EDITABLE);
        } else if (this.detailWindow.getTable().equals("Agents")) {
            detailWindow.setEdit_comboBox(AGENT_ITEMS_EDITABLE);
        } else if (this.detailWindow.getTable().equals("Sold estates")) {
            detailWindow.setEdit_comboBox(SOLD_ESTATE_ITEMS_EDITABLE);
        }
    }

}
