package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** Classe che permette operazioni sul database. */
public class DataAccess implements DaoInterface {


  public DataAccess() {}

  /**
   * Metodo che ci permettere di ottenere una prenotazione dal database in base all'id.
   *
   * @param id id della prenotazione che si vuole prelevare dalla collezione Prenotazione
   * @return ritorna la prenotazione oppure null se non è presente nel database
   * @pre id>0
   * @post Prenotazione->select(p|p.id=id)
   */
  public PrenotazioneBean getPrenotazione(int id) {
    PrenotazioneBean prenotazione = null;
    Connection con = null;
    try {
      String sql = "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.Id = ?";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        prenotazione = new PrenotazioneBean();
        prenotazione.setId(rs.getInt(1));
        prenotazione.setData(rs.getDate(2));
        prenotazione.setTime(rs.getTime(3));
        prenotazione.setConvalida(rs.getBoolean(4));
        prenotazione.setCodiceFiscale(rs.getString(5));
        prenotazione.setIdOperazione(rs.getInt(6));
        prenotazione.setIdStruttura(rs.getInt(7));
      }
      rs.close();
      query.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return prenotazione;
  }

  /**
   * Metodo che ci permette di ottenere una struttura ospedaliera dal database in base all'id.
   *
   * @param id id della struttura ospedaliera che si vuole prelevare dalla collezione Struttura
   * @return ritorna la struttura ospedaliera oppure null se non è presente nel database
   * @pre id>0
   * @post Struttura->select(s|s.id==id)
   */
  public StrutturaBean getStruttura(int id) {
    StrutturaBean struttura = null;
    Connection con = null;
    try {
      String sql = "SELECT * FROM MedQueue.Struttura WHERE Struttura.Id = ?";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        struttura = new StrutturaBean();
        struttura.setId(rs.getInt(1));
        struttura.setNome(rs.getString(2));
        struttura.setIndirizzo(rs.getString(3));
        struttura.setNumeroDiTelefono(rs.getString(4));
      }
      query.close();
      rs.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return struttura;
  }

  /**
   * Metodo che ci permette di ottenere un operazione per cui l'utente si puo prenotare in base
   * all'id.
   *
   * @param id id dell'operazione che si vuole prelevare dalla collezione Operazione
   * @return ritorna l'operazione
   * @pre id>o
   * @post Operazione->select(o|o.id==id)
   */
  public OperazioneBean getOperazione(int id) {
    OperazioneBean operazione = null;
    Connection con = null;
    try {
      String sql = "SELECT * FROM MedQueue.Operazione WHERE Operazione.Id = ?";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        operazione = new OperazioneBean();
        operazione.setId(rs.getInt(1));
        operazione.setTipoOperazione(rs.getString(2));
        operazione.setDescrizione(rs.getString(3));
      }
      query.close();
      rs.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return operazione;
  }

  /**
   * Metodo che restituisce un impiegato di una struttura ospedaliera in base al codice fiscale.
   *
   * @param codicefiscale codice fiscale dell'impiegato che si vuole prelevare dalla collezione
   *     Impiegato
   * @return ritorna l'impiegato ospedaliero
   * @pre codicefiscale!=null && codicefiscale.lenght==16
   * @post Impiegato->select(i!i.codicefiscale==codicefiscale);
   */
  public ImpiegatoBean getImpiegato(String codicefiscale) {
    ImpiegatoBean impiegato = null;
    Connection con = null;
    try {
      String sql = "SELECT * FROM MedQueue.Impiegato WHERE Impiegato.codiceFiscale = ?";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setString(1, codicefiscale);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        impiegato = new ImpiegatoBean();
        impiegato.setCodicefiscale(rs.getString(1));
        impiegato.setPassword(rs.getString(2));
        impiegato.setNome(rs.getString(3));
        impiegato.setCognome(rs.getString(4));
        impiegato.setDataDiNascita(rs.getDate(5));
        impiegato.setIndirizzoEmail(rs.getString(6));
        impiegato.setNumeroDiTelefono(rs.getString(7));
        impiegato.setIdStruttura(Integer.parseInt(rs.getString(8)));
      }
      query.close();
      rs.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return impiegato;
  }

  /**
   * Metodo che restituisce tutte le operazioni per cui è possibile prenotarsi.
   *
   * @return ritorna una lista di operazioni
   * @post Operazioni->asSet(Operazioni)
   */
  public ArrayList<OperazioneBean> getOperazioni() {
    ArrayList<OperazioneBean> operazioni = new ArrayList<OperazioneBean>();
    Connection con = null;
    try {
      String sql = "SELECT * FROM MedQueue.Operazione";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        operazioni.add(new OperazioneBean(rs.getInt(1), rs.getString(2), rs.getString(3)));
      }
      query.close();
      rs.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }

    return operazioni;
  }

  /**
   * Metodo per cancellare una prenotazione dal database in base all'id.
   *
   * @param id id della prenotazione della collezione prenotazione da cancellare
   * @return 0 se la prenotazione non è stata cancellata, 1 se la prentoazione è stata cancellata
   * @pre id>0
   * @post !Prenotazione->exists(p|p.id==id)
   */
  public int deletePrenotazione(int id) {
    int delete = 0;
    Connection con = null;
    try {
      String sql = "DELETE Prenotazione FROM MedQueue.Prenotazione WHERE Prenotazione.Id = ?";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setInt(1, id);
      delete = query.executeUpdate();
      query.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return delete;
  }

  /**
   * Metodo che restituisce il numero di prenotazioni convalidate in base all'id dell'oprazione e
   * l'id della struttura.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return numero prenotazioni convalidate
   * @pre idOperazione>0 && idStruttura>0
   * @post Prenotazione->exists(p|p.idStruttura==idStruttura && p.idOperazione==idOperazione && p.convalida==true).size()
   */
  public int numPrenotazioni(int idOperazione, int idStruttura) {
    int count = 0;
    Connection con = null;
    try {
      String sql =
          "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.convalida = 1 "
              + "AND Prenotazione.idOperazione = ?"
              + " AND Prenotazione.idStruttura = ?";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setInt(1, idOperazione);
      query.setInt(2, idStruttura);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        count++;
      }
      query.close();
      rs.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return count;
  }

  /**
   * Metodo che restituisce la prima operazione da servire in base all'ora della prenotazione.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return prenotazione da servire
   * @pre idStruttura>0 && idOperazione>0
   * @post Prenotazione->select(p|p.idStruttura==idStruttura && p.idOperazione==idOperazione && p.convalida==true)
   */
  public PrenotazioneBean serviPrenotazione(int idOperazione, int idStruttura) {
    PrenotazioneBean prenotazione = null;
    Connection con = null;
    try {
      String sql =
          "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.convalida = 1 AND "
              + "Prenotazione.idOperazione = ? AND Prenotazione.idStruttura = ? ORDER BY ora";
      con = DriverManagerConnectionPool.getConnection();
      PreparedStatement query = con.prepareStatement(sql);
      query.setInt(1, idOperazione);
      query.setInt(2, idStruttura);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        prenotazione = new PrenotazioneBean();
        prenotazione.setId(Integer.parseInt(rs.getString(1)));
        prenotazione.setData(rs.getDate(2));
        prenotazione.setTime(rs.getTime(3));
        prenotazione.setConvalida(Boolean.parseBoolean(rs.getString(4)));
        prenotazione.setCodiceFiscale(rs.getString(5));
        prenotazione.setIdOperazione(rs.getInt(6));
        prenotazione.setIdStruttura(rs.getInt(7));
        query.close();
        return prenotazione;
      }
      query.close();
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return null;
  }
}
