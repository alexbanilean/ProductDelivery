package user;

import java.util.Scanner;

public class Administrator extends User{

    private int licenseNumber;

    public Administrator() {}

    public Administrator(String name, String email, String password, String phoneNumber, int licenseNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the administrator: ");
        setName(scanner.nextLine());

        System.out.println("Enter the email of the administrator: ");
        setEmail(scanner.nextLine());

        System.out.println("Enter the password of the administrator: ");
        setPassword(scanner.nextLine());

        System.out.println("Enter the phone number of the administrator: ");
        setPhoneNumber(scanner.nextLine());

        System.out.println("Enter the license number of the administrator: ");
        setLicenseNumber(scanner.nextInt());
        scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Administrator[" +
                "name=" + name +
                ", email=" + email +
                ", password=" + password +
                ", phoneNumber=" + phoneNumber +
                ", licenseNumber=" + licenseNumber +
                "]";
    }
}
