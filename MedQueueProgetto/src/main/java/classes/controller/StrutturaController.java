package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaModel;
import java.sql.SQLException;
import java.util.Collection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
   * @param body corpo della richiesta preso in input
   * @return Struttura avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/struttura/{id}")
  public StrutturaBean getStrutturaById(@RequestBody String body)
      throws SQLException, ObjectNotFoundException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idStrutturaGet").getAsString();
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
   * @param body corpo della richiesta preso in input
   * @return Collezione di Strutture
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/strutture")
  public Collection<StrutturaBean> getAllStrutture(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordineStrutture").getAsString();
    return strutturaModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova struttura tramite StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma del salvataggio della struttura
   */
  @GetMapping("/newStruttura")
  public boolean newStruttura(@RequestBody String body) throws SQLException,
          ErrorNewObjectException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String nome = jsonObject.get("newStrutturaNome").getAsString();
    String indirizzo = jsonObject.get("newStrutturaIndirizzo").getAsString();
    String numeroCell = jsonObject.get("newStrutturaNumeroCell").getAsString();

    boolean checkNome = nome.matches("[A-Za-z]+$");
    boolean checkIndirizzo = indirizzo.matches("^[A-Za-z0-9\\-\\s,\\/]*$");
    boolean checkNumeroCell = numeroCell.matches("^[\\+][0-9]{10,12}");

    if (checkIndirizzo && checkNome && checkNumeroCell) {
      strutturaModel.doSave(new StrutturaBean(nome, indirizzo, numeroCell));
      return true;
    } else {
      throw new ErrorNewObjectException(new StrutturaBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una struttura presente sul DB tramite
   * StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteStruttura")
  public void deleteStruttura(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deleteStrutturaId").getAsString();
    strutturaModel.doDelete(strutturaModel.doRetrieveByKey(id));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una struttura presente sul DB tramite
   * StrutturaModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma della modifica della struttura
   */
  @GetMapping("/updateStruttura")
  public boolean updateStruttura(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("updateStrutturaId").getAsString();
    StrutturaBean s = strutturaModel.doRetrieveByKey(id);
    String nome = jsonObject.get("updateStrutturaNome").getAsString();
    String indirizzo = jsonObject.get("updateStrutturaInd").getAsString();
    String numeroCell = jsonObject.get("updateStrutturaNumeroCell").getAsString();

    if (s != null) {
      boolean checkNome = nome.matches("[A-Za-z]+$");
      boolean checkIndirizzo = indirizzo.matches("^[A-Za-z0-9\\-\\s,\\/]*$");
      boolean checkNumeroCell = numeroCell.matches("^[\\+][0-9]{10,12}");

      if (checkIndirizzo && checkNome && checkNumeroCell) {
        s.setNumeroDiTelefono(numeroCell);
        s.setNome(nome);
        s.setIndirizzo(indirizzo);
        strutturaModel.doUpdate(s);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ObjectNotFoundException(s);
    }
  }
}
