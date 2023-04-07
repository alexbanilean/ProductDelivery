package User;

import Factories.ShopFactory;
import Shop.Shop;

import java.util.ArrayList;
import java.util.Scanner;

import static Service.Data.getData;

public class Owner extends User{
    private ArrayList<Shop> shops;

    public Owner() {}

    public Owner(String name, String email, String password, String phoneNumber, ArrayList<Shop> shops) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.shops = shops;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(Shop shop) {
        this.shops = shops;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.print("Enter owner name: ");
        setName(scanner.nextLine());

        System.out.print("Enter owner email: ");
        setEmail(scanner.nextLine());

        System.out.print("Enter owner password: ");
        setPassword(scanner.nextLine());

        System.out.print("Enter owner phone number: ");
        setPhoneNumber(scanner.nextLine());

        while(true) {
            System.out.println("Do you want to add a shop for this owner? (y/n)");
            String ans = scanner.nextLine();

            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Is the shop already existent: ");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    System.out.println("Enter shop name: ");
                    String shopName = scanner.nextLine();

                    Shop _shop = getData().getShops().stream()
                            .filter(shop -> shop.getName().equals(shopName))
                            .findFirst()
                            .orElse(null);

                    shops.add(_shop);
                } else {
                    System.out.println("The available shop types are: ");
                    System.out.println("1. Restaurant");
                    System.out.println("2. Bar");
                    System.out.println("3. Library");
                    System.out.println("4. Florist");
                    System.out.println("5. Pharmacy");
                    System.out.println("Enter shop type: ");
                    String type = scanner.nextLine();
                    Shop shop = ShopFactory.createShop(type);
                    shop.read(scanner);
                    shops.add(shop);
                }
            } else if (ans.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    @Override
    public String toString() {
        return "Owner [" +
                ", name=" + name +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", shops=" + shops +
                "]";
    }
}
