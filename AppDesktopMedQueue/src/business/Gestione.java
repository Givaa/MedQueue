package business;

import entity.PrenotazioneBean;
import persistence.DataAccess;

/** Classe che conterrà tutte le operazioni che l'impiegato puo effettuare. **/
public class Gestione {

  /**
   * Metodo che permette di accettare una prenotazione convalidata.
   *
   * @param idOp id dell'operazione per cui si vuole accettare la prenotazione.
   * @param idStruttura id della struttura per cui si vuole accettare la prenotazione.
   * @return prenotazione o null se non c'è una prenotazione.
   **/
  public static PrenotazioneBean accettaPrenotazione(int idOp, int idStruttura) {
    PrenotazioneBean p = DataAccess.serviPrenotazione(idOp, idStruttura);
    if (p != null) {
      if (DataAccess.deletePrenotazione(p.getId()) > 0) {
        return p;
      }
    }
    return null;
  }
}
