package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.PrenotazioneBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PrenotazioneModel implements Model<PrenotazioneBean> {
    private static String nomeTabella = "prenotazioni";

    @Override
    public PrenotazioneBean doRetrieveByKey(String id) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;

        PrenotazioneBean tmp = new PrenotazioneBean();

        String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
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
    public Collection<PrenotazioneBean> doRetrieveAll(String order) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();

        String selectSQL = "SELECT * FROM " + nomeTabella;

        if(order!=null && !order.equals("")){
            selectSQL += " ORDER BY " + order;
        }

        try {

            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                PrenotazioneBean tmp = new PrenotazioneBean();
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
                if (ps != null)
                    ps.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }

        return result;
    }

    @Override
    public void doSave(PrenotazioneBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, param.getOra());
            preparedStatement.setDate(2, param.getDataPrenotazione());
            preparedStatement.setString(3, param.getCodiceFiscale());
            preparedStatement.setInt(4, param.getIdOperazione());
            preparedStatement.setInt(5, param.getIdStruttura());
            preparedStatement.setBoolean(6, param.isConvalida());

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
    public void doUpdate(PrenotazioneBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "UPDATE " + nomeTabella + " SET ora = ?, dataPrenotazione = ?, codiceFiscale = ?, idOperazione = ?, idStruttura = ?, convalida = ?  WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
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
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return;
    }

    @Override
    public void doDelete(PrenotazioneBean param) throws SQLException {
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

    public Collection<PrenotazioneBean> getUtentePrenotazioni(String cf) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        Collection<PrenotazioneBean> result = new LinkedList<PrenotazioneBean>();

        PrenotazioneBean tmp = new PrenotazioneBean();

        String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE codiceFiscale = ?";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);
            ps.setString(1, cf);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
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
                if ( ps != null ) {
                    ps.close();
                }
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }

        return result;
    }
}