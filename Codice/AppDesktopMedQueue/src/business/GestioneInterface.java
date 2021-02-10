package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;
import java.util.ArrayList;

/** Interfaccia che conterrà tutte le operazioni che l'impiegato puo effettuare. * */
public interface GestioneInterface {

  /**
   * Metodo di business che permette di accettare una prenotazione convalidata.
   *
   * @param idOp id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return prenotazione della collezione Prenotazione che ha come idOperazione l'idOperazione
   *     passato come parametro, come idStruttura l'idStruttura passato come parametro e convalida a
   *     true
   */
  public PrenotazioneBean accettaPrenotazione(Integer idOp, Integer idStruttura);

  /**
   * Metodo che restituisce il numero di prenotazioni da accettare.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return size delle prenotazioni della collezione Prenotazione che hanno come idStruttura
   *     l'idStruttura passato come parametro, come idOperazione l'idOperazione passato come
   *     parametro e convalida a true
   */
  public int getNumPrenotazioni(int idOperazione, int idStruttura);

  /**
   * Metodo di business che mostra all'impiegato le operazioni che puo servire.
   *
   * @return operazioni della collezione Operazione
   */
  public ArrayList<OperazioneBean> getListaOperazioni();

  /**
   * Metodo di business che permette di scegliere all'impiegato un operazione da gestire.
   *
   * @param id id dell'operazione della collezione Operazione
   * @return operazione della collezione Operazione che ha come id, l'id passato come parametro
   */
  public OperazioneBean getOperazione(int id);
}
