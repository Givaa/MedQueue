package database;

public class Prenotazione {
    private int id;
    private String ora;
    private String data;
    private String struttura;
    private String operazione;

    public Prenotazione() { }
    public Prenotazione(int id, String ora, String data, String struttura, String operazione) {
        this.id = id;
        this.ora = ora;
        this.data = data;
        this.struttura = struttura;
        this.operazione = operazione;
    }

    //Get
    public int getId() { return id; }
    public String getOra() { return ora; }
    public String getData() { return data; }
    public String getStruttura() { return struttura; }
    public String getOperazione() { return operazione; }

    //Set
    public void setId(int id) { this.id = id; }
    public void setOra(String ora) { this.ora = ora; }
    public void setData(String data) { this.data = data; }
    public void setStruttura(String struttura) { this.struttura = struttura; }
    public void setOperazione(String operazione) { this.operazione = operazione; }

    //toString
    @Override
    public String toString() {
        return id+" "+ora+" "+data+" "+operazione;
    }
}
