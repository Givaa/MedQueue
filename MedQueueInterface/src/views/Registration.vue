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
        <ion-content class="background">
          <div id="container">
            <img src="../../public/assets/logosvg_nobg.svg"/>
            <br>
            <strong class="capitalize">Registrazione</strong>
            <br>
            <ion-label>Nome</ion-label>
            <ion-input placeholder="Nome"></ion-input>
            <ion-label>Cognome</ion-label>
            <ion-input placeholder="Cognome"></ion-input>
            <ion-label>Codice Fiscale</ion-label>
            <ion-input placeholder="CodiceFiscale"></ion-input>
            <ion-label>Password</ion-label>
            <ion-input type="password" placeholder="Password"></ion-input>
            <ion-label>Data di Nascita</ion-label>
            <ion-datetime displayFormat="MM DD YY" placeholder="Data di nascita"></ion-datetime>
            <ion-label>Indirizzo e-mail</ion-label>
            <ion-input placeholder="Indirizzo e-mail"></ion-input>
            <ion-label>Numero di telefono</ion-label>
            <ion-input placeholder="Numero di telefono"></ion-input>
            <ion-button color="success"> Registrati </ion-button>
          </div>
        </ion-content>
    </ion-content>
  </ion-page>
</template>
<script lang="ts">
import {
  IonDatetime,
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
} from '@ionic/vue';

export default {
  name: "Registration",
  components: {
    IonDatetime,
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
  --background: url(../../public/assets/cartellina.svg)0 0/100% 100% no-repeat;

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
  margin-left: 33%;
  position: center;
  width: 32%;
}

ion-datetime{
  color: black;
}
</style>