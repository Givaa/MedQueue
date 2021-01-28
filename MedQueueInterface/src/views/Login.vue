<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-menu-button color="primary"></ion-menu-button>
        </ion-buttons>
        <ion-title>Accesso</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Accesso</ion-title>
        </ion-toolbar>
      </ion-header>
        <ion-content class="background">
          <div id="container">
            <img src="../../public/assets/logosvg_nobg.svg"/>
            <br>
            <strong class="capitalize">Accesso</strong>
            <br>
            <br>
            <br>
            <ion-label>Codice Fiscale</ion-label>
            <ion-input placeholder="Codice Fiscale"></ion-input>
            <br>
            <br>
            <ion-label>Password</ion-label>
            <ion-input type="password" placeholder="Password"></ion-input>
            <br>
            <br>
            <ion-button @click="goHomeUtente" color="success"> Accedi </ion-button>
          </div>
        </ion-content>
      </ion-content>
  </ion-page>
</template>

<script lang="ts">
import { IonButton,IonLabel, IonInput, IonButtons, IonContent, IonHeader, IonMenuButton, IonPage, IonTitle, IonToolbar } from '@ionic/vue';
import router from "@/router";

export default {
  name: "Login",
  components: {
    IonButton,
    IonLabel,
    IonInput,
    IonButtons,
    IonContent,
    IonHeader,
    IonMenuButton,
    IonPage,
    IonTitle,
    IonToolbar
  },
  methods: {
    goHomeUtente() {
      router.push("/HomeUtente");
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

ion-content.background{
  --background: url(../../public/assets/CartellinaAllungata.svg)0 0/100% 100% no-repeat;

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

ion-label{
  color: black;
  font-weight: bold;
}

ion-input{
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}
</style>