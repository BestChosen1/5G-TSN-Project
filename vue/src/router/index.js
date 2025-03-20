import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout";
import Layout_web from "../layout/Layout_web.vue";

const routes = [
  {
    path: '/',
    name: 'Layout',
    redirect:"dashboard",
    component: Layout,
    children:[
      {
        path:'user',
        name:'user',
        component:() => import("@/views/User")
      },
      {
        path: 'nftdetail',
        name: 'NFTDetail',
        component: () => import("@/views/NFTDetail")
      },
      {
        path:'cmt',
        name:'cmt',
        component:() => import("@/views/Cmt")
      },
              {
        path:'newmem',
        name:'newmem',
        component:() => import("@/views/NewMem")
      },
      {
        path: 'archive',
        name: 'archive',
        component: () => import("@/views/Archive")
      },
      {
        path: 'password',
        name: 'Password',
        component: () => import("@/views/Password")
      },
      {
        path:'dashboard',
        name:'Dashboard',
        component:() => import("@/views/Dashboard")
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
  },
  {
    path: '/web',
    name: 'Layout_web',
    redirect:"/web/nftview",
    component: Layout_web,
    children:[
      {
        path:'nftview',
        name:'NFTView',
        component:() => import("@/views/NFTView")
      },
      {
        path:'nftdetail',
        name:'NFTDetail',
        component:() => import("@/views/NFTDetail")
      },
      {
        path: 'person',
        name: 'Person',
        component: () => import("@/views/Person")
      },
    ]
  },
  {
    path: '/transaction',
    name: 'transaction',
    component: () => import("@/views/Transaction")
  },
  {
    path: '/trans',
    name: 'trans',
    component: () => import("@/views/Trans")
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
