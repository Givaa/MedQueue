<template>
  <ion-page>
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-title>Registrazione</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <ion-header collapse="condense">
        <ion-toolbar>
          <ion-title size="large">Registrazione</ion-title>
        </ion-toolbar>
      </ion-header>
        <ion-content class="background">
          <div id="container">
            <img src="../../public/assets/logosvg_nobg.svg"/>
            <br>
            <strong class="capitalize">Registrazione</strong>
            <br>
            <form>
            <ion-label>Nome</ion-label>
            <ion-input placeholder="Nome" required="true"></ion-input>
            <ion-label>Cognome</ion-label>
            <ion-input placeholder="Cognome" required="true"></ion-input>
            <ion-label>Codice Fiscale</ion-label>
            <ion-input placeholder="CodiceFiscale" pattern="[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$" required="true"></ion-input>
            <ion-label>Password</ion-label>
            <ion-input type="password" placeholder="Password" pattern="(?=^.{8,}$)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$" required="true"></ion-input>
            <ion-label>Data di Nascita</ion-label>
            <ion-datetime displayFormat="DD MM YY" placeholder="Data di nascita" required="true"></ion-datetime>
            <ion-label>Indirizzo e-mail</ion-label>
            <ion-input placeholder="Indirizzo e-mail" pattern="/\S+@\S+\.\S+/" required="true"></ion-input>
            <ion-label>Numero di telefono</ion-label>
            <ion-input placeholder="Numero di telefono" patern="^[\+][0-9]{10,12}" required="true"></ion-input>
            <ion-button type="submit" color="success"> Registrati </ion-button>
            </form>
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
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar
  },
  data(){
    return{
      minAge: "",
    };
  },
  methods: {
    minData(){
      const currentDate = new Date().getFullYear();
      return currentDate - 18;
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

ion-datetime{
  color: black;
}
</style>