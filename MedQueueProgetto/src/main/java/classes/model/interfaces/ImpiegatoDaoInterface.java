package classes.model.interfaces;

import classes.model.bean.entity.ImpiegatoBean;
import java.sql.SQLException;
import java.util.Collection;

/** Interfaccia per il Model degli Impiegati. */
public interface ImpiegatoDaoInterface {

  /**
   * Prelevamento singolo Impiegato.
   *
   * @param codFisc chiave primaria dell'impiegato
   * @return Impiegato avente quel codice fiscale
   * @throws SQLException per problemi di esecuzione della query
   */
  ImpiegatoBean doRetrieveByKey(String codFisc) throws SQLException;

  /**
   * Prelevamento di tutti gli impiegati.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collection di impiegati
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<ImpiegatoBean> doRetrieveAll(String order) throws SQLException;

  /**
   * Inserimento di un nuovo Impiegato.
   *
   * @param param Nuovo impiegato
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(ImpiegatoBean param) throws SQLException;

  /**
   * Aggiornamento di un impiegato presente nel DB.
   *
   * @param param Impiegato da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(ImpiegatoBean param) throws SQLException;

  /**
   * Eliminazione di un impiegato presente nel DB.
   *
   * @param param Impiegato da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(ImpiegatoBean param) throws SQLException;
}
