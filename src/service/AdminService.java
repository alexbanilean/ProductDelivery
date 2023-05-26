package service;

import registration.Registration;
import product.Product;
import shared.Data;
import shop.Shop;
import user.Administrator;
import factories.*;

import java.util.Scanner;

public class AdminService implements IAdminService {

    private static AdminService adminService = new AdminService();
    private Data data = Data.getData();
    private AuditService auditService = AuditService.getInstance();
    private Registration registration = Registration.getRegistration();
    private Administrator admin = (Administrator) Registration.getCurrentUser();

    private AdminService() {}
    public static AdminService getAdminService() {
        return adminService;
    }

    @Override
    public void showMenu() {
        System.out.println("\n----------Administrator menu----------");
        System.out.println("1. List all shops");
        System.out.println("2. List all products");
        System.out.println("3. List all orders");
        System.out.println("4. Add shop");
        System.out.println("5. Add product");
        System.out.println("6. Remove shop");
        System.out.println("7. Remove product");
        System.out.println("8. Remove order");
        System.out.println("9. Log out");
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
                listAllShops();
            } else if (choice == 2) {
                listAllProducts();
            } else if (choice == 3) {
                listAllOrders();
            } else if (choice == 4) {
                System.out.println("The available shop types are: ");
                System.out.println("1. Restaurant");
                System.out.println("2. Bar");
                System.out.println("3. Library");
                System.out.println("4. Florist");
                System.out.println("5. Pharmacy");
                System.out.println("Enter shop type name: ");
                String shopType = scanner.nextLine();
                Shop shop = ShopFactory.createShop(shopType);
                shop.read(scanner);
                addShop(shop);
            } else if (choice == 5) {
                System.out.println("The available product types are: ");
                System.out.println("1. Food");
                System.out.println("2. Drink");
                System.out.println("3. Book");
                System.out.println("4. Flower");
                System.out.println("5. Bouquet");
                System.out.println("6. Medicine");
                System.out.println("Enter product type name: ");
                String productType = scanner.nextLine();
                Product product = ProductFactory.createProduct(productType);
                product.read(scanner);
                System.out.println("Enter shop name: ");
                String shopName = scanner.nextLine();
                addProduct(shopName, product);
            } else if (choice == 6) {
                System.out.println("Enter shop name: ");
                String shopName = scanner.nextLine();
                removeShop(shopName);
            } else if (choice == 7) {
                System.out.println("Enter shop name: ");
                String shopName = scanner.nextLine();
                System.out.println("Enter product name: ");
                String productName = scanner.nextLine();

                removeProduct(shopName, productName);
            } else if (choice == 8) {
                System.out.println("Enter order id: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                removeOrder(orderId);
            } else if (choice == 9) {
                logOut();
                break;
            }
        }
    }

    @Override
    public void listAllShops() {
        auditService.logAction("List all shops");

        System.out.println("\n----------Shops----------");

        for(var shop : data.getShops())
            System.out.println(shop.getName());
    }

    @Override
    public void listAllProducts() {
        auditService.logAction("List all products");

        System.out.println("\n----------Products----------");

        for(var shop : data.getShops()) {
            System.out.printf("\n----------%s----------%n", shop.getName());
            for(var product : shop.getProducts())
                System.out.println(product.getName());
        }
    }

    @Override
    public void listAllOrders() {
        auditService.logAction("List all orders");

        System.out.println("\n----------Orders----------");

        for(var order : data.getOrders())
            System.out.println(order.getOrderId());
    }

    @Override
    public void addShop(Shop shop) {
        auditService.logAction("Add shop");
        data.addShop(shop);
    }

    @Override
    public void addProduct(String shopName, Product product) {
        auditService.logAction("Add product");
        data.getShops().stream()
                .filter(shop -> shop.getName().equals(shopName))
                .findFirst()
                .ifPresent(shop -> shop.addProduct(product));
    }

    @Override
    public void removeShop(String shopName) {
        auditService.logAction("Remove shop");
        data.getShops().stream()
                .filter(shop -> shop.getName().equals(shopName))
                .findFirst().ifPresent(shopToRemove -> data.removeShop(shopToRemove));
    }

    @Override
    public void removeProduct(String shopName, String productName) {
        auditService.logAction("Remove product");
        data.getShops().stream()
                .filter(shop -> shop.getName().equals(shopName))
                .findFirst().ifPresent(shopFound -> shopFound.getProducts().stream()
                                                             .filter(prod -> prod.getName().equals(productName))
                                                             .findFirst().ifPresent(shopFound::removeProduct));
    }

    @Override
    public void removeOrder(int orderId) {
        auditService.logAction("Remove order");
        data.getOrders().stream()
                .filter(ord -> ord.getOrderId() == orderId)
                .findFirst().ifPresent(order -> data.removeOrder(order));
    }

    public void logOut() {
        auditService.logAction("Log out");
        registration.logOut(admin.getUserId(), admin.getName());
    }

    public boolean validChoice(int choice) {
        return 1 <= choice && choice <= 9;
    }
}
