package classes.controller;

import classes.controller.exception.InvalidKeyException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PrenotazioneControllerTest {
    private JsonParser parser = new JsonParser();
    private PrenotazioneController prenotazioneController = new PrenotazioneController();
    private JsonElement jsonElement;
    private JsonObject rootObject;

    @Test
    void getPrenotazioneById() throws SQLException, InvalidKeyException {
        jsonElement = parser.parse("{\"idPrenotazioneGet\":\"1\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getPrenotazioneById(rootObject.toString()));

        jsonElement = parser.parse("{\"idPrenotazioneGet\":\"0\"}");
        rootObject = jsonElement.getAsJsonObject();
        JsonObject finalRootObject = rootObject;
        InvalidKeyException invalidKeyException = assertThrows(InvalidKeyException.class, () -> {
            prenotazioneController.getPrenotazioneById(finalRootObject.toString());
        });
    }

    @Test
    void getAllPrenotazioni() throws SQLException {
        jsonElement = parser.parse("{\"ordinePrenotazioni\":\"data\"}");
        rootObject = jsonElement.getAsJsonObject();
        prenotazioneController.getAllPrenotazioni(rootObject.toString());

        jsonElement = parser.parse("{\"ordinePrenotazioni\":\"\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getAllPrenotazioni(rootObject.toString()));
    }

    @Test
    void newPrenotazione() throws SQLException, ParseException {
        jsonElement = parser.parse(
                "{\"newPrenotazioniCf\":\"MNDCMN97R22A509S\"," +
                        "\"newPrenotazioniOra\":\"15:30:00\"," +
                        "\"newPrenotazioniIdOp\":\"1\"," +
                        "\"newPrenotazioniIdS\":\"1\"," +
                        "\"newPrenotazioneData\":\"2021-02-12\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.newPrenotazione(rootObject.toString()));
    }

    @Test
    void deletePrenotazione() throws SQLException {
        jsonElement = parser.parse("{\"deletePrenotazioniId\":\"11\"}");
        rootObject = jsonElement.getAsJsonObject();
        prenotazioneController.deletePrenotazione(rootObject.toString());
    }

    @Test
    void updatePrenotazione() throws SQLException, ParseException {
        jsonElement = parser.parse(
                "{\"updatePrenotazioniId\":\"11\"," +
                        "\"updatePrenotazioniCf\":\"MNDCMN97R22A509S\"," +
                        "\"updatePrenotazioniOra\":\"12:00:00\"," +
                        "\"updatePrenotazioniIdOp\":\"1\"," +
                        "\"updatePrenotazioniIdS\":\"1\"," +
                        "\"updatePrenotazioneData\":\"2022-02-11\","+
                        "\"updatePrenotazioneConvalida\":\"true\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.updatePrenotazione(rootObject.toString()));
    }

    @Test
    void getPrenotazioniByCodFisc() throws SQLException {
        jsonElement = parser.parse("{\"getPrenotazioniByCf\":\"MNDCMN97R22A509S\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getPrenotazioniByCodFisc(rootObject.toString()));
    }

    @Test
    void convalidaPrenotazione() throws SQLException, ParseException {
        jsonElement = parser.parse("{\"convalidaPrenotazione\":\"MNDCMN97R22A509S\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertTrue(prenotazioneController.convalidaPrenotazione(rootObject.toString()));
    }

    @Test
    void getOrariDisponibili() throws SQLException, ParseException {
        jsonElement = parser.parse("{\"idStruttura\":\"1\","
                + "\"idOperazione\":\"1\","
                + "\"PrenotazioneData\":\"2021-01-22\"}");
        rootObject = jsonElement.getAsJsonObject();
        assertNotNull(prenotazioneController.getOrariDisponibili(rootObject.toString()));
    }
}