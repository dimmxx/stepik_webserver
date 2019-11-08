package dBService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultHandlerImplForGetUserIdByName {


    public long handle(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.isLast());
        if(resultSet.next()){
            System.out.println(resultSet.isLast());

            return resultSet.getLong(1);
        }else return 0;
    }
}
