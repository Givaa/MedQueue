<template>
  <div class="ion-page">
    <ion-header translucent>
      <ion-toolbar>
        <ion-title>Modal Content</ion-title>
        <ion-buttons slot="end">
          <ion-button @click="dismissModal()">Close</ion-button>
        </ion-buttons>
      </ion-toolbar>
    </ion-header>
    <ion-content  color="light">
      <ion-grid>
        <ion-row>
          <ion-col>
    <ion-searchbar
        autocomplete="on"
        debounce="500"
        v-on:ionInput="filterItems($event.target.value)">
    </ion-searchbar>
    <ion-list>
      <ion-item v-for="struttura in ListaStrutture" :key="struttura.codiceStruttura+struttura.codicePresidio">
        {{struttura.descrizioneStruttura}}, {{struttura.comune}}
      </ion-item>
    </ion-list>
          </ion-col>
        </ion-row>
      </ion-grid>
    </ion-content>
  </div>
</template>

<script >

import ListaStrutture from "@/axios/ListaStrutture";
import {alertController, loadingController} from "@ionic/vue";
import appuntamentiAxios from "@/axios/AppuntamentiAxios";
export default {
  name: "modal",
  props:{
    title: String,
    pene: Object,
    strutturaSelezionata: Object
  },
  data(){
    return{
      ListaStrutture:[],
      inputValue: ''
    }
  },
  mounted() {
    this.RicezioneStrutture();
  },
  methods: {

    dismissModal(){
      console.log(this.pene);
      this.pene.dismiss();
    },

    filterItems(searchTerm) {
      if(searchTerm.length === 0)
      {
        this.RicezioneStrutture();
      }
      else if(searchTerm.length>2)
      {
        let listaApp = [];
        listaApp = this.ListaStrutture.filter(item => {
          return item.descrizioneStruttura.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;});
        this.ListaStrutture= [];
        this.ListaStrutture = listaApp;
        console.log(listaApp);
      }
    },

    controlloLunghezza (){
      if(this.inputValue.length > 2)
      {
        alert('Hi')
        // eslint-disable-next-line no-empty
      }else{}

    },
    RicezioneStrutture(){
      let appoggio = [];
      ListaStrutture.getStrutture().then((response)=>{
        appoggio = response;
        for(let i = appoggio.length-1; i>=0;i--)
        {
          if(appoggio[i].codicePresidio === "")
          {
            appoggio.splice(i,1);
          }
        }
        appoggio.sort((a,b) => (a.descrizioneStruttura > b.descrizioneStruttura) ? 1 : ((b.descrizioneStruttura > a.descrizioneStruttura) ? -1 : 0));
        this.ListaStrutture = appoggio;
      }).catch((err)=>{
        alert(err);
      })
    },
  },

}
</script>

<style scoped>

</style>