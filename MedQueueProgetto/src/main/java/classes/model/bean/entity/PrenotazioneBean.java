package classes.model.bean.entity;


import java.sql.Date;

public class PrenotazioneBean {

    private int id;
    private String ora;
    private Date dataPrenotazione;
    private String codiceFiscale;
    private int idOperazione;
    private int idStruttura;
    private boolean convalida;

    public PrenotazioneBean(String ora, Date data, String codiceFiscale, int idOp, int idSt, boolean convalida) {
        this.ora = ora;
        this.dataPrenotazione = data;
        this.codiceFiscale = codiceFiscale;
        this.idOperazione = idOp;
        this.idStruttura = idSt;
        this.convalida = convalida;
    }

    public PrenotazioneBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public Date getDataPrenotazione() { return dataPrenotazione; }

    public void setDataPrenotazione(Date data) {
        this.dataPrenotazione = data;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public int getIdOperazione() {
        return idOperazione;
    }

    public void setIdOperazione(int idOp) {
        this.idOperazione = idOp;
    }

    public int getIdStruttura() {
        return idStruttura;
    }

    public void setIdStruttura(int idSt) {
        this.idStruttura = idSt;
    }

    public boolean isConvalida() {
        return convalida;
    }

    public void setConvalida(boolean convalida) {
        this.convalida = convalida;
    }

    @Override
    public String toString() {
        return "PrenotazioneBean{" +
                "id=" + id +
                ", ora='" + ora + '\'' +
                ", dataPrenotazione=" + dataPrenotazione +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", idOperazione=" + idOperazione +
                ", idStruttura=" + idStruttura +
                ", convalida=" + convalida +
                '}';
    }
}