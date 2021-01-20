package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conessione {
    private static String user="root";
    private static String password="angelo99";

    public static void main(String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/medqueue?serverTimezone=UTC&useLegacyDatetimeCode=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
            Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("Connessione OK \n");
            con.close();
        }
        catch(ClassNotFoundException e) {
            System.out.println("DB driver not fount \n");
            System.out.println(e);
        }
        catch(Exception e) {
            System.out.println("Connessione Fallita \n");
            System.out.println(e);
        }
    }
}
