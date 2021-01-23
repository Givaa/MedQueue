package business;

public class OperazioneBean {
    private int id;
    private String tipoOperazione;
    private String descrizione;

    public OperazioneBean(){}
    public OperazioneBean(int id, String tipoOperazione, String descrizione) {
        this.id = id;
        this.tipoOperazione = tipoOperazione;
        this.descrizione = descrizione;
    }

    //SET
    public void setId(int id) { this.id = id; }
    public void setTipoOperazione(String tipoOperazione) { this.tipoOperazione = tipoOperazione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    //GET
    public int getId() { return id; }
    public String getTipoOperazione() { return tipoOperazione; }
    public String getDescrizione() { return descrizione; }

}
