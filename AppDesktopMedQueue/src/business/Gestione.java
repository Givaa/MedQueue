package business;

import persistence.DataAccess;

public class Gestione {

  public static PrenotazioneBean accettaPrenotazione(int idOp, int idStruttura) {
    PrenotazioneBean p = DataAccess.serviPrenotazione(idOp, idStruttura);
    if (p != null) {
        DataAccess.deletePrenotazione(p.getId());
    } else {
        return null;
    }
    return p;
  }
}
