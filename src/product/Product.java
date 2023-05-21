package product;

import java.util.Scanner;

public abstract class Product {
    protected int id;

    protected String name;
    protected double price;
    protected double weight; // each product will have its unit of measurement
    protected String measurements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public abstract double getPrice();
    public abstract void setPrice(double price);

    public abstract double getWeight();
    public abstract void setWeight(double weight);

    public String getMeasurements() {
        return measurements;
    }
    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public abstract void read(Scanner scanner);

    @Override
    public abstract String toString();

}
