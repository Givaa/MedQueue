package bean;

/** Classe che rappresenta per quale operazione è possibile prendere una prenotazione. */
public class OperazioneBean {
  private int id;
  private String tipoOperazione;
  private String descrizione;

  /** Inizializza un nuovo oggetto OperazioneBean. */
  public OperazioneBean() {}

  /**
   * Inizializza un nuovo oggetto OperazioneBean settando gli attributi.
   *
   * @param id id dell'operazione
   * @param tipoOperazione tipo operazione
   * @param descrizione descrizione dell'operazione
   */
  public OperazioneBean(int id, String tipoOperazione, String descrizione) {
    this.id = id;
    this.tipoOperazione = tipoOperazione;
    this.descrizione = descrizione;
  }

  /**
   * Metodo che ritorna l'id dell'operazione.
   *
   * @return id operazione
   */
  public int getId() {
    return id;
  }

  /**
   * Metodo che setta un id all'oggetto OperazioneBean.
   *
   * @param id id dell'operazione
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Metodo che restituisce il tipo d'operazione.
   *
   * @return tipo operazione
   */
  public String getTipoOperazione() {
    return tipoOperazione;
  }

  /**
   * Metodo che setta un tipo operazione all'oggetto OperazioneBean.
   *
   * @param tipoOperazione tipo operazione
   */
  public void setTipoOperazione(String tipoOperazione) {
    this.tipoOperazione = tipoOperazione;
  }

  /**
   * Metodo che restituisce la descrizione dell'operazione.
   *
   * @return descrizione operazione
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * Metodo che permette di settare la descrizione dell'operazione all'oggetto OperazioneBean.
   *
   * @param descrizione descrizione operazione
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  @Override
  public String toString() {
    return "OperazioneBean{"
        + "id="
        + id
        + ", tipoOperazione='"
        + tipoOperazione
        + '\''
        + ", descrizione='"
        + descrizione
        + '\''
        + '}';
  }
}
