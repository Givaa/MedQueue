package classes.controller;

import classes.controller.exception.AmbulatorioNotFoundException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.AmbulatoriBean;
import classes.model.dao.AmbulatoriModel;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AmbulatorioController {

    private final AmbulatoriModel ambulatorioModel = new AmbulatoriModel();

    //Prendi Ambulatorio tramite id
    @GetMapping("/ambulatorio/{id}")
    AmbulatoriBean getById(@PathVariable String id) throws SQLException,
            AmbulatorioNotFoundException {
        AmbulatoriBean a = ambulatorioModel.doRetrieveByKey(id);

        if (a != null) {
            return a;
        } else {
            throw new ObjectNotFoundException(a);
        }
    }

    @GetMapping("/ambulatori")
    Collection<AmbulatoriBean> getAll(@RequestBody String order) throws SQLException {
        return ambulatorioModel.doRetrieveAll(order);
    }

    @GetMapping("/newAmbulatorio")
    void newAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
        ambulatorioModel.doSave(a);
    }

    @GetMapping("/removeAmbulatorio")
    void deleteAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
        ambulatorioModel.doDelete(a);
    }

    @GetMapping("/updateAmbulatorio")
    void updateAmbulatorio(@RequestBody AmbulatoriBean a) throws SQLException {
        ambulatorioModel.doUpdate(a);
    }
}
