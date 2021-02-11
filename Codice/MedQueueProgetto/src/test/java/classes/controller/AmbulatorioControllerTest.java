package classes.controller;

import classes.controller.exception.InvalidKeyException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AmbulatorioControllerTest {

    @Test
    void getById() throws SQLException, InvalidKeyException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"id\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        AmbulatorioController ambulatorioController = new AmbulatorioController();
        assertNotNull(ambulatorioController.getById(rootObject.toString()));

        jsonElement = parser.parse("{\"id\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject finalRootObject = rootObject;
        InvalidKeyException i = assertThrows(InvalidKeyException.class, () -> {
            ambulatorioController.getById(finalRootObject.toString());
        });
    }

    @Test
    void getAllAmbulatori() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"ordineAmbulatori\":\"idStruttura\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        AmbulatorioController ambulatorioController = new AmbulatorioController();
        ambulatorioController.getAllAmbulatori(rootObject.toString());

        jsonElement = parser.parse("{\"ordineAmbulatori\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(ambulatorioController.getAllAmbulatori(rootObject.toString()));
    }

    @Test
    void newAmbulatorio() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(
                "{\"newAmbulatorioNome\":\"ProvaAiuto\",\"newAmbulatorioIdS\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        AmbulatorioController ambulatorioController = new AmbulatorioController();
        assertTrue(ambulatorioController.newAmbulatorio(rootObject.toString()));
    }

    @Test
    void deleteAmbulatorio() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"idAmbulatorioRemove\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        AmbulatorioController ambulatorioController = new AmbulatorioController();
        ambulatorioController.deleteAmbulatorio(rootObject.toString());
    }

    @Test
    void updateAmbulatorio() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(
                "{\"idAmbulatorioUpdate\":\"1\",\"AmbulatoriUpdateName\":\"ProvaCiao\",\"AmbulatoriUpdateIdStruttura\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        AmbulatorioController ambulatorioController = new AmbulatorioController();
        assertTrue(ambulatorioController.updateAmbulatorio(rootObject.toString()));
    }
}