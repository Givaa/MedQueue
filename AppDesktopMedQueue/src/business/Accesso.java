package business;

import bean.ImpiegatoBean;
import persistence.DaoInterface;
import persistence.DataAccess;

/** Classe per verificare le credenziali di un impiegato. */
public class Accesso implements AccessoInterface {
  DaoInterface daoOperation=new DataAccess();

  public Accesso(){}

  /**
   * Metodo di business che verifica le credenziali di un impiegato.
   *
   * @param cf codice fiscale dell'impiegato
   * @param pass password dell'impiegato
   * @return l'impiegato della collezione Impiegato che ha come codice fiscale il codice fiscale
   * passato come parametro al metodo e come password la password passata al metodo,
   * oppure null se le credenziali sono sbagliate
   */
  public ImpiegatoBean verificaCredenziali(String cf, String pass){
    ImpiegatoBean impiegato=daoOperation.getImpiegato(cf);
    if(impiegato!=null){
      if(impiegato.getPassword().equals(pass))
        return impiegato;
      else
        return null;
    }
    return null;
  }

}
