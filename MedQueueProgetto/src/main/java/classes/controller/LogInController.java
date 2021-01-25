package classes.controller;

import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController extends HttpServlet {

    private final UtenteModel um = new UtenteModel();

    @GetMapping("/login")
    boolean login(@RequestBody String username, @RequestBody String password) throws SQLException {
        UtenteBean a = um.doRetrieveByKey(username);

        return a.getPassword().equals(password);
    }
}
