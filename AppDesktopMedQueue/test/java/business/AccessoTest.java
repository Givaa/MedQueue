package business;

import bean.ImpiegatoBean;
import eccezioni.InvalidAccesException;
import eccezioni.InvalidKeyException;
import org.junit.jupiter.api.Test;
import presentazione.ControlPanelView;

import static org.junit.jupiter.api.Assertions.*;

class AccessoTest {

    @Test
    void verificaCredenziali() {
        Accesso test=new Accesso();
        assertNull(test.verificaCredenziali("mario",null));
        assertNull(test.verificaCredenziali("MNDCMN97R22A509S",null));
        assertNull(test.verificaCredenziali("FLTNGL99A14L845J","a"));
        assertNull(test.verificaCredenziali("FLTNGL99A14L845J","angelo"));
        assertNotNull(test.verificaCredenziali("FLTNGL99A14L845J","angelo99"));
    }

}