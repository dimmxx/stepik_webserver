package dBService.dao;

import dBService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
    }

    public void insertUser(String name) throws SQLException{
        executor.execUpdate("insert into users (user_name) values ('" + name + "')");
    }


}
