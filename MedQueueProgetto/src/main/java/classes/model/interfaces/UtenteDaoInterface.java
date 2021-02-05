package classes.model.interfaces;

import classes.model.bean.entity.UtenteBean;

import java.sql.SQLException;
import java.util.Collection;

public interface UtenteDaoInterface {
    UtenteBean doRetrieveByKey(String cf) throws SQLException;
    Collection<UtenteBean> doRetrieveAll(String order) throws SQLException;
    void doSave(UtenteBean param) throws SQLException;
    void doUpdate(UtenteBean param) throws SQLException;
    void doDelete(UtenteBean param) throws SQLException;
}
