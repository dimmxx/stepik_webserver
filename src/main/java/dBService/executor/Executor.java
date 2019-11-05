package dBService.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public int execUpdate(String update) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(update);
        int affectedRows = statement.getUpdateCount();

        return affectedRows;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        T value = handler.handle(resultSet);
        resultSet.close();
        statement.close();
        return value;
    }


}
