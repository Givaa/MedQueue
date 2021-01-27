package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe per controllare i metodi del Model di Utente. */
@RestController
public class UtenteController {
  private final UtenteModel utenteModel = new UtenteModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'UtenteModel.
   *
   * @param cf Chiave primaria dell'Utente
   * @return Utente avente il codice fiscale passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/utente/{cf}")
  UtenteBean getUtenteByCodFisc(@PathVariable String cf)
      throws SQLException, ObjectNotFoundException {
    UtenteBean s = utenteModel.doRetrieveByKey(cf);
    if (s != null) {
      return s;
    } else {
      throw new ObjectNotFoundException(s);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'UtenteModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Utenti
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/utenti")
  Collection<UtenteBean> getAllUtenti(@RequestBody String order) throws SQLException {
    return utenteModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo utente tramite UtenteController.
   *
   * @param u Utente da inserire
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/newUtente")
  void newUtente(@RequestBody UtenteBean u) throws SQLException {
    utenteModel.doSave(u);
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param u Utente da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteUtente")
  void deleteUtente(@RequestBody UtenteBean u) throws SQLException {
    utenteModel.doDelete(u);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param u Utente da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/updateUtente")
  void updateUtente(@RequestBody UtenteBean u) throws SQLException {
    utenteModel.doUpdate(u);
  }
}
