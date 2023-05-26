package repository;

import database.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {
    public int getProductByName(String name) {
        String command = "SELECT * FROM products WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
