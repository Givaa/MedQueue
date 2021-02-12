package classes.controller;

import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AmbulatorioControllerTest {
    private JsonParser parser = new JsonParser();
    private AmbulatorioController ambulatorioController = new AmbulatorioController();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void getById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"id\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(ambulatorioController.getById(rootObject.toString()));

        jsonElement = parser.parse("{\"id\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        rootObject = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            ambulatorioController.getById(rootObject.toString());
        });

        jsonElement = parser.parse("{\"id\":\"54564163\"}");
        rootObject = jsonElement.getAsJsonObject();
        rootObject = rootObject;
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            ambulatorioController.getById(rootObject.toString());
        });
    }

    @Test
    void getAllAmbulatori() throws SQLException {
        jsonElement = parser.parse("{\"ordineAmbulatori\":\"idStruttura\"}");
        rootObject = jsonElement.getAsJsonObject();
        ambulatorioController.getAllAmbulatori(rootObject.toString());

        jsonElement = parser.parse("{\"ordineAmbulatori\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(ambulatorioController.getAllAmbulatori(rootObject.toString()));
    }

    @Test @Before
    void newAmbulatorio() throws SQLException {
        jsonElement = parser.parse(
                "{\"newAmbulatorioNome\":\"ProvaAiuto\",\"newAmbulatorioIdS\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(ambulatorioController.newAmbulatorio(rootObject.toString()));
    }

    @Test @Before
    void updateAmbulatorio() throws SQLException {
        jsonElement = parser.parse(
                "{\"idAmbulatorioUpdate\":\"11\",\""
                        + "AmbulatoriUpdateName\":\"ProvaCiao\",\""
                        + "AmbulatoriUpdateIdStruttura\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(ambulatorioController.updateAmbulatorio(rootObject.toString()));
    }

    @Test @After
    void deleteAmbulatorio() throws SQLException {
        jsonElement = parser.parse("{\"idAmbulatorioRemove\":\"11\"}");
        rootObject = jsonElement.getAsJsonObject();
        ambulatorioController.deleteAmbulatorio(rootObject.toString());
    }
}