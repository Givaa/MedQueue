package classes.controller;

import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StrutturaControllerTest {
    private JsonParser parser = new JsonParser();
    private StrutturaController strutturaController = new StrutturaController();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void getStrutturaById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"id\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(strutturaController.getStrutturaById(rootObject.toString()));

        jsonElement = parser.parse("{\"id\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject finalRootObject = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            strutturaController.getStrutturaById(finalRootObject.toString());
        });
    }

    @Test
    void getStrutturaByNome() throws SQLException {
        jsonElement = parser.parse("{\"nomeStrutturaGet\":\"indirizzo\"}");
        rootObject = jsonElement.getAsJsonObject();
        strutturaController.getStrutturaByNome(rootObject.toString());

        jsonElement = parser.parse("{\"nomeStrutturaGet\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertThrows(ObjectNotFoundException.class, () -> {
            strutturaController.getStrutturaByNome(rootObject.toString());
        });
    }

    @Test
    void getAllStrutture() throws SQLException {
        jsonElement = parser.parse("{\"ordineStrutture\":\"indirizzo\"}");
        rootObject = jsonElement.getAsJsonObject();
        strutturaController.getAllStrutture(rootObject.toString());

        jsonElement = parser.parse("{\"ordineStrutture\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(strutturaController.getAllStrutture(rootObject.toString()));
    }

    @Test
    void newStruttura() throws SQLException {
        jsonElement = parser.parse(
                "{\"newStrutturaNome\":\"StrutturaProva\"," +
                        "\"newStrutturaIndirizzo\":\"Via Prova 14\"," +
                        "\"newStrutturaNumeroCell\":\"0878878584\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(strutturaController.newStruttura(rootObject.toString()));
    }

    @Test
    void deleteStruttura() throws SQLException {
        jsonElement = parser.parse("{\"deleteStrutturaId\":\"5\"}");
        rootObject = jsonElement.getAsJsonObject();
        strutturaController.deleteStruttura(rootObject.toString());
    }

    @Test
    void updateStruttura() throws SQLException {
        jsonElement = parser.parse(
                "{\"updateStrutturaId\":\"5\"," +
                        "\"updateStrutturaNome\":\"Struttura Prova\"," +
                        "\"updateStrutturaInd\":\"Via Prova 14\"," +
                        "\"updateStrutturaNumeroCell\":\"0878878584\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(strutturaController.updateStruttura(rootObject.toString()));
    }
}