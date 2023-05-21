package registration;

import java.util.Scanner;

public interface IRegistration {
    int logIn(Scanner scanner);
    void logOut(int userId, String username);
    void registerOwner(Scanner scanner);
    void registerAdmin(Scanner scanner);
    void registerCustomer(Scanner scanner);
    void registerDeliveryEmployee(Scanner scanner);
}
