import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '../views/Tabs.vue'
import Login from '../views/Login.vue'
import Appuntamenti from '../views/Appuntamenti.vue'

const routes: Array<RouteRecordRaw> = [

  {
    path:'/',
    component: Login
  },
  {
    path: '/appuntamenti',
    name: 'Appuntamenti',
    meta: { requiresAuth: true },
    component: Appuntamenti
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: 'active',
})

const isAuthenticated = sessionStorage.getItem('token')
console.log(isAuthenticated)
//let isAuthenticated = 'false'
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record  => record.meta.requiresAuth)
  const currentUser = isAuthenticated
  if (requiresAuth && !currentUser) {
    next('/')
  } else if (requiresAuth && currentUser) {
    next()
  } else {
    next()
  }
})
export default router
