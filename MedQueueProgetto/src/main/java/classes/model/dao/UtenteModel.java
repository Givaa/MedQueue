package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.UtenteBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UtenteModel implements Model<UtenteBean> {
  private static final String nomeTabella = "utente";

  @Override
  public UtenteBean doRetrieveByKey(String code) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    UtenteBean tmp = new UtenteBean();

    String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSQL);
      ps.setString(1, code);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setPassword(rs.getString("password"));
        tmp.setNome(rs.getString("nome"));
        tmp.setCognome(rs.getString("cognome"));
        tmp.setDataDiNascita(rs.getDate("dataDiNascita"));
        tmp.setEmail(rs.getString("email"));
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

  @Override
  public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<UtenteBean> result = new LinkedList<UtenteBean>();

    String selectSQL = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSQL += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSQL);

      ResultSet rs = ps.executeQuery();
      UtenteBean tmp = new UtenteBean();

      while (rs.next()) {
        tmp.setCodiceFiscale(rs.getString("codiceFiscale"));
        tmp.setPassword(rs.getString("password"));
        tmp.setNome(rs.getString("nome"));
        tmp.setCognome(rs.getString("cognome"));
        tmp.setDataDiNascita(rs.getDate("dataDiNascita"));
        tmp.setEmail(rs.getString("email"));
        result.add(tmp);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) ps.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(con);
      }
    }

    return result;
  }

  @Override
  public void doSave(UtenteBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, param.getCodiceFiscale());
      preparedStatement.setString(2, param.getPassword());
      preparedStatement.setString(3, param.getNome());
      preparedStatement.setString(4, param.getCognome());
      preparedStatement.setDate(5, param.getDataDiNascita());
      preparedStatement.setString(6, param.getEmail());
      preparedStatement.setInt(7, param.getNumeroDiTelefono());

      preparedStatement.executeUpdate();
    } finally {
      try {
        if (preparedStatement != null) preparedStatement.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
  }

  @Override
  public void doUpdate(UtenteBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSQL =
        "UPDATE "
            + nomeTabella
            + " SET password = ?, nome = ?, cognome = ?, dataDiNascita = ?, email = ?, numeroDiTelefono = ?  WHERE codiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, param.getPassword());
      preparedStatement.setString(2, param.getNome());
      preparedStatement.setString(3, param.getCognome());
      preparedStatement.setDate(4, param.getDataDiNascita());
      preparedStatement.setString(5, param.getEmail());
      preparedStatement.setInt(6, param.getNumeroDiTelefono());
      preparedStatement.setString(7, param.getCodiceFiscale());

      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null) preparedStatement.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return;
  }

  @Override
  public void doDelete(UtenteBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSQL = "DELETE FROM " + nomeTabella + " WHERE codiceFiscale = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, param.getCodiceFiscale());

      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null) preparedStatement.close();
      } finally {
        DriverManagerConnectionPool.releaseConnection(connection);
      }
    }
    return;
  }
}
