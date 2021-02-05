package persistence;

import bean.ImpiegatoBean;
import bean.OperazioneBean;
import bean.PrenotazioneBean;
import bean.StrutturaBean;
import eccezioni.InvalidKeyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DataAccessTest {
    private static int id;
    private static String codicefiscale1;
    private static String codicefiscale2;
    private static int idOperazione;
    private static int idStruttura;

    @BeforeAll
    public static void setUp(){
        id=100;
        idOperazione=0;
        idStruttura=0;
        codicefiscale1=null;
        codicefiscale2="FLTNGL99A14L845J2";
    }

    @Test
    void getPrenotazione() {
        try {
            if(id<=0) {
                throw new InvalidKeyException("Id invalido, occorre un id>0");
            }
            else{
               PrenotazioneBean p=new DataAccess().getPrenotazione(id);
               System.out.println(p);
            }
        } catch (InvalidKeyException i) {
            System.out.println(i.toString());
        }
    }

    @Test
    void getStruttura() {
        try {
            if(id<=0) {
                throw new InvalidKeyException("Id invalido, occorre un id>0");
            }
            else{
                StrutturaBean s =new DataAccess().getStruttura(id);
                System.out.println(s);
            }
        } catch (InvalidKeyException i) {
            System.out.println(i.toString());
        }
    }

    @Test
    void getOperazione() {
        try {
            if(id<=0) {
                throw new InvalidKeyException("Id invalido, occorre un id>0");
            }
            else{
                OperazioneBean o =new DataAccess().getOperazione(id);
                System.out.println(o);
            }
        } catch (InvalidKeyException i) {
            System.out.println(i.toString());
        }
    }

    @Test
    void getImpiegato() {
        try {
            if(codicefiscale1==null && codicefiscale2.length()!=16) {
                throw new InvalidKeyException("Codicefiscale errato");
            }
            else{
                ImpiegatoBean i=new DataAccess().getImpiegato(codicefiscale1);
                System.out.println(i);
            }
        } catch (InvalidKeyException i) {
            System.out.println(i.toString());
        }
    }

    @Test
    void numPrenotazioni() {
        try {
            if(idOperazione<=0 && idStruttura<=0) {
                throw new InvalidKeyException("Id invalido, occorre un id>0");
            }
            else{
                int x=new DataAccess().numPrenotazioni(idOperazione,idStruttura);
                System.out.println(x);
            }
        } catch (InvalidKeyException i) {
            System.out.println(i.toString());
        }
    }

    @Test
    void serviPrenotazione() {
        try {
            if(idOperazione<=0 && idStruttura<=0) {
                throw new InvalidKeyException("Id invalido, occorre un id>0");
            }
            else{
                PrenotazioneBean p=new DataAccess().serviPrenotazione(idOperazione,idStruttura);
                System.out.println(p);
            }
        } catch (InvalidKeyException i) {
            System.out.println(i.toString());
        }
    }
}