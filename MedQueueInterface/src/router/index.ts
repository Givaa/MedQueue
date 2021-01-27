import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  {
    path: '',
    redirect: '/Home/Home'
  },
  {
    path: '/Home/:id',
    component: () => import ('../views/Home.vue')
  },
  {
    path: '/Visualizzazione Coda/:id',
    component: () => import ('../views/VisualizzaCoda.vue')
  },
  {
    path:'/Accesso/:id',
    component: () => import('../views/Login.vue')
  },
  {
    path:'/Registrazione/:id',
    component: () => import('../views/Registration.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
