package user;

import exceptions.InvalidShopTypeException;
import factories.ShopFactory;
import shop.Shop;
import shared.Data;

import java.util.ArrayList;
import java.util.Scanner;

import static shared.Data.getData;

public class Owner extends User{
    private ArrayList<Shop> shops = new ArrayList<>();

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

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter owner name: ");
        setName(scanner.nextLine());

        System.out.println("Enter owner email: ");
        setEmail(scanner.nextLine());

        System.out.println("Enter owner password: ");
        setPassword(scanner.nextLine());

        System.out.println("Enter owner phone number: ");
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

                    getData().getShops().stream()
                             .filter(shop -> shop.getName().equals(shopName))
                             .findFirst().ifPresent(_shop -> shops.add(_shop));

                } else {
                    System.out.println("The available shop types are: ");
                    System.out.println("1. Restaurant");
                    System.out.println("2. Bar");
                    System.out.println("3. Library");
                    System.out.println("4. Florist");
                    System.out.println("5. Pharmacy");
                    System.out.println("Enter shop type name: ");

                    try {
                        String type = scanner.nextLine();

                        if(!type.equalsIgnoreCase("Restaurant") && !type.equalsIgnoreCase("Bar") && !type.equalsIgnoreCase("Library") && !type.equalsIgnoreCase("Florist") && !type.equalsIgnoreCase("Pharmacy"))
                            throw new InvalidShopTypeException("Invalid shop type: " + type);

                        Shop shop = ShopFactory.createShop(type);

                        shop.read(scanner, false);
                        shops.add(shop);
                        Data.addShop(shop);
                    }
                    catch (InvalidShopTypeException e) {
                        System.out.println(e.getMessage());
                    }
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
