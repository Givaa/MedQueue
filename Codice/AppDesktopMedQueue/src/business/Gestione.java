package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;
import eccezioni.InvalidKeyException;
import java.util.ArrayList;
import persistence.DaoInterface;
import persistence.DataAccess;

/** Classe che conterrà tutte le operazioni che l'impiegato puo effettuare. * */
public class Gestione implements GestioneInterface {
  private final DaoInterface dao = new DataAccess();


  public Gestione() {}

  /**
   * Implementa la funzionalita di business che permette
   *      ad un impiegato di accettare una prenotazione.
   *
   * @param idOp id della coda che l'impiegato gestisce
   * @param idStruttura id della struttura per la quale l'impiegato lavora
   * @return ritorna le informazioni della prenotazione accettata
   *      che l'impiegato dovra servire oppure
   *        se non ce ne sono null
   * @throws InvalidKeyException se idOp oppure idStruttura sono minori o uguali di 0
   * @pre idOperazione>0 && idStruttura>0
   * @post Prenotazione->Select(p|p.idStruttura==idStruttura &&
   *       p.idOperazione==idOperazione && p.convalida==true)
   */
  public PrenotazioneBean accettaPrenotazione(Integer idOp, Integer idStruttura) {
    try {
      if (idOp == null || idStruttura == null) {
        throw new InvalidKeyException("Id non valido, occorre un id>0");
      }
      if (idOp <= 0 || idStruttura <= 0) {
        throw new InvalidKeyException("Id non valido, occorre un id>0");
      } else {
        PrenotazioneBean p = dao.serviPrenotazione(idOp, idStruttura);
        if (p != null) {
          if (p.getId() <= 0) {
            throw new InvalidKeyException("Id non valido occorre un id>0");
          } else {
            if (dao.deletePrenotazione(p.getId()) > 0) {
              return p;
            }
          }
        }
      }

    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return null;
  }

  /**
   * Implementa la funzionalità di business che
   *      permette di sapere il numero di prenotazioni in coda.
   *
   * @param idOperazione id della coda
   * @param idStruttura id della struttura che gestisce la coda
   * @return numero di prenotazioni in coda
   * @throws InvalidKeyException se l'idOperazione oppure l'idStruttura sono minori o uguali a 0
   * @pre idOperazione>0 && idStruttura>0
   * @post Prenotazione->exists(p|p.idStruttura==idStruttura && p.idOperazione==idOperazione).size()
   */
  public int getNumPrenotazioni(int idOperazione, int idStruttura) {
    try {
      if (idOperazione <= 0 || idStruttura <= 0) {
        throw new InvalidKeyException("Id non valido occorre un id>0");
      } else {
        return dao.numPrenotazioni(idOperazione, idStruttura);
      }
    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return 0;
  }

  /**
   * Implementa la funzionalità di business che
   *      permette all'impiegato di conoscere le code gestibili.
   *
   * @return ritorna una lista di code
   * @post Operazioni->asSet(Operazioni)
   */
  public ArrayList<OperazioneBean> getListaOperazioni() {
    return dao.getOperazioni();
  }

  /**
   * Implementa la funzionalità di business che restituisce le informazioni su una coda.
   *
   * @param id id della coda
   * @return ritorna un oggetto contenente le informazioni della coda
   * @throws InvalidKeyException se l'id e minore o uguale di 0
   * @pre id>0
   * @post Operazione->select(o|o.idOperazione==id)
   */
  public OperazioneBean getOperazione(int id) {
    try {
      if (id <= 0) {
        throw new InvalidKeyException("Id non valido occorre un id>0");
      } else {
        return dao.getOperazione(id);
      }
    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return null;
  }
}
