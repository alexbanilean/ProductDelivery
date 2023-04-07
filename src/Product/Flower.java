package Product;

import java.util.Scanner;

public class Flower extends Product{
    private String species;
    private String color;

    public Flower() {}

    public Flower(String name, double price, double weight, String measurements, String species, String color) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.measurements = measurements;
        this.species = species;
        this.color = color;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getWeight() {
        return weight * 1000; // in grams
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the flower: ");
        setName(scanner.nextLine());

        System.out.println("Enter the price of the flower: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the weight of the flower: ");
        setWeight(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the measurements of the flower: ");
        setMeasurements(scanner.nextLine());

        System.out.println("Enter the species of the flower: ");
        setSpecies(scanner.nextLine());

        System.out.println("Enter the color of the flower: ");
        setColor(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", measurements='" + measurements + '\'' +
                ", species='" + species + '\'' +
                ", color='" + color + '\'' +
                '}';
    }


}
