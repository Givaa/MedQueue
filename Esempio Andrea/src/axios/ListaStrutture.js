import axios from 'axios'


export default {
    getStrutture() {
        return axios
            .get(process.env.VUE_APP_URL_BACKEND + '/api/v1.0/farma/webhospital/strutture',
                {
                    headers:{
                        "struttura":"140000",
                        'x-access-token': localStorage.getItem('token')
                    }

                })
            .then(response => response.data)
            .catch(err => "error");
    },
}