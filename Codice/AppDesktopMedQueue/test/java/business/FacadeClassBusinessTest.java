package business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacadeClassBusinessTest {
    private static FacadeClassBusiness test;
    private static int idValido;
    private static int idNonValido;

    @BeforeAll
    public static void startUp(){
        test=new FacadeClassBusiness();
        idValido=1;
        idNonValido=0;
    }

    @Test
    void autenticazione() {
        assertNotNull(test.autenticazione("FLTNGL99A14L845J","angelo99"));
    }

    @Test
    void autenticazione_codiceFiscaleNonValido() {
        assertNull(test.autenticazione("FLTNGL99A14L84J","angelo99"));
    }

    @Test
    void autenticazione_passwordNonValida() {
        assertNull(test.autenticazione("FLTNGL99A14L84J",null));
    }

    @Test
    void accettaPrenotazione_idNonValido() {
        assertNull(test.accettaPrenotazione(idNonValido,idNonValido));
    }

    @Test
    void accettaPrenotazione_idValido() {
        assertNotNull(test.accettaPrenotazione(idValido,idValido));
    }

    @Test
    void getCode() {
       assertNotNull(test.getCode());
    }

    @Test
    void getCoda_idNonValido() {
        assertNull(test.getCoda(idNonValido));
    }

    @Test
    void getCoda_idValido() {
        assertNotNull(test.getCoda(idValido));
    }

    @Test
    void getSizeCoda_idNonValido() {
        assertEquals(0,test.getSizeCoda(idNonValido,idNonValido));
    }

    @Test
    void getSizeCoda_idValido() {
        assertNotEquals(0, test.getSizeCoda(idValido, idValido));
    }
}