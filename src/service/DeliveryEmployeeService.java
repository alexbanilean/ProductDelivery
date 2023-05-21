package service;

import registration.Registration;
import shared.Data;
import shared.Order;
import shared.OrderStatus;
import user.DeliveryEmployee;

import java.util.Scanner;

public class DeliveryEmployeeService implements IDeliveryEmployeeService {

    private static DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();
    private Data data = Data.getData();
    private Registration registration = Registration.getRegistration();
    private DeliveryEmployee deliveryEmployee = (DeliveryEmployee) registration.getCurrentUser();

    private DeliveryEmployeeService() {}

    public static DeliveryEmployeeService getDeliveryEmployeeService() {
        return deliveryEmployeeService;
    }

    @Override
    public void showMenu() {
        System.out.println("\n----------Delivery employee menu----------");
        System.out.println("1. List all orders");
        System.out.println("2. Take order");
        System.out.println("3. Deliver order");
        System.out.println("4. Log out");
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
                listAllOrders();
            } else if (choice == 2) {
                System.out.println("Enter order id: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                takeOrder(orderId);
            } else if (choice == 3) {
                System.out.println("Enter order id: ");
                int orderId = scanner.nextInt();
                scanner.nextLine();
                deliverOrder(orderId);
            } else if (choice == 4) {
                logOut();
                break;
            }
        }
    }

    @Override
    public void listAllOrders() {

        System.out.println("\n----------Orders----------");

        for(var order : data.getOrders())
            System.out.println(order.getOrderId());
    }

    @Override
    public void takeOrder(int orderId) {
        try {
            Order order = data.getOrders().stream().filter(o -> o.getOrderId() == orderId)
                    .findFirst().orElse(null);

            if(order != null) {
                order.setDeliveryEmployee(deliveryEmployee);
                order.setOrderStatus(OrderStatus.TAKEN);
            }
        } catch (Exception e) {
            System.out.println("Order not found!");
        }
    }

    @Override
    public void deliverOrder(int orderId) {
        try {
            Order order = data.getOrders().stream().filter(o -> o.getOrderId() == orderId)
                    .findFirst().orElse(null);

            if(order != null) {
                order.setOrderStatus(OrderStatus.DELIVERED);
            }
        } catch (Exception e) {
            System.out.println("Order not found!");
        }
    }

    public void logOut() {
        registration.logOut(deliveryEmployee.getUserId(), deliveryEmployee.getName());
    }

    public boolean validChoice(int choice) {
        return 1 <= choice && choice <= 4;
    }
}
