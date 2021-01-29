<template>
  <ion-page>
      <ion-content content="menu">
        <ion-header :translucent="true">
          <ion-toolbar>
            <ion-buttons slot="start">
              <ion-menu-button color="primary"></ion-menu-button>
            </ion-buttons>
            <ion-title>Prenotazione</ion-title>
          </ion-toolbar>
        </ion-header>
        <ion-content class="background">
          <div id="container">
            <img src="../../public/assets/logosvg_nobg.svg"/>
            <br>
            <strong class="capitalize">Prenota</strong>
            <br>
            <br>
            <br>
            <ion-label>Seleziona Struttura</ion-label>
            <ion-select placeholder="Struttura">
              <ion-select-option value="str1">Napoli</ion-select-option>
              <ion-select-option value="str2">Caserta</ion-select-option>
              <ion-select-option value="str3">Salerno</ion-select-option>
              <ion-select-option value="str4">Avellino</ion-select-option>
            </ion-select>
            <br>
            <ion-label>Seleziona Operazione</ion-label>
            <ion-select placeholder="Operazione">
              <ion-select-option value="op1">Pagamento</ion-select-option>
              <ion-select-option value="op2">Effettua prestazione</ion-select-option>
              <ion-select-option value="op3">Ritiro analise</ion-select-option>
              <ion-select-option value="op4">Ritito cartella clinica</ion-select-option>
            </ion-select>
            <br>
            <ion-label>Seleziona Data</ion-label>
            <ion-datetime min="2021-01-29" max="2023" displayFormat="DD MM YY" placeholder="Data"></ion-datetime>
            <br>
            <ion-button @click="goHomeUtente" color="success"> Accedi </ion-button>
          </div>
        </ion-content>
      </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonButton,
  IonSelectOption,
  IonSelect,
  IonDatetime,
  IonContent,
  IonButtons,
  IonHeader,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';
import router from "@/router";

export default {
  name: "Prenotazione",
  components: {
    IonButton,
    IonSelectOption,
    IonSelect,
    IonDatetime,
    IonContent,
    IonButtons,
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
  color: black;
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

ion-menu ion-content {
  --background: var(--ion-item-background, var(--ion-background-color, #fff));
}

ion-menu.md ion-content {
  --padding-start: 8px;
  --padding-end: 8px;
  --padding-top: 20px;
  --padding-bottom: 20px;
}

ion-menu.md ion-list {
  padding: 20px 0;
}

ion-menu.md ion-note {
  margin-bottom: 30px;
}

ion-menu.md ion-list-header,
ion-menu.md ion-note {
  padding-left: 10px;
}

ion-menu.md ion-list#menu-list {
  border-bottom: 1px solid var(--ion-color-step-150, #d7d8da);
}

ion-menu.md ion-list#menu-list ion-list-header {
  font-size: 22px;
  font-weight: 600;

  min-height: 20px;
}

ion-menu.md ion-list#labels-list ion-list-header {
  font-size: 16px;

  margin-bottom: 18px;

  color: #757575;

  min-height: 26px;
}

ion-menu.md ion-item {
  --padding-start: 10px;
  --padding-end: 10px;
  border-radius: 4px;
}

ion-menu.md ion-item.selected {
  --background: rgba(var(--ion-color-primary-rgb), 0.14);
}

ion-menu.md ion-item.selected ion-icon {
  color: var(--ion-color-primary);
}

ion-menu.md ion-item ion-icon {
  color: #616e7e;
}

ion-menu.md ion-item ion-label {
  font-weight: 500;
}

ion-menu.ios ion-content {
  --padding-bottom: 20px;
}

ion-menu.ios ion-list {
  padding: 20px 0 0 0;
}

ion-menu.ios ion-note {
  line-height: 24px;
  margin-bottom: 20px;
}

ion-menu.ios ion-item {
  --padding-start: 16px;
  --padding-end: 16px;
  --min-height: 50px;
}

ion-menu.ios ion-item.selected ion-icon {
  color: var(--ion-color-primary);
}

ion-menu.ios ion-item ion-icon {
  font-size: 24px;
  color: #73849a;
}

ion-menu.ios ion-list#labels-list ion-list-header {
  margin-bottom: 8px;
}

ion-menu.ios ion-list-header,
ion-menu.ios ion-note {
  padding-left: 16px;
  padding-right: 16px;
}

ion-menu.ios ion-note {
  margin-bottom: 8px;
}

ion-note {
  display: inline-block;
  font-size: 16px;

  color: var(--ion-color-medium-shade);
}

ion-item.selected {
  --color: var(--ion-color-primary);
}

ion-label{
  color: black;
}

ion-input{
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}

ion-select{
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}

ion-datetime{
  color: black;
  margin-left: 40%;
  position: center;
  min-width: 193px;
  width: 20%;
}
</style>