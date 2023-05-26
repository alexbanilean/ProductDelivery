package service;

import database.DatabaseSetup;
import product.*;
import repository.*;

import java.util.Scanner;

public class DatabaseManager {

    private final DatabaseSetup databaseSetup;
    private final Scanner scanner;

    private static DatabaseManager instance = null;

    private void initDatabase() {
        databaseSetup.dropAllTables();
        databaseSetup.createTables();
        databaseSetup.addRows();
    }

    private DatabaseManager() {
        databaseSetup = new DatabaseSetup();
        scanner = new Scanner(System.in);
        initDatabase();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void showMenu() {
        System.out.println("\n----------Database menu----------");
        System.out.println("1. Get the rows of a table");
        System.out.println("2. Insert product");
        System.out.println("3. Get product by name");
        System.out.println("4. Update product price");
        System.out.println("5. Delete product");
        System.out.println("6. Delete information from all tables");
        System.out.println("7. Drop all tables");
        System.out.println("8. Return to main menu");
    }

    private void insertProduct() {
        System.out.println("What type of product do you want to insert?");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Book");
        System.out.println("4. Flower");
        System.out.println("5. Medicine");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                FoodRepository foodRepository = new FoodRepository();
                Food food = new Food();
                food.read(scanner);
                foodRepository.insertFood(food);
            }
            case 2 -> {
                DrinkRepository drinkRepository = new DrinkRepository();
                Drink drink = new Drink();
                drink.read(scanner);
                drinkRepository.insertDrink(drink);
            }
            case 3 -> {
                BookRepository bookRepository = new BookRepository();
                Book book = new Book();
                book.read(scanner);
                bookRepository.insertBook(book);
            }
            case 4 -> {
                FlowerRepository flowerRepository = new FlowerRepository();
                Flower flower = new Flower();
                flower.read(scanner);
                flowerRepository.insertFlower(flower);
            }
            case 5 -> {
                MedicineRepository medicineRepository = new MedicineRepository();
                Medicine medicine = new Medicine();
                medicine.read(scanner);
                medicineRepository.insertMedicine(medicine);
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private void getProductByName() {
        System.out.println("What type of product do you want to get?");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Book");
        System.out.println("4. Flower");
        System.out.println("5. Medicine");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the name of the product: ");
        String name = scanner.nextLine();

        switch (choice) {
            case 1 -> {
                FoodRepository foodRepository = new FoodRepository();
                Food food = foodRepository.getFoodByName(name);
                System.out.println(food);
            }
            case 2 -> {
                DrinkRepository drinkRepository = new DrinkRepository();
                Drink drink = drinkRepository.getDrinkByName(name);
                System.out.println(drink);
            }
            case 3 -> {
                BookRepository bookRepository = new BookRepository();
                Book book = bookRepository.getBookByName(name);
                System.out.println(book);
            }
            case 4 -> {
                FlowerRepository flowerRepository = new FlowerRepository();
                Flower flower = flowerRepository.getFlowerByName(name);
                System.out.println(flower);
            }
            case 5 -> {
                MedicineRepository medicineRepository = new MedicineRepository();
                Medicine medicine = medicineRepository.getMedicineByName(name);
                System.out.println(medicine);
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private void updateProductPrice() {
        System.out.println("What type of product do you want to update?");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Book");
        System.out.println("4. Flower");
        System.out.println("5. Medicine");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the name of the product: ");
        String name = scanner.nextLine();

        System.out.println("Enter the new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                FoodRepository foodRepository = new FoodRepository();
                foodRepository.updateFoodPrice(name, price);
            }
            case 2 -> {
                DrinkRepository drinkRepository = new DrinkRepository();
                drinkRepository.updateDrinkPrice(name, price);
            }
            case 3 -> {
                BookRepository bookRepository = new BookRepository();
                bookRepository.updateBookPrice(name, price);
            }
            case 4 -> {
                FlowerRepository flowerRepository = new FlowerRepository();
                flowerRepository.updateFlowerPrice(name, price);
            }
            case 5 -> {
                MedicineRepository medicineRepository = new MedicineRepository();
                medicineRepository.updateMedicinePrice(name, price);
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private void deleteProduct() {
        System.out.println("What type of product do you want to delete?");
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Book");
        System.out.println("4. Flower");
        System.out.println("5. Medicine");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the name of the product: ");
        String name = scanner.nextLine();

        switch (choice) {
            case 1 -> {
                FoodRepository foodRepository = new FoodRepository();
                foodRepository.deleteFoodByName(name);
            }
            case 2 -> {
                DrinkRepository drinkRepository = new DrinkRepository();
                drinkRepository.deleteDrinkByName(name);
            }
            case 3 -> {
                BookRepository bookRepository = new BookRepository();
                bookRepository.deleteBookByName(name);
            }
            case 4 -> {
                FlowerRepository flowerRepository = new FlowerRepository();
                flowerRepository.deleteFlowerByName(name);
            }
            case 5 -> {
                MedicineRepository medicineRepository = new MedicineRepository();
                medicineRepository.deleteMedicineByName(name);
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    public void DatabaseManagerMenu() {

        label:
        while(true) {
            showMenu();
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the name of the table: ");
                    String tableName = scanner.nextLine();
                    databaseSetup.showTable(tableName);
                }
                case 2 -> insertProduct();
                case 3 -> getProductByName();
                case 4 -> updateProductPrice();
                case 5 -> deleteProduct();
                case 6 -> databaseSetup.deleteAllRows();
                case 7 -> {
                    databaseSetup.deleteAllRows();
                    databaseSetup.dropAllTables();
                }
                case 8 -> {
                    break label;
                }
                default -> System.out.println("Invalid choice!");
            }

            System.out.println("Do you want to continue with another action? (y/n)");

            String answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("y")) {
                break;
            }
        }
    }
}
