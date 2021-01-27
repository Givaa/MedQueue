package classes.controller;

import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** Classe per visualizzare la coda di una struttura. */
@RestController
public class ViewCoda {
  public final PrenotazioneModel prenotazioneModel = new PrenotazioneModel();

  /**
   * Metodo che permette di visualizzare la coda di una struttura tramite l'id.
   *
   * @param id Chiave primaria della struttura
   * @return Coda della struttura
   * @throws SQLException per problemi di esecuzione della query
   */
  @GetMapping("/visualizzaCoda/{id}")
  Collection<PrenotazioneBean> getAllPrenotazioniByStruttura(@PathVariable int id)
      throws SQLException {
    return prenotazioneModel.getCodaStruttura(id);
  }
}
