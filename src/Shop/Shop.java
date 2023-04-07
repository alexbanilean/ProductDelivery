package Shop;

import java.util.ArrayList;
import java.util.Scanner;

import Product.Product;
import User.DeliveryEmployee;
import User.Owner;

public abstract class Shop {
    protected String name;
    protected Owner owner;
    protected String address;
    protected int rating;
    protected ArrayList<DeliveryEmployee> deliveryEmployees;
    protected ArrayList<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract int getRating();

    public abstract void setRating(int rating);

    public ArrayList<DeliveryEmployee> getDeliveryEmployees() {
        return deliveryEmployees;
    }

    public void setDeliveryEmployees(ArrayList<DeliveryEmployee> deliveryEmployees) {
        this.deliveryEmployees = deliveryEmployees;
    }

    public void addDeliveryEmployee(DeliveryEmployee deliveryEmployee) {
        deliveryEmployees.add(deliveryEmployee);
    }

    public void removeDeliveryEmployee(DeliveryEmployee deliveryEmployee) {
        deliveryEmployees.remove(deliveryEmployee);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public abstract void addProduct(Product product);

    public abstract void removeProduct(Product product);

    public abstract void read(Scanner scanner);

    @Override
    public abstract String toString();
}
