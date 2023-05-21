package shop;

import product.Product;
import product.Drink;
import product.Food;
import user.DeliveryEmployee;
import user.Owner;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant extends Shop{
    private ArrayList<Food> foodsMenu;
    private ArrayList<Drink> drinksMenu;

    public Restaurant() {}

    public Restaurant(String name, Owner owner, String address, int rating,
                      ArrayList<DeliveryEmployee> deliveryEmployees, ArrayList<Product> products) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.rating = rating;
        this.deliveryEmployees = deliveryEmployees;
        this.products = products;
        this.foodsMenu = new ArrayList<Food>();
        this.drinksMenu = new ArrayList<Drink>();

        for (Product product : products) {
            if (product instanceof Food) {
                foodsMenu.add((Food) product);
            } else if (product instanceof Drink) {
                drinksMenu.add((Drink) product);
            }
        }
    }

    public ArrayList<Food> getFoodsMenu() {
        return foodsMenu;
    }

    public void setFoodsMenu(ArrayList<Food> foodsMenu) {
        this.foodsMenu = foodsMenu;
    }

    public ArrayList<Drink> getDrinksMenu() {
        return drinksMenu;
    }

    public void setDrinksMenu(ArrayList<Drink> drinksMenu) {
        this.drinksMenu = drinksMenu;
    }

    public void addProduct(Product product) {
        if (product instanceof Food) {
            foodsMenu.add((Food) product);
        } else if (product instanceof Drink) {
            drinksMenu.add((Drink) product);
        }

        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product instanceof Food) {
            foodsMenu.remove((Food) product);
        } else if (product instanceof Drink) {
            drinksMenu.remove((Drink) product);
        }

        products.remove(product);
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating % 10 + 1; // from 1 to 10
    }

    @Override
    public void read(Scanner scanner) {

        System.out.print("Enter the name of the restaurant: ");
        setName(scanner.nextLine());

        Owner owner = new Owner();
        owner.read(scanner);
        setOwner(owner);

        System.out.print("Enter the address of the restaurant: ");
        setAddress(scanner.nextLine());

        System.out.print("Enter the rating of the restaurant: ");
        setRating(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter the number of delivery employees: ");
        int numberOfDeliveryEmployees = scanner.nextInt();
        scanner.nextLine();

        deliveryEmployees = new ArrayList<DeliveryEmployee>();
        for (int i = 0; i < numberOfDeliveryEmployees; i++) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee();
            deliveryEmployee.read(scanner);
            deliveryEmployees.add(deliveryEmployee);
        }

        System.out.print("Enter the number of products: ");
        int numberOfProducts = scanner.nextInt();
        scanner.nextLine();

        products = new ArrayList<Product>();
        foodsMenu = new ArrayList<Food>();
        drinksMenu = new ArrayList<Drink>();

        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println("Enter the number of type of the product: ");
            System.out.println("1. Food");
            System.out.println("2. Drink");
            int type = scanner.nextInt();
            scanner.nextLine();

            while (type != 1 && type != 2) {
                System.out.println("Invalid type!");
                System.out.println("Enter the number of type of the product: ");
                System.out.println("1. Food");
                System.out.println("2. Drink");
                type = scanner.nextInt();
                scanner.nextLine();
            }

            Product product;
            if (type == 1) {
                product = new Food();
            } else {
                product = new Drink();
            }

            product.read(scanner);
            addProduct(product);
        }
    }

    @Override
    public String toString() {
        return "Restaurant [" +
                "name=" + name +
                ", owner=" + owner +
                ", address=" + address +
                ", rating=" + rating +
                ", deliveryEmployees=" + deliveryEmployees +
                ", products=" + products +
                ", foodsMenu=" + foodsMenu +
                ", drinksMenu=" + drinksMenu +
                "]";
    }
}
