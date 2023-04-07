package Product;

import java.util.ArrayList;
import java.util.Scanner;

public class Bouquet extends Product{

    private ArrayList<Flower> speciesUsed;
    private String scentPallete;
    private String wrapping;

    public Bouquet() {}

    public Bouquet(String name, double price, double weight, String measurements,
                   ArrayList<Flower> speciesUsed, String scentPallete, String wrapping) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.measurements = measurements;
        this.speciesUsed = speciesUsed;
        this.scentPallete = scentPallete;
        this.wrapping = wrapping;
    }

    @Override
    public double getPrice() {
        if (speciesUsed.size() > 5)
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

    public ArrayList<Flower> getSpeciesUsed() {
        return speciesUsed;
    }

    public void setSpeciesUsed(ArrayList<Flower> speciesUsed) {
        this.speciesUsed = speciesUsed;
    }

    public String getScentPallete() {
        return scentPallete;
    }

    public void setScentPallete(String scentPallete) {
        this.scentPallete = scentPallete;
    }

    public String getWrapping() {
        return wrapping;
    }

    public void setWrapping(String wrapping) {
        this.wrapping = wrapping;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the bouquet: ");
        setName(scanner.nextLine());

        System.out.println("Enter the price of the bouquet: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the weight of the bouquet: ");
        setWeight(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the measurements of the bouquet: ");
        setMeasurements(scanner.nextLine());

        System.out.println("Enter the number of species used in the bouquet: ");
        int speciesCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < speciesCount; i++) {
            System.out.println("Enter the name of the species: ");
            String speciesName = scanner.nextLine();

            System.out.println("Enter the price of the species: ");
            double speciesPrice = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the weight of the species: ");
            double speciesWeight = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter the measurements of the species: ");
            String speciesMeasurements = scanner.nextLine();

            System.out.println("Enter the color of the species: ");
            String speciesColor = scanner.nextLine();

            speciesUsed.add(new Flower(speciesName, speciesPrice, speciesWeight,
                            speciesMeasurements, speciesName, speciesColor));
        }

        System.out.println("Enter the scent pallete of the bouquet: ");
        setScentPallete(scanner.nextLine());

        System.out.println("Enter the wrapping of the bouquet: ");
        setWrapping(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", measurements='" + measurements + '\'' +
                ", speciesUsed=" + speciesUsed.toString() +
                ", scentPallete='" + scentPallete + '\'' +
                ", wrapping='" + wrapping + '\'' +
                '}';
    }
}
