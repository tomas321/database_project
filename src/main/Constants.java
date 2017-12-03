package main;

/**
 * Created by tomko on 12.4.2017.
 */
public class Constants {
    public static final int VIEW_LIMIT = 10000;
    public static final int MAX_VIEW = 500;
    public static final String[] LOCATION_ITEMS = {"id", "street", "city"};
    public static final String[] ARRANGEMENT_ITEMS = {"id", "rooms", "toilets", "floors", "garden", "pool", "balcony", "furniture"};
    public static final String[] ESTATE_ITEMS = {"id", "name", "price", "build_at", "status", "category", "land", "rooms", "city"};
    public static final String[] OPEN_HOUSE_ITEMS = {"id", "time_at", "estate", "client", "agent"};
    public static final String[] CLIENT_ITEMS = {"id", "name", "phone", "email"};
    public static final String[] AGENT_ITEMS = {"id", "name", "phone", "started_at", "rating"};
    public static final String[] SOLD_ESTATE_ITEMS = {"id", "sold_to", "price", "sold_date", "agent"};

    public static final String[] LOCATION_ITEMS_EDITABLE = {"street", "city"};
    public static final String[] ARRANGEMENT_ITEMS_EDITABLE = {"rooms", "toilets", "floors", "garden", "pool", "balcony", "furniture"};
    public static final String[] ESTATE_ITEMS_EDITABLE = {"arrangement_id", "location_id", "name", "price", "build_at", "status", "category", "land"};
    public static final String[] OPEN_HOUSE_ITEMS_EDITABLE = {"client_id", "estate_id", "agent_id", "time_at"};
    public static final String[] CLIENT_ITEMS_EDITABLE = {"name", "phone", "email"};
    public static final String[] AGENT_ITEMS_EDITABLE = {"name", "phone", "started_at", "rating"};
    public static final String[] SOLD_ESTATE_ITEMS_EDITABLE = {"agent_id", "sold_to", "price", "sold_date"};
}
