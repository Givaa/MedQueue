package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.bind.annotation.*;

/** Classe che ci permette di effettuare il login al sito. */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LogInController extends HttpServlet {

  private final UtenteModel um = new UtenteModel();

  /**
   * Metodo che controlla le credenziali inviate.
   *
   * @param username Codice Fiscale di chi si vuole loggare
   * @param password Password dell'iscritto
   * @return Permesso/Non permesso di accesso
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping ("/login")
  public UtenteBean login(@RequestBody String username,
                       @RequestBody String password) throws SQLException {
    UtenteBean a = um.doRetrieveByKey(username);

    if(password.equals(a.getPassword())){
    return a;
    }else{
     return null;
    }
  }

  /**
   * Metodo che permette di registrarsi al sito.
   *
   * @param codFisc Chiave primaria dell'utente
   * @param password Password dell'utente
   * @param nome Nome dell'utente
   * @param cognome cognome dell'utente
   * @param dataDiNascita data di nascita dell'utente
   * @param email email dell'utente
   * @param numeroDiTelefono numero di telefono dell'utente
   * @return conferma o meno della registrazione
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/signup")
  public UtenteBean signup(@RequestBody String codFisc, @RequestBody String password,
                        @RequestBody String nome, @RequestBody String cognome,
                        @RequestBody Date dataDiNascita, @RequestBody String email,
                        @RequestBody int numeroDiTelefono) throws SQLException {

    UtenteBean a = new UtenteBean();
    String phoneNumber = String.valueOf(numeroDiTelefono);
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
      a = new UtenteBean(codFisc, password, nome, cognome,
              dataDiNascita, email, numeroDiTelefono);
      um.doSave(a);

      return a;
    } else {
      throw new ErrorNewObjectException(a);
    }

  }
}
