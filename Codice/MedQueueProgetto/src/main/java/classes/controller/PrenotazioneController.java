package classes.controller;

import classes.controller.exception.ErrorNewObjectException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.OperazioneModel;
import classes.model.dao.PrenotazioneModel;
import classes.model.dao.StrutturaModel;
import classes.model.interfaces.OperazioneDaoInterface;
import classes.model.interfaces.PrenotazioneDaoInterface;
import classes.model.interfaces.StrutturaDaoInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe utilizzata per controllare i metodi del Model di Prenotazione, in aggiunta si implementa
 * un metodo per poter prendere le prenotazioni di un singolo utente.
 */
@RestController
public class PrenotazioneController {

  private final PrenotazioneDaoInterface prenotazioneModel = new PrenotazioneModel();
  private final StrutturaDaoInterface strutturaModel = new StrutturaModel();
  private final OperazioneDaoInterface operazioneModel = new OperazioneModel();

  /**
   * Metodo che permette di utilizzare il prelevamento per id del PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Prenotazione avente l'id passato
   * @throws SQLException per problemi di esecuzione della query
   * @throws ObjectNotFoundException per problemi di oggetto non trovato
   */
  @PostMapping(value = "/prenotazione/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public PrenotazioneBean getPrenotazioneById(@RequestBody String body) throws SQLException,
          ObjectNotFoundException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("idPrenotazioneGet").getAsString();

    PrenotazioneBean p = prenotazioneModel.doRetrieveByKey(Integer.valueOf(id));
    if (p.getCodiceFiscale() != null) {
      return p;
    } else {
      throw new ObjectNotFoundException(p);
    }
  }

  /**
   * Metodo che permette di utilizzare il prelevamento di tutti gli oggetti del PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @return Collezione di Prenotazione
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/prenotazioni", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<PrenotazioneBean> getAllPrenotazioni(@RequestBody String body)
          throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String order = jsonObject.get("ordinePrenotazioni").getAsString();
    return prenotazioneModel.doRetrieveAll(order);
  }

  /**
   * Metodo che permette di utilizzare l'inserimento di una nuova prenotazione tramite
   * PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   * @throws ErrorNewObjectException per problemi di creazione di un oggetto
   * @return conferma/non conferma del salvataggio della prenotazione
   */
  @PostMapping(value = "/newPrenotazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean newPrenotazione(@RequestBody String body) throws SQLException,
          ParseException, ErrorNewObjectException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("newPrenotazioniCf").getAsString();
    String ora = jsonObject.get("newPrenotazioniOra").getAsString();
    String idOp = jsonObject.get("newPrenotazioniIdOp").getAsString();
    String idS = jsonObject.get("newPrenotazioniIdS").getAsString();
    String data = jsonObject.get("newPrenotazioneData").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    java.sql.Date dataPrenotazione = new Date(tmp.getTime());

    StrutturaBean s;
    OperazioneBean o;
    o = operazioneModel.doRetrieveByKey(Integer.valueOf(idOp));
    s = strutturaModel.doRetrieveByKey(Integer.valueOf(idS));

    boolean checkOperazione = o != null;
    boolean checkStruttura = s != null;

    if (checkOperazione && checkStruttura) {
      prenotazioneModel.doSave(new PrenotazioneBean(ora, dataPrenotazione, cf,
              Integer.valueOf(idOp), Integer.valueOf(idS), false));
      return true;
    } else {
      throw new ErrorNewObjectException(new PrenotazioneBean());
    }
  }

