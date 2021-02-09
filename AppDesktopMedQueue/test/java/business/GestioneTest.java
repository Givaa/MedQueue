package business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestioneTest {
    private static Gestione test;
    private static int idValido;
    private static int IdValidoNonTrovato;
    private static int idNonValido;

    @BeforeAll
    public static void setUp(){
        test=new Gestione();
        idValido=1;
        idNonValido=0;
        IdValidoNonTrovato=99;
    }

    @Test
    void accettaPrenotazione() {
        assertNull(test.accettaPrenotazione(idNonValido,idNonValido));
        assertNull(test.accettaPrenotazione(idNonValido,idValido));
        assertNull(test.accettaPrenotazione(idValido,idNonValido));
        assertNull(test.accettaPrenotazione(IdValidoNonTrovato,IdValidoNonTrovato));
        while (test.getNumPrenotazioni(idValido,idValido)>0)
            assertNotEquals(null,test.accettaPrenotazione(idValido,idValido));
        assertNull(test.accettaPrenotazione(idValido,idValido));
    }

    @Test
    void getNumPrenotazioni() {
        assertEquals(0,test.getNumPrenotazioni(idNonValido,idNonValido));
        assertEquals(0,test.getNumPrenotazioni(idNonValido,idValido));
        assertEquals(0,test.getNumPrenotazioni(idValido,idNonValido));
        assertEquals(0,test.getNumPrenotazioni(IdValidoNonTrovato,IdValidoNonTrovato));
        assertNotEquals(0,test.getNumPrenotazioni(idValido,idValido));
    }


    @Test
    void getOperazione() {
        assertNull(test.getOperazione(idNonValido));
        assertNull(test.getOperazione(IdValidoNonTrovato));
        assertNotEquals(null,test.getOperazione(idValido));
    }
}