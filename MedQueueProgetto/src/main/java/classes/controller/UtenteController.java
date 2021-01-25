package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtenteController {
    private final UtenteModel utenteModel = new UtenteModel();

    @GetMapping("/operazione/{id}")
    UtenteBean getOperazioneById(@PathVariable String o) throws SQLException,
            ObjectNotFoundException {
        UtenteBean s = utenteModel.doRetrieveByKey(o);
        if (s != null) {
            return s;
        } else {
            throw new ObjectNotFoundException(s);
        }
    }

    @GetMapping("/operazioni")
    Collection<UtenteBean> getAllOperazioni(@RequestBody String order) throws SQLException {
        return utenteModel.doRetrieveAll(order);
    }

    @GetMapping("/newOperazione")
    void newOperazione(@RequestBody UtenteBean u) throws SQLException {
        utenteModel.doSave(u);
    }

    @GetMapping("/deleteOperazione")
    void deleteOperazione(@RequestBody UtenteBean u) throws SQLException {
        utenteModel.doDelete(u);
    }

    @GetMapping("/updateOperazione")
    void updateOperazione(@RequestBody UtenteBean u) throws SQLException {
        utenteModel.doUpdate(u);
    }
}
