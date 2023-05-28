import java.util.Scanner;

import database.DatabaseConfiguration;
import registration.Registration;
import service.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = Registration.getRegistration();
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        while (true) {
            System.out.println("----------Welcome to the Product Delivery App!----------");

            System.out.println("Do you want to use the database? (y/n)");
            String option = scanner.nextLine();

            if (!option.equals("y") && !option.equals("n")) {
                System.out.println("Please enter a valid answer!");
                continue;
            }

            if(option.equals("y")) {
                databaseManager.DatabaseManagerMenu();

                System.out.println("Do you want to exit the app? (y/n)");
                String exit = scanner.nextLine();
                if (exit.equals("y")) {
                    break;
                }
            }

            System.out.println("Do you have an account? (y/n)");
            String answer = scanner.nextLine();

            if (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("Please enter a valid answer!");
                continue;
            }

            if (answer.equals("n")) {

                while (true) {
                    System.out.println("-----Registration-----");
                    System.out.println("Are you a administrator, owner, customer or delivery employee? (a/o/c/e)");

                    String type = scanner.nextLine();
                    int result = -1;

                    switch (type) {
                        case "a" -> result = registration.registerAdmin(scanner);
                        case "o" -> result = registration.registerOwner(scanner);
                        case "c" -> result = registration.registerCustomer(scanner);
                        case "e" -> result = registration.registerDeliveryEmployee(scanner);
                        default -> System.out.println("Please enter a valid answer!");
                    }

                    if(result == 0) {
                        break;
                    }
                }
            }

            System.out.println("----------Login----------");
            int type = registration.logIn(scanner);
            if (type == 1) {
                System.out.println("Logged in as administrator.");
                AdminService adminService = AdminService.getAdminService();
                adminService.useMenu(scanner);
            } else if (type == 2) {
                System.out.println("Logged in as owner.");
                OwnerService ownerService = OwnerService.getOwnerService();
                ownerService.useMenu(scanner);
            } else if (type == 3) {
                System.out.println("Logged in as customer.");
                CustomerService customerService = CustomerService.getCustomerService();
                customerService.useMenu(scanner);
            } else if (type == 4) {
                System.out.println("Logged in as delivery employee.");
                DeliveryEmployeeService deliveryEmployeeService = DeliveryEmployeeService.getDeliveryEmployeeService();
                deliveryEmployeeService.useMenu(scanner);
            } else {
                System.out.println("Login failed.");
            }

            System.out.println("Do you want to exit the app? (y/n)");
            String exit = scanner.nextLine();
            if (exit.equals("y")) {
                break;
            }
        }

        scanner.close();
        DatabaseConfiguration.closeDatabaseConnection();
    }
}