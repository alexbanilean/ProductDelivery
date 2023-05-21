package product;

import java.util.Scanner;

public class Drink extends Product{

    private Boolean isAlcoholic;
    private Boolean hasSugar;
    private int calories;
    private String ingredients;

    public Drink() {}

    public Drink(String name, double price, double weight, String measurements, Boolean isAlcoholic,
                 Boolean hasSugar, int calories, String ingredients) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.measurements = measurements;
        this.isAlcoholic = isAlcoholic;
        this.hasSugar = hasSugar;
        this.calories = calories;
        this.ingredients = ingredients;
    }

    @Override
    public double getPrice() {
        if (isAlcoholic)
            return price * 1.4; // 40% tax

        if (hasSugar)
            return price * 1.1; // 10% tax

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

    public Boolean getAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(Boolean alcoholic) {
        this.isAlcoholic = alcoholic;
    }

    public Boolean getHasSugar() {
        return hasSugar;
    }

    public void setHasSugar(Boolean hasSugar) {
        this.hasSugar = hasSugar;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void read(Scanner scanner) {

        System.out.println("Enter drink's name: ");
        setName(scanner.nextLine());

        System.out.println("Enter drink's price: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter drink's weight: ");
        setWeight(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter drink's measurements: ");
        setMeasurements(scanner.nextLine());

        System.out.println("Is drink alcoholic? (true/false): ");
        setAlcoholic(scanner.nextBoolean());
        scanner.nextLine();

        System.out.println("Does drink have sugar? (true/false): ");
        setHasSugar(scanner.nextBoolean());
        scanner.nextLine();

        System.out.println("Enter drink's calories: ");
        setCalories(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter drink's ingredients: ");
        setIngredients(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", measurements='" + measurements + '\'' +
                ", isAlcoholic=" + isAlcoholic +
                ", hasSugar=" + hasSugar +
                ", calories=" + calories +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}
