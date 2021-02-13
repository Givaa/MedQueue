package business;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import eccezioni.InvalidAccesException;
import eccezioni.InvalidManagementException;
import java.util.ArrayList;

/** Classe che permette di nascondere il funzionamento complesso del business code. */
public class FacadeClassBusiness {

  private final AccessoInterface accesso;
  private final GestioneInterface gestione;

  /** Costruttore della classe. */
  public FacadeClassBusiness() {
    accesso = new Accesso();
    gestione = new Gestione();
  }

  /**
   * Permette di chiedere al sistema di verificare l'autenticazione dell'impiegato.
   *
   * @param cf codice fiscale inserito dall'impiegato
   * @param pw password inserita dall impiegato
   * @return ritorna un oggetto contenente i dati dell'impiegato se l'autenticazione ha successo
   *     altrimenti null.
   * @throws InvalidAccesException se il codice fiscale o la password non rispettano il formato
   *     delle credenziali di un impiegato
   */
  public ImpiegatoBean autenticazione(String cf, String pw) {
    try {
      if (cf == null || cf.length() != 16 || pw == null) {
        throw new InvalidAccesException("Pre-condition violata");
      }
      return accesso.verificaCredenziali(cf, pw);
    } catch (InvalidAccesException ex) {
      System.out.println(ex.toString());
    }
    return null;
  }

  /**
   * Permette di chiedere al sistema di accettare una prenotazione in coda per una determinata.
   * struttura
   *
   * @param idStruttura id della struttura
   * @param idOperazione id della coda
   * @return ritorna un oggetto contenente i dati dell della prenotazione oppure un oggetto null
   * @throws InvalidManagementException se l'idStruttura o l'idOperazione sono minori o uguale di 0
   */
  public PrenotazioneBean accettaPrenotazione(Integer idStruttura, Integer idOperazione) {
    try {
      if (idStruttura <= 0 || idOperazione <= 0) {
        throw new InvalidManagementException("Pre-condition violata");
      }
      return gestione.accettaPrenotazione(idStruttura, idOperazione);
    } catch (InvalidManagementException ex) {
      System.out.println(ex.toString());
    }
    return null;
  }

  /**
   * Permette di chiedere al sistema quali sono le code gestibili.
   *
   * @return ritorna una lista di code gestibili
   */
  public ArrayList<OperazioneBean> getCode() {
    return gestione.getListaOperazioni();
  }

  /**
   * Permette di chiedere al sistema le informazioni di una determinata coda.
   *
   * @param idOperazione id della coda
   * @return ritorna un oggetto contenente le informazioni sulla coda oppure un oggetto null
   * @throws InvalidManagementException se l'idOperazione e minore o uguale di 0
   */
  public OperazioneBean getCoda(int idOperazione) {
    try {
      if (idOperazione <= 0) {
        throw new InvalidManagementException("Pre-condition violata");
      }
      return gestione.getOperazione(idOperazione);
    } catch (InvalidManagementException ex) {
      System.out.println(ex.toString());
    }
    return null;
  }

  /**
   * Permette di chiedere al sistema il numero di prenotazioni di una coda.
   *
   * @param idOperazione id della coda
   * @param idStruttura id della struttura che gestisce la coda
   * @return ritorna il numero di prenotazioni
   * @throws InvalidManagementException se l'idOperazion o l'idStruttua sono minori o uguali di 0
   */
  public int getSizeCoda(int idOperazione, int idStruttura) {
    try {
      if (idOperazione <= 0 || idStruttura <= 0) {
        throw new InvalidManagementException("Pre-condition violata");
      }
      return gestione.getNumPrenotazioni(idOperazione, idStruttura);
    } catch (InvalidManagementException ex) {
      System.out.println(ex.toString());
    }
    return 0;
  }
}
