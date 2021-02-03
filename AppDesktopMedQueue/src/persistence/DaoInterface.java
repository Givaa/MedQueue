package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;

import java.util.ArrayList;

/** Interfaccia che permette operazioni sul database. */
public interface DaoInterface {

    /**
     * Metodo che ci permettere di ottenere una prenotazione dal database in base all'id.
     *
     * @param id id della prenotazione che si vuole prelevare dalla collezione Prenotazione
     * @return prenotazione della collezione Prenotazione che ha come id, l'id passato come parametro al metodo
     *
     */
    public PrenotazioneBean getPrenotazione(int id);

    /**
     * Metodo che ci permette di ottenere una struttura ospedaliera dal database in base all'id.
     *
     * @param id id della struttura ospedaliera che si vuole prelevare dalla collezione Struttura
     * @return struttura ospedaliera della collezione Struttura che ha come id, l'id passato come parametro
     */
    public StrutturaBean getStruttura(int id);

    /**
     * Metodo che ci permette di ottenere un operazione per cui l'utente si puo prenotare in base
     * all'id.
     *
     * @param id id dell'operazione che si vuole prelevare dalla collezione Operazione
     * @return operazione della collezione Operazione che ha come id, l'id passato come parametro al metodo
     */
    public OperazioneBean getOperazione(int id);

    /**
     * Metodo che restituisce un impiegato di una struttura ospedaliera in base al codice fiscale.
     *
     * @param codicefiscale codice fiscale dell'impiegato che si vuole prelevare dalla collezione Impiegato
     * @return impiegato della collezione Impiegato che ha come codice fiscale, il codice fiscale passato come parametro al metodo
     */
    public ImpiegatoBean getImpiegato(String codicefiscale);

    /**
     * Metodo che restituisce tutte le operazioni per cui è possibile prenotarsi.
     *
     * @return tutte le operazioni che fanno parte della collezione Operazione
     */
    public ArrayList<OperazioneBean> getOperazioni();

    /**
     * Metodo per cancellare una prenotazione dal database in base all'id.
     *
     * @param id id della prenotazione della collezione Prenotazione da cancellare
     * @return 0 se la prenotazione non è stata cancellata, 1 se la prentoazione è stata cancellata
     */
    public int deletePrenotazione(int id);

    /**
     * Metodo che restituisce il numero di prenotazioni da servire in base all'id dell'oprazione e
     * l'id della struttura.
     *
     * @param idOperazione id dell'operazione della collezione Operazione
     * @param idStruttura id della struttura della collezione Struttura
     * @return size delle prenotazioni della collezione Prenotazione che hanno come idStruttura l'idStruttura
     * passato come parametro, come idOperazione l'idOperazione passato come parametro e con la convalida a true
     */
    public int numPrenotazioni(int idOperazione, int idStruttura);

    /**
     * Metodo che restituisce la prima operazione da servire in base all'ora della prenotazione.
     *
     * @param idOperazione id dell'operazione della collezione Operazione
     * @param idStruttura id della struttura della collezione Struttura
     * @return prenotazione della collezione Prenotazione che hanno come idStruttura l'idStruttura
     * passato come parametro, come idOperazione l'idOperazione passato come parametro, con convalida a true
     * ed e la prima in odrine d'orario
     *
     *
     */
    public PrenotazioneBean serviPrenotazione(int idOperazione, int idStruttura);
}
