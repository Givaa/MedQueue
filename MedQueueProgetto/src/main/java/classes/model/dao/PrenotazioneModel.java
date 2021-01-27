package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.PrenotazioneBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Model per collegare la tabella "Prenotazione" al backend.
 */
public class PrenotazioneModel implements Model<PrenotazioneBean> {
  private static final String nomeTabella = "prenotazione";

  /**
   * Prelevamento singola prenotazione.
   *
   * @param id chiave primaria della prenotazione
   * @return Prenotazione avente quell'id
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public PrenotazioneBean doRetrieveByKey(String id) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    PrenotazioneBean tmp = new PrenotazioneBean();

    String selectSql = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setString(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("struttura"));
        tmp.setIdOperazione(rs.getInt("operazione"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(con);
      }
    }
    return tmp;
  }

  /**
   * Prelevamento di tutte le prenotazione.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di tutte le prenotazioni
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<PrenotazioneBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();

    String selectSql = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSql += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);

      ResultSet rs = ps.executeQuery();
      PrenotazioneBean tmp = new PrenotazioneBean();

      while (rs.next()) {

        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("struttura"));
        tmp.setIdOperazione(rs.getInt("operazione"));
        result.add(tmp);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(con);
      }
    }

    return result;
  }

  /**
   * Inserimento di una nuova prenotazione nel DB.
   *
   * @param param Nuova prenotazione
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doSave(PrenotazioneBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, param.getOra());
      preparedStatement.setDate(2, param.getDataPrenotazione());
      preparedStatement.setString(3, param.getCodiceFiscale());
      preparedStatement.setInt(4, param.getIdOperazione());
      preparedStatement.setInt(5, param.getIdStruttura());
      preparedStatement.setBoolean(6, param.isConvalida());

      preparedStatement.executeUpdate();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }

  /**
   * Aggiornamento di una prenotazione presente nel DB.
   *
   * @param param Prenotazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doUpdate(PrenotazioneBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql =
        "UPDATE "
            + nomeTabella
            + " SET ora = ?, dataPrenotazione = ?, codiceFiscale = ?,"
            + " idOperazione = ?, idStruttura = ?, convalida = ?  WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getOra());
      preparedStatement.setDate(2, param.getDataPrenotazione());
      preparedStatement.setString(3, param.getCodiceFiscale());
      preparedStatement.setInt(4, param.getIdOperazione());
      preparedStatement.setInt(5, param.getIdStruttura());
      preparedStatement.setBoolean(6, param.isConvalida());
      preparedStatement.setInt(7, param.getId());

      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return;
  }

  /**
   * Rimozione di una prenotazione presente sul DB.
   *
   * @param param Prenotazione da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doDelete(PrenotazioneBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM " + nomeTabella + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, param.getId());

      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return;
  }

  /**
   * Metodo per prelevare tutte le prenotazioni di un utente.
   *
   * @param cf chiave primaria dell'utente di cui vogliamo prendere le prenotazioni
   * @return Collezione di prenotazioni dell'utente avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  public Collection<PrenotazioneBean> getUtentePrenotazioni(String cf) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();
    PrenotazioneBean tmp = new PrenotazioneBean();

    String selectSql = "SELECT * FROM " + nomeTabella + "WHERE codiceFiscale = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setString(1, cf);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("struttura"));
        tmp.setIdOperazione(rs.getInt("operazione"));
        result.add(tmp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(con);
      }
    }

    return result;
  }

  /**
   * Metodo per prelevare tutte le prenotazioni di una struttura.
   *
   * @param idStruttura chiave primaria della struttura di cui vogliamo la coda
   * @return Collezione che rappresenta la coda della struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  public Collection<PrenotazioneBean> getCodaStruttura(int idStruttura) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();
    PrenotazioneBean tmp = new PrenotazioneBean();

    String selectPrenotazioniSql = "SELECT * FROM " + nomeTabella
            + " WHERE idStruttura = ? ORDER BY ora";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectPrenotazioniSql);

      preparedStatement.setInt(1, idStruttura);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setOra(rs.getString("ora"));
        tmp.setDataPrenotazione(rs.getDate("data"));
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setConvalida(rs.getBoolean("convalida"));
        tmp.setIdStruttura(rs.getInt("struttura"));
        tmp.setIdOperazione(rs.getInt("operazione"));
        result.add(tmp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }

    return result;
  }
}
