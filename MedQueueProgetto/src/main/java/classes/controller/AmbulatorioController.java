package classes.controller;


import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.AmbulatoriBean;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.AmbulatoriModel;
import classes.model.dao.StrutturaModel;
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
  private final StrutturaModel strutturaModel = new StrutturaModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'AmbulatoriModel.
   *
   * @param id Chiave primaria dell'ambulatorio
   * @return Ambulatorio avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException problemi di oggetto non trovato
   */
  @GetMapping("/ambulatorio/{id}")
  public AmbulatoriBean getById(@PathVariable String id) throws SQLException,
          ObjectNotFoundException {
    int idNumerico = Integer.parseInt(id);

    if (idNumerico > 0) {
      AmbulatoriBean a = ambulatorioModel.doRetrieveByKey(id);

      if (a != null) {
        return a;
      } else {
        throw new ObjectNotFoundException(a);
      }
    }

    return null;
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'AmbulatoriModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Ambulatori
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/ambulatori")
  public Collection<AmbulatoriBean> getAllAmbulatori(@RequestBody String order)
          throws SQLException {
    return ambulatorioModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo ambulatorio tramite
   * AmbulatorioModel.
   *
   * @param a Ambulatorio da inserire
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi nell'input
   * @return conferma/non conferma del salvataggio dell'ambulatorio
   */
  @GetMapping("/newAmbulatorio")
  public boolean newAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException,
          ErrorNewObjectException {
    if (a != null) {
      StrutturaBean strutturaBean;
      strutturaBean = strutturaModel.doRetrieveByKey(String.valueOf(a.getIdStruttura()));
      String nome = a.getNome();

      boolean checkNome = nome.matches("^[a-z ,.'-]+$");
      boolean checkIdStruttura = strutturaBean != null;

      if (checkNome && checkIdStruttura) {
        ambulatorioModel.doSave(a);
      } else {
        return false;
      }
    } else {
      throw new ErrorNewObjectException(a);
    }

    return true;
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
   * @return conferma/non conferma dell'aggiornamento dell'ambulatorio
   */
  @GetMapping("/updateAmbulatorio")
  public boolean updateAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
    if (ambulatorioModel.doRetrieveByKey(String.valueOf(a.getId())) != null) {
      StrutturaBean strutturaBean;
      strutturaBean = strutturaModel.doRetrieveByKey(String.valueOf(a.getIdStruttura()));
      String nome = a.getNome();

      Boolean checkNome = nome.matches("^[a-z ,.'-]+$");
      Boolean checkIdStruttura = strutturaBean != null;

      if (checkNome && checkIdStruttura) {
        ambulatorioModel.doUpdate(a);
        return true;
      }

      return false;
    }

    return false;
  }
}
