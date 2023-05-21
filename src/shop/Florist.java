package shop;

import product.Product;
import product.Flower;
import product.Bouquet;
import user.DeliveryEmployee;
import user.Owner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Florist extends Shop{
    private HashSet<Flower> flowers;
    private HashSet<Bouquet> bouquets;

    public Florist() {}

    public Florist(String name, Owner owner, String address, int rating,
                   ArrayList<DeliveryEmployee> deliveryEmployees, ArrayList<Product> products) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.rating = rating;
        this.deliveryEmployees = deliveryEmployees;
        this.products = products;
        this.flowers = new HashSet<Flower>();
        this.bouquets = new HashSet<Bouquet>();

        for (Product product : products) {
            if (product instanceof Flower) {
                flowers.add((Flower) product);
            }
            if (product instanceof Bouquet) {
                bouquets.add((Bouquet) product);
            }
        }
    }

    public HashSet<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(HashSet<Flower> flowers) {
        this.flowers = flowers;
    }

    public HashSet<Bouquet> getBouquets() {
        return bouquets;
    }

    public void setBouquets(HashSet<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating % 5 + 1; // from 1 to 5
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof Flower) {
            flowers.add((Flower) product);
        }
        if (product instanceof Bouquet) {
            bouquets.add((Bouquet) product);
        }

        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        if (product instanceof Flower) {
            flowers.remove((Flower) product);
        }
        if (product instanceof Bouquet) {
            bouquets.remove((Bouquet) product);
        }

        products.remove(product);
    }

    @Override
    public void read(Scanner scanner) {

        System.out.print("Enter florist name: ");
        setName(scanner.nextLine());

        Owner owner = new Owner();
        owner.read(scanner);
        setOwner(owner);

        System.out.print("Enter florist address: ");
        setAddress(scanner.nextLine());

        System.out.print("Enter florist rating: ");
        setRating(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter number of delivery employees: ");
        int numberOfDeliveryEmployees = scanner.nextInt();
        scanner.nextLine();

        deliveryEmployees = new ArrayList<DeliveryEmployee>();

        for (int i = 0; i < numberOfDeliveryEmployees; i++) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee();
            deliveryEmployee.read(scanner);
            deliveryEmployees.add(deliveryEmployee);
        }

        System.out.print("Enter number of products: ");
        int numberOfProducts = scanner.nextInt();
        scanner.nextLine();

        products = new ArrayList<Product>();
        flowers = new HashSet<Flower>();
        bouquets = new HashSet<Bouquet>();

        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println("Enter the number of type of the product: ");
            System.out.println("1. Flower");
            System.out.println("2. Bouquet");
            int type = scanner.nextInt();
            scanner.nextLine();

            while (type != 1 && type != 2) {
                System.out.println("Invalid type!");
                System.out.println("Enter the number of type of the product: ");
                System.out.println("1. Flower");
                System.out.println("2. Bouquet");
                type = scanner.nextInt();
                scanner.nextLine();
            }

            Product product;
            if (type == 1) {
                product = new Flower();
            } else {
                product = new Bouquet();
            }

            product.read(scanner);
            addProduct(product);
        }
    }

    @Override
    public String toString() {
        return "Florist[" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", deliveryEmployees=" + deliveryEmployees +
                ", products=" + products +
                ", flowers=" + flowers +
                ", bouquets=" + bouquets +
                ']';
    }
}
