<template>
  <ion-page>
      <ion-content id="main-content">
        <ion-header :translucent="true">
          <ion-toolbar>
            <ion-title>Visualizza Coda</ion-title>
          </ion-toolbar>
        </ion-header>
        <div id="container">
          <strong class="capitalize">Visualizza Coda</strong>
          <ion-item>
          <h2>Coda ufficio</h2>
          </ion-item>
          <ion-item>
            <label>Seleziona la struttura:</label>
            <ion-select @click="updateStrutture" placeholder="Struttura" v-bind="selectedCod">
              <ion-select-option id="codice" v-bind:key="codice" v-for="codice in cod" >{{codice}}</ion-select-option>
            </ion-select>
          </ion-item>
          <div class="titolo1">Data</div>
          <div class="titolo2">Ora</div>
          <div class="titolo3">Tipo </div>
          <div class="colonna1">
            <div id="data" v-bind:key="data" v-for="data in date" >{{data}}</div>
          </div>
          <div class="colonna2">
            <div id="ora" v-bind:key="ora" v-for="ora in ore" >{{ora}}</div>
          </div>
          <div class="colonna3">
            <div id="prenotazione" v-bind:key="prenotazione" v-for="prenotazione in nomePrenotazioni" >{{prenotazione}}</div>
          </div>
        </div>
      </ion-content>
  </ion-page>
</template>

<script >
import prenotazioniAxios from '../axios/prenotazioni'
import operazioneAxios from '../axios/Operazione'
import {
  IonSelect,
  IonSelectOption,
  IonItem,
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';

export default {
  name: "visualizzaCoda",
  components: {
    IonSelect,
    IonSelectOption,
    IonItem,
    IonContent,
    IonHeader,
    IonPage,
    IonTitle,
    IonToolbar
  },
  data(){
    return{
      strutture: [],
      cod: [1,2],
      ore:[],
      date:[],
      prenotazioni:[],
      nomePrenotazioni: [],
      selectedCod:1
    };
  },
  methods:{

    updateStrutture(){
      prenotazioniAxios.getPrenotazioniByStruttura(this.selectedCod)
          .then((response) => {
            if(response === ''){
              this.presentAlert();
              return null;
            }else {
              this.strutture = response;

              for (let i=0; i<this.strutture.length; i++){
                this.ore[i] = this.strutture[i].ora;
                this.date[i] = this.strutture[i].dataPrenotazione;
                this.prenotazioni[i] =  this.strutture[i].idOperazione;
              }
              this.operazioneString();
            }
          })
      },

      operazioneString(){
      for(let i=0; i<this.prenotazioni.length; i++) {
        operazioneAxios.getOperazioneById(this.prenotazioni[i])
        .then((response) =>{
          this.nomePrenotazioni[i] = response.tipoOperazione;
        })
      }
      console.log(this.nomePrenotazioni);
      }
    }

}
</script>

<style scoped>

div.colonna1{
  float: left;
  margin-right: 33%;
  margin-left: 9%;
}

div.colonna2{
  float: left;
  margin-right: 33%;
}

div.colonna3{
  float: left;
  margin-right: 5%;
}

div.titolo1{
  float: left;
  margin-right: 36%;
  margin-left: 10%;
}

div.titolo2{
  float: left;
  margin-right: 38%;
}

div.titolo3{
  float: left;
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
</style>