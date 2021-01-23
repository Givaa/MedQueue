package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.StrutturaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class StrutturaModel implements Model<StrutturaBean> {
  private static final String nomeTabella = "struttura";

  @Override
  public StrutturaBean doRetrieveByKey(String code) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    StrutturaBean tmp = new StrutturaBean();

    String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

    try {
      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSQL);
      ps.setString(1, code);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        tmp.setId(rs.getInt("id"));
        tmp.setNome(rs.getString("nome"));
        tmp.setIndirizzo(rs.getString("indirizzo"));
        tmp.setNumeroDiTelefono(rs.getString("numeroDiTelefono"));
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
  public Collection<StrutturaBean> doRetrieveAll(String order) throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;

    Collection<StrutturaBean> result = new LinkedList<StrutturaBean>();

    String selectSQL = "SELECT * FROM " + nomeTabella;

    if (order != null && !order.equals("")) {
      selectSQL += " ORDER BY " + order;
    }

    try {

      con = DriverManagerConnectionPool.getConnection();
      ps = con.prepareStatement(selectSQL);

      ResultSet rs = ps.executeQuery();
      StrutturaBean tmp = new StrutturaBean();

      while (rs.next()) {

        tmp.setId(rs.getInt("id"));
        tmp.setNome(rs.getString("nome"));
        tmp.setIndirizzo(rs.getString("indirizzo"));
        tmp.setNumeroDiTelefono(rs.getString("numeroDiTelefono"));
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
  public void doSave(StrutturaBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?)";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, param.getNome());
      preparedStatement.setString(2, param.getIndirizzo());
      preparedStatement.setString(3, param.getNumeroDiTelefono());

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
  public void doUpdate(StrutturaBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSQL =
        "UPDATE " + nomeTabella + " SET nome = ?, indirizzo = ?, numeroDiTelefono = ? WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, param.getNome());
      preparedStatement.setString(2, param.getIndirizzo());
      preparedStatement.setString(3, param.getNumeroDiTelefono());
      preparedStatement.setInt(4, param.getId());

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
  public void doDelete(StrutturaBean param) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSQL = "DELETE FROM " + nomeTabella + " WHERE id = ?";

    try {
      connection = DriverManagerConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setInt(1, param.getId());

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
