package business;

import bean.ImpiegatoBean;
import eccezioni.InvalidKeyException;

/** Interfaccia per verificare le credenziali di un impiegato. */
public interface AccessoInterface {

  /**
   * Permette di verificare le credenziali di un impiegato.
   *
   * @param cf codice fiscale dell'impiegato
   * @param password password dell'impiegato
   * @return restituise cun oggetto contenente le informazioni di un impiegato se le credenziali sono giuste oppure
   *         un oggetto null se le credenziali sono sbagliate
   * @pre codicefiscale!=null && codicefiscale.lenght==16 && password!=null
   * @post Impiegato->select(i|i.codicefiscale==codicefiscale && i.password==password)
   */
  public ImpiegatoBean verificaCredenziali(String cf, String password);
}
