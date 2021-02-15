package business;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestioneTest {
  private static Gestione test;


  @BeforeAll
  public static void setUp() {
    test = new Gestione();
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

  @Test
  void accettaPrenotazione_idNonValido(){
    assertNull(test.accettaPrenotazione(-1,-1));
  }

  @Test
  void getNumeroPrenotazioni_idNonValido(){
    assertEquals(0,test.getNumPrenotazioni(0,0));
  }

  @Test
  void getNumeroPrenotazioni_idValidoNonTrovato(){
    assertEquals(0,test.getNumPrenotazioni(99,99));
  }

  @Test
  void getNumeroPrenotazioni_idValido(){
    assertNotEquals(0,test.getNumPrenotazioni(1,1));
  }

  @Test
  void getListaOperazioni(){
    assertNotNull(test.getListaOperazioni());
  }

  @Test
  void getOperazione_idNonValido(){
    assertNull(test.getOperazione(0));
  }

  @Test
  void getOperazione_idValidoNonTrovato(){
    assertNull(test.getOperazione(99));
  }

  @Test
  void getOperazione_idValido(){
    assertNotNull(test.getOperazione(1));
  }

    @Test
    void deletePrenotazioniScadute() {
    assertEquals(0,test.deletePrenotazioniScadute());
    }
}
