package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.AmbulatoriBean;
import classes.model.dao.AmbulatoriModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per controllare i metodi del Model di Ambulatori. */
@RestController
public class AmbulatorioController {

  private final AmbulatoriModel ambulatorioModel = new AmbulatoriModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'AmbulatoriModel.
   *
   * @param id Chiave primaria dell'ambulatorio
   * @return Ambulatorio avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException problemi di oggetto non trovato
   */
  @GetMapping("/ambulatorio/{id}")
  public AmbulatoriBean getById(@PathVariable String id) throws SQLException, ObjectNotFoundException {
    AmbulatoriBean a = ambulatorioModel.doRetrieveByKey(id);

    if (a != null) {
      return a;
    } else {
      throw new ObjectNotFoundException(a);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'AmbulatoriModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Ambulatori
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/ambulatori")
  public Collection<AmbulatoriBean> getAll(@RequestBody String order) throws SQLException {
    return ambulatorioModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo ambulatorio tramite
   * AmbulatorioModel.
   *
   * @param a Ambulatorio da inserire
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/newAmbulatorio")
  public void newAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
    ambulatorioModel.doSave(a);
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un ambulatorio presente sul DB tramite
   * AmbulatorioModel.
   *
   * @param a Ambulatorio da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/removeAmbulatorio")
  public void deleteAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
    ambulatorioModel.doDelete(a);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un ambulatorio presente sul DB tramite
   * AmbulatorioModel.
   *
   * @param a Ambulatorio da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/updateAmbulatorio")
  public void updateAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
    ambulatorioModel.doUpdate(a);
  }
}
