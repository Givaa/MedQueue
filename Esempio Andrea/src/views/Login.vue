<template>
  <div class="ion-page">
    <ion-content color="light">
      <ion-grid style="height: 100%">
        <ion-row class="ion-align-items-center ion-justify-content-center" style="height: 100%;">
          <ion-card class="card-center">
            <ion-card-header>
              <img src="../../public/assets/img/logo.png" height="100">
            </ion-card-header>
            <ion-card-content>
              <v-form @submit="onSubmit">
                <ion-list>
                  <ion-item>
                    <ion-label position="floating" color="medium">Username</ion-label>
                    <v-field name="username" v-slot="{field}" v-model="loginform.user" :rules="isRequired">
                      <ion-input
                          class="input-custom"
                          name="username"
                          type="text"
                          required
                          v-bind="field">
                      </ion-input>
                    </v-field>
                  </ion-item>
                  <v-error-message name="username" style="color: red" class="error"></v-error-message>
                  <ion-item>
                    <ion-label position="floating" color="medium">Password</ion-label>
                    <v-field name="password" v-model="loginform.passw" v-slot="{field}" :rules="isRequired">
                      <ion-input
                          name="password"
                          type="password"
                          required
                          v-bind="field">
                      </ion-input>
                    </v-field>
                  </ion-item>
                  <v-error-message name="password" style="color: red" class="error"></v-error-message>
                </ion-list>

                <ion-row responsive-sm>
                  <ion-col>
                    <ion-button type="submit" expand="block">Login</ion-button>
                  </ion-col>
                </ion-row>
              </v-form>

            </ion-card-content>
          </ion-card>
        </ion-row>
      </ion-grid>
    </ion-content>
  </div>
</template>

<style>
.login-logo img {
  max-width: 180px;
}

.list {
  margin-bottom: 0;
}

ion-item .item-inner {
  border-bottom: 1px solid #db2b2b;
}

</style>

<script>
import loginAxios from "../axios/login"
import * as V from 'vee-validate';
import {defineRule} from 'vee-validate';
import { isPlatform } from '@ionic/vue';
import {
  alertController,
  toastController,
  IonButton,
  IonLabel,
  IonInput,
  IonItem,
  IonIcon,
  IonContent, loadingController
} from '@ionic/vue';

const {required} = 'VeeValidateRules';
import {password} from 'vee-validate';
import router from "@/router";


export default {
  name: "Login",
  data() {
    return {
      submitted: false,
      errore: null,
      loginform: {
        user: "",
        passw: ""
      },
    }
  },
  components: {
    VField: V.Field,
    VForm: V.Form,
    VErrorMessage: V.ErrorMessage,
    IonButton, IonLabel, IonInput, IonItem, IonContent,
  },


  methods: {

    async onSubmit(data) {
      const loading = await loadingController
          .create({
            cssClass: 'my-custom-class',
            message: 'Caricamento...',
          });
      await loading.present();

      loginAxios.login(data.username, data.password)
          .then((response) => {
            this.errore = response;
            sessionStorage.setItem('codcentroprenotante', response.codcentroprenotante);
            sessionStorage.setItem("codstruttura", response.codstruttura);
            sessionStorage.setItem("erogatore", response.erogatore);
            sessionStorage.setItem("nominativo", response.nominativo);
            sessionStorage.setItem("uidutente", response.uidutente);
            sessionStorage.setItem("username", response.username);
            sessionStorage.setItem("token", response.access_token);
            loading.dismiss()
            window.location.reload();
          })
          .catch(async err => {
            if (err.response.data) {
              this.errore = err.response.data;
              loading.dismiss()
            } else {
              this.errore = err;
              loading.dismiss()
            }
            const toast = await toastController
                .create({
                  cssClass: 'my-custom-class',
                  header: 'Attenzione!!',
                  message: this.errore,
                  buttons: ['OK'],
                  duration: 2000,

                });
            return toast.present();
          });
    },
    isRequired(value) {
      if (!value) {
        return "campo obbligatorio";
      }
      return true;
    },

  },
  mounted() {
    if (sessionStorage.getItem('token'))
      this.$router.push('/appuntamenti');
  }


}
</script>
