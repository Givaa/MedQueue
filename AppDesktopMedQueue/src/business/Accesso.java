package business;

import entity.ImpiegatoBean;
import persistence.DataAccess;

/**
 * Classe per verificare le credenziali di un impiegato.
 */
public class Accesso {


    private ImpiegatoBean impiegato;



    /**
     * Inizializzo un oggetto Accesso con il codice fiscale.
     * @param username codicefiscale impiegato
     */
    public Accesso(String username){
        this.impiegato=DataAccess.getImpiegato(username);
    }

    /**
     * Inizializza un nuovo oggetto passando un impiegato
     * @param impiegato impiegato
     */
    public Accesso(ImpiegatoBean impiegato){
        this.impiegato=impiegato;
    }


    /**
     * Ritorna l'impiegato di cui si vuole verificare l'accesso.
     * @return
     */
    public ImpiegatoBean getImpiegato() {
        return impiegato;
    }


    /**
     * Setta l'impiegato ad un nuovo oggetto Accesso.
     * @param username
     */
    public void setImpiegato(String username) {
        this.impiegato=DataAccess.getImpiegato(username);
    }


    /**
     * Verifica se la password dell'impiegato che vuole eseguire l'accesso e uguale alla password inserita.
     * @param password
     * @return
     */
    public boolean verificaCredenziali(String password){
        if(password.equals(impiegato.getPassword()))
            return true;
        else
            return false;
    }
}
