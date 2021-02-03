package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** Classe che permette operazioni sul database. */
public class DataAccess implements DaoInterface{

  public DataAccess(){}

  /**
   * Metodo che ci permettere di ottenere una prenotazione dal database in base all'id.
   *
   * @param id id della prenotazione che si vuole prelevare dalla collezione Prenotazione
   * @return prenotazione della collezione Prenotazione che ha come id, l'id passato come parametro al metodo
   *
   */
  public PrenotazioneBean getPrenotazione(int id) {
    PrenotazioneBean prenotazione = new PrenotazioneBean();
    try {
      String sql = "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
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
   * @return struttura ospedaliera della collezione Struttura che ha come id, l'id passato come parametro
   */
  public StrutturaBean getStruttura(int id) {
    StrutturaBean struttura = new StrutturaBean();
    try {
      String sql = "SELECT * FROM MedQueue.Struttura WHERE Struttura.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        struttura.setId(rs.getInt(1));
        struttura.setNome(rs.getString(2));
        struttura.setIndirizzo(rs.getString(3));
        struttura.setNumeroDiTelefono(rs.getString(4));
      }
      query.close();
      rs.close();
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
   * @return operazione della collezione Operazione che ha come id, l'id passato come parametro al metodo
   */
  public OperazioneBean getOperazione(int id) {
    OperazioneBean operazione = new OperazioneBean();
    try {
      String sql = "SELECT * FROM MedQueue.Operazione WHERE Operazione.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        operazione.setId(rs.getInt(1));
        operazione.setTipoOperazione(rs.getString(2));
        operazione.setDescrizione(rs.getString(3));
      }
      query.close();
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return operazione;
  }

  /**
   * Metodo che restituisce un impiegato di una struttura ospedaliera in base al codice fiscale.
   *
   * @param codicefiscale codice fiscale dell'impiegato che si vuole prelevare dalla collezione Impiegato
   * @return impiegato della collezione Impiegato che ha come codice fiscale, il codice fiscale passato come parametro al metodo
   */
  public ImpiegatoBean getImpiegato(String codicefiscale) {
    ImpiegatoBean impiegato = new ImpiegatoBean();
    try {
      String sql = "SELECT * FROM MedQueue.Impiegato WHERE Impiegato.codiceFiscale = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setString(1, codicefiscale);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
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
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return impiegato;
  }

  /**
   * Metodo che restituisce tutte le operazioni per cui è possibile prenotarsi.
   *
   * @return tutte le operazioni che fanno parte della collezione Operazione
   */
  public ArrayList<OperazioneBean> getOperazioni() {
    ArrayList<OperazioneBean> operazioni = new ArrayList<OperazioneBean>();
    try {
      String sql = "SELECT * FROM MedQueue.Operazione";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        operazioni.add(
            new OperazioneBean(
               rs.getInt(1), rs.getString(2), rs.getString(3)));
      }
      query.close();
      rs.close();
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
   */
  public int deletePrenotazione(int id) {
    int delete = 0;
    try {
      String sql = "DELETE Prenotazione FROM MedQueue.Prenotazione WHERE Prenotazione.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      delete = query.executeUpdate();
      query.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return delete;
  }

  /**
   * Metodo che restituisce il numero di prenotazioni da servire in base all'id dell'oprazione e
   * l'id della struttura.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return size delle prenotazioni della collezione Prenotazione che hanno come idStruttura l'idStruttura
   * passato come parametro, come idOperazione l'idOperazione passato come parametro e con la convalida a true
   */
  public int numPrenotazioni(int idOperazione, int idStruttura) {
    int count = 0;
    try {
      String sql =
          "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.convalida = 1 "
              + "AND Prenotazione.idOperazione = ?"
              + " AND Prenotazione.idStruttura = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, idOperazione);
      query.setInt(2, idStruttura);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        count++;
      }
      query.close();
      rs.close();
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
   * @return prenotazione della collezione Prenotazione che hanno come idStruttura l'idStruttura
   * passato come parametro, come idOperazione l'idOperazione passato come parametro, con convalida a true
   * ed e la prima in odrine d'orario
   *
   *
   */
  public PrenotazioneBean serviPrenotazione(int idOperazione, int idStruttura) {
    PrenotazioneBean prenotazione = new PrenotazioneBean();
    try {
      String sql =
          "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.convalida = 1 AND "
              + "Prenotazione.idOperazione = ? AND Prenotazione.idStruttura = ? ORDER BY ora";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, idOperazione);
      query.setInt(2, idStruttura);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
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
