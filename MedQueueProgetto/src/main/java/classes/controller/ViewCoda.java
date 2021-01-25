package classes.controller;

import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewCoda {
    public final PrenotazioneModel prenotazioneModel = new PrenotazioneModel();

    @GetMapping("/visualizzaCoda/{id}")
    Collection<PrenotazioneBean> getAllPrenotazioniByStruttura(@PathVariable int id)
            throws SQLException {
        return prenotazioneModel.getCodaStruttura(id);
    }
}
