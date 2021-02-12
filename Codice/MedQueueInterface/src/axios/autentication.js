import axios from 'axios';

export default {

    //Funzione per la registrazione

    signup(nome, cognome, codFisc, password, dataDiNascita, email, numeroTelefono) {
        return axios
            .post('http://localhost:8080/signup', {
                    nome,
                    cognome,
                    codFisc,
                    password,
                    dataDiNascita,
                    email,
                    numeroTelefono
                }, {
                    crossDomain: true,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
            ).then(response => response.data);
    }
};