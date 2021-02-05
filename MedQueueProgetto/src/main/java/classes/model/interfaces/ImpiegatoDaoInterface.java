package classes.model.interfaces;

import classes.model.bean.entity.ImpiegatoBean;

import java.sql.SQLException;
import java.util.Collection;

public interface ImpiegatoDaoInterface {
  ImpiegatoBean doRetrieveByKey(String codFisc) throws SQLException;
  Collection<ImpiegatoBean> doRetrieveAll(String order) throws SQLException;
  void doSave(ImpiegatoBean param) throws SQLException;
  void doUpdate(ImpiegatoBean param) throws SQLException;
  void doDelete(ImpiegatoBean param) throws SQLException;
}
