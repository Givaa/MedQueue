package classes.model.bean.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OperazioneBean {

    @Id @GeneratedValue
    private int id;
    private String tipoOperazione, descrizione;
    private int idPrenotazione;

    public OperazioneBean(String tipoOperazione, String descrizione, int idPrenotazione) {
        this.tipoOperazione = tipoOperazione;
        this.descrizione = descrizione;
        this.idPrenotazione = idPrenotazione;
    }

    public OperazioneBean() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTipoOperazione() { return tipoOperazione; }

    public void setTipoOperazione(String tipoOperazione) { this.tipoOperazione = tipoOperazione; }

    public String getDescrizione() { return descrizione; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public int getIdPrenotazione() { return idPrenotazione; }

    public void setIdPrenotazione(int idPrenotazione) { this.idPrenotazione = idPrenotazione; }

    @Override
    public String toString() {
        return "OperazioneBean{" +
                "id=" + id +
                ", tipoOperazione='" + tipoOperazione + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", idPrenotazione=" + idPrenotazione +
                '}';
    }
}
