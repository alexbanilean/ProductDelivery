package product;

import java.util.Scanner;

public class Medicine extends Product{

    private String ingredients;
    private String allergens;
    private String contraindications;

    public Medicine() {}

    public Medicine(String name, double price, double weight, String measurements, String ingredients,
                    String allergens, String contraindications) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.measurements = measurements;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.contraindications = contraindications;
    }

    @Override
    public double getPrice() {
        return price * 1.5; // 50% tax
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

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    @Override
    public void read(Scanner scanner) {

        System.out.println("Enter the name of the medicine: ");
        setName(scanner.nextLine());

        System.out.println("Enter the price of the medicine: ");
        setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the weight of the medicine: ");
        setWeight(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Enter the measurements of the medicine: ");
        setMeasurements(scanner.nextLine());

        System.out.println("Enter the ingredients of the medicine: ");
        setIngredients(scanner.nextLine());

        System.out.println("Enter the allergens of the medicine: ");
        setAllergens(scanner.nextLine());

        System.out.println("Enter the contraindications of the medicine: ");
        setContraindications(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", measurements='" + measurements + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", allergens='" + allergens + '\'' +
                ", contraindications='" + contraindications + '\'' +
                '}';
    }
}
