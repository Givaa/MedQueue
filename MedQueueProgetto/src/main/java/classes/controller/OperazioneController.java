package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.OperazioneBean;
import classes.model.dao.OperazioneModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OperazioneController {

    private final OperazioneModel operazioneModel = new OperazioneModel();

    @GetMapping("/operazione/{id}")
    OperazioneBean getOperazioneById(@RequestBody String o) throws SQLException,
            ObjectNotFoundException {
        OperazioneBean op = operazioneModel.doRetrieveByKey(o);
        if (op != null) {
            return op;
        } else {
            throw new ObjectNotFoundException(op);
        }
    }

    @GetMapping("/operazioni")
    Collection<OperazioneBean> getAllOperazioni(@RequestBody String order) throws SQLException {
        return operazioneModel.doRetrieveAll(order);
    }

    @GetMapping("/newOperazione")
    void newOperazione(@RequestBody OperazioneBean o) throws SQLException {
        operazioneModel.doSave(o);
    }

    @GetMapping("/deleteOperazione")
    void deleteOperazione(@RequestBody OperazioneBean o) throws SQLException {
        operazioneModel.doDelete(o);
    }

    @GetMapping("/updateOperazione")
    void updateOperazione(@RequestBody OperazioneBean o) throws SQLException {
        operazioneModel.doUpdate(o);
    }
}
