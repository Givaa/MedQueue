import axios from 'axios';

export default {
    getStrutture(){
        return axios.post('http://localhost:8080/strutture', {

        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    },

    getStrutturaByNome(nomeStrutturaGet){
        return axios.post('http://localhost:8080/struttura/{nome}', {
            nomeStrutturaGet
        }, {
            crossDomain:true,
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(response => response.data)
    }

}