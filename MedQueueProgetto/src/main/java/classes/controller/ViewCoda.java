package classes.controller;

import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;
import java.sql.SQLException;
import java.util.Collection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Classe per visualizzare la coda di una struttura. */
@RestController
public class ViewCoda {
  public final PrenotazioneModel prenotazioneModel = new PrenotazioneModel();

  /**
   * Metodo che permette di visualizzare la coda di una struttura tramite l'id.
   *
   * @param body corpo della richiesta preso in input
   * @return Coda della struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/visualizzaCoda/{id}")
  public Collection<PrenotazioneBean> getAllPrenotazioniByStruttura(@RequestBody String body)
      throws SQLException {
    JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
    String id = jsonObject.get("getAllPrenotazionyByStruttura").getAsString();
    return prenotazioneModel.getCodaStruttura(Integer.valueOf(id));
  }
}
