package user;

import java.util.Scanner;

public class Customer extends User{

    private String preferredAddress;
    private String preferredPaymentMethod;

    public Customer() {}

    public Customer(String name, String email, String password, String phoneNumber,
                    String preferredAddress, String preferredPaymentMethod,
                    DeliveryEmployee preferredDeliveryEmployee) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.preferredAddress = preferredAddress;
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    public String getPreferredAddress() {
        return preferredAddress;
    }

    public void setPreferredAddress(String preferredAddress) {
        this.preferredAddress = preferredAddress;
    }

    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter your name: ");
        setName(scanner.nextLine());

        System.out.println("Enter your email: ");
        setEmail(scanner.nextLine());

        System.out.println("Enter your password: ");
        setPassword(scanner.nextLine());

        System.out.println("Enter your phone number: ");
        setPhoneNumber(scanner.nextLine());

        System.out.println("Enter your preferred address: ");
        setPreferredAddress(scanner.nextLine());

        System.out.println("Enter your preferred payment method: ");
        setPreferredPaymentMethod(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Customer [" +
                "name=" + name +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", address=" + preferredAddress +
                ", preferredPaymentMethod=" + preferredPaymentMethod +
                "]";
    }
}
