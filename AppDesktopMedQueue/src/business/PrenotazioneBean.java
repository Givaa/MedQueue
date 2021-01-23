package business;


public class PrenotazioneBean {
    private int id;
    private String data;
    private String time;
    private boolean convalida;
    private String codiceFiscale;
    private int idOperazione;
    private int idStruttura;

    public PrenotazioneBean(){}
    public PrenotazioneBean(int id, String data, String time, boolean convalida, String codiceFiscale, int idOperazione, int idStruttura) {
        this.id = id;
        this.data = data;
        this.time = time;
        this.convalida = convalida;
        this.codiceFiscale = codiceFiscale;
        this.idOperazione = idOperazione;
        this.idStruttura = idStruttura;
    }

    //GET
    public int getId() { return id; }
    public String getData() { return data; }
    public String getTime() { return time; }
    public boolean isConvalida() { return convalida; }
    public String getCodiceFiscale() { return codiceFiscale; }
    public int getIdOperazione() { return idOperazione; }
    public int getIdStruttura() { return idStruttura; }

    //SET
    public void setId(int id) { this.id = id; }
    public void setData(String data) { this.data = data; }
    public void setTime(String time) { this.time = time; }
    public void setConvalida(boolean convalida) { this.convalida = convalida; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }
    public void setIdOperazione(int idOperazione) { this.idOperazione = idOperazione; }
    public void setIdStruttura(int idStruttura) { this.idStruttura = idStruttura; }
}
