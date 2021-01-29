package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.ImpiegatoBean;
import classes.model.dao.ImpiegatoModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/** Classe per poter controllare i metodi di ImpiegatoModel. */
@RestController
public class ImpiegatoController {

  private final ImpiegatoModel impiegatoModel = new ImpiegatoModel();

  /**
   * Metodo che permette di utilizzare il prelevamento tramite CF dell'ImpiegatoModel.
   *
   * @param cf Chiave primaria dell'impiegato
   * @return Impiegato avente il CF passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/impiegato/{cf}")
  public ImpiegatoBean getImpiegatoByCodFis(@PathVariable String cf)
      throws SQLException, ObjectNotFoundException {

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
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Impiegati
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/impiegati")
  public Collection<ImpiegatoBean> getAllImpiegati(@RequestBody String order) throws SQLException {
    return impiegatoModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di un nuovo impiegato tramite ImpiegatoModel.
   *
   * @param i Impiegato da inserire
   * @throws SQLException per problemi di esecuzione della query
   * @throws ErrorNewObjectException per problemi nell'input
   * @return conferma/non conferma del salvataggio dell'impiegato
   */
  @GetMapping("/newImpiegato")
  public boolean newImpiegato(@RequestBody ImpiegatoBean i) throws SQLException,
          ErrorNewObjectException {
    if (i == null) {
      throw new ErrorNewObjectException(i);
    } else {
      String codFisc = i.getCodiceFiscale();
      String cognome = i.getCognome();
      String nome = i.getNome();
      String password = i.getPassword();
      String email = i.getIndirizzoEmail();
      String phoneNumber = i.getNumeroDiTelefono();

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

      if (checkName && checkSurname && checkPassword && checkPhoneNumber
              && checkCodFisc && checkMail) {
        impiegatoModel.doSave(i);
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di un impiegato presente sul DB tramite
   * ImpiegatoModel.
   *
   * @param i Impiegato da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deleteImpiegato")
  public void deleteImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
    impiegatoModel.doDelete(i);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di un impiegato presente sul DB tramite
   * ImpiegatoModel.
   *
   * @param i Impiegato da inserire
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma dell'aggiornamento dell'impiegato
   */
  @GetMapping("/updateImpiegato")
  public boolean updateImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
    if (impiegatoModel.doRetrieveByKey(i.getCodiceFiscale()) != null) {
      String codFisc = i.getCodiceFiscale();
      String cognome = i.getCognome();
      String nome = i.getNome();
      String password = i.getPassword();
      String email = i.getIndirizzoEmail();
      String phoneNumber = i.getNumeroDiTelefono();

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

      if (checkName && checkSurname && checkPassword && checkPhoneNumber
              && checkCodFisc && checkMail) {
        impiegatoModel.doUpdate(i);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ObjectNotFoundException(i);
    }
  }
}
