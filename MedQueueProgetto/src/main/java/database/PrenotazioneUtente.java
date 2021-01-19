package database;

public class PrenotazioneUtente {

    private int id;
    private String struttura;
    private String operazione;

    public PrenotazioneUtente() { }
    public PrenotazioneUtente(int id, String struttura, String operazione) {
        this.id = id;
        this.struttura = struttura;
        this.operazione = operazione;
    }

    //SET
    public void setId(int id) { this.id = id; }
    public void setStruttura(String struttura) { this.struttura = struttura; }
    public void setOperazione(String operazione) { this.operazione = operazione; }

    //GET
    public int getId() { return id; }
    public String getStruttura() { return struttura; }
    public String getOperazione() { return operazione; }

    //ToString

    @Override
    public String toString() { return id +" " + struttura + " "+operazione; }
}
