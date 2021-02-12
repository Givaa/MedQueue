package classes.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class LogInControllerTest {
    JsonParser parser = new JsonParser();
    LogInController logInController = new LogInController();

    @Test
    void login() throws SQLException {
        JsonElement jsonElement = parser.parse("{\"username\":\"CCCNTN98H02F839V\",\"password\":\"antonio98\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.login(rootObject.toString()));
    }

    @Test
    void signup() throws SQLException, ParseException {
        JsonElement jsonElement;
        jsonElement = parser.parse("{\"nomeNewUtente\":\"Bud\",\"cognomeNewUtente\":\"Spencer\",\"codFiscNewUtente\":\"DTSQJP55R30A119M\",\"passwordNewUtente\":\"Fagiolih1!\",\"numeroTelefonoNewUtente\":\"3271219447\",\"dataDiNascitaNewUtente\":\"31-10-1929\",\"emailNewUtente\":\"mazzate@paccari.it\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        assertNotNull(logInController.signup(rootObject.toString()));
    }
}