package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;
import eccezioni.InvalidKeyException;

import java.util.ArrayList;

/** Interfaccia che conterrà tutte le operazioni che l'impiegato puo effettuare. * */
public interface GestioneInterface {

  /**
   * Permette di accettare una prenotazione.
   *
   * @param idOp id della coda che l'impiegato gestisce
   * @param idStruttura id della struttura per la quale l'impiegato lavora
   * @return ritorna le informazioni della prenotazione accettata che l'impiegato dovra servire oppure
   *         se non ce ne sono null
   * @pre idOperazione>0 && idStruttura>0
   * @post Prenotazione->Select(p|p.idStruttura==idStruttura && p.idOperazione==idOperazione && p.convalida==true)
   */
  public PrenotazioneBean accettaPrenotazione(Integer idOp, Integer idStruttura);

  /**
   * Permette di sapre il numero di prenotazioni in coda
   *
   * @param idOperazione id della coda
   * @param idStruttura id della struttura che gestisce la coda
   * @return numero di prenotazioni in coda
   * @pre idOperazione>0 && idStruttura>0
   * @post Prenotazione->exists(p|p.idStruttura==idStruttura && p.idOperazione==idOperazione).size()
   */
  public int getNumPrenotazioni(int idOperazione, int idStruttura);

  /**
   * Permette di ottenere le code gestibili
   *
   * @return ritorna una lista di code
   * @post Operazioni->asSet(Operazioni)
   */
  public ArrayList<OperazioneBean> getListaOperazioni();

  /**
   * Permette di avere le informazioni di una determinata coda
   *
   * @param id id della coda
   * @return ritorna un oggetto contenente le informazioni della coda
   * @pre id>0
   * @post Operazione->select(o|o.idOperazione==id)
   */
  public OperazioneBean getOperazione(int id);
}
