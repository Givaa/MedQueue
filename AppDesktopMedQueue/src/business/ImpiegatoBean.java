package business;

public class ImpiegatoBean {

    private String codicefiscale;
    private String password;
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String indirizzoEmail;
    private String numeroDiTelefono;
    private int idStruttura;

    public ImpiegatoBean(){ }

    public ImpiegatoBean(String codicefiscale, String password, String nome, String cognome, String dataDiNascita, String indirizzoEmail, String numeroDiTelefono, int idStruttura) {
        this.codicefiscale = codicefiscale;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.indirizzoEmail = indirizzoEmail;
        this.numeroDiTelefono = numeroDiTelefono;
        this.idStruttura = idStruttura;
    }

    //GET
    public String getCodicefiscale() { return codicefiscale; }
    public String getPassword() { return password; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getDataDiNascita() { return dataDiNascita; }
    public String getIndirizzoEmail() { return indirizzoEmail; }
    public String getNumeroDiTelefono() { return numeroDiTelefono; }
    public int getIdStruttura() { return idStruttura; }

    //SET
    public void setCodicefiscale(String codicefiscale) { this.codicefiscale = codicefiscale; }
    public void setPassword(String password) { this.password = password; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setDataDiNascita(String dataDiNascita) { this.dataDiNascita = dataDiNascita; }
    public void setIndirizzoEmail(String indirizzoEmail) { this.indirizzoEmail = indirizzoEmail; }
    public void setNumeroDiTelefono(String numeroDiTelefono) { this.numeroDiTelefono = numeroDiTelefono; }
    public void setIdStruttura(int idStruttura) { this.idStruttura = idStruttura; }
}
