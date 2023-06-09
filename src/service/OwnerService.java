package service;

import factories.ProductFactory;
import product.Product;
import registration.Registration;
import shared.Data;
import shared.Order;
import user.Owner;
import shop.Shop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OwnerService implements IOwnerService {

    private static final OwnerService ownerService = new OwnerService();
    private final Data data = Data.getData();
    private final AuditService auditService = AuditService.getInstance();
    private final Registration registration = Registration.getRegistration();
    private final Owner owner = (Owner) Registration.getCurrentUser();
    private final ArrayList<Order> orders;
    private final ArrayList<Shop> shops;
    private final ArrayList<String> shopNames;

    private OwnerService() {

        if(owner.getShops() != null) {
            shopNames = (ArrayList<String>) owner.getShops().stream()
                    .map(Shop::getName)
                    .collect(Collectors.toList());

            orders = (ArrayList<Order>) data.getOrders().stream()
                    .filter(order -> shopNames.contains(order.getShop().getName()))
                    .collect(Collectors.toList());

            shops = (ArrayList<Shop>) data.getShops().stream()
                    .filter(shop -> shopNames.contains(shop.getName()))
                    .collect(Collectors.toList());
        }
        else {
            shopNames = new ArrayList<>();
            orders = new ArrayList<>();
            shops = new ArrayList<>();
        }
    }

    public static OwnerService getOwnerService() {
        return ownerService;
    }

    @Override
    public void showMenu() {
        System.out.println("\n----------Owner menu----------");
        System.out.println("1. List all products");
        System.out.println("2. List all orders");
        System.out.println("3. Add product");
        System.out.println("4. Remove product");
        System.out.println("5. Remove order");
        System.out.println("6. Log out");
    }

    @Override
    public void useMenu(Scanner scanner) {
        while (true) {
            showMenu();
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (!validChoice(choice)) {
                System.out.println("Invalid choice!");
                continue;
            }

            if (choice == 1) {
                listAllProducts();
            } else if (choice == 2) {
                listAllOrders();
            } else if (choice == 3) {
                System.out.println("The available product types are: ");
                System.out.println("1. Food");
                System.out.println("2. Drink");
                System.out.println("3. Book");
                System.out.println("4. Flower");
                System.out.println("5. Bouquet");
                System.out.println("6. Medicine");
                System.out.println("Enter product type: ");
                String productType = scanner.nextLine();
                Product product = ProductFactory.createProduct(productType);
                product.read(scanner);
                System.out.println("Enter shop name: ");
                String shopName = scanner.nextLine();
                addProduct(shopName, product);
            } else if (choice == 4) {
                System.out.println("Enter product name: ");
                String productName = scanner.nextLine();

                removeProduct(productName);
            } else if (choice == 5) {
                System.out.println("Enter order id: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                removeOrder(orderId);
            } else if (choice == 6) {
                logOut();
                break;
            }
        }
    }

    @Override
    public void listAllProducts() {
        auditService.logAction("List all products");

        System.out.println("\n----------Products----------");

        for(var shop : shops) {
            System.out.printf("\n----------%s----------", shop.getName());
            for(var product : shop.getProducts())
                System.out.println(product.getName());
        }
    }

    @Override
    public void listAllOrders() {
        auditService.logAction("List all orders");

        System.out.println("\n----------Orders----------");

        for(var order : orders)
            System.out.println(order.getOrderId());
    }

    @Override
    public void addProduct(String shopName, Product product) {
        auditService.logAction("Add product");

        try {
            data.getShops().stream()
                    .filter(shop -> shop.getName().equals(shopName))
                    .findFirst().ifPresent(shop -> shop.addProduct(product));
        }
        catch (Exception e) {
            System.out.println("Add product failed!");
        }
    }

    @Override
    public void removeProduct(String productName) {
        auditService.logAction("Remove product");

        for(var shop : shops) {
            shop.getProducts().stream()
                    .filter(prod -> prod.getName().equals(productName))
                    .findFirst().ifPresent(shop::removeProduct);
        }
    }

    @Override
    public void removeOrder(int orderId) {
        auditService.logAction("Remove order");
        orders.removeIf(order -> order.getOrderId() == orderId);
    }

    public void logOut() {
        auditService.logAction("Log out");
        registration.logOut(owner.getUserId(), owner.getName());
    }

    public boolean validChoice(int choice) {
        return 1 <= choice && choice <= 6;
    }
}
