package persistence;

import business.OperazioneBean;
import business.PrenotazioneBean;
import business.StrutturaBean;

import java.sql.*;
import java.util.ArrayList;


public class DataAccess {
    private static Connection con = null;
    private static String user="root";
    private static String password="root";

    //Connessione
    public static boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/medqueue?serverTimezone=UTC&useLegacyDatetimeCode=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
            con = DriverManager.getConnection(url,user,password);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //Disconnessione
    public static String disconnect() {
        try {
            con.close();
            return "Connessione chiusa correttamente.\n";
        } catch (SQLException e) {
            return "Chiusura connessione fallita.\n";
        }
    }

    public static PrenotazioneBean getPrenotazione(int id){
        PrenotazioneBean prenotazione=new PrenotazioneBean();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT p.* FROM Prenotazione p WHERE p.id='"+id+"'";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                prenotazione.setId(Integer.parseInt(rs.getString(1)));
                prenotazione.setData(rs.getString(2));
                prenotazione.setTime(rs.getString(3));
                prenotazione.setConvalida(Boolean.parseBoolean(rs.getString(4)));
                prenotazione.setCodicefiscale(rs.getString(5));
                prenotazione.setId_operazione(Integer.parseInt(rs.getString(6)));
                prenotazione.setId_struttura(Integer.parseInt(rs.getString(7)));
            }

            st.close();
        } catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return prenotazione;
    }

    public static StrutturaBean getStruttura(int id){
        StrutturaBean struttura=new StrutturaBean();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT s.* FROM Struttura s WHERE s.id='"+id+"'";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                struttura.setId(Integer.parseInt(rs.getString(1)));
                struttura.setNome(rs.getString(2));
                struttura.setIndirizzo(rs.getString(3));
                struttura.setNumerotelefono(rs.getString(4));
            }

            st.close();
        } catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return struttura;

    }

    public static OperazioneBean getOperazione(int id){
        OperazioneBean operazione =new OperazioneBean();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT o.* FROM Operazione o WHERE o.id='"+id+"'";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                operazione.setId(Integer.parseInt(rs.getString(1)));
                operazione.setTipo_operazione(rs.getString(2));
                operazione.setDescrizione(rs.getString(3));
            }

            st.close();
        } catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return operazione;

    }


    /*
    Metodo per verificare se esiste l'impiegato nel database,si utilizza una query basata sul codicefiscale
    Con la query verifichiano se nel database è presente un utente che possiede gia quel codice fiscale
    Se la query non restituisce nulla return false altrimenti return true
     */
    public static boolean verificaDatiImpiegato(String cf,String password){
        boolean verifica=false;
        try {

            Statement st = con.createStatement();
            String sql = "SELECT i.codicefiscale,i.password FROM impiegato i WHERE i.codicefiscale='"+cf+"'&& i.password='"+password+"'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                verifica=true;
            }
            st.close();
        } catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return verifica;
    }

    //Metodo per ottenere operazioni
    public static ArrayList<String> getOperazioni(){
        ArrayList<String> operazioni=new ArrayList<String>();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT o.tipo_operazione From operazione o";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                operazioni.add(rs.getString(1));
            }
            st.close();
        } catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return operazioni;
    }
}
