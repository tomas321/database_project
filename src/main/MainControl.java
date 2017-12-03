package main;

import controllers.*;
import org.hibernate.SessionFactory;
import utils.Hibernate;
import views.Main_window;

/**
 * Created by tomko on 2.4.2017.
 */
public class MainControl {

    public static void main(String[] args) {
        SessionFactory factory;
        try {
            factory = Hibernate.getSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object.\t" + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Main_window window = new Main_window();
        window.setVisible(true);
        new Manage_public(window);
        new Manage_Location(factory, window);
        new Manage_Arrangement(factory, window);
        new Manage_Estate(factory, window);
        new Manage_Client(factory, window);
        new Manage_OpenHouse(factory, window);
        new Manage_Agent(factory, window);
        new Manage_SoldEstate(factory, window);
    }
}
