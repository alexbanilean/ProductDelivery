package shop;

import product.Drink;
import product.Product;
import user.DeliveryEmployee;
import user.Owner;

import java.util.ArrayList;
import java.util.Scanner;

public class Bar extends Shop{
    private ArrayList<Drink> drinksMenu;

    public Bar() {}

    public Bar(String name, Owner owner, String address, int rating, ArrayList<DeliveryEmployee> deliveryEmployees, ArrayList<Product> products, ArrayList<Drink> drinksMenu) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.rating = rating;
        this.deliveryEmployees = deliveryEmployees;
        this.products = products;
        this.drinksMenu = new ArrayList<Drink>();

        for (Product product : products) {
            if (product instanceof Drink) {
                drinksMenu.add((Drink) product);
            }
        }
    }

    public ArrayList<Drink> getDrinksMenu() {
        return drinksMenu;
    }

    public void setDrinksMenu(ArrayList<Drink> drinksMenu) {
        this.drinksMenu = drinksMenu;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating % 5 + 1; // from 1 to 5
    }

    public void addProduct(Product product) {
        if (product instanceof Drink) {
            drinksMenu.add((Drink) product);
        }

        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product instanceof Drink) {
            drinksMenu.remove((Drink) product);
        }

        products.remove(product);
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the bar: ");
        setName(scanner.nextLine());

        Owner owner = new Owner();
        owner.read(scanner);
        setOwner(owner);

        System.out.println("Enter the address of the bar: ");
        setAddress(scanner.nextLine());

        System.out.println("Enter the rating of the bar: ");
        setRating(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter the number of delivery employees: ");
        int numberOfDeliveryEmployees = scanner.nextInt();
        scanner.nextLine();

        deliveryEmployees = new ArrayList<DeliveryEmployee>();

        for (int i = 0; i < numberOfDeliveryEmployees; i++) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee();
            deliveryEmployee.read(scanner);
            deliveryEmployees.add(deliveryEmployee);
        }

        System.out.println("Enter the number of products: ");
        int numberOfProducts = scanner.nextInt();
        scanner.nextLine();

        products = new ArrayList<Product>();
        drinksMenu = new ArrayList<Drink>();

        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Drink();
            product.read(scanner);
            addProduct(product);
        }
    }

    @Override
    public String toString() {
        return "Bar[" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", deliveryEmployees=" + deliveryEmployees +
                ", products=" + products +
                ", drinksMenu=" + drinksMenu +
                ']';
    }
}
