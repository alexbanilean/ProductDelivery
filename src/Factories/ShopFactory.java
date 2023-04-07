package Factories;

import Shop.Shop;
import Shop.Bar;
import Shop.Florist;
import Shop.Library;
import Shop.Pharmacy;
import Shop.Restaurant;

public class ShopFactory {
    public static Shop createShop(String shopType) {
        if (shopType.equalsIgnoreCase("restaurant")) {
            return new Restaurant();
        } else if (shopType.equalsIgnoreCase("bar")) {
            return new Bar();
        } else if (shopType.equalsIgnoreCase("library")) {
            return new Library();
        } else if (shopType.equalsIgnoreCase("florist")) {
            return new Florist();
        } else if (shopType.equalsIgnoreCase("pharmacy")) {
            return new Pharmacy();
        } else {
            throw new IllegalArgumentException("Invalid shop type: " + shopType);
        }
    }
}