package business;

import bean.OperazioneBean;
import bean.PrenotazioneBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eccezioni.InvalidKeyException;
import persistence.DaoInterface;
import persistence.DataAccess;
import persistence.DriverManagerConnectionPool;

/** Classe che conterrà tutte le operazioni che l'impiegato puo effettuare. **/
public class Gestione implements GestioneInterface{
  private DaoInterface dao=new DataAccess();

  public Gestione(){}

  /**
   * Metodo di business che permette di accettare una prenotazione convalidata.
   *
   * @param idOp id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return prenotazione della collezione Prenotazione che ha come idOperazione l'idOperazione
   * passato come parametro, come idStruttura l'idStruttura passato come parametro e convalida a true
   */
  public  PrenotazioneBean accettaPrenotazione(int idOp, int idStruttura) {
    try {
      if(idOp<=0 || idStruttura<=0) {
        throw new InvalidKeyException("Id non valido, occorre un id>0");
      }else {
        PrenotazioneBean p = dao.serviPrenotazione(idOp, idStruttura);
        if (p != null) {
          if (p.getId()<=0) {
            throw new InvalidKeyException("Id non valido occorre un id>0");
          } else {
             if (dao.deletePrenotazione(p.getId()) > 0) {
               return p;
              }
          }
        }
      }

    }catch (InvalidKeyException i){
      System.out.println(i.toString());
    }
    return null;
  }

  /**
   * Metodo che restituisce il numero di prenotazioni da accettare.
   *
   * @param idOperazione id dell'operazione della collezione Operazione
   * @param idStruttura id della struttura della collezione Struttura
   * @return size delle prenotazioni della collezione prenotazione che hanno come idStruttura l'idStruttura
   * passato come parametro, come idOperazione l'idOperazione passato come parametro e convalida a true
   */
  public int getNumPrenotazioni(int idOperazione, int idStruttura) {
    try {
      if (idOperazione<=0 || idStruttura<=0)
        throw new InvalidKeyException("Id non valido occorre un id>0");
      else
        return dao.numPrenotazioni(idOperazione, idStruttura);
    }catch (InvalidKeyException i){
      System.out.println(i.toString());
    }
    return 0;
  }

  /**
   * Metodo di business che mostra all'impiegato le operazioni che puo servire.
   *
   * @return operazioni della collezione Operazione
   */
  public ArrayList<OperazioneBean> getListaOperazioni() {
    return dao.getOperazioni();
  }

  /**
   * Metodo di business che permette di scegliere all'impiegato un operazione da gestire.
   * @param id id dell'operazione della collezione Operazione
   * @return operazione della collezione Operazione che ha come id, l'id passato come parametro
   */
  public OperazioneBean getOperazione(int id) {
    try{
      if(id<=0)
        throw new InvalidKeyException("Id non valido occorre un id>0");
      else
        return dao.getOperazione(id);
    }catch (InvalidKeyException i){
      System.out.println(i.toString());
    }
    return null;
  }

}
