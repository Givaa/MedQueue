package classes.model.interfaces;

import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StrutturaDaoInterfaceTest {
    private StrutturaDaoInterface strutturaDaoInterface = new StrutturaModel();
    private StrutturaBean strutturaBean = new StrutturaBean();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(strutturaDaoInterface.doRetrieveByKey(1));
        assertNull(strutturaDaoInterface.doRetrieveByKey(1900428));
    }

    @Test
    void doRetrieveByName() throws SQLException {
        assertNotNull(strutturaDaoInterface.doRetrieveByName("santobono"));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(strutturaDaoInterface.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        strutturaBean.setIndirizzo("Via Ababudoju");
        strutturaBean.setNome("Ababudoju Center");
        strutturaBean.setNumeroDiTelefono("0874878584");
        strutturaDaoInterface.doSave(strutturaBean);
    }

    @Test
    void doUpdate() throws SQLException {
        strutturaBean = strutturaDaoInterface.doRetrieveByName("Ababudoju Center");
        strutturaBean.setNome("Rehab Center");
        strutturaBean.setIndirizzo("Via Shady Slim 12");
        strutturaDaoInterface.doUpdate(strutturaBean);
    }

    @Test
    void doDelete() throws SQLException {
        strutturaBean = strutturaDaoInterface.doRetrieveByName("Rehab Center");
        strutturaDaoInterface.doDelete(strutturaBean);
    }
}