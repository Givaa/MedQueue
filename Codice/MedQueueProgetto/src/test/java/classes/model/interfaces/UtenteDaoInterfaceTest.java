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

class UtenteDaoInterfaceTest {
    private UtenteDaoInterface utenteDaoInterface = new UtenteModel();
    private UtenteBean utenteBean = new UtenteBean();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(utenteDaoInterface.doRetrieveByKey("MNDCMN97R22A509S"));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(utenteDaoInterface.doRetrieveAll(""));
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
        utenteDaoInterface.doSave(utenteBean);
    }

    @Test
    void doUpdate() throws SQLException {
        utenteBean = utenteDaoInterface.doRetrieveByKey("CPHMKL98H41I490J");
        utenteBean.setCognome("ProvaNto");
        utenteDaoInterface.doUpdate(utenteBean);
    }

    @Test
    void doDelete() throws SQLException {
        utenteBean = utenteDaoInterface.doRetrieveByKey("CPHMKL98H41I490J");
        utenteDaoInterface.doDelete(utenteBean);
    }
}