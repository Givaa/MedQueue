import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  {
    path: '',
    redirect: '/Home'
  },
  {
    path: '/Home',
    component: () => import ('../views/Home.vue')
  },
  {
    path: '/VisualizzazioneCoda',
    component: () => import ('../views/VisualizzaCoda.vue')
  },
  {
    path:'/Accesso',
    component: () => import('../views/Login.vue')
  },
  {
    path:'/Registrazione',
    component: () => import('../views/Registration.vue')
  },
  {
    path:'/HomeUtente',
    component: () => import('../views/HomeUtente.vue')
  },
  {
    path:'/Prenotazione',
    component: () => import('../views/Prenotazione.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
