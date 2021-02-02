import axios from 'axios';

export default {
    login(username,password){
        return axios
            .post('http://localhost:8080/login', {
            username,
            password
            },{
                crossDomain:true,
                headers:{
                    'Content-Type': 'multipart/form-data',
                }
                }
            ).then(response => response.data);
    }
};