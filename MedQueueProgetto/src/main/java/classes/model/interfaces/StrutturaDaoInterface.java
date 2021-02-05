package classes.model.interfaces;

import classes.model.bean.entity.StrutturaBean;

import java.sql.SQLException;
import java.util.Collection;

public interface StrutturaDaoInterface {
    StrutturaBean doRetrieveByKey(int id) throws SQLException;
    Collection<StrutturaBean> doRetrieveAll(String order) throws SQLException;
    void doSave(StrutturaBean param) throws SQLException;
    void doUpdate(StrutturaBean param) throws SQLException;
    void doDelete(StrutturaBean param) throws SQLException;
}
