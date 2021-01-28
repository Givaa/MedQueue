package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
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
  public UtenteBean getUtenteByCodFisc(@PathVariable String cf)
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
  public Collection<UtenteBean> getAllUtenti(@RequestBody String order) throws SQLException {

    return utenteModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo utente tramite UtenteController.
   *
   * @param u Utente da inserire
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma del salvataggio dell'utente
   */
  @GetMapping("/newUtente")
  public boolean newUtente(@RequestBody UtenteBean u) throws SQLException,
          ErrorNewObjectException {
    if (u != null) {
      String password = u.getPassword();
      String cognome = u.getCognome();
      String codFisc = u.getCodiceFiscale();
      String nome = u.getNome();
      String phoneNumber = String.valueOf(u.getNumeroDiTelefono());
      String email = u.getEmail();

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
      checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

      if (checkName && checkSurname && checkPassword
              && checkPhoneNumber && checkCodFisc && checkMail) {
        utenteModel.doSave(u);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ErrorNewObjectException(u);
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param u Utente da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteUtente")
  public void deleteUtente(@RequestBody UtenteBean u) throws SQLException {
    utenteModel.doDelete(u);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un ambulatorio presente sul DB tramite
   * UtenteController.
   *
   * @param u Utente da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma dell'aggiornamento dell'utente
   */
  @GetMapping("/updateUtente")
  public boolean updateUtente(@RequestBody UtenteBean u) throws SQLException {
    if (u != null) {
      String password = u.getPassword();
      String cognome = u.getCognome();
      String codFisc = u.getCodiceFiscale();
      String nome = u.getNome();
      String phoneNumber = String.valueOf(u.getNumeroDiTelefono());
      String email = u.getEmail();

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
      checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

      if (checkName && checkSurname && checkPassword
          && checkPhoneNumber && checkCodFisc && checkMail) {
        utenteModel.doUpdate(u);
        return true;
      } else {
        return false;
      }
    }
    return false;
  }
}
