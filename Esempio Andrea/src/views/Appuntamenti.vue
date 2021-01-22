<template>
  <div class="ion-page">
    <ion-header>
      <ion-toolbar color="dark">
        <ion-title>operatore: {{operatore}}</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content color="light">
      <ion-grid>

        <ion-row>
          <ion-col>
            <ion-searchbar
                autocomplete="on"
                debounce="500"
                v-if="!visibleButton"
                v-on:ionChange="filterItems($event.target.value)"
                v-on:ionInput="filterItems($event.target.value)"
                placeholder="Cerca la struttura su cui operi">
            </ion-searchbar>
            <ion-grid >
              <ion-row>
                <ion-col size="12">
                  <ion-label class="ion-text-wrap">
                    {{strutturaSelezionata.descrizioneStruttura}}
                  </ion-label>
                </ion-col>
                <ion-col>
                  <ion-button class="ion-no-padding" v-if="visibleButton" fill="clear" color="primary" @click="visibleListStrutture=true; visibleButton=false; strutturaSelezionata=''; ">Cambia</ion-button>
                </ion-col>
              </ion-row>
            </ion-grid>


            <ion-list v-if="visibleListStrutture">
              <ion-item @click="strutturaSelected(struttura)" v-for="struttura in ListaStruttureCopia" :key="struttura.codiceStruttura+struttura.codicePresidio">
                {{struttura.descrizioneStruttura}}, {{struttura.comune}}
              </ion-item>
            </ion-list>
          </ion-col>

        </ion-row >
        <ion-row v-if="strutturaSelezionata">
          <ion-col size="12">
            <ion-item >
              <ion-label  position="floating" color="medium">Codice fiscale</ion-label>
              <v-field   name="codicefiscale" v-slot="{field}" v-model="codiceFiscale">
                <ion-input
                    :disabled="!strutturaSelezionata"
                    class="input-custom"
                    name="codicefiscale"
                    type="text"
                    spellcheck="false"
                    autocapitalize="on"
                    v-bind="field"
                    required>
                </ion-input>
              </v-field>
            </ion-item>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col size="12">
            <ion-row>
              <ion-col size="12">
                <ion-button color="medium" style="width: 100%" v-if="strutturaSelezionata" @click="RicezioneAppuntamenti">Cerca</ion-button>
              </ion-col>
            </ion-row>
            <ion-row>
              <ion-col size="12">
                <ion-button color="dark" style="width: 100%" v-if="strutturaSelezionata && camera==='off'" @click="openPhotoCamera">SCAN</ion-button>
                <ion-button color="medium" style="width: 100%" v-if="strutturaSelezionata && camera==='auto'" @click="camera='off'">CHIUDI</ion-button>
              </ion-col>
            </ion-row>
          </ion-col>
        </ion-row>


        <ion-row style="--ion-background-color: transparent" v-if="IsVisibleApp" class="ion-align-items-center">
          <ion-col >
            <ion-list style="--ion-background-color: transparent">
              <ion-card color="dark" v-for="(appuntamento,index) in ListaAppuntamenti" :key="appuntamento.codiceImpegnativa">
                <ion-list-header>
                  <ion-row class="myRow">
                    <ion-col >
                      <ion-label color="light" style="font-size: small;">
                        Impegnativa
                      </ion-label>
                      <ion-label color="light" style="font-size: medium">
                        {{appuntamento.codiceImpegnativa}}
                      </ion-label>
                    </ion-col>
                    <ion-col style="text-align: end">
                      <ion-button @click="presentAlertConfirm" fill="outline" color="light">Esegui</ion-button>
                    </ion-col>
                  </ion-row>
                </ion-list-header>

                <ion-item style="--ion-background-color: white">
                  <div style="width: 100%">
                    <ion-list>
                      <ion-item v-for="dettaglio in ListaAppuntamenti[index].appuntamenti" :key="dettaglio.codAppuntamento" >
                        <ion-grid>
                          <ion-row class="ion-justify-content-between">
                            <ion-col>
                              <ion-label style="font-size: small" class="ion-text-wrap">{{dettaglio.desprest}}</ion-label>
                              <ion-label style="font-size: small" class="ion-text-wrap">{{dettaglio.dataappuntamento}}</ion-label>
                            </ion-col>
                            <div>
                              <ion-checkbox :disabled="dettaglio.checked" :checked="dettaglio.checked" @ionChange="prestazioneEseguita($event.target,dettaglio)" color="primary"></ion-checkbox>
                              <ion-label v-if="!dettaglio.checked" color="danger">Da eseguire</ion-label>
                              <ion-label v-if="dettaglio.checked" color="success">eseguita il {{dettaglio.dataEsecuzione}}</ion-label>
                            </div>
                          </ion-row>
                        </ion-grid>
                      </ion-item>
                    </ion-list>
                  </div>
                </ion-item>
              </ion-card>
            </ion-list>
          </ion-col>
        </ion-row>
        <qr-stream @init = "onInit" v-if="camera==='auto'" :camera = "camera" @decode="onDecode" class="mb">
          <div style="color: red;" class="frame"></div>
        </qr-stream>

      </ion-grid>
    </ion-content>

  </div>
