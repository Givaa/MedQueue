package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.ImpiegatoBean;
import classes.model.dao.ImpiegatoModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImpiegatoController {

    private final ImpiegatoModel impiegatoModel = new ImpiegatoModel();

    @GetMapping("/impiegato/{cf}")
    ImpiegatoBean getImpiegatoByCodFis(@PathVariable String cf) throws SQLException,
            ObjectNotFoundException {
        ImpiegatoBean b = impiegatoModel.doRetrieveByKey(cf);

        if (b != null) {
            return b;
        } else {
            throw new ObjectNotFoundException(b);
        }
    }

    @GetMapping("/impiegati")
    Collection<ImpiegatoBean> getAllImpiegati(@RequestBody String order) throws SQLException {
        return impiegatoModel.doRetrieveAll(order);
    }

    @GetMapping("/newImpiegato")
    void newImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
        impiegatoModel.doSave(i);
    }

    @GetMapping("/deleteImpiegato")
    void deleteImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
        impiegatoModel.doDelete(i);
    }

    @GetMapping("/updateImpiegato")
    void updateImpiegato(@RequestBody ImpiegatoBean i) throws SQLException {
        impiegatoModel.doUpdate(i);
    }
 }
