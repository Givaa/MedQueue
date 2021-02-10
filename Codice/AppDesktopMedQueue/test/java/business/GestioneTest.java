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
  public static void setUp() {
    test = new Gestione();
    idValido = 1;
    idNonValido = 0;
    IdValidoNonTrovato = 99;
  }

  @Test
  void accettazionePrenotazione_CodaNonSelezionata_1() {
    assertNull(test.accettaPrenotazione(null,null));
  }

  @Test
  void accettazionePrenotazione_PrenotazioneNonAccettata_2() {
    assertNull(test.accettaPrenotazione(1,null));
  }

  @Test
  void accettazionePrenotazione_PrenotazioneAccettata_3() {
    assertNotNull(test.accettaPrenotazione(1,1));
  }




}
