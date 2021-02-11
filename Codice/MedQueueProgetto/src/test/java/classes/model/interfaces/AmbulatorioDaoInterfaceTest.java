package classes.model.interfaces;

import classes.model.bean.entity.AmbulatoriBean;
import classes.model.dao.AmbulatoriModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AmbulatorioDaoInterfaceTest {
    private AmbulatorioDaoInterface ambulatorioDaoInterface = new AmbulatoriModel();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(ambulatorioDaoInterface.doRetrieveByKey(1));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(ambulatorioDaoInterface.doRetrieveAll("nome"));
    }

    @Test
    void doSave() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoju", 1);
        ambulatorioDaoInterface.doSave(ambulatoriBean);
    }

    @Test
    void doUpdate() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoja", 1);
        ambulatorioDaoInterface.doUpdate(ambulatoriBean);
    }

    @Test
    void doDelete() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoja", 1);
        ambulatorioDaoInterface.doDelete(ambulatoriBean);
    }
}