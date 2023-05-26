package database;

import repository.RepositoryHelper;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseSetup {

    public void createTables() {
        File file = new File("Files/QueryCreateTables.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String query;

            while((query = br.readLine()) != null) {
                Connection dbConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeSql(dbConnection, query);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error executing query: " + query);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addRows() {
        File file = new File("Files/QueryAddRows.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String query;

            while((query = br.readLine()) != null) {
                Connection dbConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeUpdateSql(dbConnection, query);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error executing query: " + query);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteAllRows() {
        File file = new File("Files/QueryDeleteAllRows.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String query;

            while((query = br.readLine()) != null) {
                Connection dbConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeUpdateSql(dbConnection, query);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error executing query: " + query);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void dropAllTables() {
        File file = new File("Files/QueryDropAllTables.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String query;

            while((query = br.readLine()) != null) {
                Connection dbConnection = DatabaseConfiguration.getDatabaseConnection();
                RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

                try {
                    repositoryHelper.executeSql(dbConnection, query);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error executing query: " + query);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void showTable(String tableName) {
        String query = "SELECT * FROM ";

        ArrayList<String> tables = new ArrayList<>(Arrays.asList("product_types",
                "products", "drinks", "food", "medicine",
                "books", "flowers", "bouquets", "flower_bouquets",
                "user_types", "users", "administrators", "customers",
                "shop_types", "shops", "delivery_employees",
                "shop_products", "orders", "order_products"
        ));

        if(tables.contains(tableName)) {
            query += tableName;
        } else {
            System.out.println("Invalid table name!");
            return;
        }

        Connection dbConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(dbConnection, query);

            while(resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnsNumber = resultSetMetaData.getColumnCount();

                for(int i = 1; i <= columnsNumber; i++) {
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + resultSetMetaData.getColumnName(i));

                    if(i > 1) {
                        System.out.print(", \n");
                    }
                }

                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing query: " + query);
        }
    }
}
