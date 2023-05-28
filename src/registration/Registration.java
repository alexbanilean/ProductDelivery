package registration;

import service.AuditService;
import user.*;

import java.util.HashSet;
import java.util.Scanner;

public class Registration implements IRegistration {

    private static final Registration registration = new Registration();
    private static final HashSet<User> users = new HashSet<>();
    private static User currentUser = null;
    private final AuditService auditService = AuditService.getInstance();

    private Registration() {}

    public static Registration getRegistration() {
        return registration;
    }

    public static HashSet<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    @Override
    public int logIn(Scanner scanner) {
        auditService.logAction("Login");

        // return 1 if admin
        // return 2 if owner
        // return 3 if customer
        // return 4 if delivery employee
        // return 0 if not found

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        int userId = users.stream()
                .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                .mapToInt(User::getUserId)
                .findFirst()
                .orElse(-1);

        if (userId != -1) {
            currentUser = users.stream()
                    .filter(user -> user.getUserId() == userId)
                    .findFirst()
                    .orElse(null);

            if (currentUser instanceof Administrator) {
                return 1;
            } else if (currentUser instanceof Owner) {
                return 2;
            } else if (currentUser instanceof Customer) {
                return 3;
            } else if (currentUser instanceof DeliveryEmployee) {
                return 4;
            }
        }

        System.out.println("User not found. Login failed.");

        return 0;
    }

    @Override
    public void logOut(int userId, String username) {
        auditService.logAction("Logout");
        currentUser = null;
        System.out.println("user " + username + " logged out.");
    }

    @Override
    public int registerAdmin(Scanner scanner) {
        auditService.logAction("Register admin");

        Administrator administrator = new Administrator();
        administrator.read(scanner);

        for (User user : users) {
            if (user.getName().equals(administrator.getName())) {
                System.out.println("Username already used. Registration failed.");
                return -1;
            }
        }

        administrator.setUserId(users.size() + 1);

        users.add(administrator);

        System.out.println("Admin " + administrator.getName() + " registered.");

        return 0;
    }

    @Override
    public int registerOwner(Scanner scanner) {
        auditService.logAction("Register owner");

        Owner owner = new Owner();
        owner.read(scanner);

        for (User user : users) {
            if (user.getName().equals(owner.getName())) {
                System.out.println("Username already used. Registration failed.");
                return -1;
            }
        }

        owner.setUserId(users.size() + 1);

        users.add(owner);

        System.out.println("Owner " + owner.getName() + " registered.");

        return 0;
    }

    @Override
    public int registerCustomer(Scanner scanner) {
        auditService.logAction("Register customer");

        Customer customer = new Customer();
        customer.read(scanner);

        for (User user : users) {
            if (user.getName().equals(customer.getName())) {
                System.out.println("Username already used. Registration failed.");
                return -1;
            }
        }

        customer.setUserId(users.size() + 1);

        users.add(customer);

        System.out.println("Customer " + customer.getName() + " registered.");

        return 0;
    }

    @Override
    public int registerDeliveryEmployee(Scanner scanner) {
        auditService.logAction("Register delivery employee");

        DeliveryEmployee deliveryEmployee = new DeliveryEmployee();
        deliveryEmployee.read(scanner);

        for (User user : users) {
            if (user.getName().equals(deliveryEmployee.getName())) {
                System.out.println("Username already used. Registration failed.");
                return -1;
            }
        }

        deliveryEmployee.setUserId(users.size() + 1);

        users.add(deliveryEmployee);

        System.out.println("Delivery employee " + deliveryEmployee.getName() + " registered.");

        return 0;
    }
}
