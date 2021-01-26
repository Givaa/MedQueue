package business;

import entity.PrenotazioneBean;
import persistence.DataAccess;

/**
 * Classe che conterra tutte le operazioni che l'impiegato puo effettuare
 */
public class Gestione {

    /**
     * Metodo che permette di accettare una prenotazione convalidata
     * @param idOp id dell'operazione per cui si vuole accettare la prenotazione
     * @param idStruttura id della struttura per cui si vuole accettare la prenotazione
     * @return prenotazione
     */
  public static PrenotazioneBean accettaPrenotazione(int idOp, int idStruttura) {
    PrenotazioneBean p = DataAccess.serviPrenotazione(idOp, idStruttura);
    if (p != null) {
        DataAccess.deletePrenotazione(p.getId());
    } else {
        return null;
    }
    return p;
  }
}
