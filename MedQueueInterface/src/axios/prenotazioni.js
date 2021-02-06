import axios from 'axios';

//Bisogna cambiare la funzione e i parametri
//E' solo un test

export default {
    getPrenotazioniByStruttura(getAllPrenotazionyByStruttura){
        return axios.post('http://localhost:8080/visualizzaCoda/{id}', {
            getAllPrenotazionyByStruttura
        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};