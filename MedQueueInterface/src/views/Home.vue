<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button color="primary"></ion-menu-button>
        </ion-buttons>
        <ion-title>{{ $route.params.id }}</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">{{ $route.params.id }}</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">
        <img src="../../public/assets/logosvg_nobg.svg"/>
        <br>
        <strong class="capitalize">Benvenuto in MedQueue</strong>
        <p>MedQueue fornisce un servizio di prenotazione online
        presso i cup convenzionati della campanoa in modo da rendere</p>
       <p> più facile e veloce l'iter burocratico che precede una
        prestazione ospedaliera</p>
        <br>
        <br>
        <p>Se non hai ancora un account sulla nostra piattaforma </p>
        <p>Registrati subito pre usufruire deinostri servizi da subito</p>
        <p>Altrimenti accedi subito</p>
        <br>
        <IonButton @click="goReg"  color="primary">Registrati</IonButton>
        <IonButton @click="goLogin"  color="success">Accedi</IonButton>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {IonButton, IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar } from '@ionic/vue';
import router from "@/router";

export default {
  name: 'Folder',
  components: {
    IonButton,
    IonButtons,
    IonContent,
    IonHeader,
    IonMenuButton,
    IonPage,
    IonTitle,
    IonToolbar
  },
  methods:{
    goLogin(){
      router.push("/Accesso/Accesso");
    },

    goReg(){
      router.push("/Registrazione/Registrazione");
    }
  }
}
</script>

<style scoped>

img{
  height: 200px;
  width: 200px;
  position: center;
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