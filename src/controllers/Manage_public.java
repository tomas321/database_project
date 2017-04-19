package controllers;

import views.Detail_window;
import views.Main_window;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.html.HTMLDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

import static main.Constants.*;

/**
 * Created by tomko on 15.4.2017.
 */
public class Manage_public {
    private Main_window mainWindow;

    public Manage_public(Main_window mainWindow) {
        this.mainWindow = mainWindow;

        Item_Listener itemlistener = new Item_Listener();
        ListSelection_Listener selectionListener = new ListSelection_Listener();
        this.mainWindow.addTable_jcomboboxListener(itemlistener);
        this.mainWindow.addTable_contentListener(selectionListener);
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

    private class ListSelection_Listener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            Detail_window detailWindow = new Detail_window();
            detailWindow.setVisible(true);

            detailWindow.setTable_labelText(mainWindow.getTable_jcombobox().getSelectedItem().toString());
            detailWindow.getDetail_textArea().setText(generateTextAreaString(new String[]{"detail", "should be", "here"}));
        }
    }

    private String generateTextAreaString(String[] s) {
        return String.join(";\n", s);
    }
}
