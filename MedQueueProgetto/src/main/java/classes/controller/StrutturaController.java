package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe per controllare i metodi del Model di Struttura. */
@RestController
public class StrutturaController {
  private final StrutturaModel strutturaModel = new StrutturaModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dello StrutturaModel.
   *
   * @param id Chiave primaria della Struttura
   * @return Struttura avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/struttura/{id}")
  public StrutturaBean getStrutturaById(@PathVariable String id)
      throws SQLException, ObjectNotFoundException {
    StrutturaBean s = strutturaModel.doRetrieveByKey(id);
    if (s != null) {
      return s;
    } else {
      throw new ObjectNotFoundException(s);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dello StrutturaModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Strutture
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/strutture")
  public Collection<StrutturaBean> getAllStrutture(@RequestBody String order) throws SQLException {
    return strutturaModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova struttura tramite StrutturaModel.
   *
   * @param s Struttura da inserire
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/newStruttura")
  public void newStruttura(@RequestBody StrutturaBean s) throws SQLException {
    strutturaModel.doSave(s);
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una struttura presente sul DB tramite
   * StrutturaModel.
   *
   * @param s Struttura da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteStruttura")
  public void deleteStruttura(@RequestBody StrutturaBean s) throws SQLException {
    strutturaModel.doDelete(s);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una struttura presente sul DB tramite
   * StrutturaModel.
   *
   * @param s Struttura da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/updateStruttura")
  public void updateStruttura(@RequestBody StrutturaBean s) throws SQLException {
    strutturaModel.doUpdate(s);
  }
}
