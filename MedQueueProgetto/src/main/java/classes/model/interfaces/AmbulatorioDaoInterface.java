package classes.model.interfaces;

import classes.model.bean.entity.AmbulatoriBean;

import java.sql.SQLException;
import java.util.Collection;

public interface AmbulatorioDaoInterface {
    AmbulatoriBean doRetrieveByKey(int id) throws SQLException;
    Collection<AmbulatoriBean> doRetrieveAll(String order) throws SQLException;
    void doSave(AmbulatoriBean param) throws SQLException;
    void doUpdate(AmbulatoriBean param) throws SQLException;
    void doDelete(AmbulatoriBean param) throws SQLException;
}
