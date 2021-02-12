package classes.model.interfaces;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.PrenotazioneBean;
import classes.model.dao.PrenotazioneModel;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PrenotazioneDaoInterfaceTest {
    private PrenotazioneDaoInterface prenotazioneDaoInterface = new PrenotazioneModel();
    private PrenotazioneBean prenotazioneBean = new PrenotazioneBean();

    @Test
    void doRetrieveByKey() throws SQLException, ObjectNotFoundException {
        assertNotNull(prenotazioneDaoInterface.doRetrieveByKey(1));
        assertNull(prenotazioneDaoInterface.doRetrieveByKey(48389572));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(prenotazioneDaoInterface.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("02-04-2022").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        prenotazioneBean.setDataPrenotazione(date);
        prenotazioneBean.setOra("11:00:00");
        prenotazioneBean.setCodiceFiscale("CPHMKL98H41I490J");
        prenotazioneBean.setConvalida(false);
        prenotazioneBean.setIdStruttura(1);
        prenotazioneBean.setIdOperazione(1);
        prenotazioneDaoInterface.doSave(prenotazioneBean);
    }

    @Test
    void doUpdate() throws SQLException {
        prenotazioneBean = prenotazioneDaoInterface.doRetrieveByKey(11);
        prenotazioneBean.setIdStruttura(2);
        prenotazioneDaoInterface.doUpdate(prenotazioneBean);
    }

    @Test
    void doDelete() throws SQLException {
        prenotazioneBean = prenotazioneDaoInterface.doRetrieveByKey(18);
        prenotazioneDaoInterface.doDelete(prenotazioneBean);
    }

    @Test
    void getUtentePrenotazioni() throws SQLException {
        assertNotNull(prenotazioneDaoInterface.getUtentePrenotazioni("CCCNTN98H02F839V"));
    }

    @Test
    void getCodaStruttura() throws SQLException {
        assertNotNull(prenotazioneDaoInterface.getCodaStruttura(1));
    }

    @Test
    void getOrariPrenotazione() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("01-22-2021").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertNotNull(prenotazioneDaoInterface.getOrariPrenotazione(1, 2, date));
    }
}