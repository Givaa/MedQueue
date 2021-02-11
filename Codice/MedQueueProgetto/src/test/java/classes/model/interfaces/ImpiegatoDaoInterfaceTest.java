package classes.model.interfaces;

import classes.model.bean.entity.ImpiegatoBean;
import classes.model.dao.ImpiegatoModel;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ImpiegatoDaoInterfaceTest {
    ImpiegatoDaoInterface impiegatoDaoInterface = new ImpiegatoModel();

    @Test
    void doRetrieveByKey() throws SQLException {
        impiegatoDaoInterface.doRetrieveByKey("ADRAMT99D13A587J");
    }

    @Test
    void doRetrieveAll() throws SQLException {
        impiegatoDaoInterface.doRetrieveAll("nome");
    }

    @Test
    void doSave() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("02-04-1999").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ImpiegatoBean impiegatoBean = new ImpiegatoBean("ADRAMT99D13A587D", "bohProva1!", "Ciao", "Ninno", date, "ciao@mario.it", "3271219447", 1);
        impiegatoDaoInterface.doSave(impiegatoBean);
    }

    @Test
    void doUpdate() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("02-04-1999").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ImpiegatoBean impiegatoBean = new ImpiegatoBean("ADRAMT99D13A587D", "bohProva1!", "Ciao", "Ninno", date, "ciao@mario.it", "3271219447", 1);
        impiegatoDaoInterface.doUpdate(impiegatoBean);
    }

    @Test
    void doDelete() throws SQLException {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date date = null;
        try {
            date = new Date(df.parse("02-04-1999").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ImpiegatoBean impiegatoBean = new ImpiegatoBean("ADRAMT99D13A587D", "bohProva1!", "Ciao", "Ninno", date, "ciao@mario.it", "3271219447", 1);
        impiegatoDaoInterface.doDelete(impiegatoBean);
    }
}