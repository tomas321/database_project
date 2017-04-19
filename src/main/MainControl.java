package main;

import controllers.*;
import org.hibernate.SessionFactory;
import utils.Hibernate;
import views.Main_window;

/**
 * Created by tomko on 2.4.2017.
 */
public class MainControl {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = Hibernate.getSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object.\t" + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Main_window window = new Main_window();
        window.setVisible(true);
        new Manage_public(window);
        Manage_location manageLocation = new Manage_location(factory, window);
        Manage_Arrangement manageArrangement = new Manage_Arrangement(factory, window);
        Manage_Estate manageEstate = new Manage_Estate(factory, window);
        Manage_Client manageClient = new Manage_Client(factory, window);
        Manage_OpenHouse manageOpenHouse = new Manage_OpenHouse(factory, window);
        Manage_Agent manageAgent = new Manage_Agent(factory, window);
        Manage_SoldEstate manageSoldEstate = new Manage_SoldEstate(factory, window);

//        Integer locID1 = manageLocation.addLocation("Banska Bystrica", "Hviezdoslavova ulica");
//        Integer locID2 = manageLocation.addLocation("Banska Bystrica", "Chorcheho");
    }
}
