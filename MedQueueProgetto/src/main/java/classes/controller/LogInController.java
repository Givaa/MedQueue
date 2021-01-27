package classes.controller;

import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
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
  public boolean login(@RequestBody String username, @RequestBody String password) throws SQLException {
    UtenteBean a = um.doRetrieveByKey(username);

    return a.getPassword().equals(password);
  }
}
