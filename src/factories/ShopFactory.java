package factories;

import shop.Shop;
import shop.Bar;
import shop.Florist;
import shop.Library;
import shop.Pharmacy;
import shop.Restaurant;

public class ShopFactory {

    private ShopFactory() {}

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