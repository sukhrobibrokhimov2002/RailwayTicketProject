package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnect  {
    public static final String USER="postgres";
    public static final String PASSWORD="05798510";
    static  String url;
    static String host="localhost";
    static String dbName="Eticket";
    public static String port="5432";


    //Method for connecting Database
    public static Connection getConnection(){
         Connection connection=null;
        url="jdbc:postgresql://"+host+":"+port+"/"+dbName;
        try {
            connection= DriverManager.getConnection(url,USER,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
