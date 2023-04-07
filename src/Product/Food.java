package Product;

import java.util.Scanner;

public class Food extends Product{

    private int calories;
    private String ingredients;
    private String allergens;

    public Food() {}

    public Food(String name, double price, double weight, String measurements,
                int calories, String ingredients, String allergens) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.measurements = measurements;
        this.calories = calories;
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    @Override
    public double getPrice() {
        if (calories < 300)
            return price * 0.9; // 10% discount

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

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the food: ");
        setName(scanner.nextLine());

        System.out.println("Enter the price of the food: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the weight of the food: ");
        setWeight(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the measurements of the food: ");
        setMeasurements(scanner.nextLine());

        System.out.println("Enter the calories of the food: ");
        setCalories(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter the ingredients of the food: ");
        setIngredients(scanner.nextLine());

        System.out.println("Enter the allergens of the food: ");
        setAllergens(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", measurements='" + measurements + '\'' +
                ", calories=" + calories +
                ", ingredients='" + ingredients + '\'' +
                ", allergens='" + allergens + '\'' +
                '}';
    }
}
