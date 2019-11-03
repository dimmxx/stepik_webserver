package dBService;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    private final Connection connection;

    public DBService(){
        this.connection = getH2Connection();
    }


    public long addUser(String name){

        return 0;
    }








    public void printConnectionInfo() {
        try{
            System.out.println(connection.getMetaData().getDatabaseProductName());
            System.out.println(connection.getMetaData().getDatabaseProductVersion());
            System.out.println(connection.getMetaData().getDriverName());
            System.out.println(connection.getAutoCommit());

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static Connection getH2Connection(){

        try{
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            //Connection connection = DriverManager.getConnection(url, name, pass);
            return ds.getConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
