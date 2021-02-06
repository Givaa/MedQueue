import axios from 'axios';

//Bisogna cambiare la funzione e i parametri
//E' solo un test

export default {
    getPrenotazioniByStruttura(ordinePrenotazioni){
        return axios.post('http://localhost:8080/prenotazioni', {
            ordinePrenotazioni
        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};