  /**
   * Metodo che permette di utilizzare l'eliminazione di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/deletePrenotazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public void deletePrenotazione(@RequestBody String body) throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("deletePrenotazioniId").getAsString();
    prenotazioneModel.doDelete(prenotazioneModel.doRetrieveByKey(Integer.valueOf(id)));
  }

  /**
   * Metodo che permette di utilizzare l'aggiornamento di una prenotazione presente sul DB tramite
   * PrenotazioneModel.
   *
   * @param body corpo della richiesta preso in input
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   * @return conferma/non conferma dell'aggiornamento
   */
  @PostMapping(value = "/updatePrenotazione", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean updatePrenotazione(@RequestBody String body) throws SQLException,
          ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("updatePrenotazioniId").getAsString();
    String cf = jsonObject.get("updatePrenotazioniCf").getAsString();
    String ora = jsonObject.get("updatePrenotazioniOra").getAsString();
    String idOp = jsonObject.get("updatePrenotazioniIdOp").getAsString();
    String idS = jsonObject.get("updatePrenotazioniIdS").getAsString();
    String data = jsonObject.get("updatePrenotazioneData").getAsString();
    Date dataPrenotazione = (Date) new SimpleDateFormat("yyyy/mm/gg").parse(data);
    Boolean cv = jsonObject.get("updatePrenotazioneConvalida").getAsBoolean();
    PrenotazioneBean p = prenotazioneModel.doRetrieveByKey(Integer.valueOf(id));


    if (p != null) {
      StrutturaBean b;
      OperazioneBean o;
      o = operazioneModel.doRetrieveByKey(p.getIdOperazione());
      b = strutturaModel.doRetrieveByKey(p.getIdStruttura());

      boolean checkCodFisc = cf.matches("[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
      boolean checkOra = ora.matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");

      if (checkCodFisc && checkOra && b != null && o != null) {
        p.setDataPrenotazione(dataPrenotazione);
        p.setConvalida(cv);
        p.setCodiceFiscale(cf);
        p.setIdStruttura(Integer.valueOf(idS));
        p.setIdOperazione(Integer.valueOf(idOp));
        p.setOra(ora);
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
   * @param body corpo della richiesta preso in input
   * @return Prenotazioni di quell'utente
   * @throws SQLException per problemi di esecuzione della query
   */
  @PostMapping(value = "/prenotazioniUtente/{cf}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public Collection<PrenotazioneBean> getPrenotazioniByCodFisc(@RequestBody String body)
          throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("getPrenotazioniByCf").getAsString();
    return prenotazioneModel.getUtentePrenotazioni(cf);
  }

  /**
   * Metodo per la convalida della prenotazione.
   *
   * @param body corpo della richiesta preso in input
   * @return conferma/non conferma della convalida
   * @throws SQLException per problemi di esecuzione della query
   * @throws ParseException per problemi di parse
   */
  @PostMapping(value = "/convalida", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public boolean convalidaPrenotazione(@RequestBody String body)
          throws SQLException, ParseException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String cf = jsonObject.get("convalidaPrenotazione").getAsString();

    //Prendo la prenotazione
    Collection<PrenotazioneBean> collection = this.getPrenotazioniByCodFisc(cf);
    Iterator iter = collection.iterator();
    PrenotazioneBean p = (PrenotazioneBean) iter.next();

    //Impostazioni variabili data e ora
    LocalDateTime now = LocalDateTime.now();
    p.getDataPrenotazione();
    Date d = p.getDataPrenotazione();
    String ora = p.getOra();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Long minHour = df.parse(ora).getTime();
    Long maxHour = df.parse(ora).getTime();
    Long timeNow = System.currentTimeMillis();
    minHour -= 1800 * 1000;
    maxHour += 600 * 1000;

    if ((now.getDayOfMonth() == d.toLocalDate().getDayOfMonth())
            && (now.getMonth() == d.toLocalDate().getMonth())
            && ((timeNow >= minHour) && (timeNow <= maxHour))) {
      p.setConvalida(true);
      return true;
    }
    return false;
  }

  /**
   * Metodo per prelevare gli orari di prenotazione liberi.
   * @body Il contenuto della request
   * @return Collezione di orari
   * @throws SQLException per problemi di esecuzione della query
   */

  @PostMapping(value = "/getOrari", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<String> getOrariDisponibili(@RequestBody String body)
          throws SQLException, ParseException{
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    int idStruttura = jsonObject.get("idStruttura").getAsInt();
    int idOperazione = jsonObject.get("idOperazione").getAsInt();
    String data = jsonObject.get("PrenotazioneData").getAsString();
    java.util.Date tmp = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    java.sql.Date dataPrenotazione = new Date(tmp.getTime());

    return prenotazioneModel.getOrariPrenotazione(idStruttura,idOperazione,dataPrenotazione);

  }

}
