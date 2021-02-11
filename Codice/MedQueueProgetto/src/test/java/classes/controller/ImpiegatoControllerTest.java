package classes.controller;

import classes.controller.exception.InvalidKeyException;
import classes.controller.exception.ObjectNotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ImpiegatoControllerTest {

    @Test
    void getImpiegatoByCodFis() throws SQLException, InvalidKeyException {
        JsonObject finalRootObject;

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"cfImpiegatoRicerca\":\"ADRAMT99D13A587J\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        ImpiegatoController impiegatoController = new ImpiegatoController();
        assertNotNull(impiegatoController.getImpiegatoByCodFis(rootObject.toString()));

        jsonElement = parser.parse("{\"cfImpiegatoRicerca\":\"ZLFVMC57C47D087H\"}");
        rootObject = jsonElement.getAsJsonObject();
        finalRootObject = rootObject;
        JsonObject cfNonPresente = finalRootObject;
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, () -> {
            impiegatoController.getImpiegatoByCodFis(cfNonPresente.toString());
        });

        jsonElement = parser.parse("{\"cfImpiegatoRicerca\":\"CODICEFISCALEerrato\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject cfNonEsatto = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            impiegatoController.getImpiegatoByCodFis(cfNonEsatto.toString());
        });
    }

    @Test
    void getAllImpiegati() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"ordineImpiegati\":\"codiceFiscale\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        ImpiegatoController impiegatoController = new ImpiegatoController();
        assertNotNull(impiegatoController.getAllImpiegati(rootObject.toString()));

        jsonElement = parser.parse("{\"ordineImpiegati\":\" \"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject finalRootObject = rootObject;
        JsonObject ordineErrato = finalRootObject;
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> {
            impiegatoController.getImpiegatoByCodFis(ordineErrato.toString());
        });

        jsonElement = parser.parse("{\"ordineImpiegati\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        finalRootObject = rootObject;
        JsonObject ordineAssente = finalRootObject;
        assertNotNull(impiegatoController.getAllImpiegati(ordineAssente.toString()));
    }

    @Test
    void newImpiegato() throws SQLException, ParseException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"newImpiegatoDataN\":\"1967-02-18\",\"newImpiegatoNome\":\"Roberto\",\"newImpiegatoEmail\":\"divin@codino.it\",\"newImpiegatoCognome\":\"Baggio\",\"newImpiegatoPassword\":\"Italia90!\",\"newImpiegatoNumero\":\"937765256465\",\"newImpiegatoCf\":\"ZLFVMC57C47D087Z\",\"newImpiegatoIdStruttura\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        ImpiegatoController impiegatoController = new ImpiegatoController();
        assertNotNull(impiegatoController.newImpiegato(rootObject.toString()));
    }

    @Test
    void updateImpiegato() throws SQLException, ParseException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"updateImpiegatoDataN\":\"1967-02-18\",\"updateImpiegatoNome\":\"Boberto\",\"updateImpiegatoEmail\":\"divin@codino.it\",\"updateImpiegatoCognome\":\"Raggio\",\"updateImpiegatoPassword\":\"Italia90!\",\"updateImpiegatoPhoneNumber\":\"937765256465\",\"updateImpiegatoCf\":\"ZLFVMC57C47D087Z\",\"updateImpiegatoIdS\":\"1\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        ImpiegatoController impiegatoController = new ImpiegatoController();
        assertNotNull(impiegatoController.updateImpiegato(rootObject.toString()));
    }

    @Test
    void deleteImpiegato() throws SQLException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse("{\"deleteImpiegatoId\":\"ZLFVMC57C47D087Z\"}");
        JsonObject rootObject = jsonElement.getAsJsonObject();
        ImpiegatoController impiegatoController = new ImpiegatoController();
        impiegatoController.deleteImpiegato(rootObject.toString());
    }
}