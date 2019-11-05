package dBService.executor;

import dBService.dataSets.UsersDataSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultHandlerImpl <T> implements ResultHandler {
    @Override
    public <T> UsersDataSet handle(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new UsersDataSet(resultSet.getLong(1), resultSet.getString(2));
    }
}
