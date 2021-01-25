package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrenotazioneController {

    private final PrenotazioneModel prenotazioneModel = new PrenotazioneModel();

    @GetMapping("/operazione/{id}")
    PrenotazioneBean getOperazioneById(String o) throws SQLException,
            ObjectNotFoundException {
        PrenotazioneBean p = prenotazioneModel.doRetrieveByKey(o);
        if (p != null) {
            return p;
        } else {
            throw new ObjectNotFoundException(p);
        }
    }

    @GetMapping("/operazioni")
    Collection<PrenotazioneBean> getAllOperazioni(String order) throws SQLException {
        return prenotazioneModel.doRetrieveAll(order);
    }

    @GetMapping("/newOperazione")
    void newOperazione(PrenotazioneBean p) throws SQLException {
        prenotazioneModel.doSave(p);
    }

    @GetMapping("/deleteOperazione")
    void deleteOperazione(PrenotazioneBean p) throws SQLException {
        prenotazioneModel.doDelete(p);
    }

    @GetMapping("/updateOperazione")
    void updateOperazione(PrenotazioneBean p) throws SQLException {
        prenotazioneModel.doUpdate(p);
    }

    @GetMapping("/prenotazioniUtente/{cf}")
    Collection<PrenotazioneBean> getPrenotazioniByCodFisc(String cf) throws SQLException {
        return prenotazioneModel.getUtentePrenotazioni(cf);
    }
}
