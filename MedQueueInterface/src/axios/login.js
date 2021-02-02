import axios from 'axios';

export default {
    login(username,password){
        return axios
            .post('http://192.168.125.73:8080/login', {
            username,
            password
            },{
                crossDomain:true,
                headers:{
                    //'Content-Type': 'text/html',
                }
                }
            ).then(response => response.data);
    }
};