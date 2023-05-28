package shared;

import java.util.ArrayList;
import shop.Shop;

public class Data {
    private static final Data data = new Data();
    private static final ArrayList<Shop> shops = new ArrayList<>();
    private static final ArrayList<Order> orders = new ArrayList<>();

    private Data() {}

    public static Data getData() {
        return data;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public static void addShop(Shop shop) {
        shops.add(shop);
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static void removeShop(Shop shop) {
        shops.remove(shop);
    }

    public static void removeOrder(Order order) {
        orders.remove(order);
    }
}
