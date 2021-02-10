package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.ImpiegatoBean;
import classes.model.dao.ImpiegatoModel;
import classes.model.dao.StrutturaModel;
import classes.model.interfaces.ImpiegatoDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per poter controllare i metodi di ImpiegatoModel. */
@RestController
public class ImpiegatoController {

  private final ImpiegatoDaoInterface impiegatoModel = new ImpiegatoModel();
  private final StrutturaModel strutturaModel = new StrutturaModel();

  /**
   * Metodo che permette di utilizzare il prelevamento tramite CF dell'ImpiegatoModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Impiegato avente il CF passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @PostMapping(
      value = "/impiegato/{cf}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ImpiegatoBean getImpiegatoByCodFis(@RequestBody String body)
      throws SQLException, ObjectNotFoundException {

    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("cfImpiegatoRicerca").getAsString();

    Boolean checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");

    if (checkCodFisc) {
      ImpiegatoBean b = impiegatoModel.doRetrieveByKey(cf);

      if (b != null) {
        return b;
      } else {
        throw new ObjectNotFoundException(b);
      }
    } else {
      return null;
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti dell'ImpiegatoModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Impiegati
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(
      value = "/impiegati",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<ImpiegatoBean> getAllImpiegati(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordineImpiegati").getAsString();
    return impiegatoModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo impiegato tramite ImpiegatoModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi nell'input
   * @throws ParseException per problemi di parse
   * @return conferma/non conferma del salvataggio dell'impiegato
   */
  @PostMapping(
      value = "/newImpiegato",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newImpiegato(@RequestBody String body)
      throws SQLException, ErrorNewObjectException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String dataN = jsonObject.get("newImpiegatoDataN").getAsString();
    Date dataNascita = (Date) new SimpleDateFormat("yyyy/mm/gg").parse(dataN);

    String nome = jsonObject.get("newImpiegatoNome").getAsString();
    Boolean checkName;
    checkName = nome.matches("[A-Za-z]+$");

    String email = jsonObject.get("newImpiegatoEmail").getAsString();
    Boolean checkMail;
    checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

    String cognome = jsonObject.get("newImpiegatoCognome").getAsString();
    Boolean checkSurname;
    checkSurname = cognome.matches("[A-Za-z]+$");

    String password = jsonObject.get("newImpiegatoPassword").getAsString();
    Boolean checkPassword;
    checkPassword =
            password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");

    String phoneNumber = jsonObject.get("newImpiegatoNumero").getAsString();
    Boolean checkPhoneNumber;
    checkPhoneNumber = phoneNumber.matches("^[\\+][0-9]{10,12}");

    String codFisc = jsonObject.get("newImpiegatoCf").getAsString();
    Boolean checkCodFisc;
    checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");

    String idStruttura = jsonObject.get("newImpiegatoIdStruttura").getAsString();
    Boolean checkIdStruttura;
    checkIdStruttura = strutturaModel.doRetrieveByKey(Integer.valueOf(idStruttura)) != null;

    if (checkName
        && checkSurname
        && checkPassword
        && checkPhoneNumber
        && checkCodFisc
        && checkMail
        && checkIdStruttura) {
      impiegatoModel.doSave(
          new ImpiegatoBean(
              codFisc,
              password,
              nome,
              cognome,
              dataNascita,
              email,
              phoneNumber,
              Integer.valueOf(idStruttura)));
      return true;
    } else {
      throw new ErrorNewObjectException(new ImpiegatoBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un impiegato presente sul DB tramite
   * ImpiegatoModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(
      value = "/deleteImpiegato",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteImpiegato(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deleteImpiegatoId").getAsString();
    impiegatoModel.doDelete(impiegatoModel.doRetrieveByKey(id));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un impiegato presente sul DB tramite
   * ImpiegatoModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   * @return conferma/non conferma dell'aggiornamento dell'impiegato
   */
  @PostMapping(
      value = "/updateImpiegato",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updateImpiegato(@RequestBody String body) throws SQLException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String codFisc = jsonObject.get("updateImpiegatoCf").getAsString();
    String cognome = jsonObject.get("updateImpiegatoCognome").getAsString();
    String nome = jsonObject.get("updateImpiegatoNome").getAsString();
    String password = jsonObject.get("updateImpiegatoPassword").getAsString();
    String email = jsonObject.get("updateImpiegatoEmail").getAsString();
    String phoneNumber = jsonObject.get("updateImpiegatoPhoneNumber").getAsString();
    String idStruttura = jsonObject.get("updateImpiegatoIdS").getAsString();
    String dataN = jsonObject.get("updateImpiegatoDataN").getAsString();
    Date dataNascita = (Date) new SimpleDateFormat("yyyy/mm/gg").parse(dataN);

    ImpiegatoBean i = impiegatoModel.doRetrieveByKey(codFisc);

    if (i != null) {

      Boolean checkMail;
      checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

      Boolean checkName;
      checkName = nome.matches("[A-Za-z]+$");

      Boolean checkSurname;
      checkSurname = cognome.matches("[A-Za-z]+$");

      Boolean checkPassword;
      checkPassword =
              password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");

      Boolean checkPhoneNumber;
      checkPhoneNumber = phoneNumber.matches("^[\\+][0-9]{10,12}");

      Boolean checkCodFisc;
      checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");

      Boolean checkStruttura;
      checkStruttura = strutturaModel.doRetrieveByKey(Integer.valueOf(idStruttura)) != null;


      if (checkName
          && checkSurname
          && checkPassword
          && checkPhoneNumber
          && checkCodFisc
          && checkMail
          && checkStruttura) {
        i.setNome(nome);
        i.setIdStruttura(Integer.valueOf(idStruttura));
        i.setNumeroDiTelefono(phoneNumber);
        i.setCognome(cognome);
        i.setPassword(password);
        i.setDataDiNascita(dataNascita);
        i.setIndirizzoEmail(email);

        impiegatoModel.doUpdate(i);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ObjectNotFoundException(new ImpiegatoBean());
    }
  }
}
