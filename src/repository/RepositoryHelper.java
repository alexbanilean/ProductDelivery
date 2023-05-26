package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryHelper {
    private static RepositoryHelper instance;

    private RepositoryHelper() {}

    public static RepositoryHelper getRepositoryHelper() {
        if (instance == null) {
            instance = new RepositoryHelper();
        }

        return instance;
    }

    public void executeSql(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void executeUpdateSql(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public ResultSet executeQuerySql(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
