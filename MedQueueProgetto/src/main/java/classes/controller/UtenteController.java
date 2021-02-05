package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** Classe per controllare i metodi del Model di Utente. */
@RestController
public class UtenteController {
  private final UtenteModel utenteModel = new UtenteModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id dell'UtenteModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Utente avente il codice fiscale passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @PostMapping(value="/utente/{cf}", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public UtenteBean getUtenteByCodFisc(@RequestBody String body)
      throws SQLException, ObjectNotFoundException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("idUtenteGet").getAsString();
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
   * @param body corpo della richiesta preso in input
   * @return Collezione di Utenti
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value="/utenti", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<UtenteBean> getAllUtenti(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordineUtenti").getAsString();
    return utenteModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo utente tramite UtenteController.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi di creazione oggetti
   * @throws ParseException per problemi di parsing
   * @return conferma/non conferma del salvataggio dell'utente
   */
  @PostMapping(value="/newUtente", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newUtente(@RequestBody String body) throws SQLException,
          ErrorNewObjectException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("newUtenteCf").getAsString();
    UtenteBean u = utenteModel.doRetrieveByKey(cf);
    String password = jsonObject.get("newUtentePassword").getAsString();
    String cognome = jsonObject.get("newUtenteCognome").getAsString();
    String nome = jsonObject.get("newUtenteNome").getAsString();
    String phoneNumber = jsonObject.get("newUtentePhoneNumber").getAsString();
    String email = jsonObject.get("newUtenteEmail").getAsString();
    String dataN = jsonObject.get("newUtenteDataN").getAsString();
    Date dataNascita = (Date) new SimpleDateFormat("yyyy/mm/gg").parse(dataN);

    Boolean checkMail;
    Boolean checkName;
    Boolean checkSurname;
    Boolean checkPassword;
    Boolean checkPhoneNumber;
    Boolean checkCodFisc;

    checkName = nome.matches("[A-Za-z]+$");
    checkSurname = cognome.matches("[A-Za-z]+$");
    checkPassword =
            password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");
    checkPhoneNumber = phoneNumber.matches("^[\\+][0-9]{10,12}");
    checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
    checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

    if (checkName && checkSurname && checkPassword
            && checkPhoneNumber && checkCodFisc && checkMail) {
      u.setNumeroDiTelefono(phoneNumber);
      u.setPassword(password);
      u.setEmail(email);
      u.setCognome(cognome);
      u.setNome(nome);
      u.setDataDiNascita(dataNascita);
      u.setCodiceFiscale(cf);
      utenteModel.doSave(u);
      return true;
    } else {
      throw new ErrorNewObjectException(new UtenteBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value="/deleteUtente", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deleteUtente(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deleteUtenteId").getAsString();
    utenteModel.doDelete(utenteModel.doRetrieveByKey(id));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parsing
   * @return conferma/non conferma dell'aggiornamento dell'utente
   */
  @PostMapping(value="/updateUtente", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updateUtente(@RequestBody String body) throws SQLException,
          ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("updateUtenteCf").getAsString();
    UtenteBean u = utenteModel.doRetrieveByKey(cf);

    if (u != null) {
      String password = jsonObject.get("updateUtentePassword").getAsString();
      String cognome = jsonObject.get("updateUtenteCognome").getAsString();
      String nome = jsonObject.get("updateUtenteNome").getAsString();
      String phoneNumber = jsonObject.get("updateUtentePhoneNumber").getAsString();
      String email = jsonObject.get("updateUtenteEmail").getAsString();
      String dataN = jsonObject.get("updateUtenteDataN").getAsString();
      Date dataNascita = (Date) new SimpleDateFormat("yyyy/mm/gg").parse(dataN);

      Boolean checkMail;
      Boolean checkName;
      Boolean checkSurname;
      Boolean checkPassword;
      Boolean checkPhoneNumber;
      Boolean checkCodFisc;

      checkName = nome.matches("[A-Za-z]+$");
      checkSurname = cognome.matches("[A-Za-z]+$");
      checkPassword =
          password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");
      checkPhoneNumber = phoneNumber.matches("^[\\+][0-9]{10,12}");
      checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

      if (checkName && checkSurname && checkPassword
          && checkPhoneNumber && checkCodFisc && checkMail) {
        u.setPassword(password);
        u.setNome(nome);
        u.setNumeroDiTelefono(phoneNumber);
        u.setDataDiNascita(dataNascita);
        u.setEmail(email);
        utenteModel.doUpdate(u);
        return true;
      } else {
        return false;
      }
    }
    return false;
  }
}
