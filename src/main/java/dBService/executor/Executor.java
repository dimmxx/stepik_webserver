package dBService.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {

    private Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(update);
        statement.close();
    }
}
