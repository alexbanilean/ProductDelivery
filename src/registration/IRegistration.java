package registration;

import java.util.Scanner;

public interface IRegistration {
    int logIn(Scanner scanner);
    void logOut(int userId, String username);
    int registerOwner(Scanner scanner);
    int registerAdmin(Scanner scanner);
    int registerCustomer(Scanner scanner);
    int registerDeliveryEmployee(Scanner scanner);
}
