package business;

public class OperazioneBean {
    private int id;
    private String tipo_operazione;
    private String descrizione;

    public OperazioneBean(){}
    public OperazioneBean(int id, String tipo_operazione, String descrizione) {
        this.id = id;
        this.tipo_operazione = tipo_operazione;
        this.descrizione = descrizione;
    }

    //SET
    public void setId(int id) { this.id = id; }
    public void setTipo_operazione(String tipo_operazione) { this.tipo_operazione = tipo_operazione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    //GET
    public int getId() { return id; }
    public String getTipo_operazione() { return tipo_operazione; }
    public String getDescrizione() { return descrizione; }

}
