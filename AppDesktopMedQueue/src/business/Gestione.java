package business;

import persistence.DataAccess;

public class Gestione {

    public static PrenotazioneBean accettaPrenotazione(int id_op,int id_struttura){
        PrenotazioneBean p=DataAccess.serviPrenotazione(id_op,id_struttura);
        if(p!=null)
            DataAccess.deletePrenotazione(p.getId());
        else
            return null;
        return p;
    }
}
