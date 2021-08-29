package repositories.impl;

import java.sql.*;
import java.util.Optional;

abstract public class PostgreSqlAbstractRepository {

    public PostgreSqlAbstractRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection newConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDatabaseName(), this.getUsername(), this.getPassword()
        );
    }

    protected String getHost() {
        return "localhost";
    }

    protected int getPort() {
        return 5432;
    }

    protected String getDatabaseName() {
        return "web";
    }

    protected String getUsername() {
        return "postgres";
    }

    protected String getPassword() {
        return "postgres";
    }

    protected void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
