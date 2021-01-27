package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.dao.OperazioneModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per controllare i metodi del Model di Operazione. */
@RestController
public class OperazioneController {

  private final OperazioneModel operazioneModel = new OperazioneModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'OperazioneModel.
   *
   * @param id Chiave primaria dell'operazione
   * @return Operazione avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/operazione/{id}")
  public OperazioneBean getOperazioneById(@RequestBody String id)
      throws SQLException, ObjectNotFoundException {
    OperazioneBean op = operazioneModel.doRetrieveByKey(id);
    if (op != null) {
      return op;
    } else {
      throw new ObjectNotFoundException(op);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'OperazioneModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Operazioni
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/operazioni")
  public Collection<OperazioneBean> getAllOperazioni(@RequestBody String order) throws SQLException {
    return operazioneModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova operazione tramite
   * OperazioneModel.
   *
   * @param o Operazione da inserire
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/newOperazione")
  public void newOperazione(@RequestBody OperazioneBean o) throws SQLException {
    operazioneModel.doSave(o);
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un operazione presente sul DB tramite
   * OperazioneModel.
   *
   * @param o Operazione da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteOperazione")
  public void deleteOperazione(@RequestBody OperazioneBean o) throws SQLException {
    operazioneModel.doDelete(o);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un operazione presente sul DB tramite
   * OperazioneModel.
   *
   * @param o Operazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/updateOperazione")
  void updateOperazione(@RequestBody OperazioneBean o) throws SQLException {
    operazioneModel.doUpdate(o);
  }
}