</template>

<script>
import ListaStrutture from "@/axios/ListaStrutture";
import appuntamentiAxios from "@/axios/AppuntamentiAxios"
import * as V from "vee-validate";
import {IonButton, alertController, loadingController, modalController, toastController} from '@ionic/vue';
import { QrStream } from 'vue3-qr-reader'
import Modal from './modal.vue'
import { DateTime } from 'luxon';
import router from "@/router";
import Login from "@/views/Login";

export default {
  components: {
    QrStream,
    VField: V.Field,
    IonButton,
  },


  name: "Appuntamenti",

  data(){
    return{
      toastStrutture: {},
      ListaStruttureCopia:[],
      prestazioneEseguitaVisible:false,
      dataNow:'',
      visibleListStrutture:false,
      visibleButton:false,
      operatore:'',
      result:'',
      camera:'off',
      error:'',
      IsVisibleApp:false,
      codiceFiscale:'',
      ListaStrutture:[],
      ListaAppuntamenti:[],
      strutturaSelezionata: "",
      caricamento: false,
      modal: null
    }
  },

  mounted() {
    this.mountToast();
    this.RicezioneStrutture();
    this.operatore = sessionStorage.getItem("nominativo");
  },

  props: {
    timeout: { type: Number, default: 2000 },
  },
  methods:{
    prestazioneEseguita(evento,prestazione)
    {
      const dataFormat = DateTime.local();
      const localeData = DateTime.fromISO(dataFormat);
      const dataNow = localeData.toLocaleString(localeData);
      const arrayAppoggio = this.ListaAppuntamenti;
      for(let i = 0;i<this.ListaAppuntamenti.length;i++)
      {
        for(let j = 0; j<arrayAppoggio[i].appuntamenti.length;j++)
        {
          if(prestazione.desprest === arrayAppoggio[i].appuntamenti[j].desprest && prestazione.codiceImpegnativa === arrayAppoggio[i].appuntamenti[j].codiceImpegnativa)
          {
            arrayAppoggio[i].appuntamenti[j].checked = !arrayAppoggio[i].appuntamenti[j].checked;
            arrayAppoggio[i].appuntamenti[j].dataEsecuzione = dataNow;
          }
        }
      }
      this.ListaAppuntamenti = [];
      this.ListaAppuntamenti = arrayAppoggio;
    },
    strutturaSelected(val)
    {
      this.strutturaSelezionata = val;
      this.visibleListStrutture=false;
      this.visibleButton=true;
    },
    async mountToast(){
      this.toastStrutture = await toastController
          .create({
            cssClass: 'my-custom-class',
            header: 'Attenzione',
            message: 'Struttura non trovata',
          });
    },
    async filterItems(searchTerm) {

      if (searchTerm.length === 0) {
        this.visibleListStrutture = false;
        this.visibleButton = false;
        this.RicezioneStrutture();
      } else if (searchTerm.length >= 2) {
        let listaApp = [];
        listaApp = this.ListaStrutture;
        this.visibleListStrutture = true;
        this.visibleButton = false;
        listaApp = this.ListaStrutture.filter(item => {
          return item.descrizioneStruttura.toLowerCase().indexOf(searchTerm.toLowerCase()) > -1;
        });
        this.ListaStruttureCopia = listaApp;
        if (this.ListaStruttureCopia.length === 0) {
          this.visibleListStrutture = false;
          this.visibleButton = false;
          return this.toastStrutture.present();
        }
        else {
          return this.toastStrutture.dismiss();
        }
      }
    },


    strutturaChanged: function(_value) {
      this.strutturaSelezionata = _value.detail.value
    },
    async presentAlertConfirm() {
      const toast = await toastController
          .create({
            cssClass: 'my-custom-class',
            header: 'Attenzione',
            message: 'Confermi di voler erogare la seguente impegnativa?',
            buttons: [
              {
                text: 'No',
                role: 'cancel',
                cssClass: 'secondary',
                handler: blah => {
                  console.log('Confirm Cancel:', blah)
                },
              },
              {
                text: 'Si',
                handler: () => {
                  console.log('Confirm Okay')
                },
              },
            ],
          });
      return toast.present();
    },
    async alertErrCF() {
      const toast = await toastController
          .create({
            cssClass: 'my-custom-class',
            header: 'Attenzione',
            message: 'Codice fiscale non corretto',
            duration: 2000,
            buttons: [
              {
                text: 'Riprova',
                role: 'cancel',
                cssClass: 'secondary',
                handler: blah => {
                  console.log('Confirm Cancel:', blah)
                },
              }
            ],
          });
      return toast.present();
    },
    openPhotoCamera(){
      this.camera = "auto";
    },

    onDecode (result) {
      this.result = result;
      if(this.result){
        this.camera='off';
        this.codiceFiscale = result;
        this.RicezioneAppuntamenti();
      }
    },

    async onInit (promise) {
      try {
        await promise
      } catch (error) {
        if (error.name === 'NotAllowedError') {
          this.error = "ERRORE: Non hai abilitato i permessi della fotocamera"
        } else if (error.name === 'NotFoundError') {
          this.error = "ERRORE: Nessun dispositivo di cattura è stato individuato"
        } else if (error.name === 'NotSupportedError') {
          this.error = "ERRORE: La connessione non è sicura (HTTPS, localhost)"
        } else if (error.name === 'NotReadableError') {
          this.error = "ERRORE: Il dispositivo di cattura è già in esercizio"
        } else if (error.name === 'OverconstrainedError') {
          this.error = "ERRORE: Il dispositivo di cattura non è supportato "
        } else if (error.name === 'StreamApiNotSupportedError') {
          this.error = "ERRORE: Il servizio di stream non è supportato dal browser utilizzato"
        }
        else if (error.name === 'InsecureContextError') {
          this.error = "ERRORE: La connessione non è sicura (HTTPS, localhost)"
        }
        const toast = await toastController
            .create({
              cssClass: 'my-custom-class',
              header: 'Attenzione',
              message: this.error,
              duration: 2000,
              buttons: [
                {
                  text: 'Riprova',
                  role: 'cancel',
                  cssClass: 'secondary',
                  handler: blah => {
                    console.log('Confirm Cancel:', blah)
                  },
                }
              ],
            });
        return toast.present();
      }
    },

    RicezioneStrutture(){
      let appoggio = [];

        ListaStrutture.getStrutture().then((response) => {
          appoggio = response;
          for (let i = appoggio.length - 1; i >= 0; i--) {
            if (appoggio[i].codicePresidio === "") {
              appoggio.splice(i, 1);
            }
          }
          appoggio.sort((a, b) => (a.descrizioneStruttura > b.descrizioneStruttura) ? 1 : ((b.descrizioneStruttura > a.descrizioneStruttura) ? -1 : 0));
          this.ListaStrutture = appoggio;
        }).catch(async (err) => {
          const toast = await toastController
              .create({
                cssClass: 'my-custom-class',
                header: 'Attenzione',
                message: err.response.data,
                duration: 2000,
              });
          return toast.present();
        })
    },

    async RicezioneAppuntamenti(){
      const loading = await loadingController
          .create({
            cssClass: 'my-custom-class',
            message: 'Caricamento...',
          });


      if(this.codiceFiscale.length===16)
      {
        await loading.present();
        this.caricamento = true;
        appuntamentiAxios.ricercaAppuntamenti(this.codiceFiscale).then(async (response)=>{
          this.ListaAppuntamenti = response;
          for(let i = 0;i<this.ListaAppuntamenti.length;i++)
          {
            for (let j = 0; j<this.ListaAppuntamenti[i].appuntamenti.length;j++)
            {
              if(!this.ListaAppuntamenti[i].appuntamenti[j].dataEsecuzione){
                this.ListaAppuntamenti[i].appuntamenti[j].checked = false;//MI DOVREBBE ARRIVARE CAMBAIRE APPENA ARRIVA
              }
              else{
                this.ListaAppuntamenti[i].appuntamenti[j].checked = true;
              }
            }
          }
          loading.dismiss()
          if(this.ListaAppuntamenti.length===0)
          {
            const toast = await toastController
                .create({
                  cssClass: 'my-custom-class',
                  header: 'Attenzione',
                  message: "Nessun appuntamento presente",
                  duration: 2000,
                  buttons: [
                    {
                      text: 'Riprova',
                      role: 'cancel',
                      cssClass: 'secondary',
                      handler: blah => {
                        console.log('Confirm Cancel:', blah)
                      },
                    }
                  ],
                });
            return toast.present();
          }

        }).catch((err)=>{
          alert(err);
          loading.dismiss()
        })
      }
      else
      {
        this.alertErrCF()
        loading.dismiss()
      }


    }
  },
  watch:{
    codiceFiscale: function (val){
      if(val.length===0)
        this.ListaAppuntamenti=[];
    },
    ListaAppuntamenti:function (val)
    {
      if(val.length>0)
        this.IsVisibleApp=true;
    }
  }
}
</script>

<style>
.myRow{
  width: 100%;
}
.alert-radio-label.sc-ion-alert-md{
  white-space: normal !important;
  font-size: small !important;
  font-weight: normal !important;;
  min-height: 100% !important;;
}
</style>
