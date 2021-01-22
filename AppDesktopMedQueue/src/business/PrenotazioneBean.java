package business;


public class PrenotazioneBean {
    private int id;
    private String data;
    private String time;
    private boolean convalida;
    private String codicefiscale;
    private int id_operazione;
    private int  id_struttura;

    public PrenotazioneBean(){}
    public PrenotazioneBean(int id, String data, String time, boolean convalida, String codicefiscale, int id_operazione, int id_struttura) {
        this.id = id;
        this.data = data;
        this.time = time;
        this.convalida = convalida;
        this.codicefiscale = codicefiscale;
        this.id_operazione = id_operazione;
        this.id_struttura = id_struttura;
    }

    //GET
    public int getId() { return id; }
    public String getData() { return data; }
    public String getTime() { return time; }
    public boolean isConvalida() { return convalida; }
    public String getCodicefiscale() { return codicefiscale; }
    public int getId_operazione() { return id_operazione; }
    public int getId_struttura() { return id_struttura; }

    //SET
    public void setId(int id) { this.id = id; }
    public void setData(String data) { this.data = data; }
    public void setTime(String time) { this.time = time; }
    public void setConvalida(boolean convalida) { this.convalida = convalida; }
    public void setCodicefiscale(String codicefiscale) { this.codicefiscale = codicefiscale; }
    public void setId_operazione(int id_operazione) { this.id_operazione = id_operazione; }
    public void setId_struttura(int id_struttura) { this.id_struttura = id_struttura; }
}
