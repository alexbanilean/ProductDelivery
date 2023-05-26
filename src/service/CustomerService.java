package service;

import registration.Registration;
import shared.Data;
import shared.Order;
import user.Customer;

import java.util.Scanner;

public class CustomerService implements ICustomerService{

    private static CustomerService customerService = new CustomerService();
    private Data data = Data.getData();
    private AuditService auditService = AuditService.getInstance();
    private Registration registration = Registration.getRegistration();
    private Customer customer = (Customer) Registration.getCurrentUser();

    private CustomerService() {}
    public static CustomerService getCustomerService() {
        return customerService;
    }

    @Override
    public void showMenu() {
        System.out.println("\n----------Customer menu----------");
        System.out.println("1. List all shops");
        System.out.println("2. List all products");
        System.out.println("3. Add order");
        System.out.println("4. Remove order");
        System.out.println("5. Log out");
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
                Order order = new Order();
                order.read(scanner);
                addOrder(order);
            } else if (choice == 4) {
                System.out.println("Enter order id: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                removeOrder(orderId);
            } else if (choice == 5) {
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
    public void addOrder(Order order) {
        auditService.logAction("Add order");
        data.addOrder(order);
    }

    @Override
    public void removeOrder(int orderId) {
        auditService.logAction("Remove order");
        Order order = data.getOrders().stream()
                .filter(ord -> ord.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if(order != null && order.getCustomer().getUserId() == customer.getUserId())
            data.removeOrder(order);
    }

    public void logOut() {
        auditService.logAction("Log out");
        registration.logOut(customer.getUserId(), customer.getName());
    }

    public boolean validChoice(int choice) {
        return 1 <= choice && choice <= 5;
    }
}
