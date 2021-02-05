package classes.model.interfaces;

import classes.model.bean.entity.OperazioneBean;

import java.sql.SQLException;
import java.util.Collection;

public interface OperazioneDaoInterface {
    OperazioneBean doRetrieveByKey(int id) throws SQLException;
    Collection<OperazioneBean> doRetrieveAll(String order) throws SQLException;
    void doSave(OperazioneBean param) throws SQLException;
    void doUpdate(OperazioneBean param) throws SQLException;
    void doDelete(OperazioneBean param) throws SQLException;
}
