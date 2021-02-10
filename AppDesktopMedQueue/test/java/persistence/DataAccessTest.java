package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {
  private static DataAccess test;
  private static int idValido;
  private static int idNonValido;
  private static int idValidoNonTrovato;

  @BeforeAll
  public static void setUp() {
    test = new DataAccess();
    idValido = 1;
    idNonValido = 0;
    idValidoNonTrovato = 99;
  }

  @Test
  void getPrenotazione() {
    assertNull(test.getPrenotazione(idNonValido));
    assertNull(test.getPrenotazione(idValidoNonTrovato));
    String str = "2021-01-22";
    String str1 = "13:30:00";
    Date date = Date.valueOf(str);
    Time time = Time.valueOf(str1);
    PrenotazioneBean p = new PrenotazioneBean(1, date, time, true, "MNDCMN97R22A509S", 1, 1);
    assertEquals(p, test.getPrenotazione(idValido));
  }

  @Test
  void getStruttura() {
    assertNull(test.getStruttura(idNonValido));
    assertNull(test.getStruttura(idValidoNonTrovato));
    StrutturaBean s =
        new StrutturaBean(1, "santobono", "Via della Croce Rossa n. 8 Napoli (NA)", "0812205111");
    assertEquals(s, test.getStruttura(idValido));
  }

  @Test
  void getOperazione() {
    assertNull(test.getOperazione(idNonValido));
    assertNull(test.getOperazione(idValidoNonTrovato));
    OperazioneBean o =
        new OperazioneBean(1, "Pagamento Ticket", "Pagamento Ticket per visita medica");
    assertEquals(o, test.getOperazione(1));
  }

  @Test
  void getImpiegato() {
    assertNull(test.getImpiegato("mario"));
    assertNull(test.getImpiegato("FLTNGL99A14L845S"));
    String str = "1999-01-14";
    Date date = Date.valueOf(str);
    ImpiegatoBean impiegato =
        new ImpiegatoBean(
            "FLTNGL99A14L845J",
            "angelo99",
            "angelo",
            "afeltra",
            date,
            "a.afeltra12@studenti.unisa.it",
            "3394487295",
            1);
    assertEquals(impiegato, test.getImpiegato("FLTNGL99A14L845J"));
  }

  @Test
  void deletePrenotazione() {
    assertEquals(0, test.deletePrenotazione(idNonValido));
    assertEquals(0, test.deletePrenotazione(idValidoNonTrovato));
    assertEquals(1, test.deletePrenotazione(idValido));
    assertEquals(0, test.deletePrenotazione(idValido));
  }

  @Test
  void numPrenotazioni() {
    assertEquals(0, test.numPrenotazioni(idNonValido, idNonValido));
    assertEquals(0, test.numPrenotazioni(idNonValido, idValido));
    assertEquals(0, test.numPrenotazioni(idValido, idNonValido));
    assertEquals(0, test.numPrenotazioni(idValidoNonTrovato, idValidoNonTrovato));
    assertNotEquals(0, test.numPrenotazioni(idValido, idValido));
  }

  @Test
  void serviPrenotazione() {
    assertNull(test.serviPrenotazione(idNonValido, idNonValido));
    assertNull(test.serviPrenotazione(idNonValido, idValido));
    assertNull(test.serviPrenotazione(idValido, idNonValido));
    assertNull(test.serviPrenotazione(idValidoNonTrovato, idValidoNonTrovato));
    assertNotEquals(null, test.serviPrenotazione(idValido, idValido));
  }
}
