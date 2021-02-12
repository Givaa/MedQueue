<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Convalida Prenotazione</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Convalida Prenotazione</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">

        <label>Inserire il Codice Fiscale</label>
        <br>
        <br>
        <ion-input id="cf" v-model="codiceFiscale" placeholder="CodiceFiscale"></ion-input>
        <br>
        <ion-button @click="convalda" color="primary">Convalida</ion-button>


      </div>

    </ion-content>
  </ion-page>
</template>

<script>
import prenotazioniAxios from '../axios/prenotazioni'
import {
  IonInput,
  IonButton,
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  alertController
} from '@ionic/vue';

export default {
  name: "Convalida",
  components: {
    IonInput,
    IonButton,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar,
  },
  data(){
    return{
      codiceFiscale:""
    };
  },
  methods:{
    convalda(){
      console.log(this.codiceFiscale)
      prenotazioniAxios.convalida(this.codiceFiscale)
      .then((response) =>{
        if(response === true){
          const toast = alertController
          .create({
            header:"Prenotazione Convalidata",
          })
          return toast.present();
        }else{
          const toast = alertController
              .create({
                header:"Prenotazione non convalidata",
              })
          return toast.present();
        }
      })
    }
  }

}
</script>

<style scoped>

label{
  font-weight: bold;
}

#container {
  text-align: center;
  position: relative;
  left: 0;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
}

#container strong {
  font-size: 20px;
  line-height: 26px;
}

#container p {
  font-size: 16px;
  line-height: 22px;
  color: #8c8c8c;
  margin: 0;
}

#container a {
  text-decoration: none;
}
</style>