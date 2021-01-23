package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.ImpiegatoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ImpiegatoModel implements Model<ImpiegatoBean> {

    private static final String nomeTabella = "Impiegato";

    @Override
    public ImpiegatoBean doRetrieveByKey(String codFisc) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        ImpiegatoBean tmp = new ImpiegatoBean();

        String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);
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
    public Collection<ImpiegatoBean> doRetrieveAll(String order) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        Collection<ImpiegatoBean> result = new LinkedList<ImpiegatoBean>();

        String selectSQL = "SELECT * FROM " + nomeTabella;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {

            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);

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
                result.add(tmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }

        return result;
    }

    @Override
    public void doSave(ImpiegatoBean param) throws SQLException {
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
            preparedStatement.setString(6, param.getIndirizzoEmail());
            preparedStatement.setString(7, param.getNumeroDiTelefono());

            preparedStatement.executeUpdate();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void doUpdate(ImpiegatoBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "UPDATE " + nomeTabella + " SET password = ?, nome = ?, cognome = ?, dataDiNascita = ?, indirizzoEmail = ?, numeroDiTelefono = ?  WHERE codiceFiscale = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, param.getPassword());
            preparedStatement.setString(2, param.getNome());
            preparedStatement.setString(3, param.getCognome());
            preparedStatement.setDate(4, param.getDataDiNascita());
            preparedStatement.setString(5, param.getIndirizzoEmail());
            preparedStatement.setString(6, param.getNumeroDiTelefono());
            preparedStatement.setString(7, param.getCodiceFiscale());

            preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return;
    }

    @Override
    public void doDelete(ImpiegatoBean param) throws SQLException {
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
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return;
    }
}
