package database;

import java.io.IOException;
import java.sql.*;

public class DataAccess {
    private static String user="root";
    private static String password="angelo99";


    //Inserimento Utente
    public static void insUtente(String CF,String nome,String cognome,String password,String e_mail,String numero_telefono,String data_nascita) throws IOException {
        try {
            //------COLLEGAMENTO COL DB-------
            String url = "jdbc:mysql://localhost:3306/medqueue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            //------INSERIMENTO UTENTE-------
            String sql = "INSERT INTO Utente VALUES (?,?,?,?,?,?,?)"; //preparo la stringa da mandare al db
            Date data=Date.valueOf(data_nascita); //converto la data in tipo date
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,CF);
            ps.setString(2,password);
            ps.setString(3,nome);
            ps.setString(4,cognome);
            ps.setDate(5,data);
            ps.setString(6,e_mail);
            ps.setString(7,numero_telefono);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
            con.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }

    //Inserimento Prenotazione

    public static void insPrenotazione(String data,String ora,Boolean convalida,String codicefiscale,int id_operazione,int id_struttura) throws IOException {
        try {
            //------COLLEGAMENTO COL DB-------
            String url = "jdbc:mysql://localhost:3306/medqueue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            //------INSERIMENTO UTENTE-------
            String sql = "INSERT INTO Prenotazione(Data,Ora,Convalida,CodiceFiscale,Id_Operazione,Id_Struttura) VALUES (?,?,?,?,?,?)"; //preparo la stringa da mandare al db
            Date d=Date.valueOf(data); //converto la data in tipo date
            Time o=Time.valueOf(ora); //converto l'ora in tipo time
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,d);
            ps.setTime(2,o);
            ps.setBoolean(3,convalida);
            ps.setString(4,codicefiscale);
            ps.setInt(5,id_operazione);
            ps.setInt(6,id_struttura);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
            con.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    //Inserimento Struttura

    public static void insStruttura(String nome,String indirizzo,String numero_telefono) throws IOException {
        try {
            //------COLLEGAMENTO COL DB-------
            String url = "jdbc:mysql://localhost:3306/medqueue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            //------INSERIMENTO UTENTE-------
            String sql = "INSERT INTO Struttura(Nome,Indirizzo,Numero_di_telefono) VALUES (?,?,?)"; //preparo la stringa da mandare al db
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nome);
            ps.setString(2,indirizzo);
            ps.setString(3,numero_telefono);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
            con.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    //Inserimento Operazione

    public static void insOperazione(String tipo_operazione,String descrizione) throws IOException {
        try {
            //------COLLEGAMENTO COL DB-------
            String url = "jdbc:mysql://localhost:3306/medqueue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            //------INSERIMENTO UTENTE-------
            String sql = "INSERT INTO Operazione(Tipo_Operazione,Descrizione) VALUES (?,?)"; //preparo la stringa da mandare al db
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tipo_operazione);
            ps.setString(2,descrizione);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
            con.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }

    //Inserimento Ambulatorio

    public static void insAmbulatorio(String nome,int id_struttura) throws IOException {
        try {
            //------COLLEGAMENTO COL DB-------
            String url = "jdbc:mysql://localhost:3306/medqueue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            //------INSERIMENTO UTENTE-------
            String sql = "INSERT INTO Ambulatorio(nome,Id_Struttura) VALUES (?,?)"; //preparo la stringa da mandare al db
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nome);
            ps.setInt(2,id_struttura);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
            con.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    //Inserimento Impiegato

    public static void insImpiegato(String codicefiscale,String password,String nome,String cognome,String data_di_nascita,String email,String numero_tele,int id_struttura) throws IOException {
        try {
            //------COLLEGAMENTO COL DB-------
            String url = "jdbc:mysql://localhost:3306/medqueue?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();
            //------INSERIMENTO UTENTE-------
            String sql = "INSERT INTO Impiegato VALUES (?,?,?,?,?,?,?,?)"; //preparo la stringa da mandare al db
            Date d=Date.valueOf(data_di_nascita);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,codicefiscale);
            ps.setString(2,password);
            ps.setString(3,nome);
            ps.setString(4,cognome);
            ps.setDate(5,d);
            ps.setString(6,email);
            ps.setString(7,numero_tele);
            ps.setInt(8,id_struttura);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
            con.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }
}
