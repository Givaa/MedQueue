package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe utilizzata per controllare i metodi del Model di Prenotazione, in aggiunta si implementa
 * un metodo per poter prendere le prenotazioni di un singolo utente.
 */
@RestController
public class PrenotazioneController {

  private final PrenotazioneModel prenotazioneModel = new PrenotazioneModel();

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
   */
  @GetMapping("/newPrenotazione")
  public void newOperazione(PrenotazioneBean p) throws SQLException {
    prenotazioneModel.doSave(p);
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param p Prenotazione da eliminare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/deletePrenotazione")
  public void deleteOperazione(PrenotazioneBean p) throws SQLException {
    prenotazioneModel.doDelete(p);
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param p Prenotazione da aggiornare
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/updatePrenotazione")
  public void updateOperazione(PrenotazioneBean p) throws SQLException {
    prenotazioneModel.doUpdate(p);
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
