import { createApp } from 'vue'
import {createRouter, createWebHistory} from "vue-router"
import App from './App.vue'
import Home from '@/views/SignIn'
import SuccessPage from '@/views/SuccessPage'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/', name:'HomePage', component: Home},
        {path: '/success', name:'SuccessPage', component: SuccessPage}
    ]
})

createApp(App)
    .use(router)
    .mount('#app')
