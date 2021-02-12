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
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class UtenteControllerTest {
    private UtenteController utenteController = new UtenteController();
    private JsonParser parser = new JsonParser();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void getUtenteByCodFisc() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"idUtenteGet\":\"CCCNTN98H02F839V\"}");
        rootObject = jsonElement.getAsJsonObject();
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
    void getAllUtenti() throws SQLException {
        jsonElement = parser.parse("{\"ordineUtenti\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(utenteController.getAllUtenti(rootObject.toString()));
    }

    @Test
    void newUtente() throws SQLException, ParseException {
    jsonElement =
        parser.parse(
            "{\"newUtenteCf\":\"JPXZLH58S22L917F\",\""
                + "newUtentePassword\":\"Prova112!\",\""
                + "newUtenteCognome\":\"Provinocontrollerc\",\""
                + "newUtenteNome\":\"Provinocontrollern\",\""
                + "newUtentePhoneNumber\":\"3271219447\",\""
                + "newUtenteEmail\":\"provino@live.it\",\""
                + "newUtenteDataN\":\"1999-12-31\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(utenteController.newUtente(rootObject.toString()));
    }

    @Test
    void deleteUtente() throws SQLException {
        jsonElement = parser.parse("{\"deleteUtenteId\":\"JPXZLH58S22L917F\"}");
        rootObject = jsonElement.getAsJsonObject();
        utenteController.deleteUtente(rootObject.toString());
    }

    @Test
    void updateUtente() throws SQLException, ParseException {
        jsonElement = parser.parse(
                "{\"updateUtenteCf\":\"JPXZLH58S22L917F\",\"updateUtentePassword\":\"Prova123!\",\""
                        + "updateUtenteCognome\":\"ProvinoControllerC\",\""
                        + "updateUtenteNome\":\"ProvinoControllerN\",\""
                        + "updateUtentePhoneNumber\":\"3271219447\",\""
                        + "updateUtenteEmail\":\"provino@live.it\",\""
                        + "updateUtenteDataN\":\"1999-12-31\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(utenteController.updateUtente(rootObject.toString()));
    }
}