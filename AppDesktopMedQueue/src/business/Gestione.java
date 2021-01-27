package business;

import entity.OperazioneBean;
import entity.PrenotazioneBean;
import java.util.ArrayList;
import persistence.DataAccess;

/** Classe che conterrà tutte le operazioni che l'impiegato puo effettuare. * */
public class Gestione {

  /**
   * Metodo che permette di accettare una prenotazione convalidata.
   *
   * @param idOp id dell'operazione per cui si vuole accettare la prenotazione.
   * @param idStruttura id della struttura per cui si vuole accettare la prenotazione.
   * @return prenotazione o null se non c'è una prenotazione.
   */
  public static PrenotazioneBean accettaPrenotazione(int idOp, int idStruttura) {
    PrenotazioneBean p = DataAccess.serviPrenotazione(idOp, idStruttura);
    if (p != null) {
      if (DataAccess.deletePrenotazione(p.getId()) > 0) {
        return p;
      }
    }
    return null;
  }

  /**
   * Metodo che restituisce il numero di prenotazioni da accettare.
   *
   * @param idOperazione id della operazione
   * @param idStruttura id della struttura
   * @return numero prenotazioni
   */
  public static int getNumPrenotazioni(int idOperazione, int idStruttura) {
    return DataAccess.numPrenotazioni(idOperazione, idStruttura);
  }

  /**
   * Metodo che la lista di operazioni che l'impiegato può servire.
   *
   * @return lista operazioni
   */
  public static ArrayList<OperazioneBean> getListaOperazioni() {
    return DataAccess.getOperazioni();
  }
}
