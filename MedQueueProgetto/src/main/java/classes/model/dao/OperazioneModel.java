package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.OperazioneBean;
import classes.model.bean.entity.PrenotazioneBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class OperazioneModel implements Model<OperazioneBean> {

    private static final String nomeTabella = "operazione";

    @Override
    public OperazioneBean doRetrieveByKey(String code) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        OperazioneBean tmp = new OperazioneBean();

        String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);
            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
                tmp.setId(rs.getInt("id"));
                tmp.setTipoOperazione(rs.getString("tipoOperazione"));
                tmp.setDescrizione(rs.getString("descrizione"));
                tmp.setIdPrenotazione(rs.getInt("idPrenotazione"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( ps != null ) {
                    ps.close();
                }
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
        return tmp;
    }

    @Override
    public Collection<OperazioneBean> doRetrieveAll(String order) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        Collection<OperazioneBean> result = new LinkedList<OperazioneBean>();

        String selectSQL = "SELECT * FROM " + nomeTabella;

        if(order!=null && !order.equals("")){
            selectSQL += " ORDER BY " + order;
        }

        try {

            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                OperazioneBean tmp = new OperazioneBean();
                tmp.setId(rs.getInt("id"));
                tmp.setTipoOperazione(rs.getString("tipoOperazione"));
                tmp.setDescrizione(rs.getString("descrizione"));
                tmp.setIdPrenotazione(rs.getInt("idPrenotazione"));
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
    public void doSave(OperazioneBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, param.getTipoOperazione());
            preparedStatement.setString(2, param.getDescrizione());
            preparedStatement.setInt(3, param.getIdPrenotazione());

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
    public void doUpdate(OperazioneBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "UPDATE " + nomeTabella + " SET tipoOperazione = ?, descrizione = ?, idPrenotazione = ? WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, param.getTipoOperazione());
            preparedStatement.setString(2, param.getDescrizione());
            preparedStatement.setInt(3, param.getIdPrenotazione());
            preparedStatement.setInt(4, param.getId());

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
    public void doDelete(OperazioneBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM " + nomeTabella + " WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, param.getId() );

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
