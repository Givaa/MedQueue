package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServlet;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe che ci permette di effettuare il login al sito. */
@RestController
public class LogInController extends HttpServlet {

  private final UtenteModel um = new UtenteModel();

  /**
   * Metodo che controlla le credenziali inviate.
   *
   * @param body corpo della richiesta preso in input
   * @return Permesso/Non permesso di accesso
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping (value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public UtenteBean login(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String username = jsonObject.get("username").getAsString();
    String password = jsonObject.get("password").getAsString();

    UtenteBean a = um.doRetrieveByKey(username);

    if (password.equals(a.getPassword())) {
      return a;
    } else {
      return null;
    }
  }

  /**
   * Metodo che permette di registrarsi al sito.
   *
   * @param body corpo della richiesta preso in input
   * @return conferma o meno della registrazione
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di conversione di data
   */
  @PostMapping (value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public UtenteBean signup(@RequestBody String body) throws SQLException,
          ParseException {

    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

    String nome = jsonObject.get("nome").getAsString();
    Boolean checkName;
    checkName = nome.matches("[A-Za-z]+$");

    String cognome = jsonObject.get("cognome").getAsString();
    Boolean checkSurname;
    checkSurname = cognome.matches("[A-Za-z]+$");

    String codFisc = jsonObject.get("codFisc").getAsString();
    Boolean checkCodFisc;
    checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");

    String password = jsonObject.get("password").getAsString();
    String phoneNumber = jsonObject.get("numeroTelefono").getAsString();

    String dataNascita = jsonObject.get("dataDiNascita").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascita);
    java.sql.Date dataDiNascita = new Date(tmp.getTime());

    String email = jsonObject.get("email").getAsString();
    Boolean checkMail;
    checkMail = email.matches("/\\S+@\\S+\\.\\S+/");

    Boolean checkPassword;
    checkPassword =
            password.matches("(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$");
    checkCodFisc = codFisc.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");


    Boolean checkPhoneNumber;
    checkPhoneNumber = phoneNumber.matches("^[0-9]{10,12}");

    UtenteBean a = new UtenteBean();
    if (checkName && checkSurname && checkPassword
            && checkPhoneNumber && checkCodFisc /*&& checkMail*/) {
      a = new UtenteBean(codFisc, password, nome, cognome,
              dataDiNascita, email, phoneNumber);
      um.doSave(a);

      return a;
    } else {
      throw new ErrorNewObjectException(a);
    }

  }
}
