package main;

import java.util.ArrayList;

/**
 * Created by tomko on 12.4.2017.
 */
public class Constants {
    public static final int INTERVAL = 100;
    public static final int MAX_VIEW = 1000;
    public static final String[] LOCATION_ITEMS = {"id", "street", "city"};
    public static final String[] ARRANGEMENT_ITEMS = {"id", "rooms", "toilets", "floors", "garden", "pool", "balcony", "furniture"};
    public static final String[] ESTATE_ITEMS = {"id", "name", "price", "status", "category", "land"};
    public static final String[] OPEN_HOUSE_ITEMS = {"id", "time_at"};
    public static final String[] CLIENT_ITEMS = {"id", "name", "phone", "email"};
    public static final String[] AGENT_ITEMS = {"id", "name", "phone", "started_at", "rating"};
    public static final String[] SOLD_ESTATE_ITEMS = {"id", "sold_to", "price", "sold_date"};
}
