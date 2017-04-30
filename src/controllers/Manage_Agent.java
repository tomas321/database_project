package controllers;

import models.Agent;
import org.hibernate.*;
import views.Detail_window;
import views.ErrorMessage;
import views.Main_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static main.Constants.MAX_VIEW;
import static main.Constants.VIEW_LIMIT;

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
        this.mainWindow.addDelete_buttonListener(listener);
        this.mainWindow.addSearch_buttonListener(listener);
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
                    agents = (ArrayList) listAgents();
                    mainWindow.setResults_labelText(getSize());
                    max_view = (agents.size() <= MAX_VIEW) ? agents.size() : MAX_VIEW;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
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
            if (a.getSource().equals(mainWindow.getDeleteItem_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Agents")) {
                    int index = mainWindow.getContent_table().getSelectedRow();
                    int houseID = ((Agent) agents.get(index)).getId();
                    deleteAgent(houseID);
                }
            }
            if (a.getSource().equals(mainWindow.getSearch_button())) {
                if (mainWindow.getTable_jcombobox().getSelectedItem().equals("Agents")) {
                    mainWindow.setUp_table_content(new Object[]{"id", "name", "phone", "rating", "started at"});
                    agents = (ArrayList) searchSoldEstates(mainWindow.getSearch_textfieldText(), mainWindow.getSearch_comboboxItem()); // works as a charm :)
                    mainWindow.setResults_labelText(agents.size());
                    max_view = (agents.size() <= VIEW_LIMIT) ? agents.size() : VIEW_LIMIT;
                    for (int i = 0; i < max_view; i++) { // display first 100 locations in the main window
                        agentItem = (Agent) agents.get(i);
                        mainWindow.addItem_table_content(generateItem(agentItem));
                    }
                }
            }
        }
    }

    public Long getSize() {
        Session session = factory.openSession();
        Transaction tx = null;
        Long result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("SELECT count(id) FROM Agent");
            result = (Long) query.list().get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "get amount error");
        } finally {
            session.close();
        }
        return result;
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
            new ErrorMessage(mainWindow, "cannot connect to database");
        } finally {
            session.close();
        }
        return agents;
    }

    public List searchSoldEstates(Object filter, Object attribute) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList search_result = null;
        Query query;
        try {
            tx = session.beginTransaction();
            if (attribute.equals("id")) {
                query = session.createQuery("FROM Agent WHERE id = :_filter");
                query.setParameter("_filter", Integer.parseInt((String) filter));
            } else if (attribute.equals("name") || attribute.equals("phone")) {
                query = session.createQuery("FROM Agent WHERE " + attribute + " like :_filter");
                query.setParameter("_filter", "%" + filter + "%");
            } else if (attribute.equals("started_at")) {
                query = session.createQuery("FROM Agent WHERE started_at = :_filter");
                query.setParameter("_filter", stringToDate((String) filter));
            } else { // else if rating
                query = session.createQuery("FROM Agent WHERE rating = :_filter");
                query.setParameter("_filter", parseRating((String) filter));
            }
            search_result = (ArrayList) query.list();
            System.out.println("[SEARCH] search query: " + query.getQueryString());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "bad filter string");
        } finally {
            session.close();
        }
        agents = search_result;
        return search_result;
    }

    public void deleteAgent(Integer agentID) {
        Session session = factory.openSession();
        Transaction tx = null;
        int affected = -1;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Agent WHERE id = :_id");
            query.setParameter("_id", agentID);
            affected = query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            new ErrorMessage(mainWindow, "CANNOT DELETE\nother objects rely on this entry");
        }finally {
            System.out.println("[DELETE] sold estate with id " + agentID + " deleted. [ROWS] rows affected: " + affected);
            session.close();
        }
    }

    private long get_num_of_soldEstates(Agent agent) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList result;
        long count = 0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT count(s.id) FROM Agent a " +
                    "JOIN a.sold_estates s WHERE a.id = :agentID GROUP BY a.name");
            query.setParameter("agentID", agent.getId());
            result = (ArrayList) query.list();
            if (result.size() > 0) count = (long) result.get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    // query for bot earned money and number of sold estates
    private float get_earned_money(Agent agent) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList result;
        float count = 0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT sum(s.price) FROM Agent a " +
                    "JOIN a.sold_estates s WHERE a.id = :agentID GROUP BY a.name");
            query.setParameter("agentID", agent.getId());
            result = (ArrayList) query.list();
            if (result.size() > 0) count = (float) result.get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    private Date stringToDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String generateItemString(Agent agent) {
        return "id: " + agent.getId() + " ; " +
                agent.getName() + " ; " +
                agent.getPhone() + " ; " +
                agent.parseRating(agent.getRating()) + " ; " +
                agent.getStarted_at();
    }

    private String generateItemString(Agent agent, Object sold_count, Object earned) {
        if (sold_count == null) sold_count = 0;
        if (earned == null) earned = 0;
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
                return 1; // will not happen
        }
    }

    private Object[] generateItem(Agent agent) {
        return new Object[]{agent.getId(), agent.getName(), agent.getPhone(), agent.parseRating(agent.getRating()), agent.getStarted_at()};
    }
}
