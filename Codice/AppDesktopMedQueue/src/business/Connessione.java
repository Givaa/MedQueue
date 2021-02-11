package business;

import java.sql.Connection;
import java.sql.SQLException;
import persistence.DriverManagerConnectionPool;

/** Classe che offre le funzionalita di business per connettersi e disconettersi da un database. */
public class Connessione implements ConnessioneInterface {

  /** Metodo che permette la connessione al database. */
  public Connessione() {}

  /**
   * Metodo che permette la connessione al database.
   *
   * @return connessione
   */
  public Connection connect() {
    Connection con = null;
    try {
      con = DriverManagerConnectionPool.getConnection();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return con;
  }

  /**
   * Metodo che permette di disconnettersi dal database.
   *
   * @param connect connessione al database
   * @return true o false
   */
  public boolean disconnect(Connection connect) {
    boolean value = false;
    try {
      DriverManagerConnectionPool.releaseConnection(connect);
      value = true;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return value;
  }
}
