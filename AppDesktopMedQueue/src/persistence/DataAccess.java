package persistence;


import java.sql.*;

public class DataAccess {

    private static Connection connection = null;

    private static String username = "root";
    private static String psw = "root";

    public static boolean connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/medqueue?serverTimezone=UTC&useLegacyDatetimeCode=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
            connection = DriverManager.getConnection(url,username,psw);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static String disconnect() {

        try {
            connection.close();
            return "Connessione chiusa correttamente.\n";
        } catch (SQLException e) {
            return "Chiusura connessione fallita.\n";
        }
    }

}
