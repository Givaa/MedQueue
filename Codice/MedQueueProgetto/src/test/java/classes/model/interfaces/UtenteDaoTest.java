package classes.model.interfaces;

import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class UtenteDaoTest {
    private UtenteModel utenteModel = new UtenteModel();
    private UtenteBean utenteBean = new UtenteBean();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(utenteModel.doRetrieveByKey("MNDCMN97R22A509S"));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(utenteModel.doRetrieveAll(""));
        assertNotNull(utenteModel.doRetrieveAll("nome"));
    }

    @Test
    void doSave() throws SQLException {
        utenteBean.setEmail("thisProva@prova.it");
        utenteBean.setNome("Provino");
        utenteBean.setCognome("Prova");
        utenteBean.setPassword("Prova1!");
        utenteBean.setCodiceFiscale("CPHMKL98H41I490J");
        utenteBean.setNumeroDiTelefono("3271219447");
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("02-04-1981").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        utenteBean.setDataDiNascita(date);
        utenteModel.doSave(utenteBean);
    }

    @Test
    void doUpdate() throws SQLException {
        utenteBean = utenteModel.doRetrieveByKey("CRLNTN92S15H703Q");
        utenteBean.setNome("Tony");
        utenteModel.doUpdate(utenteBean);
    }

    @Test
    void doDelete() throws SQLException {
        utenteBean = utenteModel.doRetrieveByKey("CPHMKL98H41I490J");
        utenteModel.doDelete(utenteBean);
    }
}