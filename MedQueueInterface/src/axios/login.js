import axios from 'axios';

export default {
    login(username,password){
        return axios.post('/login', {
            username,
            password
            },

            ).then(response => response.data);
    }
};