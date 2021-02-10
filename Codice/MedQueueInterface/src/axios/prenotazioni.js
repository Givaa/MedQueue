import axios from 'axios';


export default {
    getPrenotazioniByStruttura(getAllPrenotazionyByStruttura) {
        return axios.post('http://localhost:8080/visualizzaCoda/{id}', {
            getAllPrenotazionyByStruttura
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    addPrenotazione(newPrenotazioniCf, newPrenotazioniOra, newPrenotazioniIdOp, newPrenotazioniIdS, newPrenotazioneData) {
        return axios.post('http://localhost:8080/newPrenotazione', {
            newPrenotazioniCf,
            newPrenotazioniOra,
            newPrenotazioniIdOp,
            newPrenotazioniIdS,
            newPrenotazioneData
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    getOrariDisponibili(idStruttura, idOperazione, PrenotazioneData) {
        return axios.post('http://localhost:8080/getOrari', {
            idStruttura,
            idOperazione,
            PrenotazioneData
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    getPrenotazioneByCf(getPrenotazioniByCf) {
        return axios.post('http://localhost:8080/prenotazioniUtente/{cf}', {
            getPrenotazioniByCf
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    elimina(deletePrenotazioniId) {
        return axios.post('http://localhost:8080/deletePrenotazione', {
            deletePrenotazioniId
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};