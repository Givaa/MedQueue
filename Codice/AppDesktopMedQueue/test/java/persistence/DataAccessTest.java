package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {
  private static DataAccess test;
  private static int idValido;
  private static int idValido2;
  private static int idNonValido;
  private static int idValidoNonTrovato;

  @BeforeAll
  public static void setUp() {
    test = new DataAccess();
    idValido = 1;
    idValido2=2;
    idNonValido = 0;
    idValidoNonTrovato = 99;
  }

  @Test
  void getPrenotazioione_idNonValido() {
    assertNull(test.getPrenotazione(idNonValido));
  }

  @Test
  void getPrenotazioione_idValidoNonTrovato() {
    assertNull(test.getPrenotazione(idValidoNonTrovato));
  }

  @Test
  void getPrenotazioione_idValido() {
    String str = "2021-01-22";
    String str1 = "13:30:00";
    Date date = Date.valueOf(str);
    Time time = Time.valueOf(str1);
    PrenotazioneBean p = new PrenotazioneBean(1, date, time, true, "MNDCMN97R22A509S", 1, 1);
    assertEquals(p, test.getPrenotazione(idValido));
  }

  @Test
  void getStruttura_idNonValido() {
    assertNull(test.getStruttura(idNonValido));
  }

  @Test
  void getStruttura_idValidoNonTrovato() {
    assertNull(test.getStruttura(idValidoNonTrovato));
  }

  @Test
  void getStruttura_idValido() {
    StrutturaBean s =
            new StrutturaBean(1, "santobono", "Via della Croce Rossa n. 8 Napoli (NA)", "0812205111");
    assertEquals(s, test.getStruttura(idValido));
  }

  @Test
  void getOperazione_idNonValido() {
    assertNull(test.getOperazione(idNonValido));
  }

  @Test
  void getOperazione_idValidoNonTrovato() {
    assertNull(test.getOperazione(idValidoNonTrovato));
  }

  @Test
  void getOperazione_idValido() {
    OperazioneBean o =
            new OperazioneBean(1, "Pagamento Ticket", "Pagamento Ticket per visita medica");
    assertEquals(o, test.getOperazione(1));
  }

  @Test
  void getImpiegato_CodiceFiscaleNonValido() {
    assertNull(test.getImpiegato("mario"));
  }

  @Test
  void getImpiegato_CodiceFiscaleValidoNonTrovato() {
    assertNull( test.getImpiegato("FLTNGL99A14L845S"));
  }

  @Test
  void getImpiegato_CodiceFiscaleValido() {
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
  void deletePrenotazione_idNonValido() {
    assertEquals(0, test.deletePrenotazione(idNonValido));
  }

  @Test
  void deletePrenotazione_idValido() {
    assertEquals(1, test.deletePrenotazione(idValido2));
  }

  @Test
  void deletePrenotazione_idValidoNonTrovato() {
    assertEquals(0, test.deletePrenotazione(idValidoNonTrovato));
  }


  @Test
  void numPrenotazioni_idNonValido() {
    assertEquals(0, test.numPrenotazioni(idNonValido, idNonValido));
  }

  @Test
  void numPrenotazioni_idValidoNonTrovato() {
    assertEquals(0, test.numPrenotazioni(idValidoNonTrovato, idValidoNonTrovato));
  }

  @Test
  void numPrenotazioni_idValido() {
    assertNotEquals(0, test.numPrenotazioni(idValido, idValido));
  }

  @Test
  void serviPrenotazione_idNonValido() {
    assertNull(test.serviPrenotazione(idNonValido, idNonValido));
  }

  @Test
  void serviPrenotazione_idValidoNonTrovato() {
    assertNull(test.serviPrenotazione(idValidoNonTrovato, idValidoNonTrovato));
  }

  @Test
  void serviPrenotazione_idValido() {
    assertNotEquals(null, test.serviPrenotazione(idValido, idValido));
  }


  @Test
  void getOperazioni() {
    assertNotNull(test.getOperazioni());
  }
}
