package classes.controller;

import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.ImpiegatoBean;
import classes.model.dao.ImpiegatoModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collection;

@RestController
public class ImpiegatoController {

    private final ImpiegatoModel iM = new ImpiegatoModel();

    @GetMapping("/impiegato/{cf}")
    ImpiegatoBean getImpiegatoByCF(String cf) throws SQLException, ObjectNotFoundException{
        ImpiegatoBean b = iM.doRetrieveByKey(cf);

        if (b != null ) { return b; }
        else { throw new ObjectNotFoundException(b); }
    }

    @GetMapping("/impiegati")
    Collection<ImpiegatoBean> getAllImpiegati(String order) throws SQLException { return iM.doRetrieveAll(order); }

    @GetMapping("/newImpiegato")
    void newImpiegato(ImpiegatoBean i) throws SQLException { iM.doSave(i); }

    @GetMapping("/deleteImpiegato")
    void deleteImpiegato(ImpiegatoBean i) throws SQLException { iM.doDelete(i); }

    @GetMapping("/updateImpiegato")
    void updateImpiegato(ImpiegatoBean i) throws SQLException { iM.doUpdate(i); }
 }
