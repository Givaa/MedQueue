package classes.model.dao;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Interfaccia per i vari model.
 *
 * @param <T> Oggetto su cui modellare l'interfaccia
 */
public interface Model<T> {

  /**
   * Prelevamento tramite chiave primaria.
   *
   * @param code chiave primaria dell'oggetto
   * @return oggetto avente la chiave primaria indicata
   * @throws SQLException per problemi di esecuzione della query
   */
  T doRetrieveByKey(String code) throws SQLException;

  /**
   * Prelevamento di tutti gli oggetti T dal DB.
   *
   * @param order Ordine per la visualizzazione della collezione
   * @return Collezione di oggetti T
   * @throws SQLException per problemi di esecuzione della query
   */
  Collection<T> doRetrieveAll(String order) throws SQLException;

  /**
   * Aggiunta di un oggetto T al DB.
   *
   * @param param Nuovo Oggetto T
   * @throws SQLException per problemi di esecuzione della query
   */
  void doSave(T param) throws SQLException;

  /**
   * Aggiornamento di un oggetto T presente sul DB.
   *
   * @param param Oggetto T da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  void doUpdate(T param) throws SQLException;

  /**
   * Rimozione di un oggetto T presente sul DB.
   *
   * @param param Oggetto T da rimuovere
   * @throws SQLException per problemi di esecuzione della query
   */
  void doDelete(T param) throws SQLException;
}
