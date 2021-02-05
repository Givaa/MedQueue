package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.ImpiegatoBean;
import classes.model.interfaces.ImpiegatoDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Model per collegare la tabella "Impiegato" al backend.
 */
public class ImpiegatoModel implements ImpiegatoDaoInterface {

  private static final String nomeTabella = "impiegato";

  /**
   * Prelevamento singolo Impiegato.
   *
   * @param codFisc chiave primaria dell'impiegato
   * @return Impiegato avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public ImpiegatoBean doRetrieveByKey(String codFisc) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    ImpiegatoBean tmp = new ImpiegatoBean();

    String selectSql = "SELECT * FROM " + nomeTabella + " WHERE codiceFiscale = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);
      ps.setString(1, codFisc);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setPassword(rs.getString("password"));
        tmp.setNome(rs.getString("nome"));
        tmp.setCognome(rs.getString("cognome"));
        tmp.setDataDiNascita(rs.getDate("dataDiNascita"));
        tmp.setIndirizzoEmail(rs.getString("indirizzoEmail"));
        tmp.setNumeroDiTelefono(rs.getString("numeroDiTelefono"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
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
   * Prelevamento di tutti gli impiegati.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collection di impiegati
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public Collection<ImpiegatoBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<ImpiegatoBean> result = new LinkedList<ImpiegatoBean>();

    String selectSql = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSql += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSql);

      ResultSet rs = ps.executeQuery();
      ImpiegatoBean tmp = new ImpiegatoBean();

      while (rs.next()) {

        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setPassword(rs.getString("password"));
        tmp.setNome(rs.getString("nome"));
        tmp.setCognome(rs.getString("cognome"));
        tmp.setDataDiNascita(rs.getDate("dataDiNascita"));
        tmp.setIndirizzoEmail(rs.getString("indirizzoEmail"));
        tmp.setNumeroDiTelefono(rs.getString("numeroDiTelefono"));
        tmp.setIdStruttura(rs.getInt("idStruttura"));
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
   * Inserimento di un nuovo Impiegato.
   *
   * @param param Nuovo impiegato
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doSave(ImpiegatoBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, param.getCodiceFiscale());
      preparedStatement.setString(2, param.getPassword());
      preparedStatement.setString(3, param.getNome());
      preparedStatement.setString(4, param.getCognome());
      preparedStatement.setDate(5, param.getDataDiNascita());
      preparedStatement.setString(6, param.getIndirizzoEmail());
      preparedStatement.setString(7, param.getNumeroDiTelefono());
      preparedStatement.setInt(8, param.getIdStruttura());

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
   * Aggiornamento di un impiegato presente nel DB.
   *
   * @param param Impiegato da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doUpdate(ImpiegatoBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql =
        "UPDATE "
            + nomeTabella
            + " SET password = ?, nome = ?, cognome = ?, dataDiNascita = ?,"
            + " indirizzoEmail = ?, numeroDiTelefono = ?,"
            + "idStruttura = ?  WHERE codiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getPassword());
      preparedStatement.setString(2, param.getNome());
      preparedStatement.setString(3, param.getCognome());
      preparedStatement.setDate(4, param.getDataDiNascita());
      preparedStatement.setString(5, param.getIndirizzoEmail());
      preparedStatement.setString(6, param.getNumeroDiTelefono());
      preparedStatement.setInt(7, param.getIdStruttura());
      preparedStatement.setString(8, param.getCodiceFiscale());

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
   * Eliminazione di un impiegato presente nel DB.
   *
   * @param param Impiegato da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @Override
  public void doDelete(ImpiegatoBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM " + nomeTabella + " WHERE codiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, param.getCodiceFiscale());

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
}
