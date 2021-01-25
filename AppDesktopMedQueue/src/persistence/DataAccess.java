package persistence;

import business.ImpiegatoBean;
import business.OperazioneBean;
import business.PrenotazioneBean;
import business.StrutturaBean;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataAccess {

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static PrenotazioneBean getPrenotazione(int id) {
    PrenotazioneBean prenotazione = new PrenotazioneBean();
    try {
      String sql = "SELECT * FROM MedQueue.Prenotazione WHERE Prenotazione.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        prenotazione.setId(Integer.parseInt(rs.getString(1)));
        prenotazione.setData(rs.getString(2));
        prenotazione.setTime(rs.getString(3));
        prenotazione.setConvalida(Boolean.parseBoolean(rs.getString(4)));
        prenotazione.setCodiceFiscale(rs.getString(5));
        prenotazione.setIdOperazione(Integer.parseInt(rs.getString(6)));
        prenotazione.setIdStruttura(Integer.parseInt(rs.getString(7)));
      }
      rs.close();
      query.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return prenotazione;
  }

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static StrutturaBean getStruttura(int id) {
    StrutturaBean struttura = new StrutturaBean();
    try {
      String sql = "SELECT * FROM MedQueue.Struttura WHERE Struttura.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        struttura.setId(Integer.parseInt(rs.getString(1)));
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

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static OperazioneBean getOperazione(int id) {
    OperazioneBean operazione = new OperazioneBean();
    try {
      String sql = "SELECT * FROM MedQueue.Operazione WHERE Operazione.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        operazione.setId(Integer.parseInt(rs.getString(1)));
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

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static ImpiegatoBean getImpiegato(String codicefiscale) {
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
        impiegato.setDataDiNascita(rs.getString(5));
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

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static boolean verificaDatiImpiegato(String cf, String password) {
    boolean verifica = false;
    try {
      String sql =
          "SELECT Impiegato.codiceFiscale, Impiegato.password "
                  + "FROM MedQueue.Impiegato "
                  + "WHERE Impiegato.codiceFiscale = ? AND Impiegato.password = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setString(1, cf);
      query.setString(2, password);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        verifica = true;
      }
      query.close();
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
    return verifica;
  }

  // Metodo per ottenere operazioni
  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static ArrayList<OperazioneBean> getOperazioni() {
    ArrayList<OperazioneBean> operazioni = new ArrayList<OperazioneBean>();
    try {
      String sql = "SELECT * FROM MedQueue.Operazione";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      ResultSet rs = query.executeQuery();
      while (rs.next()) {
        operazioni.add(
            new OperazioneBean(
                Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
      }
      query.close();
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }

    return operazioni;
  }

  @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
  public static void deletePrenotazione(int id) {
    try {
      String sql = "DELETE Prenotazione FROM MedQueue.Prenotazione WHERE Prenotazione.Id = ?";
      PreparedStatement query = DriverManagerConnectionPool.getConnection().prepareStatement(sql);
      query.setInt(1, id);
      query.executeUpdate();
      query.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("SQLException:" + e.getMessage());
    }
  }

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static int numPrenotazioni(int idOperazione, int idStruttura) {
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

  @SuppressFBWarnings({
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE",
    "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE"
  })
  public static PrenotazioneBean serviPrenotazione(int idOperazione, int idStruttura) {
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
        prenotazione.setData(rs.getString(2));
        prenotazione.setTime(rs.getString(3));
        prenotazione.setConvalida(Boolean.parseBoolean(rs.getString(4)));
        prenotazione.setCodiceFiscale(rs.getString(5));
        prenotazione.setIdOperazione(Integer.parseInt(rs.getString(6)));
        prenotazione.setIdStruttura(Integer.parseInt(rs.getString(7)));
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
