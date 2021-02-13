package classes.model.interfaces;

import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StrutturaDaoTest {
    private StrutturaModel strutturaModel = new StrutturaModel();
    private StrutturaBean strutturaBean = new StrutturaBean();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(strutturaModel.doRetrieveByKey(1));
        assertNull(strutturaModel.doRetrieveByKey(1900428));
    }

    @Test
    void doRetrieveByName() throws SQLException {
        assertNotNull(strutturaModel.doRetrieveByName("San Leonardo"));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(strutturaModel.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        strutturaBean.setIndirizzo("Via Ababudoju");
        strutturaBean.setNome("Ababudoju Center");
        strutturaBean.setNumeroDiTelefono("0874878584");
        strutturaModel.doSave(strutturaBean);
    }

    @Test
    void doUpdate() throws SQLException {
        strutturaBean = strutturaModel.doRetrieveByName("Ababudoju Center");
        strutturaBean.setNome("Rehab Center");
        strutturaBean.setIndirizzo("Via Shady Slim 12");
        strutturaModel.doUpdate(strutturaBean);
    }

    @Test
    void doDelete() throws SQLException {
        strutturaBean = strutturaModel.doRetrieveByName("Da Rimuovere Dao");
        strutturaModel.doDelete(strutturaBean);
    }
}