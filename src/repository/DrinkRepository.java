package repository;

import database.DatabaseConfiguration;
import product.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrinkRepository {

    public void insertDrink(Drink drink) {
        String commandProduct = "INSERT INTO products (name, price, product_type_id) VALUES (?, ?, ?)";
        String commandDrink = "INSERT INTO drinks (product_id, calories, isAlcoholic, hasSugar) VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {

            ProductTypeRepository productTypeRepository = new ProductTypeRepository();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, drink.getName());
            preparedStatementProduct.setDouble(2, drink.getPrice());
            preparedStatementProduct.setInt(3, productTypeRepository.getProductTypeByName("drink"));

            preparedStatementProduct.execute();

            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementDrink = connection.prepareStatement(commandDrink);
            preparedStatementDrink.setInt(1, productRepository.getProductByName(drink.getName()));
            preparedStatementDrink.setInt(2, drink.getCalories());
            preparedStatementDrink.setBoolean(3, drink.getAlcoholic());
            preparedStatementDrink.setBoolean(4, drink.getHasSugar());

            preparedStatementDrink.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Drink getDrinkByName(String name) {
        String query = "SELECT * FROM drinks JOIN products p WHERE product_id = p.id AND p.name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToDrink(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Drink mapToDrink(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            return new Drink(
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getDouble("weight"),
                    resultSet.getString("measurements"),
                    resultSet.getBoolean("isAlcoholic"),
                    resultSet.getBoolean("hasSugar"),
                    resultSet.getInt("calories"),
                    resultSet.getString("ingredients")
            );
        }

        return null;
    }

    public void updateDrinkPrice(String name, double price) {
        String command = "UPDATE products SET price = ? WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteDrinkByName(String name) {
        String commandDrink = "DELETE FROM drinks WHERE product_id = ?";
        String commandProduct = "DELETE FROM products WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementDrink = connection.prepareStatement(commandDrink);
            preparedStatementDrink.setInt(1, productRepository.getProductByName(name));

            preparedStatementDrink.execute();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, name);

            preparedStatementProduct.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
