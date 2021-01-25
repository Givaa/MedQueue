package classes.controller;

import classes.controller.exception.AmbulatorioNotFoundException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.AmbulatoriBean;
import classes.model.dao.AmbulatoriModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.sql.SQLException;
import java.util.Collection;

@RestController
public class AmbulatorioController {
    private final AmbulatoriModel ambulatorioModel = new AmbulatoriModel();

    AmbulatorioController() { }

    //Prendi Ambulatorio tramite id
    @GetMapping("/ambulatorio/{id}")
    AmbulatoriBean getById(@PathVariable String id) throws SQLException, AmbulatorioNotFoundException {
        AmbulatoriBean a = ambulatorioModel.doRetrieveByKey(id);
        if ( a != null ) { return a; }
        else { throw new ObjectNotFoundException(a); }
    }

    @GetMapping("/ambulatori")
    Collection<AmbulatoriBean> getAll(String order) throws SQLException {
        return ambulatorioModel.doRetrieveAll(order);
    }

    @GetMapping("/newAmbulatorio")
    void newAmbulatorio(AmbulatoriBean a) throws SQLException { ambulatorioModel.doSave(a); }

    @GetMapping("/removeAmbulatorio")
    void deleteAmbulatorio(AmbulatoriBean a) throws SQLException { ambulatorioModel.doDelete(a); }

    @GetMapping("/updateAmbulatorio")
    void updateAmbulatorio(AmbulatoriBean a) throws SQLException { ambulatorioModel.doUpdate(a); }
}
