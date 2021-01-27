import com.classes.model.bean.PrenotazioneBean;
import com.classes.model.DataAccess;

public class PrenotazioneModel implements Model<PrenotazioneBean> {
    private static String nomeTabella = "prenotazioni";

    @Override
    public PrenotazioneBean doRetriveByKey(int id) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;

        PrenotazioneBean tmp = new PrenotazioneBean();

        String selectSQL = "SELECT * FROM " + nomeTabella + "WHERE id = ?";

        try {
            con = DataAccess.getConnection();
            ps = con.preparedStatement(selectSQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
                tmp.setOra(rs.getString("ora"));
                tmp.setData(rs.getDate("data"));
                tmp.setStruttura(rs.getString("struttura"));
                tmp.setOperazione(rs.getString("operazione"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( ps != null ) {
                    ps.close();
                } finally {
                    DataAccess.disconnect(con);
                }
            }
        }

        return tmp;
    }

}