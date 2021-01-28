package classes.controller;

import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe che ci permette di effettuare il login al sito. */
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
  @GetMapping("/login")
  public boolean login(@RequestBody String username,
                       @RequestBody String password) throws SQLException {
    UtenteBean a = um.doRetrieveByKey(username);

    return a.getPassword().equals(password);
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
  public boolean signup(@RequestBody String codFisc, @RequestBody String password,
                        @RequestBody String nome, @RequestBody String cognome,
                        @RequestBody Date dataDiNascita, @RequestBody String email,
                        @RequestBody int numeroDiTelefono) throws SQLException {
    UtenteBean a = new UtenteBean(codFisc, password, nome, cognome,
            dataDiNascita, email, numeroDiTelefono);
    um.doSave(a);

    return true;
  }
}
