package classes.controller;

import classes.controller.exception.InvalidKeyException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class AmbulatorioControllerTest {

    @Test
    void getById() throws SQLException, InvalidKeyException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"id\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        String id = rootObject.get("id").getAsString();
        System.out.println(id);
        AmbulatorioController a = new AmbulatorioController();
        a.getById(rootObject.toString());
        jsonElement = parser.parse("{\"id\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        a.getById(rootObject.toString());
        id = rootObject.get("id").getAsString();
        System.out.println(id);
    }

    @Test
    void getAllAmbulatori() {
    }

    @Test
    void newAmbulatorio() {
    }

    @Test
    void deleteAmbulatorio() {
    }

    @Test
    void updateAmbulatorio() {
    }
}