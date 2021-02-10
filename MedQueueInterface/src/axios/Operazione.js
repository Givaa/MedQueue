import axios from 'axios';

export default {
    getOperazioneBtId(idOperazioneGet){
        return axios.post('http://localhost:8080/operazione/{id}', {
            idOperazioneGet
        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    getOperazioni(){
        return axios.post('http://localhost:8080/operazioni', {
        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    getOperazioneByNome(tipoOperazioneGet) {
        return axios.post('http://localhost:8080/operazione/{tipo}', {
            tipoOperazioneGet
        }, {
            crossDomain: true,
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }
};