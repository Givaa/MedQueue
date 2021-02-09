package business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AccessoTest {

  @Test
  void verificaCredenziali() {
    Accesso test = new Accesso();
    assertNull(test.verificaCredenziali("mario", null));
    assertNull(test.verificaCredenziali("MNDCMN97R22A509S", null));
    assertNull(test.verificaCredenziali("FLTNGL99A14L845J", "a"));
    assertNull(test.verificaCredenziali("FLTNGL99A14L845J", "angelo"));
    assertNotNull(test.verificaCredenziali("FLTNGL99A14L845J", "angelo99"));
  }
}
