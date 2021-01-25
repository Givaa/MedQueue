package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.StrutturaBean;
import classes.model.dao.StrutturaModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StrutturaController {
    private final StrutturaModel strutturaModel = new StrutturaModel();

    @GetMapping("/operazione/{id}")
    StrutturaBean getOperazioneById(@PathVariable String o) throws SQLException,
            ObjectNotFoundException {
        StrutturaBean s = strutturaModel.doRetrieveByKey(o);
        if (s != null) {
            return s;
        } else {
            throw new ObjectNotFoundException(s);
        }
    }

    @GetMapping("/operazioni")
    Collection<StrutturaBean> getAllOperazioni(@RequestBody String order) throws SQLException {
        return strutturaModel.doRetrieveAll(order);
    }

    @GetMapping("/newOperazione")
    void newOperazione(@RequestBody StrutturaBean s) throws SQLException {
        strutturaModel.doSave(s);
    }

    @GetMapping("/deleteOperazione")
    void deleteOperazione(@RequestBody StrutturaBean s) throws SQLException {
        strutturaModel.doDelete(s);
    }

    @GetMapping("/updateOperazione")
    void updateOperazione(@RequestBody StrutturaBean s) throws SQLException {
        strutturaModel.doUpdate(s);
    }
}
