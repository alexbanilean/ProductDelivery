package shop;

import product.Product;
import product.Book;
import user.DeliveryEmployee;
import user.Owner;

import java.util.ArrayList;
import java.util.Scanner;

public class Library extends Shop{
    private ArrayList<Book> books;

    public Library() {}

    public Library(String name, Owner owner, String address, int rating,
                   ArrayList<DeliveryEmployee> deliveryEmployees, ArrayList<Product> products) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.rating = rating;
        this.deliveryEmployees = deliveryEmployees;
        this.products = products;
        this.books = new ArrayList<Book>();

        for (Product product : products) {
            if (product instanceof Book) {
                books.add((Book) product);
            }
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating % 100 + 1; // from 1 to 100
    }

    public void addProduct(Product product) {
        if (product instanceof Book) {
            books.add((Book) product);
        }

        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product instanceof Book) {
            books.remove((Book) product);
        }

        products.remove(product);
    }

    @Override
    public void read(Scanner scanner) {

        System.out.print("Enter library name: ");
        setName(scanner.nextLine());

        Owner owner = new Owner();
        owner.read(scanner);
        setOwner(owner);

        System.out.print("Enter library address: ");
        setAddress(scanner.nextLine());

        System.out.print("Enter library rating: ");
        setRating(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter library delivery employees: ");
        int deliveryEmployeesCount = scanner.nextInt();
        scanner.nextLine();

        deliveryEmployees = new ArrayList<DeliveryEmployee>();

        for (int i = 0; i < deliveryEmployeesCount; i++) {
            DeliveryEmployee deliveryEmployee = new DeliveryEmployee();
            deliveryEmployee.read(scanner);
            deliveryEmployees.add(deliveryEmployee);
        }

        System.out.print("Enter library products: ");
        int productsCount = scanner.nextInt();
        scanner.nextLine();

        products = new ArrayList<Product>();

        for (int i = 0; i < productsCount; i++) {
            Product product = new Book();
            product.read(scanner);
            addProduct(product);
        }
    }

    @Override
    public String toString() {
        return "Library[" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", deliveryEmployees=" + deliveryEmployees +
                ", products=" + products +
                ", books=" + books +
                ']';
    }
}
