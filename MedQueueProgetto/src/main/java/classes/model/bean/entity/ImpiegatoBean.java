package classes.model.bean.entity;

import java.sql.Date;

/**
 * Classe utilizzata per rappresentare l'entità Impiegato del DB.
 */
public class ImpiegatoBean {

  private String codiceFiscale;
  private String password;
  private String nome;
  private String cognome;
  private Date dataDiNascita;
  private String indirizzoEmail;
  private String numeroDiTelefono;

  /**
   * Costruttore dell'oggetto ImpiegatoBean.
   *
   * @param codiceFiscale Chiave principale dell'impiegato
   * @param password Password dell'impiegato
   * @param nome Nome dell'impiegato
   * @param cognome Cognome dell'impiegato
   * @param dataDiNascita Data di nascita dell'impiegato
   * @param indirizzoEmail Indirizzo Email dell'impiegato
   * @param numeroDiTelefono Numero di telefono dell'impiegato.
   */
  public ImpiegatoBean(
      String codiceFiscale,
      String password,
      String nome,
      String cognome,
      Date dataDiNascita,
      String indirizzoEmail,
      String numeroDiTelefono) {
    this.codiceFiscale = codiceFiscale;
    this.password = password;
    this.nome = nome;
    this.cognome = cognome;
    this.dataDiNascita = dataDiNascita;
    this.indirizzoEmail = indirizzoEmail;
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Costruttore vuoto dell'oggetto ImpiegatoBean.
   */
  public ImpiegatoBean() {}

  /**
   * Prelevamento del codice fiscale dell'impiegato.
   *
   * @return codice fiscale dell'impiegato
   */
  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Impostazione del codice fiscale dell'impiegato.
   *
   * @param codiceFiscale nuovo codice fiscale dell'impiegato
   */
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Prelevamento della password dell'impiegato.
   *
   * @return password dell'impiegato
   */
  public String getPassword() {
    return password;
  }

  /**
   * Impostazione della password dell'impiegato.
   *
   * @param password nuova password dell'impiegato.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Prelevamento del nome dell'impiegato.
   *
   * @return nome dell'impiegato
   */
  public String getNome() {
    return nome;
  }

  /**
   * Impostazione del nome dell'impiegato.
   *
   * @param nome nuovo nome dell'impiegato
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Prelevamento del cognome dell'impiegato.
   *
   * @return cognome dell'impiegato
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Impostazione del cognome dell'impiegato.
   *
   * @param cognome nuovo cognome dell'impiegato
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Prelevamento della data di nascita dell'impiegato.
   *
   * @return data di nascita dell'impiegato
   */
  public Date getDataDiNascita() {
    return dataDiNascita;
  }

  /**
   * Impostazione della data di nascita dell'impiegato.
   *
   * @param dataDiNascita nuova data di nascita dell'impiegato.
   */
  public void setDataDiNascita(Date dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  /**
   * Prelevamento dell'indirizzo email dell'impiegato.
   *
   * @return indirizzo email dell'impiegato.
   */
  public String getIndirizzoEmail() {
    return indirizzoEmail;
  }

  /**
   * Impostazione dell'indirizzo email dell'impiegato.
   *
   * @param indirizzoEmail nuovo indirizzo email dell'impiegato
   */
  public void setIndirizzoEmail(String indirizzoEmail) {
    this.indirizzoEmail = indirizzoEmail;
  }

  /**
   * Prelevamento del numero di telefono dell'impiegato.
   *
   * @return numero di telefono dell'impiegato
   */
  public String getNumeroDiTelefono() {
    return numeroDiTelefono;
  }

  /**
   * Impostazione del numero di telefono dell'impiegato.
   *
   * @param numeroDiTelefono nuovo numero di telefono dell'impiegato
   */
  public void setNumeroDiTelefono(String numeroDiTelefono) {
    this.numeroDiTelefono = numeroDiTelefono;
  }

  /**
   * Rappresentazione scritta dell'oggetto Impiegato.
   *
   * @return Stringa con tutti i campi dell'oggetto
   */
  @Override
  public String toString() {
    return "ImpiegatoBean{"
        + "codiceFiscale='"
        + codiceFiscale
        + '\''
        + ", password='"
        + password
        + '\''
        + ", nome='"
        + nome
        + '\''
        + ", cognome='"
        + cognome
        + '\''
        + ", dataDiNascita="
        + dataDiNascita
        + ", indirizzoEmail='"
        + indirizzoEmail
        + '\''
        + ", numeroDiTelefono='"
        + numeroDiTelefono
        + '\''
        + '}';
  }
}
