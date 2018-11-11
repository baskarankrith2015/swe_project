package com.pancakes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by krithikabaskaran on 10/21/18.
 */
public class SqlReturnClass {
    ResultSet resultSet;
    Connection connection;

    public ResultSet getResultSet() {
        return resultSet;
    }

    Statement statement;

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    SqlReturnClass(ResultSet resultSet, Connection connection, Statement statement) {
        this.resultSet = resultSet;
        this.connection = connection;
        this.statement = statement;
    }

    void close() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();

    }
}
