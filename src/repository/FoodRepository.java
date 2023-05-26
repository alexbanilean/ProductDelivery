package repository;

import database.DatabaseConfiguration;
import product.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodRepository {

    public void insertFood(Food food) {
        String commandProduct = "INSERT INTO products (name, price, product_type_id) VALUES (?, ?, ?)";
        String commandFood = "INSERT INTO food (product_id, calories, ingredients, allergens) VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {

            ProductTypeRepository productTypeRepository = new ProductTypeRepository();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, food.getName());
            preparedStatementProduct.setDouble(2, food.getPrice());
            preparedStatementProduct.setInt(3, productTypeRepository.getProductTypeByName("food"));

            preparedStatementProduct.execute();

            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementFood = connection.prepareStatement(commandFood);
            preparedStatementFood.setInt(1, productRepository.getProductByName(food.getName()));
            preparedStatementFood.setInt(2, food.getCalories());
            preparedStatementFood.setString(3, food.getIngredients());
            preparedStatementFood.setString(4, food.getAllergens());

            preparedStatementFood.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Food getFoodByName(String name) {
        String query = "SELECT * FROM food JOIN products p WHERE product_id = p.id AND p.name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToFood(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Food mapToFood(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Food(
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getDouble("weight"),
                    resultSet.getString("measurements"),
                    resultSet.getInt("calories"),
                    resultSet.getString("ingredients"),
                    resultSet.getString("allergens")
            );
        }
        return null;
    }

    public void updateFoodPrice(String name, double price) {
        String query = "UPDATE products SET price = ? WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteFoodByName(String name) {
        String commandFood = "DELETE FROM food WHERE product_id = ?";
        String commandProduct = "DELETE FROM products WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementFood= connection.prepareStatement(commandFood);
            preparedStatementFood.setInt(1, productRepository.getProductByName(name));

            preparedStatementFood.execute();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, name);

            preparedStatementProduct.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
