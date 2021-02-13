package classes.model.interfaces;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.dao.OperazioneModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class OperazioneDaoTest {
    private OperazioneModel operazioneModel = new OperazioneModel();

    @Test
    void doRetrieveByKey() throws SQLException {
        assertNotNull(operazioneModel.doRetrieveByKey(1));
    }

    @Test
    void doRetrieveByTipo() throws SQLException {
        assertNotNull(operazioneModel.doRetrieveByTipo("Pagamento Ticket"));
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () ->{
            operazioneModel.doRetrieveByTipo("Tipo errato");
        });
    }

    @Test
    void doRetrieveAll() throws SQLException {
        assertNotNull(operazioneModel.doRetrieveAll(""));
    }

    @Test
    void doSave() throws SQLException {
        OperazioneBean operazioneBean = new OperazioneBean("Prova", "Inserita operazine di prova");
        operazioneModel.doSave(operazioneBean);
    }

    @Test
    void doUpdate() throws SQLException {
        OperazioneBean operazioneBean = operazioneModel.doRetrieveByTipo("Prova");
        operazioneBean.setTipoOperazione("ProvaModifica");
        operazioneModel.doUpdate(operazioneBean);
    }

    @Test
    void doDelete() throws SQLException {
        OperazioneBean operazioneBean = operazioneModel.doRetrieveByTipo("Da Rimuovere Dao");
        operazioneModel.doDelete(operazioneBean);
    }
}