package classes.model.interfaces;

import classes.model.bean.entity.AmbulatoriBean;
import classes.model.dao.AmbulatoriModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AmbulatorioDaoTest {
    private AmbulatoriModel ambulatorioModel = new AmbulatoriModel();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(ambulatorioModel.doRetrieveByKey(1));
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(ambulatorioModel.doRetrieveAll("nome"));
    }

    @Test
    void doSave() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoju", 1);
        ambulatorioModel.doSave(ambulatoriBean);
    }

    @Test
    void doUpdate() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoja", 1);
        ambulatorioModel.doUpdate(ambulatoriBean);
    }

    @Test
    void doDelete() throws SQLException {
        AmbulatoriBean ambulatoriBean = new AmbulatoriBean("Ababudoja", 1);
        ambulatorioModel.doDelete(ambulatoriBean);
    }
}