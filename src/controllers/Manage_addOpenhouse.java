package controllers;

import models.Agent;
import models.Client;
import models.Estate;
import models.Open_house;
import org.hibernate.*;
import views.Add_openhouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomko on 25.4.2017.
 */
public class Manage_addOpenhouse {
    private SessionFactory factory;
    private Add_openhouse addOpenhouse;

    public Manage_addOpenhouse(SessionFactory factory) {
        this.factory = factory;
        this.addOpenhouse = new Add_openhouse();
        this.addOpenhouse.setVisible(true);

        Listener listener = new Listener();
        this.addOpenhouse.addAdd_buttunListener(listener);
        this.addOpenhouse.addCancel_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addOpenhouse.getAdd_button())) {
                String dateTime = makeDateTime(addOpenhouse.getH_time_textFieldText(),
                        addOpenhouse.getM_time_textFieldText(),
                        addOpenhouse.getD_date_textFieldText(),
                        addOpenhouse.getM_date_textFieldText(),
                        addOpenhouse.getY_date_textFieldText());
                addOpenhouse(dateTime,
                        addOpenhouse.getClient_textFieldText(),
                        addOpenhouse.getAgent_textFieldText(),
                        Integer.parseInt(addOpenhouse.getPick_estate_textFieldText()));
                addOpenhouse.dispose();
            }
            if (actionEvent.getSource().equals(addOpenhouse.getCancel_button())) {
                addOpenhouse.dispose();
            }
        }
    }

    private Integer addOpenhouse(String datetime, String client_name, String agent_name, Integer estateID) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer openhouseID = null;
        Client client;
        Agent agent;
        try {
            tx = session.beginTransaction();
            Open_house openHouse = new Open_house();

            String client_query_str = "FROM Client c WHERE c.name like :c_name";
            Query clientQuery = session.createQuery(client_query_str);
            clientQuery.setParameter("c_name", client_name);
            client = (Client) clientQuery.list().get(0);
            String agent_query_str = "FROM Agent a WHERE a.name like :a_name";
            Query agentQuery = session.createQuery(agent_query_str);
            agentQuery.setParameter("a_name", agent_name);
            agent = (Agent) agentQuery.list().get(0);
            Estate estate = (Estate) session.get(Estate.class, estateID);

            openHouse.setTime_at(datetime);
            openHouse.setAgent(agent);
            openHouse.setClient(client);
            openHouse.setEstate(estate);

            openhouseID = (Integer) session.save(openHouse);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding open house\t" + e.getMessage());
        } finally {
            session.close();
        }
        return openhouseID;
    }

    private String makeDateTime(String hour, String minute, String day, String month, String year) {
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
    }
}
