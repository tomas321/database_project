package controllers;

import models.Agent;
import models.Client;
import models.Location;
import org.hibernate.*;
import views.Main_window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static main.Constants.MAX_VIEW;

/**
 * Created by tomko on 18.4.2017.
 */
public class Manage_Agent {
    private SessionFactory factory;
    private Main_window mainWindow;
    private List agents;

    public Manage_Agent(SessionFactory factory, Main_window mainWindow) {
        this.factory = factory;
        this.mainWindow = mainWindow;

        Listener listener = new Listener();
        this.mainWindow.addChoose_table_buttonListener(listener);
        this.mainWindow.addAddItem_buttonListener(listener);
    }

    private class Listener implements ActionListener {
        ArrayList agents = null;
        Agent agentItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Agents")) {
                    mainWindow.getTable_content_listModel().removeAllElements();
                    agents = (ArrayList) listAgents(); // works as a charm :)
                    max_view = (agents.size() <= MAX_VIEW) ? agents.size() : MAX_VIEW;
//                    mainWindow.setPage_label((start_pos + 1) + " - " + end_pos);
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        agentItem = (Agent) agents.get(i);
                        mainWindow.addTable_content(generateItemString(agentItem));
                    }
                }
            }
//            if (a.getSource().equals(mainWindow.getAddItem_button())) {
//                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Clients")) {
//                    new Manage_addClient(factory);
//                }
//            }
        }
    }

    public Integer addAgent(Object name, Object phone, Object rating, Object started_at) {
        Session session= factory.openSession();
        Transaction tx = null;
        Integer agentID = null;
        try {
            tx = session.beginTransaction();
            Agent agent = new Agent();
            agent.setName((String) name);
            agent.setPhone((String) phone);
            agent.setRating(parseRating((String) rating));
            agent.setStarted_at((String) started_at);
            agentID = (Integer) session.save(agent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            System.out.println("error adding agent " + e.getMessage());
        } finally {
            session.close();
        }
        return agentID;
    }

    public List listAgents() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.agents = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Agent";
            Query agent_query = session.createQuery(sql_query);
            agent_query.setMaxResults(MAX_VIEW);
            agents = agent_query.list();
            for (int i = 0; i < 100; i++) {
                Agent agent = (Agent) agents.get(i);
                System.out.println(generateItemString(agent));
            }
            System.out.println("count: " + agents.size());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return agents;
    }

    private String generateItemString(Agent agent) {
        return "id: " + agent.getId() + " ; " +
                agent.getName() + " ; " +
                agent.getPhone() + " ; " +
                agent.parseRating(agent.getRating()) + " ; " +
                agent.getStarted_at();
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
                return 0; // will not happen
        }
    }
}
