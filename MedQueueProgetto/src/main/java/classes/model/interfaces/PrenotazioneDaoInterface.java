package classes.model.interfaces;

import classes.model.bean.entity.PrenotazioneBean;

import java.sql.SQLException;
import java.util.Collection;

public interface PrenotazioneDaoInterface {
    PrenotazioneBean doRetrieveByKey(int id) throws SQLException;
    Collection<PrenotazioneBean> doRetrieveAll(String order) throws SQLException;
    void doSave(PrenotazioneBean param) throws SQLException;
    void doUpdate(PrenotazioneBean param) throws SQLException;
    void doDelete(PrenotazioneBean param) throws SQLException;
    Collection<PrenotazioneBean> getUtentePrenotazioni(String cf) throws SQLException;
    Collection<PrenotazioneBean> getCodaStruttura(int idStruttura) throws SQLException;
}
