package business;

import bean.ImpiegatoBean;
import eccezioni.InvalidKeyException;
import java.util.regex.Pattern;
import persistence.DaoInterface;
import persistence.DataAccess;

/** Classe che implementa le funzionalità di business relative all'accesso di un impiegato. */
public class Accesso implements AccessoInterface {
  DaoInterface daoOperation = new DataAccess();


  public Accesso() {}

  /**
   * Implementa la funzionalità di business che verifica le credenziali dell'impiegato.
   *
   * @param cf codice fiscale dell'impiegato
   * @param pass password dell'impiegato
   * @return restituisce un oggetto contenente le informazioni di un impiegato
   *      se le credenziali sono giuste oppure un oggetto null se le credenziali sono sbagliate
   * @throws InvalidKeyException se il codice fiscale o la password non rispetta il formato
   *      o non viene passata una password
   * @pre codicefiscale!=null && codicefiscale.lenght==16 && password!=null
   * @post Impiegato->select(i|i.codicefiscale==codicefiscale && i.password==password)
   */
  public ImpiegatoBean verificaCredenziali(String cf, String pass) {
    try {
      if (Pattern.compile(
                  "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}"
                      + "[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
              .matcher(cf)
              .matches()
          == false) {
        throw new InvalidKeyException("Codice Fiscale non rispetta il formato.");
      }
      if (pass == null) {
        throw new InvalidKeyException("Password non inserita.");
      }
      if (Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$").matcher(pass).matches()
          == false) {
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
