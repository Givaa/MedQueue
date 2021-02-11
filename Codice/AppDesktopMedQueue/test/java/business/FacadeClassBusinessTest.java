package business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacadeClassBusinessTest {
    private static FacadeClassBusiness test;

    @BeforeAll
    public static void startUp(){
        test=new FacadeClassBusiness();
    }

    @Test
    void getConnessione() {
        assertNotNull(test.getConnessione());
    }


    @Test
    void autenticazione() {
        assertNotNull(test.autenticazione("FLTNGL99A14L845J","angelo99"));
    }

    @Test
    void accettaPrenotazione() {
        assertNotNull((test.accettaPrenotazione(1,1)));
    }

    @Test
    void getCode() {
       assertNotNull(test.getCode());
    }

    @Test
    void getCoda() {
        assertNotNull(test.getCoda(1));
    }

    @Test
    void getSizeCoda() {
        assertNotEquals(0,test.getSizeCoda(1,1));
    }
}