package dBService;

import dBService.dao.UsersDAO;
import dBService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private final Connection connection;

    public DBService() {
        //this.connection = getH2Connection();
        this.connection = getMySQLConnection();
    }

    public UsersDataSet getUsersDataSet(long id) throws DBException, SQLException {
        UsersDataSet usersDataSet = new UsersDAO(connection).getUserDataSet(id);
        return usersDataSet;
    }

    public List<UsersDataSet> getAllUsersDataSet() throws SQLException{
        List<UsersDataSet> list = new ArrayList<>();
        list = new UsersDAO(connection).getAllUsers();
        return list;
    }

    public long addUser(String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createDataBase();
            dao.createTable();
            dao.insertUser(name);
            connection.commit();



            return 0;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {

            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public long getUserIdByName(String name) throws DBException, SQLException{
        return new UsersDAO(connection).getUserIdByNameLegacy(name);
    }

    public void printConnectionInfo() {
        try {
            System.out.println("DatabaseProductName: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DataBaseProductVersion: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("DriverName: " + connection.getMetaData().getDriverName());
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("AutoCommit: " + connection.getAutoCommit());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMySQLConnection() {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_TYPE = "jdbc:mysql://";
        String HOST_NAME = "3.133.58.254"; //aws streamddata0719@gmail.com
        String PORT = "3306";
        String DB_NAME = "testdb";
        String USER = "remote_user";
        String PASS = "remote_user";

        try {
            DriverManager.registerDriver((Driver) Class.forName(JDBC_DRIVER).getDeclaredConstructor().newInstance());
            StringBuilder urlString = new StringBuilder();
            urlString.
                    append(DB_TYPE).
                    append(HOST_NAME + ":").
                    append(PORT + "/").
                    append(DB_NAME + "?").
                    append("user=" + USER + "&").
                    append("password=" + PASS + "&useSSL=false");

            Connection connection = DriverManager.getConnection(urlString.toString());
            return connection;
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getH2Connection() {

        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            //Connection connection = DriverManager.getConnection(url, name, pass);
            return ds.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection is closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
