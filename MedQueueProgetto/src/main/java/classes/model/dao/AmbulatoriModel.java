package classes.model.dao;

import classes.model.DriverManagerConnectionPool;
import classes.model.bean.entity.AmbulatoriBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class AmbulatoriModel implements Model<AmbulatoriBean>{

    private static final String nomeTabella = "ambulatori";

    @Override
    public AmbulatoriBean doRetrieveByKey(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        AmbulatoriBean tmp = new AmbulatoriBean();

        String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
                tmp.setId(rs.getInt("id"));
                tmp.setNome(rs.getString("nome"));
                tmp.setIdStruttura(rs.getInt("idStruttura"));
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
    public Collection<AmbulatoriBean> doRetrieveAll(String order) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        Collection<AmbulatoriBean> result = new LinkedList<AmbulatoriBean>();

        String selectSQL = "SELECT * FROM " + nomeTabella;

        if(order!=null && !order.equals("")){
            selectSQL += " ORDER BY " + order;
        }

        try {

            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(selectSQL);

            ResultSet rs = ps.executeQuery();
            AmbulatoriBean tmp = new AmbulatoriBean();

            while(rs.next()){

                tmp.setId(rs.getInt("id"));
                tmp.setNome(rs.getString("nome"));
                tmp.setIdStruttura(rs.getInt("idStruttura"));
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
    public void doSave(AmbulatoriBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + nomeTabella + " VALUES (?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, param.getId());
            preparedStatement.setString(2, param.getNome());
            preparedStatement.setInt(3, param.getIdStruttura());

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
    public void doUpdate(AmbulatoriBean param) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "UPDATE " + nomeTabella + " SET nome = ?, idStruttura = ? WHERE id = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, param.getNome());
            preparedStatement.setInt(2, param.getIdStruttura());
            preparedStatement.setInt(3, param.getId());

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
    public void doDelete(AmbulatoriBean param) throws SQLException {
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
