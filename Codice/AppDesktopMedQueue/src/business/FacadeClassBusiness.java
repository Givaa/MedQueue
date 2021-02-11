package business;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;

import java.sql.Connection;
import java.util.ArrayList;

public class FacadeClassBusiness {

    private AccessoInterface accesso;
    private GestioneInterface gestione;
    private ConnessioneInterface connessione;

    public FacadeClassBusiness(){
        accesso=new Accesso();
        gestione=new Gestione();
        connessione= new Connessione();
    }

    public Connection getConnessione(){
        return connessione.connect();
    }

    public void disconnect(Connection con){
        connessione.disconnect(con);
    }

    public ImpiegatoBean autenticazione(String cf,String pw){
        return accesso.verificaCredenziali(cf,pw);
    }

    public PrenotazioneBean accettaPrenotazione(Integer idStruttura,Integer idOperazione){
        return gestione.accettaPrenotazione(idStruttura,idOperazione);
    }

    public ArrayList<OperazioneBean> getCode(){
        return gestione.getListaOperazioni();
    }

    public OperazioneBean getCoda(int idOperazione){
        return gestione.getOperazione(idOperazione);
    }

    public int getSizeCoda(int idOperazione,int idStruttura){
        return gestione.getNumPrenotazioni(idOperazione,idStruttura);
    }



}
