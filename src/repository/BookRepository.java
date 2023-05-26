package repository;

import database.DatabaseConfiguration;
import product.Book;
import service.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {

    public void insertBook(Book book) {
        String commandProduct = "INSERT INTO products (name, price, product_type_id) VALUES (?, ?, ?)";
        String commandBook = "INSERT INTO books (product_id, author, numberOfPages, releaseDate) VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductTypeRepository productTypeRepository = new ProductTypeRepository();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, book.getName());
            preparedStatementProduct.setDouble(2, book.getPrice());
            preparedStatementProduct.setInt(3, productTypeRepository.getProductTypeByName("book"));

            preparedStatementProduct.execute();

            ProductRepository productRepository = new ProductRepository();
            PreparedStatement preparedStatementBook = connection.prepareStatement(commandBook);
            preparedStatementBook.setInt(1, productRepository.getProductByName(book.getName()));
            preparedStatementBook.setString(2, book.getAuthor());
            preparedStatementBook.setInt(3, book.getNumberOfPages());
            preparedStatementBook.setDate(4, java.sql.Date.valueOf(book.getReleaseDate()));

            preparedStatementBook.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Book getBookByName(String name) {
        String query = "SELECT * FROM books JOIN products p WHERE product_id = p.id AND p.name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToBook(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Book mapToBook(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Book(
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getDouble("weight"),
                    resultSet.getString("measurements"),
                    resultSet.getString("author"),
                    resultSet.getInt("numberOfPages"),
                    resultSet.getDate("releaseDate").toLocalDate()
            );
        }

        return null;
    }

    public void updateBookPrice(String name, double price) {
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

    public void deleteBookByName(String name) {
        String commandBook = "DELETE FROM books WHERE product_id = ?";
        String commandProduct = "DELETE FROM products WHERE name = ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            ProductRepository productRepository = new ProductRepository();

            PreparedStatement preparedStatementBook = connection.prepareStatement(commandBook);
            preparedStatementBook.setInt(1, productRepository.getProductByName(name));

            preparedStatementBook.execute();

            PreparedStatement preparedStatementProduct = connection.prepareStatement(commandProduct);
            preparedStatementProduct.setString(1, name);

            preparedStatementProduct.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
