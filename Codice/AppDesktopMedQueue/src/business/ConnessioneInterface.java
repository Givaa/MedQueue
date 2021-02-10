package business;

import java.sql.Connection;

/**
 * Interfaccia che offre le funzionalita di business per connettersi e disconettersi da un database.
 */
public interface ConnessioneInterface {

  /**
   * Metodo che permette la connessione al database.
   *
   * @return connessione
   */
  public Connection connect();

  /**
   * Metodo che permette di disconnettersi dal database.
   *
   * @param connect connessione al database
   * @return true o false
   */
  public boolean disconnect(Connection connect);
}
