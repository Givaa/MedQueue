package business;

import bean.ImpiegatoBean;
import eccezioni.InvalidKeyException;
import java.util.regex.Pattern;
import persistence.DaoInterface;
import persistence.DataAccess;

/** Classe per verificare le credenziali di un impiegato. */
public class Accesso implements AccessoInterface {
  DaoInterface daoOperation = new DataAccess();

  /**
   * Metodo di business che verifica le credenziali di un impiegato.
   */
  public Accesso() {}

  /**
   * Metodo di business che verifica le credenziali di un impiegato.
   *
   * @param cf codice fiscale dell'impiegato
   * @param pass password dell'impiegato
   * @return l'impiegato della collezione Impiegato che ha come codice fiscale il codice fiscale
   *     passato come parametro al metodo e come password la password passata al metodo, oppure null
   *     se le credenziali sono sbagliate
   */
  public ImpiegatoBean verificaCredenziali(String cf, String pass) {
    try {
      if (Pattern.compile("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}"
              + "[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$").matcher(cf).matches() == false) {
        throw new InvalidKeyException("Codice Fiscale non rispetta il formato.");
      }
      if(pass==null)
        throw new InvalidKeyException("Password non inserita.");
      if (Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
              .matcher(pass).matches() == false) {
        throw new InvalidKeyException("Password non rispetta il formato.");
      } else {
        ImpiegatoBean impiegato = daoOperation.getImpiegato(cf);
        if (impiegato != null) {
          if (impiegato.getPassword().equals(pass)) {
            return impiegato;
          }
        }
      }
    } catch (InvalidKeyException i) {
      System.out.println(i.toString());
    }
    return null;
  }
}
