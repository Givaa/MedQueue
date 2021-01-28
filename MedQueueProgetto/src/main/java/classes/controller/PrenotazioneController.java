package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.OperazioneModel;
import classes.model.dao.PrenotazioneModel;
import java.sql.SQLException;
import java.util.Collection;

import classes.model.dao.StrutturaModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe utilizzata per controllare i metodi del Model di Prenotazione, in aggiunta si implementa
 * un metodo per poter prendere le prenotazioni di un singolo utente.
 */
@RestController
public class PrenotazioneController {

  private final PrenotazioneModel prenotazioneModel = new PrenotazioneModel();
  private final StrutturaModel strutturaModel = new StrutturaModel();
  private final OperazioneModel operazioneModel = new OperazioneModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id del PrenotazioneModel.
   *
   * @param id Chiave primaria della Prenotazione
   * @return Prenotazione avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @GetMapping("/prenotazione/{id}")
  public PrenotazioneBean getPrenotazioneById(String id) throws SQLException, ObjectNotFoundException {
    PrenotazioneBean p = prenotazioneModel.doRetrieveByKey(id);
    if (p != null) {
      return p;
    } else {
      throw new ObjectNotFoundException(p);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti del PrenotazioneModel.
   *
   * @param order Ordine in cui si vuole visualizzare la collezione
   * @return Collezione di Prenotazione
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/prenotazioni")
  public Collection<PrenotazioneBean> getAllPrenotazioni(String order) throws SQLException {
    return prenotazioneModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova prenotazione tramite
   * PrenotazioneModel.
   *
   * @param p Prenotazione da inserire
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma del salvataggio della prenotazione
   */
  @GetMapping("/newPrenotazione")
  public boolean newPrenotazione(PrenotazioneBean p) throws SQLException {
    if ( p != null ) {
      StrutturaBean s;
      OperazioneBean o;
      String cf = p.getCodiceFiscale();
      String ora = p.getOra();
      o = operazioneModel.doRetrieveByKey(String.valueOf(p.getIdOperazione()));
      s = strutturaModel.doRetrieveByKey(String.valueOf(p.getIdStruttura()));

      boolean checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      boolean checkOra = ora.matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");
      boolean checkOperazione = o != null;
      boolean checkStruttura = s != null;

      if (checkCodFisc && checkOra && checkOperazione && checkStruttura) {
        prenotazioneModel.doSave(p);
        return true;
      } else {
        return false;
      }
    } else {
      throw new ErrorNewObjectException(p);
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param p Prenotazione da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deletePrenotazione")
  public void deletePrenotazione(PrenotazioneBean p) throws SQLException {
    prenotazioneModel.doDelete(p);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param p Prenotazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   * @return conferma/non conferma dell'aggiornamento
   */
  @GetMapping("/updatePrenotazione")
  public boolean updatePrenotazione(PrenotazioneBean p) throws SQLException {
    if (p != null) {
      StrutturaBean b;
      OperazioneBean o;
      String cf = p.getCodiceFiscale();
      String ora = p.getOra();
      o = operazioneModel.doRetrieveByKey(String.valueOf(p.getIdOperazione()));
      b = strutturaModel.doRetrieveByKey(String.valueOf(p.getIdStruttura()));

      boolean checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      boolean checkOra = ora.matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");

      if (checkCodFisc && checkOra && b != null && o != null) {
        prenotazioneModel.doUpdate(p);
        return true;
      } else {
        return false;
      }
    }

    return false;
  }

  /**
   * Metodo che permette di avere tutte le prenotazione di un utente in base al suo Codice Fiscale.
   *
   * @param cf Codice Fiscale dell'utente
   * @return Prenotazioni di quell'utente
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/prenotazioniUtente/{cf}")
  public Collection<PrenotazioneBean> getPrenotazioniByCodFisc(String cf) throws SQLException {
    return prenotazioneModel.getUtentePrenotazioni(cf);
  }
}
