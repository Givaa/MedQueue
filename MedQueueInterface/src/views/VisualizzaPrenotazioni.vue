<template>
  <ion-page>
    <ion-content content="menu">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-menu-button color="primary"></ion-menu-button>
          </ion-buttons>
          <ion-title>Visualizza Prenotazioni</ion-title>
        </ion-toolbar>
      </ion-header>
      <div id="container">
        <strong class="capitalize">Prenotazioni</strong>
        <p>Prenotazioni</p>
      </div>
    </ion-content>
  </ion-page>
</template>

<script lang="ts">
import {
  IonContent,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonListHeader,
  IonRouterOutlet,
  IonSplitPane,
  IonButtons,
  IonHeader,
  IonMenu,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar
} from '@ionic/vue';
import {ref} from "vue";
import {
  homeOutline,
  homeSharp,
  listOutline,
  listSharp,
  logInOutline,
  logInSharp,
  pencilOutline,
  pencilSharp
} from "ionicons/icons";
import {useRoute} from "vue-router";
import menu1 from "./menu.vue"

export default {
  name: "Prenotazione",
  components: {
    IonContent,
    IonButtons,
    IonHeader,
    IonMenuButton,
    IonPage,
    IonTitle,
    IonToolbar
  },
  setup() {
    const selectedIndex = ref(0);
    const appPages = [
      {
        title:"Home",
        url:"/HomeUtente",
        iosIcon: homeOutline,
        mdIcon: homeSharp
      },
      {
        title: 'Log In',
        url: '/Prenotazione',
        iosIcon: logInOutline,
        mdIcon: logInSharp
      },
      /**{
        title: 'Sign in',
        url: '/Registrazione',
        iosIcon: pencilOutline,
        mdIcon: pencilSharp
      },
       {
        title: 'Visualizza Coda',
        url: '/VisualizzazioneCoda',
        iosIcon: listOutline,
        mdIcon: listSharp
      }*/
    ];

    const path = window.location.pathname.split('folder/')[1];
    if (path !== undefined) {
      selectedIndex.value = appPages.findIndex(page => page.title.toLowerCase() === path.toLowerCase());
    }

    const route = useRoute();

    return {
      selectedIndex,
      appPages,
      homeOutline,
      homeSharp,
      logInOutline,
      logInSharp,
      pencilOutline,
      pencilSharp,
      listOutline,
      listSharp,
      isSelected: (url: string) => url === route.path ? 'selected' : ''
    }
  }

}
</script>

<style scoped>
#page{
  background-color: blue;
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