package product;

import java.time.LocalDate;
import java.util.Scanner;

public class Book extends Product{
    private String author;
    private int numberOfPages;
    private LocalDate releaseDate;

    public Book() {}

    public Book(String name, double price, double weight, String measurements,
                String author, int numberOfPages, LocalDate releaseDate) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.measurements = measurements;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.releaseDate = releaseDate;
    }

    @Override
    public double getPrice() {
        if (releaseDate.getYear() < 2005)
            return price * 0.8; // 20% discount

        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getWeight() {
        return weight; // in kilograms
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the book: ");
        setName(scanner.nextLine());

        System.out.println("Enter the price of the book: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the weight of the book: ");
        setWeight(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the measurements of the book: ");
        setMeasurements(scanner.nextLine());

        System.out.println("Enter the author of the book: ");
        setAuthor(scanner.nextLine());

        System.out.println("Enter the number of pages of the book: ");
        setNumberOfPages(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter the release date of the book: ");
        setReleaseDate(LocalDate.parse(scanner.nextLine()));
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", measurements='" + measurements + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", releaseDate=" + releaseDate +
                '}';
    }


}
