package shared;

import java.util.ArrayList;
import shop.Shop;

public class Data {
    private static Data data = new Data();
    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private ArrayList<Order> orders = new ArrayList<Order>();

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

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeShop(Shop shop) {
        shops.remove(shop);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
