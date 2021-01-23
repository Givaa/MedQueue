package classes.model.bean.entity;

import java.sql.Date;


public class ImpiegatoBean {

    private String codiceFiscale;
    private String password, nome, cognome;
    private Date dataDiNascita;
    private String indirizzoEmail, numeroDiTelefono;

    public ImpiegatoBean(String codiceFiscale, String password, String nome, String cognome, Date dataDiNascita, String indirizzoEmail, String numeroDiTelefono) {
        this.codiceFiscale = codiceFiscale;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.indirizzoEmail = indirizzoEmail;
        this.numeroDiTelefono = numeroDiTelefono;
    }

    public ImpiegatoBean() {
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

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }

    public String getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(String numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    @Override
    public String toString() {
        return "ImpiegatoBean{" +
                "codiceFiscale='" + codiceFiscale + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", indirizzoEmail='" + indirizzoEmail + '\'' +
                ", numeroDiTelefono='" + numeroDiTelefono + '\'' +
                '}';
    }
}
