import axios from 'axios';
import Vue from "vue";

export default {
    login (username,password) {
        return axios
            .post( process.env.VUE_APP_URL_BACKEND + '/api/v1.0/farma/webhospital/loginErogatore', {
                username,
                password
            },{
                headers: {
                    'Content-Type': 'application/json',
                    'struttura':'140000'
                },

            })
            .then(response => response.data);
    }
};
