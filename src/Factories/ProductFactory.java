package Factories;

import Product.*;

public class ProductFactory {
    public static Product createProduct(String productType) {
        if (productType.equalsIgnoreCase("book")) {
            return new Book();
        } else if (productType.equalsIgnoreCase("bouquet")) {
            return new Bouquet();
        } else if (productType.equalsIgnoreCase("flower")) {
            return new Flower();
        } else if (productType.equalsIgnoreCase("drink")) {
            return new Drink();
        } else if (productType.equalsIgnoreCase("food")) {
            return new Food();
        } else if (productType.equalsIgnoreCase("medicine")) {
            return new Medicine();
        } else {
            throw new IllegalArgumentException("Invalid product type: " + productType);
        }
    }
}
