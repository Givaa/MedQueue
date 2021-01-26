package entity;

/**
 * Classe che rappresenta la prenotazione di un utente
 */
public class PrenotazioneBean {
  private int id;
  private String data;
  private String time;
  private boolean convalida;
  private String codiceFiscale;
  private int idOperazione;
  private int idStruttura;

  /**
   * Inizializza un nuovo oggetto PrenotazioneBean
   */
  public PrenotazioneBean() {}

  /**
   * Inizializza un novo oggetto PrenotazioneBean settando gli attributi
   * @param id id della prenotazione
   * @param data data per cui l'utente si è prenotato
   * @param time ora per cui l'utente si è prenotato
   * @param convalida convalida della prenotazione in struttura
   * @param codiceFiscale codicefiscale dell utente che ha effettuato la prenotazione
   * @param idOperazione id dell'operazione per cui l'utente si è prenotato
   * @param idStruttura id della struttura persso la quale l'utente ha effettuato la prenotazione
   */
  public PrenotazioneBean(
      int id,
      String data,
      String time,
      boolean convalida,
      String codiceFiscale,
      int idOperazione,
      int idStruttura) {
    this.id = id;
    this.data = data;
    this.time = time;
    this.convalida = convalida;
    this.codiceFiscale = codiceFiscale;
    this.idOperazione = idOperazione;
    this.idStruttura = idStruttura;
  }

  /**
   * Metodo per ottenere l'id della prenotazione
   * @return id della prenotazione
   */
  public int getId() {
    return id;
  }

  /**
   * Metodo che permette di settare l'id per un oggetto PrenotazioneBean
   * @param id id della prenotazione
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Metodo che restituisce la data della prenotazione
   * @return data
   */
  public String getData() {
    return data;
  }

  /**
   * Metodo che permette di settare la data per un oggetto PrenotazioneBean
   * @param data data della prenotazione
   */
  public void setData(String data) {
    this.data = data;
  }

  /**
   * Metodo che restituisce l'ora della prentozione
   * @return ora
   */
  public String getTime() {
    return time;
  }

  /**
   * Metodo che permette di settare l'ora per un oggetto PrenotazioneBean
   * @param time ora della prenotazione
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Metodo che restituisce la convalida della prenotazione
   * @return convalida
   */
  public boolean isConvalida() {
    return convalida;
  }

  /**
   * Metodo che permette di settare la convalida per un oggetto PrenotazioneBean
   * @param convalida convalida della prenotazione
   */
  public void setConvalida(boolean convalida) {
    this.convalida = convalida;
  }

  /**
   * Metodo che restituisce il codicefiscale dell'utente che ha effettuato la prenotazione
   * @return codicefiscale
   */
  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Metodo che permette di settare il codiceficale per un oggetto PrenotazioneBean
   * @param codiceFiscale codicefiscale dell'utente che effettua la prenotazione
   */
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Metodo che restituisce l'id dell'operazione per cui l'utente si e prenotato
   * @return id operazione
   */
  public int getIdOperazione() {
    return idOperazione;
  }

  /**
   * Metodo che permette di settare l'id dell operazione per cui si vuole prenotare per un oggetto PrenotazioneBean
   * @param idOperazione id dell'operazione per cui si vuole prenotare
   */
  public void setIdOperazione(int idOperazione) {
    this.idOperazione = idOperazione;
  }

  /**
   * Metodo che restituisce l'id della struttura per la quale l'utente si e prenotato
   * @return id struttura
   */
  public int getIdStruttura() {
    return idStruttura;
  }

  /**
   * Metodo che permette di settare l'id della struttura per cui l'utente vuole prenotarsi per un oggetto PrenotazioneBean
   * @param idStruttura id della struttura per cui l'utente si vuole prenotare
   */
  public void setIdStruttura(int idStruttura) {
    this.idStruttura = idStruttura;
  }


}
