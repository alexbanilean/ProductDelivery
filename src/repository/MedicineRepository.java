package repository;

import database.DatabaseConfiguration;
import product.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineRepository {

    public void insertMedicine(Medicine medicine) {
        String commandProduct = "INSERT INTO products (name, price, product_type_id) VALUES (?, ?, ?)";
        String commandMedicine = "INSERT INTO medicine (product_id, ingredients, allergens) VALUES (?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductTypeRepository productTypeRepository = new ProductTypeRepository();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, medicine.getName());
            preparedStatementProduct.setDouble(2, medicine.getPrice());
            preparedStatementProduct.setInt(3, productTypeRepository.getProductTypeByName("medicine"));

            preparedStatementProduct.execute();

            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementMedicine = connection.prepareStatement(commandMedicine);
            preparedStatementMedicine.setInt(1, productRepository.getProductByName(medicine.getName()));
            preparedStatementMedicine.setString(2, medicine.getIngredients());
            preparedStatementMedicine.setString(3, medicine.getAllergens());

            preparedStatementMedicine.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Medicine getMedicineByName(String name) {
        String query = "SELECT * FROM medicine JOIN products p WHERE product_id = p.id AND p.name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            return mapToMedicine(preparedStatement.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Medicine mapToMedicine(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Medicine(
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getDouble("weight"),
                    resultSet.getString("measurements"),
                    resultSet.getString("ingredients"),
                    resultSet.getString("allergens"),
                    resultSet.getString("contraindications")
            );
        }
        return null;
    }

    public void updateMedicinePrice(String name, double price) {
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

    public void deleteMedicineByName(String name) {
        String commandMedicine = "DELETE FROM medicine WHERE product_id = ?";
        String commandProduct = "DELETE FROM products WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementMedicine = connection.prepareStatement(commandMedicine);
            preparedStatementMedicine.setInt(1, productRepository.getProductByName(name));

            preparedStatementMedicine.execute();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, name);

            preparedStatementProduct.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
