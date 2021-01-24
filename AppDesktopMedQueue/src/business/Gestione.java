package business;

import persistence.DataAccess;

public class Gestione {

    public static PrenotazioneBean accettaPrenotazione(int id_op,int id_struttura){
        return DataAccess.serviPrenotazione(id_op,id_struttura);
    }
}
