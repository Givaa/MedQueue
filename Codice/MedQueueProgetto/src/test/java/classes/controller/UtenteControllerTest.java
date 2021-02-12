package classes.controller;

import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import classes.model.bean.entity.UtenteBean;
import classes.model.dao.UtenteModel;
import classes.model.interfaces.UtenteDaoInterface;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UtenteControllerTest {
    private UtenteController utenteController = new UtenteController();
    private JsonParser parser = new JsonParser();

    @Test
    void getUtenteByCodFisc() throws SQLException, InvalidKeyException {
        JsonElement jsonElement = parser.parse("{\"idUtenteGet\":\"CCCNTN98H02F839V\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        assertNotNull(utenteController.getUtenteByCodFisc(rootObject.toString()));

        jsonElement = parser.parse("{\"idUtenteGet\":\"ZZZZZZ98Z02Z839Z\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject oggettoNonPresente = rootObject;
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            utenteController.getUtenteByCodFisc(oggettoNonPresente.toString());
        });

        jsonElement = parser.parse("{\"idUtenteGet\":\"11111198Z02Z839Z\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject oggettoNonValido = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            utenteController.getUtenteByCodFisc(oggettoNonValido.toString());
        });
    }

    @Test
    void getAllUtenti() {
    }

    @Test
    void newUtente() {
    }

    @Test
    void deleteUtente() {
    }

    @Test
    void updateUtente() {
    }
}