package dBService.dao;

import dBService.dataSets.UsersDataSet;
import dBService.executor.Executor;
import dBService.executor.ResultHandler;
import dBService.executor.ResultHandlerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
    }

    public int insertUser(String name) throws SQLException{
        return executor.execUpdate("insert into users (user_name) values ('" + name + "')");
    }

    public UsersDataSet getUserDataSet(long id) throws SQLException{
        String query = "select * from users where id=" + id;
        return executor.execQuery(query, new ResultHandler<UsersDataSet>() {
            @Override
            public UsersDataSet handle(ResultSet result) throws SQLException {
                result.next();
                return new UsersDataSet(result.getLong("id"), result.getString("user_name"));
            }
        });
    }

    public UsersDataSet getUserDataSetLamda(long id) throws SQLException{
        String query = "select * from users where id=" + id;
        return executor.execQuery(query, result -> {
            result.next();
            return new UsersDataSet(result.getLong("id"), result.getString("user_name"));
        });
    }

    public <T> T getUserDataSetLegacy(long id) throws SQLException{
        String query = "select * from users where id=" + id;
        ResultHandlerImpl <UsersDataSet> resultHandler = new ResultHandlerImpl();

        return executor.execQuery(query, resultHandler);
    }






    public List<UsersDataSet> getAllUsers() throws SQLException{
        String query = "select * from users";
        return executor.execQuery(query, new ResultHandler<List<UsersDataSet>>() {
            @Override
            public List<UsersDataSet> handle(ResultSet resultSet) throws SQLException {
                List<UsersDataSet> list = new ArrayList<>();
                while (!resultSet.isLast()){
                    resultSet.next();
                    list.add(new UsersDataSet(resultSet.getLong(1), resultSet.getString(2)));
                }
                return list;
            }
        });
    }


    public List<UsersDataSet> getAllUsersLambda() throws SQLException{
        String query = "select * from users";
        return executor.execQuery(query, resultSet ->  {
            List<UsersDataSet> list = new ArrayList<>();
                while (!resultSet.isLast()){
                    resultSet.next();
                    list.add(new UsersDataSet(resultSet.getLong(1), resultSet.getString(2)));
                }
                return list;
        });
    }





}
