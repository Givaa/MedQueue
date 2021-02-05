package business;

import bean.ImpiegatoBean;

/** Interfaccia per verificare le credenziali di un impiegato. */
public interface AccessoInterface {

  /**
   * Metodo di business che verifica le credenziali di un impiegato.
   *
   * @param cf codice fiscale dell'impiegato
   * @param password password dell'impiegato
   * @return impiegato della collezione Impiegato che ha come codice fiscale il codice fiscale
   *     passato come parametro al metodo e come password la password passata al metodo
   */
  public ImpiegatoBean verificaCredenziali(String cf, String password);
}
