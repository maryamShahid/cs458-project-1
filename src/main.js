import { createApp } from 'vue'
import {createRouter, createWebHistory} from "vue-router"
import App from './App.vue'
import Home from '@/views/SignIn'
import SuccessPage from '@/views/SuccessPage'
import FacebookPage from '@/views/FacebookPage'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', name:'HomePage', component: Home},
        {path: '/success', name:'SuccessPage', component: SuccessPage},
        {path: '/facebook', name:'FacebookPage', component: FacebookPage}
    ]
})

createApp(App)
    .use(router)
    .mount('#app')
