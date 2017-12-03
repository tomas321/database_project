package controllers;

import views.Main_window;

import java.awt.event.*;

import static main.Constants.*;

/**
 * Created by tomko on 15.4.2017.
 */
public class Manage_public {
    private Main_window mainWindow;

    public Manage_public(Main_window mainWindow) {
        this.mainWindow = mainWindow;

        Item_Listener itemlistener = new Item_Listener();
        Listener listener = new Listener();
        this.mainWindow.addTable_jcomboboxListener(itemlistener);
        this.mainWindow.addDelete_buttonListener(listener);
    }

    private class Item_Listener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getItem().equals("Arrangements")) {
                mainWindow.set_search_comboboxItems(ARRANGEMENT_ITEMS);
            } else if (itemEvent.getItem().equals("Locations")) {
                mainWindow.set_search_comboboxItems(LOCATION_ITEMS);
            } else if (itemEvent.getItem().equals("Estates")) {
                mainWindow.set_search_comboboxItems(ESTATE_ITEMS);
            } else if (itemEvent.getItem().equals("Open houses")) {
                mainWindow.set_search_comboboxItems(OPEN_HOUSE_ITEMS);
            } else if (itemEvent.getItem().equals("Clients")) {
                mainWindow.set_search_comboboxItems(CLIENT_ITEMS);
            } else if (itemEvent.getItem().equals("Agents")) {
                mainWindow.set_search_comboboxItems(AGENT_ITEMS);
            } else if (itemEvent.getItem().equals("Sold estates")) {
                mainWindow.set_search_comboboxItems(SOLD_ESTATE_ITEMS);
            }
        }
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    private String generateTextAreaString(String[] s) {
        return String.join(";\n", s);
    }

    private int extractId(String item) {
        return 0;
    }
}
