package User;

import java.time.Duration;
import java.util.Scanner;

public class DeliveryEmployee extends User{

    private String carNumber;
    private Duration averageDeliveryTime;
    private int averageOrdersPerDay;

    public DeliveryEmployee() {}

    public DeliveryEmployee(String name, String email, String password, String phoneNumber,
                            String carNumber, Duration averageDeliveryTime, int averageOrdersPerDay) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.carNumber = carNumber;
        this.averageDeliveryTime = averageDeliveryTime;
        this.averageOrdersPerDay = averageOrdersPerDay;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Duration getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(Duration averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public int getAverageOrdersPerDay() {
        return averageOrdersPerDay;
    }

    public void setAverageOrdersPerDay(int averageOrdersPerDay) {
        this.averageOrdersPerDay = averageOrdersPerDay;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the delivery employee: ");
        setName(scanner.nextLine());

        System.out.println("Enter the email of the delivery employee: ");
        setEmail(scanner.nextLine());

        System.out.println("Enter the password of the delivery employee: ");
        setPassword(scanner.nextLine());

        System.out.println("Enter the phone number of the delivery employee: ");
        setPhoneNumber(scanner.nextLine());

        System.out.println("Enter the car number of the delivery employee: ");
        setCarNumber(scanner.nextLine());

        System.out.println("Enter the average delivery time of the delivery employee: ");
        setAverageDeliveryTime(Duration.ofMinutes(scanner.nextInt()));
        scanner.nextLine();

        System.out.println("Enter the average orders per day of the delivery employee: ");
        setAverageOrdersPerDay(scanner.nextInt());
        scanner.nextLine();
    }

    @Override
    public String toString() {
        return "DeliveryEmployee[" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "carNumber='" + carNumber + '\'' +
                ", averageDeliveryTime=" + averageDeliveryTime +
                ", averageOrdersPerDay=" + averageOrdersPerDay +
                ']';
    }
}
