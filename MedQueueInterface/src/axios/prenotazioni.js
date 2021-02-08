import axios from 'axios';


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
    },

    addPrenotazione(newPrenotazioniCf, newPrenotazioniOra, newPrenotazioniIdOp, newPrenotazioniIdS, newPrenotazioneData){
        return axios.post('http://localhost:8080/newPrenotazione', {
            newPrenotazioniCf,
            newPrenotazioniOra,
            newPrenotazioniIdOp,
            newPrenotazioniIdS,
            newPrenotazioneData
        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};