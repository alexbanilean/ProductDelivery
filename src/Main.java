import java.util.Scanner;
import registration.Registration;
import service.AdminService;
import service.CustomerService;
import service.DeliveryEmployeeService;
import service.OwnerService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = Registration.getRegistration();

        while (true) {
            System.out.println("----------Welcome to the Product Delivery App!----------");

            System.out.println("Do you have an account? (y/n)");
            String answer = scanner.nextLine();

            if (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("Please enter a valid answer!");
                continue;
            }

            if (answer.equals("n")) {
                System.out.println("-----Registration-----");
                System.out.println("Are you a administrator, owner, customer or delivery employee? (a/o/c/e)");
                while (true) {
                    String type = scanner.nextLine();
                    if (type.equals("a")) {
                        registration.registerAdmin(scanner);
                        break;
                    } else if (type.equals("o")) {
                        registration.registerOwner(scanner);
                        break;
                    } else if (type.equals("c")) {
                        registration.registerCustomer(scanner);
                        break;
                    } else if (type.equals("e")) {
                        registration.registerDeliveryEmployee(scanner);
                        break;
                    } else {
                        System.out.println("Please enter a valid answer!");
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
    }
}