package repository;

import database.DatabaseConfiguration;
import product.Flower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlowerRepository {

    public void insertFlower(Flower flower) {
        String commandProduct = "INSERT INTO products (name, price, product_type_id) VALUES (?, ?, ?)";
        String commandFlower = "INSERT INTO flowers (product_id, species, color) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {

            ProductTypeRepository productTypeRepository = new ProductTypeRepository();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, flower.getName());
            preparedStatementProduct.setDouble(2, flower.getPrice());
            preparedStatementProduct.setInt(3, productTypeRepository.getProductTypeByName("flower"));

            preparedStatementProduct.execute();

            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementFlower = connection.prepareStatement(commandFlower);
            preparedStatementFlower.setInt(1, productRepository.getProductByName(flower.getName()));
            preparedStatementFlower.setString(2, flower.getSpecies());
            preparedStatementFlower.setString(3, flower.getColor());

            preparedStatementFlower.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Flower getFlowerByName(String name) {
        String query = "SELECT * FROM flowers JOIN products p WHERE product_id = p.id AND p.name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToFlower(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Flower mapToFlower(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Flower(
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getDouble("weight"),
                    resultSet.getString("measurements"),
                    resultSet.getString("species"),
                    resultSet.getString("color")
            );
        }
        return null;
    }

    public void updateFlowerPrice(String name, double price) {
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

    public void deleteFlowerByName(String name) {
        String commandFlower = "DELETE FROM flowers WHERE product_id = ?";
        String commandProduct = "DELETE FROM products WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementFlower = connection.prepareStatement(commandFlower);
            preparedStatementFlower.setInt(1, productRepository.getProductByName(name));

            preparedStatementFlower.execute();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, name);

            preparedStatementProduct.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
