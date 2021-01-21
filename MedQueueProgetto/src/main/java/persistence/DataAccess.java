package persistence;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class DataAccess {
    private static Connection con = null;
    private static String user="root";
    private static String password="root";




    //Connessione
    public static boolean connect(String username, String psw) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/medqueue?serverTimezone=UTC&useLegacyDatetimeCode=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
            con = DriverManager.getConnection(url,username,psw);
            System.out.println("Connesso");
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

    //Inserimento Utente
    public  void insUtente(String CF,String nome,String cognome,String password,String e_mail,String numero_telefono,String data_nascita) throws IOException {
        try {
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
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }

    //Inserimento persistence.Prenotazione
    public  void insPrenotazione(String data,String ora,Boolean convalida,String codicefiscale,int id_operazione,int id_struttura) throws IOException {
        try {
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
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    //Inserimento Struttura
    public  void insStruttura(String nome,String indirizzo,String numero_telefono) throws IOException {
        try {
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
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    //Inserimento Operazione
    public  void insOperazione(String tipo_operazione,String descrizione) throws IOException {
        try {
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
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }

    //Inserimento Ambulatorio
    public  void insAmbulatorio(String nome,int id_struttura) throws IOException {
        try {
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
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    //Inserimento Impiegato
    public  void insImpiegato(String codicefiscale,String password,String nome,String cognome,String data_di_nascita,String email,String numero_tele,int id_struttura) throws IOException {
        try {
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
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }


    /*
    Metodo per ottenere la lista delle prenotazioni dell'utente, si utilizza una query basata sul codicefiscale.
    La query restituira l'id della prenotazione il nome della struttura e il tipo di operazione
    L'oggetto prenotazioneUtente rappresenta la singola prenotazione(id,nome_struttura,tipo_operazione) della lista prenotazioni
     */
    public  ArrayList<PrenotazioneUtente> getPrenotazioniUtente(String cf){
         ArrayList<PrenotazioneUtente> prenotazioni=new ArrayList<PrenotazioneUtente>();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT p.id,s.nome,o.tipo_operazione FROM Prenotazione p,Struttura s,Operazione o,Utente u WHERE u.codicefiscale='"+cf+"' && u.codicefiscale=p.codicefiscale && p.Id_Struttura=s.Id && p.Id_operazione=o.id";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
               prenotazioni.add(new PrenotazioneUtente(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3)));
            }

            st.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
        return prenotazioni;
    }

    /*
    Metodo per ottenere i dettagli di una prenotazione, si utilizza una query basata sul id prenotazione
    La query restituira id,ora,data prenotazione nome struttura e tipo operazione
    L'oggetto prenotazione rappresentera il dettaglio della prenotazione
     */
    public Prenotazione getDettagliPrenotazione(int id){
        Prenotazione p=new Prenotazione();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT p.id,p.ora,p.data,s.nome,o.tipo_operazione FROM prenotazione p,struttura s,operazione o WHERE p.id='"+id+"' && p.id_struttura=s.id && p.id_operazione=o.id";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                p.setId(Integer.parseInt(rs.getString(1)));
                p.setOra(rs.getString(2));
                p.setData(rs.getString(3));
                p.setStruttura(rs.getString(4));
                p.setOperazione(rs.getString(5));
            }
            st.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return p;
    }

    /*
    Metodo per verificare se esiste gia un utente nel database,si utilizza una query basata sul codicefiscale
    Con la query verifichiano se nel database è presente un utente che possiede gia quel codice fiscale
    Se la query non restituisce nulla return false altrimenti return true
     */
    public  boolean verificaEsistenzaUtente(String cf){
        boolean verifica=false;
        try {
            Statement st = con.createStatement();
            String sql = "SELECT u.codicefiscale FROM utente u WHERE u.codicefiscale='"+cf+"'";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                verifica=true;
            }
            st.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return verifica;
    }

    /*
    Metodo per verificare se nel database esiste un utente che ha le credenziali(codicefiscale,password) richieste
    Con la query verifichiamo se nel database è presente un utente con le determinate credenziali
    Se la query non restituisce nulla return null altrimenti return true
     */
    public  boolean verificaDatiUtente(String cf,String password){
        boolean verifica=false;
        try {

            Statement st = con.createStatement();
            String sql = "SELECT u.codicefiscale,u.password FROM utente u WHERE u.codicefiscale='"+cf+"'&& u.password='"+password+"'";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                verifica=true;
            }
            st.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        return verifica;
    }

    //Metodo per convalidare una prenotazione
    public  void convalidaPrenotazione(int id){
        try {
            Statement st = con.createStatement();
            String sql = "UPDATE Prenotazione SET convalida=? WHERE id=?"; //preparo la stringa da mandare al db
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,true);
            ps.setInt(2,id);
            //---------INVIO DATI AL DATABASE--------
            if(ps.executeUpdate()<0) //invio al db la stringa insermineto utente
                System.err.println("Update failed\n");
            st.close();
        }
        catch(SQLException e) {
            System.err.println("SQLException:"+ e.getMessage());
        }
        System.out.println("\nOperation done\n");
    }

}
