package database;

import repository.RepositoryHelper;
import service.AuditService;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseSetup {

    private AuditService auditService = AuditService.getInstance();

    public void createTables() {
        auditService.logAction("Create tables");
        File file = new File("files/QueryCreateTables.txt");

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
        auditService.logAction("Add rows");
        File file = new File("files/QueryAddRows.txt");

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
        auditService.logAction("Delete all rows");
        File file = new File("files/QueryDeleteAllRows.txt");

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
        auditService.logAction("Drop all tables");
        File file = new File("files/QueryDropAllTables.txt");

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
        auditService.logAction("Show table " + tableName);
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
