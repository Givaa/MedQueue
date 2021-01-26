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
        <strong class="capitalize">{{ $route.params.id }}</strong>
        <p>Benvenuto </p>
      </div>
    </ion-content>
  </ion-page>
</template>

<script>
export default {
  name: "test"
}
</script>

<style scoped>

</style>