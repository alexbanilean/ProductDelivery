package shop;

import product.Product;
import product.Medicine;
import user.DeliveryEmployee;
import user.Owner;

import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacy extends Shop{
    private ArrayList<Medicine> medicines;

    public Pharmacy() {}

    public Pharmacy(String name, Owner owner, String address, int rating,
                    ArrayList<DeliveryEmployee> deliveryEmployees, ArrayList<Product> products) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.rating = rating;
        this.deliveryEmployees = deliveryEmployees;
        this.products = products;
        this.medicines = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Medicine) {
                medicines.add((Medicine) product);
            }
        }
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating % 10 + 1; // from 1 to 10
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof Medicine) {
            medicines.add((Medicine) product);
        }

        products.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        if (product instanceof Medicine) {
            medicines.remove((Medicine) product);
        }

        products.remove(product);
    }

    @Override
    public void read(Scanner scanner, boolean optionOwner) {

        System.out.println("Enter pharmacy name: ");
        setName(scanner.nextLine());

        System.out.println("Enter pharmacy address: ");
        setAddress(scanner.nextLine());

        System.out.println("Enter pharmacy rating: ");
        setRating(scanner.nextInt());
        scanner.nextLine();

        if(optionOwner) {
            Owner owner = new Owner();
            owner.read(scanner);
            setOwner(owner);
        }

        System.out.println("Enter number of delivery employees: ");
        int numberOfDeliveryEmployees = scanner.nextInt();
        scanner.nextLine();

        deliveryEmployees = new ArrayList<>();

        for (int i = 0; i < numberOfDeliveryEmployees; i++) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee();
            deliveryEmployee.read(scanner);
            deliveryEmployees.add(deliveryEmployee);
        }

        System.out.println("Enter number of products: ");
        int numberOfProducts = scanner.nextInt();
        scanner.nextLine();

        products = new ArrayList<>();
        medicines = new ArrayList<>();

        for (int i = 0; i < numberOfProducts; i++) {
            Product product = new Medicine();
            product.read(scanner);
            addProduct(product);
        }
    }

    @Override
    public String toString() {
        return "Pharmacy[" +
                "medicines=" + medicines +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", deliveryEmployees=" + deliveryEmployees +
                ", products=" + products +
                ']';
    }
}
