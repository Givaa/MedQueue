package classes.model.bean.entity;


import java.sql.Date;

public class UtenteBean {


    private String codiceFiscale;
    private String password, nome, cognome;
    private Date dataDiNascita;
    private String email;
    private int numeroDiTelefono;

    public UtenteBean(String codiceFiscale, String password, String nome, String cognome, Date dataDiNascita, String email, int numeroDiTelefono) {
        this.codiceFiscale = codiceFiscale;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public UtenteBean() {
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(int numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    @Override
    public String toString() {
        return "UtenteBean{" +
                "codiceFiscale='" + codiceFiscale + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", email='" + email + '\'' +
                ", numeroDiTelefono=" + numeroDiTelefono +
                '}';
    }
}
