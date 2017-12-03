package controllers;

import models.Agent;
import models.Sold_estate;
import org.hibernate.*;
import views.Add_soldestate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomko on 25.4.2017.
 */
public class Manage_addSoldestate {
    private SessionFactory factory;
    private Add_soldestate addSoldestate;

    public Manage_addSoldestate(SessionFactory factory) {
        this.factory = factory;
        this.addSoldestate = new Add_soldestate();
        this.addSoldestate.setVisible(true);

        Listener listener = new Listener();
        this.addSoldestate.addAdd_buttunListener(listener);
        this.addSoldestate.addCancel_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addSoldestate.getAdd_button())) {
                String date = makeDate(addSoldestate.getD_date_textFieldText(), addSoldestate.getM_date_textFieldText(), addSoldestate.getY_date_textFieldText());
                addSoldestate(date,
                        addSoldestate.getPrice_textFieldText(),
                        addSoldestate.getSold_to_textFieldText(),
                        addSoldestate.getSold_by_textFieldText());
                addSoldestate.dispose();
            }
            if (actionEvent.getSource().equals(addSoldestate.getCancel_button())) {
                addSoldestate.dispose();
            }
        }
    }

    private Integer addSoldestate(String date, String price, String client_name, String agent_name) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer soldestateID = null;
        Agent agent;
        try {
            tx = session.beginTransaction();
            Sold_estate soldEstate = new Sold_estate();
            String agent_query_str = "FROM Agent a WHERE a.name like :a_name";
            Query agent_query = session.createQuery(agent_query_str);
            agent_query.setParameter("a_name", agent_name);
            agent = (Agent) agent_query.list().get(0);

            soldEstate.setAgent(agent);
            soldEstate.setSold_to(client_name);
            soldEstate.setSold_date(date);
            soldEstate.setPrice(Double.parseDouble(price));

            soldestateID = (Integer) session.save(soldEstate);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding sold estate\t" + e.getMessage());
        } finally {
            session.close();
        }
        return soldestateID;
    }

    private String makeDate(String day, String month, String year) {
        return year + "-" + month + "-" + day;
    }
}
