package dBService.executor;

import dBService.dataSets.UsersDataSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultHandlerImpl <T> implements ResultHandler {

    @Override
    public T handle(ResultSet resultSet) throws SQLException {
        resultSet.next();
        UsersDataSet usersDataSet = new UsersDataSet(resultSet.getLong(1), resultSet.getString(2));
        return (T)usersDataSet;
    }
}
