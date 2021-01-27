package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.ImpiegatoBean;
import classes.model.dao.ImpiegatoModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per poter controllare i metodi di ImpiegatoModel. */
@RestController
public class ImpiegatoController {

  private final ImpiegatoModel impiegatoModel = new ImpiegatoModel();

  /**
   * Metodo che permette di utilizzare il prelevamento tramite CF dell'ImpiegatoModel.
   *
   * @param cf Chiave primaria dell'impiegato
   * @return Impiegato avente il CF passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/impiegato/{cf}")
  ImpiegatoBean getImpiegatoByCodFis(@PathVariable String cf)
      throws SQLException, ObjectNotFoundException {
    ImpiegatoBean b = impiegatoModel.doRetrieveByKey(cf);

    if (b != null) {
      return b;
    } else {
      throw new ObjectNotFoundException(b);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'ImpiegatoModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Impiegati
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/impiegati")
  Collection<ImpiegatoBean> getAllImpiegati(@RequestBody String order) throws SQLException {
    return impiegatoModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo impiegato tramite ImpiegatoModel.
   *
   * @param i Impiegato da inserire
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/newImpiegato")
  void newImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
    impiegatoModel.doSave(i);
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un impiegato presente sul DB tramite
   * ImpiegatoModel.
   *
   * @param i Impiegato da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteImpiegato")
  void deleteImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
    impiegatoModel.doDelete(i);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un impiegato presente sul DB tramite
   * ImpiegatoModel.
   *
   * @param i Impiegato da inserire
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/updateImpiegato")
  void updateImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
    impiegatoModel.doUpdate(i);
  }
}
