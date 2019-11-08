package dBService.dao;

import dBService.dataSets.UsersDataSet;
import dBService.executor.Executor;
import dBService.executor.ResultHandler;
import dBService.executor.ResultHandlerImpl;
import dBService.executor.ResultHandlerImplForGetUserIdByName;

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

    public void createDataBase() throws SQLException {
        executor.execUpdate("create database if not exists testdb");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
    }

    public void insertUser(String name) throws SQLException{
        executor.execUpdate("insert into users (user_name) values ('" + name + "')");
    }

    public long getUserIdByName(String name) throws SQLException{
        String query = "select id from users where user_name='" + name +  "'";
        Long id = executor.execQuery(query, new ResultHandler<Long>() {
            @Override
            public Long handle(ResultSet resultSet) throws SQLException {
                if(!resultSet.isLast()) {
                    resultSet.next();
                    return resultSet.getLong(1);
                }else return 0L;
            }
        });
        System.out.println(id);
        return id;
    }

    public long getUserIdByNameLegacy(String name) throws SQLException{
        String query = "select id from users where user_name='" + name +  "'";
        ResultHandlerImplForGetUserIdByName resultHandler = new ResultHandlerImplForGetUserIdByName();
        return executor.execQueryLegacy(query, resultHandler);
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

    public UsersDataSet getUserDataSetLegacy(long id) throws SQLException{
        String query = "select * from users where id=" + id;
        ResultHandlerImpl <UsersDataSet> resultHandler = new ResultHandlerImpl();
        UsersDataSet usersDataSet = (UsersDataSet) executor.execQuery(query, resultHandler);
        return usersDataSet;
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
