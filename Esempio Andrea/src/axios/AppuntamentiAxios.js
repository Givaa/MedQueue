import axios from 'axios'

export default {
    ricercaAppuntamenti(cf, strutturaScelta) {
        return axios
            .post(`${process.env.VUE_APP_URL_BACKEND}/api/v1.0/farma/webhospital/appuntamentiErogatore`,
                {
                    'codice_fiscale': cf.toUpperCase(),
                    // codStruttura: strutturaScelta
                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'struttura': '140000',
                        'x-access-token': localStorage.getItem('token')
                    }
                })
            .then(response => response.data);
    },
}