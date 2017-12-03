package controllers;

import models.Agent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import views.Add_agent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomko on 24.4.2017.
 */
public class Manage_addAgent {
    private SessionFactory factory;
    private Add_agent addAgent;

    public Manage_addAgent(SessionFactory factory) {
        this.factory = factory;
        this.addAgent = new Add_agent();
        this.addAgent.setVisible(true);

        Listener listener = new Listener();
        this.addAgent.addCancel_buttonListener(listener);
        this.addAgent.addAdd_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(addAgent.getAdd_button())) {
                Object date = makeDate(addAgent.getD_started_at_textFieldText(), addAgent.getM_started_at_textFieldText(), addAgent.getY_started_at_textFieldText());
                addAgent(joinName(addAgent.getFirst_name_textFieldText(), addAgent.getSurname_textFieldText()),
                        addAgent.getPhone_textFieldText(),
                        date,
                        addAgent.getRating_comboBox().getSelectedItem());
                addAgent.dispose();
            }
            if (actionEvent.getSource().equals(addAgent.getCancel_button())) {
                addAgent.dispose();
            }
        }
    }

    private Integer addAgent(Object name, Object phone, Object started_at, Object rating) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer agentID = null;
        try {
            tx = session.beginTransaction();
            Agent agent = new Agent();
            agent.setName((String) name);
            agent.setPhone((String) phone);
            agent.setStarted_at((String) started_at);
            agent.setRating(parseRating((String) rating));
            agentID = (Integer) session.save(agent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding agent\t" + e.getMessage());
        } finally {
            session.close();
        }
        return agentID;
    }

    private Object makeDate(String day, String month, String year) {
        return year + "-" + month + "-" + day;
    }

    private String joinName(String a, String b) {
        return a + " " + b;
    }

    private int parseRating(String rating) {
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
            default:
                return -1;
        }
    }
}
