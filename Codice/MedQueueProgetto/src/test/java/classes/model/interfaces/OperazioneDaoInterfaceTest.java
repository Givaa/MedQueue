package classes.model.interfaces;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.dao.OperazioneModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OperazioneDaoInterfaceTest {
    private OperazioneDaoInterface operazioneDaoInterface = new OperazioneModel();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(operazioneDaoInterface.doRetrieveByKey(1));
    }

    @Test
    void doRetrieveByTipo() throws SQLException {
        assertNotNull(operazioneDaoInterface.doRetrieveByTipo("Pagamento Ticket"));
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () ->{
            operazioneDaoInterface.doRetrieveByTipo("Tipo errato");
        });
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(operazioneDaoInterface.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        OperazioneBean operazioneBean = new OperazioneBean("Prova", "Inserita operazine di prova");
        operazioneDaoInterface.doSave(operazioneBean);
    }

    @Test
    void doUpdate() throws SQLException {
        OperazioneBean operazioneBean = operazioneDaoInterface.doRetrieveByTipo("Prova");
        operazioneBean.setTipoOperazione("ProvaModifica");
        operazioneDaoInterface.doUpdate(operazioneBean);
    }

    @Test
    void doDelete() throws SQLException {
        OperazioneBean operazioneBean = operazioneDaoInterface.doRetrieveByTipo("ProvaModifica");
        operazioneDaoInterface.doDelete(operazioneBean);
    }
}