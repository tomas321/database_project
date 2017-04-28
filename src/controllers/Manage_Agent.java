package controllers;

import models.Agent;
import models.Client;
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
        this.mainWindow.addTable_contentMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2 && mainWindow.getTable_jcombobox().getSelectedItem().equals("Agents")) {
                    // your valueChanged overridden method
                    Detail_window detailWindow = new Detail_window();
                    detailWindow.setVisible(true);
                    Agent agent = (Agent) agents.get(row);

                    detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
                    detailWindow.getDetail_textArea().setText(generateItemString(agent, get_num_of_soldEstates(agent), get_earned_money(agent)));
                    new Manage_detail(factory, detailWindow, agent.getId());
                }
            }
        });
    }

    private class Listener implements ActionListener {
        ArrayList agents = null;
        Agent agentItem = null;
        int max_view;
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource().equals(mainWindow.getChoose_table_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Agents")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "name", "phone", "rating", "started at"});
                    agents = (ArrayList) listAgents(); // works as a charm :)
                    max_view = (agents.size() <= MAX_VIEW) ? agents.size() : MAX_VIEW;
                    for (int i = 0; i < MAX_VIEW; i++) { // display first 100 locations in the main window
                        agentItem = (Agent) agents.get(i);
                        mainWindow.addItem_table_content(generateItem(agentItem));
                    }
                }
            }
            if (a.getSource().equals(mainWindow.getAddItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Agents")) {
                    new Manage_addAgent(factory);
                }
            }
        }
    }

    public List listAgents() {
        Session session = factory.openSession();
        Transaction tx = null;
        this.agents = null;
        try {
            tx = session.beginTransaction();
            String sql_query = "FROM Agent a ORDER BY a.id";
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

    private Long get_num_of_soldEstates(Agent agent) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long result = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(s.id) FROM Agent a " +
                    "JOIN a.sold_estates s WHERE a.id = :agentID GROUP BY a.name");
            query.setParameter("agentID", agent.getId());
            result = (Long) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    // query for bot earned money and number of sold estates
    /**
     * select sum(s.price) as earned, count(s.price) as count
     * from agents a
     * join sold_estates s on s.agent_id = a.id
     * where a.id = 15
     * group by a.id
     */
    private Double get_earned_money(Agent agent) {
        Session session = factory.openSession();
        Transaction tx = null;
        Double result = 0.0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT sum(s.price) FROM Agent a " +
                    "JOIN a.sold_estates s WHERE a.id = :agentID GROUP BY a.name");
            query.setParameter("agentID", agent.getId());
            result = (Double) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    private String generateItemString(Agent agent) {
        return "id: " + agent.getId() + " ; " +
                agent.getName() + " ; " +
                agent.getPhone() + " ; " +
                agent.parseRating(agent.getRating()) + " ; " +
                agent.getStarted_at();
    }

    private String generateItemString(Agent agent, Object sold_count, Object earned) {
        return "id: " + agent.getId() + "\n" +
                "name: " + agent.getName() + "\n" +
                "phone: " + agent.getPhone() + "\n" +
                "rating: " + agent.parseRating(agent.getRating()) + "\n" +
                "started at: " + agent.getStarted_at() + "\n" +
                "number of sold estates: " + sold_count + "\n" +
                "earned money: " + earned + " euros";
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
            default:
                return 0; // will not happen
        }
    }

    private Object[] generateItem(Agent agent) {
        return new Object[]{agent.getId(), agent.getName(), agent.getPhone(), agent.parseRating(agent.getRating()), agent.getStarted_at()};
    }
}
